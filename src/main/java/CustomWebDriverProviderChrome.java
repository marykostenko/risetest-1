import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.currentThread;

public class CustomWebDriverProviderChrome implements WebDriverProvider {

    public WebDriver createDriver(DesiredCapabilities capabilities) {

        ChromeOptions chromeOptions = new ChromeOptions();

        /**
         * можно сохранять профиль пользователя Chrome
         * для этого добавить строку:
         * chromeOptions.addArguments("user-data-dir=./src/test/profiles/chrome/testProfile/");
         */

        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("disable-popup-blocking", "true");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.directory_upgrade", true);
        prefs.put("download.default_directory", new File("src/main/resources/downloads").getAbsolutePath());
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.extensions_to_open", "");
        prefs.put("password_manager_enabled", false);

        chromeOptions.setExperimentalOption("prefs", prefs);

        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        ChromeDriver chromeDriver  = new ChromeDriver(capabilities);

        return chromeDriver;
    }

}