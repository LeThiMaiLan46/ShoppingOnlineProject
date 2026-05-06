package Utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    public static Object[][] getAllData(String filePath, String sheetName) {

        List<Object[]> dataList = new ArrayList<>();

        try {
            System.out.println(filePath);
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            int emptyRowCount = 0;

            // Loop từ row 1 (bỏ header)
            for (int i = 1; i <= totalRows; i++) {

                Row currentRow = sheet.getRow(i);

                if (currentRow == null || isRowEmpty(currentRow)) {
                    emptyRowCount++;

                    // nếu gặp 3 dòng trống liên tiếp → stop
                    if (emptyRowCount >= 3) break;

                    continue;
                }

                emptyRowCount = 0;

                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) {

                    String key = headerRow.getCell(j).getStringCellValue();

                    Cell cell = currentRow.getCell(j);
                    String value = (cell == null) ? "" : cell.toString();

                    dataMap.put(key, value);

                }

                dataList.add(new Object[]{dataMap});
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList.toArray(new Object[0][]);
    }

    private static boolean isRowEmpty(Row row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.toString().trim().length() > 0) {
                return false;
            }
        }
        return true;
    }
}
