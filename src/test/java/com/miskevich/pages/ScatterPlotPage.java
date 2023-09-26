package com.miskevich.pages;

import com.miskevich.driver.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

public class ScatterPlotPage extends BasePage {

    @FindBy(xpath = "//div[@class='app-title'][contains(text(),'Scatter-plot')]")
    private WebElement openScatter;

    @FindBy(xpath = "//div[text()='X Attribute']/following-sibling::*")
    private WebElement xAttribute;

    @FindBy(xpath = "//div[text()='Y Attribute']/following-sibling::*")
    private WebElement yAttribute;

    @FindBy(xpath = "//div[text()='Intervals']/following-sibling::*")
    private WebElement interval;

    @FindBy(xpath = "//*[@class='scatter-plot-x-label']")
    private WebElement xLabel;

    @FindBy(xpath = "//*[@class='scatter-plot-y-label']")
    private WebElement yLabel;

    @FindBy(css = "g[class='x axis']")
    private WebElement xAxis;

    @FindBy(css = "g[class='y axis']")
    private WebElement yAxis;

    @FindBy(xpath = "//*[contains(@class,'scatter-plot-container')]//*[contains(@class,'scatter_element')]")
    private List<WebElement> bubbles;

    @Step("Open scatter-plot menu")
    public void scatterClick() {
        log.info("Open scatter-plot menu");
        wait.until(ExpectedConditions.visibilityOf(openScatter));
        openScatter.click();
    }

    @Step("Select x attribute")
    public void xAttributeSelect(String xAttributeText) {
        log.info("X the attribute has not changed" + xAttributeText);
        waitFluent.until(ExpectedConditions.visibilityOf(xAttribute));
        xAttribute.click();
        WebElement xAttributeElement = BrowserDriver.getDriver()
                .findElement(By.xpath("//li[@class='autocomplete-dropdown-item']//a[contains(text(),'" + xAttributeText + "')]"));
        xAttributeElement.sendKeys(Keys.ENTER);
    }

    @Step("Select y attribute")
    public void yAttributeSelect(String yAttributeText) {
        log.info("Y the attribute has not changed" + yAttributeText);
        waitFluent.until(ExpectedConditions.visibilityOf(yAttribute));
        yAttribute.click();
        WebElement yAttributeElement = BrowserDriver.getDriver()
                .findElement(By.xpath("//li[@class='autocomplete-dropdown-item']//a[contains(text(),'" + yAttributeText + "')]"));
        yAttributeElement.sendKeys(Keys.ENTER);
    }


    @Step("Get bubbles")
    public boolean getBubbles() {
        log.info("Get bubbles");
        bubbles.size();
        return bubbles.size() > 0;
    }

    @Step("X axis changes")
    public String getXAxis() {
        log.warn("X axis remains the same" + xAxis);
        return xAxis.getText();
    }

    @Step("Y axis changes")
    public String getYAxis() {
        log.warn("Y axis remains the same" + yAxis);
        return yAxis.getText();
    }

    @Step("X axis name changes")
    public boolean isDisplayedLabelX() {
        log.info("axis name changes x " + xLabel.getText());
        xLabel.getText();
        return xLabel.isDisplayed();
    }

    @Step("Y axis name changes")
    public boolean isDisplayedLabelY() {
        log.info("axis name changes y " + yLabel.getText());
        yLabel.getText();
        return yLabel.isDisplayed();
    }

    @Step("Y axis name changes")
    public boolean isDisplayedXAttribute() {
        log.info("The attribute name changes x " + xAttribute.getText());
        xAttribute.getText();
        return xAttribute.isDisplayed();
    }

    public boolean isDisplayedYAttribute() {
        log.info("The attribute name changes y " + yAttribute.getText());
        yAttribute.getText();

        return yAttribute.isDisplayed();
    }

    public ScatterPlotPage() throws IOException {
        PageFactory.initElements(BrowserDriver.getDriver(), this);
    }
}
