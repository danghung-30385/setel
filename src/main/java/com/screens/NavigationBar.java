package com.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class NavigationBar {
    @AndroidFindBy(id="menu_content_search")
    MobileElement iconMagnifyingGlass;

    @AndroidFindBy(id="quick_add_item")
    MobileElement btnQuickAddItem;

    public NavigationBar(AppiumDriver<?> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnMagnifyingGlassIcon() {
        iconMagnifyingGlass.click();
    }

    public void clickOnQuickAddItemButton() {
        btnQuickAddItem.click();
    }
}
