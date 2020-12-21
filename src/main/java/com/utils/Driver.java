package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    public static String readProperty(String property) {
        Properties prop;
        String value = null;
        try {
            prop = new Properties();
            prop.load(new FileInputStream(new File("config.properties")));

            value = prop.getProperty(property);

            if (value == null || value.isEmpty()) {
                throw new Exception("Value not set or empty");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }


    public static AppiumDriver<?> returnDriver(String platform) throws Exception {
        AppiumDriver<?> driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (Boolean.parseBoolean(Driver.readProperty("run.hybrid"))) {
            capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
        }

        String completeURL = "http://" + Driver.readProperty("run.ip") + ":" + Driver.readProperty("run.port") + "/wd/hub";

        switch (platform.toLowerCase()) {

            case "ios":
                capabilities.setCapability(MobileCapabilityType.APP, new File(Driver.readProperty("app.ios.path")).getAbsolutePath());
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Driver.readProperty("device.ios.name"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Driver.readProperty("platform.ios.version"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);

                if ( Boolean.parseBoolean(Driver.readProperty("platform.ios.xcode8"))) {
                    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                }

                driver = new IOSDriver<RemoteWebElement>(new URL(completeURL), capabilities);
                break;

            case "android":

                capabilities.setCapability(MobileCapabilityType.APP, new File(Driver.readProperty("app.android.path")).getAbsolutePath());
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Driver.readProperty("device.android.name"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);

                driver = new AndroidDriver<RemoteWebElement>(new URL(completeURL), capabilities);
                break;

            default:
                throw new Exception("Platform not supported");
        }

        return driver;
    }
}
