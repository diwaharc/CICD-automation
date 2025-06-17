package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstractpage {
      
	WebDriver driver;
	
	public Abstractpage (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	  @FindBy (css="[routerlink*='myorders']")
	  	WebElement order;
	
	  public  gotoOrder gotoOrder()
	   {
		  
		 WaitForWebElementToAppear(order);
	    	order.click();
	    	return new gotoOrder(driver);
	    	//it can also be written like below for more readability 
	    	//gotoOrder gotoorder  = new gotoOrder(driver);
	    	//return gotoorder;
	    }
	
	//this is used only for by elements defined 
	public void WaitForElementToAppear(By FindBy)
	{
		WebDriverWait w = new WebDriverWait(driver , Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));
		
	}
	
    //this is used for findbhy webelements which are initialised by pagefactory.initelements.
	public void WaitForWebElementToAppear(WebElement FindBy)
	{
		WebDriverWait w = new WebDriverWait(driver , Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(FindBy));
		
	}
	//this is used for that roller loading icon which appears while clcking on add to cart.
	public void WaitForElementTodisappear( WebElement roller) throws InterruptedException
	{
		
		Thread.sleep(4000);
	}
	
}
