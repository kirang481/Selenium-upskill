package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class W3_AddNewUsersPOM {
	private WebDriver driver; 

	public W3_AddNewUsersPOM(WebDriver driver) {
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
	public void UserNameFtn(String userText) {
		this.userText.sendKeys(userText);
	}
	public void emailIdFtn(String emailText) {
		this.emailText.sendKeys(emailText);
	}
	public void firstName(String idText) {
		this.idText.sendKeys(idText);
	}
	public void lastName(String id2Text) {
		this.id2Text.sendKeys(id2Text);
	}
	public void urlText(String urlText) {
		this.urlText.sendKeys(urlText);
	}
	public void showPassword() {
		this.showBtn.click();
	}
	public void setPassword(String passText) {
		this.passText.clear();
		this.passText.sendKeys(passText);
	}
	public void selectDropdown(String text) {
		Select drop = new Select((roleDrop));
		drop.selectByVisibleText(text);
	}
	public void submitUser() {
		this.addUsers.click();
	}
}