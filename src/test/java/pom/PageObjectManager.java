package pom;

import org.openqa.selenium.WebDriver;

import pages.GraphPage;
import pages.HomePage;
import pages.QueuePage;
import pages.SignInPage;

public class PageObjectManager {

	private WebDriver driver;

	// private DriverFactory driverFactory;
	private HomePage homePage;
	private SignInPage signIn;
	private QueuePage queue;
	private GraphPage graph;
	// private CommonPage commonPage;

	public PageObjectManager(WebDriver driver) {
		// driverFactory = new DriverFactory();
		// driver = driverFactory.getDriver();
		this.driver = driver;

		System.out.println("driver inside POM is " + driver);
	}

	// public DriverFactory getDriverFactory() {
	//
	// if (driverFactory == null) {
	// driverFactory = new DriverFactory();
	// }
	// return driverFactory;
	//
	// }

	public WebDriver getDriver() {
		return driver;
	}

	public HomePage getHomePage() {

		if (homePage == null) {
			homePage = new HomePage(driver);
		}
		return homePage;

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

	public GraphPage getGraphPage() {

		if (graph == null) {
			graph = new GraphPage(driver);
		}
		return graph;

	}

	// public CommonPage getCommonPage() {
	//
	// if (commonPage == null) {
	// commonPage = new CommonPage(driver);
	// }
	// return commonPage;

	// }

}
