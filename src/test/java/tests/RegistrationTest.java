package tests;

import utils.CSVDataProviders;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "registrationData", dataProviderClass = CSVDataProviders.class)
    public void testSuccessfulRegistration(String email, String first, String last, String zip, String dogsNo, String catsNo, String password, String confirm, String expectedResult) {
        regPage.registerPersonalData(ThreadLocalRandom.current().nextInt(10000, 100000)+email,first,last,zip,dogsNo,catsNo);
        regPage.enterPassword(password);
        regPage.enterConfirmPassword(confirm);
        regPage.acceptTermsAndConditions();
        regPage.submitRegistration();
        if (expectedResult.equalsIgnoreCase("success")) {
            regPage.assertRegistrationSuccess();
        }
        if (expectedResult.equalsIgnoreCase("InvalidPassword")) {
            regPage.assertRegistrationPwdValidation();
        }
        if (expectedResult.equalsIgnoreCase("NotMatchedConfrimPAssword")) {
            regPage.assertRegistrationConfirmPasswordValidation();
        }
    }
    @Test(enabled = true)
    public void testPasswordValidation(){
        regPage.registerPersonalData("test"+ ThreadLocalRandom.current().nextInt(10000, 100000)+"@gmail.com","first","last","25100","3","2");
        regPage.enterPassword("password");
        regPage.enterConfirmPassword("confirm");
        regPage.acceptTermsAndConditions();
        regPage.submitRegistration();
        regPage.clearPassword();
        regPage.assertRegistrationPwdRequired();

//        regPage.clearConfirmPassword();
//        regPage.assertRegistrationConfirmPasswordRequired();
    }

    @Test(enabled = true)
    public void testEmailValidation() {
        regPage.openRegistrationForm();
        regPage.enterEmailAddress("azzasalaheldin11@gmail.com");
        regPage.proceedFromEmail();
        regPage.assertRegistrationEmailExist();
        regPage.clickDismiss();
        regPage.enterEmailAddress("dsfvgdf@fdf.");
        regPage.proceedFromEmail();
        regPage.assertRegistrationEmailValidation();
    }

    @Test(enabled = true)
    public void testPersonalValidation() {
        regPage.openRegistrationForm();
        regPage.enterEmailAddress("test"+ ThreadLocalRandom.current().nextInt(10000, 100000)+"@gmail.com");
        regPage.proceedFromEmail();
        regPage.enterFirstName("test");
        regPage.enterLastName("last");
        regPage.enterZipCode("2");
        regPage.assertZipCodeValidation();
        regPage.enterZipCode("25100");
        regPage.clearFirstName();
        regPage.assertFirstNameValidation();
        regPage.clearLastName();
        regPage.assertLastNameValidation();
    }
}
