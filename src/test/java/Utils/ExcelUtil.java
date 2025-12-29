package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtil {

	private static String filepath = "src/test/resources/TestData/DSAlgo.xlsx";

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;

	@DataProvider(name = "ExcelData")
	public synchronized Object[][] getData(Method methodName) throws Exception {
		FileInputStream filelocation = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(filelocation);
		// System.out.println(methodName.getName());
		// System.out.println(methodName.getDeclaringClass());
		// System.out.println(methodName.getDeclaringClass().getName());
		String testsheet = getSheetName(
				methodName.getDeclaringClass().getName());
		sheet = workbook.getSheet(testsheet);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();

		List<Map<String, String>> data = new ArrayList<>();

		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < rows; i++) {
			Map<String, String> testData = new HashMap<>();
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < cols; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = (row.getCell(j) == null)
						? ""
						: formatter.formatCellValue(row.getCell(j));
				// : row.getCell(j).toString();
				testData.put(key, value);
			}
			data.add(testData);
		}

		workbook.close();

		// Convert List<Map> → Object[][]
		Object[][] finalTestData = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			finalTestData[i][0] = data.get(i);
		}

		return finalTestData;
	}

	private static String getSheetName(String sheetMethod) {

		switch (sheetMethod) {
			case "TestCase.TestCase_SignIn" :
				return "Sign-IN";
			case "TestCase.TestCase_Queue" :
				return "Queue";
			case "TestCase.TestCase_Graph" :
				return "Graph";
			default :
				return null;
		}

	}

	public static synchronized List<Map<String, String>> getTestData(
			String sheetName) throws IOException {
		List<Map<String, String>> testData = new ArrayList<>();

		workbook = new XSSFWorkbook(filepath);
		sheet = workbook.getSheet(sheetName);

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();

		// List<Map<String, String>> data = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < rows; i++) {
			Map<String, String> map = new HashMap<>();
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < cols; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = (row.getCell(j) == null)
						? ""
						: formatter.formatCellValue(row.getCell(j));
				// row.getCell(j).toString();
				map.put(key, value);
			}
			testData.add(map);
		}

		workbook.close();
		return testData;

	}

}
