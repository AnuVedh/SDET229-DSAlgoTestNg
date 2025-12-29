package baseTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import driverFactory.DriverFactory;
import pom.PageObjectManager;

public class BaseTest {

	protected PageObjectManager pom;
	// private ModuleFlow moduleFlow;

	@BeforeClass
	public void launchBrowser() {
		DriverFactory.initDriver();
		pom = new PageObjectManager(DriverFactory.getDriver());
	}

	@AfterClass
	public void quitBrowser() {

		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
		}
	}

	@BeforeMethod
	public void modulesetUp() {

		pom.getHomePage().launchApplication();
		pom.getHomePage().clickGetStarted();
		pom.getSignIN().clickOnSignIn();

	}

	//
	// @AfterMethod
	// public void takeScreenhot(Scenario scenario) {
	//
	// if (scenario.isFailed()) {
	// byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
	// .getScreenshotAs(OutputType.BYTES);
	// Allure.addAttachment("Failed " + scenario.getName(),
	// new ByteArrayInputStream(screenshot));// allure
	//
	// scenario.attach(screenshot, "image/png",
	// "Failed " + scenario.getName());// extent report
	// //}
	// }
}