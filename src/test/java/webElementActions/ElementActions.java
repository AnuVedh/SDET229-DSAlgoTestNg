package webElementActions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	private WebDriver driver;
	private WebDriverWait wait;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickAction(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void scrollWaitAndClick(WebElement element) {

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({block:'center'});", element);

		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public int getElementCount(List<WebElement> element) {
		return element.size();
	}

	public void clickElementByText(List<WebElement> elements, String text) {
		for (WebElement element : elements) {
			if (element.getText().trim().equalsIgnoreCase(text.trim())) {
				clickAction(element);
				return;
			}
		}
		throw new RuntimeException("Element with text not found: " + text);
	}

	public boolean isElementPresentByText(List<WebElement> elements,
			String text) {

		if (elements == null || elements.isEmpty()) {
			return false;
		}
		for (WebElement element : elements) {
			if (element.getText() != null
					&& element.getText().trim().equalsIgnoreCase(text.trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean isElementTextEquals(WebElement element,
			String expectedText) {

		if (element == null || expectedText == null) {
			return false;
		}
		String actualText = element.getText().trim();
		return actualText.equalsIgnoreCase(expectedText.trim());
	}

}
