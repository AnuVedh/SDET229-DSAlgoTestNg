
package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ExcelUtil;

public class SignInPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(SignInPage.class);
	// private WebDriver driver = super.driver;
	@FindBy(xpath = "//a[text()='Sign in']")
	private WebElement signInLink;

	@FindBy(xpath = "//a[text()='Register!']")
	private WebElement registerLink;

	@FindBy(id = ("id_username"))
	private WebElement UsernameInputbox;

	@FindBy(id = ("id_password"))
	private WebElement PasswordInputbox;

	@FindBy(xpath = ("//input[@value='Login']"))
	private WebElement LoginButton;

	@FindBy(xpath = ("//div[@role='alert']"))
	private WebElement LoginAlert;

	@FindBy(xpath = ("//div[@class='col-sm']"))
	private WebElement AlertWindow;
	// @FindBy(linkText = ("Sign out"))
	// private List<WebElement> Signoutlink;
	@FindBy(linkText = ("Sign out"))
	private WebElement Signoutlink;

	public SignInPage(WebDriver driver) {
		super(driver);

	}

	public void clickOnSignIn() {

		logger.info("Click On SignIn Link");

		signInLink.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("login"));

	}

	public String getPageTitle() {
		logger.info("Verify Sign-IN Page Title");
		return driver.getTitle();
	}

	public void clickLink() {
		logger.info("Click on Register Link");
		registerLink.click();
	}

	public void Login(String username, String password) {
		logger.info("Login for user: " + username);
		UsernameInputbox.sendKeys(username);
		PasswordInputbox.sendKeys(password);
		LoginButton.click();

	}

	public void userLogin() throws IOException {

		logger.info("Login");
		List<Map<String, String>> userdata = ExcelUtil.getTestData("Account");
		UsernameInputbox.sendKeys(userdata.get(0).get("Username"));
		PasswordInputbox.sendKeys(userdata.get(0).get("Password"));
		LoginButton.click();

	}

	public String verifySuccesfulLogin() {

		logger.info("Verify Login ");
		return LoginAlert.getText();
	}

	public String verifySuccesfulLogintc(String testcase)
			throws InterruptedException {

		logger.info("Verify Login ");
		String validationMessage1 = null;

		if (testcase.contains("empty")) {

			// Thread.sleep(1000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			validationMessage1 = (String) js.executeScript(
					"return arguments[0].validationMessage;", UsernameInputbox);
			if (validationMessage1.isBlank()) {
				validationMessage1 = (String) js.executeScript(
						"return arguments[0].validationMessage;",
						PasswordInputbox);
			}
		} else {
			validationMessage1 = LoginAlert.getText();
		}
		System.out.println(validationMessage1);

		return validationMessage1;

	}

	public void Signout(String actualMessage) {
		System.out.println("actual message is" + actualMessage);
		// if (!(actualMessage == null)) {
		if (actualMessage.trim().equalsIgnoreCase("You are logged in")) {
			Signoutlink.click();
		}
	}

	public void Signout() {

		if (driver.getTitle().equalsIgnoreCase("Assessment")) {
			driver.navigate().back();
		}

		// try {
		// if (Signoutlink.isDisplayed()) {
		// Signoutlink.click();
		// }
		// } catch (NoSuchElementException e) {
		//
		// }
		// if (!Signoutlink.isEmpty()) {
		// Signoutlink.get(0).click();
		//
		// // driver.close();
		// }
		Signoutlink.click();
	}
}
