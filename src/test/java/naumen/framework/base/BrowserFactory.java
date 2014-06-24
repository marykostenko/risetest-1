package naumen.framework.base;

import naumen.framework.base.Browser.Browsers;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.naming.NamingException;
import java.util.Arrays;

import static naumen.framework.base.Logger.getLoc;

/** setup platform and browser
 */
public abstract class BrowserFactory {

	public static PropertiesResourceManager props = new PropertiesResourceManager(Browser.PROPERTIES_FILE);

	/**
	 * Setting up Driver
	 * @param type Browser type
	 * @return RemoteWebDriver
	 */
	public static RemoteWebDriver setUp(final Browsers type) {
		RemoteWebDriver driver = null;
        DesiredCapabilities cp;
        Long threadID = Thread.currentThread().getId();
		switch (type) {
		case CHROME:
			cp = DesiredCapabilities.chrome();
			cp.setCapability("chrome.switches", Arrays.asList("--no-sandbox", "--disable-popup-blocking"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("lang=ru");
			cp.setCapability(ChromeOptions.CAPABILITY, options);
            cp.setCapability(CapabilityType.PROXY, Browser.getProxy());
			String chr_dr = "chromedriver";
            if (System.getProperty("os.name").toLowerCase().contains("windows"))
                chr_dr += ".exe";
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/../" + chr_dr);
            driver = WebDriverManager.getDriver(threadID, type.toString(), cp);
			break;
		case FIREFOX:
			cp = DesiredCapabilities.firefox();
            cp.setCapability(CapabilityType.PROXY, Browser.getProxy());
            driver = WebDriverManager.getDriver(threadID, type.toString(), cp);
			break;
		case IEXPLORE:
			//local security request flag INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS
			//(but this flag may cause appearing "skipped" tests)
			//if(new PropertiesResourceManager(Browser.PROPERTIES_FILE).getProperty("localrun").equalsIgnoreCase("true")){
				cp = DesiredCapabilities.internetExplorer();
				cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cp.setCapability("ignoreZoomSetting", true);
				cp.setCapability("ignoreProtectedModeSettings" , true);
                cp.setCapability("UnexpectedAlertBehavior", "Ignore");
                cp.setCapability(CapabilityType.PROXY, Browser.getProxy());
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/../IEDriverServer.exe");
				// делаем три попытки запуска IE из-за возможного возникновения:
				// Unexpected error launching Internet Explorer.
				// Protected Mode must be set to the same value (enabled or disabled) for all zones.
                for(int i = 0; i <=2; i++){
					try{
						Logger.getInstance().info("Попытка запуска IE №" + (i + 1));
						driver = WebDriverManager.getDriver(threadID, type.toString(), cp);
						break;
					}catch(org.openqa.selenium.WebDriverException e){
						continue;
					}
				}
			break;
		default:
			break;
		}
        driver.manage().window().maximize();
        return driver;
	}

	/**
	 * Setting up Driver
	 * @param type Browser type
	 * @return RemoteWebDriver
	 * @throws NamingException NamingException
	 */
	public static RemoteWebDriver setUp(final String type) throws NamingException {
		for (Browsers t : Browsers.values()) {
			if (t.toString().equalsIgnoreCase(type)) {
				return setUp(t);
			}
		}
		throw new NamingException(getLoc("loc.browser.name.wrong")+":\nchrome\nfirefox\niexplore");
	}
}
