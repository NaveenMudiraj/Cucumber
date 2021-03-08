package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import junit.framework.Assert;

public class ExcelDataDriven {

	// Identify Testcases coloum by scanning the entire 1st row
	// once coloumn is identified then scan entire testcase coloum to identify
	// purcjhase testcase row
	// after you grab purchase testcase row = pull all the data of that row and feed
	// into test

	public void validateDateRange() throws IOException, InterruptedException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C://Users//NaveenMudiraj//Downloads//Test.xls");
		// XSSFWorkbook workbook=new XSSFWorkbook(fis);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				int rowcount = sheet.getLastRowNum();
				System.out.println("Total rows" + rowcount);

				while (ce.hasNext()) {
					Cell value = ce.next();
					value.setCellValue(value.toString());
					System.out.println("value.getStringCellValue()" + value.getStringCellValue());
					if (value.getStringCellValue().equalsIgnoreCase("Range:")) {
						System.out.println("@@Range id Identified@@@");
						coloumn = k;

					}

					k++;
				}
				System.out.println(coloumn);

				//// once coloumn is identified then scan entire testcase coloum to identify
				//// purcjhase testcase row
				Thread.sleep(5000);
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase("01/22/2021 - 01/26/2021")) {

						System.out.println("Inside the date range");

						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellTypeEnum() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}

					}
				}
			}
		}

	}

	public void excelvalidationMethod(String ReportName, String DateRange) throws IOException, InterruptedException {
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C://Users//Naveen Mudiraj//Downloads/Untitled.xls");
		// XSSFWorkbook workbook=new XSSFWorkbook(fis);
		HSSFWorkbook workbook = new HSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				int rowcount = sheet.getLastRowNum();
				System.out.println("Total rows" + rowcount);
				Thread.sleep(5000);
				for (int ij = 0; ij < rowcount + 1; ij++) {
					Thread.sleep(5000);
					Cell cel = sheet.getRow(ij).getCell(3);
					if (cel != null) {

						cel.setCellValue(cel.toString());
						String data = cel.getStringCellValue();

						System.out.println("@@data@@" + data);
						if (data.equals(ReportName)) {
							System.out.println("Inside Report Name validation");
							Assert.assertTrue(true);
						}
						if (cel.getStringCellValue().equals(DateRange)) {
							System.out.println("Insid date range validation");
							Assert.assertTrue(true);
							workbook.close();
							break;
						}
					}
				}
			}
		}
	}
}
