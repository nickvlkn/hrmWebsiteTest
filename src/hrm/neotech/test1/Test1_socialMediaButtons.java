package hrm.neotech.test1;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class Test1_socialMediaButtons     {

	public void SocialMediaLinks(WebDriver driver) throws InterruptedException {
		//d-Confirm that the social media buttons in the bottom-right corner are displayed properly and link to the correct platforms.

		// Define the XPath locators for social media icons
		String[] socialMediaXpaths = {
		    "//a[@class='linkedIn']/img",
		    "//a[@class='facebook']/img",
		    "//a[@class='twitter']/img",
		    "//a[@class='youtube']/img"
		};
		// Loop through each icon, click it, and switch to its tab
		for (String xpath : socialMediaXpaths) {
		    WebElement icon = driver.findElement(By.xpath(xpath));
		    icon.click();
		    // Handle new tabs/windows
		    Set<String> handles = driver.getWindowHandles();
		    Iterator<String> it = handles.iterator();
		    it.next(); // Skip the main window
		    driver.switchTo().window(it.next()); // Switch to the new tab
		    Thread.sleep(500);
		    System.out.println(driver.getTitle()); // Print the title of the new tab
		    Thread.sleep(2000);
		    driver.close(); // Close the new tab
		    driver.switchTo().window(handles.iterator().next()); // Switch back to the main window
		}
		 Thread.sleep(2000);
	 
	}
}
