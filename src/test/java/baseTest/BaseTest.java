
package baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import driverFactory.DriverFactory;
import pom.PageObjectManager;

//@Listeners({ChainTestListener.class, Chaintestlistener.class})
public class BaseTest {

	protected PageObjectManager pom;
	protected WebDriver driver;
	@BeforeSuite

	public void LoadTestdata() throws Exception {
		Utils.ExcelUtil.getAllsheetData();

	}

	@BeforeMethod
	@Parameters({"browser"})
	public void launchBrowser(@Optional("chrome") String browser)
			throws Exception {
		DriverFactory.initDriver(browser);
		pom = new PageObjectManager(DriverFactory.getDriver());
		pom.getHomePage().launchApplication();
		pom.getHomePage().clickGetStarted();

	}

	@AfterMethod(alwaysRun = true)
	public void quitBrowser() {

		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
		}
	}

}
