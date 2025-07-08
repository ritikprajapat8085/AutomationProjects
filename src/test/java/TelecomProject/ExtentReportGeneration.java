package TelecomProject;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class ExtentReportGeneration implements ITestListener
{
    ExtentSparkReporter htmlReport ; // This is the file that will store the report
    ExtentReports report;          // This is an object which is generating the report
    ExtentTest test;   // This is an object which will refer to the test case of your script and update the status of test method
     public void onStart(ITestContext context)
     {
         htmlReport = new ExtentSparkReporter("C:\\Users\\Admin\\MyRestAPI\\src\\test\\java\\TelecomProject\\TelecomProjectReport.html");
         report = new ExtentReports();
         report.attachReporter(htmlReport);  // The report should be saved to the html file
         // Configuration of Report
         htmlReport.config().setDocumentTitle("TelecomProjectReport");
         htmlReport.config().setReportName("TelecomProject");
         htmlReport.config().setTheme(Theme.STANDARD);
         htmlReport.config().setTimeStampFormat("dd-MMMM-yyyy");
         // Add environment information
         report.setSystemInfo("Machine", "Asus");
         report.setSystemInfo("Operating System", "Windows 10");
         report.setSystemInfo("Browser", "Chrome");
         report.setSystemInfo("User", "Ritik");
       //report.setSystemInfo("Application", "Google");
     }

    public void onTestSuccess(ITestResult result)
    {
        test = report.createTest(result.getName()); // ceate new enttity in report (i.e. method pass name)
        test.log(Status.PASS, "Test Pass" +result.getName());
    }

    public void onTestFailure(ITestResult result)
    {
        test = report.createTest(result.getName()); // ceate new enttity in report
        test.log(Status.FAIL, "Test Fail" +result.getName());
        test.log(Status.FAIL, "Test Fail because of " +result.getThrowable());
    }

    public void onTestSkipped(ITestResult result)
    {
        test = report.createTest(result.getName());
        test.log(Status.SKIP, "Test Skipped" +result.getName());
    }

    public void onFinish(ITestContext context)
    {
        report.flush();  // it will write to report whatever we did so far
    }


}
