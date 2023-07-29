import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;

import static config.AppConfig.APP_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import config.User;

public class RegistrationPageTest {

    private WebDriver driver;
    public RegistrationPage objRegPage;
    public LoginPage objLoginPage;

    public MainPage objMainPage;

    private User user;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objRegPage = new RegistrationPage(driver);
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        driver.get(APP_URL);
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    public void checkItIsAbleToRegisterNewUser() throws InterruptedException {
        objMainPage.clickOnLoginButton();
        objLoginPage.clickOnRegister();
        user = User.getRandomUser();
        objRegPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        objLoginPage.waitLoadingLoginPage();
        assertEquals("Страница вход не открылась", objLoginPage.getLoginPageText(), "Вход");
        assertEquals("Url отличается от ожидаемого", LoginPage.LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Регистрация пользователя с невалидным паролем")
    public void registrationTestFail(){
        objMainPage.clickOnLoginButton();
        objLoginPage.clickOnRegister();
        user = User.getRandomUser();
        user.setPassword("123qw");
        objRegPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(driver.findElement(objRegPage.errorPasswordText).isDisplayed());
        assertEquals("Ошибка не верная", objRegPage.getErrorPasswordText(), "Некорректный пароль");
    }

    @After
    public void shutDown(){
        driver.quit();
    }
}
