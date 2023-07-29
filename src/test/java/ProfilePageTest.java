import config.User;
import config.UserAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.*;


import static config.AppConfig.APP_URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfilePageTest {

    private WebDriver driver;
    private User user;
    private UserAPI userMethods = new UserAPI();
    public LoginPage objLoginPage;
    public MainPage objMainPage;
    public ProfilePage objprofilePage;
    private ValidatableResponse response;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objprofilePage=new ProfilePage(driver);
        driver.get(APP_URL);
        user = User.getRandomUser();
        response = userMethods.registerUser(user);
        accessToken = response.extract().path("accessToken");
    }

    @Test
    @DisplayName("Переход с главной страницы на страницу личного кабинета")
    public void checkItIsAbleToOpenProfilePage(){
        objMainPage.clickOnLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.clickOnAccountButton();
        assertEquals("Url отличается от ожидаемого", ProfilePage.PROFILE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void checkItIsAbleToOpenConstructorFromProfilePageByClickOnConstructorButton(){
        objMainPage.clickOnLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.clickOnAccountButton();
        objprofilePage.clickOnConstructorButton();
        objMainPage.waitLoadMainPage();
        assertTrue( driver.findElement(objMainPage.textBurgerMainPage).isDisplayed());
    }
    @Test
    @DisplayName("Переход из личного кабинета по клику на лого Бургрера")
    public void checkItIsAbleToOpenConstructorFromProfilePageByClickOnLogo(){
        objMainPage.clickOnLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.clickOnAccountButton();
        objLoginPage.clickOnLogo();
        objMainPage.waitLoadMainPage();
        assertTrue( driver.findElement(objMainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitAccountTest(){
        objMainPage.clickOnLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.clickOnAccountButton();
        objprofilePage.clickOnExitButton();
        objMainPage.waitLoadingAnimation();
        assertTrue("Не удалось выйти из аккаунта", driver.findElement(objLoginPage.loginText).isDisplayed());
    }
    @After
    public void shutDown(){
        userMethods.deleteUser(accessToken);
        driver.quit();
    }

}
