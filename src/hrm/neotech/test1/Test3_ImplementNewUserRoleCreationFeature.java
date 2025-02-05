/* *
 
 Description

Pre-requisites:
Ensure that the new user is created per HRM7-2: Implement Add New Employee Functionality
Done
 . This is a necessary step before viewing a new employee in directory page.

User Interface :
Add a new "+” button on the top right of the Users page, accessible under Admin > User Management > Users in the left navigation menu. This button will trigger the display of a modal for creating a new user role.

Modal Dialog Implementation:
Implement a modal dialog that opens upon clicking the "+" button. The modal should collect the following information from the admin:
Employee Name: A mandatory field that concatenates the first and last name of the employee (e.g., <FirstName and LastName> John Doe).
Username: A mandatory field automatically generated from the employee's first and last name in lowercase (e.g., <firstnamelastname>).
ESS Role: A dropdown with "Default ESS" pre-selected.
Supervisor Role: A dropdown with "Default Supervisor" pre-selected.
Admin Role: A dropdown option that allows selection of "Global Admin".
Status: A toggle or dropdown to enable or disable the user, with "Enabled" as the default state.
Password: A mandatory field with a predefined default password (e.g., Neotech@2023).

Testing Criteria:
Verify that the new user roles are correctly added to the system and that they have the expected permissions based on the assigned roles.
 
 * */
package hrm.neotech.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import hrm.neotech.utils.CommonMethods;
import hrm.neotech.utils.ConfigsReader;

public class Test3_ImplementNewUserRoleCreationFeature extends CommonMethods {

	public static void main(String[] args) {
		setUp();
		// URL: https://hrm.neotechacademy.com

		loginHRM(driver);
		click(driver.findElement(By.xpath("//span[text()='Admin']")));
		click(driver.findElement(By.xpath("//span[text()='User Management']")));
		click(driver.findElement(By.xpath("//span[text()='Users']")));
		click(driver.findElement(By.xpath("//i[normalize-space()=\"add\"]")));
		// Add User Form
		// wait
		waitForVisibility(By.xpath("//div[@class=\"modal-content\"]"));
		// Full up
		// Employee name
		String fullName = ConfigsReader.getProperty("firstname") + " " + ConfigsReader.getProperty("lastname");
		WebElement employeeName = driver.findElement(By.id("selectedEmployee_value"));
		sendText(employeeName, fullName);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		// Username expect : <firstnamelastname>
		WebElement username = driver.findElement(By.id("user_name"));
		String formattedUserName = fullName.replaceAll("\\s+", "").toLowerCase(); // This will print 'firstnamelastname'
		
		if (username.getDomProperty("value").equals(formattedUserName)) {
				System.out.println("Username: auto generated.");
		}
		else
			System.out.println("FAIL: Username not auto generated.");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		WebElement essRoleDropdown=driver.findElement(By.xpath("//oxd-decorator[2]//div[1]//div[1]//div[1]//div[2]//div[1]//button[1]"));
		// Create a Select object
        Select select = new Select(essRoleDropdown);

        // Get the currently selected option
        WebElement selectedOption = select.getFirstSelectedOption();

        // Get the text of the selected option
        String selectedText = selectedOption.getText();

        // Print the selected option text
        System.out.println("Pre-selected option: " + selectedText);
		wait(3);
		tearDown();
	}

}
