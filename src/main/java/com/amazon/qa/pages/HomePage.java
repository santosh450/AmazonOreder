package com.amazon.qa.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class HomePage extends TestBase{
	
	// Page Factory - Object Repository (OR)
	
	@FindBy(id="nav-hamburger-menu")
	WebElement OpenMenu;
	
	@FindBy(xpath="//ul//li//a//div[contains(text(),'Mobiles, Computers')]")
	WebElement ParentMenu;
	
	@FindBy(xpath="//li//a//div[contains(text(),'Laptops')]")
	WebElement ChildMenu;
	
	@FindBy(xpath="//label//span//span[contains(text(),'Apple')]")
	WebElement AppleItem;
	
	
	@FindAll({@FindBy(xpath="//span[contains(@aria-label,'5 stars')]")})
	List<WebElement> AllRatings;
	

	@FindAll({@FindBy(xpath="//span[@class='a-price-whole']")})
	List<WebElement> AllPrices;
	
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	
	//Initializing Page Objects
	public HomePage() throws IOException, EncryptedDocumentException, InvalidFormatException {
		PageFactory.initElements(driver, this);
	}
	
	public void chooseAppleProduct() throws Exception{
		OpenMenu.click();
		ParentMenu.click();
		Actions act = new Actions(driver);
		act.moveToElement(ChildMenu).perform();
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		ChildMenu.click();
		AppleItem.click();
	}
	
	public ProductPage chooseBestOne() throws IOException{
		ArrayList<String> priceList = new ArrayList<String>();
		ArrayList<String> ratingList = new ArrayList<String>();
		ArrayList<Integer> priceList_5Stars = new ArrayList<Integer>();
		System.out.println(AllPrices.size());
		System.out.println(AllRatings.size());
		for(int i=0;i<AllPrices.size();i++){
			priceList.add(AllPrices.get(i).getText());
		}
		for(int i=0;i<AllRatings.size();i++){
			ratingList.add(AllRatings.get(i).getAttribute("aria-label").toString());
		}
		Collections.sort(priceList);
		Collections.sort(ratingList);
		System.out.println(priceList);
		System.out.println(ratingList);
		
		for(int i=0;i<AllRatings.size();i++){
			if(AllRatings.get(i).getAttribute("aria-label").toString().equals("5.0 out of 5 stars")){
				String s = AllPrices.get(i).getText();
				s = s.replaceAll(",","");
				int rup = Integer.parseInt(s);
				priceList_5Stars.add(rup);
			}
		}
		Collections.sort(priceList_5Stars);
		int max_rup = priceList_5Stars.get(priceList_5Stars.size()-1);
		System.out.println(priceList_5Stars);
		for(int i=0;i<AllRatings.size();i++){
			String s = AllPrices.get(i).getText();
			s = s.replaceAll(",","");
			int rup = Integer.parseInt(s);
			if(AllRatings.get(i).getAttribute("aria-label").toString().equals("5.0 out of 5 stars") && rup==max_rup){
				//AllPrices.get(i).click();
				jse.executeScript("arguments[0].click();", AllPrices.get(i));
			}
		}
		return new ProductPage();
	}
}
