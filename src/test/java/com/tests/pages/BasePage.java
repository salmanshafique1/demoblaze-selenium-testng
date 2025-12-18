package com.tests.pages;

import com.framework.core.DriverManager;
import com.framework.core.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    protected Waits waits;
    protected BasePage(){
        this.driver = DriverManager.getDriver();
        this.waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }
    protected void click(WebElement element){
        waits.forClickable(element).click();
    }
    protected void type(WebElement element, String text){
        WebElement e1 = waits.forVisible(element);
        e1.clear();
        e1.sendKeys(text);
    }
    protected String getText(WebElement element){
        return waits.forVisible(element).getText();
    }
    public String getPageTitle(){
        return driver.getTitle();
    }

}
