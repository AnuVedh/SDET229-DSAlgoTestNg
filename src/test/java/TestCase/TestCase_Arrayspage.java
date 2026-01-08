package TestCase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
//import org.apache.logging.log4j.core.util.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ExcelUtil;
import baseTest.BaseTest;
import pages.ArraysPage;

public class TestCase_Arrayspage extends BaseTest{
	
	private static final Logger logger = LogManager.getLogger(TestCase_Arrayspage.class);
	
	
	
	@BeforeMethod
	public void commonsetup() throws IOException
	{

        pom.getHomePage().launchApplication();
        pom.getHomePage().clickGetStarted();
        pom.getHomePage().clickSignInLink();
        pom.getSignIN().userLogin();
	}


    @Test(priority = 2, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
    public void verifyTopicNavigation(Map<String, String> testData) throws IOException {

    	String topicName = testData.get("ArraysSubtopic");
    	pom.getArraysPage().arraysgetstartedClick();
    	pom.getArraysPage().topicsCovered(topicName);
     
  

        String actualTitle = pom.getArraysPage().fetchtitlepage();
        Assert.assertEquals(actualTitle, topicName, "Title mismatch for topic: " + topicName);
    }
    @Test
    public void verifyTryHereOpensEditor(String topicName) throws IOException
    {

    	pom.getArraysPage().arraysgetstartedClick();
    	pom.getArraysPage().topicsCovered(topicName);
    	pom.getArraysPage().tryhere();
    	Assert.assertTrue(pom.getArraysPage().textEditorIsDisplayed(), "Text editor not displayed");
    }



    	
    // Execute Python code from Excel and verify output
    @Test(priority = 4, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
    public void verifyValidPythonCodeExecution(Map<String, String> testData) throws IOException
    {
    	 String arraysubtopic = testData.get("ArraysSubtopic");
    	 String code = testData.get("Code");
         String expectedMessage = testData.get("Output");


         pom.getArraysPage().arraysgetstartedClick();
         pom.getArraysPage().topicsCovered(arraysubtopic);
         pom.getArraysPage().tryhere();
         pom.getArraysPage().runPythonCode(code);

         String actualMsg = pom.getArraysPage().getResult();
         Assert.assertEquals(actualMsg, expectedMessage,
                 "Warning message mismatch for topic: ");
       
        }


    // Execute Python code from Excel and verify output
    @Test(priority = 4, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
    public void verifyInValidPythonCodeExecution(Map<String, String> testData) throws IOException
    {
    	 String arraysubtopic = testData.get("ArraysSubtopic");
    	 String code = testData.get("Code");
         String expectedMessage = testData.get("Output");


         pom.getArraysPage().arraysgetstartedClick();
         pom.getArraysPage().topicsCovered(arraysubtopic);
         pom.getArraysPage().tryhere();
         pom.getArraysPage().runPythonCode(code);

         String actualMsg = pom.getArraysPage().getResult();
         Assert.assertEquals(actualMsg, expectedMessage,
                 "Warning message mismatch for topic: ");
       
        }
    
    //Test 5: Verify Practice Questions page navigation
    @Test  
    public void verifyPracticeQuestionsNavigation() throws IOException {

        pom.getArraysPage().arraysgetstartedClick();
        pom.getArraysPage().topicsCovered("Applications of Array");
        pom.getArraysPage().practicequeLink();

        String title = pom.getArraysPage().getPageTitle();
        Assert.assertEquals(title, "Practice Questions");
    }

   // Test 6: Verify each practice question subtopic opens correctly
    @Test(dataProvider = "ExcelData" , dataProviderClass = ExcelUtil.class)
    public void verifyPracticeQuestionSubpageNavigation(Map<String,String> testData) throws IOException
    {

    	String subTopics = testData.get("Practicesubtopic");
        pom.getArraysPage().arraysgetstartedClick();
        pom.getArraysPage().topicsCovered("Applications of Array");
        pom.getArraysPage().practicequeLink();
        pom.getArraysPage().practicesubTopiclink(subTopics);
        
        String actualTitle = pom.getArraysPage().subtopicTitle();
        Assert.assertEquals(actualTitle, "QUESTION");


    	
    }
    
    
    @Test(priority = 4, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class,retryAnalyzer = Utils.RetryAnalyzer.class)
    public void verifyPracticepageValidPythonCodeExecution(Map<String, String> testData) throws IOException
    {
    	 String Practicesubtopic = testData.get("Practicesubtopic");
    	 String code = testData.get("Code");
         String expectedMessage = testData.get("Output");

//         pom.getHomePage().launchApplication();
//         pom.getHomePage().clickGetStarted();
//         pom.getHomePage().clickSignInLink();
//         pom.getSignIN().userLogin();
         pom.getArraysPage().arraysgetstartedClick();
         pom.getArraysPage().topicsCovered("Applications of Array");
         pom.getArraysPage().practicequeLink();
         pom.getArraysPage().practicesubTopiclink(Practicesubtopic);

        
         pom.getArraysPage().runPythonCode(code);

         String actualMsg = pom.getArraysPage().getResult();
         Assert.assertEquals(actualMsg, expectedMessage,
                 "Warning message mismatch for topic: ");
       
     }


    @Test(priority = 4, dataProvider = "ExcelData", dataProviderClass = ExcelUtil.class)
    public void verifyPracticepageInValidPythonCodeExecution(Map<String, String> testData) throws IOException
    {
    	 String Practicesubtopic = testData.get("Practicesubtopic");
    	 String code = testData.get("Code");
         String expectedMessage = testData.get("Output");

//         pom.getHomePage().launchApplication();
//         pom.getHomePage().clickGetStarted();
//         pom.getHomePage().clickSignInLink();
//         pom.getSignIN().userLogin();
         pom.getArraysPage().arraysgetstartedClick();
         pom.getArraysPage().topicsCovered(Practicesubtopic);
         pom.getArraysPage().tryhere();
         pom.getArraysPage().runPythonCode(code);

         String actualMsg = pom.getArraysPage().getResult();
         Assert.assertEquals(actualMsg, expectedMessage,
                 "Warning message mismatch for topic: ");
       
        }

}
