package UtilityPackage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentReportUtility {
    public ExtentReports extent;  // for managing the report
    public ExtentTest test;       // respresent each testcase in report
    ExtentSparkReporter  htmlReporter  ;  // for setting up the report path and name
    //String reportName ;
    public ExtentReportUtility() {
       // htmlReporter = new  ExtentSparkReporter( reportName);
        htmlReporter = new  ExtentSparkReporter("OHRMProjectReport.html"); // Creating HTML report file
        extent = new ExtentReports();           // Initializing the ExtentReports object
         extent.attachReporter(htmlReporter);     // Attaching reporter to the report

        // Configuration of Report
        htmlReporter.config().setDocumentTitle("My OHRM Report");
        htmlReporter.config().setReportName("OHRMTest Project Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("dd-MMMM-yyyy");





        // Add environment information
        extent.setSystemInfo("DeviceName", "Lenovo");
        extent.setSystemInfo("Generation", "Core-i3-10Gen");
        extent.setSystemInfo("Operating System", "Windows 10");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("TesterName", "Ritik Prajati");
        extent.setSystemInfo("Application Under Test ", "OrangeHRM ");
    }
    // Method to create new test case in report
    public void createTest(String testName)
    {
        test = extent.createTest(testName);
    }
    // Final report save whatever we did so far in report
    public void flushReport()
    {
        extent.flush();
    }
}
