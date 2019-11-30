package com.amazon.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.LoginPage;
import com.amazon.qa.util.TestUtil;

public class LoginPageTest extends TestBase{
	
	LoginPage login;
	public LoginPageTest() throws IOException {
		super();
	}
	
	@BeforeSuite
	public void setup() throws IOException{
		Intialization();
		login = new LoginPage();
	}
	
	@AfterSuite
	public void CloseBrowswe(){	
		tearDown();
	}
	
	@Test (priority=1)
	public void ValidatePageTitle() {
		String title = TestUtil.getPageTitle(driver);
		Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}
	
	@Test (priority=2)
	public void ValidatePageURL() {
		String url = TestUtil.getPageURL(driver);
		Assert.assertEquals(url, "https://www.amazon.in/gp/huc/view.html?ie=UTF8&newItems=C6bf35216-73f9-4e08-91d4-33657096de7f%2C3");
	}
	
}
