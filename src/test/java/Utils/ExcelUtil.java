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

	private static String filepath = "src/test/resources/TestData/DSAlgo1.xlsx";

	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;

	private static final Map<String, Object[][]> excelData = new HashMap<>();

	// @DataProvider(name = "ExcelData")
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

	// @DataProvider(name = "ExcelData")
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

	@DataProvider(name = "ExcelData2")
	public static Object[][] getSheetDataforTest(Method methodName) {
		// return excelData.get(sheetName);

		String testsheet = methodName.getName();
		System.out.println(testsheet);

		switch (testsheet) {
			case "testDropdownWithoutLogin" :
			case "testDropdownAfterLogin" :
				return excelData.get("HomePage_Dropdown");

			case "testGetStartedWithoutLogin" :
			case "testGetStartedAfterLogin" :
				return excelData.get("HomePage_GetStarted");

			case "verifyTryHereButtonVisible":
			case "verifyTopicNavigation" :
			case "verifyValidPythonCodeExecution" :
				return excelData.get("Validpythoncode");
			
				

			case "verifyInValidPythonCodeExecution" :
				return excelData.get("InValidpythoncode");

			case "verifyPracticeQuestionSubpageNavigation" :
			case "verifyPracticepageValidPythonCodeExecution" :
				return excelData.get("PracticepageValidcode");

			case "verifyPracticepageInValidPythonCodeExecution" :
				return excelData.get("PracticepageInValidcode");
				
//			case "VerifyblankUsername_validPassword":
//				return excelData.get("Registration");
//				
//			case "VerifyvalidUsername_blankPassword":
//				return excelData.get("Registration");
//				
//			case "VerifypasswordWithOnlyNumbers":
//				return excelData.get("Registration");
//				
//			case "VerifypasswordWithOnlySpecialChar":
//				return excelData.get("Registration");
//			
//			case "VerifypasswordWithLessthan8character":
//				return excelData.get("Registration");
			case "VerifyRegisterTestcases":
				return excelData.get("Registration");
			default :
				return null;
		}
	}

}
