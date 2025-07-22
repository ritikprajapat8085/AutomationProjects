package bstackdemo__POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_01
{
  WebDriver driver ;
    public LoginPage_01(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="signin") WebElement signinBtn ;
    @FindBy(xpath = "//*[@id=\"username\"]") WebElement usernameDropdown ;
    @FindBy(xpath = "//div[text()='demouser']") WebElement selectDemouser;
    @FindBy(id="password") WebElement passwordDropdown ;
    @FindBy(xpath = "//div[text()='testingisfun99']") WebElement selectPassword;
    @FindBy(id="login-btn") WebElement  loginBtn ;


    // Click signin button
    public void clickSign() {
        signinBtn.click();
    }
    // Select username from dropdown
    public void selectUsername(String username) {
        usernameDropdown.click();
        driver.findElement(By.xpath("//div[text()='" + username + "']")).click();  //div[text() ='demouser']
    }
    // Select password from dropdown
    public void selectPassword(String password) {
         passwordDropdown.click();
         driver.findElement(By.xpath("//div[text()='" + password + "']")).click();  //div[text() ='testingisfun99']
    }
    // Click login button
    public void clickLogin() {
        loginBtn.click();
    }
    // Complete login method
    public void fulllogin(String un, String pass) {
        selectUsername(un);
        selectPassword(pass);
        clickLogin();
    }
}

