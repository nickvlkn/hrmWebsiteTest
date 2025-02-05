package com.neotech.testbase;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.neotech.utils.ConfigsReader;
import com.neotech.utils.Constants;

public class BaseClass {
	
	public static WebDriver driver;

	@BeforeMethod(alwaysRun=true)
	public static void setUp() {
		
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		String browser = ConfigsReader.getProperty("browser");


		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			//We could use chrome as a case to cover the possibility of no match
			//driver = new ChromeDriver();
			break;

		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
		String url = ConfigsReader.getProperty("url");
		driver.get(url);
	}
	
	@AfterMethod(alwaysRun=true)
	public static void tearDown()
	{
		if(driver != null)
		{
			driver.quit();
		}
	}

}
