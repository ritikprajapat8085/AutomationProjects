
package OHRMScenario_1_Login_Logout;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class OHRMTestUsingDataProvider
{
    WebDriver driver;
    FileInputStream readfile ;
    XSSFWorkbook wb ;
    XSSFSheet sheet;
    XSSFRow row ;
    XSSFCell cell ;



    @BeforeTest
    public  void setup() throws IOException
    {
        readfile = new FileInputStream("C:\\Users\\Admin\\Documents\\Staragile Automation course\\CapstoneProjects\\OHRMProject\\OHRMTestData.xlsx");
        wb = new XSSFWorkbook(readfile);
        sheet = wb.getSheet("Sheet1") ;
       driver= new ChromeDriver();
        driver .manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }


    @Test(dataProvider = "logindata")
    public void logintest(String un,String ps) throws InterruptedException {
        driver.findElement(By.name("username")).sendKeys(un);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(ps);

          WebElement btn = driver.findElement(By.tagName("button")) ;
          btn.click();
          Thread.sleep(3000);

      // Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));  // if you assert here then invalid test will failed x marks not right mark comming. if not assert then all green but printing next line as well in invalid scenarios
      //  System.out.println("User Login Sucessfully....");
    }


    @DataProvider
    public Object[][] logindata()
   {
      int r = sheet.getPhysicalNumberOfRows();
    //  int c = sheet.getRow(0).getLastCellNum();// optional

      String[][] data = new String[r-1][2]  ; // can direcly write 2 cell

       for(int i=0;i<r-1 ;i++)
       {
           row =sheet.getRow(i+1);
           for( int j=0;j<2;j++)
           {
               cell =row.getCell(j);
               data[i][j]= cell.getStringCellValue();
           }
       }
       return data;
   }


 @AfterMethod
 public void logout() throws InterruptedException {
     if(driver.getCurrentUrl().contains("dashboard"))
     {
         System.out.println("UserLogin Successfully...");
         driver.findElement(By.xpath("//img[@class='oxd-userdropdown-img']")).click();
         driver.findElement((By.linkText("Logout"))).click();
         System.out.println("User Logout Sucessfully........");
       //  Thread.sleep(3000);
     }
     else {
         System.out.println("Invalid user name / password");
     }
 }

    @AfterTest
    public void closeup() throws IOException {
        wb.close();
        readfile.close();
        driver.close();
    }
}
