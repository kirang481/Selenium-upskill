//test case RETC_080
//To verify whether application allows admin to add new property with all details & added property details in home screen for user
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
import com.training.pom.W2_AddNewPropPOM;
import com.training.pom.W3_AddNewPropPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W3_AddNewPropTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W2_AddNewPropPOM w2_AddNewPropPOM;
	private static W3_AddNewPropPOM w3_AddNewPropPOM;
	private static Properties properties;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		//Set up commands
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		loginRealEstatePOM = new LoginRealEstatePOM(driver);
		w2_AddNewPropPOM = new W2_AddNewPropPOM(driver);
		w3_AddNewPropPOM = new W3_AddNewPropPOM(driver);
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
	}

	@Test (priority=1)
	public void publishPropTest() throws InterruptedException {
		//Using two different POM file to create new test
		//Creating new property starts here
		w2_AddNewPropPOM.clickProperty();
		w2_AddNewPropPOM.clickAddNew();
		w2_AddNewPropPOM.addTitleProp("new launch");
		w2_AddNewPropPOM.clickTextBtn();
		w2_AddNewPropPOM.addDescProp("new launch");
		w3_AddNewPropPOM.enterPrice("50000");
		w3_AddNewPropPOM.enterPriceSq("200");
		w3_AddNewPropPOM.clickMainText();
		w3_AddNewPropPOM.enterStatus("New");
		w3_AddNewPropPOM.enterLocation("Electronic city");
		w3_AddNewPropPOM.enterPissession("immediate");
		w3_AddNewPropPOM.clickLocation();
		w3_AddNewPropPOM.enterAddress("yeshwanthapur");
		w3_AddNewPropPOM.enterGoogleAddress("yeshwanthapur");
		w3_AddNewPropPOM.enterLatitude("120");
		w3_AddNewPropPOM.enterLongitude("56");
		w3_AddNewPropPOM.clickDetails();
		w3_AddNewPropPOM.enterStorage("2");
		w3_AddNewPropPOM.clickKeyword();
		//Scrolling up in webpage to make publish button visible
		((JavascriptExecutor)driver).executeScript("scroll(0,-500)");
		w2_AddNewPropPOM.clickFeatures();
		w2_AddNewPropPOM.clickRegions();
		((JavascriptExecutor)driver).executeScript("scroll(0,-100)");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//Creating new property ends here
		w2_AddNewPropPOM.clickPublish();
		//Thread.sleep(3000);
		w3_AddNewPropPOM.mpoveOverUser();
		w3_AddNewPropPOM.clickLogOutMtd();
		w3_AddNewPropPOM.clickHomeMtd();
		w3_AddNewPropPOM.assertValidation();
	}
}