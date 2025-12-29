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

	public HomePage(WebDriver driver) {
		super(driver);
		new ElementActions(driver);
	}

	public void clickGetStarted() {

		getStartedButton.click();
	}

	public String fetchTitle() {
		return driver.getTitle();

	}

	public void launchApplication() {
		String url = ConfigReader.getProperty("url");
		driver.get(url);
	}

	// select register link or signin link
	public void clickLink(String linkName) {
		if (linkName.equalsIgnoreCase("Register")) {
			registerLink.click();
		} else if (linkName.equalsIgnoreCase("Sign in")) {
			signinLink.click();
		}
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

		// WebDriverWait wait = new WebDriverWait(driver,
		// Duration.ofSeconds(10));

		int retries = 3;
		while (retries > 0) {
			try {

				WebElement freshDropdownBtn = wait.until(ExpectedConditions.elementToBeClickable(dropdownBtn));
				freshDropdownBtn.click();

				// Re-fetch dropdown options each time to avoid
				// StaleElementReference Exception
				List<WebElement> freshOptions = wait.until(ExpectedConditions.visibilityOfAllElements(dropdownTopics));

				for (WebElement option : freshOptions) {
					if (option.getText().trim().equalsIgnoreCase(topicName)) {
						wait.until(ExpectedConditions.elementToBeClickable(option)).click();
						return;
					}
				}

			} catch (StaleElementReferenceException e) {

				// ReInitialize page factory to refresh all elements
				//
				PageFactory.initElements(driver, this);// recreates ALL @FindBy
														// elements fresh from
														// the current DOM.
				retries--;
			}
		}

		throw new RuntimeException("Dropdown option NOT found: " + topicName);
	}

	public String fetchErrorMsg() {

		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		logger.error("Error Message is displayed" + errorMsg);

		return errorMsg.getText();
	}

	public void getStartedclick(String topic) {

		for (int i = 0; i < getstartedTopic.size(); i++) { // looping since it
															// has multiple
															// getstarted button

			String topicsTxt = getstartedTopic.get(i).getText();// getting exact
																// location text

			if (topicsTxt.equalsIgnoreCase(topic)) {
				WebElement btn = getstartedBtn.get(i);

				btn.click();

				logger.info("topics clicked are: " + topicsTxt);

				break;
			}
		}

	}

	public String fetchIntroductionPageTitle() {
		logger.info("Page Title: " + intropageHeading.getText());
		return intropageHeading.getText();
	}

	public void gotosignin() {
		signinLink.click();
	}

}