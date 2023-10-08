package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class ZipFilesCheckTest {
    private static String zipFile = "zip/ZipFileTest.zip";
    private static String pdfFile = "testPDF.pdf";
    private static String csvFile = "testCSV.csv";
    private static String xlsxFile = "testExcel.xlsx";
    private static String expectedData = "1. Hello World!";

    private static ClassLoader classLoader = ZipFilesCheckTest.class.getClassLoader();

    private InputStream getInputStreamForFileFromZip(String fileName) throws IOException {
        ZipInputStream inputStream = new ZipInputStream(classLoader.getResourceAsStream(zipFile));
        ZipEntry entry;
        while ((entry = inputStream.getNextEntry()) != null) {
            if (entry.getName().equals(fileName)) {
                return inputStream;
            }
        }
        throw new IOException("Файл " + fileName + " не найден в указанном архиве");
    }

    @Test
    @DisplayName("Pdf файл содержит необходимый текст.")
    void findPdfContentInZip() throws IOException {
        try (InputStream inputStream = getInputStreamForFileFromZip(pdfFile)) {
            PDF pdf = new PDF(inputStream);
            String pdfText = pdf.text;
            assertThat(pdfText).contains(expectedData);
        }
    }

    @Test
    @DisplayName("CSV файл содержит необходимый текст.")
    void findCsvContentInZip() throws Exception {
        try (InputStream inputStream = getInputStreamForFileFromZip(csvFile);
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String firstValue = reader.readNext()[0];
            assertThat(firstValue).contains(expectedData);
        }
    }

    @Test
    @DisplayName("Xlsx файл содержит необходимый текст.")
    void findXlsxContentInZip() throws Exception {
        try (InputStream inputStream = getInputStreamForFileFromZip(xlsxFile)) {
            XLS xlsx = new XLS(inputStream);
            String firstCell = xlsx.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
            assertThat(firstCell).contains(expectedData);
        }
    }
}
