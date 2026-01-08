package baseTest;




import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


import driverFactory.DriverFactory;
import pom.PageObjectManager;

public class BaseTest {

	protected PageObjectManager pom;
	// private ModuleFlow moduleFlow;




	@BeforeSuite

	public void LoadTestdata() throws Exception {
		Utils.ExcelUtil.getAllsheetData();
		System.out.println("Loada test data");
	}

	@BeforeMethod
	@Parameters({"browser"})
	public void launchBrowser(String browser) throws Exception {
		DriverFactory.initDriver(browser);
		pom = new PageObjectManager(DriverFactory.getDriver());
		pom.getHomePage().launchApplication();
		pom.getHomePage().clickGetStarted();

	}


	@AfterMethod
	public void quitBrowser() {

		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
		}
	}

}