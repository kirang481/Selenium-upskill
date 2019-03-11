package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class W3_AddNewUserDbPOM {
	private WebDriver driver; 

	public W3_AddNewUserDbPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[contains(text(),'Users')]")
	private WebElement usersBtn;

	@FindBy(linkText="Add New")
	private WebElement newUserLink;

	@FindBy(id="user_login")
	private WebElement userText;

	@FindBy(id="email")
	private WebElement emailText;

	@FindBy(id="first_name")
	private WebElement idText;

	@FindBy(id="last_name")
	private WebElement id2Text;

	@FindBy(id="url")
	private WebElement urlText;

	@FindBy(xpath="//button[contains(text(),'Show password')]")
	private WebElement showBtn;

	@FindBy(id="pass1-text")
	private WebElement passText;

	@FindBy(id="role")
	private WebElement roleDrop;

	@FindBy(id="createusersub")
	private WebElement addUsers;

	public void clickUsers() {
		this.usersBtn.click();
	}
	public void addNewFtn() {
		this.newUserLink.click();
	}
	public String UserNameFtn(String userText) {
		this.userText.sendKeys(userText);
		return this.userText.getAttribute("value");
	}
	public String emailIdFtn(String emailText) {
		this.emailText.sendKeys(emailText);
		return this.emailText.getAttribute("value");
	}
	public String firstName(String idText) {
		this.idText.sendKeys(idText);
		return this.idText.getAttribute("value");
	}
	public String lastName(String id2Text) {
		this.id2Text.sendKeys(id2Text);
		return this.id2Text.getAttribute("value");
	}
	public String urlText(String urlText) {
		this.urlText.sendKeys(urlText);
		return this.urlText.getAttribute("value");
	}
	public void showPassword() {
		this.showBtn.click();
	}
	public String setPassword(String passText) {
		this.passText.clear();
		this.passText.sendKeys(passText);
		return this.passText.getAttribute("value");
	}
	public String selectDropdown(String text) {
		Select drop = new Select((roleDrop));
		drop.selectByVisibleText(text);
		return this.roleDrop.getAttribute("value");
	}
	public void submitUser() {
		this.addUsers.click();
	}
}