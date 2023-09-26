package com.miskevich.driver;

import com.miskevich.enums.BrowserType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private static Properties properties;

    private Settings() {
    }


    public static Properties getMyProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("src/test/resources/project.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return properties;

    }

    public static BrowserType getBrowserType() {
        return BrowserType.valueOf(getMyProperties().getProperty("browserType"));
    }

    public static String getBaseUrl() {
        return getMyProperties().getProperty("siteUrl");
    }

    public static String getUserName() {
        return getMyProperties().getProperty("userName");
    }

    public static String getUserPassword() {
        return getMyProperties().getProperty("userPassword");
    }


}

