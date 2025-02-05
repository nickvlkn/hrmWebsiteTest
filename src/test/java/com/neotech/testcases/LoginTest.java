package com.neotech.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;
import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class LoginTest extends CommonMethods {

	@Test(groups="smoke")
	public void validLogin() {

		LoginPageElements loginPage = new LoginPageElements();
		DashboardPageElements dashboard = new DashboardPageElements();

		// send username
		sendText(loginPage.username, ConfigsReader.getProperty("username"));

		// send password
		sendText(loginPage.password, ConfigsReader.getProperty("password"));

		// click on login button
		click(loginPage.loginBtn);
		// Or use jsClick() or Actions.click()

		wait(1);

		// Verify the account name
		String expected = "Jacqueline White";
		String actual = dashboard.accountName.getText();

		// Assertion
		Assert.assertEquals(actual, expected, "The account does NOT match!!!");

	}

	@Test(groups={"smoke" ,"regression"})
	public void emptyPasswordLogin() {

		LoginPageElements loginPage = new LoginPageElements();

		sendText(loginPage.username, ConfigsReader.getProperty("username"));
		click(loginPage.loginBtn);
		
		String expected = "Password cannot be empty";
		String actual = loginPage.passwordError.getText();

		Assert.assertEquals(actual, expected, "Error messages does NOT match!");

	}
	@Test(groups="regression")
	public void invalidPassword() {
		
		LoginPageElements loginPage = new LoginPageElements();
		
		sendText(loginPage.username, ConfigsReader.getProperty("username"));
		sendText(loginPage.password, "wrongPass123");
		click(loginPage.loginBtn);
		
		String expected = "Invalid Credentials...";
		String actual = loginPage.invalidMsg.getText();
		
		Assert.assertEquals(actual, expected, "Error messages does NOT match!");
		
		
	}

}
