package packs;

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;


public class NewTest{
	WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver","D:\\gd\\geckodriver.exe"); 
       
	  DesiredCapabilities capabilities = DesiredCapabilities.firefox();  
	  capabilities.setCapability("marionette",true);  
	  driver= new FirefoxDriver(capabilities);  
  }
  @Test
  public void beforeMethod() {
	  driver.navigate().to("http://automationpractice.com/index.php");
	  driver.findElement(By.className("login")).click();
	  
  }
  
    @Test(dependsOnMethods = "beforeMethod")
    public void registration(){
      WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
      myDynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email_create']")));
      driver.findElement(By.id("email_create")).sendKeys("elianaaar@gmail.com");
      driver.findElement(By.id("SubmitCreate")).click();
      myDynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='customer_firstname']")));
      driver.findElement(By.id("customer_firstname")).sendKeys("Maria");
	  driver.findElement(By.id("customer_lastname")).sendKeys("Shikawat");
	  driver.findElement(By.id("passwd")).sendKeys("mi@123");
	  driver.findElement(By.id("address1")).sendKeys("Kholi 10,Goregoan East,Mumbai 400087");
	  driver.findElement(By.id("city")).sendKeys("Melon");
	  Select dropdown =new Select(driver.findElement(By.id("id_state")));
	  dropdown.selectByVisibleText("New York");
	  driver.findElement(By.id("postcode")).sendKeys("00000");
	  driver.findElement(By.id("phone_mobile")).sendKeys("9768253031");
	  driver.findElement(By.id("alias")).sendKeys("mir");
	  driver.findElement(By.id("submitAccount")).click();
	  myDynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='logout']")));
	  driver.findElement(By.xpath("//a[@class='logout']")).click();
	  
    }
    
    
    
    @Test(dependsOnMethods = "registration")
    public void login(){
    	
  	  driver.findElement(By.id("email")).sendKeys("elianaaar@gmail.com");
  	  driver.findElement(By.id("passwd")).sendKeys("mi@123");
  	  driver.findElement(By.id("SubmitLogin")).click();
  	  String actualUrl="http://automationpractice.com/index.php?controller=my-account";
  	  String expectedUrl= driver.getCurrentUrl();

      if(actualUrl.equalsIgnoreCase(expectedUrl))
      {
          System.out.println("Test passed");
      }
      else
      {
          System.out.println("Test failed");
      }
  	  Assert.assertEquals(actualUrl, expectedUrl);
  	  
  	  
  	  
    }
    
    @Test(dependsOnMethods = "login")
    public void search(){
  	 driver.findElement(By.id("search_query_top")).sendKeys("dress");
     driver.findElement(By.name("submit_search")).click();
  	  
    }
    
    @Test(dependsOnMethods = "search")
    public void addToCart() {
    	WebDriverWait myDynamicElement = new WebDriverWait(driver, 30);
        myDynamicElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='replace-2x img-responsive']")));
    	driver.findElement(By.xpath("//img[@alt='Printed Summer Dress']")).click();
    	driver.findElement(By.xpath("//button[@class='exclusive']")).click();
    }
    
}

