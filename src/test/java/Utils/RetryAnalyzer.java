package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static ThreadLocal<Integer> retryCount = ThreadLocal
			.withInitial(() -> 0);

	@Override
	public boolean retry(ITestResult testResult) {
		// if (!testResult.isSuccess() && retrycount < 1)
		// if (result.getThrowable() instanceof AssertionError
		// && retryCount < maxRetryCount)
		// testResult.getStatus() == ITestResult.FAILURE &&
		if (retryCount.get() < 3) {
			System.out.println("retrycount" + retryCount);
			retryCount.set(retryCount.get() + 1);
			return true;
		}
		return false;
	}

}
