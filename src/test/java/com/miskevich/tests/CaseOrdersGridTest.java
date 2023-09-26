package com.miskevich.tests;

import com.miskevich.driver.BrowserDriver;
import com.miskevich.driver.Settings;
import com.miskevich.pages.ChatGridPage;
import com.miskevich.pages.LoginPage;
import com.miskevich.pages.OrdersGridPage;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CaseOrdersGridTest {

    OrdersGridPage ordersGridPage;

    ChatGridPage chatGridPage;

    LoginPage loginPage;

    @BeforeMethod
    public void openMainPage() throws IOException {
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(Settings.getUserName(), Settings.getUserPassword());
        chatGridPage = new ChatGridPage();
        ordersGridPage = new OrdersGridPage();
    }

    @AfterMethod
    public void cleanUp() {
        BrowserDriver.close();
    }

    @Description("Open chat menu" +
            "Click on filter" +
            "Click id" +
            "Colum id disappears" +
            "Click on filter" +
            "Click id" +
            "Colum id appears")
    @Test
    public void testColumFilter() {
        chatGridPage.chartClick();
        ordersGridPage.clickFilter();
        ordersGridPage.clickIdFilter();
        assertFalse(ordersGridPage.isDisplayIdColumAppearsDisappears());
        ordersGridPage.clickFilter();
        ordersGridPage.clickIdFilter();

        assertTrue(ordersGridPage.isDisplayIdColumAppearsDisappears());
    }

    @Description("Open chat menu" +
            "Click on tool panel" +
            "Click id" +
            "Colum id disappears" +
            "Click id" +
            "Colum id appears")
    @Test
    public void testColumToolPanel() {
        chatGridPage.chartClick();
        ordersGridPage.tollPanelClick();

        ordersGridPage.tollPanelClickId();
        assertFalse(ordersGridPage.isDisplayIdColumAppearsDisappears());
        ordersGridPage.tollPanelClickId();
        assertTrue(ordersGridPage.isDisplayIdColumAppearsDisappears());

    }

    @Description("Open chat menu" +
            "Hover over the Type name column and click on menu" +
            "In the menu of the column named type, click on the third element" +
            "Click on the ID in the tray menu of the column named type" +
            "Colum id disappears" +
            "Click on the ID in the tray menu of the column named type" +
            "Colum id appears")
    @Test
    public void testOrdersGridFilterColumns() {
        chatGridPage.chartClick();
        ordersGridPage.columClickType();
        ordersGridPage.thirdVersionOfTheMenu();

        ordersGridPage.columClickIdColumType();
        assertFalse(ordersGridPage.isDisplayIdColumAppearsDisappears());
        ordersGridPage.columClickIdColumType();
        assertTrue(ordersGridPage.isDisplayIdColumAppearsDisappears());

    }


}
