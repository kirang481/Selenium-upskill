package com.training.pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class W3_AddNewPropPOM {
	private WebDriver driver; 

	public W3_AddNewPropPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="_price")
	private WebElement priceText;

	@FindBy(id="_price_per")
	private WebElement persqText;

	@FindBy(id="ui-id-2")
	private WebElement maindetText;

	@FindBy(id="_status")
	private WebElement statusText;

	@FindBy(id="_location")
	private WebElement locationText;

	@FindBy(id="_possession")
	private WebElement possessionText;

	@FindBy(id="ui-id-3")
	private WebElement locaitonLink;

	@FindBy(id="_friendly_address")
	private WebElement addressText;

	@FindBy(id="_address")
	private WebElement googleAddress;

	@FindBy(id="_geolocation_lat")
	private WebElement latitudeText;

	@FindBy(id="_geolocation_long")
	private WebElement longitudeText;

	@FindBy(id="ui-id-4")
	private WebElement detailsLink;

	@FindBy(id="_storage_room")
	private WebElement storageText;

	@FindBy(xpath="//ul[contains(@class,'children acf-bl')]//input[contains(@value,'24')]")
	private WebElement centralCheck;

	@FindBy(xpath="//a[contains(text(),'Howdy,')]")
	private WebElement clickonUser;

	@FindBy(xpath="//*[@id=\"wp-admin-bar-logout\"]/a")
	private WebElement clickLogout;

	@FindBy(xpath="//a[@title='Real Estate']")
	private WebElement clickHome;
	
	@FindBy(xpath="//*[@id=\\\"message\\\"]/p")
	private WebElement publishMessage;

	public void enterPrice(String priceBtn) {
		this.priceText.sendKeys(priceBtn);
	}
	public void enterPriceSq(String persqText) {
		this.persqText.sendKeys(persqText);
	}
	public void clickMainText() {
		this.maindetText.click();
	}
	public void enterStatus(String statusText) {
		this.statusText.sendKeys(statusText);
	}
	public void enterLocation(String locationText) {
		this.locationText.sendKeys(locationText);
	}
	public void enterPissession(String possessionText) {
		this.possessionText.sendKeys(possessionText);
	}
	public void clickLocation() {
		this.locaitonLink.click();
	}
	public void enterAddress(String addressText) {
		this.addressText.sendKeys(addressText);
	}
	public void enterGoogleAddress(String googleAddress) {
		this.googleAddress.sendKeys(googleAddress);
	}
	public void enterLatitude(String latitudeText) {
		this.latitudeText.sendKeys(latitudeText);
	}
	public void enterLongitude(String longitudeText) {
		this.longitudeText.sendKeys(longitudeText);
	}
	public void clickDetails() {
		this.detailsLink.click();
	}
	public void enterStorage(String storageText) {
		this.storageText.sendKeys(storageText);
	}
	public void clickKeyword() {
		this.centralCheck.click();
	}
	public void mpoveOverUser() {
		Actions move = new Actions(driver);
		move.moveToElement(clickonUser).perform();
	}
	public void clickLogOutMtd() {
		this.clickLogout.click();
	}
	public void clickHomeMtd() {
		this.clickHome.click();
	}
	public void assertValidation() {
		String expected = "new launch";
		String Actual = driver.findElement(By.linkText(expected)).getText();
		Assert.assertEquals(Actual, expected);
	}
}