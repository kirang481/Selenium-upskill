package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddNewCatPOM {
	private WebDriver driver; 

	public AddNewCatPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"menu-posts\"]/a/div[3]")
	private WebElement postsBtn;

	@FindBy(linkText="Categories")
	private WebElement catBtn;

	@FindBy(id="tag-name")
	private WebElement tagTextbox;

	@FindBy(id="tag-slug")
	private WebElement slugTextbox;

	@FindBy(id="tag-description")
	private WebElement descrTextbox;

	@FindBy(id="submit")
	private WebElement subBtn;

	public void clickPosts() {
		this.postsBtn.click();
	}
	public void addNewFtn() {
		this.catBtn.click();
	}
	public void newName(String tagTextbox) {
		this.tagTextbox.sendKeys(tagTextbox);
	}
	public void newSlag(String slugTextbox) {
		this.slugTextbox.sendKeys(slugTextbox);
	}
	public void newDescr(String descrTextbox) {
		this.descrTextbox.sendKeys(descrTextbox);
	}
	public void subFtn() {
		this.subBtn.click();
	}
}