//test case RETC_046
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.W2_AddNewPropPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W2_AddNewPropTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W2_AddNewPropPOM w2_AddNewPropPOM;
	private static Properties properties;
	private static ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		//Set up commands
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		loginRealEstatePOM = new LoginRealEstatePOM(driver);
		w2_AddNewPropPOM = new W2_AddNewPropPOM(driver);
		// open the browser 
		driver.get(baseUrl);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test (priority=0)
	public void validLoginTest() {
		//Admin log in
		loginRealEstatePOM.sendUserName("admin");
		loginRealEstatePOM.sendPassword("admin@123");
		loginRealEstatePOM.clickLoginBtn(); 
		screenShot.captureScreenShot("LoginforNewProp");
	}

	@Test (priority=1)
	public void publishPropTest() throws InterruptedException {
		//Creating property
		w2_AddNewPropPOM.clickProperty();
		w2_AddNewPropPOM.clickAddNew();
		w2_AddNewPropPOM.addTitleProp("prestige");
		w2_AddNewPropPOM.clickTextBtn();
		w2_AddNewPropPOM.addDescProp("home town");
		w2_AddNewPropPOM.clickFeatures();
		w2_AddNewPropPOM.clickRegions();
		//Scrolling up in webpage to make publish button visible
		((JavascriptExecutor)driver).executeScript("scroll(0,-500)");
		Thread.sleep(2000);
		w2_AddNewPropPOM.clickPublish();
		screenShot.captureScreenShot("New Property");
	}
	
	@Test (priority=2)
	public void textValidation() {
		//Text validation
		String expected = "Post published. View post"; 
		String actual = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		Assert.assertEquals(expected, actual);
	}
}