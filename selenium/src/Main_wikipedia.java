package com.example.tests;

import com.example.pageobjects.TestngPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main_wikipedia {

    private WebDriver driver;
    private TestngPageObjects testngPageObjects;

    @BeforeTest
    public void setUp() {
        Properties properties = new Properties();
        try {
            // Загружаем файл config.properties
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Получаем значение пути к chromedriver из config.properties
        String chromedriverPath = properties.getProperty("chromedriver.path");

        // Устанавливаем путь к chromedriver
        System.setProperty("webdriver.chrome.driver", chromedriverPath);

        driver = new ChromeDriver();

        testngPageObjects = new TestngPageObjects(driver);
    }

    @Test(priority = 1)
    public void testNavigateToCurrentEventsByCssSelector() {
        // Переход на главную страницу сайта Wikipedia
        testngPageObjects.goToHomePage();

        // Клик по ссылке "Contents"
        testngPageObjects.clickContentsLink();

        // Клик по ссылке "Current events" по частичному тексту
        testngPageObjects.clickCurrentEventsLinkBySelector();
    }

    @Test(priority = 2)
    public void testNavigateToCurrentEventsByXpath() {
        // Переход на главную страницу сайта Wikipedia
        testngPageObjects.goToHomePage();

        // Клик по ссылке "Contents"
        testngPageObjects.clickContentsLink();

        // Клик по ссылке "Current events" по частичному тексту
        testngPageObjects.clickContentsLinkByXpath();
    }

    @Test(priority = 3)
    public void testGetMainMenuLinks() {
        // Переход на главную страницу сайта Wikipedia
        testngPageObjects.goToHomePage();

        // Получение списка названий ссылок содержания Main menu и вывод их в консоль
        List<String> mainMenuLinks = testngPageObjects.getMainMenuLinks();
        System.out.println("Main menu links:");
        for (String link : mainMenuLinks) {
            System.out.println("- " + link);
        }
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
