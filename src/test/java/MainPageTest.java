import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.*;

import static config.AppConfig.APP_URL;
import static org.junit.Assert.assertEquals;

public class MainPageTest {

    private WebDriver driver;
    public LoginPage objLoginPage;
    public MainPage objMainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        driver.get(APP_URL);
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    public void checkItIsAbleToSelectSaucesTab() throws InterruptedException {
        objMainPage.clickOnSaucesTab();
        assertEquals("Соусы",objMainPage.getTextOfActiveTab());
    }

    @Test
    @DisplayName("Переход в раздел 'Булки'")
    public void checkItIsAbleToSelectBunTab() throws InterruptedException {
        objMainPage.clickOnSaucesTab();
        objMainPage.clickOnBunsTab();
        assertEquals("Булки",objMainPage.getTextOfActiveTab());
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    public void checkItIsAbleToSelectFillingTab() throws InterruptedException {
        objMainPage.clickOnFillingTab();
        assertEquals("Начинки",objMainPage.getTextOfActiveTab());
    }

    @After
    public void shutDown(){
        driver.quit();
    }
}
