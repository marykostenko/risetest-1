package naumen.framework.base;

import junit.framework.Assert;
import naumen.framework.base.elements.BaseElement;
import org.testng.Reporter;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger {
	/**
	 * Locale
	 */
	public enum Locale {
		/**
		 * @uml.property name="eN"
		 * @uml.associationEnd
		 */
		EN, /**
		 * @uml.property name="rU"
		 * @uml.associationEnd
		 */
		RU
	}

	private static final Locale DEF_LOCALE = Locale.RU;
	private static final String INCROWD_LOCALE = "aqa.locale";
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
	private static Logger instance = null;
	public static final String LOG_DELIMITER = "::";
	// This flag allows/restricts logging step names
	private static boolean logSteps = true;
	private static PropertiesResourceManager localManager = new PropertiesResourceManager(String.format("localization/loc_%1$s.properties",
            System.getProperty(INCROWD_LOCALE, DEF_LOCALE.toString()).toLowerCase()));
	private static int screenIndex = 0;

	/**
	 * Gets locale
	 * @param key Key
	 * @return Locale
	 */
	protected static String getLoc(final String key) {
		return localManager.getProperty(key, key);
	}

	/** конвертирует сообщения в UTF-8
	 * @param message сообщение
	 * @return после конвертации
	 */
	public static String convert(String message) {
    	if (System.getProperty("os.name").toLowerCase().contains("win"))
            message = message.replace("И", "и");
	return message;
	}

	/** конвертирует сообщения в UTF-8
	 * @param message сообщение
	 * @return после конвертации
	 */
	public static String convertSimple(String message) {
    	byte[] bytesInUTF8 = null;
		try {
			bytesInUTF8 = message.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	return new String(bytesInUTF8);
	}

	/**
	 * Sets the new local
	 * @param newLoc new Local manager
	 */
	public static void setNewLocalManager(final PropertiesResourceManager newLoc){
		localManager = newLoc;
	}

	/**
	 * local info
	 * @param msg Message
	 */
	public void infoLoc(final String msg) {
		String message = localManager.getProperty(msg, msg);
		logger.info(message);
//		Reporter.log(message + "<br>");
        Reporter.log(message);
	}

	/**
	 * Constructor
	 */
	private Logger() {
	}

	/**
	 * Implementation of the Singleton pattern
	 * @return Logger instance
	 */
	public static synchronized Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

	/**
	 * Logging a step number
	 * @param step - step number
	 */
	public void step(final int step) {
		logDelimMsg(getLoc("loc.logger.step") + String.valueOf(step));
	}

	/**
	 * Logging a several steps in a one action
	 * @param fromStep - the first step number to be logged
	 * @param toStep - the last step number to be logged
	 */
	public void step(final int fromStep, final int toStep) {
		logDelimMsg(getLoc("loc.logger.steps") + String.valueOf(fromStep) + "-" + String.valueOf(toStep));
	}

	/**
	 * This method is using for formatting almost all log records
	 * @param msg Formatted message
	 */
	private void logDelimMsg(final String msg) {
		if (logSteps) {
			info(String.format("--------==[ %1$s ]==--------", msg));
		}
	}

	/**
	 * This method logs test's name
	 * @param testName test's name
	 */
	public void logTestName(final String testName) {
		if (logSteps) {
			String formattedName = String.format("=====================  %1$s: '%2$s' =====================", getLoc("loc.logger.test.case"), testName);

			String delims = "";
			int nChars = formattedName.length();
			for (int i = 0; i < nChars; i++) {
				delims += "-";
			}
			info(delims);
			info(formattedName);
			info(delims);
			logDelimMsg(getLoc("loc.logger.test.preconditions"));
		}
	}

	/**
	 * Prints the dots
	 * @param count Amount of dots
	 */
	public void printDots(final int count) {
		String delims = "";
		for (int i = 0; i < count; i++) {
			delims += ".";
		}
		info(delims);
	}

	/**
	 * log Test End
	 * @param testName test's name
	 */
	public void logTestEnd(final String testName) {
		if (logSteps) {
			info("");
			String formattedEnd = String.format("***** %1$s: '%2$s' %3$s! *****", getLoc("loc.logger.test.case"), testName, getLoc("loc.logger.test.passed"));
			String stars = "";
			int nChars = formattedEnd.length();
			for (int i = 0; i < nChars; i++) {
				stars += "*";
			}
			info(stars);
			info(formattedEnd);
			info(stars);
			info("");
		}
	}

	/**
	 * This method logs test's name
	 * @param testName test's name
	 */
	public void logPrereqName(final String testName) {
		if (logSteps) {
			info(String.format("=====================  %1$s: '%2$s' =====================", getLoc("loc.logger.test.prerequisite.case"), testName));
			info("----------------------------------------------------------------------------------------------------");
			logDelimMsg("Preconditions");
		}
	}

	/**
	 * Prerequisites
	 * @param testName test's name
	 */
	public void logPrereq(final String testName) {
		if (logSteps) {
			info("----------------------------------------------------------------------------------------------------");
			info(String.format("=====================  %1$s: '%2$s' =====================", getLoc("loc.logger.test.prerequisite.case.creating"), testName));
			info("----------------------------------------------------------------------------------------------------");
		}
	}

	/**
	 * End of Prerequisites
	 * @param testName test's name
	 */
	public void logPrereqEnd(final String testName) {
		if (logSteps) {
			info(String.format("===================== %1$s: '%2$s' =====================", getLoc("loc.logger.test.prerequisite.case.succes"), testName));
			info("----------------------------------------------------------------------------------------------------");
		}
	}

	// All methods below are using for creation messages with a different level of importance
	/**
	 * Debug log
	 * @param message Message
	 */
	public void debug(final String message) {
		String msg = message;
		logger.debug(msg);
		msg = "<div class=\"skipped\">" + message + "</div>"; // yellow color from reportng css
		String m = convert(msg);
		Reporter.log(m + "<br>");
	}

	/**
	 * Info log
	 * @param message Message
	 */
	public void info(final String message) {
		logger.info(message);
		Reporter.log(getTime() + convert(message) + "<br>");
	}

	/**
	 * Info link
	 * @param link link
	 */
	public void infoLink(final String link) {
		logger.info(link);
		String href = CommonFunctions.regexGetMatch(link, "http[\\W\\w]+");
		Reporter.log(getTime() + convert(link.replace(href, "")) + "<a href='" + href + "'>" + href + "</a><br>");
	}

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("[HH:mm:ss]  ");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
	 * Warning log
	 * @param message Message
	 */
	public void warn(final String message) {
		String msg = message;
		logger.warn(message);
        msg = "<div class=\"orange\">WARNING: " + msg + "</div>"; // orange color from reportng css
        Reporter.log(getTime() + convert(msg) + "<br>");
	}

    /**
     * Warning log
     * @param message Message
     */
    public void notify(final String message) {
        String msg = message;
        logger.warn(message);
        msg = "<div class=\"skipped\">" + msg + "</div>"; // yellow color from reportng css
        Reporter.log(getTime() + convert(msg) + "<br>");
    }


	/**
	 * Warning log with screenshot
	 * @param message Message
	 */
	public void warnWithScreenShot(final String message, BaseTest test) {
		String msg = message;
		logger.warn(message);
        msg = "<div class=\"orange\">" + msg + "</div>"; // orange color from reportng css
		Reporter.log(getTime() + convert(msg) + "<br>");
		String str = "<img width=\"600\" height=\"480\" src='" + convert(BaseEntity.makeScreen("screen" + screenIndex)) + "'/>" + "<br>";
        Reporter.log(str);
		screenIndex++;
		test.warn();
	}
    /**
     * Notification log with screenshot
     * @param message Message
     */
    public void notifyWithScreenShot(final String message) {
        String msg = message;
        logger.warn(message);
		msg = "<div class=\"skipped\">" + msg + "</div>"; // yellow color from reportng css
        Reporter.log(getTime() + convert(msg) + "<br>");
        String str = "<img width=\"600\" height=\"480\" src='" + convert(BaseEntity.makeScreen("screen" + screenIndex)) + "'/>" + "<br>";
        Reporter.log(str);
        screenIndex++;
    }


	/**
	 * make screenshot of the element
	 * @param element element
	 */
	public void includeElementScreenShot(BaseElement element) {
		String msg = "Скриншот элемента: " + element.getName() + " >>>";
		logger.info(msg);
		Reporter.log(convert(msg) + "<br>");
		Reporter.log("<img src='" + convert(BaseEntity.makeElementScreen("screen" + screenIndex, element)) + " '/>" + "<br>");
		screenIndex++;
	}


	/**
	 * Red Warning log
	 * @param message Message
	 */
	public void warnRed(final String message) {
		String msg = message;
		logger.warn(msg);
		msg = "<div class=\"failedConfig\">" + msg + "</div>"; // red color from reportng css
		Reporter.log(getTime() + convert(msg) + "<br>");
	}

	/**
	 * Error log
	 * @param message Message
	 */
	public void error(final String message) {
		String msg = message;
		logger.error(message);
		msg = "<div class=\"failedConfig\">" + msg + "</div>"; // red color from reportng css
		Reporter.log(getTime() + convert(msg) + "<br>");
	}

	/**
	 * Fatal log
	 * @param message Message
	 */
	public void fatal(final String message) {
		String msg = message;
		logger.fatal(message);
		msg = "<div class=\"failedConfig\">" + msg + "</div>"; // red color from reportng css
		Reporter.log(getTime() + convert(msg) + "<br>");
		Assert.assertTrue(false);
	}
}
