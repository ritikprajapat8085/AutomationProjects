package OHRM_Scenario2_LoginPage_AdminPage_POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
public class LoginTestCase {
    WebDriver driver;
    LoginPage login ;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        login = new LoginPage(driver);  // Create LoginPage object and pass driver object to it .
    }



    @Test
    public void validLoginTest() throws InterruptedException {

        login.login("Admin", "admin123");
        Thread.sleep(3000);

      // Verify Title
      String expectedTitle = "OrangeHRM";
      String actualTitle = driver.getTitle();
      Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!")  ;

     // Verify URL
      String currentURL = driver.getCurrentUrl();
      Assert.assertTrue(currentURL.contains("/dashboard"), "URL is not correct!");
      System.out.println("User login successfully to dashboard...");
    }
    @AfterClass
    public void close() {
        driver.quit();
    }
}
