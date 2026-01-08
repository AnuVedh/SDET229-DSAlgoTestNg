package driverFactory;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.ConfigReader;

public class DriverFactory {
	private static final Logger logger = LogManager
			.getLogger(DriverFactory.class);
	private static final ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();

	private static WebDriver driver;

	public static WebDriver getDriver() {
		return tldriver.get();

	}

	public static void initDriver(String browser) {

		String browserName = browser;

		if (browserName == null) {

			browserName = ConfigReader.getProperty("browser");

		}

		logger.info("You selected " + browserName + " to run these tests");

		if (browserName.trim().equalsIgnoreCase("chrome")) {
			tldriver.set(new ChromeDriver());

		} else if (browserName.trim().equalsIgnoreCase("firefox")) {
			tldriver.set(new FirefoxDriver());

		} else if (browserName.trim().equalsIgnoreCase("edge")) {
			tldriver.set(new EdgeDriver());

		} else {
			logger.info("You selected wrong browser: " + browserName);
			return;
		}
		driver = tldriver.get();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().window().maximize();

	}

}