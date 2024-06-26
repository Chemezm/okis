package com.example.tests;

import com.example.pageobjects.WikipediaHomePage;
import com.example.pageobjects.WikipediaLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class WikipediaTests {

    private WebDriver driver;
    private WikipediaHomePage homePage;
    private WikipediaLoginPage loginPage;

    @BeforeTest
    public void setUp() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String chromedriverPath = properties.getProperty("chromedriver.path");
        System.setProperty("webdriver.chrome.driver", chromedriverPath);

        driver = new ChromeDriver();
        homePage = new WikipediaHomePage(driver);
        loginPage = new WikipediaLoginPage(driver);
    }

    @Test(priority = 1)
    public void testHomePageTitle() {
        homePage.goToHomePage();
        String title = homePage.getPageTitle();
        System.out.println("Page title: " + title);
        assert title.equals("Wikipedia, the free encyclopedia");
    }

    @Test(priority = 2)
    public void testMainHeading() {
        homePage.goToHomePage();
        String heading = homePage.getMainHeading();
        System.out.println("Main heading: " + heading);
        assert heading.contains("Welcome to Wikipedia");
    }

    @Test(priority = 3)
    public void testSearchFunctionality() {
        homePage.goToHomePage();
        homePage.searchFor("Selenium (software)");
        String searchHeading = homePage.getSearchResultsHeading();
        System.out.println("Search results heading: " + searchHeading);
        assert searchHeading.contains("Selenium (software)");
    }

    @Test(priority = 4)
    public void testMainMenuLinks() {
        homePage.goToHomePage();
        homePage.clickContentsLink();
        List<String> menuLinks = homePage.getMainMenuLinks();
        System.out.println("Main menu links: " + menuLinks);
        assert menuLinks.contains("Main page");
    }

    @Test(priority = 5)
    public void testNavigateToLoginPage() {
        homePage.goToHomePage();
        homePage.navigateToLoginPage();
        String loginPageTitle = loginPage.getPageTitle();
        System.out.println("Login page title: " + loginPageTitle);
        assert loginPageTitle.contains("Log in");
    }

    @Test(priority = 6)
    public void testLoginError() {
        homePage.goToHomePage();
        homePage.navigateToLoginPage();
        loginPage.enterUsername("invalidUsername");
        loginPage.enterPassword("invalidPassword");
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getErrorMessage();
        System.out.println("Login error message: " + errorMessage);
        assert errorMessage.contains("Incorrect username or password entered. Please try again.");
    }

    @Test(priority = 7)
    public void testDonateLinkPresence() {
        homePage.goToHomePage();
        homePage.clickContentsLink();
        String donateLinkText = homePage.getDonateLinkText();
        System.out.println("Donate link text: " + donateLinkText);
        assert donateLinkText.equals("Donate");
    }

    @Test(priority = 8)
    public void testRandomArticleNavigation() {
        homePage.goToHomePage();
        homePage.clickContentsLink();
        homePage.navigateToRandomArticle();
        String randomArticleTitle = homePage.getPageTitle();
        System.out.println("Random article title: " + randomArticleTitle);
        assert !randomArticleTitle.equals("Wikipedia, the free encyclopedia");
    }

    @Test(priority = 9)
    public void testCreateAccountButtonPresence() {
        homePage.goToHomePage();
        homePage.navigateToLoginPage();
        boolean isCreateAccountButtonPresent = loginPage.isCreateAccountButtonPresent();
        System.out.println("Create Account Button Present: " + isCreateAccountButtonPresent);
        assert isCreateAccountButtonPresent;
    }

    @Test(priority = 10)
    public void testForgotPasswordLinkText() {
        homePage.goToHomePage();
        homePage.navigateToLoginPage();
        String forgotPasswordLinkText = loginPage.getForgotPasswordLinkText();
        System.out.println("Forgot Password Link Text: " + forgotPasswordLinkText);
        assert forgotPasswordLinkText.equals("Forgot your password?");
    }

    @Test(priority = 11)
    public void testRememberMeCheckboxPresence() {
        homePage.goToHomePage();
        homePage.navigateToLoginPage();
        boolean isRememberMeCheckboxPresent = loginPage.isRememberMeCheckboxPresent();
        System.out.println("Remember Me Checkbox Present: " + isRememberMeCheckboxPresent);
        assert isRememberMeCheckboxPresent;
    }

    @Test(priority = 12)
    public void testSubHeadingText() {
        homePage.goToHomePage();
        homePage.navigateToLoginPage();
        String subHeadingText = loginPage.getSubHeadingText();
        System.out.println("Sub Heading Text: " + subHeadingText);
        assert subHeadingText.contains("Log in");
    }

    @Test(priority = 13)
    public void testPasswordFormatErrorMessage() {
        homePage.goToHomePage();
        homePage.navigateToLoginPage();
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("short"); // Assuming "short" is a password that triggers a format error.
        loginPage.clickLoginButton();
        String passwordFormatErrorMessage = loginPage.getPasswordFormatErrorMessage();
        System.out.println("Password Format Error Message: " + passwordFormatErrorMessage);
        assert passwordFormatErrorMessage.contains("There are problems with some of your input.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}