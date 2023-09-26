package com.miskevich.pages;

import com.miskevich.driver.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@class='d-flex align-items-center benchmark-selection']")
    private WebElement benchmarkSelection;

    @FindBy(xpath = "//a[@href='/settings']")
    private WebElement settingButton;

    @Step("Set name elements menu ")
    public WebElement section(String sectionName) {
        WebElement element = BrowserDriver.getDriver()
                .findElement(By.xpath("//div[@class='app-title'][contains(text(),'')]"));
        sectionName.isEmpty();
        return element;
    }

    @Step("Setting button is displayed")
    public boolean isSettingDisplayed() {
        log.info("Setting button is displayed" + settingButton.isDisplayed());

        return settingButton.isDisplayed();
    }

    @Step("Benchmark selection is displayed")
    public boolean iskBenchmarkSelectionDisplayed() {
        log.info("Benchmark selection is displayed" + benchmarkSelection.isDisplayed());
        return benchmarkSelection.isDisplayed();
    }

    public MainPage() throws IOException {
        PageFactory.initElements(BrowserDriver.getDriver(), this);
    }
}
