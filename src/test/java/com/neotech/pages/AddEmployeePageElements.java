package com.neotech.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmployeePageElements {

	
	@FindBy(id = "photo-preview-lable")
	public WebElement employeePhoto;
	
	@FindBy(id = "first-name-box")
	public WebElement firtsName;

	@FindBy(id = "middle-name-box")
	public WebElement middleName;

	@FindBy(id = "last-name-box")
	public WebElement lastName;

	@FindBy(id = "employeeId")
	public WebElement employeeID;

	@FindBy(id = "location")
	public WebElement location;

	// Create Login Details
	@FindBy(xpath = "//span[@class='oxd-switch-container']/div")
	public WebElement createLoginDetails;

	@FindBy(xpath = "//input[@id='username']")
	public WebElement username;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;

	@FindBy(xpath = "//input[@id='confirmPassword']")
	public WebElement confirmPassword;

	// status Enabled - Disabled
	@FindBy(xpath = "//input[@id='status_Enabled']")
	public WebElement statusEnabled;

	@FindBy(xpath = "//input[@id='status_Disabled']")
	public WebElement statusDisabled;

	@FindBy(id = "modal-save-button")
	public WebElement saveButton;

	@FindBy(xpath = "//button[normalize-space()=\"Cancel\"]")
	public WebElement cancelButton;

}
