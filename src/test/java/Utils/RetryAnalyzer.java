package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int retrycount = 0;
	@Override
	public boolean retry(ITestResult testResult) {
		// if (!testResult.isSuccess() && retrycount < 1)
		// if (result.getThrowable() instanceof AssertionError
		// && retryCount < maxRetryCount)
		// testResult.getStatus() == ITestResult.FAILURE &&
		if (retrycount < 3) {
			System.out.println("retrycount" + retrycount);
			retrycount++;
			return true;
		}
		return false;
	}

}
