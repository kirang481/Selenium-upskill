//test case RETC_050
//To verify whether application allows admin to add property details into trash & display the same
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.W2_AddTrashPropPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W2_AddTrashPropTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W2_AddTrashPropPOM w2_AddTrashPropPOM;
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
		w2_AddTrashPropPOM = new W2_AddTrashPropPOM(driver);
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
		screenShot.captureScreenShot("LoginforTrashProp");
	}

	@Test (priority=1)
	public void publishPropTest() throws InterruptedException {
		//Creating property and moving to trash
		w2_AddTrashPropPOM.clickProperty();
		w2_AddTrashPropPOM.clickAddNew();
		w2_AddTrashPropPOM.addTitleProp("prestigeKiran");
		w2_AddTrashPropPOM.clickTextBtn();
		w2_AddTrashPropPOM.addDescProp("home town");
		w2_AddTrashPropPOM.clickFeatures();
		w2_AddTrashPropPOM.clickRegions();
		//Scroll up to make move to trash link visible
		((JavascriptExecutor)driver).executeScript("scroll(0,-500)");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		w2_AddTrashPropPOM.moveToTrash();
		//Accepting pop-up to confirm trash movement
		driver.switchTo().alert().accept();
		w2_AddTrashPropPOM.clickMoveTrash();
		//Finding element using search button in trash window
		w2_AddTrashPropPOM.searchText();
		w2_AddTrashPropPOM.clickSearch();
		screenShot.captureScreenShot("Property added in Trash");
	}
	
	@Test (priority=2)
	public void textValidation() {
		//Finding and validating element present in searchbox
		String expected = "prestigeKiran";
		String actual = driver.findElement(By.xpath("//strong[contains(text(),'prestigeKiran')]")).getText();
		Assert.assertEquals(expected, actual);
	}
}