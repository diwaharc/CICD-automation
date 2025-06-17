package test;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Framework {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String Productname = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver ();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("diwahar506@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@22");
		driver.findElement(By.id("login")).click();
		WebDriverWait w = new WebDriverWait(driver , Duration.ofSeconds(5));
		//we put explicit wait until the entire product section loads only then we can take all the webelements list 
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
        List<WebElement> product = driver.findElements(By.cssSelector(".mb-3"));
    
        //always use declared variable name ex= productname without double quotes in the below stream
        WebElement add = product.stream().filter(prod->
        prod.findElement(By.cssSelector(".card-body b")).getText().equalsIgnoreCase(Productname)).findFirst().orElse(null);
        //traversing from .card body parent class to child class , instead of taking child class we take button tag and add:last-of-type [1/2](button type)
        add.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        //making sure we wait for few seconds so that animation loding and product addedd pop up goes so that we can click cartt button uninterreptued
        w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
       // w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
        //Thread.sleep(2000);
        w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartSection h3")));
        List<WebElement> finalproduct = driver.findElements(By.cssSelector(".cartSection h3"));
        
        Boolean Actual = finalproduct.stream().anyMatch(
        		s->s.getText().equalsIgnoreCase("ZARA COAT 3"));
        Assert.assertTrue(Actual);
        driver.findElement(By.cssSelector(".subtotal button")).click();
        ////input[contains(@placeholder,'Country')]
        //it should be Action with s and also make sure to so build and perform.
        Actions a = new Actions(driver);        
        a.sendKeys(driver.findElement(By.xpath("//input[contains(@placeholder,'Country')]")), "India").build().perform();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'ta-item')][2]")));
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
        driver.findElement(By.className("action__submit")).click();
        w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hero-primary")));
        String order = driver.findElement(By.cssSelector(".hero-primary")).getText();
      Assert.assertTrue(order.equalsIgnoreCase("Thankyou for the order.")) ;
       driver.quit();
	}

}
