package com.training.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class W2_AddNewRegnPOM {
	private WebDriver driver; 

	public W2_AddNewRegnPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"menu-posts-property\"]/a/div[3]")
	private WebElement propBtn;

	@FindBy(linkText="Add New")
	private WebElement addNewLink;
	
	@FindBy(id="region-add-toggle")
	private WebElement addNewRegLink;
	
	@FindBy(id="newregion")
	private WebElement addNewRegText;
	
	@FindBy(id="newregion_parent")
	private WebElement selectNewReg;
	
	@FindBy(id="region-add-submit")
	private WebElement submitNewReg;

	@FindBy(xpath="//*[@id=\"title\"]")
	private WebElement addTitle;

	@FindBy(id="content-html")
	private WebElement textBtn;

	@FindBy(id="content")
	private WebElement addDescr;

	@FindBy(xpath="//label[contains(text(),'Elec4City')]/input")
	private WebElement selAddReg;

	@FindBy(id="publish")
	private WebElement pubBtn;

	public void clickProperty() {
		this.propBtn.click();
	}
	public void clickAddNew() {
		this.addNewLink.click();
	}
	public void clickAddNewReg() {
		this.addNewRegLink.click();
	}
	public void addNewRegText(String addNewRegText) {
		this.addNewRegText.sendKeys(addNewRegText);
	}
	public void selectRegDropFun() {
		Select drop = new Select((selectNewReg));
		drop.selectByValue("27");
	}
	public void addNewRegFun() {
		this.submitNewReg.click();
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
	public void moveOverAddReg() {
		Actions fet = new Actions(driver);
		fet.moveToElement(selAddReg).click().build().perform();
	}
	public void clickPublish() {
		this.pubBtn.click();
	}
}