package TestCase;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SignInPage;

public class TestCase_Home {
	private static final Logger logger = LogManager
			.getLogger(TestCase_Home.class);
    
	HomePage homepage = new HomePage();
	SignInPage signIn = new SignInPage();

	@Given("The user opens DSAlgo portal link")
	public void user_is_in_launch_page() {

		homepage.launchApplication();

	}

	@When("The User clicks on GetStarted")
	public void user_user_clicks_on_get_started() {
		homepage.clickGetStarted();
	}

	@Then("User should navigate to Home page")
	public void user_should_navigate_to_home_page() {
		logger.info("verifying title of home page");
		Assert.assertEquals(homepage.fetchTitle(), "NumpyNinja");
	}

	@Given("User is on home page")
	public void user_is_on_home_page() {

		homepage.launchApplication();
		homepage.clickGetStarted();

	}

	@When("User clicks the {string} link")
	public void user_clicks_the_link(String link) {
		homepage.clickLink(link);
	}

	@Then("User should be navigated to the registration page")
	public void user_should_be_navigated_to_the_registration_page() {

		logger.info("verifying title of register page");

		Assert.assertEquals(homepage.fetchregisterTitle(), "Register");
	}

	@Then("User should be navigated to the SignIn page")
	public void user_should_be_navigated_to_the_sign_in_page() {

		logger.info("verifying title of signin page");
		Assert.assertEquals(homepage.fetchsigninpageTitle(), "Login");

	}

	@When("The User select {string} from dropdown menu")
	public void the_user_select_from_dropdown_menu(String topiclink) {
		homepage.selectTopicFromDropdown(topiclink);
	}

	@Then("The user get warning message {string}")
	public void the_user_get_warning_message(String expectedMsg) {

		logger.info(
				"Verifying error message when clicking on dropdown options without signin");
		String actualMsg = homepage.fetchErrorMsg();
		Assert.assertEquals(actualMsg, expectedMsg,
				"Warning message does not match. Expected: " + expectedMsg
						+ " but found: " + actualMsg);
	}

	@Given("User is signed in and on Home Page")
	public void user_is_signed_in_and_on_home_page() throws IOException {
		homepage.launchApplication();
		homepage.clickGetStarted();

		homepage.gotosignin();
		signIn.userLogin();

	}

	@Then("User should be navigating to the corresponding {string} introduction page")
	public void user_should_be_navigating_to_the_corresponding_introduction_page(
			String expectedTitle) {
		String actualTitle = homepage.fetchIntroductionPageTitle();

		logger.info(
				"verifying page navigating to corresponding page after signin");
		Assert.assertEquals(actualTitle, expectedTitle,
				"page title does not match");

	}

	@When("The user clicks on Get Started button for corresponding {string}")
	public void the_user_clicks_on_get_started_button_for_corresponding(
			String topic) {
		homepage.getStartedclick(topic);
	}

}