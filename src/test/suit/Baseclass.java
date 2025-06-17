package test.suit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.Landingpage;



public class Baseclass {
	
	//initializing both webdriver and landing here so that all the methods can utilise them .
	 public WebDriver driver;
	 public Landingpage landing;
	
	public WebDriver initializebrowser() throws IOException
	{
		
		//we create object of property class to get get knowledge on which browser needs to be executed, check for globaldata.properties class .
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "//src//test//resources//GlobalData.properties");
		prop.load(file);
		
		
		//this line is used to store info of the browser value defined in global properties class in string variable ,but commented this line and defined another below
		//String browser = prop.getProperty("browser");
		
		// inorder to not only dependent on global porperties class where browser = chrome is defined , for changing we need to again go into that class and change 
		// so we made a below line when we use mvn test -perrorvalidation -Dbrowser = chrome in cmd promt , it will first check below line where system.getproperty will first get cmd prompt line browser value 
		// so since it is chrome then 1st segement  in the below  line where != null ?  will be passed and execute 2nd segment where it will provide chrome value which is from cmd prompt
		//if there is no -dgrowser va;ue in cmp prompt , then 3rd segemt will be executed and gets browser value from globaal property class
		String browser = System.getProperty("browser")!= null ? System.getProperty("browser") :  prop.getProperty("browser");
		
		if (browser.contains("chrome"))
		{ 
			//inorder to run browser in headless mode which means no  browser will be opened , just backend run and execution will be successful we defined as below 
			//when from cmdprompt / jenkins if we send -dbrowser = chromeheadless , then we use chromeoptions class and pass its object to chromedriver parameter
			//checking if browser value contains headless value if it is yes then we are adding arguments using options object of chromeoptions class
			ChromeOptions options = new ChromeOptions();
			if (browser.contains("headless")) {options.addArguments("headless");
			}
			//if browser is not headless and options object is null , still below line executes normally using prperties class browser value
			driver = new ChromeDriver(options);
			//sometime headless mode there is no actual GUI window and cause elements invisible / not clickable ,This below line ensures your elements (especially responsive ones) render as they would on a normal desktop browser.

			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browser.equalsIgnoreCase("firefox"))
			
		{
			
			driver = new FirefoxDriver();
			
		}
		
		
		driver.manage().window().maximize();
		return driver;
	}
	
	
	//this below code is used for converting the json datas to string and then to hashmaps , 
	//so that it can be passed as arguments to the submit order class for logging in.
	public List<HashMap<String, String>> getjsontodata(String filepath) throws IOException
	{ 
		//json to string conversion
		//jackson-databind is the core dependency that allows ObjectMapper to work.
		//It’s a constant that represents the UTF-8 character encoding.
		//UTF-8 is a universal, standard character set that supports all languages, emojis, and special symbols.

		String jsoncontent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		//string to hashmap conversion
		List<HashMap<String , String>> Data = mapper.readValue(jsoncontent, new TypeReference <List<HashMap<String , String>>>(){});
		return Data;
		
		
	}
	 
	//The reason why we used (alwaysRun = true) is , while running testnggrouping xml , which only runs method/testcase with grouping "onlyfirsterror" .
	//but these before and after method are prerequisite for runniung basic tests , so this grouping expects these methods also to be defined with grouping "onlyfirsterror".
	//which is not ideal , then these 2 griups will run only for that error validation scenareio only while running that xml , so we use always run = true , to make us run at any conditionsd
	@BeforeMethod(alwaysRun= true)
	public Landingpage launchapplication () throws IOException
	{    //initializebrowser() methos returns the current object instance , so we are getting it in driver variable in this class , and proceeding to landing page.
		driver=initializebrowser();
		//landing page is initialised on the top of the class to access across all the classes 
	    landing = new Landingpage(driver);
		landing.geturl();
	    return landing;   
		
	}
	
	public String getscreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
				FileUtils.copyFile(source, file);
				return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		
	}
	
	
	
	@AfterMethod (alwaysRun= true)
	public void close()
	{
		
	     driver.quit();

	}
	
	

}
