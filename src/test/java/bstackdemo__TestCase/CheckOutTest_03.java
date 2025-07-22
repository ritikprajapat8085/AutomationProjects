package bstackdemo__TestCase;
import UtilityClasses.ExtentReportUtility;
import UtilityClasses.WebDriverFactory;
import bstackdemo__POM.CheckoutPage_03;
import bstackdemo__POM.LoginPage_01;
import bstackdemo__POM.ProductPage_CartPage_02;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
public class CheckOutTest_03
{
    WebDriver driver;
    LoginPage_01 login ;
    ProductPage_CartPage_02 productPage_cartPage;
    CheckoutPage_03  checkoutPage ;
    ExtentReportUtility reportutil ;
    @BeforeTest
    public void reportsetup() {
        reportutil = new ExtentReportUtility("bstackdemo_CheckOutTests.html") ;
    }
    @AfterTest
    public  void reportsaving() {
        reportutil.flushReport();
    }


    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bstackdemo.com/");
        Thread.sleep(3000);

        productPage_cartPage = new ProductPage_CartPage_02(driver);
        login = new  LoginPage_01(driver);
        checkoutPage = new CheckoutPage_03(driver);
    }
    @AfterMethod
    public void closeup() {
      //  driver.close();
    }

   // - TC_007: Place order with valid details
      @Test(priority = 1)
    public void TC_007_CheckoutProcess() throws InterruptedException {
        reportutil.createTest("Place order by entering shipping details");
        System.out.println("TC_007: Place order with valid details");
        login.clickSign();
        Thread.sleep(2000);
        login.fulllogin("demouser", "testingisfun99");
        Thread.sleep(2000);
        productPage_cartPage.appleVendor();
        productPage_cartPage.goToCart();
        Assert.assertTrue(checkoutPage.isCheckoutEnabled(),"Checkout button should be visible");
        Assert.assertTrue(checkoutPage.isCheckoutVisible(),"Checkout button should be enable");
        checkoutPage.clickCheckOut();
        Thread.sleep(2000);
        checkoutPage.fillCheckoutDetails("ritik","prajapati","Indore","M.P.","454001");
        checkoutPage.clickPlaceOrder();
        Thread.sleep(2000);
        String expectedUrl = "https://bstackdemo.com/confirmation";
        Assert.assertTrue(driver.getCurrentUrl().contains("/confirmation"), "Should be on order confirmation page");
        checkoutPage.getMessage();
        reportutil.test.pass("Sucessfully ordered ");
        System.out.println("--------------- Assertion +TestCase Passed-------------------");
        System.out.println("==============================xxxxxxxxx==================================");
    }


    @Test(priority = 2)
    public void TC_008_checkoutWithoutAddingItems() throws InterruptedException {
        reportutil.createTest("checkoutWithoutAddingItems");
        System.out.println(" TC_008: Checkout flow without adding items (negative test)");
        login.clickSign();
        Thread.sleep(2000);
        login.fulllogin("demouser", "testingisfun99");
        Thread.sleep(2000);

        checkoutPage.OpenbasketCart();

        reportutil.test.pass("No items added on cart  ");
        System.out.println("--------------- TestCase Passed-------------------");
        System.out.println("==============================xxxxxxxxx==================================");

    }



}
