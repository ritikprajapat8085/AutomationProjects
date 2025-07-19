package OHRM_Scenario2_LoginPage_AdminPage_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {

    WebDriver driver;
    @FindBy(name = "username") WebElement usernameField;
    @FindBy(name = "password") WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']") WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
