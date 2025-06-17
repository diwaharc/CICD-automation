package test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartpage extends Abstractpage{
	
WebDriver driver;
	
	public cartpage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	private @FindBy (css = ".cartSection h3")
	List<WebElement> productdselected;
	
	@FindBy (css = ".subtotal button")
	WebElement Checkout;
	
	
	
	public Boolean selectedproducts(String productname)
	{
	    //while using hashmap , when i send second id and password with adidas product , this below line initially has zara coat and failed the assert step
		//so i have changes it to "product name" below generically , so that it will dynamically checks for it 
		Boolean Actual = productdselected.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		return Actual;
			    
	}
	
	public checkoutpage checkout()
	{
		
		Checkout.click();
       return new checkoutpage(driver);
	}
	// w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection h3")));
    // List<WebElement> finalproduct = driver.findElements(By.cssSelector(".cartSection h3"));
     
    // Boolean Actual = finalproduct.stream().anyMatch(
     		//s->s.getText().equalsIgnoreCase("ZARA COAT 3"));
    // Assert.assertTrue(Actual);
    // driver.findElement(By.cssSelector(".subtotal button")).click();
	
	
	
	
	
	
	

}
