package Utils;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	// @Override
	public void onTestStart(ITestResult result) {

		// TODO Auto-generated method stub

		// test = extent.createTest(result.getName());
		// test = extent.createTest(result.getTestClass().getName());
		// test.log(Status.INFO, result.getName());

	}

	// @Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		String browser = result.getTestContext().getCurrentXmlTest()
				.getParameter("browser");

		if (result.getAttribute("TestCaseData") != null) {
			test = extent.createTest(result.getTestClass().getName() + " _ "
					+ result.getName() + "_"
					+ result.getAttribute("TestCaseData") + "_" + browser);

		} else {
			test = extent.createTest(
					result.getTestClass().getName() + " _ " + result.getName());

		}
		test.log(Status.PASS, result.getName());

	}

	//// @Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		String browser = result.getTestContext().getCurrentXmlTest()
				.getParameter("browser");
		if (result.getAttribute("TestCaseData") != null) {
			test = extent.createTest(result.getTestClass().getName() + " _ "
					+ result.getName() + "_"
					+ result.getAttribute("TestCaseData") + "_" + browser);

		} else {
			test = extent.createTest(
					result.getTestClass().getName() + " _ " + result.getName());
		}

		test.log(Status.FAIL, result.getName());
		test.log(Status.FAIL, result.getThrowable())
				.addScreenCaptureFromBase64String(Screenshot.base64Sceenshot(),
						"Test Failure Screenshot");
		Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(
				Screenshot.byteScreenshot()));/* no annotation needed */

	}

	// @Override
	public void onTestSkipped(ITestResult result) {

		String browser = result.getTestContext().getCurrentXmlTest()
				.getParameter("browser");
		if (result.getAttribute("TestCaseData") != null) {
			test = extent.createTest(result.getTestClass().getName() + " _ "
					+ result.getName() + "_"
					+ result.getAttribute("TestCaseData") + "_" + browser);

		} else {
			test = extent.createTest(
					result.getTestClass().getName() + " _ " + result.getName());

		}
		test.log(Status.SKIP, result.getName());

	}

	// @Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

		String Timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS")
				.format(new Date());
		String repname = "DS-Algo TestReport" + Timestamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\Report\\" + repname);
		sparkReporter.config().setDocumentTitle("DSALgo-DataStructure");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

	}

	// @Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		extent.flush();

	}

}
