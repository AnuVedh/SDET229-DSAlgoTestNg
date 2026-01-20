
package Utils;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.qameta.allure.Allure;

public class ExtentReport implements ITestListener {

	private static ConcurrentHashMap<String, ExtentReports> reportMap = new ConcurrentHashMap<>();
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		String browser = context.getCurrentXmlTest().getParameter("browser");
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		String repname = "DS-Algo TestReport_" + browser + "_" + timestamp
				+ ".html";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
				".\\Report\\" + repname);
		sparkReporter.config().setDocumentTitle("DS-Algo DataStructure");
		sparkReporter.config().setReportName("Functional Testing - " + browser);
		sparkReporter.config().setTheme(Theme.DARK);

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Browser", browser);

		reportMap.put(browser, extent);
	}

	@Override
	public void onTestStart(ITestResult result) {
		String browser = result.getTestContext().getCurrentXmlTest()
				.getParameter("browser");
		ExtentReports extent = reportMap.get(browser);

		String className = result.getTestClass().getName();

		System.out.println(parentTest.get());
		System.out.println(className);

		if (parentTest.get() == null
				|| !parentTest.get().getModel().getName().equals(className)) {

			ExtentTest parent = extent.createTest(className);
			parentTest.set(parent);
		}

		String nodeName = result.getMethod().getMethodName() + "_" + browser;
		ExtentTest child = parentTest.get().createNode(nodeName);
		test.set(child);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Object testName = "";
		if (result.getAttribute("TestCaseData") != null) {
			testName = "_" + (result.getAttribute("TestCaseData"));
		}
		test.get().log(Status.PASS,
				"Test Passed: " + result.getName() + "_" + testName);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = "";

		Object[] params = result.getParameters();

		if (params.length > 0) {

			testName = "_" + (result.getMethod().getMethodName()) + params[0];
		}
		test.get().log(Status.FAIL,
				"Test Failed: " + result.getName() + testName);
		test.get().log(Status.FAIL, result.getThrowable());

		// Screenshot logic
		test.get().addScreenCaptureFromBase64String(
				Screenshot.base64Sceenshot(), "Failure Screenshot");
		Allure.addAttachment("Failure Screenshot",
				new ByteArrayInputStream(Screenshot.byteScreenshot()));
	}

	@Override
	public void onFinish(ITestContext context) {
		String browser = context.getCurrentXmlTest().getParameter("browser");
		ExtentReports extent = reportMap.get(browser);

		if (extent != null) {
			extent.flush();
		}

		// Clean up ThreadLocals for this thread
		parentTest.remove();
		test.remove();
	}
}