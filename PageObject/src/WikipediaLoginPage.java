package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaLoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WikipediaLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Получить заголовок страницы.
    public String getPageTitle() {
        return driver.getTitle();
    }

    public void enterUsername(String username) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpName1")));
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpPassword1")));
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpLoginAttempt")));
        loginButton.click();
    }

    public String getErrorMessage() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#userloginForm > form > div.mw-message-box.cdx-message.cdx-message--block.mw-message-box-error.cdx-message--error")));
        return errorMessage.getText();
    }

    // Проверка наличия кнопки "Create account"
    public boolean isCreateAccountButtonPresent() {
        WebElement createAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pt-createaccount-2")));
        return createAccountButton.isDisplayed();
    }

    // Проверка текста ссылки "Forgot your password?"
    public String getForgotPasswordLinkText() {
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='Special:PasswordReset']")));
        return forgotPasswordLink.getText();
    }

    // Проверка наличия чекбокса "Remember me"
    public boolean isRememberMeCheckboxPresent() {
        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-checkbox")));
        return rememberMeCheckbox.isDisplayed();
    }

    // Получение текста подзаголовка страницы
    public String getSubHeadingText() {
        WebElement subHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
        return subHeading.getText();
    }

    // Получение сообщения об ошибке при неверном формате пароля
    public String getPasswordFormatErrorMessage() {
        WebElement passwordFormatErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cdx-message__contentME")));
        return passwordFormatErrorMessage.getText();
    }
}
