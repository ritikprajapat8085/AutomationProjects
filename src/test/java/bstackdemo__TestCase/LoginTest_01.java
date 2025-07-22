package bstackdemo__TestCase;
import UtilityClasses.ExtentReportUtility;
import UtilityClasses.WaitUtils;
import UtilityClasses.WebDriverFactory;
import bstackdemo__POM.LoginPage_01;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest_01
{
    WebDriver driver;
    LoginPage_01 login;
  ExtentReportUtility  reportutil ;
    @BeforeTest
    public void reportsetup() {
        reportutil = new ExtentReportUtility("bstackdemo_LoginTest.html") ;
    }
    @AfterTest
    public  void reportsaving() {
        reportutil.flushReport();
    }
    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://bstackdemo.com/");
         WaitUtils.setImplicitWait(driver,10);  // add otherwise  NoSuchElement Exception
         login = new LoginPage_01(driver);
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }



    @Test(priority = 1)
    public void validLoginTest() throws InterruptedException  //TC_001: Login with valid credentials (username: demouser, password: testingisfun99)
    {
        reportutil.createTest("Login with valid credential");
        System.out.println("TC_001: Login with valid credentials");
        login.clickSign();
        Thread.sleep(2000);     // add otherwise  NoSuchElement Exception
        login.fulllogin("demouser", "testingisfun99");
        String currentUrl = driver.getCurrentUrl();
        String actualText = driver.findElement(By.xpath("//span[text()='demouser']")).getText();
        Assert.assertTrue(currentUrl.contains("signin") , "Login might have failed - current URL: " + currentUrl);
        Assert.assertEquals(actualText,"demouser" ," Text Not matching....");
        reportutil.test.pass("user login sucessfully: demouser testingisfun99 ");
        System.out.println("User login Sucessfully.....");
        System.out.println("Product Page username is: "+actualText);
        System.out.println("======================x==============================");
    }

    @Test(priority = 2)
    public void InvalidLoginTest() throws InterruptedException  //  - TC_002: Login with invalid credentials
    {
        reportutil.createTest("Login with Invalid credential");
        System.out.println(" TC_002: Login with invalid credentials ");
        login.clickSign();
        Thread.sleep(2000);     // add otherwise  NoSuchElement Exception
        login.fulllogin("locked_user", "testingisfun99");
        reportutil.test.fail("Invalid  username/password: locked_user/testingisfun99");
        System.out.println(" Invalid  username/password.....");
        System.out.println("=======================x===============================");
    }





    @Test(priority = 3)
    public void EmptyLoginTest() throws InterruptedException   // - TC_003: Login with empty username/password
    {
        reportutil.createTest("Login with empty username/password");
        System.out.println(" TC_003: Login with empty username/password");
        login.clickSign();
        Thread.sleep(2000);     // add otherwise  NoSuchElement Exception
        login.fulllogin("locked_user", "");
        reportutil.test.fail("Empty username/password: locked_user / ----- ");
        System.out.println(" Empty username/password.....");
        System.out.println("=======================x===============================");
    }

}
