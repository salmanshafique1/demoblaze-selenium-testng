package com.tests.base;
import com.listeners.AllureScreenshotListener;
import com.framework.config.ConfigManager;
import com.framework.core.DriverFactory;
import com.framework.core.DriverManager;
import com.listeners.AllureScreenshotListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
@Listeners({
        io.qameta.allure.testng.AllureTestNg.class,
        AllureScreenshotListener.class
})
public abstract class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setup(){
        DriverFactory.initDriver();
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigManager.getConfig().getBaseUrl());
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverManager.unload();
    }

}
