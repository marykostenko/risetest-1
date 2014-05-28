package naumen.framework.base;

import naumen.framework.base.exceptions.NoDefaultFiltersException;
import naumen.framework.base.exceptions.NoDocumentsTreeException;
import naumen.framework.base.exceptions.NoSbiExecutionIdException;
import naumen.framework.base.exceptions.NoUserRoleException;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


/** предоставляет возможность создавать сущности посредством http запросов
 */
public class HttpUtils extends BaseEntity{

//	private final int successCode = 200;
    private List<Integer> acceptedCodes = Arrays.asList(200, 302);
	private static String response;
    private static JSONObject documentsTree = new JSONObject();
    private static String userRole = "";

	/** возвращает response
	 * @return response
	 */
	public static String getResponse(){
		return response;
	}

	/** конвертирует в utf-8
	 * @param message сообщения
	 * @return конвертация
	 */
	public static String convert(String message) {
    	byte[] bytesInUTF8 = null;
		try {
			bytesInUTF8 = message.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	return new String(bytesInUTF8);
	}

    /** Выполняет запрос с сохранением ответа в файл
     * @param url url
     * @param formparams параметры
     * @param cookieStore cookies
     * @param logName текст лога
     * @param saveFileName имя файла, куда нужно сохранить содержимое ответа на запрос
     * @return cookies from response
     */
    public DefaultHttpClient executePostRequest(String url, List<NameValuePair> formparams, CookieStore cookieStore,
                                                String logName, String saveFileName){
        response = null;
        DefaultHttpClient httpClient = null;
        for(int i = 0; i <= 2; i++){
            try{
                httpClient = new DefaultHttpClient();
//				logger.info("POST request to url: " + url);
                HttpPost httpPost = new HttpPost(url);
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
                httpPost.setEntity(entity);
                if(cookieStore != null){
                    httpClient.setCookieStore(cookieStore);
                }
                HttpResponse httpResponse = httpClient.execute(httpPost);
                saveEntityToFile(httpResponse.getEntity(), saveFileName);
                BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), Charset.forName("UTF-8")));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line + "\r\n");
                }
                response = result.toString();
                if(acceptedCodes.contains(httpResponse.getStatusLine().getStatusCode())){
                    logger.info("POST-запрос на: " + logName + " завершился успешно");
                }else{
                    System.out.println(result.toString());
                    logger.fatal("POST-запрос на: " + logName + " завершился неудачей");
                }
                break;
            }catch (Exception e) {
                continue;
            }
        }
        return httpClient;
    }

	/** Выполняет запрос
	 * @param url url
	 * @param formparams параметры
	 * @param cookieStore cookies
	 * @param logName текст лога
	 * @return cookies from response
	 */
	public DefaultHttpClient executePostRequest(String url, List<NameValuePair> formparams, CookieStore cookieStore, String logName){
        response = null;
        DefaultHttpClient httpClient = null;
        for(int i = 0; i <= 2; i++){
			try{
				httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
			    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			    httpPost.setEntity(entity);
			    if(cookieStore != null){
			    	httpClient.setCookieStore(cookieStore);
			    }
			    HttpResponse httpResponse = httpClient.execute(httpPost);
			    BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), Charset.forName("UTF-8")));
                StringBuffer result = new StringBuffer();
		    	String line = "";
		    	while ((line = rd.readLine()) != null) {
		    		result.append(line + "\r\n");
		    	}
		    	response = result.toString();
		    	if(acceptedCodes.contains(httpResponse.getStatusLine().getStatusCode())){
			    	logger.info("POST-запрос на: " + logName + " завершился успешно");
			    }else{
			    	System.out.println(result.toString());
			    	logger.fatal("POST-запрос на: " + logName + " завершился неудачей");
			    }
                break;
			}catch (Exception e) {
				continue;
			}
		}
		return httpClient;
	}

	/** Выполняет запрос
	 * @param url url
	 * @param cookieStore cookies
	 * @param logName текст лога
	 * @return cookies from response
	 */
	public HttpClient executeGetRequest(String url, CookieStore cookieStore, String logName){
		response = null;
        DefaultHttpClient httpClient = null;
        for(int i = 0; i <=2; i++){
			try{
				httpClient = new DefaultHttpClient();
				logger.info("GET request to url: " + url);
				HttpGet httpGet = new HttpGet(url);
				if(cookieStore != null){
			    	httpClient.setCookieStore(cookieStore);
			    }
			    HttpResponse httpResponse = httpClient.execute(httpGet);
			    BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), Charset.forName("UTF-8")));
		    	StringBuffer result = new StringBuffer();
		    	String line = "";
		    	while ((line = rd.readLine()) != null) {
		    		result.append(line + "\r\n");
		    	}
		    	response = result.toString();
			    if(acceptedCodes.contains(httpResponse.getStatusLine().getStatusCode())){
			    	logger.info("GET-запрос на: " + logName + " завершился успешно");
				}else{
			    	System.out.println(result.toString());
                    logger.warn("HTTP RESPONSE CODE=" + httpResponse.getStatusLine().getStatusCode());
			    	logger.fatal("GET-запрос на: " + logName + " завершился неудачей");
			    }
			    break;
			}catch (Exception e) {
				continue;
			}
		}
		return httpClient;
	}

	@Override
	protected String formatLogMsg(String message) {
		return message;
	}

    private static HttpClient trustEveryoneSslHttpClient() {
        try {
            SchemeRegistry registry = new SchemeRegistry();

            SSLSocketFactory socketFactory = new SSLSocketFactory(new TrustStrategy() {

                public boolean isTrusted(final X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }

            }, org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            registry.register(new Scheme("https", 443, socketFactory));
            registry.register(new Scheme("http", 443, socketFactory));
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
            DefaultHttpClient client = new DefaultHttpClient(mgr, new DefaultHttpClient().getParams());
            return client;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * выполняет GET-запрос, клиент доверяет всем сертификатам безопасности
     * @param url
     * @return response for request
     */
    public HttpResponse executeGetRequest(String url){
        HttpClient client = trustEveryoneSslHttpClient();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            return response;
        }
        catch (IOException ioe) {
            logger.info(ioe.toString());
            ioe.printStackTrace();
            return null;
        }
    }
    /**
     * выполняет GET-запрос
     * @param client
     * @param url
     * @return response for request
     */
    public HttpResponse executeGetRequest(HttpClient client, String url){
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            return response;
        }
        catch (IOException ioe) {
            logger.info(ioe.toString());
            ioe.printStackTrace();
            return null;
        }
    }

    /**
     * GET-запрос и получение DOCUMENTS_TREE
     * @return documents tree
     */
    public JSONObject getDocumentsTree() throws NoDocumentsTreeException {
        String requestURL = browser.getRedirectedURL() + "/BIMON/servlet/AdapterHTTP?ACTION_NAME=DOCUMENTS_TREE";
        CookieStore cookieStore = getCurrentSessionCookieStore();
        executeGetRequest(requestURL, cookieStore, "Получение Documents Tree");
        if (!response.equals(null)) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject parseObj = (JSONObject) parser.parse(response);
                documentsTree = (JSONObject) parseObj.get("documents_tree");
            }
            catch (org.json.simple.parser.ParseException pe) {
                logger.warn("не удалось распарсить json-строку с documents tree");
                throw new NoDocumentsTreeException();
            }
        }
        return documentsTree;
    }

    /**
     * вернуть куки текущей сессии
     */
    public CookieStore getCurrentSessionCookieStore() {
        Set<org.openqa.selenium.Cookie> cookieSet = browser.getDriver().manage().getCookies();
        CookieStore cookieStore = new BasicCookieStore();
        for(org.openqa.selenium.Cookie cookie: cookieSet) {
            BasicClientCookie apacheCookie = new BasicClientCookie(cookie.getName(), cookie.getValue());
//          В IE передаются корявые куки
            if (browser.getDriver().getCapabilities().getBrowserName().toLowerCase().contains("explore")) {
                apacheCookie.setDomain("bi.fcntp.ru");
                apacheCookie.setPath("/BIMON");
            }
            else {
                apacheCookie.setDomain(cookie.getDomain());
                apacheCookie.setPath(cookie.getPath());
            }
            cookieStore.addCookie(apacheCookie);
            logger.info("cookie=" + cookie.toString());
        }
        return cookieStore;
    }

    /**
     * используя documents_tree,
     * получить роль пользователя для открытия данного отчета
     * @return роль пользователя для открытия данного отчета
     */
    public String getRole(JSONObject report) throws NoUserRoleException {
        try {
            userRole = (String) ((JSONObject)((JSONObject) report.get("url")).get("extend")).get("ROLE");
        }
        catch(Exception e) {
            throw new NoUserRoleException();
        }
        return userRole;
    }

    /**
     * получение фильтров по умолчанию для отчета
     * @param id
     * @param label
     * @param role
     * @param sbiExecutionId
     * @return JSONObject defaultParameters
     */
    public JSONObject getDefaultParameters(String id, String label, String role, String sbiExecutionId) throws NoDefaultFiltersException {
        JSONObject defaultParameters;
        String requestURL = browser.getRedirectedURL() +
                "/BIMON/servlet/AdapterHTTP?ACTION_NAME=GET_VIEWPOINTS_ACTION&LIGHT_NAVIGATOR_DISABLED=TRUE";
        CookieStore cookieStore = getCurrentSessionCookieStore();
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("OBJECT_ID", id));
        formparams.add(new BasicNameValuePair("OBJECT_LABEL", label));
        formparams.add(new BasicNameValuePair("isFromCross", "false"));
        formparams.add(new BasicNameValuePair("ROLE", role));
        formparams.add(new BasicNameValuePair("SBI_EXECUTION_ID", sbiExecutionId));
        executePostRequest(requestURL, formparams, cookieStore, "Получение фильтров по умолчанию");
        JSONParser parser = new JSONParser();
        try {
            JSONObject resp = (JSONObject)parser.parse(response);
            JSONObject obj = (JSONObject)((JSONArray) resp.get("results")).get(0);
            defaultParameters = (JSONObject) obj.get("parameters");
        }
        catch (org.json.simple.parser.ParseException pe) {
            logger.fatal("не удалось распарсить ответ на запрос");
            throw new NoDefaultFiltersException();
        }
        catch (NullPointerException npe) {
            logger.fatal("в ответе на запрос отсутствует искомое значение");
            throw new NoDefaultFiltersException();
        }

        return defaultParameters;
    }

    /**
     * делает POST-запрос для получения SBI_EXECUTION_ID
     * @param role
     * @param id
     * @param label
     * @return String sbiExecutionId
     * @throws NoSbiExecutionIdException
     */
    public String getSbiExecutionId (String id, String label, String role) throws NoSbiExecutionIdException {
        String sbiExecutionId = "";
        String requestURL = browser.getRedirectedURL() +
                "/BIMON/servlet/AdapterHTTP?ACTION_NAME=START_EXECUTION_PROCESS_ACTION&LIGHT_NAVIGATOR_DISABLED=TRUE";
        CookieStore cookieStore = getCurrentSessionCookieStore();
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("isFromCross", "false"));
        formparams.add(new BasicNameValuePair("ROLE", role));
        formparams.add(new BasicNameValuePair("OBJECT_ID", id));
        formparams.add(new BasicNameValuePair("OBJECT_LABEL", label));
        executePostRequest(requestURL, formparams, cookieStore, "Получение SBI_EXECUTION_ID");
        JSONParser parser = new JSONParser();
        try {
            JSONObject resp = (JSONObject)parser.parse(response);
            sbiExecutionId = (String) resp.get("execContextId");
        }
        catch (org.json.simple.parser.ParseException pe) {
            logger.warn("не удалось распарсить ответ на запрос");
            throw new NoSbiExecutionIdException();
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
            logger.fatal("в ответе на запрос отсутствует искомое значение");
            throw new NoSbiExecutionIdException();
        }
        return sbiExecutionId;
    }

    /**
     * сохранить содержимое HttpEntity в файл
     * @param ent HttpEntity
     * @param filename  имя файла
     */
    public void saveEntityToFile(HttpEntity ent, String filename){
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            try {
                ent.writeTo(fos);
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
     * сделать POST-запрос на выгрузку отчета в Excel
     * @param report JSON-объект отчета из docTree
     * @param params параметры для отчета
     * @param filename имя файла, куда сохранять ответ
     * @throws NoSbiExecutionIdException
     */
    public void exportTableAction(JSONObject report, JSONObject params, String filename) throws NoSbiExecutionIdException {
        CookieStore cookieStore = getCurrentSessionCookieStore();
        JSONObject extend = (JSONObject)((JSONObject)report.get("url")).get("extend");
        Long objectId = (Long) extend.get("OBJECT_ID");
        String objectLabel = (String) extend.get("OBJECT_LABEL");
        String role = (String) extend.get("ROLE");
        String engine = ((String) extend.get("ENGINE")).replace('+', ' ');
        String typeCode = (String) extend.get("TYPE_CODE");
        String sbiExecutionId = getSbiExecutionId("" + objectId, objectLabel, role);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("OBJECT_ID", "" + objectId));
        formparams.add(new BasicNameValuePair("OBJECT_LABEL", objectLabel));
        formparams.add(new BasicNameValuePair("isFromCross", "false"));
        formparams.add(new BasicNameValuePair("ROLE", role));
        formparams.add(new BasicNameValuePair("ENGINE", engine));
        formparams.add(new BasicNameValuePair("TYPE_CODE", typeCode));
        formparams.add(new BasicNameValuePair("SBI_EXECUTION_ID", sbiExecutionId));

//        String paramStr = "{\"year_field_visible_description\":\"\",\"year\":\"2013\"}";
        String paramStr = params.toString();
        formparams.add(new BasicNameValuePair("PARAMETERS", paramStr));

        String requestURL = browser.getRedirectedURL() +
                "/BIMON/servlet/AdapterHTTP?ACTION_NAME=EXPORT_TABLE_ACTION&LIGHT_NAVIGATOR_DISABLED=TRUE";
        browser.newHar(browser.getStartBrowserURL());
        executePostRequest(requestURL, formparams, cookieStore, "Экспорт в Excel", filename);
    }
     /**
     * найти строку- логин-тикет в тексте ответа на запрос
     * @param resp ответ на GET-запрос, откуда берем искомое значение
     * @return строка - логин-тикет
     */
    public String getLoginTicketFromHTML(HttpResponse resp) {
        String retStr = "";
        try{
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(resp.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                retStr += line;
            }
            int startInd = retStr.indexOf("\"lt\" value=\"") + 12;
            int endInd = retStr.indexOf("\"", startInd);
            retStr = retStr.substring(startInd, endInd);

        }
        catch (IOException ioe) {
            logger.info(ioe.toString());
            ioe.printStackTrace();
        }
        return retStr;
    }

    /**
     * найти строку- логин-тикет в тексте ответа на GET-запрос
     * @param getRequestURL
     * @return строка - логин-тикет
     */
    public String getLoginTicket(String getRequestURL) {
        return getLoginTicketFromHTML(executeGetRequest(getRequestURL));
    }

    /**
     * найти строку- логин-тикет в тексте ответа на GET-запрос
     * @param client
     * @param getRequestURL
     * @return строка - логин-тикет
     */
    public String getLoginTicket(HttpClient client, String getRequestURL) {
        return getLoginTicketFromHTML(executeGetRequest(client, getRequestURL));
    }

    /**
     * отключить HTTP-клиент
     * @param client
     */
    public void shutdown(HttpClient client) {
        try{
            client.getConnectionManager().shutdown();
        }
        catch (Exception e) {
            return;
        }
    }
}
