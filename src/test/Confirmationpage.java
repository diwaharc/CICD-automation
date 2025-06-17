package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Confirmationpage extends Abstractpage{

	
	
	
WebDriver driver;
	
	public Confirmationpage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	By Confirmationmessage = By.cssSelector(".hero-primary");
	
	@FindBy (css=".hero-primary")
	WebElement message;
	
	public String Confirmation()
	{
		WaitForElementToAppear(Confirmationmessage);
		
	    String order = message.getText();
        return order;
		
		
	}
	
	
	
	 //w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy());
     //String order = driver.findElement(By.cssSelector(".hero-primary")).getText();
}
