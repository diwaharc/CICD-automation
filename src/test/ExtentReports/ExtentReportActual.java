package test.ExtentReports;



import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportActual {
	
	
	@BeforeTest
	public static  ExtentReports ExtentReportNG()
	
	
	{      
        String path = System.getProperty("user.dir") + "//reports//Report.html";
        System.out.println(path);

		ExtentSparkReporter  spark = new ExtentSparkReporter(path);
		
		spark.config().setReportName("Actaulreport");
		spark.config().setDocumentTitle("Extentknowledge");
		
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("tester", "diwahar1");
		return extent;
		
		
   	}

}
