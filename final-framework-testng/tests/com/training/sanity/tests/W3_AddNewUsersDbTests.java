//test case RETC_077
//To Verify whether application allows admin to add new user & details get displayed in database
package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.dataproviders.LoginDataProviders;
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.W3_AddNewUserDbPOM;
import com.training.pom.W3_AddNewUsersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W3_AddNewUsersDbTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W3_AddNewUserDbPOM w3_AddNewUserDbPOM;
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
		w3_AddNewUserDbPOM = new W3_AddNewUserDbPOM(driver);
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
	
	@Test(priority=1, dataProvider = "db-inputs-user", dataProviderClass = LoginDataProviders.class)
	public void createNewUser(String userName, String email, String firstName, String lastName, String webSite, String password, String role) {
		//Creating new user with below details hard code, SQL query filtered with WHERE CLAUSE to retrieve data for specific user
		//TO retest with same details go ahead and delete the Existing user with below name
		w3_AddNewUserDbPOM.clickUsers();
		w3_AddNewUserDbPOM.addNewFtn();
		String user = w3_AddNewUserDbPOM.UserNameFtn("manzoor");
		String emailid = w3_AddNewUserDbPOM.emailIdFtn("manzoor@gmail.com");
		String first = w3_AddNewUserDbPOM.firstName("manzoor");
		String last = w3_AddNewUserDbPOM.lastName("mehadi");
		String url = w3_AddNewUserDbPOM.urlText("www.google.com");
		w3_AddNewUserDbPOM.showPassword();
		String pass = w3_AddNewUserDbPOM.setPassword("Manzoor@Mehadi123");
		String desig = w3_AddNewUserDbPOM.selectDropdown("Agent");
		w3_AddNewUserDbPOM.submitUser();
		
		//Asserting values returned from database with hard coded values
		Assert.assertEquals(user, userName);
		Assert.assertEquals(emailid, email);
		Assert.assertEquals(first, firstName);
		Assert.assertEquals(last, lastName);
		Assert.assertEquals(url, webSite);
		Assert.assertEquals(pass, password);
		Assert.assertEquals(desig, role);	
	}
}