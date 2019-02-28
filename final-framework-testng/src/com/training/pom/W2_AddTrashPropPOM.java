package com.training.pom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class W2_AddTrashPropPOM {
	private WebDriver driver; 
	private String name;

	public W2_AddTrashPropPOM(WebDriver driver) {
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

	@FindBy(id="in-property_feature-297")
	private WebElement addFtr;

	@FindBy(id="in-region-24")
	private WebElement addReg;

	@FindBy(linkText="Move to Trash")
	private WebElement trashLink;
	
	@FindBy(xpath="//*[@id=\"wpbody-content\"]/div[3]/ul/li[4]/a")
	private WebElement trashBtn;
	
	@FindBy(id="post-search-input")
	private WebElement searchText;
	
	@FindBy(id="search-submit")
	private WebElement searchBtn;
	
	public void clickProperty() {
		this.propBtn.click();
	}
	public void clickAddNew() {
		this.addNewLink.click();
	}
	public void addTitleProp(String addTitle) {
		this.addTitle.sendKeys(addTitle);
		name=this.addTitle.getAttribute("value");
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
	public void moveToTrash() {
		this.trashLink.click();
	}
	public void clickMoveTrash() {
		this.trashBtn.click();
	}
	//Searching same entered obejct name, storing and re-using the value entered in Line 57
	public void searchText() {
		this.searchText.sendKeys(name);
	}
	public void clickSearch() {
		this.searchBtn.click();
	}
}