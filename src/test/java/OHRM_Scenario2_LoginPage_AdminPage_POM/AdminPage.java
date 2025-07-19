package OHRM_Scenario2_LoginPage_AdminPage_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AdminPage
{
    WebDriver driver;

    // Constructor
    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    //1.  Left side menu options
    @FindBy(xpath = "//ul[@class='oxd-main-menu']/li") List<WebElement> leftMenuOptions;

    //  Admin tab element
    @FindBy(xpath = "//span[text()='Admin']") WebElement adminTab;

    // 2.  searchByUserName() : Usernamesearch field
    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement usernameSearchField;

   //3.  searchByUserRole() :  userRole dropdown control
    @FindBy(xpath="(//div[@class='oxd-select-text-input'])[1]")   WebElement userRoleDropdown;
    @FindBy(xpath = "//div[@role='listbox']/div/span") List<WebElement> userRoleOptions;

   //4.  searchByUserStatus():  status dropdown  control
    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]") WebElement statusDropdown;
    @FindBy(xpath = "//div[@role='listbox']/div/span") List<WebElement> statusOptions;

    // Search button
    @FindBy(xpath = "//button[@type='submit']") WebElement searchButton;

    // Reset button
    @FindBy(xpath = "(//button[@type='button'])[1]") WebElement resetButton;

    //  recordbyusernmae
    @FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span") WebElement recordbyusername;

    public void clickAdminTab() {
        adminTab.click();
    }
    public int getLeftMenuOptionsCount() {
        return leftMenuOptions.size();
    }

    public void printLeftMenuOptionsText() {
        System.out.println("Left Menu Options:");
        for (WebElement option : leftMenuOptions) {
            System.out.println(option.getText());
        }
    }
    public void searchByUsername(String username) {
        usernameSearchField.sendKeys(username);
        searchButton.click();
    }

  public void selectUserRole(String role) {
        userRoleDropdown.click();
        for (WebElement option : userRoleOptions) {
            if (option.getText().equals(role)) {
                option.click();
                break;
            }
        }
    }

    public void selectUserStatus(String status) {
        statusDropdown.click();
        for (WebElement option : statusOptions) {
            if (option.getText().equals(status)) {
                option.click();
                break;
            }
        }
    }

    public String getResultText() {
        return recordbyusername.getText();
    }

    public void resetSearch() {
        resetButton.click();
    }

}
