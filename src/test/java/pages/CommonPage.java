package pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import webElementActions.ElementActions;

public class CommonPage {

	private WebDriver driver;
	private ElementActions elementActions;

	public CommonPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
	}

	public void goToPage(WebElement element) {
		elementActions.scrollWaitAndClick(element);
	}

	public int getLinkCount(List<WebElement> element) {

		return elementActions.getElementCount(element);
	}

	public boolean getSubTopicLinkName(List<WebElement> element,
			String linkName) {

		return (elementActions.isElementPresentByText(element, linkName));

	}

	public boolean verifySubTopiclinkPage(WebElement element,
			String subTopicLinkName) throws InterruptedException {
		return elementActions.isElementTextEquals(element, subTopicLinkName);
	}

	public void clickOnDropDown(WebElement element) {
		elementActions.clickAction(element);

	}

	public void selectFromDropDown(WebElement element)
			throws InterruptedException {
		elementActions.clickAction(element);
	}

	public void clickonSubTopicsLinks(List<WebElement> element,
			String PageLinks) throws InterruptedException {
		elementActions.clickElementByText(element, PageLinks);
	}

	public void clickonTryHere(WebElement element) {

		elementActions.clickAction(element);
	}

	public boolean verifyAssementPage() {
		if (driver.getTitle().equalsIgnoreCase("Assessment")) {
			return true;
		}
		return false;
	}

	public void enterCode(WebElement element, String code)
			throws InterruptedException {

		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
		actions.sendKeys(element, code).perform();
	}

	public void executeCode(WebElement element) {
		elementActions.clickAction(element);
	}

	public boolean getOutput(WebElement element, String output) {

		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			System.out.println(alertMsg);
			System.out.println(output);
			return (alertMsg.equalsIgnoreCase(output.trim()));
		} catch (NoAlertPresentException e) {
			return (element.getText().equalsIgnoreCase(output.trim()));
		}
	}
}
