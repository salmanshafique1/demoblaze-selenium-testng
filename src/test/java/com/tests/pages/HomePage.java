
package com.tests.pages;

import com.framework.exceptions.FrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
        waits.forVisible(driver.findElement(By.id("totalp")));
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
        // We use a custom wait that ignores the Stale exception
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    List<WebElement> products = d.findElements(By.cssSelector(".hrefch"));
                    for (WebElement product : products) {
                        if (product.getText().trim().equalsIgnoreCase(productName)) {
                            product.click();
                            return true;
                        }
                    }
                    return false;
                });

        return new ProductPage();
    }

}
