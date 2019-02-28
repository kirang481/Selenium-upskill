//test case RETC_049
package com.training.sanity.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.LoginRealEstatePOM;
import com.training.pom.W2_AddNewComtPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class W2_AddNewComtTests {

	private static WebDriver driver;
	private static String baseUrl;
	private static String userUrl;
	private static LoginRealEstatePOM loginRealEstatePOM;
	private static W2_AddNewComtPOM w2_AddNewComtPOM;
	private static Properties properties;
	private static ScreenShot screenShot;
	private int before,after;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		//Created new property called user URL for non-admin log in
		userUrl = properties.getProperty("userURL");
		baseUrl = properties.getProperty("baseURL");
		//Set up commands
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		screenShot = new ScreenShot(driver); 
		loginRealEstatePOM = new LoginRealEstatePOM(driver);
		w2_AddNewComtPOM = new W2_AddNewComtPOM(driver);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test (priority=0)
	public void postNewComtTest() throws IOException {
		//Steps for posting comments by user
		driver.get(userUrl);
		w2_AddNewComtPOM.clickBlogFnt();
		w2_AddNewComtPOM.clickPostFnt();
		w2_AddNewComtPOM.clickReadMore();
		w2_AddNewComtPOM.addNewComment("good112");
		w2_AddNewComtPOM.addNewName("Test B4");
		w2_AddNewComtPOM.addNewEmail("test@test1.com");
		w2_AddNewComtPOM.postNewComment();
		screenShot.captureScreenShot("User Add Cmt");
	}

	@Test (priority=1)
	public void launchNewWindow() throws AWTException {
		//Creates new tab using Ctrl+t keyboard
		Robot window = new Robot();
		window.keyPress(KeyEvent.VK_CONTROL);
		window.keyPress(KeyEvent.VK_T);  
		window.keyRelease(KeyEvent.VK_CONTROL);
		window.keyRelease(KeyEvent.VK_T);
	}
		
	@Test (priority=2)
	public void validLoginTest() {
		//Admin log in steps
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		driver.get(baseUrl);
		loginRealEstatePOM.sendUserName("admin");
		loginRealEstatePOM.sendPassword("admin@123");
		loginRealEstatePOM.clickLoginBtn(); 
		screenShot.captureScreenShot("LoginforNewProp");
	}

	@Test (priority=3)
	public void replyCommentAdminTest() {
		//Steps to reply for admin comments
		w2_AddNewComtPOM.clickCommentLink();
		w2_AddNewComtPOM.naviToAddedComment();
		w2_AddNewComtPOM.clickReplyLink();
		//Counting number of comments before reply and storing in variable
		before = w2_AddNewComtPOM.countNoItems();
		w2_AddNewComtPOM.enterTextReply("Comment added by admin123");
		w2_AddNewComtPOM.clickReplyBtn();
		driver.navigate().refresh();
		//Counting number of comments after reply and storing in variable
		after = w2_AddNewComtPOM.countNoItems();
	}

	@Test (priority=4)
	public void textValidation() {
		//Counts the item before replying to comment and increment by 1;
		int expected = ++before;
		System.out.println(expected);
		int actual = after;
		System.out.println(after);
		Assert.assertEquals(expected, actual);
	}
}