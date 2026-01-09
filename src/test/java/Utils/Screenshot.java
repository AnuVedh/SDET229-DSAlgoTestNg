package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driverFactory.DriverFactory;
import io.qameta.allure.Attachment;

public class Screenshot {

	static Date date = new Date();
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
	static String stringDate = sdf1.format(date);
	private static WebDriver driver;
	// private static WebDriver driver =

	public static String fullPageScreenshot(String pageTitle)
			throws IOException {

		pageTitle += stringDate;
		System.out.println(pageTitle);
		String FilePath = System.getProperty("user.dir")
				+ "\\test-output\\screenshots\\" + pageTitle + ".png";
		TakesScreenshot takephoto = (TakesScreenshot) driver;
		File capturePhotoFile = takephoto.getScreenshotAs(OutputType.FILE);
		File savePhotoFile = new File(FilePath);
		FileUtils.copyFile(capturePhotoFile, savePhotoFile);
		return FilePath;
		// System.out.println(scenario);
		// scenario.attach(capturePhotoFile, ".png",
		// capturePhotoFile.toString());

	}

	public static void elementScreenshot(String pageTitle,
			WebElement pageElement) throws IOException {

		pageTitle += stringDate;
		System.out.println(pageTitle);

		File capturePhotoFile = pageElement.getScreenshotAs(OutputType.FILE);
		File savePhotoFile = new File(System.getProperty("user.dir")
				+ "\\test-output\\screenshots\\" + pageTitle + ".png");
		FileUtils.copyFile(capturePhotoFile, savePhotoFile);

	}

	public static String base64Sceenshot() {
		driver = DriverFactory.getDriver();
		if (driver == null) {
			return null;
		}
		try {
			return ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BASE64);
		} catch (NoSuchSessionException e) {
			return null;
		}

	}

	public static byte[] byteScreenshot() {
		driver = DriverFactory.getDriver();
		if (driver == null) {
			return null;
		}
		try {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		} catch (NoSuchSessionException e) {
			return null;
		}
	}

	@Attachment(value = "Failure Screenshot", type = "image/png")
	public static byte[] attachScreenshot(byte[] screenshot) {
		return screenshot;
	}
}
