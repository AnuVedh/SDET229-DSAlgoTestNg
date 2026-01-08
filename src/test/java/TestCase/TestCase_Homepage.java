package TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Map;
import Utils.ExcelUtil;
import baseTest.BaseTest;

public class TestCase_Homepage extends BaseTest {

	private static final Logger logger = LogManager
			.getLogger(TestCase_Homepage.class);
    
	
	@BeforeMethod
	public void commonsetup() 
	{
		pom.getHomePage().launchApplication();
		pom.getHomePage().clickGetStarted();
	}
	@Test
	public void navigateFromLaunchToHome() {

	
		logger.info("verifying title of home page");
	
		Assert.assertEquals(pom.getHomePage().fetchTitle(), "NumpyNinja");
	}
	@Test
	public void VerifyRegisterPageNavigation()
	{
		
		pom.getHomePage().clickRegisterLink();

		logger.info("verifying title of register page");
		
		Assert.assertEquals(pom.getHomePage().fetchregisterTitle(),"Register");
	}
	
	@Test
	public void VerifySignInPageNavigation() {


		pom.getHomePage().clickSignInLink();
		logger.info("verifying title of signin page");
		Assert.assertEquals(pom.getHomePage().fetchsigninpageTitle(), "Login");
		

	}

    //Dropdown without login
    @Test(priority = 1, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
    public void testDropdownWithoutLogin(Map<String, String> testData) throws Exception {
   //     if ("dropdownWithoutLogin".equals(testData.get("scenario"))) return;

        String topic = testData.get("topic");
        String expectedMessage = "You are not logged in";


        pom.getHomePage().selectTopicFromDropdown(topic);

        String actualMsg = pom.getHomePage().fetchErrorMsg();
        Assert.assertEquals(actualMsg, expectedMessage,
                "Warning message mismatch for topic: " + topic);
    }

    //Dropdown after login 
    @Test(priority = 2, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class,retryAnalyzer = Utils.RetryAnalyzer.class)
    public void testDropdownAfterLogin(Map<String, String> testData) throws Exception {

        String topic = testData.get("topic");
        String expectedHeading = testData.get("Expected_output").strip();


        pom.getHomePage().clickSignInLink();
        pom.getSignIN().userLogin();
        pom.getHomePage().selectTopicFromDropdown(topic);

        String actualHeading = pom.getHomePage().fetchIntroductionPageTitle();
        Assert.assertEquals(actualHeading, expectedHeading,
                "Introduction page title mismatch for topic: " + topic);
    }

    //GetStarted button without login
//    @Test(priority = 3, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
//    public void testGetStartedWithoutLogin(Map<String, String> testData) throws Exception {
//   
//
//        String topic = testData.get("topic");
//        String expectedMessage = testData.get("Expected_output");
//
//
//        pom.getHomePage().getStartedclick(topic);
//
//        String actualMsg = pom.getHomePage().fetchErrorMsg();
//        Assert.assertEquals(actualMsg, expectedMessage,
//                "Warning message mismatch for topic: " + topic);
//    }
    @Test(priority = 3, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
  public void testGetStartedWithoutLogin(Map<String, String> testData) throws Exception {
 

      String topic = testData.get("topic");
      String expectedMessage = "You are not logged in";


      pom.getHomePage().getStartedclick(topic);

      String actualMsg = pom.getHomePage().fetchErrorMsg();
      Assert.assertEquals(actualMsg, expectedMessage,
              "Warning message mismatch for topic: " + topic);
  }

    //GetStarted button after login
    @Test(priority = 4, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
    public void testGetStartedAfterLogin(Map<String, String> testData) throws Exception {
      
        String topic = testData.get("topic");
        String expectedHeading = testData.get("Expected_output").trim();


    	pom.getHomePage().clickSignInLink();
        pom.getSignIN().userLogin();
        pom.getHomePage().getStartedclick(topic);
        String actualHeading = pom.getHomePage().fetchIntroductionPageTitle();
        Assert.assertEquals(actualHeading, expectedHeading,
                "Introduction page title mismatch for topic: " + topic);
  
        
    }
    
 
}

