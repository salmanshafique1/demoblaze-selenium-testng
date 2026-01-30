package com.tests.pages;

import com.framework.core.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends BasePage{

    @FindBy(id = "sign-username")
    private WebElement signupUsernameInput;
    @FindBy(id = "sign-password")
    private WebElement signupPasswordInput;
    @FindBy(css = "#signInModal .btn-primary")
    private WebElement signupButton;

    public SignupPage enterUsername(String username){
        type(signupUsernameInput, username);
        return this;
    }
    public SignupPage enterPassword(String password){
        type(signupPasswordInput, password);
        return this;
    }

    public void clickSignup() {
        click(signupButton);
    }

    public String getSignupAlertTextAndAccept(){
        Alert alert = DriverManager.getDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
