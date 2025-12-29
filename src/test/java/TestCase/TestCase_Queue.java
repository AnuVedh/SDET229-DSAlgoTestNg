package TestCase;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;
import testFlow.ModuleFlow;

public class TestCase_Queue extends BaseTest {

	private ModuleFlow moduleFlow;

	@BeforeMethod
	public void goToQueuePage() throws InterruptedException, IOException {

		moduleFlow = new ModuleFlow(pom);
		moduleFlow.commonSetUp();
		moduleFlow.moduleSetUp();
	}

	@AfterMethod
	public void SignOut() {

		moduleFlow.SignoutSetup();
		pom.getSignIN().Signout();

	}

	@Test
	public void VerifyQueueSubTopicLinkCount() {

		Assert.assertTrue(pom.getQueuePage().GetLinkCount());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
	public void VerifyQueueSubTopicLinkNames(Map<String, String> linkName) throws IOException {

		Assert.assertTrue(pom.getQueuePage().GetLinkName(linkName.get("QueuePageLinks")));
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
	public void VerifyQueueSubTopicsPage(Map<String, String> linkName) throws InterruptedException {

		moduleFlow.SubTopicSetup("Queue", linkName.get("QueuePageLinks"));
		boolean result = pom.getQueuePage().verifyQlinkPage(linkName.get("QueuePageLinks"));
		Assert.assertTrue(result);
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
	public void VerfiyTryHereLink(Map<String, String> linkName) throws InterruptedException {
		moduleFlow.SubTopicSetup("Queue", linkName.get("QueuePageLinks"));
		pom.getQueuePage().TryHere(linkName.get("QueuePageLinks"));
		Assert.assertTrue(pom.getQueuePage().VerifyAssementPage());

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
	public void VerifyCodeExecution(Map<String, String> linkName) throws InterruptedException {
		moduleFlow.SubTopicSetup("Queue", linkName.get("QueuePageLinks"));
		pom.getQueuePage().TryHere(linkName.get("QueuePageLinks"));
		pom.getQueuePage().EnterCode(linkName.get("Code"));
		Assert.assertTrue(pom.getQueuePage().getOutput(linkName.get("Output")));

	}

}