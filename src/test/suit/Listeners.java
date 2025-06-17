package test.suit;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import test.ExtentReports.ExtentReportActual;

public class Listeners extends Baseclass implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReportActual.ExtentReportNG();
	//Threadlocal is used because we make sure the current session variable is not overridden with other parallelly running tests , since we used test = "parallel"
    ThreadLocal <ExtentTest >extendtest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub
		 //extentTest test is used to create the tests in the report , so we use extentReport's 'extent' object to call the methods
		test = extent.createTest(result.getMethod().getMethodName());
		//this threadlocal vaiable used here to set the test , so that test results is recorded for that current session test without replacing other parallel test session varaiable
		extendtest.set(test);
	}
	@Override
	public void onTestSuccess (ITestResult result) {
	// TODO Auto-generated method stub
		//this threadlocal vaiable used here to get the tests logs , so that test results is recorded for that current session test without replacing other parallel test session varaiable

		extendtest.get().log(Status.PASS, "its pass");
	}
	@Override
	public void onTestFailure (ITestResult result) {
	// TODO Auto-generated method stub
		extendtest.get().fail(result.getThrowable());
		// inorder to give knowledge to the driver defined in takescreenshots(defined in base class) class , we define the below line 
		//		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());(surround with try catch) is clicked to create the below try and catch blocks

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//result. is used because all these defined methods has result as arguments which has knowledge of the current sessions tests.
		//String path = getscreenshot(result.getMethod().getMethodName()) [surround with try and catch]
		String path = null;
		try {
			path = getscreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extendtest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped (ITestResult result) {
	// TODO Auto-generated method stub
	}
	@Override
	public void onFinish (ITestContext context) {
	    extent.flush(); // Final step that writes the report which is mandatory
	}
	

}
