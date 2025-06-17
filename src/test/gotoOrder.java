package test;


import java.util.List;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class gotoOrder extends Abstractpage{
		
	WebDriver driver;
		
		public gotoOrder (WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
			
		}
		
		@FindBy (css = ".subtotal button")
		WebElement Checkout;
		
		private @FindBy (css = "tr td:nth-child(3)")
		List<WebElement> productsonorders;
		
		
		
		
		
		public Boolean Productsinorderpage(String Productname) throws InterruptedException
		{
		    
			Thread.sleep(3000);
			Boolean Actual = productsonorders.stream().anyMatch(s->s.getText().equalsIgnoreCase(Productname));
			return Actual;
				    
		}
		
	
	
		
		
		
		
		
		

	}


