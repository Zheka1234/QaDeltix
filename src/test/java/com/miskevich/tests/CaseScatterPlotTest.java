package com.miskevich.tests;

import com.miskevich.driver.BrowserDriver;
import com.miskevich.driver.Settings;
import com.miskevich.pages.LoginPage;
import com.miskevich.pages.ScatterPlotPage;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class
CaseScatterPlotTest {

    ScatterPlotPage scatterPlot;

    LoginPage loginPage;


    @BeforeMethod
    public void openMainPage() throws IOException {
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(Settings.getUserName(), Settings.getUserPassword());
        scatterPlot = new ScatterPlotPage();
    }

    @AfterMethod
    public void clenUp() {
        BrowserDriver.close();
    }


    @DataProvider(name = "attributeNames")
    public Object[][] attributeNamesProvider() {
        return new Object[][]{
                {"Price", "Exec size"},
                {"Exec size", "Num of executions"},
                {"Avg fill price", "Participation rate"},
                {"Num of executions", "Size"},
                {"Size", "Avg fill price"},


        };
    }

    @Description("Open scatter-plot" +
            "Select x Attribute." +
            "Select Y Attribute." +
            "Check that:" +
            "The names of the axes coincide with the names of the attributes" +
            "When changing attributes, the coordinate axis changes" +
            "Coordinate axis is displayed")
    @Test(dataProvider = "attributeNames")
    public void scatterPlotTest(String xAttributeName, String yAttributeName) {

        scatterPlot.scatterClick();


        String initialXAxis = scatterPlot.getXAxis();
        String initialYAxis = scatterPlot.getYAxis();

        scatterPlot.xAttributeSelect(xAttributeName);
        scatterPlot.yAttributeSelect(yAttributeName);

        SoftAssert soft = new SoftAssert();
        soft.assertTrue(scatterPlot.getBubbles());
        soft.assertEquals(scatterPlot.isDisplayedLabelX(), scatterPlot.isDisplayedXAttribute());
        soft.assertEquals(scatterPlot.isDisplayedLabelY(), scatterPlot.isDisplayedYAttribute());


        String updatedXAxis = scatterPlot.getXAxis();
        String updatedYAxis = scatterPlot.getYAxis();

        soft.assertNotEquals(initialXAxis, updatedXAxis, "X-axis values are not updated");
        soft.assertNotEquals(initialYAxis, updatedYAxis, "Y-axis values are not updated");
        soft.assertAll();
    }
}
