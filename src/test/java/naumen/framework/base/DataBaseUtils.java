package naumen.framework.base;

import org.testng.SkipException;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Describes functions for working with Data Bases.
 */
public class DataBaseUtils extends BaseEntity {

	private static PropertiesResourceManager props = new PropertiesResourceManager("database.properties");

	private static String driverName;
	private static String ip;
	private static String port;
	private static String baseName;
	private static String serverUrl;
	private static String userName;
	private static String password;
	private static Connection connection;
	// количество возвращаемых результатов select-ов
	private final int maxReturnedRows = 20;
	// работа с динамическими скриптами
	private final String dynamicScriptName = "dynamic_script.sql";
	private final int loginTimeout = 40;

	/**
	 * Constructor.
	 */
	public DataBaseUtils() {
		this("");
	}

	/** cinstructor
	 * @param stage stage
	 */
	public DataBaseUtils(String stage) {
		if(connection == null){
			initializeDB(stage);
			connection = connectToDb(userName, password);
		}
	}

	/**
	 * Variables initialization.
	 * @param stage stage
	 */
	private void initializeDB(final String stage) {
		System.out.println("Connection DB info:");
		String serverUrlProp = null;
		driverName = props.getProperty("driverName"+stage);
		ip = props.getProperty("ip"+stage);
		port = props.getProperty("port"+stage);
		baseName = props.getProperty("baseName"+stage);
		serverUrlProp = props.getProperty("serverUrl"+stage);
		serverUrl = String.format(serverUrlProp, ip, port, baseName);
		userName = props.getProperty("userName"+stage);
		password = props.getProperty("password"+stage);
		String format = "driverName=%1$s; ip=%2$s; port=%3$s; baseName=%4$s; serverUrlProp=%5$s; serverUrl=%6$s; userName=%7$s; password=%8$s;";
		System.out.println(String.format(format, driverName, ip, port, baseName, serverUrlProp, serverUrl, userName, password));
	}

	/**
	 * Connects to DB
	 * @param user User
	 * @param pass Password
	 * @return Connection connection to database
	 */
	private Connection connectToDb(final String user, final String pass) {
		Connection connect = null;
		try {
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			DriverManager.setLoginTimeout(loginTimeout);
			connect = DriverManager.getConnection(serverUrl, user, pass);
			System.out.println("DB connection established");
		} catch (ClassNotFoundException e) {
			error("Unable to connect to the Database " + e.getMessage());
			throw new SkipException("Unable to connect to the Database!");
		} catch (Exception e) {
			error("Unable to connect to the Database " + e.getMessage());
			throw new SkipException("Unable to connect to the Database!");
		}
		return connect;
	}

	/**
	 * Refreshes connection.
	 */
	private void refreshConnection() {
		try {
			if (connection.isClosed()) {
				info("Connection was closed. Connecting...");
				connection = connectToDb(userName, password);
			}
		} catch (Exception e){
			debug(e.getMessage());
		}
	}

	/**
	 * Performs request.
	 * @param request Request.
	 * @return ResultSet results of request
	 */
	public HashMap<Integer, HashMap<String, String>> doRequestAndReturnHm(final String request){
		HashMap<Integer, HashMap<String, String>> result = null;
		Statement statement = null;
		refreshConnection();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE);
			System.out.println(request);
			statement.executeUpdate(request);
			result = getRsMap(statement.getResultSet());
			statement.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	/** convert resultSet to hasmap
	 * @param set resultset
	 * @return resulted hashmap
	 * @throws SQLException SQLException
	 */
	public HashMap<Integer, HashMap<String, String>> getRsMap(ResultSet set) throws SQLException{
		HashMap<String, String> setHm;
		HashMap<Integer, HashMap<String, String>> hm = new HashMap<Integer, HashMap<String, String>>();
		ResultSetMetaData rsmd = set.getMetaData();
		int i = 0;
		while(set.next()){
			setHm = new HashMap<String, String>();
			for(int j = 1; j <= rsmd.getColumnCount(); j++){
				setHm.put(rsmd.getColumnName(j), set.getNString(rsmd.getColumnName(j)));
			}
			hm.put(i, setHm);
			i++;
			// обычно не нужно иметь все значения полученные селектом
			if(i == maxReturnedRows){
				return hm;
			}
		}
		return hm;
	}

	/**
	 * Performs request.
	 * @param request Request.
	 * @return ResultSet results of request
	 */
	public String select(final String request){
		HashMap<Integer, HashMap<String, String>> res = null;
		for(int i = 0; i <= 1; i++){
			res = doRequestAndReturnHm(request);
			try{
				return res.get(0).get(res.get(0).keySet().toArray()[0]);
			}catch (NullPointerException e) {
				info("select не вернул значение в " + i + "-й раз.");
				try {
					Thread.sleep(browser.getTimeoutShortInt()/2);
				} catch (InterruptedException exeption) {
					System.out.println(exeption.getMessage());
				}
			}
		}
		return null;
	}

	/** формирует скрипт(сначала выполняется проверка на наличие файла со скриптом,если файла нет, то он формируется динамически)
	 * @param scriptName наименование скрипта или сам скрипт(при динамическом формировании)
	 * @return скрипт
	 */
	public String getScript(String scriptName){
		File f = new File("./src/test/resources/scripts/" + System.getProperty("hostname") + "/" + scriptName + ".sql");
		if(!f.exists()){
			f = new File("../src/test/resources/scripts/" + System.getProperty("hostname") + "/" + scriptName + ".sql");
			if(!f.exists()){
				info("Не удалось найти скрипт по пути: " + "/src/test/resources/scripts/" + System.getProperty("hostname") + "/" + scriptName + ".sql");
				info("Скрипт будет сформирован динамически...");
				f = createScriptFile(scriptName);
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			int lineNumber = getLineNumber(br);
			br = new BufferedReader(new FileReader(f));
			return linesToOne(br, lineNumber);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/** returns quantity of lines in the script file
	 * @param reader reader
	 * @return quantity of lines in the script file
	 */
	private int getLineNumber(BufferedReader reader){
		int counter = 0;
		try{
			while((reader.readLine()) != null){
				counter++;
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		return counter;
	}

	/** concat all lines in the script file to one line
	 * @param reader reader
	 * @param lineNumber quantity of lines
	 * @return result line
	 */
	private String linesToOne(BufferedReader reader, int lineNumber){
		String line;
		String script = "";
		try{
			if(lineNumber == 1){
				return reader.readLine().replace(";", "");
			}
			while((line = reader.readLine()) != null){
				script +=  line + "\n\r";
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		return script;
	}

	/** Создает файл со скриптом
	 * @param script скрипт
	 * @return путь к файлу
	 */
	private File createScriptFile(String script){
		File f = new File(dynamicScriptName);
		if(f.exists()){
			f.delete();
		}
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.append(script);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	/**
	 * Performs request.
	 * @param request Request.
	 * @param columnLabel columnLabel
	 * @return ResultSet results of request
	 */
	@SuppressWarnings("deprecation")
	public String select(final String request,final String columnLabel){
		System.out.println("Выполняем запрос: \n\r" + request);
		HashMap<Integer, HashMap<String, String>> hmSet = null;
		for(int i = 0; i <= 1; i++){
			hmSet = doRequestAndReturnHm(request);
			try{
				return hmSet.get(0).get(columnLabel);
			}catch (NullPointerException e) {
				info("select не вернул значение в " + i + "-й раз.");
				browser.sleep(browser.getTimeoutShortInt()/2);
			}
		}
		return null;
	}

	/**
	 * Performs request.
	 * @param request Request.
	 * @return ResultSet results of request
	 */
	public List<String> selectAll(final String request){
		HashMap<Integer, HashMap<String, String>> res = null;
		List<String> rows = new ArrayList<String>();
		res = doRequestAndReturnHm(request);
		for(Integer key : res.keySet()){
			HashMap<String, String> hm = res.get(key);
			rows.add(res.get(key).get(hm.keySet().toArray()[0]));
		}
		return rows;
	}

	/**
	 * Performs request.
	 * @param request Request.
	 * @param columnLabels columnLabel
	 * @return ResultSet results of request
	 */
	public List<LinkedHashMap<String, String>> selectAll(final String request,final List<String> columnLabels){
		HashMap<Integer, HashMap<String, String>> res = null;
		List<LinkedHashMap<String, String>> rows = new ArrayList<LinkedHashMap<String, String>>();
		try {
			res = doRequestAndReturnHm(request);
			for(Integer key : res.keySet()){
				LinkedHashMap<String, String> columns = (LinkedHashMap<String, String>) res.get(key);
				rows.add(columns);
			}
			return rows;
		} catch (Exception e) {
			info("select не вернул значение.");
		}
		return null;
	}

	/**
	 * Performs query which may be an INSERT, UPDATE, or DELETE statement.
	 * @param query Query
	 * @return Integer result
	 */
	public int doUpdate(final String query){
		int result = 0;
		refreshConnection();
		try {
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(query);

		} catch (SQLException e) {
			fatal(e.getMessage());
		}
		return result;
	}

	@Override
	protected String formatLogMsg(final String message) {
		String formattedMessage = String.format("Database: '%1$s'.", message);
		return formattedMessage;
	}
}
