package bstackdemo__TestCase;
import UtilityClasses.ExtentReportUtility;
import UtilityClasses.WebDriverFactory;
import bstackdemo__POM.LoginPage_01;
import bstackdemo__POM.ProductPage_CartPage_02;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartTests_02 {
    WebDriver driver;
    LoginPage_01 login ;
    ProductPage_CartPage_02 productPage_cartPage;
    ExtentReportUtility  reportutil ;

    @BeforeTest
    public void reportsetup() {
        reportutil = new ExtentReportUtility("bstackdemo_CartTests.html") ;
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
    }
    @AfterMethod
    public void closeup() {
        driver.close();
    }





    //  TC_004: Add single item to cart
    @Test(priority = 1)
    public void TC_004_AddSingleItemToCart() throws InterruptedException {

        reportutil.createTest("Add Single Item ToCart");
        System.out.println("TC_004: Add single item to cart ");
        login.clickSign();
        Thread.sleep(2000);
        login.fulllogin("demouser", "testingisfun99");
        Thread.sleep(2000);
        productPage_cartPage.appleVendor();
        productPage_cartPage.goToCart();
        productPage_cartPage.getCartItemCount();
        reportutil.test.pass("Item added to card ");
        System.out.println("==============================xxxxxxxxx==================================");
    }



    //  TC_005: Add mutiple item to cart
    @Test(priority = 2)
    public void  TC005_addmultipleitemToCart() throws InterruptedException {
        reportutil.createTest("Add multiple Item ToCart");
        System.out.println("TC_005: Add multiple items to cart and verify cart count ");
        login.clickSign();
        Thread.sleep(2000);
        login.fulllogin("demouser", "testingisfun99");
        Thread.sleep(2000);
        productPage_cartPage.appleVendor();
        productPage_cartPage.addMultipleProductsToCart(3); // Add 3 products using helper method
        System.out.println("Total items added to cart :") ;
        productPage_cartPage.getmultipleItems();
      //  Assert.assertTrue();

        productPage_cartPage.ItemCounts();
        reportutil.test.pass(" multiple Item added to card ");
        System.out.println(" ----------------------Testcase Passed---------------");
        System.out.println("==============================xxxxxxxxx==================================");
    }

    //  TC_006: Remove item from cart
    @Test(priority = 3)
    public void TC006_removeitemToCart() throws InterruptedException {
        reportutil.createTest("remove Item ToCart");
        System.out.println("TC_006: Remove item from cart ");
        login.clickSign();
        Thread.sleep(2000);
        login.fulllogin("demouser", "testingisfun99");
        Thread.sleep(2000);

        productPage_cartPage.appleVendor();
        productPage_cartPage.addMultipleProductsToCart(2); // Add 3 products using helper method
         System.out.println("before removing Total items added to cart :") ;
         productPage_cartPage.getmultipleItems();
         productPage_cartPage.ItemCounts();

         productPage_cartPage.RemoveItem();
         System.out.println(" after removing  one items ,remaining items :") ;
         productPage_cartPage.getmultipleItems();
         reportutil.test.pass(" removed one item from card ");
        System.out.println("==============================xxxxxxxxx==================================");
    }
}
