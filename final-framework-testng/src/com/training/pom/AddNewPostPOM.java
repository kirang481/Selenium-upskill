package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewPostPOM {
	private WebDriver driver; 

	public AddNewPostPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"menu-posts\"]/a/div[3]")
	private WebElement postsBtn;

	@FindBy(linkText="Add New")
	private WebElement newBtn;

	@FindBy(xpath="//*[@id=\"title\"]")
	private WebElement titleTextbx;

	@FindBy(xpath="//*[@id=\"content\"]")
	private WebElement contentTextbx;

	@FindBy(name="publish")
	private WebElement publishBtn;

	public void clickPosts() {
		this.postsBtn.click();
	}
	public void addNewFtn() {
		this.newBtn.click();
	}
	public void newTitle(String titleTextbx) {
		this.titleTextbx.sendKeys(titleTextbx);
	}
	public void contentTextbx(String contentTextbx) {
		this.contentTextbx.sendKeys(contentTextbx);
	}
	public void publishBtn() {
		this.publishBtn.click();
	}
}