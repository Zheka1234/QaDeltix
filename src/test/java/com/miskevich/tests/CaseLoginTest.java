package com.miskevich.tests;

import com.miskevich.driver.BrowserDriver;
import com.miskevich.driver.Settings;
import com.miskevich.pages.LoginPage;
import com.miskevich.pages.MainPage;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class CaseLoginTest {

    LoginPage loginPage;

    MainPage mainPage;

    @BeforeMethod
    public void openMainPage() throws IOException {
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(Settings.getUserName(), Settings.getUserPassword());
        mainPage = new MainPage();

    }

    @AfterMethod
    public void cleanUp() {
        BrowserDriver.close();
    }


    @DataProvider(name = "checkSection")
    public Object[][] sectionNamesProvider() {
        return new Object[][]{
                {"Summary"},
                {"Grid & chart"},
                {"Histogram"},
                {"Scatter-plot"},
                {"Reports"},
        };
    }

    @Description("Log in." +
            "Open main page" +
            "Settings button is displayed" +
            "Benchmark Selector control is displayed" +
            "Application Toolbar with Summary, Grid & chart, Histogram, Scatter-plot and Reports tabs is displayed")
    @Test(dataProvider = "checkSection")
    public void caseLoginTest(String sectionName) throws IOException {
        assertTrue(mainPage.isSettingDisplayed());
        assertTrue(mainPage.iskBenchmarkSelectionDisplayed());
        mainPage.section(sectionName);

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(mainPage.section(sectionName).isDisplayed());

        soft.assertAll();

    }
}
