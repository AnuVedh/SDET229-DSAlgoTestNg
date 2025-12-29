package TestCase;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;

public class TestCase_Graph extends BaseTest {

	@BeforeMethod
	public void goToGraphPage() throws InterruptedException, IOException {

		pom.getSignIN().userLogin();
		pom.getGraphPage().goToGraphPage();

	}

	@AfterMethod
	public void SignOut() {

		pom.getSignIN().Signout();

	}

	@Test
	public void VerifyGraphSubTopicLinkCount() {

		Assert.assertTrue(pom.getGraphPage().getGraphsubTopicLinkCount());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
	public void VerifyGraphSubTopicLinkNames(Map<String, String> linkName)
			throws IOException {

		Assert.assertTrue(pom.getGraphPage()
				.getGraphsubTopicLinkName(linkName.get("GraphPageLinks")));
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyGraphSubTopicsPage(Map<String, String> linkName)
			throws InterruptedException {

		pom.getGraphPage()
				.clickonGraphsubTopicLinks(linkName.get("GraphPageLinks"));
		boolean result = pom.getGraphPage()
				.verifyGraphsubTopicLinkPage(linkName.get("GraphPageLinks"));
		Assert.assertTrue(result);
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerfiyGraphSubtopicTryHereLink(Map<String, String> linkName)
			throws InterruptedException {

		pom.getGraphPage()
				.clickonGraphsubTopicLinks(linkName.get("GraphPageLinks"));
		pom.getGraphPage().tryHere(linkName.get("GraphPageLinks"));
		Assert.assertTrue(pom.getQueuePage().VerifyAssementPage());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, groups = {
			"SubTopic"})
	public void VerifyGraphSubtopicCodeExecution(Map<String, String> linkName)
			throws InterruptedException {
		pom.getQueuePage().subTopicSetUp(linkName.get("GraphPageLinks"));
		pom.getQueuePage().enterCode(linkName.get("Code"));
		Assert.assertTrue(pom.getQueuePage().getOutput(linkName.get("Output")));

	}

}
