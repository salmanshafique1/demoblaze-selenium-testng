package com.framework.core;

import com.framework.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class Waits {
    private static final Logger log = LoggerFactory.getLogger(Waits.class);
    private final WebDriverWait wait;
    public Waits(WebDriver driver){
        int seconds = ConfigManager.getConfig().getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    public WebElement forVisible(WebElement element){
        log.info("Waiting for element to be visible: {}", element.toString());
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement forClickable(WebElement element){
        log.info("Waiting for element to be clickable: {}", element.toString());
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}