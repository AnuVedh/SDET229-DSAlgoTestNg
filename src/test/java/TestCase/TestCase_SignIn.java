package TestCase;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;

//@Listeners({io.qameta.allure.testng.AllureTestNg.class,
//		Utils.ExtentReport.class})
public class TestCase_SignIn extends BaseTest {

	private String loginMessage = null;

	@BeforeMethod()
	public void signInSetUp() {
		pom.getSignIN().clickOnSignIn();
	}

	@Test(priority = 1)
	public void VerifyRegisterLink() {

		pom.getSignIN().clickLink();
		Assert.assertEquals(pom.getSignIN().getPageTitle(), "Registration");
	}

	@Test(priority = 2)
	public void VerifyLogin() throws IOException, InterruptedException {
		String expectedMessage = "You are logged in";
		pom.getSignIN().userLogin();
		loginMessage = pom.getSignIN().verifySuccesfulLogintc(expectedMessage);
		Assert.assertEquals(loginMessage.trim(), expectedMessage);
	}

	@Test(dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class, retryAnalyzer = Utils.RetryAnalyzer.class, priority = 3)
	public void VerifySignInTestcases(Map<String, String> Logindata)
			throws IOException, InterruptedException {
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"TC : " + Logindata.get("TestCase"));
		pom.getSignIN().Login(Logindata.get("Username"),
				Logindata.get("Password"));
		loginMessage = pom.getSignIN()
				.verifySuccesfulLogintc(Logindata.get("TestCase"));

		Assert.assertEquals(loginMessage.trim(),
				Logindata.get("Expected Result").trim());
	}
}