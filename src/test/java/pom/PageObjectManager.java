package pom;

import org.openqa.selenium.WebDriver;

import pages.ArraysPage;
import pages.GraphPage;
import pages.HomePage;
import pages.QueuePage;
import pages.RegistrationPage;
import pages.SignInPage;

public class PageObjectManager {

	private WebDriver driver;

	private HomePage homePage;
	private RegistrationPage registerPage;
	private SignInPage signIn;
	private QueuePage queue;
	private GraphPage graph;
	private ArraysPage array;

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

	public RegistrationPage getRegisterPage() {

		if (registerPage == null) {
			registerPage = new RegistrationPage(driver);
		}
		return registerPage;
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

	public ArraysPage getArraysPage() {

		if (array == null) {
			array = new ArraysPage(driver);
		}
		return array;

	}

}
