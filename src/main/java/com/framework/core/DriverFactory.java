package com.framework.core;

import com.framework.config.ConfigManager;
import com.framework.config.FrameworkConfig;
import com.framework.enums.BrowserType;
import com.framework.enums.RunMode;
import com.framework.exceptions.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public final class DriverFactory {
    private DriverFactory(){

    }
    public static void initDriver(){
        if (DriverManager.getDriver() != null){
            return;
        }
        FrameworkConfig cfg = ConfigManager.getConfig();
        BrowserType browserType = BrowserType.valueOf(cfg.getBrowser().toUpperCase());
        RunMode runMode = RunMode.valueOf(cfg.getRunMode().toUpperCase());

        WebDriver driver;
        switch (runMode){
            case LOCAL -> driver = createLocalDriver(browserType, cfg.isHeadless());
            case GRID -> driver = createRemoteDriver(browserType, cfg.getGridUrl());
            default -> throw new FrameworkException("Unsupported run mode: " + runMode);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        DriverManager.setDriver(driver);
    }
    private static WebDriver createLocalDriver(BrowserType browserType, boolean headless){
        return switch (browserType){
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if(headless){
                    options.addArguments("--headless=new");
                }
                options.addArguments("--disable-gpu");
                options.addArguments("--start-maximized");
                yield new ChromeDriver(options);
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (headless){
                    options.addArguments("-headless");
                }
                yield new FirefoxDriver(options);
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                if (headless){
                    options.addArguments("--headless=new");
                }
                yield new EdgeDriver(options);
            }
        };
    }
    private static WebDriver createRemoteDriver(BrowserType browserType, String gridUrl){
        try {
            URL url = new URL(gridUrl);
            return switch (browserType) {
                case CHROME -> new RemoteWebDriver(url, new ChromeOptions());
                case FIREFOX -> new RemoteWebDriver(url, new FirefoxOptions());
                case EDGE -> new RemoteWebDriver(url, new EdgeOptions());
            };
        }
            catch (Exception e) {
                throw new FrameworkException("Failed to create driver for GRID", e);
        }
    }
}
