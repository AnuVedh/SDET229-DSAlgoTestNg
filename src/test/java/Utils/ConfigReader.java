package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;

	public static synchronized String getProperty(String key) {
		try {
			if (prop == null) {
				prop = new Properties();
				FileInputStream fis = new FileInputStream(
						"src/test/resources/Config/config.properties");
				prop.load(fis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}