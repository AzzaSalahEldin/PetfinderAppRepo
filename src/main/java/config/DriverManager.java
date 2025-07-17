package driver;

import config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.*;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String env = ConfigManager.get("env");
            String browser = ConfigManager.get("browser");
            boolean headless = ConfigManager.getBoolean("headless");

            if (env.equalsIgnoreCase("remote")) {
                driver.set(initRemoteDriver(browser, headless));
            } else {
                driver.set(initLocalDriver(browser, headless));
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    private static WebDriver initLocalDriver(String browser, boolean headless) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless=new");
            return new ChromeDriver(options);
        }
        throw new RuntimeException("Unsupported local browser: " + browser);
    }

    private static WebDriver initRemoteDriver(String browser, boolean headless) {
        String hubUrl = ConfigManager.get("hubUrl");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless=new");

            try {
                return new RemoteWebDriver(new URL(hubUrl), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid hub URL: " + hubUrl, e);
            }
        }
        throw new RuntimeException("Unsupported remote browser: " + browser);
    }
}
