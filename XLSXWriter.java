import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XLSXWriter {

    public static void writeProgramsToFile(List<Program> programs, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Programs");

        int rowCount = 0;
        for (Program program : programs) {
            Row row = sheet.createRow(rowCount++);
            createProgramRow(program, row);
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createProgramRow(Program program, Row row) {
        Cell channelCell = row.createCell(0);
        channelCell.setCellValue(program.getChannel());

        Cell timeCell = row.createCell(1);
        timeCell.setCellValue(program.getTime().toString());

        Cell titleCell = row.createCell(2);
        titleCell.setCellValue(program.getTitle());
    }
}
