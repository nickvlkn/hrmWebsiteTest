package hrm.neotech.test1;
import hrm.neotech.utils.BaseClass;

/*
 
  Test Scenarios for the Login Page
	1. UI Validation
	a-Verify that the company header is displayed correctly on the login page.
	b-Ensure the username and password text boxes are visible and functional.
	c-Check that the login button is present and clickable.
	d-Confirm that the social media buttons in the bottom-right corner are displayed properly and link to the correct platforms.
	
	2. Functional Tests
	a-Test logging in with valid credentials.
	b-Test logging in with invalid credentials (e.g., wrong username/password, empty fields).
	c-Verify behavior when clicking the login button without entering any data.
	
	3. Security Tests
	a-Ensure sensitive input fields (like the password box) mask the input.
	b-Validate error messages for incorrect or missing inputs (e.g., "Invalid Username or Password").
	
	4. Link Validation
	a-Test if the social media buttons redirect to the correct external pages.
	
	5. Performance Tests
	a-Measure how long it takes for the login page to load.
	b-Check response time after clicking the login button with valid credentials.
  
  
 */

public class Test1 extends BaseClass {

	public static void main(String[] args) throws InterruptedException {
		//https://hrm.neotechacademy.com/  
		//If web site asking CAPTCHA my code is stuck. //org.openqa.selenium.NoSuchElementException:
		setUp();
		 Thread.sleep(1000);
		 //Test Social Media Links 
		Test1_socialMediaButtons testerSocialMeida=new Test1_socialMediaButtons();
		testerSocialMeida.SocialMediaLinks(driver);
		
		//Test all element in the page visible
		Test1_visibility testerVisibility=new Test1_visibility();
		testerVisibility.Visibility(driver);
		
		
		//Login with Invalid Value
		Test1_loginInvalidValue testerLoginInvalidValue=new Test1_loginInvalidValue();
		testerLoginInvalidValue.LoginInvalidValue(driver);
	
		Thread.sleep(2000);
		tearDown();
	}
}
