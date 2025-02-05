package hrm.neotech.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class CommonMethods extends BaseClass{
	/**
	 * This method clears a textbox and sends a text parameter to it.
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) 
	{
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * 
	 * @param elementList
	 * @param selectValue
	 * 
	 */
	public static void clickRadioOrCheckbox(List<WebElement> elementList, String selectValue)
	{
		for (WebElement el : elementList)
		{
			String elemntValue = el.getDomAttribute("value").trim();
			
			if (elemntValue.equals(selectValue) && el.isEnabled())
			{
				el.click();
				break;
			}
		}
	}
	
	/**
	 * This method pauses the execution for a certain amount of seconds.
	 * 
	 * @param seconds
	 */
	public static void wait(int seconds)
	{
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method selects a drop down element based on visible text.
	 * 
	 * @param element
	 * @param visibleText
	 */
	public static void selectDropdown(WebElement element, String visibleText) 
	{
		try 
		{
			Select sl = new Select(element);
			sl.selectByVisibleText(visibleText);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method selects a drop down element based on index.
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDropdown(WebElement element, int index) 
	{
		try 
		{
			Select sl = new Select(element);
			sl.selectByIndex(index);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches the focus of the driver to an Alert and accepts it if found.
	 * If not found, NoAlertPresentException is handled.
	 */
	public static void acceptAlert()
	{
		try 
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		catch (NoAlertPresentException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches the focus of the driver to an Alert and dismisses it if found.
	 * If not found, NoAlertPresentException is handled.
	 */
	public static void dismissAlert()
	{
		try 
		{
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}
		catch (NoAlertPresentException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches the focus of the driver to an Alert and returns its text if found.
	 * If not found, NoAlertPresentException is handled.
	 * 
	 * @return
	 */
	public static String getAlertText()
	{
		String alertText = null;
		
		try 
		{
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
		}
		catch (NoAlertPresentException e) 
		{
			e.printStackTrace();
		}
		
		return alertText;
	}
	
	/**
	 * This method switches the focus of the driver to an Alert and sends text to it if found.
	 * If not found, NoAlertPresentException is handled.
	 * 
	 * @return
	 */
	public static void sendAlertText(String text)
	{
		try 
		{
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
		}
		catch (NoAlertPresentException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method switches to a frame using its name or id.
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(String nameOrId)
	{
		try 
		{
			driver.switchTo().frame(nameOrId);
		}
		catch (NoSuchFrameException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches to a frame using its index.
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(int index)
	{
		try 
		{
			driver.switchTo().frame(index);
		}
		catch (NoSuchFrameException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches to a frame using a WebElement.
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(WebElement element)
	{
		try 
		{
			driver.switchTo().frame(element);
		}
		catch (NoSuchFrameException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches the focus of the driver to the child window.
	 */
	public static void switchToChildWindow()
	{
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		
		for (String handle : handles)
		{
			if (!mainWindow.equals(handle))
			{
				driver.switchTo().window(handle);
			}
		}
	}
	
	/**
	 * This method creates a wait object based on the driver and EXPLICIT_WAIT_TIME
	 * 
	 * @return
	 */
	public static WebDriverWait getWaitObject()
	{
		return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
	}
	
	/**
	 * This method will wait for the element to be visible.
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement waitForVisibility(By locator)
	{
		return getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * This method will wait for the element to be visible.
	 * 
	 * @param element 
	 * 
	 * @return
	 */
	public static WebElement waitForClickability(WebElement element)
	{
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will wait for the element to be clickable and then on click it.
	 * 
	 * @param element
	 */
	public static void click(WebElement element)
	{
		waitForClickability(element);
		element.click();
	}
	
	//Volkan Yildiz /////////////////////////

	/**
	 * This method taking a screenshot auto name.
	 * 
	 * Example : takeScreenshot(driver,"screenshot/HRM/screenshot.png");
	 * 
	 * What is Timestamp :
	 * If the file already exists, this line generates a timestamp that represents the current date and time. 
	 * It uses SimpleDateFormat to format the current date into a string that looks like yyyyMMdd_HHmmss. 
	 * For example: if the current date and time is January 16, 2025 at 10:30:45, the generated timestamp 
	 * would be: 20250116_103045
	 * 
	 * @param driver
	 * @param filePath
	 */
	public static void takeScreenshotWithTimestampGeneratedName(WebDriver driver, String filePath)  {

		//  Create an object of TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		//  Take the photo
		File source = ts.getScreenshotAs(OutputType.FILE);

		File destFile = new File(filePath);
		
		// Check the File exits
		if (destFile.exists()) {
			String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmmss").format(new Date());
			String newFilePath = filePath.substring(0, filePath.lastIndexOf(".")) + "_" + timeStamp+ filePath.substring(filePath.lastIndexOf("."));
			destFile = new File(newFilePath);

		}
		//Save the file 
	     try {
	    	 Files.copy(source, destFile);
	            System.out.println("Screenshot saved at: " + destFile.getPath());
	        } catch (IOException e) {
	            System.out.println("Failed to save screenshot: " + e.getMessage());
	        }
	}
	
	/**
	 * This method takes a screenshot and saves it with a unique name. If a file
	 * with the same name already exists in the folder, it appends a counter (1, 2,
	 * 3, 4, ...) to generate a new file name.
	 * 
	 * Example: takeScreenshotWithGeneratedName(driver,
	 * "screenshot/HRM/screenshot.png");
	 * DO NOT FORGET file extension (.png, .jpg, jpeg...)
	 * 
	 * @param driver   the WebDriver instance used to take the screenshot
	 * @param filePath the desired file path and name for saving the screenshot
	 */
	public static void takeScreenshotWithGeneratedName(WebDriver driver, String filePath) {

		// 1. Create an object of TakesScreenshot
		TakesScreenshot ts = (TakesScreenshot) driver;

		// 2. Take the photo
		File source = ts.getScreenshotAs(OutputType.FILE);

		File destFile = new File(filePath);

		// Counter for unique file names.
		int counter = 1;

		// Base Name: The part of the file path that represents the name of the file
		// without its extension.
		// Extension: The file extension (such as .png, .jpg, etc.), which is the part
		// that comes after the dot (.) in the file name.
		String baseName = filePath.substring(0, filePath.lastIndexOf("."));
		String extension = filePath.substring(filePath.lastIndexOf("."));

		// 3. Save the photo
		if (destFile.exists()) {
			destFile = new File(baseName + counter + extension);
			while (destFile.exists()) {
				counter++;
				destFile = new File(baseName + counter + extension);
			}
		}

		try {
			// Copy the screenshot to the destination file
			Files.copy(source, destFile);
			System.out.println("Screenshot saved at: " + destFile.getPath());
		} catch (IOException e) {
			System.out.println("Failed to save screenshot: " + e.getMessage());
		}
	}
	
	/**
	 * HRM login
	 * @param driver
	 * @param element
	 */
	public static void loginHRM(WebDriver driver) {
		WebElement userName=driver.findElement(By.id("txtUsername"));
		WebElement password=driver.findElement(By.id("txtPassword"));
		sendText(userName, ConfigsReader.getProperty("username"));
		sendText(password, ConfigsReader.getProperty("password"));
		WebElement button =driver.findElement(By.xpath("//button"));
		click(button);
		
	}
	
	//Volkan Yildiz /////////////////////////

	
	
	
	
	

}
