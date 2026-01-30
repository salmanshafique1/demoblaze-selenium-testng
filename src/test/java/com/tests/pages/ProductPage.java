package com.tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ProductPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(ProductPage.class);

    @FindBy(css = ".name")
    private WebElement productTitle;

    @FindBy(css = ".price-container")
    private WebElement priceContainer;

    @FindBy(linkText = "Add to cart")
    private WebElement addToCartLink;

    public String getProductTitle() {
        return getText(productTitle);
    }

    public String getPriceText() {
        return getText(priceContainer);
    }
    public ProductPage addToCartAndAcceptAlert() {
        log.info("Adding product to cart");
        click(addToCartLink);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        return this;
    }
}