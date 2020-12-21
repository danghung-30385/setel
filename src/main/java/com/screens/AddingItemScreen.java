package com.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class AddingItemScreen {
    @AndroidFindBy(id="message")
    MobileElement txtMessage;

    @AndroidFindBy(id="button1")
    MobileElement btnAddItem;


    public AddingItemScreen(AppiumDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void enterMessageValue(String keyword) {
        txtMessage.sendKeys(keyword);
    }

    public void clickOnAddItemButton() {
        btnAddItem.click();
    }
}
