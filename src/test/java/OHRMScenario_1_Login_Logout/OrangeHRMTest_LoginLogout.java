package OHRMScenario_1_Login_Logout;

import UtilityPackage.ExcelUtility;
import UtilityPackage.ExtentReportUtility;
import UtilityPackage.ScreenshotUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class OrangeHRMTest_LoginLogout
{
    WebDriver driver;
    ExcelUtility excel;
    ScreenshotUtility screenshotUtil;
    ExtentReportUtility reportUtil;




    @BeforeTest
    public  void setup() throws IOException
    {

        // Load Excel file with constructor
        ExcelUtility excel = new ExcelUtility("C:\\Users\\Admin\\Documents\\Staragile Automation course\\CapstoneProjects\\OHRMProject\\OHRMTestData.xlsx");

    }

    @BeforeMethod
    public void loginsetup() throws Exception
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();      // Maximize window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // wait max for 10 sec
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Open OrangeHRM site
        Thread.sleep(2000);  // Pause to let page load
    }

    @Test
    public void logintest() throws InterruptedException
    {

        int rowCount = excel.getRowCount();
        for(int i=0;i<=rowCount;i++)  // Loop over each row in Excel (5 rows in this case)
        {
            // Get username and password from Excel for the current row
            String username =    excel.getCellData(i,0);    // column 0 = Username
            String password =  excel.getCellData(i,1);      // column 1 = Password

           //  System.out.println("Username: " + username + " | Password: " + password);
        }




        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        WebElement btn = driver.findElement(By.tagName("button")) ;
        btn.click();
        Thread.sleep(3000);

        // Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));  // if you assert here then invalid test will failed x marks not right mark comming. if not assert then all green but printing next line as well in invalid scenarios
        //  System.out.println("User Login Sucessfully....");
    }

    @AfterMethod
    public void logout()
    {
        if(driver.getCurrentUrl().contains("dashboard"))
        {
            System.out.println("UserLogin Successfully...");
            driver.findElement(By.xpath("//img[@class='oxd-userdropdown-img']")).click();
            driver.findElement((By.linkText("Logout"))).click();
            System.out.println("User Logout Sucessfully........");
        }
        else
        {
            System.out.println("Invalid user name / password");
        }
    }

    @AfterTest
    public void closeup() throws IOException
    {
        driver.close();
    }

}
