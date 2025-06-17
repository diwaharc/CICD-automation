package test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Dupe {

	public static void main(String[] args) throws InterruptedException {
		
		String Productname = "ZARA COAT 3";
		String country = "india";
		WebDriver driver = new ChromeDriver ();
		driver.manage().window().maximize();
		Landingpage Landingpage = new Landingpage(driver);
		Landingpage.geturl();
		

		
	   //we could have used productcatalogue productcatalogue = new productcatalogue(driver);
		//and called addtocart by productcatalogue.addtocart();
		//but inorder to stop using creation of object of class repeatedly in this class , we are returning "return new productcatalogue(driver);" 
		//in landing page to productcatalogue productcatalogue using Landingpage.logintoapplication("diwahar506@gmail.com", "Password@22"); 
		//parallely we are getting product catalogue class object/instance to product catalogue page to access methods and webelements
		productcatalogue productcatalogue = Landingpage.logintoapplication("diwahar506@gmail.com", "Password@22");
		//reason why we directly used addtocart menthod from productcatalogue class is , it has the return values of getproductlist(), and getproductbyname() into its method.
		//addtocart method has only getproductbyname() mentod , but getproductByNamemethod() already has return value of getproductlist().
        productcatalogue.addtoCart(Productname);
        Thread.sleep(2000);
        cartpage cartpage = productcatalogue.gotoCart();
        
        
      
       
       
        Thread.sleep(2000);
        Boolean Actual = cartpage.selectedproducts(Productname);
        Assert.assertTrue(Actual);
        
        
        
        checkoutpage checkoutpage = cartpage.checkout();
        checkoutpage.selectcountry(country); 
        Confirmationpage Confirmationpage = checkoutpage.Submitorder();
        
        
        
        
        String order = Confirmationpage.Confirmation();
        //alway use assert related code lines in this class only.
        Assert.assertTrue(order.equalsIgnoreCase("Thankyou for the order.")) ;
        driver.close();
       
        
         
       
	}

}
