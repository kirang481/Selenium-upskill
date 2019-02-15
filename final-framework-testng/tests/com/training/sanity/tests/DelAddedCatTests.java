//test case RETC_019
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.DelAddedCatPOM;
import com.training.pom.LoginRealEstatePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class DelAddedCatTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static DelAddedCatPOM delAddedCatPOM;
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
		delAddedCatPOM = new DelAddedCatPOM(driver);
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
		loginRealEstatePOM.sendUserName("admin");
		loginRealEstatePOM.sendPassword("admin@123");
		loginRealEstatePOM.clickLoginBtn(); 
		screenShot.captureScreenShot("Loginfor DelCat");
	}
	
	@Test (priority=1)
	public void deleteCatgTest() {
		delAddedCatPOM.clickPosts();
		delAddedCatPOM.catClickFtn();
		delAddedCatPOM.selChbxFtn();
		delAddedCatPOM.listBox();
		delAddedCatPOM.deleteAction();
		screenShot.captureScreenShot("Delete Category");
	}
	
	@Test (priority=2)
	public void textValidation() {
		String expected = "Categories deleted.";
		String actual = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		Assert.assertEquals(expected, actual);
	}  
}