package org.atmecs.practo.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.contstants.FileConstants;
import org.atmecs.practo.helper.PractoHelper;
import org.atmecs.practo.reports.LogReporter;
import org.atmecs.practo.testbase.TestBase;
import org.atmecs.practo.utils.ExcelReader;
import org.atmecs.practo.utils.Productaccess;
import org.atmecs.practo.utils.PropertiesReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PractoHome extends TestBase {
	LogReporter log=new LogReporter();
	ExcelReader excelread=new ExcelReader();
	PropertiesReader propread=new PropertiesReader();
	
	PractoHelper practohelp=new PractoHelper(); 
	@DataProvider(name = "pharmacy")
	public String[][] getExpected() throws IOException {
		String Excelarray[][] = null;
		Excelarray = excelread.returnLocator(FileConstants.expecteddata_file);
		return Excelarray;
	}
	@Test(priority=0,dataProvider="pharmacy")
	void practohome(String expectedurl,String prodname,String prodprice,String noofProd,String prodname1,String prodprice1,String noofProd1,String total,String aftertotal, String prodname2) throws IOException, InterruptedException {
		Properties prop=propread.property(FileConstants.config_file);
		Properties prop1=propread.property(FileConstants.pharmacy_file);
		driver.get(prop.getProperty("url"));
		log.LogReport("Url is loaded" );
		driver.manage().window().maximize();
		practohelp.click(prop1.getProperty("loc.menu.pharmacy.xpath"), driver);
		log.LogReport("Successfully Navigated to the Pharmacy webpage");
		practohelp.click(prop1.getProperty("loc.imglink.painrelief.xpath"), driver);
		log.LogReport("Click the to painrelief image link");
		driver.close();
		log.LogReport("Close the previous window");
		driver=practohelp.winhandler(driver);
		log.LogReport("Successfully Navigated to the painrelief webpage");
		practohelp.correctUrlchecker(driver,expectedurl);
		practohelp.click(prop1.getProperty("loc.submenu.fitness&wellness.xpath"), driver);
		log.LogReport("Navigate to the webpage fitness & wellness through left panel");
		practohelp.click(prop1.getProperty("loc.btn.add1.xpath"), driver);
		practohelp.click(prop1.getProperty("loc.btn.add2.xpath"), driver);
		//practohelp.click(prop1.getProperty("loc.btn.add3.xpath"), driver);
		
		practohelp.click(prop1.getProperty("loc.btn.viewcart.xpath"), driver);	
		practohelp.validatemanage(driver,prop1.getProperty("loc.panel.cart.xpath"),prodname,prodprice,noofProd,prodname2, prodprice1,noofProd1); 
		practohelp.validatetotalprice(driver,prop1.getProperty("loc.chekoutpanel.payamt.xpath"),total);
		practohelp.click(prop1.getProperty("loc.linktxt.location.xpath"),driver);
		Thread.sleep(2000);
		practohelp.click(prop1.getProperty("loc.btn.removeitem.xpath"), driver); 
		practohelp.validateafter(driver,prop1.getProperty("loc.panel.cart.xpath"),prodname1,prodprice1,noofProd1);
		practohelp.validatetotalprice(driver,prop1.getProperty("loc.chekoutpanel.payamt.xpath"),aftertotal);
		driver.quit();	
	}
}
