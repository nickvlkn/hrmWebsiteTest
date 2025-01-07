package hrm.neotech.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hrm.neotech.utils.BaseClass;
import hrm.neotech.utils.ConfigsReader;

public class Test1_loginInvalidValue extends BaseClass {
	
	public void LoginInvalidValue (WebDriver driver)throws InterruptedException {
 
		Thread.sleep(1000);
		//Login Orage Hrm
		//User name: Admin 
		//Password : Neotech@123
			WebElement userName=driver.findElement(By.id("txtUsername"));
			WebElement password=driver.findElement(By.id("txtPassword"));
			
			//send empty value
			userName.sendKeys("");
			password.sendKeys("");
			WebElement button =driver.findElement(By.xpath("//button"));
			button.click();
			WebElement usernameError=driver.findElement(By.id("txtUsername-error"));
			WebElement passwordError=driver.findElement(By.id("txtPassword-error"));
			if(usernameError.isDisplayed()&&passwordError.isDisplayed()) {
				System.out.println("PASS: Username and Password cannot be empty, error displayed");
			}else
				System.out.println("FAIL: Username and Password cannot be empty, error NOT displayed");
			//Clear
			userName.clear();
			password.clear();
			//send wrong value
			userName.sendKeys("WrongUsername");
			password.sendKeys("WrongPassword");
			button.click();
			Thread.sleep(1000);
			
			String invalidText = driver.findElement(By.xpath("//div[@class='toast-message']")).getText();
			if (invalidText.equals("Invalid Credentials"))
			System.out.println("PASS: Invalid Credentials warning message displayed ");
			else 
			System.out.println("FAIL: Invalid Credentials warning message NOT displayed  ");
			
			Thread.sleep(2000);
			
			driver.navigate().to(ConfigsReader.getProperty("url"));
			Thread.sleep(3000);
			WebElement userName2=driver.findElement(By.id("txtUsername"));
			WebElement password2=driver.findElement(By.id("txtPassword"));
				
		
			//Login with valid values
			userName2.sendKeys(ConfigsReader.getProperty("username"));
			password2.sendKeys(ConfigsReader.getProperty("password"));
			driver.findElement(By.xpath("//button")).click();
			
			WebElement getAccountName=driver.findElement(By.id("account-name"));
			String name = getAccountName.getText();		
			System.out.println("PASS: Login succes. Welcome " + name );
		
		Thread.sleep(1000);
	 
	}
}
