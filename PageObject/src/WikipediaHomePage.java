package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class WikipediaHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WikipediaHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Перейти на главную страницу Wikipedia.
    public void goToHomePage() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    //Зайти в меню бургер
    public void clickContentsLink() {
        WebElement contentsLink = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        contentsLink.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // Получить основной заголовок на главной странице.
    public String getMainHeading() {
        WebElement mainHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mp-welcome")));
        return mainHeading.getText();
    }

    // Выполнить поиск по заданному запросу.
    public void searchFor(String query) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        searchBox.sendKeys(query);
        searchBox.submit();
    }

    //Получить заголовок страницы с результатами поиска.
    public String getSearchResultsHeading() {
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
        return heading.getText();
    }


    //Перейти на страницу входа.
    public void navigateToLoginPage() {
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pt-login-2")));
        loginLink.click();
    }

    //Получить список названий ссылок главного меню.
    public List<String> getMainMenuLinks() {
        List<WebElement> menuLinks = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#p-navigation li a")));
        return menuLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // находит и возвращает текст ссылки "Donate".
    public String getDonateLinkText() {
        WebElement donateLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n-sitesupport")));
        return donateLink.getText();
    }

    //находит и кликает на ссылку "Random article".
    public void navigateToRandomArticle() {
        WebElement randomArticleLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n-randompage")));
        randomArticleLink.click();
    }
}
