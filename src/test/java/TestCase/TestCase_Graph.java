package TestCase;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;

public class TestCase_Graph extends BaseTest {

	@BeforeMethod
	public void goToGraphPage() throws InterruptedException, IOException {

		// this.context = context;
		pom.getSignIN().clickOnSignIn();
		pom.getSignIN().userLogin();
		pom.getGraphPage().goToGraphPage();

	}

	@Test
	public void VerifyGraphSubTopicLinkCount() {

		Assert.assertTrue(pom.getGraphPage().getGraphsubTopicLinkCount());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
	public void VerifyGraphSubTopicLinkNames(Map<String, String> linkName)
			throws IOException {
		// context.setAttribute("TestCaseData", linkName.get("GraphPageLinks"));

		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Graph Subtopic :" + linkName.get("GraphPageLinks"));
		Assert.assertTrue(pom.getGraphPage()
				.getGraphsubTopicLinkName(linkName.get("GraphPageLinks")));
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyGraphSubTopicsPage(Map<String, String> linkName)
			throws InterruptedException {
		// context.setAttribute("TestCaseData", linkName.get("GraphPageLinks"));
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Graph Subtopic :" + linkName.get("GraphPageLinks"));
		pom.getGraphPage()
				.clickonGraphsubTopicLinks(linkName.get("GraphPageLinks"));
		// boolean result = pom.getGraphPage()
		// .verifyGraphsubTopicLinkPage(linkName.get("GraphPageLinks"));
		Assert.assertTrue(pom.getGraphPage()
				.verifyGraphsubTopicLinkPage(linkName.get("GraphPageLinks")));
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerfiyGraphSubtopicTryHereLink(Map<String, String> linkName)
			throws InterruptedException {
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Graph Subtopic :" + linkName.get("GraphPageLinks"));
		// context.setAttribute("TestCaseData", linkName.get("GraphPageLinks"));
		pom.getGraphPage()
				.clickonGraphsubTopicLinks(linkName.get("GraphPageLinks"));
		pom.getGraphPage().tryHere(linkName.get("GraphPageLinks"));
		Assert.assertTrue(pom.getQueuePage().VerifyAssementPage());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyGraphSubtopicCodeExecutionValid(
			Map<String, String> linkName) throws InterruptedException {
		// context.setAttribute("TestCaseData", linkName.get("GraphPageLinks"));
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Graph Subtopic :" + linkName.get("GraphPageLinks"));
		pom.getQueuePage().subTopicSetUp(linkName.get("GraphPageLinks"));
		pom.getQueuePage().enterCode(linkName.get("Code"));
		Assert.assertTrue(pom.getQueuePage().getOutput(linkName.get("Output")));

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyGraphSubtopicCodeExecutionInvalid(
			Map<String, String> linkName) throws InterruptedException {
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"Graph Subtopic :" + linkName.get("GraphPageLinks"));
		pom.getQueuePage().subTopicSetUp(linkName.get("GraphPageLinks"));
		pom.getQueuePage().enterCode(linkName.get("InvalidCode"));
		Assert.assertTrue(
				pom.getQueuePage().getOutput(linkName.get("InvalidOutput")));

	}

}
