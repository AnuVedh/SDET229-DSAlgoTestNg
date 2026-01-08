package pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.DriverFactory;
import webElementActions.ElementActions;

public class ArraysPage extends BasePage {

	
	private WebDriver driver;
	
	private static final Logger logger = LogManager
			.getLogger(ArraysPage.class);
	@FindBy(xpath = "//div[2][@class='col']/div[1]/div/a")
	WebElement arraysGetStartedBtn;
	@FindBy(xpath = "//h4[contains(@class,'bg-secondary text-white')]")
	WebElement fetchpageTitle;

	@FindBy(xpath = "//a[contains(@class,'list-group-item')]")
	List<WebElement> arraysubpageLinks;
	@FindBy(xpath = "//div[@class='col-sm']/strong//p[contains(@class,'bg-secondary text-white')]")
	WebElement arraysubpageTitle;

	@FindBy(xpath = "//a[contains(@class,'btn btn-info')]")
	WebElement tryhereBtn;
	@FindBy(xpath = "//div[contains(@class,'CodeMirror cm-s-default')]")

	WebElement texteditor;
	@FindBy(xpath = "//button[@type='button']")
	WebElement runBtn;
	@FindBy(id = "output")
	WebElement consoleOutput;
	String expectedMessage;
	@FindBy(xpath = "//a[contains(text(),'Practice Questions')]")
	WebElement practiceQueLink;
	@FindBy(xpath = "//a[contains(@class,'list-group-item')]")
	List<WebElement> practiceSubTopics;

	@FindBy(xpath = "//div[@class='question']/h2")
	WebElement practicesubTopicsTitle;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	
	ElementActions elementActions;
	public ArraysPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		elementActions = new ElementActions(driver);

	}

	public void arraysgetstartedClick() {
		
		elementActions.clickAction(arraysGetStartedBtn);
	}
	public String fetchArrayspageTitle() {
		logger.info("ArraysPage Title"+fetchpageTitle.getText());
	
		return fetchpageTitle.getText();
	}
	public void topicsCovered(String topiccovered) {
		for (int i = 0; i < arraysubpageLinks.size(); i++) { // looping since it has multiple getstarted button
																  

			String topicsTxt = arraysubpageLinks.get(i).getText();// getting exact location text
																	
			if (topicsTxt.equalsIgnoreCase(topiccovered)) {
				WebElement topiclink = arraysubpageLinks.get(i);

				elementActions.clickAction(topiclink);
			
				logger.info("topics clicked are: " + topicsTxt);
		
				break;
			}
		}
	}
	public String fetchtitlepage() {
		String pagetitle = arraysubpageTitle.getText();
		logger.info("Arrays Subpage Title: "+arraysubpageTitle.getText());
		
		return pagetitle;
	}
	public void tryhere() {
	
		elementActions.clickAction(tryhereBtn);
	}
	public boolean textEditorIsDisplayed() {
		return texteditor.isDisplayed();
	}

	public void runPythonCode(String code) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Clear CodeMirror completely
		js.executeScript("arguments[0].CodeMirror.setValue('')", texteditor);

		// Set new cleaned code
		js.executeScript("arguments[0].CodeMirror.setValue(arguments[1])",
				texteditor, code);

		elementActions.clickAction(runBtn);
	

	}

	public String getResult() {

		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();//
			logger.info("Error Message from alert "+alertMsg);
			return (alertMsg);
		} catch (NoAlertPresentException e) {
			return consoleOutput.getText().trim();
		}

	}

	public void practicequeLink() {
		
		elementActions.clickAction(practiceQueLink);

	}

	public String getPageTitle() {
		logger.info("Practice page title "+driver.getTitle());
	
		return driver.getTitle();
	}

	public void practicesubTopiclink(String practicequeList) {
		
		
	
		        for (WebElement topic : practiceSubTopics) {
		            if (topic.getText().trim().equalsIgnoreCase(practicequeList)) {
		              //  wait.until(ExpectedConditions.elementToBeClickable(topic)).click();//
		            	elementActions.clickAction(topic);
		                return;
		            }
		        }
		    
	   throw new RuntimeException("Subtopic NOT found: " + practicequeList);

	

		    
		

		
	
		}

	

	public String subtopicTitle() {
	

		WebElement titleElement = wait
				.until(ExpectedConditions.visibilityOf(practicesubTopicsTitle));
		String titleText = titleElement.getText();
		return titleText;
	}

}
