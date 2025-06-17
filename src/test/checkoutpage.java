package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkoutpage extends Abstractpage{

	
WebDriver driver;
	
	public checkoutpage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (xpath = "//input[contains(@placeholder,'Country')]")
	        
	WebElement Country;
	

	@FindBy (xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement specificCountry;
	

	@FindBy (css = ".actions .action__submit")
	WebElement submit;
	
	By ind = By.xpath("//button[contains(@class,'ta-item')][2]");
	
	public void selectcountry (String countryname)
	{
		Actions a = new Actions(driver);        
	     a.sendKeys(Country, countryname).build().perform();
	     WaitForElementToAppear(ind);
	     specificCountry.click();
		
	}
	
	public Confirmationpage Submitorder()
	{
		
		submit.click();
		return new Confirmationpage(driver);
		
	}
	 
	
}
