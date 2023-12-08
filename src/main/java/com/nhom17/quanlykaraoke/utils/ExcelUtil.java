package com.nhom17.quanlykaraoke.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nhom17.quanlykaraoke.entities.HangHoa;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 08-Dec-2023 6:32:31 PM
 */
public class ExcelUtil {
	public static final int COLUMN_INDEX_ID = 0;
	public static final int COLUMN_INDEX_TITLE = 1;
	public static final int COLUMN_INDEX_PRICE = 2;
	public static final int COLUMN_INDEX_QUANTITY = 3;
	public static final int COLUMN_INDEX_TOTAL = 4;
	private static CellStyle cellStyleFormatNumber = null;

	private static List<String> listTableTitles;
	private static List<? extends Object> listData;

	/**
	 *
	 */

	ExcelUtil() {

	}

	public static void writeExcel(List<String> listTableTitles, List<? extends Object> listData, String excelFilePath)
			throws IOException {
		ExcelUtil.listTableTitles = listTableTitles;
		ExcelUtil.listData = listData;

		// Create Workbook
		Workbook workbook = getWorkbook(excelFilePath);

		// Create sheet
		Sheet sheet = workbook.createSheet("Danh sách hàng hóa");

		int rowIndex = 0;

		// Write header
		writeHeader(sheet, rowIndex);

		// Write data
		rowIndex++;
		for (Object o : listData) {
			// Create row
			Row row = sheet.createRow(rowIndex);
			// Write data on row
			writeData(sheet, o, row);
			rowIndex++;
		}

		// Auto resize column witdth
		int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		autosizeColumn(sheet, numberOfColumn);

		// Create file excel
		createOutputFile(workbook, excelFilePath);
	}

	// Create workbook
	private static Workbook getWorkbook(String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	// Write header with format
	private static void writeHeader(Sheet sheet, int rowIndex) {
		// create CellStyle
		CellStyle cellStyle = createStyleForHeader(sheet);

		// Create row
		Row row = sheet.createRow(rowIndex);

		// Create cells
		int index = 0;
		for (String tableTitle : listTableTitles) {
			Cell cell = row.createCell(index);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(tableTitle);

			index++;
		}
	}

	// Write data
	private static void writeData(Sheet sheet, Object o, Row row) {
		HangHoa h = (HangHoa) o;

		if (cellStyleFormatNumber == null) {
			short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
			// DataFormat df = workbook.createDataFormat();
			// short format = df.getFormat("#,##0");

			// Create CellStyle
			Workbook workbook = row.getSheet().getWorkbook();
			cellStyleFormatNumber = workbook.createCellStyle();
			cellStyleFormatNumber.setDataFormat(format);
		}

		// Create cell
		// create CellStyle
		CellStyle cellStyle = createStyleForDataRow(sheet);
		int index = 0;

		Cell cell = row.createCell(index);
		cell.setCellValue(h.getMaHangHoa());
		cell.setCellStyle(cellStyle);

		index++;
		cell = row.createCell(index);
		cell.setCellValue(h.getTenHangHoa());
		cell.setCellStyle(cellStyle);

		index++;
		cell = row.createCell(index);
		cell.setCellValue(h.getLoaiHangHoa().getTenLoaiHangHoa());
		cell.setCellStyle(cellStyle);

		index++;
		cell = row.createCell(index);
		cell.setCellValue(h.getSoLuongTon());
		cell.setCellStyle(cellStyle);

		index++;
		cell = row.createCell(index);
		cell.setCellValue(MoneyFormatUtil.format(h.getDonGia()));
		cell.setCellStyle(cellStyle);

		index++;
		cell = row.createCell(index);
		cell.setCellValue(h.isTrangThai() ? "Hoạt động" : "Ngưng hoạt động");
		cell.setCellStyle(cellStyle);
	}

	// Create CellStyle for header
	private static CellStyle createStyleForHeader(Sheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14); // font size
		font.setColor(IndexedColors.WHITE.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		return cellStyle;
	}

	// Create CellStyle for data row
	private static CellStyle createStyleForDataRow(Sheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 12); // font size
		font.setColor(IndexedColors.BLACK.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		return cellStyle;
	}

	// Auto resize column width
	private static void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	// Create output file
	private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
		try (OutputStream os = new FileOutputStream(excelFilePath)) {
			workbook.write(os);
		}
	}
}
