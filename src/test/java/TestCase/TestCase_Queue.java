package TestCase;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;

public class TestCase_Queue extends BaseTest {

	// private ITestContext context;

	@BeforeMethod
	public void goToQueuePage() throws InterruptedException, IOException {
		// this.context = context;
		pom.getSignIN().clickOnSignIn();
		pom.getSignIN().userLogin();
		pom.getQueuePage().goToQueuePAge();
	}

	// @AfterMethod
	// public void SignOut() {
	// pom.getSignIN().Signout();
	// }

	@Test
	public void VerifyQueueSubTopicLinkCount() {

		Assert.assertTrue(pom.getQueuePage().getQsubTopicLinkCount());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
	public void VerifyQueueSubTopicLinkNames(Map<String, String> linkName)
			throws IOException {

		// context.setAttribute("TestCaseData", linkName.get("QueuePageLinks"));
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Queue Subtopic :" + linkName.get("QueuePageLinks"));
		Assert.assertTrue(pom.getQueuePage()
				.getQsubTopicLinkName(linkName.get("QueuePageLinks")));
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyQueueSubTopicsPage(Map<String, String> linkName)
			throws InterruptedException {

		// context.setAttribute("TestCaseData", linkName.get("QueuePageLinks"));
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Queue Subtopic :" + linkName.get("QueuePageLinks"));
		pom.getQueuePage()
				.clickonQueueSubtopicLinks(linkName.get("QueuePageLinks"));
		// boolean result = pom.getQueuePage()
		// .verifyQueuesubTopicLinkPage(linkName.get("QueuePageLinks"));
		Assert.assertTrue(pom.getQueuePage()
				.verifyQueuesubTopicLinkPage(linkName.get("QueuePageLinks")));
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerfiyQueueSubtopicTryHereLink(Map<String, String> linkName)
			throws InterruptedException {

		// context.setAttribute("TestCaseData", linkName.get("QueuePageLinks"));
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Queue Subtopic :" + linkName.get("QueuePageLinks"));
		pom.getQueuePage()
				.clickonQueueSubtopicLinks(linkName.get("QueuePageLinks"));
		pom.getQueuePage().tryHere(linkName.get("QueuePageLinks"));
		Assert.assertTrue(pom.getQueuePage().VerifyAssementPage());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyQueueSubtopicCodeExecutionValid(
			Map<String, String> linkName) throws InterruptedException {
		// context.setAttribute("TestCaseData", linkName.get("QueuePageLinks"));
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Queue Subtopic :" + linkName.get("QueuePageLinks"));
		pom.getQueuePage().subTopicSetUp(linkName.get("QueuePageLinks"));
		pom.getQueuePage().enterCode(linkName.get("Code"));
		Assert.assertTrue(pom.getQueuePage().getOutput(linkName.get("Output")));

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyQueueSubtopicCodeExecutionInvalid(
			Map<String, String> linkName) throws InterruptedException {

		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Queue Subtopic :" + linkName.get("QueuePageLinks"));
		pom.getQueuePage().subTopicSetUp(linkName.get("QueuePageLinks"));
		pom.getQueuePage().enterCode(linkName.get("InvalidCode"));
		Assert.assertTrue(
				pom.getQueuePage().getOutput(linkName.get("InvalidOutput")));

	}

}