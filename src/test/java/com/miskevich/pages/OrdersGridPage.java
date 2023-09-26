package com.miskevich.pages;

import com.miskevich.driver.BrowserDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class OrdersGridPage extends BasePage {

    @FindBy(xpath = "//span[@class='ml-1'][contains(text(),'Filters')]")
    private WebElement buttonFilter;

    @FindBy(xpath = "//div[@class='ag-column-tool-panel-container']//*[@title='Id']/preceding-sibling::span")
    private WebElement clickIdFilter;

    @FindBy(xpath = "//span[@ref='eText'][contains(text(),'Id')]")
    private WebElement displayIdCheck;

    @FindBy(xpath = "//div[@col-id='orderId']")
    private WebElement displayIdCheckFalse;

    @FindBy(xpath = "//button[@ref='eToggleButton']")
    private WebElement toolPanel;

    @FindBy(xpath = "//*[@title='Id']/preceding-sibling::span")
    private WebElement toolPanelClickId;

    @FindBy(xpath = "//div[@col-id='orderId']")
    private WebElement moveClick;

    @FindBy(xpath = "//div[@col-id='orderType']//span[@ref='eMenu']")
    private WebElement columClicktype;

    @FindBy(xpath = "//span[@class='ag-icon ag-icon-columns']")
    private WebElement thirdVersionOfTheMenu;

    @FindBy(xpath = "//div[@class='ag-menu-column-select-wrapper']//*[@title='Id']/preceding-sibling::span")
    private WebElement columClickIdColumType;

    @Step("Click filter")
    public void clickFilter() {
        log.info("Click Filter ");
        buttonFilter.click();
    }

    @Step("Click on the id in filter")
    public void clickIdFilter() {
        log.info("Click on the id in filter");
        clickIdFilter.click();
    }

    @Step("Id colum appears/disappears")
    public boolean isDisplayIdColumAppearsDisappears() {
        log.info("display id colum appears/disappears ");
        try {
            if (displayIdCheckFalse.isDisplayed()) {
                return true;
            } else return false;
        } catch (Exception ex) {
            return false;
        }
    }

    @Step("Click in the toll panel")
    public void tollPanelClick() {
        log.info("click on the tool panel");
        toolPanel.click();
    }

    @Step("Click on id in the tool panel")
    public void tollPanelClickId() {
        log.info("Click on id in the tool panel");

        toolPanelClickId.click();
    }

    @Step("Hover over the Type name column and click on menu")
    public void columClickType() {
        log.info("Hover over the Type name column and click on menu");
        Actions actions = new Actions(BrowserDriver.getDriver());
        actions.moveToElement(moveClick).perform();
        columClicktype.click();


    }

    @Step("In the menu of the column named type, click on the third element")
    public void thirdVersionOfTheMenu() {
        log.info("In the menu of the column named type, click on the third element");
        thirdVersionOfTheMenu.click();

    }

    @Step("Click on the ID in the tray menu of the column named type")
    public void columClickIdColumType() {
        log.info("Click on the ID in the tray menu of the column named type");
        columClickIdColumType.click();
    }


    public OrdersGridPage() throws IOException {

        PageFactory.initElements(BrowserDriver.getDriver(), this);
    }
}