package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public final static String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By constructorButton = By.xpath(".//a[@href='/']/p[text()='Конструктор']");
    public final By exitButton = By.xpath(".//button[text()='Выход']");

    @Step("Клик по кнопке 'Конструктор'.")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        waitLoadingAnimation();
    }

    @Step("Клик по кнопке 'Выйти'.")
    public void clickOnExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(exitButton));
        driver.findElement(exitButton).click();
    }

    @Step("Ожидание закгрузки анимации")
    public void waitLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("Modal_modal__loading__3534A")));
    }
}
