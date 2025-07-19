package OHRMScenario_1_Login_Logout;

import UtilityPackage.ExcelUtility;
import UtilityPackage.ExtentReportUtility;
import UtilityPackage.ScreenshotUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.time.Duration;

public class OrangeHRMTest_LoginLogout {
    WebDriver driver;
    ExcelUtility excel;
    ScreenshotUtility screenshotUtil;
    ExtentReportUtility reportUtil;

    // These variables are for each test iteration
    String username ;
    String password ;
    int currentRow = 1;  // for reading excel Row index start from 1 and  coloum :0

    @BeforeTest
    public  void setup() throws IOException {
        excel = new ExcelUtility("C:\\Users\\Admin\\Documents\\Staragile Automation course\\CapstoneProjects\\OHRMProject\\OHRMTestData.xlsx");   // Load Excel file with constructor
        reportUtil = new ExtentReportUtility();  // Setup Extent Report only once

    }

    @BeforeMethod
    public void loginsetup() throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();      // Maximize window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // wait max for 10 sec
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Open OrangeHRM site
        Thread.sleep(2000);  // Pause to let page load
        screenshotUtil = new  ScreenshotUtility(driver);  // here bcz we are not setting for one time we are calling every time
    }







    @Test(invocationCount = 5) // Run this test 5 times for 5 rows of data
    public void logintest() throws InterruptedException, IOException {

        int rowCount = excel.getRowCount();  // calling the function from excelutility class

        username = excel.getCellData(currentRow, 0); // Get current row's username and password
        password = excel.getCellData(currentRow, 1);  // Get current row's username and password

        reportUtil.createTest("Login Test: " + username + " / " + password);  // Create report entry by calling extentreportutility  class

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        WebElement btn = driver.findElement(By.tagName("button")) ;
        screenshotUtil.captureScreenshot("Login_" + username + ".png");
        btn.click();
        Thread.sleep(3000);   // Wait for login to process
        screenshotUtil.captureScreenshot("Login_" + username + ".png");    // Capture login screenshot
        reportUtil.test.addScreenCaptureFromPath("src/test/java/screenshotcapture/Login_" + username + ".png");
      //  Thread.sleep(3000);
        /*
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));  // if you assert here then invalid test will failed x marks not right mark comming. if not assert then all green but printing next line as well in invalid scenarios
            System.out.println("User Login Sucessfully....");
         */

        if (username.equals("Admin") && password.equals("admin123")) {   // Check if login was valid (Admin/admin123)
            boolean isDashboardVisible = driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed();
            Assert.assertTrue(isDashboardVisible);
            System.out.println("UserLogin Successfully...");

            screenshotUtil.captureScreenshot("Login_dashboard" + username + ".png");    // Capture login screenshot
            reportUtil.test.pass("Login successful for valid credentials.");

            // Logout
            driver.findElement(By.xpath("//img[@class='oxd-userdropdown-img']")).click();
            Thread.sleep(1000);
            driver.findElement((By.linkText("Logout"))).click();
            Thread.sleep(2000);
            System.out.println("User Logout Sucessfully........");
        }
        else {
            System.out.println("Invalid user name / password");
            reportUtil.test.fail("Login failed for invalid credentials.");
        }
        currentRow++;   // Move to next row for next invocation
    }




    @AfterMethod
    public void closeup() throws IOException {
        if (driver != null) {    // Close browser after each test
            driver.quit();
        }
    }

    @AfterTest
    public void tearDownAfterTest() throws Exception {
          reportUtil.flushReport();    // Flush report to save
          excel.closeWorkbook();  // Close Excel workbook by calling clseWorkbook() function
    }

}
