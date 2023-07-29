package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    public final By loginButton = By.className("Auth_link__1fOlj");

    @Step("Клик по кнопке 'Войти'")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }
}
