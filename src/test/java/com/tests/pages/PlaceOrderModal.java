package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderModal extends BasePage {

    @FindBy(id = "name") private WebElement nameInput;
    @FindBy(id = "country") private WebElement countryInput;
    @FindBy(id = "city") private WebElement cityInput;
    @FindBy(id = "card") private WebElement cardInput;
    @FindBy(id = "month") private WebElement monthInput;
    @FindBy(id = "year") private WebElement yearInput;
    @FindBy(xpath = "//button[text()='Purchase']") private WebElement purchaseButton;
    @FindBy(xpath = "//button[text()='OK']") private WebElement confirmationOkButton;

    private final By confirmationTitleBy =
            By.cssSelector(".sweet-alert.showSweetAlert.visible h2");

    private final By confirmationDetailsBy =
            By.cssSelector(".sweet-alert.showSweetAlert.visible p");


    public PlaceOrderModal fillOrderForm(String name,
                                         String country,
                                         String city,
                                         String card,
                                         String month,
                                         String year) {

        type(nameInput, name);
        type(countryInput, country);
        type(cityInput, city);
        type(cardInput, card);
        type(monthInput, month);
        type(yearInput, year);

        return this;
    }

    public PlaceOrderModal clickPurchase() {
        click(purchaseButton);
        return this;
    }

    public String getConfirmationTitle() {
        WebElement title = waits.forVisible(driver.findElement(confirmationTitleBy));
        return title.getText();
    }

    public String getConfirmationDetails() {
        WebElement details = waits.forVisible(driver.findElement(confirmationDetailsBy));
        return details.getText();
    }

    public void confirmOrder() {
        click(confirmationOkButton);
    }
}