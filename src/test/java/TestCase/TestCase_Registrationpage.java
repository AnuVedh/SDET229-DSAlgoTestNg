package TestCase;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;

public class TestCase_Registrationpage extends BaseTest {

	
	@BeforeMethod
	public void commonsetup() throws IOException
	{

       
        pom.getHomePage().clickRegisterLink();
}
	@Test
	public void VerifyUsernamefieldVisiblity()
	{
		pom.getRegisterPage().usernameFieldvisible();
		
	}
	@Test
	public void VerifyPasswordfieldVisiblity()
	{
		pom.getRegisterPage().passwordFieldvisible();
	}
	@Test
	public void VerifyRegisterBtnVisiblity()
	{
		pom.getRegisterPage().registerBtnvisible();
	}



	@Test(dataProvider = "ExcelData2", dataProviderClass = ExcelUtil.class)
	public void VerifyRegisterTestcases(Map<String, String> testData)
	{
		Reporter.getCurrentTestResult().setAttribute("TestCaseData",
				"TC : " + testData.get("TestCase"));
		String expected_message=testData.get("Expected_Result");
		pom.getRegisterPage().enterUsername(testData.get("Username"));
		pom.getRegisterPage().enterPassword(testData.get("Password"));
		pom.getRegisterPage().enterConfirmPassword(testData.get("ConfirmPassword"));
		pom.getRegisterPage().clickRegister();
		 String actual_message=null;
		    System.out.println(testData.get("Username"));
		    if(testData.get("Username")=="")
		    {
		    	
		    	actual_message = pom.getRegisterPage().getUsernameValidationMessage();
		    	
		    }
		    else if(testData.get("Password")=="")
		    {
		    	actual_message = pom.getRegisterPage().getPasswordValidationMessage();
		    }
		    else if((testData.get("Username")!=null) && (testData.get("Password")!=null))
		    {
		    	 actual_message = pom.getRegisterPage().ErrorMsg();
		    }
			Assert.assertEquals(actual_message,expected_message);
		}
		
	
	
	
}