package com.miskevich.driver;

import com.miskevich.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BrowserFactory {
    static {
        System.setProperty("webdriver.chrome.driver", Settings.getMyProperties().getProperty("webdriver.chrome.driver"));

    }

    public static WebDriver createDriver(BrowserType browserType) {
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case REMOTE_WEB_DRIVER:
                try {
                    driver = new RemoteWebDriver(new URL("http://192.168.0.104:4444"), new ChromeOptions());
                } catch (MalformedURLException e) {
                    throw new RuntimeException("URL is not supported" + e.getMessage());
                }
                break;
            default:
                throw new IllegalStateException("Browser Not Supported: " + browserType);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
