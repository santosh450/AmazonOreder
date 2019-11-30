package com.amazon.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class CheckoutPage extends TestBase{

	
	// Page Factory - Object Repository (OR)
	
	@FindBy(partialLinkText="Proceed to Buy")
	WebElement checkOut;
	
	@FindBy(partialLinkText="Deliver to this address")
	WebElement DeliveryToThis;
	
	@FindBy(xpath="//input[@value='Continue' and @class='a-button-text']")
	WebElement continueThis;
	
	@FindBy(xpath="//input[@value='SelectableAddCreditCard']")
	WebElement ChooseDebit;
	
	@FindBy(name="addCreditCardNumber")
	WebElement AddCrediCardNumber;
	
	@FindBy(xpath="//a[@id='nav-cart']")
	WebElement Cart;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement Delete;
	
	@FindBy(xpath="//span[text()='Account & Lists']")
	WebElement SignOutOpt;
	
	@FindBy(xpath="//span[text()='Sign Out']")
	WebElement SignOut;
	
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	
	//Initializing Page Objects
	public CheckoutPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public void MakePayment() {
	
		checkOut.click();
		DeliveryToThis.click();
		continueThis.click();
		ChooseDebit.click();
		AddCrediCardNumber.sendKeys("123412341234");
		driver.close();
		driver.switchTo().window(ProductPage.parent_win);
		Cart.click();
		Delete.click();
		Actions act = new Actions(driver);
		act.moveToElement(SignOutOpt).moveToElement(SignOut).click().build().perform();
		driver.navigate().to(System.getProperty("user.dir")+"/test-output/index.html#");
		driver.navigate().refresh();
		driver.findElement(By.id("firstname-placeholder")).sendKeys(Keys.F5);
	}

}
