//test case RETC_018
//To verify whether application allows admin to add new category
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
import com.training.pom.AddNewCatPOM;
import com.training.pom.LoginRealEstatePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddNewCatTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static AddNewCatPOM addNewCatPOM;
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
		addNewCatPOM = new AddNewCatPOM(driver);
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
		screenShot.captureScreenShot("Loginfor NewCat");
	}

	@Test (priority=1)
	public void publishCatgTest() {
		//Creates new category
		addNewCatPOM.clickPosts();
		addNewCatPOM.addNewFtn();
		addNewCatPOM.newName("New Launches");
		addNewCatPOM.newSlag("launch");
		addNewCatPOM.newDescr("New Launches of vilas, apartments, flats");
		addNewCatPOM.subFtn();
		driver.navigate().refresh();
		screenShot.captureScreenShot("New Category");
	}
	
	@Test (priority=2)
	public void catgValidation() {
		//Validates the message after creating new category
		String expected = "New Launches";
		String actual = driver.findElement(By.linkText("New Launches")).getText();
		Assert.assertEquals(actual, expected);
	}  
}