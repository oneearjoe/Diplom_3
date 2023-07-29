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
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;

import static config.AppConfig.APP_URL;

import static org.junit.Assert.assertTrue;
public class LoginPageTest {
    private WebDriver driver;
    private User user;
    private UserAPI userMethods = new UserAPI();
    public RegistrationPage objRegPage;
    public LoginPage objLoginPage;
    public MainPage objMainPage;
    public ForgotPasswordPage objForgotPasPage;
    private ValidatableResponse response;
    private String accessToken;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        driver.get(APP_URL);
        user = User.getRandomUser();
        response = userMethods.registerUser(user);
        accessToken = response.extract().path("accessToken");
    }

    @Test
    @DisplayName("Логин с главной страницы")
    public void loginFromMainPageTest(){
        objMainPage.clickOnLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        assertTrue(objMainPage.IsMainPageLoad());
    }

    @Test
    @DisplayName("Логин со страницы личного кабинета")
    public void loginFromProfilePageTest(){
        objMainPage.clickOnAccountButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.waitLoadMainPage();
        assertTrue(objMainPage.IsMainPageLoad());
    }

    @Test
    @DisplayName("Логин со страницы регистрации")
    public void loginFromRegistrationPageTest(){
        objMainPage.clickOnLoginButton();
        objLoginPage.clickOnRegister();
        objRegPage = new RegistrationPage(driver);
        objRegPage.clickLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        assertTrue(objMainPage.IsMainPageLoad());
    }

    @Test
    @DisplayName("Логин со страницы восстановления пароля")
    public void loginFromForgotPasswordPageTest(){
        objMainPage.clickOnAccountButton();
        objLoginPage.clickOnForgotPasswordLink();
        objForgotPasPage = new ForgotPasswordPage(driver);
        objForgotPasPage.clickOnLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        assertTrue(objMainPage.IsMainPageLoad());
    }

    @After
    public void shutDown(){
        userMethods.deleteUser(accessToken);
        driver.quit();
    }
}
