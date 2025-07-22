package bstackdemo__POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class ProductPage_CartPage_02
{
    WebDriver driver;
    public ProductPage_CartPage_02(WebDriver driver) {  // Constructor to initialize WebElements
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "checkmark") WebElement phonevendorApple;
    @FindBy(xpath = "(//div[@class='shelf-item__buy-btn'])[1]") WebElement addtocardBtn ;
    @FindBy(xpath = "//div[@class='shelf-item__details']") WebElement productDetail ;
    @FindBy(xpath = "//div[@class='sub-price']/p") WebElement productprice ;
    @FindBy(xpath = "//div[@class='shelf-item__buy-btn']")
    List<WebElement> addtoCart ;   //   //div[text()='Add to cart']
    @FindBy(xpath = "//div[@class='shelf-item__details']") List<WebElement> multiproductDetail ;
    @FindBy(xpath = "//div[@class='float-cart__close-btn']" ) WebElement clickonCross ;
    @FindBy(xpath="//span[@class='bag bag--float-cart-closed']") WebElement  clickonBaskect;
    @FindBy(xpath = "(//div[@class='shelf-item__del'])[1]" ) WebElement removesingleItem;
    @FindBy(xpath = "//p[@class='desc']") WebElement itemCount ;
    @FindBy(xpath = "//span[@class='bag bag--float-cart-closed']//span") WebElement itemcounts ;

    public void appleVendor() {   //  Click on phonevendor options
        phonevendorApple.click();
    }

    public void goToCart()   //  TC_004: Add single item to cart
    {
        addtocardBtn.click();
        String prodcut =productDetail.getText() ;
        String price =productprice.getText() ;
        System.out.println("Product details are:");
        System.out.println( prodcut);
        System.out.println( price);
         //clickonCross.click();   // for TC007 comment on this
    }



    public void getCartItemCount()   // for TC01
    {
        String count = itemCount.getText() ;
        System.out.println("single item count is: "+ count);
    }
    public void ItemCounts()   // for TC02
    {
        String actualcount = itemcounts.getText() ;
     System.out.println("Total item count is: "+ actualcount);
    }
    public void getmultipleItems()
    {
        System.out.println("item are :");
        for (WebElement i: multiproductDetail)
        {
            System.out.println( i.getText());
        }
    }

    // Method to add product by index
    public void addProductToCart(int index) throws InterruptedException {
        addtoCart.get(index).click();
        Thread.sleep(2000);
        clickonCross.click();
    }

    // Add multiple products
    public void addMultipleProductsToCart(int count) throws InterruptedException {
        for (int i = 1; i <=count; i++)
        {
            addProductToCart(i);
        }
    }

 public void RemoveItem() throws InterruptedException  // remove items
 {
     clickonBaskect.click();
     Thread.sleep(2000);
     removesingleItem.click();
 }




}
