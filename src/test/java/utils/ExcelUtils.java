package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class ExcelUtils {

    private ExcelUtils() {
        // Prevent instantiation
    }

    public static List<Map<String, String>> getTestData(
            String resourcePath,   // e.g. "testdata/pet.xlsx"
            String sheetName) 
    {

        List<Map<String, String>> testDataList = new ArrayList<>();

        // ✅ Correct way to load from src/test/resources
        InputStream fis = ExcelUtils.class
                .getClassLoader()
                .getResourceAsStream(resourcePath);

        if (fis == null) {
            throw new RuntimeException(
                    "Excel file not found in classpath: " + resourcePath
            );
        }

        try (Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row is missing");
            }

            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = headerRow.getLastCellNum();

            for (int i = 1; i <= lastRowNum; i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();

                for (int j = 0; j < lastCellNum; j++) {

                    String key = getCellValueAsString(headerRow.getCell(j));
                    String value = getCellValueAsString(row.getCell(j));

                    rowData.put(key, value);
                }

                // ✅ Only include rows marked Run = Y
                if ("Y".equalsIgnoreCase(rowData.get("Run"))) {
                    testDataList.add(rowData);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }

        return testDataList;
    }

    private static String getCellValueAsString(Cell cell) {

        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getDateCellValue().toString();
                }
                yield String.valueOf((long) cell.getNumericCellValue());
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            default -> cell.toString().trim();
        };
    }
}
