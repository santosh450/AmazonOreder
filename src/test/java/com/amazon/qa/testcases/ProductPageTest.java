package com.amazon.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.ProductPage;
import com.amazon.qa.util.TestUtil;

public class ProductPageTest extends TestBase{
	
	HomePage home ;
	ProductPage product;
	public ProductPageTest() throws IOException {
		super();
	}
	
	@BeforeClass
	public void setup() throws Exception{
		home = new HomePage();
		product = new ProductPage();
		home.chooseAppleProduct();
		product = home.chooseBestOne();
	}
	
	@AfterClass
	public void CloseBrowswe(){	
		//tearDown();
	}
	
	@Test (priority=1)
	public void ValidatePageTitle() {
		String title = TestUtil.getPageTitle(driver);
		Assert.assertEquals(title, "Apple Laptops: Buy Apple Laptops online at best prices in India - Amazon.in");
	}
	
	@Test (priority=2)
	public void ValidatePageURL() {
		String url = TestUtil.getPageURL(driver);
		Assert.assertEquals(url, "[https://www.amazon.in/gp/huc/view.html?ie=UTF8&newItems=C6bf35216-73f9-4e08-91d4-33657096de7f%2C3");
	}
	
}
