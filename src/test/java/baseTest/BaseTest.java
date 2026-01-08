package baseTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import DriverFactory.DriverFactory;
import pom.PageObjectManager;

public class BaseTest {

	protected PageObjectManager pom;

	@BeforeMethod
	public void launchBrowser() {
		DriverFactory.initDriver();
		pom = new PageObjectManager(DriverFactory.getDriver());
	}

	@AfterMethod
	public void quitBrowser() {

		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
		}
	}

}