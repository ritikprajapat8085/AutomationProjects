package UtilityPackage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtility {
    WebDriver driver;

    public ScreenshotUtility(WebDriver driver)  // Constructor receives driver instance
    {
        this.driver = driver;
    }
    public void captureScreenshot(String fileName) throws IOException, IOException {   // Method to capture screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;     // Taking screenshot
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("src/test/java/screenshotcapture" + fileName);   // Saving screenshot in screenshots folder
         FileHandler.copy(src,dest);
    }
}
