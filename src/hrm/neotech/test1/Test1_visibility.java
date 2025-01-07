package hrm.neotech.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hrm.neotech.utils.BaseClass;

public class Test1_visibility extends BaseClass{
	
	public void Visibility(WebDriver driver) throws InterruptedException {
		//a-Verify that the company header is displayed correctly on the login page.
		WebElement header=driver.findElement(By.xpath("//div[@class='organization-logo shadow']"));
		WebElement userName=driver.findElement(By.id("txtUsername"));
		WebElement password=driver.findElement(By.id("txtPassword"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
	
		//Header 
		if(header.isDisplayed()) 
		System.out.println("PASS: header are visible. "+ header.isDisplayed());
		else 
	    System.out.println("FAIL: header not visible. "+ header.isDisplayed());

		//User name and password
		if(userName.isDisplayed()&&password.isDisplayed()) 
			System.out.println("PASS: Username and Password fields are visible.");
		else 
			System.out.println("FAIL: One or both fields are not visible.");
		
		//Login Button 
		if (loginButton.isDisplayed()) 
			System.out.println("PASS: Login button are visible.");
		else 
			System.out.println("FAIL: Login button are not visible.");
		Thread.sleep(500);
	 
		//Ensure sensitive input fields (like the password box) mask the input.	
		userName.sendKeys("userName");
		password.sendKeys("passord");
		String inputType = password.getAttribute("type");
		if(inputType.equals("password")) 
			System.out.println("PASS: Password is masking");
		else
			System.out.println("FAIL: Password is not masking");
		//Clear
		userName.clear();
		password.clear();
		
	    Thread.sleep(500);
	}

 

}
