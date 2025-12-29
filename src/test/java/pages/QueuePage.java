package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import webElementActions.ElementActions;

public class QueuePage extends BasePage {

	private static final Logger logger = LogManager.getLogger(QueuePage.class);

	@FindBy(xpath = "//ul//a[@class='list-group-item']")
	private List<WebElement> qlinkCount;
	@FindBy(xpath = "//a[@href='queue']")
	private WebElement qStartButton;
	@FindBy(xpath = "//div/strong/p")
	private WebElement qPageLinkTitle;
	@FindBy(xpath = "//div[@class='CodeMirror-scroll']")
	private WebElement codeSpace1;
	@FindBy(xpath = "//button[text()='Run']")
	private WebElement runButton;
	@FindBy(id = "output")
	private WebElement Output;
	@FindBy(linkText = "Try here>>>")
	private WebElement tryHereLink;
	@FindBy(xpath = "//a[@data-toggle='dropdown']")
	private WebElement DSdropdown;
	@FindBy(xpath = "//a[text()='Queue']")
	private WebElement selectQueueOption;
	@FindBy(xpath = "//a[@class='dropdown-item']")
	private List<WebElement> selectTopic;
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu')]//a")
	List<WebElement> dropdownTopics;
	@FindBy(xpath = "//a[contains(@class,'nav-link dropdown-toggle')]")
	WebElement dropdownBtn;

	ElementActions elementActions;

	public QueuePage(WebDriver driver) {
		super(driver);
		elementActions = new ElementActions(driver);

	}

	public void goToQueuePAge() {

		elementActions.scrollWaitAndClick(qStartButton);

		// ((JavascriptExecutor) driver).executeScript(
		// "arguments[0].scrollIntoView({block: 'center'});",
		// qStartButton);
		// WebDriverWait wait = new WebDriverWait(driver,
		// Duration.ofSeconds(10));
		//
		// wait.until(ExpectedConditions.visibilityOf(qStartButton));
		// wait.until(ExpectedConditions.elementToBeClickable(qStartButton));
		//
		// qStartButton.click();

	}

	public boolean GetLinkCount() {

		logger.info("Verifying count and names of links on Queue page");
		// List<String> qLinkName = new LinkedList<String>();
		if (qlinkCount.size() == 4) {

			return true;
		}
		return false;
	}

	public boolean GetLinkName(String linkName) {

		logger.info("Verifying names of links on Queue page");

		for (WebElement qlink : qlinkCount) {
			// System.out.println(qlink.getText() + linkName);
			if (qlink.getText().equalsIgnoreCase(linkName.trim())) {
				// System.out.println(qlink.getText() + linkName);
				return true;
			}
		}
		return false;

	}

	public void clickonQLinks(String QueuePageLinks) throws InterruptedException {

		logger.info("Click on " + QueuePageLinks);
		System.out.println(qlinkCount.size());
		for (WebElement qlink : qlinkCount) {
			System.out.println(qlink.getText());
			if (qlink.getText().trim().equalsIgnoreCase(QueuePageLinks.strip())) {
				qlink.click();
				Thread.sleep(5000);

				break;

			}
		}

	}

	public boolean verifyQlinkPage(String QueuePageLinks) throws InterruptedException {

		logger.info("Verify title of Queue Page Links");
		qPageLinkTitle.getText();

		if (qPageLinkTitle.getText().equalsIgnoreCase(QueuePageLinks.trim())) {

			Thread.sleep(3000);
			// driver.navigate().back();
			return true;
		}

		logger.info("Verify title of Queue Page Links: Fail");
		return false;

	}

	public void ClickOnDropDown() {
		DSdropdown.click();
		// return false;

	}

	public void selectFromDropDown(String topic) throws InterruptedException {

		selectQueueOption.click();
	}

	public void TryHere(String QueuePageLinks) {

		logger.info("Click on try here :" + QueuePageLinks);
		tryHereLink.click();

	}

	public boolean VerifyAssementPage() {

		if (driver.getTitle().equalsIgnoreCase("Assessment")) {
			logger.info("Tryhere Link - Assement page displayed");
			return true;
		}
		logger.error("Tryhere Link - Assement page Fail");
		return false;

	}

	public void EnterCode(String code) throws InterruptedException {

		logger.info("Enter code:" + code);
		Actions actions = new Actions(driver);
		actions.click(codeSpace1).build().perform();
		actions.sendKeys(codeSpace1, code).perform();
		runButton.click();
		logger.info("Click on Run to execute code");

	}

	public boolean getOutput(String output) {

		logger.info("Validate code  output");

		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			return (alertMsg.equalsIgnoreCase(output.trim()));
		} catch (NoAlertPresentException e) {
			return (Output.getText().equalsIgnoreCase(output.trim()));
		}

	}

}
