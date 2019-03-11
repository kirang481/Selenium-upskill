//test case RETC_076
//To Verify whether application allows admin to add multiple users for different roles
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
import com.training.pom.W3_AddNewUsersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W3_AddNewUsersTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W3_AddNewUsersPOM w3_AddNewUsersPOM;
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
		w3_AddNewUsersPOM = new W3_AddNewUsersPOM(driver);
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
	
	@Test (priority=1, dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void addUsers(String Username,String email,String firstname,String lastname,String url,String password,String selectdrop) throws InterruptedException {
		//Click on New user link
		w3_AddNewUsersPOM.clickUsers();
		w3_AddNewUsersPOM.addNewFtn();
		//Creating New Users
		w3_AddNewUsersPOM.UserNameFtn(Username);
		w3_AddNewUsersPOM.emailIdFtn(email);
		w3_AddNewUsersPOM.firstName(firstname);
		w3_AddNewUsersPOM.lastName(lastname);
		w3_AddNewUsersPOM.urlText(url);
		w3_AddNewUsersPOM.showPassword();
		w3_AddNewUsersPOM.setPassword(password);
		//Select method declared as string in POM file
		w3_AddNewUsersPOM.selectDropdown(selectdrop);
		w3_AddNewUsersPOM.submitUser();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//Assertion
		String expected = "New user created. Edit user";
		String actual = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		Assert.assertEquals(expected, actual);
	}
}