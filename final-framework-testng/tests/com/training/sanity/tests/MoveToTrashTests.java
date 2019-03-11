//test case RETC_016
//To verify whether application allows  admin to add the post into trash
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
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.MoveToTrashPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class MoveToTrashTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static MoveToTrashPOM moveToTrashPOM;
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
		moveToTrashPOM = new MoveToTrashPOM(driver);
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
		//admin log in
		loginRealEstatePOM.sendUserName("admin");
		loginRealEstatePOM.sendPassword("admin@123");
		loginRealEstatePOM.clickLoginBtn(); 
		screenShot.captureScreenShot("Login");
	}

	@Test (priority=1)
	public void deletePosts() {
		//Delete the posts
		moveToTrashPOM.clickPosts();
		moveToTrashPOM.clickAllPosts();
		moveToTrashPOM.beforeDisplayMtd();
		//Counting number of items before trash movement
		moveToTrashPOM.validationItems();
		//Mouse over an item method
		moveToTrashPOM.mouseOverPost();
		moveToTrashPOM.moveTrash();
		moveToTrashPOM.afterDisplayMtd();
		//Counting number of items after trash movement
		moveToTrashPOM.validationItems();
		screenShot.captureScreenShot("Trash move");
	}
	@Test (priority=2)
	public void textValidation() {
		//text validation
		String actual = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		String expected = "1 post moved to the Trash. Undo";
		Assert.assertEquals(expected, actual);
	}
}