package com.Program;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Execute{

	
	
	
	
	

	public static void  main(String[] args) throws IOException, InterruptedException {
	


       //Initializing the ChromeBrowser.
        WebDriver driver = new  ChromeDriver();
        
        Set<Cookie> sessions = driver.manage().getCookies();
        
         //Navigating to the Amazon Web site.
        driver.get("https://www.amazon.in/customer-preferences/country/?ref_=navm_em_locale_0_1_1_23");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        for(Cookie cookie : sessions) {
        	
        	driver.manage().addCookie(cookie);
        }
        
        
         //Handling  Mouse Hover.
;	      Actions a= new Actions(driver);

        
         WebElement Accountlist = driver.findElement(By.xpath("//span[@class='nav-line-2 ']"));
         a.moveToElement(Accountlist).perform();	
       
        //Click on the "Sign In" button.
        driver.findElement(By.xpath("//span[@class='nav-action-inner']")).click();	
       
       //Fetching Test data (phone number) from external property file.
        GetProperty gp= new GetProperty();
        String num = gp.getProperty("number");
        
       //Find the phone number or email text field and enter your phone Number
        driver.findElement(By.id("ap_email")).sendKeys(num);
       
       //click on Continue button.
        driver.findElement(By.id("continue")).click();
       
        //Fetching Test data (password) from external property  file.
        String passwrd = gp.getProperty("password");
        
        //now Find the password  field and enter your password.
       driver.findElement(By.id("ap_password")).sendKeys(passwrd);
       
       //Then click on Submit button.
       driver.findElement(By.id("signInSubmit")).click();	        
        
       
       //check whether Home page is displayed or not.
       
       //if Displayed find search text field and search for the product.
       driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 15 pro max");
       
       //click on search icon.
       driver.findElement(By.id("nav-search-submit-button")).click();	
       
       //check whether desired product is displayed or not.
       
       
       //if displayed then click on that product.
       driver.findElement(By.xpath("(//img[@class='s-image'])[2]")).click();
          
       // As a click on product it switch to next tab. 
      Set<String> wh = driver.getWindowHandles();

      //getting the window handle of the tab.
      ArrayList<String> al= new ArrayList<String>(wh);
      driver.switchTo().window(al.get(1));
      
      //Then click on add to cart button.
      driver.findElement(By.xpath("(//input[@title='Add to Shopping Cart'])[2]")).click();
      
      //Check whether product is added to cart or not.
      String added = driver.findElement(By.xpath("(//h4[@class='a-alert-heading'])[5]")).getText();
      System.out.println(added);
      
      
     //Check if product is out of stock
    if (driver.findElements(By.id("availability")).size() > 0) {
          String availability = driver.findElement(By.id("availability")).getText();
          if (availability.contains("Out of stock")) {
              // Trigger alert for out of stock
              System.out.println("Product is out of stock. We will notify you once it's restocked.");
          }
      }
    
    Thread.sleep(5000);
    //Closing the Browser.
    driver.close();
   }

}