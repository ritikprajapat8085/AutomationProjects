package OHRM_Scenario2_LoginPage_AdminPage_POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
public class AdminTestCase {
    WebDriver driver;
    LoginPage login ;
    AdminPage admin;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        login = new LoginPage(driver);
        login.login("Admin", "admin123");    // Perform login first
        admin = new AdminPage(driver);
    }

    @Test(priority = 1)
    public void menuOptionCountTest() {
        admin.clickAdminTab();
        int actualcount = admin.getLeftMenuOptionsCount();
        Assert.assertEquals(actualcount,12,"Options  not counted correctly....");
        System.out.println("assertion pass...") ;
        System.out.println("Menu Option Count: " + actualcount);
        admin.printLeftMenuOptionsText();
    }

    @Test(priority = 2)
    public void searchByUsernameTest()  {
        admin.clickAdminTab();

        admin.searchByUsername("Admin");

        System.out.println("Search Result: " + admin.getResultText());
        admin.resetSearch();
    }

    @Test(priority = 3)
    public void searchByUserRoleTest() {
        admin.clickAdminTab();
        admin.selectUserRole("Admin");
        admin.searchButton.click();
        System.out.println("Search Result: " + admin.getResultText());
        admin.resetSearch();
    }

    @Test(priority = 4)
    public void searchByStatusTest() {
        admin.clickAdminTab();
        admin.selectUserStatus("Enabled");
        admin.searchButton.click();
        System.out.println("Search Result: " + admin.getResultText());
        admin.resetSearch();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
