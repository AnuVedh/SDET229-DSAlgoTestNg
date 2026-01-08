package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtil {

	private static String filepath = "src/test/resources/TestData/DSAlgo.xlsx";

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;

	private static final Map<String, Object[][]> excelData = new HashMap<>();

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

	public static synchronized void getAllsheetData() throws Exception {
		FileInputStream filelocation = new FileInputStream(filepath);
		workbook = new XSSFWorkbook(filelocation);
		for (Sheet sheet : workbook) {

			int rows = sheet.getPhysicalNumberOfRows();
			int cols = sheet.getRow(0).getLastCellNum();
			Object[][] data = new Object[rows - 1][1];

			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i < rows; i++) {
				Map<String, String> testData = new HashMap<>();
				Row row = sheet.getRow(i);

				for (int j = 0; j < cols; j++) {
					String key = sheet.getRow(0).getCell(j)
							.getStringCellValue();
					String value = (row.getCell(j) == null)
							? ""
							: formatter.formatCellValue(row.getCell(j));
					// : row.getCell(j).toString();
					testData.put(key, value);
				}

				data[i - 1][0] = testData;

			}
			excelData.put(sheet.getSheetName(), data);

		}
		workbook.close();

	}

	@DataProvider(name = "ExcelData")
	public static Object[][] getSheetData(Method methodName) {
		// return excelData.get(sheetName);

		String testsheet = methodName.getDeclaringClass().getName();
		System.out.println(testsheet);

		switch (testsheet) {
			case "TestCase.TestCase_SignIn" :
				// return "Sign-IN";
				return excelData.get("Sign-IN");
			case "TestCase.TestCase_Queue" :
				return excelData.get("Queue");
			case "TestCase.TestCase_Graph" :
				return excelData.get("Graph");
			default :
				return null;
		}

	}

	@DataProvider(name = "ExcelDataanu")
	public static Object[][] getSheetDataforTest(Method methodName) {
		// return excelData.get(sheetName);

		String testsheet = methodName.getName();
		System.out.println(testsheet);

		switch (testsheet) {
			case "TestCase.TestCase_SignIn" :
				// return "Sign-IN";
				return excelData.get("Sign-IN");
			case "TestCase.TestCase_Queue" :
				return excelData.get("Queue");
			case "TestCase.TestCase_Graph" :
				return excelData.get("Graph");
			default :
				return null;
		}

	}

}
