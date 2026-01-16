package baseTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;

import driverFactory.DriverFactory;
import pom.PageObjectManager;
@Listeners(ChainTestListener.class)
public class BaseTest {

	protected PageObjectManager pom;
	 protected WebDriver driver;
	@BeforeSuite

	public void LoadTestdata() throws Exception {
		Utils.ExcelUtil.getAllsheetData();
		System.out.println("Loada test data");
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
	
	 @AfterMethod()
	    public void attachScreenShotOnFailure(ITestResult testResult){
	        if(!testResult.isSuccess()){
	            ChainTestListener.embed(((TakesScreenshot)(driver)).getScreenshotAs(OutputType.BYTES),"image/png");
	        }
	    }
//	  /*Get Screen Shot*/
//    public byte[] takeScreenShot(){
//        return ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.BYTES);
//    }


	@AfterMethod(alwaysRun = true)
	public void quitBrowser() {

		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
		}
	}

}