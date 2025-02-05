package com.neotech.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.neotech.testbase.BaseClass;

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

	/**
	 * This method will create and return a JavascriptExecutor object.
	 * 
	 */
	public static JavascriptExecutor getJSObject()
	{
		return (JavascriptExecutor) driver;
		
		/*
		 The line above is the same as this: 
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 
		 return js;
		 
		 */
	}
	
	
	
	/**
	 * This method will click on a given element using JavasciptExecutor
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element)
	{

		getJSObject().executeScript("arguments[0].click()", element);
	}
	
	/**
	 * This method will scroll the page until a given element is in view.
	 * 
	 * @param element
	 */
	public static void scrollToElement(WebElement element)
	{
		getJSObject().executeScript("arguments[0].scrollIntoView(true)",element);
	}
	
	/**
	 * This method scrolls the page up based on the pixel parameter provided
	 * 
	 * @param pixel
	 */
	public static void scrollUp(int pixel)
	{
		getJSObject().executeScript("window.scrollBy(0, -"+ pixel+")");
	}
	
	
	/**
	 * This method scrolls the page down based on the pixel parameter provided
	 * 
	 * @param pixel
	 */
	public static void scrollDown(int pixel)
	{
		getJSObject().executeScript("window.scrollBy(0, "+ pixel+")");
	}
	
	
	/**
	 * This method will select a day from a calendar given the days as a List<WebElement> and the 
	 * date to select.
	 * 
	 * @param elements
	 * @param date
	 */
	public static void selectCalendarDate(List<WebElement> elements, String date)
	{
		
		for (WebElement day : elements)
		{
			String dayNum = day.getText();
			
			if (dayNum.equals(date))
			{
				if (day.isEnabled())
				{
					day.click();
					break;
				}
				else
				{
					System.out.println("This day is not enabled!");
					break;
				}
			}
		}
		
		
	}
	
	/**
	 * This method takes a screenshot and saves it with the given fileName
	 * 
	 * @param fileName
	 */
	public static void takeScreenshot(String fileName)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File screenShot = ts.getScreenshotAs(OutputType.FILE);
		
		try {
			Files.copy(screenShot, new File("screenshots/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
