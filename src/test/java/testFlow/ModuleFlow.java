package testFlow;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pages.HomePage;
import pages.QueuePage;
import pages.SignInPage;
import pom.PageObjectManager;

public class ModuleFlow {

	WebDriver driver;

	QueuePage queuePage;
	HomePage homePage;
	SignInPage signIn;

	public ModuleFlow(PageObjectManager pom) {
		this.queuePage = pom.getQueuePage();
		this.homePage = pom.getHomePage();
		this.signIn = pom.getSignIN();
		this.driver = pom.getDriver();
	}

	public void commonSetUp() {

		homePage.launchApplication();
		homePage.clickGetStarted();
		signIn.clickOnSignIn();

	}

	public void moduleSetUp() throws IOException {

		// homePage.launchApplication();
		// homePage.clickGetStarted();
		// signIn.clickOnSignIn();
		signIn.userLogin();
		goToPage();

	}

	public void SubTopicSetup(String moduleName, String linkName)
			throws InterruptedException {

		queuePage.ClickOnDropDown();
		queuePage.selectFromDropDown(moduleName);
		queuePage.clickonQLinks(linkName);

	}

	public void SignoutSetup() {

		if (driver.getTitle().equalsIgnoreCase("Assessment")) {
			driver.navigate().back();
		}

	}

	public void goToPage() {
		queuePage.goToQueuePAge();
	}

}
