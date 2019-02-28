package com.training.pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class W2_AddNewComtPOM {
	private WebDriver driver; 
	private String name;

	public W2_AddNewComtPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="menu-item-617")
	private WebElement blogLink;

	@FindBy(linkText="New Launch")
	private WebElement postLink;

	@FindBy(xpath="//*[@id=\"post-3520\"]/div/a")
	private WebElement readMoreLink;

	@FindBy(id="comment")
	private WebElement commentTextBox;

	@FindBy(id="author")
	private WebElement nameTextBox;

	@FindBy(id="email")
	private WebElement emailTextBox;

	@FindBy(id="submit")
	private WebElement postCommentBox;

	@FindBy(xpath="//*[@id=\"menu-comments\"]/a/div[3]")
	private WebElement commentLink;

	@FindBy(xpath="//td[contains(@class,'author column-author')]//strong[contains(text(),'Test B4')]")
	private WebElement addedCommentLink;

	@FindBy(linkText="Reply")
	private WebElement replyLink;

	@FindBy(id="replycontent")
	private WebElement replyTextBox;

	@FindBy(id="replybtn")
	private WebElement replyButton;
	
	//Obejcts for user steps
	public void clickBlogFnt() {
		this.blogLink.click();
	}
	public void clickPostFnt() {
		Actions move = new Actions(driver);
		move.moveToElement(postLink).perform();
	}
	public void clickReadMore() {
		this.readMoreLink.click();
	}
	public void addNewComment(String commentTextBox) {
		this.commentTextBox.sendKeys(commentTextBox);
	}
	public void addNewName(String nameTextBox) {
		this.nameTextBox.sendKeys(nameTextBox);
		name=this.nameTextBox.getAttribute("value");
	}
	public void addNewEmail(String emailTextBox) {
		this.emailTextBox.sendKeys(emailTextBox);
	}
	public void postNewComment() {
		this.postCommentBox.click();
	}
	//Objects for admin steps
	public void clickCommentLink() {
		this.commentLink.click();
	}
	public void naviToAddedComment() {
		Actions nav = new Actions(driver);
		nav.moveToElement(addedCommentLink).perform();
	}
	public void clickReplyLink() {
		this.replyLink.click();
	}
	public void enterTextReply(String replyTextBox) {
		this.replyTextBox.sendKeys(replyTextBox);
	}
	public void clickReplyBtn() {
		this.replyButton.click();
	}
	public int countNoItems() {
		return Integer.parseInt(driver.findElement(By.xpath("//span[contains(@class,'comment-count-approved')]")).getText());	
	}
}