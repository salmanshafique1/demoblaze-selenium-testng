package com.framework.core;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {}

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }


    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);

    public static void unload() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            log.info("Closing WebDriver");
            driver.quit();
            DRIVER.remove();
        }
    }
}
