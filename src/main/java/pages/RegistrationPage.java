package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegistrationPage {
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email") WebElement emailInput;
    @FindBy(id = "password") WebElement passwordInput;
    @FindBy(id = "confirmPassword") WebElement confirmPasswordInput;
    @FindBy(id = "submitButton") WebElement submitButton;

    @FindBy(css = ".success-msg") WebElement successMessage;
    @FindBy(css = ".error-msg") WebElement errorMessage;

    public void register(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
        submitButton.click();
    }

    public void assertRegistrationSuccess() {
        Assert.assertTrue(successMessage.isDisplayed(), "Success message not shown");
    }

    public void assertRegistrationFailed() {
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not shown");
    }
}
