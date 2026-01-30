package com.tests.base;
import com.listeners.AllureScreenshotListener;
import com.framework.config.ConfigManager;
import com.framework.core.DriverFactory;
import com.framework.core.DriverManager;
import com.listeners.AllureScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import org.slf4j.Logger;

@Listeners({
        io.qameta.allure.testng.AllureTestNg.class,
        AllureScreenshotListener.class
})
public abstract class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        DriverFactory.initDriver();
        log.info("--------Start of the test------");
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigManager.getConfig().getBaseUrl());
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        log.info("-----End of the test-----");
        DriverManager.unload();
    }

}
