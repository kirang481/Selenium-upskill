//test case RETC_048
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
import com.training.pom.W2_AddNewRegnPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W2_AddNewRegnTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W2_AddNewRegnPOM w2_AddNewRegnPOM;
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
		w2_AddNewRegnPOM = new W2_AddNewRegnPOM(driver);
		// open the browser 
		driver.get(baseUrl);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}

	@Test (priority=0)
	public void validLoginTest() {
		//admin log in
		loginRealEstatePOM.sendUserName("admin");
		loginRealEstatePOM.sendPassword("admin@123");
		loginRealEstatePOM.clickLoginBtn(); 
		screenShot.captureScreenShot("LoginforNewProp");
	}

	@Test (priority=1)
	public void publishPropTest() throws InterruptedException {
		//Creating new property by creating new region
		w2_AddNewRegnPOM.clickProperty();
		w2_AddNewRegnPOM.clickAddNew();
		w2_AddNewRegnPOM.clickAddNewReg();
		w2_AddNewRegnPOM.addNewRegText("Elec4City");
		w2_AddNewRegnPOM.selectRegDropFun();
		w2_AddNewRegnPOM.addNewRegFun();
		//refreshing webpage after adding region to make it visible in list
		driver.navigate().refresh();
		w2_AddNewRegnPOM.addTitleProp("prestige");
		w2_AddNewRegnPOM.clickTextBtn();
		w2_AddNewRegnPOM.addDescProp("home town");
		w2_AddNewRegnPOM.moveOverAddReg();
		//Scroll up to make publish button visible
		((JavascriptExecutor)driver).executeScript("scroll(0,-500)");
		Thread.sleep(2000);
		w2_AddNewRegnPOM.clickPublish();
		screenShot.captureScreenShot("New Property with New Region");
	}
	
	@Test (priority=2)
	public void textValidation() {
		//text validation
		String expected = "Post published. View post"; 
		String actual = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		Assert.assertEquals(expected, actual);
	}
}