package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DelAddedCatPOM {
	private WebDriver driver; 

	public DelAddedCatPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"menu-posts\"]/a/div[3]")
	private WebElement postsBtn;

	@FindBy(linkText="Categories")
	private WebElement catBtn;

	//hard coded to delete first element in the list, change to x path to delete desired element (below xpath deletes the last element//
	@FindBy(name="delete_tags[]")
	//@FindBy(xpath="//*[@id=\"cb-select-156\"]")
	private WebElement checkbx;

	@FindBy(id="bulk-action-selector-top")
	private WebElement listBox;

	@FindBy(id="doaction")
	private WebElement applyBtn;

	public void clickPosts() {
		this.postsBtn.click();
	}
	public void catClickFtn() {
		this.catBtn.click();
	}
	public void selChbxFtn() {
		this.checkbx.click();
	}
	public void listBox() {	
		Select del = new Select(listBox);
		del.selectByVisibleText("Delete");
	}
	public void deleteAction() {
		this.applyBtn.click();
	}
}