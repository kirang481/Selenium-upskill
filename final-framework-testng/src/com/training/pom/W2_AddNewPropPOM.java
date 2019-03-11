package com.training.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class W2_AddNewPropPOM {
	private WebDriver driver; 

	public W2_AddNewPropPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"menu-posts-property\"]/a/div[3]")
	private WebElement propBtn;

	@FindBy(linkText="Add New")
	private WebElement addNewLink;

	@FindBy(xpath="//*[@id=\"title\"]")
	private WebElement addTitle;

	@FindBy(id="content-html")
	private WebElement textBtn;

	@FindBy(id="content")
	private WebElement addDescr;

	@FindBy(id="in-property_feature-442")
	private WebElement addFtr;

	@FindBy(id="in-region-24")
	private WebElement addReg;

	@FindBy(id="publish")
	private WebElement pubBtn;

	public void clickProperty() {
		this.propBtn.click();
	}
	public void clickAddNew() {
		this.addNewLink.click();
	}
	public void addTitleProp(String addTitle) {
		this.addTitle.sendKeys(addTitle);
	}

	public void clickTextBtn() {
		this.textBtn.click();
	}
	public void addDescProp(String addDescr) {
		this.addDescr.sendKeys(addDescr);
	}
	public void clickFeatures() {
		this.addFtr.click();
	}
	public void clickRegions() {
		this.addReg.click();	
	}
	public void clickPublish() {
		this.pubBtn.click();
	}
}