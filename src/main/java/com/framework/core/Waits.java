package com.framework.core;

import com.framework.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waits {
    private final WebDriverWait wait;
    public Waits(WebDriver driver){
        int seconds = ConfigManager.getConfig().getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    public WebElement forVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement forClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
