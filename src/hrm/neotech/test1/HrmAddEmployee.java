//1. Open Chrome browser
//2. Go to "https://hrm.neotechacademy.com/"
//3. Login into the application
//4. Click on PIM > Add Employee
//5. Add Employee
//6. Log out
//7. Quit the browser

package hrm.neotech.test1;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import hrm.neotech.utils.BaseClass;
import hrm.neotech.utils.ConfigsReader;

public class HrmAddEmployee extends BaseClass {
	
	public static void login(WebDriver driver){
		//3. Login into the application
		driver.findElement(By.id("txtUsername")).sendKeys(ConfigsReader.getProperty("username"));
		driver.findElement(By.id("txtPassword")).sendKeys(ConfigsReader.getProperty("password"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
    public static WebElement waitForElement(WebDriver driver, By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))  // Maximum wait time
                .pollingEvery(Duration.ofSeconds(1)) // Polling interval
                .ignoring(org.openqa.selenium.ElementNotInteractableException.class)
                .ignoring(Exception.class); // Ignore   exceptions

        return wait.until(driverInstance -> driverInstance.findElement(locator));
    }
	public static void main(String[] args) throws InterruptedException {
		setUp();
		//URL: 	https://hrm.neotechacademy.com/
		login(driver);
		WebDriverWait WebDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		//4. Click on PIM > Add Employee
		WebElement buttonPim =waitForElement(driver, (By.linkText("PIM")));
		buttonPim.click();
		
		WebElement buttonAddEmployee= WebDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("menu_pim_addEmployee"))));
		buttonAddEmployee.click();
		
		//5. Add Employee
		//FluentWait
		WebElement firstNameTextbox = waitForElement(driver, (By.xpath("//input[@id='first-name-box']")));
		firstNameTextbox.sendKeys("Alex");
		WebElement lastNameTextbox = waitForElement(driver, (By.xpath("//input[@id='last-name-box']")));
		lastNameTextbox.sendKeys("Harry");
		
		driver.findElement(By.xpath("//div[@class='input-group-append']")).click();
		//ASK QUESTION if i wanna choose New York Sales Office giving Exception. (Because end of the list i have to get all list or scroll down)
		List<WebElement> elementList2 = driver.findElements(By.xpath("//ul[contains(@class, 'dropdown-menu')]//li//a//span[text()='Canadian Development Center']"));
		if (!elementList2.isEmpty()) {
		    elementList2.get(0).click();
		}
		WebElement saveButton =driver.findElement(By.id("modal-save-button"));
		saveButton.click();
		
		//Check Employee 
		WebDriverWait.until(ExpectedConditions.visibilityOfElementLocated((By.id("pim.navbar.employeeName"))));
		WebElement newEmpName=driver.findElement(By.id("pim.navbar.employeeName"));
		String getEmployeName =newEmpName.getText();
		if (getEmployeName.equals(driver.findElement(By.id("pim.navbar.employeeName")).getText()))  
		{
			System.out.println("Adding Employee successful");
			System.out.println("Hello, "+newEmpName.getText());
		}
		else System.out.println("Adding Employee Unsuccessful");
		
		//Logout
		WebElement logoutDD =waitForElement(driver, (By.xpath("//a[@id='user-dropdown']")));
		logoutDD.click();
		WebDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("logoutLink")));
		WebElement logout =driver.findElement(By.id("logoutLink"));
		if(logout.isDisplayed()){
			logout.click();
			System.out.println("Logout Seccessful.."); }
		else System.out.println("Error");
		Thread.sleep(5000);	//Just for last view
		tearDown();
	}
}
