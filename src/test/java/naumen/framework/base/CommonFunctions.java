package naumen.framework.base;

import net.lightbody.bmp.core.har.Har;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonFunctions {
	// ==============================================================================================
	// Methods for using Regular expressions

	public static final String MM_DD_YYYY = "MM-dd-yyyy";
	public static final String MMM_YYYY = "MMM-yyyy";
	public static final String MM_YYYY = "MM-yyyy";
	private static final int MAX_RAND = 8;
	private static Boolean serFilesIsCopied = false;

	/** Копирует файлы сериализации полученные как артифакты
	 * вышестоящего проекта в workspace. Путь к этим артифактам:  workspace/ser/target/*.ser
	 */
	@SuppressWarnings("static-access")
	public static void copySerializationFiles(){

		FileUtils fileUtils = new FileUtils();
		if(!serFilesIsCopied){
			try{
				// Копирование файлов из дирректории debugfiles
		          if(System.getProperty("useDebugFiles")!=null && System.getProperty("useDebugFiles").equalsIgnoreCase("true")){
		        	  File f1 = new File("./src/test/resources/debugfiles");
			          if(!f1.exists()){
			        	  f1 = new File("../src/test/resources/debugfiles");
			        	 if(!f1.exists()){
			        		 System.out.println("ОТСУТСТВУЮТ ФАЙЛЫ СЕРИАЛИЗАЦИИ ИЗ ДИРЕКТОРИИ 'src/recources/debugfiles/'");
			        		 return;
			        	 }
			          }
			          f1 = new File(f1.getAbsolutePath());
			          File f2 = new File("./");
			          fileUtils.copyDirectory(f1, f2);
			          System.out.println("ФАЙЛЫ ИЗ ДИРРЕКТОРИИ 'src/recources/debugfiles/' СКОПИРОВАНЫ\n(чтобы отключить копирование делайте запуск без параметра -DuseDebugFiles)");
		          }
		          // Копирование файлов из вышестоящих проектов
		          File f1 = new File("./ser/target/");
		          if(!f1.exists()){
		        	  f1 = new File("../ser/target/");
		        	 if(!f1.exists()){
		        		 System.out.println("ОТСУТСТВУЮТ ФАЙЛЫ СЕРИАЛИЗАЦИИ ИЗ ВЫШЕСТОЯЩИХ ПРОЕКТОВ");
		        		 return;
		        	 }
		          }
		          f1 = new File(f1.getAbsolutePath());
		          File f2 = new File("./");
		          fileUtils.copyDirectory(f1, f2);
		          System.out.println("ФАЙЛЫ СЕРИАЛИЗАЦИИ ИЗ ПРЕДЫДУЩЕГО ПРОЕКТА СКОПИРОВАНЫ");
		          serFilesIsCopied = true;
		        }catch(FileNotFoundException ex){
		          System.out.println(ex.getMessage() + " in the specified directory.");
		        }catch(IOException e){
		          System.out.println(e.getMessage());
		        }
		}
		serFilesIsCopied = true;
	}

	/**
	 * This method creates a RegExp pattern.
	 * @param regex pattern in a string
	 * @param matchCase should be matching case sensitive?
	 */
	private static Pattern regexGetPattern(String regex, boolean matchCase) {
		int flags;
		if (matchCase) {
			flags = 0;
		} else {
			flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
		}
		return Pattern.compile(regex, flags);
	}

	/** извлекает название файла из пути к нему
	 * @param path путь к файлу
	 * @return название файла
	 */
	public static String getFileName(String path){
		return  regexGetMatchGroup(path, "\\w+[.]\\w+", 0);
	}

	/** конвертирует дату в тайстемп
	 * @param date дата
	 * @param format формат даты(например dd.MM.yyyy)
	 * @return таймстемп
	 */
	public static String convertDateToTimeStamp(String date, String format){
		long startDate = 0;
		try {
			startDate = new SimpleDateFormat(format).parse(date).getTime();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return String.valueOf(startDate);
	}

	/**
	 * Get first match in the string
	 */
	public static String regexGetMatch(String text, String regex) {
		return regexGetMatch(text, regex, false);
	}

	/**
	 * Get first match in the string
	 */
	public static String regexGetMatch(String text, String regex, boolean matchCase) {
		return regexGetMatchGroup(text, regex, 0, matchCase);
	}

	/**
	 * validate, that string corresponds a pattern
	 */
	public static boolean regexIsMatch(String text, String pattern) {
		return regexIsMatch(text, pattern, false);
	}

	/**
	 * validate, that string corresponds a pattern
	 */
	public static boolean regexIsMatch(String text, String regex, boolean matchCase) {
		Pattern p = regexGetPattern(regex, matchCase);
		Matcher m = p.matcher(text);
		return m.find();
	}

	/**
	 * Get the N-th matching group
	 * @param text - String where we are looking for
	 * @param regex - pattern for which we are looking for
	 * @param groupIndex -Number of matching group we want to find
	 */
	public static String regexGetMatchGroup(String text, String regex, int groupIndex) {
		return regexGetMatchGroup(text, regex, groupIndex, false);
	}

	/**
	 * Get the N-th matching group
	 * @param text - String where we are looking for
	 * @param regex - pattern for which we are looking for
	 * @param groupIndex - Number of matching group we want to find
	 * @param matchCase - Is search case sensitive?
	 */
	public static String regexGetMatchGroup(String text, String regex, int groupIndex, boolean matchCase) {
		Pattern p = regexGetPattern(regex, matchCase);
		Matcher m = p.matcher(text);
		if (m.find()) {
			return m.group(groupIndex);
		} else {
			return null;
		}
	}

	/**
	 * Get the casesCount of groups has been found
	 * @param text - String where we are looking for
	 * @param regex - pattern for which we are looking for * @param matchCase - Is search case sensitive?
	 */
	public static int regexGetNumberMatchGroup(String text, String regex) {
		return regexGetNumberMatchGroup(text, regex, false);
	}

	/**
	 * Get the casesCount of groups has been found
	 * @param text - String where we are looking for
	 * @param regex - pattern for which we are looking for
	 */
	public static int regexGetNumberMatchGroup(String text, String regex, boolean matchCase) {
		int number = 0;
		Pattern p = regexGetPattern(regex, matchCase);
		Matcher m = p.matcher(text);
		while (m.find()) {
			m.group();
			number++;
		}
		return number;
	}

	// ==============================================================================================
	// Methods for using Date

	/**
	 * get current date in the custom pattern
	 */
	public static String getCurrentDate(String pattern) {
		return formatDate(new Date(), pattern);
	}

	/**
	 * get current date in the custom pattern
	 */
	public static String getCurrentDate(String pattern, int add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, add);
		Date afterAdd = calendar.getTime();
		return formatDate(afterAdd, pattern);

	}

	/**
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateEnLocale(String pattern) {
		return new SimpleDateFormat(pattern, new Locale("en", "EN")).format(new Date());
	}

	/**
	 * {@link #getCurrentDateEnLocale(String)} with arg "MM-dd-yyyy"
	 * @return
	 */
	public static String getCurrentDateEnLocale() {
		return getCurrentDateEnLocale(MM_DD_YYYY);
	}

	/**
	 * Get the unic suffix based on the current date
	 */
	public static String getTimestamp() {
		return getCurrentDate("yyyyMMddHHmmss");
	}

	/**
	 * Parse string to the Calendar entity
	 * @param s - String to be converted
	 */
	public static Calendar dateString2Calendar(String s) throws ParseException {
		Calendar cal = Calendar.getInstance();
		Date d1 = new SimpleDateFormat("dd.mm.yyyy").parse(s);
		cal.setTime(d1);
		return cal;
	}

	/**
	 * Format date to string using custom pattern
	 * @param date - date to be formatted
	 * @param pattern - custom pattern of the date
	 */
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * Format date to string using 'en' locale
	 * @param date - date to be formatted
	 * @param pattern - custom pattern of the date
	 */
	public static String formatDateEnLocation(Date date, String pattern) {
		return new SimpleDateFormat(pattern,  new Locale("en", "EN")).format(date);
	}
	/**
	 * Format date in the "dd.MM.yyyy" pattern
	 * @param date - date to be formatted
	 */
	public static Date parseDate(String date) {
		return parseDate(date, "dd.mm.yyyy");
	}

    public static void saveHarFile(Har har, String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            try {
                har.writeTo(fos);
            }
            finally {
                fos.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Save text to a file
     * @param filename
     * @param text
     * @throws IOException
     */
    public static void saveTextFile(String filename, String text) throws IOException{
        File flt = new File(filename);
        FileWriter wrt = new FileWriter(flt);
        wrt.append(text);
        wrt.flush();
    }

    /**
     * Save text to a file
     * @param filename
     * @param text
     * @throws IOException
     */
    public static void saveTextFile(String filename, StringBuffer text) throws IOException{
        File flt = new File(filename);
//        FileWriter wrt = new FileWriter(flt);
        PrintWriter wrt = new PrintWriter(flt, "UTF-8");
        wrt.append(text);
        wrt.flush();
    }

    public static boolean createFolder(String folder){
        String folderPath = System.getProperty("user.dir") + folder;
        return new File(folderPath).mkdir();
    }

    /**
     * Read text from a file
     * @param filename
     * @return text
     * @throws IOException
     */
    public static String readTextFromFile(String filename) throws IOException{
        StringBuilder sb = new StringBuilder();
        File flt = new File(filename);
            BufferedReader in = new BufferedReader(new FileReader(flt.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        return sb.toString();
    }
	/**
	 * Parse string to the Date entity
	 * @param date - string to be parsed
	 * @param pattern custom pattern of the date, according to which string should be parsed
	 */
	public static Date parseDate(String date, String pattern) {
		Date result = null;
		try {
			result = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Increase date to the N days
	 * @param date date to be increased
	 * @param days casesCount of the days
	 * @return date increased by N days
	 */
	public static Date increaseDateByXDays(final Date date, final int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	// ==============================================================================================
	// Methods to escape special symbols

	/**
	 * Escape () and backslash
	 * @param text - text with special symbols
	 * @return escaped text
	 */
	public static String escapeMetaCharacters(final String text) {
		return text.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
	}

	/**
	 * Escape backslash and quote
	 * @param text - text with special symbols
	 * @return text without backslashes
	 */
	public static String escapeSeleniumCharacters(final String text) {
		return text.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\\\"");
	}

	/**
	 * Adding \s to the numbers in the string (useful in validation, if there is a space between digits ("99 678" for
	 * example))
	 * @param text - text with numbers
	 * @return escapeSpacesFromNumbers text
	 */
	public static String escapeSpacesFromNumbers(final String text) {
		return text.replaceAll("(\\d)", "$1\\\\s*");
	}

	// ==============================================================================================
	// Environment Methods

	/**
	 * РЈСЃС‚Р°РЅР°РІР»РёРІР°РµС‚ РєСѓСЂСЃРѕСЂ РјС‹С€РєРё РІ С†РµРЅС‚СЂ СЌРєСЂР°РЅР° Moves the mouse pointer to the center of screen
	 */
	public static void centerMouse() {
		try {
			Robot robot = new Robot();
			int x = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
			int y = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
			robot.mouseMove(x / 2, y / 2);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	// =============================================================================
	/** Возвращает нужную дату в заданном формате
	 * @param dateFormat формат даты
	 * @param addDay количество дней вперед, начиная от текущей даты
	 * @return дата в нужном формате
	 */
	public static String getDate(String dateFormat, int addDay){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, addDay);
		Date date = (Date) calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		formatter.setTimeZone(TimeZone.getTimeZone("Etc/GMT-4"));
		return  formatter.format(date);
	}

	public static String convertNumberToText(String number){
		String[] letters = new String[]{
				"а","б","в","г","д","е","ж","з","и","к"
		};
		String text = "";
		for(Character c : number.toCharArray()){
			try{
				text = text + letters[Integer.parseInt(c.toString())];
			}catch (Exception e) {
				text = text + c;
			}
		}
		return text;
		
	}

    /**
     * является ли строка десятичным числом, возможно отрицательным
     * @param str
     * @return да, если да
     */
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static boolean isWholeNumeric(String str){
        return str.matches("-?\\d+(\\.\0+)?");  //match a number with optional '-' and decimal.
    }

    /**
     * является ли данная строка целым числом?
     * числа вида n.(0)*, например 10.0, -123.000, считаются целыми
     * @param text
     * @return true, если является
     */
    public static boolean isWholeNumber(String text){
        text = text.replace(',', '.');
        if (!CommonFunctions.isNumeric(text))
            return false;
        else{
            if(!text.contains("."))
                return true;
            else{
                int size = text.split("\\.").length;
                text = text.split("\\.")[size - 1];
                if (text.equals("") || Integer.parseInt(text) > 0)
                    return false;
                else
                    return true;
            }
        }
    }

	/** Возвращает дату смещенную относительно другой даты
	 * @param relavityDate точка смещения
	 * @param format формат  даты смещения
	 * @param shift сдивг(может быть как + так и -)
	 * @return результат смещения
	 */
	public static String getDateRelavity(String relavityDate, String format, int shift){
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = dateFormat.parse(relavityDate);
		}catch(Exception e){
			System.out.println("Cannot convert date: " + relavityDate + " to format: " + format);
		}
		calendar.setTime(d);
		calendar.add(Calendar.DATE, shift);
		return dateFormat.format(calendar.getTime());
	}

	/** Возвращает текущее время в формате "dd.MM.yyyy HH:mm"
	 * @return время в формате "dd.MM.yyyy HH:mm"
	 */
	public static String getCurrentDateTime(){
		return getDate("dd.MM.yyyy HH:mm", 0);
	}

	/** Возвращает случайную дату из периода
	 * @param beginDate начало периода (dd.MM.yyyy)
	 * @param endDate конец периода (dd.MM.yyyy)
	 * @return дата (dd.MM.yyyy)
	 */
	public static String getDateFromPeriod(String beginDate, String endDate){
		String format = "dd.MM.yyyy";
		long begin = getTimestamp(format, beginDate);
		long end = getTimestamp(format, endDate);
		boolean less = false;
		long rand = 0;
		while(!less){
			rand = Math.abs(new Random().nextLong()/(new Random().nextInt(1000) + 1000));
			if(rand < (end - begin)){
				less = true;
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(end - rand);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}

	/** Возвращает timestamp даты
	 * @param format формат
	 * @param date дата
	 * @return timestamp
	 */
	private static long getTimestamp(String format, String date){
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(d);
		return cal.getTimeInMillis();
	}

	/** Преобразовывает дату в нужный формат
	 * @param date дата
	 * @param oldFormat старый формат
	 * @param newFormat новый формат
	 * @return дата в нужном формате
	 */
	@SuppressWarnings("serial")
	public static String convertDate(String date, String oldFormat, String newFormat){
		 DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){
			 @Override
		        public String[] getMonths() {
		            return new String[]{"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня",
		                "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"};
		        }
		    };
		SimpleDateFormat df = new SimpleDateFormat(oldFormat, myDateFormatSymbols);
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			Logger.getInstance().info("Переданная дата " + date + " не в нужном формате: " + oldFormat);
			e.printStackTrace();
		}
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        df = new SimpleDateFormat(newFormat, myDateFormatSymbols);
        return df.format(cal.getTime());
	}
	
	

	/**
	 * Получить полный путь к файлу, если существует
	 * @param path имя
	 * @return полный путь
	 */
	public static String getPathOfFile(String path) {
		File f = new File("." + path);
		if(!f.exists()){
			f = new File(".." + path);
			if(!f.exists()){
				Logger.getInstance().fatal("Файл: " + path + " не найден!");
			}
		}
		return f.getAbsolutePath();
	}

	/** Возвращает случайное значение определенной длинны
	 * @param length необходимая длина числа
	 * @return случайное значение нужной длины
	 */
	public static String getRandomValue(int length){
		Random r = new Random();
		String value = "";
		for(int i = 0; i < length; i++){
			value = value + String.valueOf(r.nextInt(MAX_RAND) + 1);
		}
		return value;
	}

	private static String[] words = {"Авто ","генерируемый ", "Текст ", "Белые ",
			"медведи ", "ходят,", "едят ", "полярников,", "Экспедиция ", "успешна ", "мороз ", "кушать "};
	/** Возвращает случайное значение определенной длинны (Строка)
	 * @param length необходимая длина числа
	 * @return случайное значение нужной длины
	 */
	public static String getRandomStringValue(int length){
		Random r = new Random();
		String value = "";
		for(int i = 0; i < length/5; i++){
			value = value + words[r.nextInt(MAX_RAND)];
		}
		value = value.substring(0, length-2) + ".";
		return value;
	}

	/** Возвращает текущую дату в формате dd.MM.yyyy
	 * @return дата в формате dd.MM.yyyy
	 */
	public static String getCurrentDate(){
		return getDate("dd.MM.yyyy", 0);
	}

	/** Возвращает текущую дату или любую будущую дату, которая не является рабочим днем
	 * @return текущую дату или любую будущую дату, которая не является рабочим днем
	 */
	public static String getNoWorkingDay(){
		DataBaseUtils db = new DataBaseUtils(Browser.getDbPrefix());
		for(int i = 0; i<= 30; i++){
			if(db.select(db.getScript("getCurrentDayInfo").replace("&&day_date", getDate("dd.MM.yy", i))) != null){
				continue;
			}else{
				return  getDate("dd.MM.yyyy", i);
			}
		}
		Logger.getInstance().warn("В течении целого месяца нет ни одного рабочего дня!");
		return null;
	}

	/** Возвращает текущее время в формате HH:mm
	 * @return текущее время в формате HH:mm
	 */
	public static String getCurrentTime(){
		return getDate("HH:mm", 0);
	}

	/** Разбивает число больше 1000 в группы по три через пробел,
	 * например если на входе '3300', то на выходе будет '3 300'
	 * @param number разбиваемое число
	 * @return разбитое число
	 */
	public static String convertNumberToFormat(String number){
		if(number == null){
			return "";
		}
		boolean isNegative = false;
		if(!number.matches("[-]*[0-9]+[.,]*[0-9]*")){
			return number;
		}
		if(number.substring(0, 1).equals("-")){
			number = number.substring(1);
			isNegative = true;
		}
		String mNumber = "";
		String invertNumber = "";
		String fractionalPart = "";
		String fractionalDivider = "";
		// Если число содержит точку или запятую, то обрезаем его до точки
		if(number.contains(".")||number.contains(",")){
			if(number.contains(".")){
				fractionalDivider = ".";
			}else if(number.contains(",")){
				fractionalDivider = ",";
			}
			fractionalPart = number.substring(number.indexOf(fractionalDivider) + 1);
			number = number.substring(0, number.indexOf(fractionalDivider));
		}
		// Если количество символов больше трех, то делаем разбивку по три символа через пробел
		if(number.length() > 2 + 1){
			// Получаем инвертированное число
			for(int i = number.length() - 1; i >= 0; i--){
				invertNumber = invertNumber + number.substring(i, i + 1);
			}
			// Разбиваем число
			for(int i = 0; i < invertNumber.length(); i++){
				if((i + 1) % (2 + 1) == 0){
					mNumber = mNumber + invertNumber.substring(i,i + 1) + " ";
				}else{
					mNumber = mNumber + invertNumber.substring(i,i + 1);
				}
			}
			// Делаем обратную инверсию числа
			number = "";
			for(int i = mNumber.length() - 1; i >= 0; i--){
				number = number + mNumber.substring(i,i+1);
			}
		}
		// Работа с дробной частью:
		// Если один знак после точки/запятой, то добавляем 0
		// Иначе оставляем как есть
		if(fractionalPart.length() < 1){
			fractionalPart = "00";
		}else if(fractionalPart.length() < 2){
			fractionalPart = fractionalPart + "0";
		}
		String returnValue = number + "," + fractionalPart;
		if(returnValue.substring(0,1).equals(" ")){
			returnValue = returnValue.substring(1);
		}
		if(isNegative){
			returnValue = "-" + returnValue;
		}
		return returnValue;
	}
	
	public static String getRandomStringByDate(){
		String date=getDate("dd.MM.yyyyHH:mm:ss", 0);
		date=date.replace(Integer.toString(0), "ф");
		date=date.replace(Integer.toString(1), "ы");
		date=date.replace(Integer.toString(2), "в");
		date=date.replace(Integer.toString(3), "а");
		date=date.replace(Integer.toString(4), "п");
		date=date.replace(Integer.toString(5), "р");
		date=date.replace(Integer.toString(6), "о");
		date=date.replace(Integer.toString(7), "л");
		date=date.replace(Integer.toString(8), "д");
		date=date.replace(Integer.toString(9), "ж");
		date=date.replace(".", "э");
		date=date.replace(":", "й");
		return date;
	}

    public static String getRandomLatinStringByDate(){
        String date=getDate("dd.MM.yyyyHH:mm:ss", 0);
        date=date.replace(Integer.toString(0), "f");
        date=date.replace(Integer.toString(1), "y");
        date=date.replace(Integer.toString(2), "h");
        date=date.replace(Integer.toString(3), "e");
        date=date.replace(Integer.toString(4), "i");
        date=date.replace(Integer.toString(5), "o");
        date=date.replace(Integer.toString(6), "p");
        date=date.replace(Integer.toString(7), "w");
        date=date.replace(Integer.toString(8), "s");
        date=date.replace(Integer.toString(9), "d");
        date=date.replace(".", "a");
        date=date.replace(":", "z");
        return date;
    }
}
