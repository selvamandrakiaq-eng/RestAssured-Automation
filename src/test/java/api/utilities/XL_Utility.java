package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL_Utility {

    public static int getRowCount(String filePath, String sheetName) throws IOException {
        try (FileInputStream input = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {
            Sheet sheet = workbook.getSheet(sheetName);
            return sheet.getLastRowNum();
        }
    }

    public static int getCellCount(String filePath, String sheetName, int rowNumber)
            throws IOException {
        try (FileInputStream input = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {
            Row row = workbook.getSheet(sheetName).getRow(rowNumber);
            return row == null ? 0 : row.getLastCellNum();
        }
    }

    public static String getCellData(String filePath, String sheetName,
                                     int rowNumber, int columnNumber) throws IOException {
        try (FileInputStream input = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNumber);
            if (row == null || row.getCell(columnNumber) == null) {
                return "";
            }
            return new DataFormatter().formatCellValue(row.getCell(columnNumber));
        }
    }
}
