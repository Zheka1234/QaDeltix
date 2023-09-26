package com.miskevich.pages;

import com.miskevich.driver.BrowserDriver;
import com.miskevich.driver.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static com.miskevich.driver.Settings.getMyProperties;


public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement loginUser;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordUser;

    @FindBy(xpath = "//button")
    private WebElement button;

    @Step("Login to the site")
    public void login(String setLogin, String setPassword) {
        log.info("Login to the site");
        loginUser.click();
        loginUser.sendKeys(setLogin);
        passwordUser.click();
        passwordUser.sendKeys(setPassword);
        button.click();
    }

    @Step("Open site")
    public void open() throws IOException {
        log.error("No open site" + getMyProperties().getProperty("siteUrl"));
        BrowserDriver.getDriver().get(Settings.getBaseUrl());

    }

    public LoginPage() throws IOException {
        PageFactory.initElements(BrowserDriver.getDriver(), this);
    }
}
