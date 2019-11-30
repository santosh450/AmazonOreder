package com.amazon.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.amazon.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public TestUtil() throws IOException {
		super();
	}

	static public long PAGE_LOAD_TIMEOUT = 20;
	static public long IMPLICITY_WAIT = 10;
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("C:/Users/Santosh/workspace/FreeCrmPOM/src/ScreenShots/");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	public static String getPageTitle(WebDriver driver){
		return driver.getTitle();
	}
	
	public static String getPageURL(WebDriver driver){
		return driver.getCurrentUrl();
	}
}
