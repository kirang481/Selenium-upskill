//test case RETC_079
//To Verify whether application allows admin to Add multiple New Feature in the application
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
import com.training.dataproviders.LoginDataProviders;
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.W3_AddNewFeatPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W3_AddNewFeatTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W3_AddNewFeatPOM w3_AddNewFeatPOM;
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
		w3_AddNewFeatPOM = new W3_AddNewFeatPOM(driver);
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
	}
	
	@Test (priority=1, dataProvider = "excel-inputsAddfeat", dataProviderClass = LoginDataProviders.class)
	public void addUsers(String nameText,String slugText,String text,String descText) {
		//Click on New user link
		w3_AddNewFeatPOM.clickProp();
		w3_AddNewFeatPOM.addNewFtn();
		//Adding new features using below methods
		w3_AddNewFeatPOM.nameFtn(nameText);
		w3_AddNewFeatPOM.slugFtn(slugText);
		w3_AddNewFeatPOM.featDropFtn(text);
		w3_AddNewFeatPOM.descFtn(descText);
		w3_AddNewFeatPOM.addNewBtn();
		//refreshing webpage to getadded new feature
		driver.navigate().refresh();
		//scrolling since element not tracable
		((JavascriptExecutor)driver).executeScript("scroll(0,-500)");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//Search string defined in POM file by capturing text entered in line 69
		w3_AddNewFeatPOM.searchText();
		w3_AddNewFeatPOM.searchBtn();
		//Assertion
		String expected = nameText;
		String actual = driver.findElement(By.linkText(nameText)).getText();
		Assert.assertEquals(expected, actual);
	}
}