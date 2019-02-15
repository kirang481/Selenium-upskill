package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoveToTrashPOM {
	private WebDriver driver; 

	public MoveToTrashPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id=\"menu-posts\"]/a/div[3]")
	private WebElement postsBtn;

	@FindBy(linkText="All Posts")
	private WebElement allPostslink;

	@FindBy(linkText="New Launches")
	private WebElement addedLink;

	@FindBy(linkText="Trash")
	private WebElement trashBtn;

	@FindBy(xpath="//*[@id=\"posts-filter\"]/div[1]/div[3]/span[1]")
	private WebElement noItems;

	public void clickPosts() {
		this.postsBtn.click();
	}
	public void clickAllPosts() {
		this.allPostslink.click();
	}
	public void mouseOverPost() {
		Actions trash = new Actions(driver);
		trash.moveToElement(addedLink).build().perform();
	}
	public void moveTrash() {
		this.trashBtn.click();
	}
	public void beforeDisplayMtd() {
		System.out.println("Number of posts before trash movement\n");
	}
	public void afterDisplayMtd() {
		System.out.println("\nNumber of posts after trash movement\n");
	}
	public void validationItems() {
		String items = noItems.getText();
		System.out.println(""+items);
	}
}