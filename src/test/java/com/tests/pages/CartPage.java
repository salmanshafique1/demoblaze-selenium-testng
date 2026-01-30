package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage{

    @FindBy(css = "#tbodyid tr")
    private List<WebElement> cartRows;
    @FindBy(id = "totalp")
    private WebElement totalPrice;
    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    public int getNumberOfItems(){
        return cartRows.size();
    }
    public int getTotalPrice(){
        String text = getText(totalPrice);
        return text.isEmpty() ? 0 : Integer.parseInt(text.trim());
    }
    public CartPage deleteItemByName(String productName){
        for(WebElement row: cartRows){
            WebElement titleCell = row.findElement(By.xpath("./td[2]"));
            if (titleCell.getText().trim().equalsIgnoreCase(productName)){
                WebElement deleteLink = row.findElement(By.xpath(".//a[text()='Delete']"));
                click(deleteLink);
                break;
            }
        }
        return this;
    }

    public PlaceOrderPage clickPlaceOrder() {
        click(placeOrderButton);
        return new PlaceOrderPage();
    }


}
