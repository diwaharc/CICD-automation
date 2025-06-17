package test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.suit.Baseclass;
//run hashmaps xml file which has grouping of only products
public class SubmitOrder extends Baseclass {
	String Productname = "ZARA COAT 3";

	//while using in braces it should be all small letters "dataprovider" followed by getdata only with double quotes, and then in {} should be groups
	@Test(dataProvider = "getdata" , groups = {"product"})
	public void submitorder(HashMap<String , String> input) throws IOException, InterruptedException {
		
		String country = "india";
		
		
		//we no more needed this line because this launch application is added in base class with before method annotation ,also submit order extends baseclass
	   // Landingpage landing = launchapplication();
		

		
	  //once the above line is removed , below landing variable has no initialisation , so we declare it initially on base class on top .
		// by typing landingpage landing on base class on top by giving public so that it can be accessed here .
		// by using "landing.logintoapplication("diwahar506@gmail.com", "Password@22");"  which is returning new productcatalogue and creating object of that class
		//instead of haedcoding the arguments values , we use input.email /input.password to get the values dynamically everytime from data provider
		productcatalogue productcatalogue = landing.logintoapplication(input.get("email"), input.get("password"));
		
		Thread.sleep(2000);
        productcatalogue.addtoCart(input.get("product"));
        Thread.sleep(2000);
        cartpage cartpage = productcatalogue.gotoCart();
        
        Thread.sleep(2000);
        Boolean Actual = cartpage.selectedproducts(input.get("product"));
        Assert.assertTrue(Actual);
        
        checkoutpage checkoutpage = cartpage.checkout();
        checkoutpage.selectcountry(country); 
        Confirmationpage Confirmationpage = checkoutpage.Submitorder();
        
        
        String order = Confirmationpage.Confirmation();
        //alway use assert related code lines in this class only.
        Assert.assertTrue(order.equalsIgnoreCase("Thankyou for the order.")) ;
        
         
    }
	@Test(dependsOnMethods = {"submitorder"})
	public void productsunderorder() throws InterruptedException
	{
		productcatalogue productcatalogue = landing.logintoapplication("diwahar506@gmail.com", "Password@22");
        //Thread.sleep(2000);

		gotoOrder gotoorder = productcatalogue.gotoOrder();
		Assert.assertTrue(gotoorder.Productsinorderpage(Productname));
		
		
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException
	{    
		//we could have used simply getjsontodata() function , so that it might be called , coz this entire class extends baseclass
		//but inorder to dynamically send json file links to base class and makimg it generic , we pass argument into the above method and storing in list hasmap variable
		//usage of list<hashmap<string string> data is because this list only can hold values from json which has array list of datas, 
		//so we get those values from json and store it in data variable and usin below by calling like data.get(0)(1)
		List<HashMap<String , String>> Data = getjsontodata(System.getProperty("user.dir")+ "//src//test//suit//purchaseorder.json") ;
		//new Object[][] is used to create a 2D array of objects at runtime.
		//You must instantiate it using new to actually allocate memory and return the data.

		return new Object[][] { {Data.get(0)},{Data.get(1)} };
	}
	
	

}
