package naumen.framework.base;

import naumen.framework.base.Browser.Browsers;
import naumen.framework.base.elements.BaseElement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import static org.testng.AssertJUnit.assertTrue;

public abstract class BaseEntity {
	protected static int stepNumber = 1;
	protected static Logger logger = Logger.getInstance();
//	protected static Browser browser = Browser.getInstance();
	protected static boolean isLogged = false;
	protected static String testName = "default test name";
	private static final String PROPERTIES_FILE = "selenium.properties";
	private static PropertiesResourceManager props = new PropertiesResourceManager(PROPERTIES_FILE);
	protected static JAssert jassert = new JAssert();
    private static String scrDir = "";
    protected static Browser browser;

	/** возвращает управление PropertiesResourceManager
	 * @return PropertiesResourceManager
	 */
	protected static PropertiesResourceManager propMg(){
		return props;
	}

	/**
	 * @param key 
	 * @return 
	 */
	protected static String getLoc(final String key) {
		return Logger.getLoc(key);
	}

// ==============================================================================================

	/**
	 * @param message 
	 * @return null
	 */
	protected abstract String formatLogMsg(String message);

	/**
	 * @param message
	 */
	protected final void debug(final String message) {
		logger.debug(String.format("[%1$s] %2$s", this.getClass().getSimpleName(), formatLogMsg(message)));
	}

	/**
	 * @param message
	 */
	protected void info(final String message) {
		logger.info(formatLogMsg(message));
	}

	/**
	 * @param message 
	 */
	protected void warn(final String message) {
		logger.warn(formatLogMsg(message));
	}

    /**
     * @param message
     */
    protected void notify(final String message) {
        logger.notify(formatLogMsg(message));
    }

	/**
	 * @param message
	 */
	protected void error(final String message) {
		logger.error(formatLogMsg(message));
	}

	/**
	 * 
	 * @param message
	 */
	protected void fatal(final String message) {
		logger.fatal(formatLogMsg(message));
		assertTrue(formatLogMsg(message), false);
	}

	/**
	 * Logging a step number
	 * @param step - step number
	 */
	public static void logStep(final int step) {
		logger.step(step);
	}

	/**
	 * Logging a several steps in a one action
	 * @param fromStep - the first step number to be logged
	 * @param toStep - the last step number to be logged
	 */
	public void logStep(final int fromStep, final int toStep) {
		logger.step(fromStep, toStep);
	}

	// ==============================================================================================

	/**
	 * 
	 * @param isTrue 
	 * @param passMsg 
	 * @param failMsg
	 */
	public void doAssert(final Boolean isTrue, final String passMsg, final String failMsg) {
		if (isTrue) {
			info(passMsg);
		} else {
			fatal(failMsg);
		}
	}

    /**
     *
     * @param isTrue
     * @param passMsg
     * @param failMsg
     */
    public void doAssertNotifyOnly(final Boolean isTrue, final String passMsg, final String failMsg) {
        if (isTrue) {
            info(passMsg);
        } else {
            notify(failMsg);
        }
    }

	/**
	 * Assert Objects are Equal
	 * @param expected Expected Value
	 * @param actual Actual Value
	 */
	public void assertEquals(final Object expected, final Object actual) {
		if (!expected.equals(actual)) {
			fatal("Expected value: '" + expected + "', but was: '" + actual + "'");
		}
	}

    /**
     * Assert String Starts with prefix, doesn't fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param prefix prefix substring
     * @param actual Actual Value
     */
    public void assertStartsWithNoFail(final String passMessage, final String failMessage, final String prefix,
                                   final String actual) {
        if (actual.startsWith(prefix)) {
            info(passMessage);
        } else {
            error(failMessage);
            error("Expected value starts with: '" + prefix + "', but was: '" + actual + "'");
        }
    }

	/**
	 * Assert Objects are Equal, doesn't fail the test
	 * @param passMessage Pass Message
	 * @param failMessage Fail Message
	 * @param expected Expected Value
	 * @param actual Actual Value
	 */
	public void assertEqualsNoFail(final String passMessage, final String failMessage, final Object expected,
			final Object actual) {
		if (expected.equals(actual)) {
			info(passMessage);
		} else {
			error(failMessage);
			error("Expected value: '" + expected + "', but was: '" + actual + "'");
		}
	}
    /**
     * Assert Objects are Equal, fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param expected Expected Value
     * @param actual Actual Value
     */
    public void assertEqualsWarn(final String passMessage, final String failMessage, final Object expected,
                                   final Object actual, BaseTest test) {
        if (expected.equals(actual)) {
            info(passMessage);
        } else {
            warn(failMessage);
            warn("Ожидалось: '" + expected + "', а было получено: '" + actual + "'");
            test.warn();
        }
    }
    /**
     * Assert Objects are Equal, doesn't fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param expected Expected Value
     * @param actual Actual Value
     */
    public void assertEqualsNotify(final String passMessage, final String failMessage, final Object expected,
                                 final Object actual) {
        if (expected.equals(actual)) {
            info(passMessage);
        } else {
            notify(failMessage);
            notify("Ожидалось: '" + expected + "', а было получено: '" + actual + "'");
        }
    }
    /**
     * Assert Objects are Equal, fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param expected Expected Value
     * @param actual Actual Value
     * @param tol tolerance
     */
    public void assertEqualsWarn(final String passMessage, final String failMessage, double expected,
                                 double actual, final double tol, BaseTest test) {
        double absVal = expected - actual;
        if (absVal < 0)
            absVal *= -1;
        if (absVal <= tol) {
            info(passMessage);
        } else {
            DecimalFormat df = new DecimalFormat("#.###");
            warn(failMessage);
            warn("Ожидалось: '" + df.format(expected) + "', а было получено: '" + df.format(actual) + "', расхождение на " + df.format(absVal));
            test.warn();
        }
    }
    /**
     * Assert Objects are Equal, doesn't fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param expected Expected Value
     * @param actual Actual Value
     * @param tol tolerance
     */
    public void assertEqualsNotify(final String passMessage, final String failMessage, double expected,
                                 double actual, final double tol) {
        double absVal = expected - actual;
        if (absVal < 0)
            absVal *= -1;
        if (absVal <= tol) {
            info(passMessage);
        } else {
            DecimalFormat df = new DecimalFormat("#.###");
            notify(failMessage);
            notify("Ожидалось: '" + df.format(expected) + "', а было получено: '" + df.format(actual) + "'");
        }
    }

    /**
     * Assert Objects are Equal, doesn't fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param expected Expected Value
     * @param actual Actual Value
     */
    public void assertEqualsOnlyWarn(final String passMessage, final String failMessage, final Object expected,
                                 final Object actual) {
        if (expected.equals(actual)) {
            info(passMessage);
        } else {
            warn(failMessage);
        }
    }

    /**
     * return formatted difference string(semi-bold font) + <=5 symbols before and <=5 symbols after it
     * @param expected
     * @param actual
     * @return difference string (formatted!)
     */
    public String[] getDifferenceString(StringBuffer expected, StringBuffer actual) {
        StringUtils su = new StringUtils();
        int startInd = su.indexOfDifference(expected, actual);
        String expectedReversed = su.reverse(expected.toString());
        String actualReversed = su.reverse(actual.toString());
        int endInd = su.indexOfDifference(expectedReversed, actualReversed);
        if (startInd > (Math.max(expected.length(), actual.length()) - endInd))
            endInd--;
        int numSymbolsBefore = Math.min(5, startInd);
        int numSymbolsAfter = Math.min(5, endInd);
        String prefixExpected = expected.substring(startInd - numSymbolsBefore, startInd);
        prefixExpected += "<b>";
        String prefixActual = actual.substring(startInd - numSymbolsBefore, startInd);
        prefixActual += "<b>";
        String postfixExpected = su.reverse(expectedReversed.substring(endInd - numSymbolsAfter, endInd));
        postfixExpected = "</b>" + postfixExpected;
        String postfixActual = su.reverse(actualReversed.substring(endInd - numSymbolsAfter, endInd));
        postfixActual = "</b>" + postfixActual;
        String middleExpected = "";
        String middleActual = "";
        try {
            middleExpected = expected.substring(startInd, expected.length() - endInd);
        }
        catch (IndexOutOfBoundsException ioob) {
            ioob.printStackTrace();
        }
        try {
            middleActual = actual.substring(startInd, actual.length() - endInd);
        }
        catch (IndexOutOfBoundsException ioob) {
            ioob.printStackTrace();
        }
        return new String[]{prefixExpected + middleExpected + postfixExpected, prefixActual + middleActual + postfixActual};
    }

    /**
     * Assert Strings are Equal, show if not, doesn't fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param expected Expected String
     * @param actual Actual String
     * @return true if equal
     */
    public boolean assertStringsEqualsGetDifferenceOnlyWarn(final String passMessage, final String failMessage, final String expected,
                                     final String actual, BaseTest test) {
        if (expected.equals(actual)) {
            info(passMessage);
            return true;
        } else {
            warn(failMessage);
            String[] diffStr = getDifferenceString(new StringBuffer(expected), new StringBuffer(actual));
            logger.warn("В первоначальном отчете: " + diffStr[0]);
            logger.warn("В отчете по прямой ссылке: " + diffStr[1]);
            test.warn();
            return false;
        }
    }

    /**
     * Assert Strings are Equal, show if not, doesn't fail the test
     * @param passMessage Pass Message
     * @param failMessage Fail Message
     * @param expected Expected String
     * @param actual Actual String
     * @return true if equal
     */
    public boolean assertStringsEqualsGetDifferenceOnlyWarn(final String passMessage, final String failMessage, StringBuffer expected,
                                                            StringBuffer actual, BaseTest test) {
        int maxPrintedSymbols = 1000;
        if (expected.toString().equals(actual.toString())) {
            info(passMessage);
            return true;
        } else {
            warn(failMessage);
            String[] diffStr = getDifferenceString(expected, actual);
            if ((diffStr[0].length() <= maxPrintedSymbols) && (diffStr[1].length() <= maxPrintedSymbols)){
                logger.warn("В первоначальном отчете: " + diffStr[0]);
                logger.warn("В отчете по прямой ссылке: " + diffStr[1]);
            }
            else{
                logger.warn("Длина подстроки различия отчетов превышает " + maxPrintedSymbols + " символов");
            }
            test.warn();
            return false;
        }
    }
	/**
	 * Assert Objects are Equal
	 * @param message Fail Message
	 * @param expected Expected Value
	 * @param actual Actual Value
	 */
	public void assertEquals(final String message, final Object expected, final Object actual) {
		if (!expected.equals(actual)) {
			fatal(message);
		}
	}

    /**
     * проверяет равенство двух целых чисел, в случае неудачи - warning
     * @param errorMessage
     * @param successMessage
     * @param Left
     * @param Right
     */
    public void assertEquals(final String errorMessage, final String successMessage, int Left, int Right, BaseTest test) {
        if (Left == Right) {
            logger.info(successMessage);
        }
        else {
            logger.warn(errorMessage);
            test.warn();
        }
    }

	/**
	 * Assert current page's URL.
	 * @param url expected URL
	 */
	public void assertCurrentURL(String url) {
		String actualUrl = browser.getLocation();
		assertEquals(url, actualUrl);
		info("Page has correct URL.");
	}

	// ==============================================================================================
	//
	/**
	 * screenshot
	 * @param name Имя класса
	 * @param additionalInfo additionalInfo
	 * @return String path to screen
	 */
	public String makeScreen(final Class<? extends BaseEntity> name, final boolean additionalInfo) {
        String fileName = name.getPackage().getName() + "." + name.getSimpleName();
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            scrDir = "surefire-reports\\html\\Screenshots\\";
        }
        else {
            scrDir = "surefire-reports/html/Screenshots/";
        }
        try {
			String pageSource = browser.getDriver().getPageSource();
			FileUtils.writeStringToFile(new File(String.format(scrDir + "%1$s.txt", fileName)), pageSource);
		} catch (Exception e1) {
			warn("Failed to save page source");
		}
		if (browser.getDriver().getCapabilities().getBrowserName().toLowerCase().contains("explore")) {
            try {
                File screen = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.FILE);
                Dimension windowSize = browser.getDriver().manage().window().getSize();
                Point upperLeftCorner = browser.getDriver().manage().window().getPosition();
                int width = windowSize.getWidth();
                int height = windowSize.getHeight();
                Rectangle rect = new Rectangle(width, height);
                BufferedImage img;
                Robot r = new Robot();
                r.delay(5000);
                img = r.createScreenCapture(new Rectangle(upperLeftCorner.getX(), upperLeftCorner.getY(), width, height));
                ImageIO.write(img, "png", screen);
                FileUtils.copyFile(screen,
                new File(String.format(scrDir + "%1$s.png", fileName)));
            } catch (Exception e) {
                warn("Failed to save screenshot");
            }
        }
        else {
            try {
                File screen = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.FILE);
                if (!screen.renameTo(new File(String.format(scrDir + "%1$s.png", fileName))))
                    logger.notify("WARN: Failed to save screenshot");
            } catch (Exception e) {
                warn("Failed to save screenshot");
            }
        }
		if (additionalInfo) {
		String formattedName = String.format("<a href='Screenshots/%1$s.png'>ScreenShot</a>", fileName);
		String formattedNamePageSource = String.format("<a href='Screenshots/%1$s.txt'>Page Source</a>", fileName);
		logger.info(formattedName);
		logger.info(formattedNamePageSource);
		logger.printDots(formattedName.length());
		}
		return new File(String.format(scrDir + "%1$s.png", fileName)).getAbsolutePath();
	}

	/**
	 * screenshot
	 * @param name screenshot name
	 */
	public static String makeScreen(String name) {
		String fileName = name + System.currentTimeMillis();
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            scrDir = "surefire-reports\\html\\Screenshots\\";
        }
        else {
            scrDir = "surefire-reports/html/Screenshots/";
        }
        if (browser.getDriver().getCapabilities().getBrowserName().toLowerCase().contains("explore")) {
            try {
                File screen;
    //			logger.info("Make screen as RemoteWebDriver");
                screen = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);

                Dimension windowSize = browser.getDriver().manage().window().getSize();
    //            logger.info("window size = " + windowSize.toString());
                int width = windowSize.getWidth();
                int height = windowSize.getHeight();
                Rectangle rect = new Rectangle(width, height);
                BufferedImage img;
                img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                BufferedImage dest = img.getSubimage(0, 0, rect.width, rect.height);
                ImageIO.write(dest, "png", screen);
                File f = new File(String.format(scrDir + "%1$s.png", fileName));
                if (!screen.renameTo(new File(String.format(scrDir + "%1$s.png", fileName))))
                    logger.notify("WARN: Failed to save screenshot: ");
                logger.info("path to screen: " + f.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("WARN: Failed to save screenshot" + e.getMessage());
            }
        }
        else {
            try {
                File screen = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screen,
                        new File(String.format(scrDir + "%1$s.png", fileName)));
            } catch (Exception e) {
                System.out.println("Failed to save screenshot");
            }
        }
		File copyFile = new File(String.format(scrDir + "%1$s.png",fileName));
		String path = null;
		try {
			path = copyFile.getCanonicalPath().replaceAll(".*workspace", "https://jenkins.demohoster.com/job");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String screenShot = path.replaceAll("\\\\", "/").replaceAll("(.*)(target)", "$1/$2");
		String formattedName = String.format("<a href='Screenshots/%1$s.png'><img src='Screenshots/%1$s.png' height='480' width='600'/></a>", fileName);
        logger.info(formattedName);
		logger.printDots(formattedName.length());
		return screenShot;
	}

    protected void makeScreen(){
        String fileName = this.getClass().getPackage().getName() + "." + this.getClass().getSimpleName();
        makeScreen(fileName);
    }


	/**
	 * make screenshot of the element
	 * @param fileName screenshot name
	 * @param element element
	 * @return path to screenshot
	 */
	public static String makeElementScreen(String fileName, BaseElement element) {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            scrDir = "surefire-reports\\html\\Screenshots\\";
        }
        else {
            scrDir = "surefire-reports/html/Screenshots/";
        }
        try {
			File screen = ((TakesScreenshot)browser.getDriver()).getScreenshotAs(OutputType.FILE);
			Point p = element.getElement().getLocation();
			Dimension windowSize = browser.getDriver().manage().window().getSize();
            System.out.println("window size = " + windowSize.toString());
            int elWidth = element.getElement().getSize().getWidth();
			int elHeight = element.getElement().getSize().getHeight();
            System.out.println("element size = " + element.getElement().getSize().toString());
            int winWidth = windowSize.getWidth();
            int winHeight = windowSize.getHeight();
            int width = elWidth < winWidth ? elWidth : winWidth;
            int height = elHeight < winHeight ? elHeight : winHeight;
			Rectangle rect = new Rectangle(width, height);
			BufferedImage img = null;
			img = ImageIO.read(screen);
			BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,
	        rect.height);
			ImageIO.write(dest, "png", screen);
			File f = new File(String.format(scrDir + "%1$s.png", fileName));
			FileUtils.copyFile(screen, f);
		} catch (Exception e) {
			System.out.println("WARN: Failed to save screenshot: " + e.getMessage());
		}
		File copyFile = new File(String.format(scrDir + "%1$s.png",fileName));
		String path = null;
		try {
			path = copyFile.getCanonicalPath().replaceAll(".*workspace", "https://jenkins.demohoster.com/job");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		String screenShot = path.replaceAll("\\\\", "/").replaceAll("(.*)(target)", "$1ws/$2");
		return screenShot;
	}

	public void closeWindowAndSwitch(){
		int count = browser.getDriver().getWindowHandles().size();
		browser.getDriver().close();
		browser.waitForWindowIsCloseAndSwitch(count);
	}

    /**
     * возвращает текст с элемента с помощью JS
     * элемент может быть невидимым
     * @param el веб-элемент
     * @return text
     */
    public StringBuffer getTextViaJS(WebElement el) {
        return new StringBuffer((String) ((JavascriptExecutor)browser.getDriver()).executeScript(
                "return arguments[0].innerText", el));
    }

    /**
     * возвращает текст с элемента с помощью JS
     * элемент может быть невидимым
     * @param el веб-элемент
     * @return text
     */
    public String getStringViaJS(WebElement el) {
        return ((String) ((JavascriptExecutor)browser.getDriver()).executeScript(
                "return arguments[0].innerText", el));
    }

	/**
	 * for IE only
	 */
	public void acceptUnsignedSertificate() {
		if (Browser.currentBrowser == Browsers.IEXPLORE) {
			browser.navigate("javascript:document.getElementById('overridelink').click()");
		}
	}

	/**
	 * Logging steps
	 */
	protected void LogStep() {
		logStep(stepNumber++);
	}
	/**
	 * String[] to String with separator after each element
	 * @param a String Array
	 * @param separator Separator
	 * @return
	 */
	public static String arrayToString(String[] a, String separator) {
	    StringBuffer result = new StringBuffer();
	    if (a.length > 0) {
	        result.append(a[0]);
	        for (int i=1; i<a.length; i++) {
	            result.append(separator);
	            result.append(a[i]);
	        }
	    }
	    return result.toString();
	}
	/**
	 * Waits for alert
	 * @return Alert
	 */
	public Alert waitForAlert() {
		Boolean mark = false;
		while(!mark) {
        try {
            browser.getDriver().switchTo().alert();
            mark=true;
            break;
        }catch(NoAlertPresentException e) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        	continue;
        }
	   }
	   Alert alert = browser.getDriver().switchTo().alert();
	   return alert;
	}

	/**
	 * Scroll to the bottom of the page
	 */
	public void scrollPageDown(){
		browser.getDriver().executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," +
        "document.body.scrollHeight,document.documentElement.clientHeight));");
	}
	/**
	 * enum Question Types 
	 */
	public enum QUEST_TYPE {
		YesNo("Yes/No"),MCSO("Multiple-choice (select one)");

		private String name = "";

		QUEST_TYPE(String itemName) {
			name = itemName;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	/** ждет появления алерта
	 * @param driver драйвер
	 * @param seconds ожидание в секундах
	 * @return алерт, если удалось дождаться, иначе Null
	 */
	public Alert waitForAlert(WebDriver driver, int seconds) {
	    Wait<WebDriver> wait = new WebDriverWait(driver, seconds).ignoring(NullPointerException.class);
	    Alert alert = null;
	    alert = wait.until(new ExpectedCondition<Alert>() {
	        @Override
	        public Alert apply(WebDriver driver) {
	            Alert alert = driver.switchTo().alert();
	            alert.getText();
	            return alert;
	        }
	    });
	    return alert;
	}

	public Alert getAlert(){
	    return waitForAlert(browser.getDriver(), 10);
	}

	public void clickOkButtonOnAlert(Browser browser) {
	    logger.info("Click the 'OK' button on Alert popup");
	    getAlert().accept();
	}

	public void clickCancelButtonOnAlert(Browser browser) {
	    logger.info("Click the 'Cancel' button on Alert popup");
	    getAlert().dismiss();
	}

	public String getAlertText() {
	    return getAlert().getText();
	}
}
