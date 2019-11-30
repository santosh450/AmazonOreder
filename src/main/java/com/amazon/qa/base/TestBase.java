package com.amazon.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.amazon.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public TestBase() throws IOException{
		prop = new Properties();
		//FileInputStream fip = new FileInputStream("C:/Users/Santosh/workspace/AmazonOreder/src/main/java/com/amazon/qa/config/config.properties");
		FileInputStream fip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/amazon/qa/config/config.properties");
		prop.load(fip);
	}
	public void Intialization() throws IOException{
		if(prop.getProperty("browser").equals("chrome")){
			//System.setProperty("webdriver.chrome.driver","Z:/Study/Java/Selenium/chromedriver_win32/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(prop.getProperty("browser").equals("firefox")){
			//System.setProperty("webdriver.gecko.driver", "Z:/Study/Java/Selenium/geckodriver-v0.24.0-win64/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new  FirefoxDriver();
		}
		else if(prop.getProperty("browser").equals("IE")){
			//System.setProperty("webdriver.ie.driver", "Z:/Study/Java/Selenium/IEDriverServer_x64_3.4.0/MicrosoftWebDriver.exe");
			//WebDriverManager.edgedriver().setup(); 
			WebDriverManager.iedriver().setup(); 
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITY_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	public void tearDown(){
		driver.close();
	}

}
