package com.amazon.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class LoginPage extends TestBase{

	
	// Page Factory - Object Repository (OR)
	@FindBy(id="nav-link-accountList")
	WebElement SignInButton;
	
	@FindBy(name="email")
	WebElement LoginName;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	@FindBy(name="password")
	WebElement LoginPassword;
	
	@FindBy(id="signInSubmit")
	WebElement LoginSubmit;
		
	//Initializing Page Objects
	public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage enterLoginDetails() throws Exception{
		SignInButton.click();
		LoginName.sendKeys(prop.getProperty("username"));
		continueButton.click();
		LoginPassword.sendKeys(prop.getProperty("password"));
		LoginSubmit.click();
		return new HomePage();
	}
}
