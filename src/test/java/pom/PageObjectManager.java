package pom;

import org.openqa.selenium.WebDriver;

import pages.ArraysPage;
import pages.HomePage;
import pages.QueuePage;
import pages.SignInPage;

public class PageObjectManager {

	private WebDriver driver;

	// private DriverFactory driverFactory;
	private HomePage homePage;
	private SignInPage signIn;
	private QueuePage queue;
	private ArraysPage arrayspage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public HomePage getHomePage() {

		if (homePage == null) {
			homePage = new HomePage(driver);
		}
		return homePage;

	}
	public ArraysPage getArraysPage() {

		if (arrayspage == null) {
			arrayspage = new ArraysPage(driver);
		}
		return arrayspage;

	}
	public SignInPage getSignIN() {

		if (signIn == null) {
			signIn = new SignInPage(driver);
		}
		return signIn;

	}

	public QueuePage getQueuePage() {

		if (queue == null) {
			queue = new QueuePage(driver);
		}
		return queue;

	}

}
