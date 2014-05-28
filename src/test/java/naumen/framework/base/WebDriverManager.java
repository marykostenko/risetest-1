package naumen.framework.base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public class WebDriverManager {
    private static HashMap<Long, RemoteWebDriver> driversMap = new HashMap<Long, RemoteWebDriver>();

    public static RemoteWebDriver getDriver(Long threadID){
        if (driversMap.containsKey(threadID))
            return driversMap.get(threadID);
        else{
            return startNewDriver(threadID, "chrome");
        }
    }

    public static RemoteWebDriver getDriver(Long threadID, String browser, DesiredCapabilities cp){
        if (driversMap.containsKey(threadID))
            return driversMap.get(threadID);
        else{
            return startNewDriver(threadID, browser, cp);
        }
    }

    private static RemoteWebDriver startNewDriver(Long threadID, String browser){
        System.out.println("Starting new webdriver, threadID=" + threadID);
        RemoteWebDriver d;
        if(browser.equalsIgnoreCase("chrome")){
            d = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")) {
            d = new FirefoxDriver();
        } else if(browser.contains("explore")) {
            d = new InternetExplorerDriver();
        } else {
            throw new IllegalArgumentException("Browser type not supported: " + browser);
        }
        driversMap.put(threadID, d);
        return d;
    }

    private static RemoteWebDriver startNewDriver(Long threadID, String browser, DesiredCapabilities cp){
        System.out.println("Starting new webdriver, threadID=" + threadID);
        RemoteWebDriver d;
        if(browser.equalsIgnoreCase("chrome")){
            d = new ChromeDriver(cp);
        } else if(browser.equalsIgnoreCase("firefox")) {
            d = new FirefoxDriver(cp);
        }
        else if(browser.contains("explore")) {
            d = new InternetExplorerDriver(cp);
        } else {
            throw new IllegalArgumentException("Browser type not supported: " + browser);
        }
        driversMap.put(threadID, d);
        return d;
    }

    public static void stopDriver(Long threadID){
        if (!driversMap.containsKey(threadID)){
            System.out.println("No driver in map with treadID=" + threadID);
            return;
        }
        else{
            System.out.println("Stopping webdriver, threadID=" + threadID);
            try{
                Thread.sleep(2000);
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
            driversMap.get(threadID).quit();
            driversMap.remove(threadID);
        }
    }
}
