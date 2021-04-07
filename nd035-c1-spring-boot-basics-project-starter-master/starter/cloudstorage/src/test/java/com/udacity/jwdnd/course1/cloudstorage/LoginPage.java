package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    @FindBy(css="#inputUsername")
    private WebElement usernameField;

    @FindBy(css="#inputPassword")
    private WebElement passwordField;

    @FindBy(id="loginButton")
    private WebElement submitButton;

    @FindBy(id="error-msg")
    public WebElement loginError;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }

    public boolean onLoginPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        try {
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("inputPassword")));
            return marker.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean onLoginError()  {
        try {
            return this.loginError.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
