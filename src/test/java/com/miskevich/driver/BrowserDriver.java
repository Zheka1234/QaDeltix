package com.miskevich.driver;

import com.miskevich.enums.BrowserType;
import org.openqa.selenium.WebDriver;

public class BrowserDriver {

    private static WebDriver driver;

    private static BrowserType browserType;

    private BrowserDriver() {
    }

    public static void initDriver() {
        browserType = Settings.getBrowserType();
        driver = BrowserFactory.createDriver(browserType);
    }


    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


