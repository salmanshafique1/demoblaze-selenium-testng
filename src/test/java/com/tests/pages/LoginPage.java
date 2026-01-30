package com.tests.pages;

import com.framework.core.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "loginusername")
    private WebElement loginUsernameInput;
    @FindBy(id = "loginpassword")
    private WebElement loginPasswordInput;
    @FindBy(css = "#loginModal .btn-primary")
    private WebElement loginButton;
    @FindBy(id = "nameofuser")
    private WebElement loggedInUserLabel;

    public LoginPage enterUsername(String username){
        type(loginUsernameInput, username);
        return this;
    }
    public LoginPage enterPassword(String password){
        type(loginPasswordInput, password);
        return this;
    }
    public HomePage clickLogin(){
        click(loginButton);
        return new HomePage();
    }
    public HomePage loginValidUser(String username, String password){
        return enterUsername(username).enterPassword(password).clickLogin();
    }

    public String getInvalidLoginAlertText(){
        Alert alert = DriverManager.getDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

}
