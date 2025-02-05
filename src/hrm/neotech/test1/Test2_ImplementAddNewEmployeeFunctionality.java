//1. Open Chrome browser
//2. Go to "https://hrm.neotechacademy.com/"
//3. Login into the application
//4. Click on PIM > Add Employee
//5. Add Employee
//6. Log out
//7. Quit the browser

package hrm.neotech.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import hrm.neotech.utils.CommonMethods;
import hrm.neotech.utils.ConfigsReader;

public class Test2_ImplementAddNewEmployeeFunctionality extends CommonMethods {
	
	public static void main(String[] args) throws InterruptedException {
		setUp();
		//URL: 	https://hrm.neotechacademy.com/
		loginHRM(driver);
		click(driver.findElement(By.linkText("PIM")));
		click(driver.findElement(By.xpath("//a[@id=\"menu_pim_addEmployee\"]")));
		
		waitForVisibility(By.cssSelector("#first-name-box"));
		sendText(driver.findElement(By.cssSelector("#first-name-box")), ConfigsReader.getProperty("firstname"));
		sendText(driver.findElement(By.cssSelector("#last-name-box")), ConfigsReader.getProperty("lastname"));
		selectDropdown(driver.findElement(By.id("location")), "New York Sales Office");
		// Add image
		String filePath = System.getProperty("user.dir") + "/screenshots/SC/spiderman.jpg";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Make the input element visible
		WebElement fileInput = driver.findElement(By.xpath("//input[@id='employeePicture']"));
		js.executeScript("arguments[0].style.display='block';", fileInput);
		sendText(fileInput, filePath);
		click(driver.findElement(By.cssSelector("#modal-save-button")));
		
		
		
		
		wait(5);
		tearDown();
	}
}
