package com.training.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class W2_AddNewFeatPOM {
	private WebDriver driver; 

	public W2_AddNewFeatPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"menu-posts-property\"]/a/div[3]")
	private WebElement propBtn;

	@FindBy(linkText="Add New")
	private WebElement addNewLink;
	
	@FindBy(id="property_feature-add-toggle")
	private WebElement addNewFeat;
	
	@FindBy(id="newproperty_feature")
	private WebElement addNewFText;
	
	@FindBy(id="newproperty_feature_parent")
	private WebElement selectNewF;
	
	@FindBy(id="property_feature-add-submit")
	private WebElement submitNewF;

	@FindBy(xpath="//*[@id=\"title\"]")
	private WebElement addTitle;

	@FindBy(id="content-html")
	private WebElement textBtn;

	@FindBy(id="content")
	private WebElement addDescr;

	@FindBy(xpath="//label[contains(text(),'BestFrame')]/input")
	private WebElement selAddFet;

	@FindBy(id="publish")
	private WebElement pubBtn;

	public void clickProperty() {
		this.propBtn.click();
	}
	public void clickAddNew() {
		this.addNewLink.click();
	}
	public void clickAddNewFeat() {
		this.addNewFeat.click();
	}
	public void addNewFetText(String addNewFText) {
		this.addNewFText.sendKeys(addNewFText);
	}
	public void selectFetDropFun() {
		Select drop = new Select((selectNewF));
		drop.selectByVisibleText("interior");
	}
	public void addNewFetFun() {
		this.submitNewF.click();
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
	public void moveOverAddFet() {
		Actions fet = new Actions(driver);
		fet.moveToElement(selAddFet).click().build().perform();
	}
	public void clickPublish() {
		this.pubBtn.click();
	}
}