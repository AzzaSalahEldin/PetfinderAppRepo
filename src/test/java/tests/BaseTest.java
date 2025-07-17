package tests;
import config.ConfigManager;
import config.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.RegistrationPage;

public class BaseTest {
    protected WebDriver driver;
    protected RegistrationPage regPage;
    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigManager.get("url"));
        regPage = new RegistrationPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
