package naumen.framework.base;

import com.google.common.base.Strings;
import naumen.framework.base.elements.BaseElement;
import naumen.framework.base.elements.Label;
import naumen.framework.base.elements.TextBox;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.NamingException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static naumen.framework.base.Logger.getLoc;

public final class Browser {
	private static final long SLEEP_DEFAULT = 3000l;
	private static final long SLEEP_SWITCH = 30000l;
	private static final long SLEEP_THREAD_SLEEP = 250;
	private static final long IMPLICITY_WAIT = 5;
	private static final String DEFAULT_CONDITION_TIMEOUT = "defaultConditionTimeout";
	private static final String DEFAULT_PAGE_LOAD_TIMEOUT = "defaultPageLoadTimeout";
	private static final String DEFAULT_SHORT_TIMEOUT = "defaultShortTimeout";
	private static final String URL_LOGIN_PAGE = "urlLoginPage";
	private static final String HOSTNAME = "hostname";
	private static final String PORT = "port";
    private static final String REDIRECTED_URL = "redirectedURL";
    private static final String REDIRECTED_PORT = "redirectedPort";
    private static final String SUITE_CONFIGURATION = "suiteConfiguration";
    private static final String THREAD_COUNT = "threadCount";
	
	/**
	 * speedValue=100 defaultPageLoadTimeout=60 defaultConditionTimeout=180
	 * urlLoginPage=http://opensesame:%23pears0n@dev.pearsoninnovationlabs.com/ #overrided in
	 * Browser.initProperties-default=firefox; #if null - use argument 'browser' to JVM; if other value (without '*'),
	 * #will use it as browserStartCommand #Usage: #firefox #iexplore #chrome #null browser=iexplore
	 */
	static final String PROPERTIES_FILE = "selenium.properties";
	private static final String BROWSER_BY_DEFAULT = "firefox";
	private static final String BROWSER_PROP = "browser";

	// browsers
	private static Browser instance;
	private static RemoteWebDriver driver;
	public static PropertiesResourceManager props;

	private static String browserURL;
	private static String dbPrefix;
	private static String timeoutForPageLoad;
	private static String timeoutForCondition;
	private static String shortTimeout;
    private static String redirectedURL;
    private static int threadCount;
	public static Browsers currentBrowser;
	public static String stageProperty;
    public static String suiteConfiguration;

    public static Proxy proxy;
	/**
	 * Private constructor (singleton pattern)
	 */
	private Browser() {
		Logger.getInstance().info(String.format(getLoc("loc.browser.ready"), currentBrowser.toString()));
	}

    public static ProxyServer getServer(){
        return ProxyPortManager.getServer(Thread.currentThread().getId());
    }

    public static ProxyServer getServer(Long threadID){
        return ProxyPortManager.getServer(threadID);
    }

	/**
	 * Checks if is Browser live
	 * @return true\false
	 */
	public boolean isBrowserAlive() {
		return instance != null;
	}

	/**
	 * Gets instance of Browser
	 * @return browser instance
	 */
    public static Browser getInstance() {
        if (instance == null) {
            initProperties();
            try {
                if (!System.getProperty("noui", "false").equalsIgnoreCase("true")) {
                    driver = BrowserFactory.setUp(currentBrowser.toString());
                    driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
                }
                Logger.getInstance().info(getLoc("loc.browser.constructed"));
            } catch (NamingException e) {
                Logger.getInstance().info("NamingException...");
                e.printStackTrace();
            }
            instance = new Browser();
        }
        return instance;
    }
    /**
     * Gets instance of Browser
     * @return browser instance
     */
	public static Browser getInstance(String port, String redirectedPort) {
		if (instance == null) {
            setPort(port);
            setRedirectedPort(redirectedPort);
            initProperties();
			try {
				if (!System.getProperty("noui", "false").equalsIgnoreCase("true")) {
				driver = BrowserFactory.setUp(currentBrowser.toString());
				driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
				}
				Logger.getInstance().info(getLoc("loc.browser.constructed"));
			} catch (NamingException e) {
				Logger.getInstance().info("NamingException...");
				e.printStackTrace();
			}
			instance = new Browser();
		}
		return instance;
	}

    public static Proxy getProxy()
    {
        try{
            return ProxyPortManager.getProxy(Thread.currentThread().getId());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * прекращаем работу прокси-сервера
     * @throws Exception
     */
    private void stopServer() throws Exception{
        ProxyPortManager.closeProxy(Thread.currentThread().getId());
    }

    /**
     * прекращаем работу прокси-сервера
     * @throws Exception
     */
    private void stopServer(Long threadID) throws Exception{
        ProxyPortManager.closeProxy(threadID);
    }

	/**
	 * The implementation of the browser is closed
	 * <p>
	 * see {@link BaseEntity # checkAndKill()} all browser processes will be killed
	 * <p>
	 * void after test
	 */
	public void exit() {
		try {
            quitDriver();
            stopServer();
            Logger.getInstance().info(getLoc("loc.browser.driver.quit"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			instance = null;
		}
	}

    /**
     * The implementation of the browser is closed
     * <p>
     * see {@link BaseEntity # checkAndKill()} all browser processes will be killed
     * <p>
     * void after test
     */
    public void exit(Long threadID) {
        try {
            quitDriver(threadID);
            stopServer(threadID);
            Logger.getInstance().info(getLoc("loc.browser.driver.quit"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }


	/**
	 * gets TimeoutForCondition
	 * @return timeoutForCondition
	 */
	public String getTimeoutForCondition() {
		return timeoutForCondition;
	}


	/**
	 * gets short timeout
	 * @return short timeout
	 */
	public String getTimeoutShort() {
		return shortTimeout;
	}

	/**
	 * gets short timeout
	 * @return short timeout
	 */
	public int getTimeoutShortInt() {
		return Integer.parseInt(shortTimeout);
	}

	/**
	 * Ждет появления нового окна.
	 * @param prevWndCount количества окон до появления нового окна
	 * @return true, если новое окно появилось
	 */
	public Boolean waitForNewWindowWithoutAssertion(final int prevWndCount) {
		Boolean isAppears = true;
		final int wndCount = prevWndCount;
		Long timeout = 10l;
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.getWindowHandles().size() > wndCount;
				}
			});
		} catch (TimeoutException e) {
			isAppears = false;
		}
		return isAppears;
	}

	public int getThreadCount(){
        return threadCount;
    }

    /**
	 * gets TimeoutForPageLoad
	 * @return timeoutForPageLoad
	 */
	public String getTimeoutForPageLoad() {
		return timeoutForPageLoad;
	}

	/**
	 * gets StartBrowserURL
	 * @return browserURL
	 */
	public String getStartBrowserURL() {
		return browserURL;
	}

    /**
     * gets DocumentsTreeRequest
     * @return documentsTreeRequest
     */
    public String getRedirectedURL() {
        return redirectedURL;
    }

    /**
     * create new HAR with label lab
     * @param lab
     */
    public static void newHar(String lab){
        getServer().newHar(lab);
    }

    /**
     * get HAR data
     * @return HAR har
     */
    public static Har getHar(){
        return getServer().getHar();
    }

    /**
     * create new HAR with label lab
     * @param lab
     * @param threadID
     */
    public static void newHar(String lab, long threadID){
//        getServer(threadID).setCaptureBinaryContent(true);
        getServer(threadID).newHar(lab);
    }

    /**
     * get HAR data
     * @param threadID
     * @return HAR har
     */
    public static Har getHar(long threadID){
        return getServer(threadID).getHar();
    }

    public String getSuiteConfiguration() {
        return props.getProperty(SUITE_CONFIGURATION);
    }

    public String getPort() {
        return props.getProperty(PORT);
    }

    private static void setPort(String port){
        props.setProperty(PORT, port);
    }


    private static void setRedirectedPort(String redirectedPort){
        props.setProperty(REDIRECTED_PORT, redirectedPort);
    }

	/**
	 * init
	 */
	private static void initProperties() {
        try{
            proxy = ProxyPortManager.getProxy(Thread.currentThread().getId());
        }
        catch(Exception e){
            e.printStackTrace();
        }
		props = new PropertiesResourceManager(PROPERTIES_FILE);
		timeoutForPageLoad = props.getProperty(DEFAULT_PAGE_LOAD_TIMEOUT);
		timeoutForCondition = props.getProperty(DEFAULT_CONDITION_TIMEOUT);
		shortTimeout = props.getProperty(DEFAULT_SHORT_TIMEOUT);
		// Получаем URL
		String hostname = props.getProperty(HOSTNAME);
		String port = props.getProperty(PORT);
        String redirectedPort = props.getProperty(REDIRECTED_PORT);
		browserURL = String.format(props.getProperty(URL_LOGIN_PAGE), hostname, port);
        redirectedURL = String.format(props.getProperty(REDIRECTED_URL), hostname, redirectedPort);
        threadCount = Integer.parseInt(props.getProperty(THREAD_COUNT));
        suiteConfiguration = props.getProperty(SUITE_CONFIGURATION);
        if (props.getProperty(BROWSER_PROP).equalsIgnoreCase("null")) {
			// using System.getProperty
			currentBrowser = Browsers.valueOf(System.getProperty(BROWSER_PROP, BROWSER_BY_DEFAULT).toUpperCase());
		} else {
			// using selenium.properties
			if(Strings.isNullOrEmpty(System.getProperty("browser"))){
				String proper = props.getProperty(BROWSER_PROP);
				currentBrowser = Browsers.valueOf(proper.toUpperCase());
				System.setProperty("browser", currentBrowser.toString());
			}else{
				String proper = System.getProperty(BROWSER_PROP);
				currentBrowser = Browsers.valueOf(proper.toUpperCase());
			}
		}
	}

	/** возвращает префикс к базе данных согласно hostname
	 * @return префикс к базе данных
	 */
	public static String getDbPrefix(){
		return dbPrefix;
	}

	/**
	 * wait the download page (on Javascript readyState)
	 */
	public void waitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Long.parseLong(getTimeoutForPageLoad()));
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver d) {
					if (!(d instanceof JavascriptExecutor)) {
						return true;
					}
					Object result = ((JavascriptExecutor) d).executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
					if (result != null && result instanceof Boolean && (Boolean) result) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			System.out.println(getLoc("loc.browser.page.timeout"));
		}
	}

	private final long defaultConditionTimeout = 30;

	/**  ждет выполнения условия
	 * @param obj объект класса
	 * @param methodName название метода условия
	 * @param args аргументы
	 * @param params список параметров
	 * @param info информация об ожидаемом событии
	 * @return true, если условие выполнилось
	 */
	@SuppressWarnings("unchecked")
	public boolean waitCondition(final Object obj, final String methodName,  final Class[] params, final Object[] args, String info){
		return waitCondition(obj, methodName, params, args, info, defaultConditionTimeout);
	}

	/**  ждет выполнения условия
	 * @param obj объект класса
	 * @param methodName название метода условия
	 * @param args аргументы
	 * @param params список параметров
	 * @param info информация об ожидаемом событии
	 * @param timeout timeout
	 * @return true, если условие выполнилось
	 */
	@SuppressWarnings("unchecked")
	public boolean waitCondition(final Object obj, final String methodName,  final Class[] params, final Object[] args, String info, long timeout){
		try {
			final Method condition = obj.getClass().getDeclaredMethod(methodName, params);
			WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {

				public Boolean apply(final WebDriver d) {
					try{
						if((Boolean) condition.invoke(obj, args)){
							return true;
						}
						return false;
					}catch (Exception e) {
						return false;
					}
				}
			});
		} catch (Exception e) {
			Logger.getInstance().warn("Не удалось дождаться: " + info);
			System.out.println("Не удалось дождаться: " + info);
			System.out.println(e.getMessage() + " " + e.getClass());
			return false;
		}
		return true;
	}

	/**
	 * waiting, while number of open windows will be more than previous
	 * @param prevWndCount - number of previous
	 */
	public void waitForNewWindow(final int prevWndCount) {
		final int wndCount = prevWndCount;
		WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(getTimeoutForCondition()));
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver d) {
					return d.getWindowHandles().size() > wndCount;
				}
			});
		} catch (Exception e) {
			Assert.assertTrue(getLoc("loc.browser.newwindow.notappear"), false);
		}
	}

	public void waitForWindowIsCloseAndSwitch(int prevWndCount) {
		  final int wndCount = prevWndCount;
		  WebDriverWait wait = new WebDriverWait(getDriver(), Long.parseLong(getTimeoutForCondition()));
		  try {
		   wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
		    public Boolean apply(final WebDriver d) {
		     return d.getWindowHandles().size() < wndCount;
		    }
		   });
		  } catch (Exception e) {
		   Assert.assertTrue("Window is not closed!", false);
		  }
        getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[wndCount - 2]);
		 }
	/**
	 * Select the previous window (the list of handlers)
	 */
	public void selectPreviousWindow() {
		Object[] handles = getDriver().getWindowHandles().toArray();
		String handle = getDriver().getWindowHandle();
		Assert.assertTrue(getLoc("loc.browser.windows.count.small"), handles.length > 1);
		for (int i = 1; i < handles.length; i++) {
			if (handles[i].equals(handle)) {
				getDriver().switchTo().window((String) handles[i - 1]);
				return;
			}
		}
		getDriver().switchTo().window((String) handles[handles.length - 2]);

	}

	/** Закрывает окно и переключается на предыдущее.
	 */
	public void closeWindowAndSwitch() {
		final int wndCount = getDriver().getWindowHandles().size();
		String handle = getDriver().getWindowHandle();
		WebDriver wd = getDriver().switchTo().window(handle);
		wd.close();
		WebDriverWait wait = new WebDriverWait(getDriver(), Long.parseLong(getTimeoutForCondition()));
		try {
			wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver d) {
					return d.getWindowHandles().size() < wndCount;
				}
			});
		} catch (TimeoutException e) {
			Assert.assertTrue("Окно не закрылось", false);
		}
		selectLastWindow();
	}

	/**
	 * Select the first window (the list of handlers)
	 */
	public void selectFirstWindow() {
		Object[] handles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) handles[0]);

	}

	/**
	 * We expect the emergence of the new window and select it
	 */
	public void selectNewWindow() {
		Object[] handles = getDriver().getWindowHandles().toArray();
		waitForNewWindow(handles.length);
		handles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) handles[handles.length - 1]);

	}

	/**
	 * Select the last window (the list of handlers)
	 */
	public void selectLastWindow() {
		Object[] handles = getDriver().getWindowHandles().toArray();
        getDriver().switchTo().window((String) handles[handles.length - 1]);

	}

	/**
	 * Go to the home page
	 */
	public void openStartPage() {
        getDriver().navigate().to(props.getProperty(getStartBrowserURL()));
	}

	/**
	 * maximizes the window
	 * works on IE7, IE8, IE9, FF 3.6
	 */
	public void windowMaximise() {
        getDriver().executeScript("if (window.screen) {window.moveTo(0, 0);window.resizeTo(window.screen.availWidth,window.screen.availHeight);};");
	}

	/**
	 * Navgates to the Url
	 * @param url Url
	 */
	public void navigate(final String url) {
		Logger.getInstance().infoLink("Навигация по URL: " + url);
        getDriver().get(url);
	}

    /**
     * Navigates back
     */
    public void back() {
        Logger.getInstance().info("Навигация назад");
        getDriver().navigate().back();
    }

	/**
	 * Refresh page.
	 */
	public void refresh() {
        getDriver().navigate().refresh();
		Logger.getInstance().info("Page was refreshed.");
	}

	/**
	 * get RemoteWebDriver
	 * @return driver
	 */
	public RemoteWebDriver getDriver() {
        return WebDriverManager.getDriver(Thread.currentThread().getId());
	}

    /**
     * quit current WebDriver
     */
    public void quitDriver(){
        WebDriverManager.stopDriver(Thread.currentThread().getId());
    }

    public void quitDriver(Long threadID){
        WebDriverManager.stopDriver(threadID);
    }

	/**
	 * Open new window by hot keys Ctrl + N, webdriver switch focus on it.
	 */
	public void openNewWindow() {
		getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL, "n");
		Object[] headers = getDriver().getWindowHandles().toArray();
		getDriver().switchTo().window(headers[headers.length - 1].toString());
	}

	/**
	 * click and switch to new window. (works on IE also)
	 * @param element element
	 */
	public void clickAndSwitchToNewWindow(final BaseElement element) {
		Set<String> existingHandles = getDriver().getWindowHandles();
		element.click();

		String foundHandle = null;
		long endTime = System.currentTimeMillis() + SLEEP_SWITCH;
		while (foundHandle == null && System.currentTimeMillis() < endTime) {
			Set<String> currentHandles = getDriver().getWindowHandles();
			if (currentHandles.size() != existingHandles.size()) {
				for (String currentHandle : currentHandles) {
					if (!existingHandles.contains(currentHandle)) {
						foundHandle = currentHandle;
						Logger.getInstance().info("new window was found");
						break;
					}
				}

			}
			if (foundHandle == null) {
				try {
					Thread.sleep(SLEEP_THREAD_SLEEP);
				} catch (InterruptedException e) {
					Logger.getInstance().fatal("new window not found");
					e.printStackTrace();
				}
			}
		}

		if (foundHandle != null) {
			getDriver().switchTo().window(foundHandle);
		} else {
			Logger.getInstance().fatal("new window not found");
		}
	}

	/**
	 * Trigger
	 * @param script script
	 * @param element element
	 */
	public void trigger(final String script, final WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript(script, element);
	}

	/**
	 * Executes a script
	 * @note Really should only be used when the web driver is sucking at exposing functionality natively
	 * @param script The script to execute
	 * @return Object
	 */
	public Object trigger(final String script) {
		return ((JavascriptExecutor) getDriver()).executeScript(script);
	}

	/**
	 * Opens a new tab for the given URL (doesn't work on IE)
	 * @param url The URL to
	 */
	public void openTab(final String url) {
		String script = "var d=document,a=d.createElement('a');a.target='_blank';a.href='%s';a.innerHTML='.';d.body.appendChild(a);return a";
		Object element = trigger(String.format(script, url));
		if (element instanceof WebElement) {
			WebElement anchor = (WebElement) element;
			anchor.click();
			trigger("var a=arguments[0];a.parentNode.removeChild(a);", anchor);
		} else {
			throw new JavaScriptException(element, "Unable to open tab", 1);
		}
	}

	/**
	 * Clicks on element
	 * @param selector By Locator
	 */
	@Deprecated
	public void click(final By selector) {
		new Label(selector).click();
	}

	/**
	 * waits ForIsElementPresent
	 * @param selector By Locator
	 */
	@Deprecated
	public void waitForIsElementPresent(final By selector) {
		new Label(selector).waitAndAssertIsPresent();
	}

	/**
	 * Types a text
	 * @param selector By Locator
	 * @param text text
	 */
	@Deprecated
	public void type(final By selector, final String text) {
		new TextBox(selector).type(text);
	}

	/**
	 * Focuses on the element
	 * @param selector By Locator
	 * @param really name
	 */
	@Deprecated
	public void focus(final By selector, final String really) {
		new Label(selector).focus();
	}

	/**
	 * Gets current URL
	 * @return current URL
	 */
	public String getLocation() {
		return getDriver().getCurrentUrl();
	}

	/**
	 * Executes Java Scipt
	 * @param script Java Script
	 */
	public void jsExecute(final String script) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(script);
	}

	/**
	 * Sleeps for amount of time
	 * @param mSeconds milli seconds
	 */
	@Deprecated
	public void sleep(final long mSeconds) {
		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Sleep method - do not USE it
	 */
	@Deprecated
	public void sleep() {
		sleep(SLEEP_DEFAULT);
	}

	/**
	 * Browsers enumeration
	 */
	public enum Browsers {
		/**
		 * @uml.property name="fIREFOX"
		 * @uml.associationEnd
		 */
		FIREFOX("firefox"), /**
		 * @uml.property name="iEXPLORE"
		 * @uml.associationEnd
		 */
		IEXPLORE("iexplore"), /**
		 * @uml.property name="cHROME"
		 * @uml.associationEnd
		 */
		CHROME("chrome"),
		SAFARI("safari"),
		IPHONE("iphone"),
		IPAD("ipad"),
		ANDROID("android");
		public String value;

		/**
		 * Constructor
		 * @param values Value
		 */
		Browsers(final String values) {
			value = values;
		}

		/**
		 * Returns string value
		 * @return String value
		 */
		public String toString() {
			return value;
		}
	}

	/** переключается на фрейм, если он существует
	 * @param locator локатор
	 */
	public void switchToFrameIfPresent(By locator){
		if(new Label(locator).waitForIsElementPresent()){
			try{
				getDriver().switchTo().frame(getDriver().findElement(locator));
				Logger.getInstance().info("Успешно переключились на frame с локатором: " + locator);
			}catch (StaleElementReferenceException e) {
				System.out.println(e.getMessage());
				getDriver().switchTo().frame(getDriver().findElement(locator));
				Logger.getInstance().info("Успешно переключились на frame с локатором: " + locator);
			}
		}
	}

	/** переключается на контент по умолчанию
	 */
	public void switchToDefaultContent(){
		getDriver().switchTo().defaultContent();
	}

	/** создает новое окно и переключается на него
	 */
	public void createNewWindowAndSwitch(){
		((JavascriptExecutor)getDriver()).executeScript("window.open('')");
		selectLastWindow();
	}

	/** закрывает все окна кроме текущего
	 */
	public void closeAllWindowButCurrent(){
		String handle = getDriver().getWindowHandle();
		for(String h : getDriver().getWindowHandles()){
			if(!h.equals(handle)){
				getDriver().switchTo().window(h);
				getDriver().close();
				getDriver().switchTo().window(handle);
			}
		}
	}
}
