//test case RETC_020
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
import com.training.pom.AddNewTagPOM;
import com.training.pom.LoginRealEstatePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddNewTagTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static AddNewTagPOM addNewTagPOM;
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
		addNewTagPOM = new AddNewTagPOM(driver);
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
		screenShot.captureScreenShot("Loginfor NewTag");
	}

	@Test (priority=1)
	public void publishTagTest() {
		addNewTagPOM.clickPosts();
		addNewTagPOM.clickTag();
		addNewTagPOM.newName("New Launches");
		addNewTagPOM.newSlag("launch");
		addNewTagPOM.newDescr("New Launches of vilas, apartments, flats");
		addNewTagPOM.subFtn();
		driver.navigate().refresh();
		screenShot.captureScreenShot("New Tag");
	}
	
	@Test (priority=2)
	public void tagValidation() {
		String expected = "New Launches";
		String actual = driver.findElement(By.linkText("New Launches")).getText();
		Assert.assertEquals(actual, expected);
	}  
}