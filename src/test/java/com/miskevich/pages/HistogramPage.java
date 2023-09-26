package com.miskevich.pages;

import com.miskevich.driver.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

public class HistogramPage extends BasePage {

    @FindBy(xpath = "//div[@class='app-title'][contains(text(),'Histogram')]")
    private WebElement openHistogram;

    @FindBy(xpath = "//*[contains(@class,'bars')]//*[contains(@class,'bar_container')]")
    private List<WebElement> bars;

    @FindBy(xpath = "//*[@class='tooltip']")
    private WebElement tool;

    @Step("Open the histogram menu and wait for the bars ")
    public boolean histogramClick() {
        log.info("histogram click and wait for it to appear " + openHistogram.getText());
        wait.until(ExpectedConditions.visibilityOf(openHistogram));
        openHistogram.click();
        log.info("histogramClick good");
        return openHistogram.isDisplayed();
    }

    @Step("Columns is displayed")
    public List<WebElement> getBars() {
        log.info("Our columns is displayed");
        return bars;
    }

    @Step("Move the mouse over the column")
    public void moveMouseToElement(WebElement element, int xOffset, int yOffset) throws IOException {
        Actions actions = new Actions(BrowserDriver.getDriver());
        actions.moveToElement(element, xOffset, yOffset);
        actions.build().perform();
    }

    @Step("Element is displayed")
    public WebElement getBarElement(WebElement barContainer) {
        return waitFluent.until(ExpectedConditions.visibilityOf(barContainer.findElement(By.xpath("./*[@class='bar']"))));
    }

    @Step("Get the values")
    public WebElement getTooltip() {
        log.info("getTooltip start");
        return tool;
    }

    @Step("AVG price is displayed")
    public String getBordersText() {
        return tool.findElement(By.xpath("./div[1]")).getText();
    }


    @Step("Count is displayed")
    public String getCountText() {
        return tool.findElement(By.xpath("./div[2]")).getText();
    }


    public HistogramPage() throws IOException {
        PageFactory.initElements(BrowserDriver.getDriver(), this);
    }

}
