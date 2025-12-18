package com.tests.pages;

import com.framework.core.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupModal extends BasePage{

    @FindBy(id = "sign-username")
    private WebElement signupUsernameInput;
    @FindBy(id = "sign-password")
    private WebElement signupPasswordInput;
    @FindBy(css = "#signInModal .btn-primary")
    private WebElement signupButton;

    public SignupModal enterUsername(String username){
        type(signupUsernameInput, username);
        return this;
    }
    public SignupModal enterPassword(String password){
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
