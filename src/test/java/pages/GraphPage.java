package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GraphPage extends BasePage {

	@FindBy(xpath = "//a[@href='graph']")
	private WebElement graphStartButton;

	@FindBy(xpath = "//ul//a[@class='list-group-item']")
	private List<WebElement> graphPageLinkCount;

	@FindBy(xpath = "//div/strong/p")
	private WebElement graphPageLinkTitle;

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

	@FindBy(xpath = "//a[text()='Graph']")
	private WebElement selectGraphOption;

	private static final Logger logger = LogManager.getLogger(GraphPage.class);

	// ElementActions elementActions;
	CommonPage commonPage;

	public GraphPage(WebDriver driver) {
		super(driver);
		// elementActions = new ElementActions(driver);
		commonPage = new CommonPage(driver);
	}

	public void goToGraphPage() {
		logger.info("Select Graph page");
		commonPage.goToPage(graphStartButton);

	}

	public boolean getGraphsubTopicLinkCount() {
		logger.info("Verifying count and names of links on Graph page");
		return (commonPage.getLinkCount(graphPageLinkCount) == 2);

	}

	public boolean getGraphsubTopicLinkName(String linkname) {
		logger.info("Verifying names of sub topic links on Graph page");
		return (commonPage.getSubTopicLinkName(graphPageLinkCount, linkname));
	}

	public void clickOnDropDown() {
		commonPage.clickOnDropDown(DSdropdown);
	}

	public void selectFromDropDown(String topic) throws InterruptedException {
		commonPage.selectFromDropDown(selectGraphOption);
	}

	public void clickonGraphsubTopicLinks(String GraphPageLinks)
			throws InterruptedException {
		logger.info("Click on links in Graph Page: " + GraphPageLinks);
		commonPage.clickonSubTopicsLinks(graphPageLinkCount, GraphPageLinks);
	}

	public boolean verifyGraphsubTopicLinkPage(String GraphPageLinks)
			throws InterruptedException {
		logger.info("Verify page title of links on Graph Page");
		return (commonPage.verifySubTopiclinkPage(graphPageLinkTitle,
				GraphPageLinks));
	}

	public void tryHere(String GraphPageLinks) {
		logger.info("Click on try here:" + GraphPageLinks);
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

	public void subTopicSetUp(String GraphPageLinks)
			throws InterruptedException {
		commonPage.clickonSubTopicsLinks(graphPageLinkCount, GraphPageLinks);
		commonPage.clickonTryHere(tryHereLink);

	}

}
