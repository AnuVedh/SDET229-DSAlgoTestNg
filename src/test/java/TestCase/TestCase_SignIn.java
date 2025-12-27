package TestCase;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;
import testFlow.ModuleFlow;

public class TestCase_SignIn extends BaseTest {

	private ModuleFlow moduleFlow;

	@BeforeMethod()
	public void GotoSignInPage() {
		moduleFlow = new ModuleFlow(pom);
		moduleFlow.commonSetUp();
	}

	@Test()
	public void VerifyRegisterLink() {

		pom.getSignIN().clickLink();
		Assert.assertEquals(pom.getSignIN().getPageTitle(), "Registration");
	}

	@Test()
	public void VerifyLogin() throws IOException {
		String expectedMessage = "You are logged in";
		pom.getSignIN().userLogin();
		String loginMessage = pom.getSignIN().verifySuccesfulLogin();
		pom.getSignIN().Signout(loginMessage);
		Assert.assertEquals(loginMessage, expectedMessage);
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, retryAnalyzer = Utils.RetryAnalyzer.class)
	public void VerifySignInTestcases(Map<String, String> Logindata) throws IOException, InterruptedException {
		pom.getSignIN().Login(Logindata.get("Username"), Logindata.get("Password"));
		String loginMessage = pom.getSignIN().verifySuccesfulLogintc(Logindata.get("TestCase"));
		pom.getSignIN().Signout(loginMessage);
		System.out.println("count");
		Assert.assertEquals(loginMessage.trim(), Logindata.get("Expected Result").trim());
	}
}