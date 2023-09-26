package com.miskevich.pages;

import com.miskevich.driver.BrowserDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

public class BasePage {

    Logger log = Logger.getLogger(BasePage.class);

    WebDriverWait wait = new WebDriverWait(BrowserDriver.getDriver(), Duration.ofSeconds(5));

    Wait<WebDriver> waitFluent = new FluentWait<>(BrowserDriver.getDriver())
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class);

    public BasePage() throws IOException {
    }
}
