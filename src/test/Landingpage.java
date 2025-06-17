package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landingpage extends Abstractpage {
	
	
WebDriver driver;
     //The constructor in a Page Object Model (POM) class is used to initialize the WebDriver instance. 
     //It allows the test class ex : dupe or other page classes to pass the same WebDriver object to the page, 
     //so it can find and interact with web elements.
	
	public Landingpage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	//driver.findElement(By.id("userEmail")).sendKeys("diwahar506@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("Password@22");
	//driver.findElement(By.id("login")).click();
	@FindBy(id="userEmail")
	WebElement username;
	@FindBy(id="userPassword")
	WebElement passsword;
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	
	//make sure return type is productcatalogue
	public productcatalogue logintoapplication(String user , String password)
	{
		username.sendKeys(user);
		passsword.sendKeys(password);
		submit.click();
		
		//We use new to create an object of a class, and assign it to a reference variable check the dupe class where we have created a productcatalogue variable.
		//The variable holds the memory address of that object (ex) new productcatalogue(driver) 
		//allowing us to call methods and access elements defined in productcatalogue  class.
		return new productcatalogue(driver);
	}
	
	public String errormessage()
	{
		
		WaitForWebElementToAppear(errormessage);
		return errormessage.getText();
	}
	
	
	public void geturl()
	{
		driver.get("https://rahulshettyacademy.com/client/");

		
	}
}

