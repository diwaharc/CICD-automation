package test.ExtentReports;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportBasic {
	//make sure extent report dependency is added in pom file.
	ExtentReports extent;
	
	@BeforeTest
	public void Extentreportsbase()
	{
		 
		//ExtentSparkReporter class is the one will creates reports with all the configurations which expects path.
		//so we catch the path link and store it in a string variable to provide it to extentsparkreporter arguments.
		String path = System.getProperty("user.dir")+ "//Fold//Report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//providing report name and title of report
		reporter.config().setReportName("testreport");
		reporter.config().setDocumentTitle("titlecheck");
		
		//Extentreports is the main clkass which is responsible for execution of tests and generate the reports 
		extent = new ExtentReports();
		//the created report is attached with the main class for execution.
		extent.attachReporter(reporter);
		extent.setSystemInfo("tester", "diwa");
		
		
	}
	
	
	
	@Test
	public void justtitle()
	{   
		//this justtitle is the test case we are executing and generating report.
		//Extenttest is a class which is used to get the object instance of this current test case , so that we can use it to do some functionalities 
		//createtest is used with the extentreport object extent to create test for this testcases to reflect in the report
		ExtentTest test = extent.createTest("justtitle");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
	    test.fail("test failed");
	    //write all accumulated test information to the report file 
	    //it should be given at the end of the test execution , denoting the end of execution .
		extent.flush();
	}

}
