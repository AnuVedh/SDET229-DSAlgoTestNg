package baseTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import driverFactory.DriverFactory;
import pom.PageObjectManager;

public class BaseTest {

	protected PageObjectManager pom;

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

}