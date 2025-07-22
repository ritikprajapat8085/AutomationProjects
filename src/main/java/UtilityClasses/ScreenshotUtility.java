package UtilityClasses;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtility
{
    public  static void getScreenshot(WebDriver driver,String fileName) throws  IOException
    {   // Method to capture screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;     // Taking screenshot
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("src/test/java/screenshotcapture" + fileName+ ".png");   // Saving screenshot in screenshots folder
         FileHandler.copy(src,dest);
    }
}
