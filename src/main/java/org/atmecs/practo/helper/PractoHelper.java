package org.atmecs.practo.helper;

import java.util.List;
import java.util.Set;

import org.atmecs.practo.reports.LogReporter;
import org.atmecs.practo.utils.Productaccess;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

public class PractoHelper {
	LogReporter log=new LogReporter();
	Productaccess  access=new Productaccess();
	public void click(String locators, WebDriver webdriver) {
		WebDriverWait wait2 = new WebDriverWait(webdriver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(locators)));
		WebElement click_operation = webdriver.findElement(By.xpath(locators));
		click_operation.click();
	}
	public WebDriver winhandler(WebDriver driver) {
		String window_array[]=new String[5];
		Set<String> windows=driver.getWindowHandles();
		int initial=0;
		for (String win:windows)
		{
			window_array[initial]=win;
		}
		driver=driver.switchTo().window(window_array[0]);
		return driver;
	}
	
	public void correctUrlchecker(WebDriver driver,String expectedurl) {
		try {
		Assert.assertEquals(driver.getCurrentUrl(),expectedurl);
		System.out.println("User landed or Reached the correct webpage");
		log.LogReport("Successfully Validated the correct Url is :"+ driver.getCurrentUrl());
	}
		catch(AssertionError e) {
			System.out.println("Navigate to wrong Webpage");
		}	
		}
	public void validatetotalprice(WebDriver driver,String locator,String paytotal) {
		try {
			System.out.println();
		WebElement ele=driver.findElement(By.xpath(locator));
		String total=ele.getText();
		total=total.substring(1,total.length());
		  Assert.assertEquals(total, paytotal);
		  System.out.println("Successfully Verified the total payment :"+paytotal);
		}
		catch(AssertionError e ) {
			System.out.println("Wrong Payment Total");
		}
	}
	public String[] elementloader(WebDriver driver,String locator1) {
	WebElement proddetails=driver.findElement(By.xpath(locator1));
	  String[] prodarray= proddetails.getText().split("\n"); 
	  return prodarray;
	}
	 public void validatename(WebDriver driver,String locator1,String prodname,int index) {
		 String[] prodarray=elementloader(driver,locator1);
		 try {
			 Assert.assertEquals(prodarray[index],prodname);
			  System.out.println("Successfully Verified the Product Name :"+prodname);
			 
		 }catch(AssertionError e) {
			 System.out.println("Product Name "+prodarray[index]+" is not match with :"+prodname);
		 }
	 }
	 public void validateprice(WebDriver driver,String locator1,String prodprice,int index) {
		 String[] prodarray=elementloader(driver,locator1);
		 String price=prodarray[index];
		  price=price.substring(1,price.length());
		 try {
			  Assert.assertEquals(price,prodprice);
			  System.out.println("Successfully Verified the Poduct Price :"+prodprice); 
		 }catch(AssertionError e) {
			 System.out.println("Product price "+price+" is not match with :"+prodprice);
		 }
	 }
	 public void validateprodcount(WebDriver driver,String locator1,String prodcount,int index) {
		 String[] prodarray=elementloader(driver,locator1);
		 try {
			 Assert.assertEquals(prodarray[index],prodcount);
			  System.out.println("Successfully Verified the product count :"+prodcount);
		 }catch(AssertionError e) {
			 System.out.println("Product count "+prodarray[index]+" is not match with :"+prodcount);
		 }
	 }
	 public void  validatemanage(WebDriver driver,String locator,String prodname,String prodprice,String noofprod,String prodname1,String prodprice1,String noofprod1) {
		 String[] list=elementloader(driver,locator);
		   int length=list.length/7;
		   length=(length*4)-1;
		   length=list.length-length;
			int storage[]=new int[length];
			int limit=(list.length/7)+1;
			int[] prodindex=access.indexgenerator(storage, limit);
			 int count=0;
			while(count<1) {
			 validatename(driver,locator, prodname,prodindex[count]);
			 count++;
			 validateprice(driver, locator, prodprice,prodindex[count]);
			 count++;
			 validateprodcount(driver, locator, noofprod,prodindex[count]);
			 count++;
			 validatename(driver,locator, prodname1,prodindex[count]);
			 count++;
			 validateprice(driver, locator, prodprice1,prodindex[count]);
			 count++;
			 validateprodcount(driver, locator, noofprod1,prodindex[count]);
			}
		 
	 }

	
	public void validateafter(WebDriver driver,String locator,String prodname1,String prodprice1,String noofprod1){
		
			 String[] list=elementloader(driver,locator);
			   int length=list.length/7;
			   length=(length*4)-1;
			   length=list.length-length;
				int storage[]=new int[length];
				int limit=(list.length/7)+1;
				int[] prodindex=access.indexgenerator(storage, limit);
				 int count=0;
				while(count<1) {
					 validatename(driver,locator, prodname1,prodindex[count]);
					 count++;
					 validateprice(driver, locator, prodprice1,prodindex[count]);
					 count++;
					 validateprodcount(driver, locator, noofprod1,prodindex[count]);
				}
				}
	
	
}
