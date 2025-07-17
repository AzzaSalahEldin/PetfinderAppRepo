package tests;

import utils.CSVDataProviders;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = CSVDataProviders.class)
    public void testLogin(String email, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        if (expectedResult.equalsIgnoreCase("success")) {
            loginPage.assertLoginSuccess();
        } else {
            loginPage.assertLoginFailed();
        }
    }
}
