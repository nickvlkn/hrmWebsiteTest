package neotech.practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hrm.neotech.utils.BaseClass;
import hrm.neotech.utils.ConfigsReader;

public class practice1 extends BaseClass {

	public static void login(WebDriver driver) {
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys(ConfigsReader.getProperty("username"));
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(ConfigsReader.getProperty("password"));
		driver.findElement(By.id("btnLogin")).click();

	}

	public static void allElements(WebDriver driver) {
		System.out.println("==All Elements=====================================");
		List<WebElement> allElement = driver.findElements(By.xpath("//table//tbody//tr//td"));
		for (int i = 0; i < allElement.size(); i++) {
			WebElement allElementVar = allElement.get(i);
			System.out.println(allElementVar.getText());

		}
	}

	public static void rows(WebDriver driver) {
		System.out.println("==Rows====================================");
		List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
		for (int i = 0; i < rows.size(); i++) {
			WebElement rowsVar = rows.get(i);
			System.out.println(rowsVar.getText());
		}
	}

	public static void names(WebDriver driver) {
		System.out.println("==Name=====================================");
		List<WebElement> name = driver.findElements(By.xpath("//table//tbody/tr/td[2]"));
		for (int i = 0; i < name.size(); i++) {
			WebElement nameVar = name.get(i);
			System.out.println(nameVar.getText());
		}
	}

	public static void paymentMethod(WebDriver driver) {
		System.out.println("==Payment Method=====================================");
		List<WebElement> paymentMethod = driver.findElements(By.xpath("//table//tbody/tr/td[10]"));
		for (int i = 0; i < paymentMethod.size(); i++) {
			WebElement patmentMethorVar = paymentMethod.get(i);
			System.out.println(patmentMethorVar.getText());
		}
	}

	public static void country(WebDriver driver) {
		System.out.println("==Country=====================================");
		List<WebElement> country = driver.findElements(By.xpath("//table//tbody/tr/td[8]"));
		for (int i = 0; i < country.size(); i++) {
			WebElement countryVar = country.get(i);
			System.out.println(countryVar.getText());
		}
	}

	public static void columns(WebDriver driver) {
		System.out.println("==Columns=====================================");
		List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));

			for (WebElement column : columns) {
				String columnsText = column.getText().trim();
				if (!columnsText.isEmpty()) {
					System.out.print(column.getText() + "\t"); // Tab-separated
				}
			}
			System.out.println();
		}
	}

	public static void headers(WebDriver driver) {
		System.out.println("==Headers=====================================");
		List<WebElement> headers = driver.findElements(By.xpath("//table//tr//th"));
		for (int i = 0; i < headers.size(); i++) {
			WebElement headersVar = headers.get(i);
			String headerText = headersVar.getText().trim();
			if (!headerText.isEmpty()) {
				System.out.println(headersVar.getText());
			}

		}
	}

	public static void main(String[] args) throws InterruptedException {
		setUp();
		// URL: https://neotech.vercel.app/web-orders
//		WebElement table=driver.findElement(By.id("ordersTable"));
		// 1) Open Chrome browser
		// 2) Go to "https://neotech.vercel.app/web-orders"
		// 3) Login to the application
		// 4) Find all MasterCard users then print names
		//		a-)print all Master card users all informations (rows)
		// 5) Change all MasterCard to VISA
		// 6) Print new list
		// 7) Quit the browser
		login(driver);
	
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement table = driver.findElement(By.id("ordersTable"));
		List<WebElement> rows = table.findElements(By.xpath("//table[@id='ordersTable']/tbody/tr"));
		Integer usersSize = 0;

		List<String> nameList = new ArrayList<>();

		for (int i = 0; i < rows.size(); i++) {
			WebElement rowsVar = rows.get(i);
			
			if (rowsVar.getText().contains("MasterCard")) {
				System.out.println(rowsVar.getText());
				
				//users names
				WebElement nameCell = rowsVar.findElement(By.xpath("td[2]"));
				String name = nameCell.getText().trim();
				
				//click edit
				WebElement edit = rowsVar.findElement(By.xpath("td[13]//a"));
				edit.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editFormDiv")));
				
				WebElement editPaymentMethod = driver.findElement(By.xpath("//div//input[@id='tb_paymentMethod']"));
				editPaymentMethod.clear();
				editPaymentMethod.sendKeys("VISA");
				
				WebElement saveButton=driver.findElement(By.id("btnSave"));
				saveButton.click();
				
				if(!name.isEmpty()) {
					nameList.add(name);
					usersSize++;
				}
			}
		}
		String[] namesArray = nameList.toArray(new String[0]);

		System.out.println();
		System.out.println("Find " + usersSize + " MasterCard users.");
		System.out.println();
		System.out.println("USERES NAMES:");
		for (String name : namesArray) {
			System.out.println(name);
		}
		System.out.println();
		System.out.println("====NEW LIST====");
		List<WebElement> updatedRows=table.findElements(By.xpath("//tbody/tr"));
		
	
		for (int i = 0; i < rows.size(); i++) {
			WebElement rowsVar = rows.get(i);
			if(!rowsVar.getText().contains("MasterCard")) {
				System.out.println(rowsVar.getText()+"\n");
			}
		}

		Thread.sleep(5000);
		tearDown();
	}
}
