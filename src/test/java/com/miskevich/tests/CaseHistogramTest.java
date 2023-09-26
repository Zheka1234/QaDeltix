package com.miskevich.tests;

import com.miskevich.driver.BrowserDriver;
import com.miskevich.driver.Settings;
import com.miskevich.pages.HistogramPage;
import com.miskevich.pages.LoginPage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CaseHistogramTest {

    HistogramPage histogramPage;

    LoginPage loginPage;

    private String COUNT_PATTERN = "Count: \\d+";

    private String AVG_PATTERN = "Avg fill price: [\\[,\\(]\\d+\\.\\d+, \\d+\\.\\d+[),\\]]";


    @BeforeMethod
    public void openMainPage() throws IOException {
        loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(Settings.getUserName(), Settings.getUserPassword());
        histogramPage = new HistogramPage();
    }

    @AfterMethod
    public void cleanUp() {
        BrowserDriver.close();
    }

    @Description("Open histogram." +
            "Hover over the columns." +
            "Values are displayed.")
    @Test
    public void testHistogram() {

        assertTrue(histogramPage.histogramClick());
        List<WebElement> barContainers = histogramPage.getBars();
        assertTrue(barContainers.size() > 0);
        barContainers.forEach(barContainer -> {
            WebElement bar = histogramPage.getBarElement(barContainer);
            try {
                histogramPage.moveMouseToElement(bar, -20, -20);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            histogramPage.getTooltip();
            String borders = histogramPage.getBordersText();
            String count = histogramPage.getCountText();
            assertTrue(borders.matches(AVG_PATTERN), borders);
            assertTrue(count.matches(COUNT_PATTERN), count);

        });
    }
}