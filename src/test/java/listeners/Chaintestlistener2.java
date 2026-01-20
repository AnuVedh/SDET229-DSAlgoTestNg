package listeners;

import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;

import Utils.Screenshot;

public class Chaintestlistener2 extends ChainTestListener {

	@Override
	public void onTestFailure(ITestResult result) {

		super.onTestFailure(result);

		try {
			String testName = result.getName();
			// driver = DriverFactory.getDriver();
			// final byte[] screenshot = ((TakesScreenshot) driver)
			// .getScreenshotAs(OutputType.BYTES);
			ChainTestListener.log(testName + "" + " testcase failed");// logs
																		// for
																		// failed
																		// testcases
			ChainTestListener.embed(Screenshot.byteScreenshot(), "image/png");// to
																				// attach
			// screenshot
			// for failed test
			// cases
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
