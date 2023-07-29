package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    private WebDriver driver;
    public final static String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public final By loginText = By.xpath(".//main/div/h2[text()='Вход']");
    private final By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    private final By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");
    public final By enterButton = By.xpath(".//div/a[@href='/']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");

    @Step("Ввод email в поле email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля в поле Пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке логин")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        waitLoadingAnimation();
    }
    @Step("Логин юзера")
    public void loginUser(String email,String password){
        setEmail(email);
        setPassword(password);
        clickOnLoginButton();
    }
    @Step("Клик на кнопку 'Stellar Burgers'")
    public void clickOnLogo() {
        driver.findElement(enterButton).click();
        waitLoadingAnimation();
    }
    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickOnRegister() {
        driver.findElement(registerLink).click();
        waitLoadingAnimation();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickOnForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        waitLoadingAnimation();
    }
    @Step("Ожидание открытия страницы Входа")
    public void waitLoadingLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginText));
    }
    @Step("Получение текста страницы Входа")
    public String getLoginPageText() {
        return driver.findElement(loginText).getText();
    }

    @Step("Ожидание закгрузки анимации")
    public void waitLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.className("Modal_modal__loading__3534A")));
    }
}
