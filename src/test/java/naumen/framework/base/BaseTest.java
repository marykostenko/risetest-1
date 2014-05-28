package naumen.framework.base;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;


/**
 * An abstract class that describes the basic test application contains
 * methods for logging and field test settings (options)
 */
public abstract class BaseTest extends BaseEntity implements IAnalys {

	/**
	 * to override
	 */
	public abstract void runTest();
	protected boolean hasWarn = false;
    private Long threadID;


    /** выполняется в начале выполнения тестов из сьюты
	 */

//	@BeforeSuite
//	public void beforeSuite(){
//		CommonFunctions.copySerializationFiles();
//	}


    /**
     *  Before Class method
     */
    @BeforeClass
    public void before() {
        threadID = Thread.currentThread().getId();
        if (!System.getProperty("noui", "false").equalsIgnoreCase("true")) {
            browser = Browser.getInstance();
            for(int i = 0; i <= 2; i++){
                try{
                    browser.windowMaximise();
                    break;
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            browser.navigate(browser.getStartBrowserURL());
        }
        stepNumber = 1;
    }

    /**
     *Close browser after each test Class
     */
    @AfterClass(alwaysRun = true)
    public void after() {
//        if (System.getProperty("noui", "false").equalsIgnoreCase("true")) {
//            return;
//        }
//        if (browser.isBrowserAlive()) {
//            browser.exit();
        String sessionID = browser.getDriver().getSessionId().toString();
        browser.exit(threadID);
        checkAndKill();
//        }
    }

    /**
     * killing process by Image name
     */
    public void checkAndKill() {
        logger.info("killing processes");
        try {
            String line;
            String command;
            if(Browser.currentBrowser.toString().equals("iexplore")){
                try{
//					while(true)
                    for(int i=0;i<5;i++)
                        Runtime.getRuntime().exec(String.format("taskkill /IM iexplore.exe /F", Browser.currentBrowser.toString()));
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (System.getProperty("os.name").toLowerCase().contains("win")){
                command = String.format("taskkill /IM %1$s.exe /F", Browser.currentBrowser.toString());
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while (((line = input.readLine()) != null)) {
                    logger.info(line);
                }
                input.close();
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
    }


    public int getThreadCount(){
        return browser.getThreadCount();
    }

	/** возвращает true, если в тесте были warnings
	 * @return true, если в тесте были warnings
	 */
	public boolean hasWarn(){
		return hasWarn;
	}

	/** переводит состояние флага наличия warnings в true
	 */
	public void warn(){
		hasWarn = true;
	}

    /**
     * делает warning и скриншот
     */
    protected void warnWithScreen(){
        warn();
        String fileName = this.getClass().getPackage().getName() + "." + this.getClass().getSimpleName();
        makeScreen(fileName);
    }

    /**
     * выводит в лог название стенда, в зависимости от порта подключения
     */
	public void showStand() {
        if (browser.getPort().equals(""))
            logger.info("<b>РАБОЧИЙ СТЕНД</b>");
        else
            logger.info("<b>ТЕСТОВЫЙ СТЕНД</b>");
    }

    public static String getStand() {
        if (browser.getPort().equals(""))
            return "WorkStand";
        else
            return "TestStand";
    }

    /** test
	 * @throws Throwable Throwable
	 */
	public void xTest() throws Throwable {
        try {
            hasWarn = false;
			logger.logTestName(this.getClass().getName());
			if (!System.getProperty("noui", "false").equalsIgnoreCase("true")) {
				browser.navigate(browser.getStartBrowserURL());
			}
			BaseEntity.testName = this.getClass().getName();
			showStand();
            runTest();
			if(!hasWarn){
				logger.logTestEnd(this.getClass().getName());
			}else{
				logger.warn("");
				logger.fatal("В тесте были WARNINGS! См. лог.");
				logger.warn("");
			}
		} catch (Throwable e) {
			if (shouldAnalys()) {
				invokeAnalys(e, browser.getDriver().getPageSource());
			} else {
				logger.warn("");
				logger.warnRed(getLoc("loc.test.failed"));
				logger.warn("");
				throw e;
			}
		} finally {
			browser.switchToDefaultContent();
			if(!propMg().getProperty("localrun").equalsIgnoreCase("true")){
                checkAndKill();
			}
		}
	}

	/** format log
	 * @param message message
	 * @return formatted message
	 */
	protected String formatLogMsg(final String message) {
		return message;
	}

    /**
     * из response POST-запроса, отправленного при построении отчета, вытаскиваем json с данными
     * @param base64EncodedContent String
     * @return
     */
    public static JSONObject getJSONFromBase64(String base64EncodedContent){
        byte[] decoded = Base64.decodeBase64(base64EncodedContent.getBytes());
        try{
            String decodedContent = new String(decoded, "UTF-8");
            return (JSONObject)new JSONParser().parse(decodedContent);
        }
        catch(UnsupportedEncodingException ueo){
            logger.fatal("Unsupported Encoding Exception");
            return null;
        }
        catch(ParseException pe){
            logger.fatal("Parse Exception");
            return null;
        }
    }
}
