package UtilityClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory
{
    public static WebDriver getDriver()
    {
         WebDriver driver ;
         return  new ChromeDriver();
    }

}
