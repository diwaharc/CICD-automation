package test;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import test.suit.Baseclass;

public class errorvalidation extends Baseclass {

	
	@Test(groups = {"onlyFirstError"})
	public void loginerrorvalidation () throws IOException, InterruptedException {
		
		
		//if wrong id or password is given , then the below assert will pass it telling , actual matches the expected o/p.
		//we also removed productcatalogue productcatalogue = , bacause we can simply invoke logintoapplication by using landing.
		//because we already have created project and returned from base class
		landing.logintoapplication("diwahar1506@gmail.com", "Password@22");
		Assert.assertEquals("Incorrect email or password.", landing.errormessage());
		
	}
       @Test
       public void productlistvalidation () throws InterruptedException 
       {
          
    	   productcatalogue productcatalogue = landing.logintoapplication("diwahar506@gmail.com", "Password@22");
   		
           productcatalogue.addtoCart("ZARA COAT 3");
           Thread.sleep(2000);
           cartpage cartpage = productcatalogue.gotoCart();
          
          
           Thread.sleep(2000);
           Boolean Actual = cartpage.selectedproducts("ZARA COAT 3");
           Assert.assertTrue(Actual);
           
           
       
	}

}
