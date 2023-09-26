package com.miskevich.pages;

import com.miskevich.driver.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

public class ChatGridPage extends BasePage {

    @FindBy(xpath = "//div[@class='app-title'][contains(text(),'Grid & chart')]")
    private WebElement openChat;
    @FindBy(xpath = "//div[@row-index='5']//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-with-height ag-cell-value']")
    private WebElement orderOpen;

    @FindBy(xpath = "//span[text()='Avg fill price']/following-sibling::div[@Class='legend-container__item-value']")
    private WebElement avgPrice;

    @FindBy(xpath = "//div[@class='chart-view__info mb-2 hidden-text']")
    private WebElement lineDescriptionChat;

    @FindBy(xpath = "//div[@tabindex='-1']/..//div[@col-id='orderType']")
    private List<WebElement> columnElements;

    @FindBy(xpath = "//div[@col-id='orderType']//span[@ref='eText']")
    private WebElement typeSort;

    @FindBy(xpath = "//div[@row-index='5']//div[@col-id='averageFillPrice']/span[1]")
    private WebElement tableAvg;

    @FindBy(xpath = "//span[text()='Mid price']/following-sibling::div[@Class='legend-container__item-value']")
    private WebElement midPrice;

    @FindBy(css = "g[class$='MID_PRICE']>text:last-child")
    private WebElement mid;

    @FindBy(xpath = "//div[@class='legend-container__square']")
    private WebElement midPriceColor;

    @FindBy(css = "g[class$='MID_PRICE']>rect")
    private WebElement midColor;


    @Step("Open menu Chat")
    public void chartClick() {
        log.info("chart click and wait for it to appear " + openChat.getText());
        wait.until(ExpectedConditions.visibilityOf(openChat));
        openChat.click();

    }

    @Step("Click on order")
    public void orderClick() {
        log.info("Click on order");
        orderOpen.click();
    }

    @Step("Get the icon color in your order Mid price")
    public String getMidPriceColor() {
        log.info("Get the icon color in your order Mid price" + midColor.getText());

        return midColor.getText();
    }

    @Step("Get the color of the icon in your order in the table below on the right Mid price")
    public String getMidColor() {
        log.info("Get the color of the icon in your order in the table below on the right Mid price" + midPriceColor.getText());
        return midPriceColor.getText();
    }

    @Step("Get the value in your order in the table below Mid Price")
    public double getMidPrice() {
        log.info("Get the value in your order in the table below Mid Price" + midPrice.getText());
        return Double.parseDouble(midPrice.getText());
    }

    @Step("Get the value in your order in the table below on the right Mid price")
    public double getMidPriceTooltip() {
        log.info("getMidPriceTooltip start");

        return Double.parseDouble(mid.getText());
    }

    @Step("Get the value in your order in the table below AVG Price")
    public double getAvgPrice() {
        log.info("Get the value in your order in the table below AVG Price" + avgPrice.getText());
        return Double.parseDouble(avgPrice.getText());
    }

    @Step("Get the cost of your order in the main AVG price table.")
    public double getAvgTablePrice() {
        log.info("Get the cost of your order in the main AVG price table" + tableAvg.getText());
        return Double.parseDouble(tableAvg.getText());
    }

    @Step("Get AVG price per order in description")
    public double chatDescription() {
        log.info("AVG price per order in description ");
        String temp = lineDescriptionChat.getText();
        int index = temp.indexOf("Exec price:");
        int index2 = temp.indexOf(",", index + 11);
        String result = temp.substring(index + 11, index2);
        return Double.parseDouble(result);
    }

    @Step("Elements in colum")
    public List<WebElement> getColumnElements() {
        return columnElements;

    }

    @Step("click sort type")
    public void clickSortType() {
        typeSort.click();
    }

    public ChatGridPage() throws IOException {
        PageFactory.initElements(BrowserDriver.getDriver(), this);
    }

}
