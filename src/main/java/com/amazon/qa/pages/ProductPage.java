package com.amazon.qa.pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.util.WriteXML;

public class ProductPage extends TestBase{

	
	// Page Factory - Object Repository (OR)
	
	@FindBy(xpath="//select[@id='quantity']")
	WebElement quantity;
	
	@FindAll({@FindBy(xpath="//div[@id='feature-bullets']//span")})
	List<WebElement> ProcuctDetails;
	
	@FindBy(xpath="//a[@id='nav-link-accountList']//span[@class='nav-line-1']")
	WebElement UserName;
	
	@FindBy(xpath="//span[text()='Show More']")
	WebElement showMore;
	
	@FindBy(id="add-to-cart-button")
	WebElement addToCart;
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	static String parent_win;
	
	//Initializing Page Objects
	public ProductPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public CheckoutPage addProductToCart() throws Exception{
		Set<String> hands = driver.getWindowHandles();
		parent_win = driver.getWindowHandle();
		Iterator<String> itr = hands.iterator();
		while(itr.hasNext()){
			String child_win = itr.next();
			if(! child_win.equals(parent_win)){
				driver.switchTo().window(child_win);
			}
		}
		System.out.println(driver.getCurrentUrl());
		Select sel = new Select(quantity);
		sel.selectByVisibleText("3");
		
		showMore.click();
		
		System.out.println("Product details:");
		String[] products = new String[ProcuctDetails.size()];
		for(int i=0;i<ProcuctDetails.size();i++){
			products[i]=ProcuctDetails.get(i).getText();
		}
		String name = UserName.getText().toString();
		String[] names = name.split(" ");
		WriteXML.WriteDataToExcel(names[1], products);
		
		addToCart.click();
		return new CheckoutPage();
	}

}
