package com.miskevich.tests;

import com.miskevich.driver.BrowserDriver;
import com.miskevich.driver.Settings;
import com.miskevich.pages.ChatGridPage;
import com.miskevich.pages.LoginPage;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class CaseChatTest {

    ChatGridPage chatPage;

    LoginPage loginPage;


    @BeforeMethod
    public void openMainPage() throws IOException {
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(Settings.getUserName(), Settings.getUserPassword());
        chatPage = new ChatGridPage();

    }

    @AfterMethod
    public void cleanUp() {
        BrowserDriver.close();
    }

    @Description("Open Chat Grid." +
            "Click on order." +
            "Compare the Mid price order value in the table below and on the right in it." +
            "Compare the Mid price color of the icons in the table below and on the right in it." +
            "Compare the AVG price value in the table below with chat description." +
            "Compare the AVG price value in the sub-table with the AVG price understatement in the main table.")
    @Test
    public void testChat() {
        chatPage.chartClick();
        chatPage.orderClick();

        assertEquals(chatPage.getMidPriceTooltip(), chatPage.getMidPrice());
        assertEquals(chatPage.getMidColor(), chatPage.getMidPriceColor());
        assertEquals(chatPage.getAvgPrice(), chatPage.chatDescription());
        assertEquals(chatPage.getAvgPrice(), chatPage.getAvgTablePrice());

    }
}
