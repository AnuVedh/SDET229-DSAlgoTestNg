
package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QueuePage extends BasePage {

	private static final Logger logger = LogManager.getLogger(QueuePage.class);

	@FindBy(xpath = "//ul//a[@class='list-group-item']")
	private List<WebElement> qPageLinkCount;
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
	@FindBy(xpath = "//a[text()='Queue']")
	private WebElement selectQueueOption;
	@FindBy(xpath = "//a[@class='dropdown-item']")
	private List<WebElement> selectTopic;
	@FindBy(xpath = "//div[contains(@class,'dropdown-menu')]//a")
	List<WebElement> dropdownTopics;
	@FindBy(xpath = "//a[contains(@class,'nav-link dropdown-toggle')]")
	WebElement dropdownBtn;
	@FindBy(xpath = "//a[@data-toggle='dropdown']")
	private WebElement DSdropdown;

	// ElementActions elementActions;
	CommonPage commonPage;

	public QueuePage(WebDriver driver) {
		super(driver);
		// elementActions = new ElementActions(driver);
		commonPage = new CommonPage(driver);

	}

	public void goToQueuePAge() {

		logger.info("Select Graph page");
		commonPage.goToPage(qStartButton);

	}

	public boolean getQsubTopicLinkCount() {
		logger.info("Verifying count and names of links on Graph page");
		return (commonPage.getLinkCount(qPageLinkCount) == 4);

	}

	public boolean getQsubTopicLinkName(String linkname) {
		logger.info("Verifying names of sub topic links on Graph page");
		return (commonPage.getSubTopicLinkName(qPageLinkCount, linkname));
	}

	public void clickOnDropDown() {
		commonPage.clickOnDropDown(DSdropdown);
	}

	public void selectFromDropDown(String topic) throws InterruptedException {
		commonPage.selectFromDropDown(selectQueueOption);
	}

	public void clickonQueueSubtopicLinks(String qPageLinks)
			throws InterruptedException {
		logger.info("Click on Queue Page link: " + qPageLinks);
		commonPage.clickonSubTopicsLinks(qPageLinkCount, qPageLinks);
	}

	public boolean verifyQueuesubTopicLinkPage(String qPageLinks)
			throws InterruptedException {
		logger.info("Verify page title of links on Queue Page");
		return (commonPage.verifySubTopiclinkPage(qPageLinkTitle, qPageLinks));
	}

	public void tryHere(String qPageLinks) {
		logger.info("Click on try here:" + qPageLinks);
		commonPage.clickonTryHere(tryHereLink);

	}

	public boolean VerifyAssementPage() {
		logger.info("Verify Assessment page");
		return (commonPage.verifyAssementPage());

	}

	public void enterCode(String code) throws InterruptedException {

		logger.info("Enter code:" + code);
		commonPage.enterCode(codeSpace1, code);
		commonPage.executeCode(runButton);

	}

	public boolean getOutput(String output) {
		logger.info("Validate code  output");
		return (commonPage.getOutput(Output, output));
	}
	public void subTopicSetUp(String qPageLinks) throws InterruptedException {
		commonPage.clickonSubTopicsLinks(qPageLinkCount, qPageLinks);
		commonPage.clickonTryHere(tryHereLink);

	}

}
