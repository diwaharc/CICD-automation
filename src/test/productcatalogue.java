package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productcatalogue extends Abstractpage {
	
WebDriver driver;
	
	public productcatalogue (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
     @FindBy(css= ".mb-3")
     List<WebElement> prodlist;
     
     
     @FindBy(css= ".card-body b")
     List<WebElement> Prodname;
     
     //make sure to use by variable instead of findby , because they are not driver.findelement , they are either by or limited driverscope by ex(add.findelement)
     By addtocart = By.cssSelector(".card-body button:last-of-type");
     
     By productby  =  By.cssSelector(".mb-3");
     
     By toast = By.id("toast-container");
     
     public List<WebElement> getprodlist()
     {
    	 WaitForElementToAppear(productby);
    	 return prodlist;
    	 
     }
     
     public WebElement getproductbyName(String Productname)
     {    
    	 //use getprodlist().stream , coz that method which used to get all the elements above 
    	 WebElement add = getprodlist().stream().filter(prod->
    	   prod.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(Productname)).findFirst().orElse(null); 
    	 return add;
     }
     
     public void addtoCart(String Productname )
     {
    	 //use "webelement add" in this method as well, because we click the add to cart with that specific product chosen , 
    	 //so we use the above method getproductbyname to click the button by storing it in add , check main code "framework"
    	 WebElement add = getproductbyName(Productname);
    	 add.findElement(addtocart).click();
    	 WaitForElementToAppear(toast);
    	 
     }
     
     @FindBy (css="[routerlink*='cart']")
 	WebElement cart;
 	
   
     
 	    public cartpage gotoCart()
     {
     	cart.click();
     	return new cartpage(driver);
     }
 	    
 	    
 	    
 	
	//w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
   // List<WebElement> product = driver.findElements(By.cssSelector(".mb-3"));

    //always use declared variable name ex= productname without double quotes in the below stream
   // WebElement add = product.stream().filter(prod->
   // prod.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(Productname)).findFirst().orElse(null);
    //traversing from .card body parent class to child class , instead of taking child class we take button tag and add:last-of-type [1/2](button type)
   // add.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	
}

