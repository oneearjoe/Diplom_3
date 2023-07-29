package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    public final By errorPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    public final By loginButton = By.className("Auth_link__1fOlj");

    @Step("Ввод name в поле Name")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввод email в поле Email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод password в поле Password")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке Регистрация")
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
        waitLoadingAnimation();
    }

    @Step("Клие по кнопке логин")
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    @Step("Регистрация нового пользователя")
    public void registerNewUser(String name, String email,String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Получение текста ошибки когда введён не верный пароль")
    public String getErrorPasswordText() {
        return driver.findElement(errorPasswordText).getText();
    }

    @Step("Ожидание загрузки анимации")
    public void waitLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("Modal_modal__loading__3534A")));
    }
}
