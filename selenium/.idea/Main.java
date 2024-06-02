package com.example.tests;

import com.example.pageobjects.TestngPageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {

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
        String EdgedriverPath = properties.getProperty("EdgeDriver.path");

        // Устанавливаем путь к edgedriver
        System.setProperty("webdriver.edge.driver", EdgedriverPath);


        driver = new EdgeDriver();


        testngPageObjects = new TestngPageObjects(driver);
    }

    @Test(priority = 1)
    public void Testng() {
        // Переход на главную страницу сайта Testng и получение заголовка страницы
        testngPageObjects.goToTestng();

        // Получение списка названий ссылок содержания и вывод их в консоль
        List<String> contentLinks = testngPageObjects.getContentsLinksTestng();
        System.out.println("List of contents links:");
        for (String link : contentLinks) {
            System.out.println("- " + link);
        }

        testngPageObjects.clickCssSelectorTestng();
        testngPageObjects.clickXpathTestng();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
