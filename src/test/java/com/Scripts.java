package com;

import com.screens.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.appium.java_client.AppiumDriver;

import com.utils.Driver;
import com.utils.DateTime;

import com.apis.APIProject;
import com.apis.APITask;

public class Scripts {
    private AppiumDriver<?> driver;
    private APIProject apiProject = new APIProject();
    private APITask apiTask = new APITask();
    private String baseURI;
    private String bearerToken;
    private String globalProjectName;
    private String globalTaskName;

    @Before
    public void setUp() throws Exception {
        driver = Driver.returnDriver(Driver.readProperty("run.platform"));
        baseURI = String.valueOf(Driver.returnDriver(Driver.readProperty("base.uri")));
        bearerToken = String.valueOf(Driver.returnDriver(Driver.readProperty("bearer.token")));
    }

    @Test
    public void VerifyTheProjectHasBeenCreatedSuccessfullyViaAPIOnMobile() {
        LoginScreen loginScreen = new LoginScreen(driver);
        NavigationBar navigationBar = new NavigationBar(driver);
        SearchingScreen searchingScreen = new SearchingScreen(driver);


        //create project by API
        String id = new DateTime().createCurrentTimeStamp();
        String tcProjectName = "Setel_" + id;
        apiProject.createProjectSuccessfully(baseURI, tcProjectName, bearerToken);
        globalProjectName = tcProjectName;

        //log in and verify on Mobile
        loginScreen.clickOnWelcomeContinueWithEmailButton();
        loginScreen.enterEmail("hungnd6@vng.com.vn");
        loginScreen.clickOnContinueWithEmailButton();
        loginScreen.enterPassword("Aa123456");
        loginScreen.clickOnLoginButton();

        navigationBar.clickOnMagnifyingGlassIcon();

        searchingScreen.enterSearchingValue(tcProjectName);
        searchingScreen.clickOnProjectTag();
        String actualSearchingResult = searchingScreen.getResultProjectFirstItemValue();
        Assert.assertEquals(tcProjectName, actualSearchingResult);
    }

    @Test
    public void VerifyTheTaskHasBeenCreatedSuccessfullyOnMobileByAPI() {
        //Check login & login

        NavigationBar navigationBar = new NavigationBar(driver);
        SearchingScreen searchingScreen = new SearchingScreen(driver);
        AddingItemScreen addingItemScreen = new AddingItemScreen(driver);
        ProjectScreen projectScreen = new ProjectScreen(driver);

        if(globalProjectName==null){
            //create project by API
            String id = new DateTime().createCurrentTimeStamp();
            String tcProjectName = "Setel_" + id;
            apiProject.createProjectSuccessfully(baseURI, tcProjectName, bearerToken);
            globalProjectName = tcProjectName;
        }

        navigationBar.clickOnMagnifyingGlassIcon();
        searchingScreen.enterSearchingValue(globalProjectName);
        searchingScreen.clickOnProjectTag();
        searchingScreen.clickOnResultFirstItem();
        navigationBar.clickOnQuickAddItemButton();
        String id = new DateTime().createCurrentTimeStamp();
        String tcTaskName = "To do " + id;
        addingItemScreen.enterMessageValue(tcTaskName);
        addingItemScreen.clickOnAddItemButton();
        projectScreen.clickOnTaskList();
        globalTaskName= tcTaskName;
        apiTask.verifyTaskHasBeenCreatedSuccessfully(baseURI, bearerToken, tcTaskName);

    }

    @Test
    public void VerifyTheTaskHasBeenReopenedSuccessfullyViaAPIOnMobile() {
        //Check login & login
        NavigationBar navigationBar = new NavigationBar(driver);
        SearchingScreen searchingScreen = new SearchingScreen(driver);
        AddingItemScreen addingItemScreen = new AddingItemScreen(driver);
        ProjectScreen projectScreen = new ProjectScreen(driver);

        if(globalProjectName==null){
            //create project by API
            String id = new DateTime().createCurrentTimeStamp();
            String tcProjectName = "Setel_" + id;
            apiProject.createProjectSuccessfully(baseURI, tcProjectName, bearerToken);
            globalProjectName = tcProjectName;
        }

        if (globalTaskName==null) {
            navigationBar.clickOnMagnifyingGlassIcon();
            searchingScreen.enterSearchingValue(globalProjectName);
            searchingScreen.clickOnProjectTag();
            searchingScreen.clickOnResultFirstItem();
            navigationBar.clickOnQuickAddItemButton();
            String id = new DateTime().createCurrentTimeStamp();
            String tcTaskName = "To do " + id;
            addingItemScreen.enterMessageValue(tcTaskName);
            addingItemScreen.clickOnAddItemButton();
            projectScreen.clickOnTaskList();
            globalTaskName= tcTaskName;
        }

        navigationBar.clickOnMagnifyingGlassIcon();
        searchingScreen.enterSearchingValue(globalTaskName);
        searchingScreen.clickOnTaskTag();
        searchingScreen.clickOnFirstSearchingTaskCheckMark();

        navigationBar.clickOnMagnifyingGlassIcon();
        searchingScreen.enterSearchingValue(globalTaskName);
        searchingScreen.clickOnTaskTag();

        String actualSearchingResult = searchingScreen.getResultTaskFirstItemValue();
        Assert.assertEquals(globalTaskName, actualSearchingResult);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
