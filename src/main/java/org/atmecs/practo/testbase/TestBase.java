package org.atmecs.practo.testbase;

import java.io.IOException;
import java.util.Properties;

import org.atmecs.practo.contstants.FileConstants;
import org.atmecs.practo.utils.ExcelReader;
import org.atmecs.practo.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

public class TestBase {
	protected WebDriver driver;
	PropertiesReader propread=new PropertiesReader();
ExcelReader excelreader = new ExcelReader();

	@BeforeTest
	public void setup() throws IOException {
		Properties prop = propread.property(FileConstants.config_file);
		switch (prop.getProperty("webdrivername")) {
		case "chrome":
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", FileConstants.chromefile);
			driver = new ChromeDriver(chromeoptions);
			break;
		case "firefox":
			FirefoxOptions firefoxop=new FirefoxOptions();
			firefoxop.addArguments("--disable-notifications");
			System.setProperty("webdriver.gecko.driver", FileConstants.firefoxfile);
			driver = new FirefoxDriver(firefoxop);
			break;
		case "Ie":
			System.setProperty("webdriver.ie.driver", FileConstants.Iefile);
			driver = new InternetExplorerDriver();
			break;
		case "Edge":
			System.setProperty("webdriver.edge.driver", FileConstants.edgefile);
			driver = new EdgeDriver();
			break;
		}
	}

}
