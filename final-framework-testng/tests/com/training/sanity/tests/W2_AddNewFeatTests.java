//test case RETC_047
//To verify whether application allows admin to add new Feature while adding new property
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
import com.training.pom.W2_AddNewFeatPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W2_AddNewFeatTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W2_AddNewFeatPOM w2_AddNewFeatPOM;
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
		w2_AddNewFeatPOM = new W2_AddNewFeatPOM(driver);
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
		//Creating new post and selecting created feature
		w2_AddNewFeatPOM.clickProperty();
		w2_AddNewFeatPOM.clickAddNew();
		w2_AddNewFeatPOM.clickAddNewFeat();
		w2_AddNewFeatPOM.addNewFetText("BestFrame");
		w2_AddNewFeatPOM.selectFetDropFun();
		w2_AddNewFeatPOM.addNewFetFun();
		//refreshing webpage after adding feature to make it visible in list
		driver.navigate().refresh();
		w2_AddNewFeatPOM.addTitleProp("prestige");
		w2_AddNewFeatPOM.clickTextBtn();
		w2_AddNewFeatPOM.addDescProp("home town");
		w2_AddNewFeatPOM.moveOverAddFet();
		w2_AddNewFeatPOM.clickPublish();
		screenShot.captureScreenShot("New Property with New Function");
	}
	
	@Test (priority=2)
	public void textValidation() {
		//Text validation
		String expected = "Post published. View post"; 
		String actual = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		Assert.assertEquals(expected, actual);
	}
}