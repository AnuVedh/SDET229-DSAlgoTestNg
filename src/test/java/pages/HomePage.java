package pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;
import webElementActions.ElementActions;

public class HomePage extends BasePage {

	private static final Logger logger = LogManager.getLogger(HomePage.class);

	@FindBy(xpath = "//button[@class='btn']")
	private WebElement getStartedButton;

	@FindBy(xpath = "//a[contains(text(),' Register ')]") // register link
	WebElement registerLink;
	@FindBy(xpath = "//input[@value='Register']") // verify registerpage
	WebElement register;
	@FindBy(xpath = "//a[contains(text(),'Sign in')]") // signin link
	WebElement signinLink;
	@FindBy(xpath = "//input[@value='Login']") // verify signin page with login
												// button text
	WebElement signin;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu')]/a")
	List<WebElement> dropdownTopics;

	@FindBy(xpath = "//a[contains(@class,'nav-link dropdown-toggle')]")
	WebElement dropdownBtn;
	

	@FindBy(xpath = "//div[contains(@class,'alert')]")
	WebElement errorMsg;

	@FindBy(xpath = "//div[@class='col']/div/div/h5")
	List<WebElement> getstartedTopic;

	@FindBy(xpath = "//div[@class='col']/div/div/a")
	List<WebElement> getstartedBtn;

	@FindBy(xpath = "//h4[contains(@class,'bg-secondary text-white')]")
	WebElement intropageHeading;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	ElementActions elementActions;
	public HomePage(WebDriver driver) {
		super(driver);
		elementActions = new ElementActions(driver);
	
	}

	public void clickGetStarted() {

		elementActions.clickAction(getStartedButton);
		
	}

	public String fetchTitle() {
		return driver.getTitle();

	}

	public void launchApplication() {
		String url = ConfigReader.getProperty("url");
		driver.get(url);
	}
	public void goToHomePage() {
	    driver.navigate().to("https://dsportalapp.herokuapp.com/home");
	}

	public void clickRegisterLink() {
	    elementActions.clickAction(registerLink);
	}

	public void clickSignInLink() {
	    elementActions.clickAction(signinLink);
	}


	public String fetchregisterTitle() {

		String registertitle = register.getAttribute("value");
		logger.info("Registeration page" + registertitle);
		return registertitle;

	}

	public String fetchsigninpageTitle() {
		String signintitle = signin.getAttribute("value");
		logger.info("SignIn page" + signintitle);
		return signintitle;
	}

	public void selectTopicFromDropdown(String topicName) {

		elementActions.clickAction(dropdownBtn);
		
		List<WebElement> freshOptions = wait.until(ExpectedConditions.visibilityOfAllElements(dropdownTopics));
		elementActions.clickElementByText(freshOptions, topicName);
		
	}

	public String fetchErrorMsg() {

		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		logger.error("Error Message is displayed" + errorMsg);

		return errorMsg.getText();
	}
	
	

	public void getStartedclick(String topic) {
		
		
		System.out.println("Current URL: " + driver.getCurrentUrl());
		System.out.println("Topics found: " + getstartedTopic.size());
		System.out.println("Buttons found: " + getstartedBtn.size());

	//	String normalizedTarget = topic.replaceAll("[\\t\\n\\r]+", "").trim().toLowerCase();
		String normalizedTarget = topic.strip().toLowerCase();
		List<WebElement> freshTopics =
	            wait.until(ExpectedConditions.visibilityOfAllElements(getstartedTopic));
		
		for (int i = 0; i < freshTopics.size(); i++) {
		    String normalizedText = freshTopics.get(i).getText().strip().toLowerCase();
		    logger.info("Topic clicked: " + getstartedTopic.get(i).getText());


		    if (normalizedText.equals(normalizedTarget)) {
		        List<WebElement> freshButtons =
		                wait.until(ExpectedConditions.visibilityOfAllElements(getstartedBtn));
		        System.out.print("FreshButtons:"+freshButtons.get(i).getText());
		        elementActions.clickAction(freshButtons.get(i));
		        
		        
		        return;
		    }
		}
		throw new RuntimeException("Get Started button not found for topic: " + topic);


	}

	public String fetchIntroductionPageTitle() {
		logger.info("Page Title: " + intropageHeading.getText());
		return intropageHeading.getText();
	}

	public void gotosignin() {
		elementActions.clickAction(signinLink);
//	"Stack\r\n"
//	+ ""
	}

}