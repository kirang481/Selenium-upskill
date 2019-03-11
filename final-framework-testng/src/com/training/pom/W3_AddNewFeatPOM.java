package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class W3_AddNewFeatPOM {
	private WebDriver driver; 
	private String entName;

	public W3_AddNewFeatPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[contains(text(),'Properties')]")
	private WebElement propBtn;

	@FindBy(linkText="Features")
	private WebElement newFeat;

	@FindBy(id="tag-name")
	private WebElement nameText;

	@FindBy(id="tag-slug")
	private WebElement slugText;

	@FindBy(id="parent")
	private WebElement featDrop;

	@FindBy(id="tag-description")
	private WebElement descText;

	@FindBy(id="submit")
	private WebElement subBtn;
	
	@FindBy(id="tag-search-input")
	private WebElement searchText;
	
	@FindBy(id="search-submit")
	private WebElement searchBtn;

	public void clickProp() {
		this.propBtn.click();
	}
	public void addNewFtn() {
		this.newFeat.click();
	}
	public void nameFtn(String nameText) {
		this.nameText.sendKeys(nameText);
		//Storing this variable over here to make use it in search bar line number 70
		entName=this.nameText.getAttribute("value");
	}
	public void slugFtn(String slugText) {
		this.slugText.sendKeys(slugText);
	}
	public void featDropFtn(String text) {
		Select drop = new Select((featDrop));
		drop.selectByVisibleText(text);
	}
	public void descFtn(String descText) {
		this.descText.sendKeys(descText);
	}
	public void addNewBtn() {
		this.subBtn.click();
	}
	public void searchText() {
		this.searchText.sendKeys(entName);
	}
	public void searchBtn() {
		this.searchBtn.click();
	}
}