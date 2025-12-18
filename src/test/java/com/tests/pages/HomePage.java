
package com.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    @FindBy(id = "login2")
    private WebElement loginLink;
    @FindBy(id = "signin2")
    private WebElement signupLink;
    @FindBy(id = "cartur")
    private WebElement cartLink;
    @FindBy(css = ".list-group-item")
    private List<WebElement> categoryLinks;
    @FindBy(css = ".hrefch")
    private List<WebElement> productLinks;
    public LoginModal openLoginModal(){
        click(loginLink);
        return new LoginModal();
    }
    public SignupModal  openSignupModal(){
        click(signupLink);
        return new SignupModal();
    }
    public CartPage goToCart(){
        click(cartLink);
        return new CartPage();
    }
    public HomePage selectCategory(String categoryName){
        for(WebElement category: categoryLinks){
            if(category.getText().trim().equalsIgnoreCase(categoryName)){
                click(category);
                break;
            }
        }
        return this;
    }

    public ProductPage openProductByName(String productName) {


        for (int i = 0; i < 3; i++) {
            try {
                List<WebElement> products =
                        driver.findElements(By.cssSelector(".hrefch"));

                for (WebElement product : products) {
                    if (product.getText().trim().equalsIgnoreCase(productName)) {
                        waits.forClickable(product).click();
                        return new ProductPage();
                    }
                }

            } catch (Exception e) {

                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            }
        }

        throw new RuntimeException("Product not found: " + productName);
    }

}
