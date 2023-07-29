package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath(".//a[@href='/account']");
    private final By bunsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Булки']");
    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    private final By activeIngredientTab = By.cssSelector(".tab_tab_type_current__2BEPc");
    public final By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        waitLoadingAnimation();
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
        waitLoadingAnimation();
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickOnBunsTab() throws InterruptedException {
        driver.findElement(bunsButton).click();

    }
    @Step("Клик по кнопке 'Соуса'")
    public void clickOnSaucesTab() throws InterruptedException {
        driver.findElement(saucesButton).click();
    }
    @Step("Клик по кнопке 'Начинки'")
    public void clickOnFillingTab() throws InterruptedException {
        driver.findElement(fillingsButton).click();
    }
    @Step("Ожидание закгрузки анимации")
    public void waitLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.className("Modal_modal__loading__3534A")));
    }
    @Step("Ожидание загрузки главной страницы")
    public void waitLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textBurgerMainPage));
    }
    @Step("Проверка, что отобразилась таба Булки на главной страницы")
    public Boolean IsMainPageLoad(){
        waitLoadMainPage();
        return driver.findElement(bunsButton).isDisplayed();
    }

    @Step("Получение названия активной табы")
    public String getTextOfActiveTab(){
        return driver.findElement(activeIngredientTab).getText();
    }
}
