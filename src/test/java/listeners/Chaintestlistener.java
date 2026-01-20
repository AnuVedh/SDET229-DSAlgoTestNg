package listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;

import driverFactory.DriverFactory;

public class Chaintestlistener implements ITestListener  {
	WebDriver driver =DriverFactory.getDriver();
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		String testName= result.getName();
		//driver = DriverFactory.getDriver();
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		ChainTestListener.log(testName+""+" testcase failed");//logs for failed testcases
		ChainTestListener.embed(screenshot, "img/png");//to attach screenshot for failed test cases
	}

}
