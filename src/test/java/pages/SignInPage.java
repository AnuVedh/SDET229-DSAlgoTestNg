
package pages;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utils.ExcelUtil;
import webElementActions.ElementActions;

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
	private ElementActions elementActions;

	public SignInPage(WebDriver driver) {
		super(driver);
		elementActions = new ElementActions(driver);

	}

	public void clickOnSignIn() {

		logger.info("Click On SignIn Link");
		elementActions.clickAction(signInLink);

	}

	public String getPageTitle() {
		logger.info("Verify Sign-IN Page Title");

		elementActions.waitForUrlToContain("register");
		return driver.getTitle();
	}

	public void clickLink() {
		logger.info("Click on Register Link");
		elementActions.clickAction(registerLink);
	}

	public void Login(String username, String password) {
		logger.info("Login for user: " + username);
		elementActions.sendKeys(UsernameInputbox, username);
		elementActions.sendKeys(PasswordInputbox, password);
		elementActions.clickAction(LoginButton);
	}

	public void userLogin() throws IOException {

		logger.info("Login");
		List<Map<String, String>> userdata = ExcelUtil.getTestData("Account");
		elementActions.sendKeys(UsernameInputbox,
				userdata.get(0).get("Username"));
		elementActions.sendKeys(PasswordInputbox,
				userdata.get(0).get("Password"));
		elementActions.clickAction(LoginButton);

	}

	public boolean verifySuccesfulLogin(String expectedMessage) {

		logger.info("Verify Login ");
		return elementActions.isElementTextEquals(LoginAlert, expectedMessage);
	}

	public String verifySuccesfulLogintc(String testcase)
			throws InterruptedException {

		logger.info("Verify Login ");

		if (testcase.contains("empty username")) {
			return elementActions.JavaScriptvalidation(UsernameInputbox);
		} else if (testcase.contains("empty password")) {
			return elementActions.JavaScriptvalidation(PasswordInputbox);
		} else {
			return elementActions.getText(LoginAlert);
		}

	}

	public void Signout(String actualMessage) {

		if (actualMessage.trim().equalsIgnoreCase("You are logged in")) {
			elementActions.clickAction(Signoutlink);

		}
	}

	public void Signout() {

		if (driver.getTitle().equalsIgnoreCase("Assessment")) {
			driver.navigate().back();
		}

		elementActions.clickAction(Signoutlink);

	}
}
