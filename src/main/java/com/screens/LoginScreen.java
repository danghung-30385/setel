package com.screens;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class LoginScreen {
    @AndroidFindBy(id="btn_welcome_continue_with_email")
    MobileElement btnWelcomeContinueWithEmail;

    @AndroidFindBy(id="email_exists_input")
    MobileElement txtEmail;

    @AndroidFindBy(id="btn_continue_with_email")
    MobileElement btnContinueWithEmail;

    @AndroidFindBy(id="log_in_password")
    MobileElement txtPassword;

    @AndroidFindBy(id="btn_log_in")
    MobileElement btnLogin;

    public LoginScreen(AppiumDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnWelcomeContinueWithEmailButton() {
        btnWelcomeContinueWithEmail.click();
    }

    public void enterEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void clickOnContinueWithEmailButton() {
        btnContinueWithEmail.click();
    }

    public void clickOnLoginButton() {
        btnLogin.click();
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }
}
