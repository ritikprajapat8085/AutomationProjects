package bstackdemo__POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CheckoutPage_03 {
    WebDriver driver;
    public CheckoutPage_03(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath="//span[@class='bag bag--float-cart-closed']") WebElement  clickonBaskect;
    @FindBy(xpath ="//span[@class='bag__quantity']") WebElement itemcouning ;  // for empty cart
    // click on checkout button
    @FindBy(xpath = " //div[text()='Checkout']") WebElement checkoutBtn ;
    // Form fields
    @FindBy(id = "firstNameInput") WebElement firstname;
    @FindBy(id = "lastNameInput") WebElement lastname;
    @FindBy(id = "addressLine1Input") WebElement addressInput;
    @FindBy(id = "postCodeInput") WebElement postalCodeInput;
    @FindBy(id = "checkout-shipping-continue") WebElement  submitBtn;
    @FindBy(id="provinceInput") WebElement stateInput;


    @FindBy(xpath="//div[@class ='checkout-form']")
    List<WebElement> orderplaceMessage;

    public void clickCheckOut()   {    // Click on  checkoutbutton
        checkoutBtn.click();
    }
    public boolean isCheckoutEnabled() {
        return checkoutBtn.isEnabled();
    }
    public boolean isCheckoutVisible() {
        return checkoutBtn.isDisplayed();
    }
    public void OpenbasketCart() {
          clickonBaskect.click();
          System.out.println("items are :"+itemcouning.getText());
    }





    // Fill checkout form
    public void fillCheckoutDetails(String fname, String lname ,String address, String state, String postalCode) {
        firstname.sendKeys(fname);
        lastname.sendKeys(lname);
        addressInput.sendKeys(address);
        stateInput.sendKeys(state);
        postalCodeInput.sendKeys(postalCode);
    }

    // Click checkout/place order
    public void clickPlaceOrder() {
       submitBtn.click();
    }
    public void getMessage() {
        for(WebElement i: orderplaceMessage) {
            String  message  = i.getText();
            System.out.println("OrderMessage is: "+ message);
        }
    }
}
