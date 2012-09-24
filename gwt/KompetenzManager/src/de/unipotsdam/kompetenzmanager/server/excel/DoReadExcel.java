package de.unipotsdam.kompetenzmanager.server.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.examples.CellTypes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gwt.dev.util.Strings;

import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class DoReadExcel {

	private ArrayList<List<XSSFCell>> sheetData;
	private String filename;

	public DoReadExcel() throws IOException {
		this.sheetData = new ArrayList<List<XSSFCell>>();
		// An excel file name. You can create a file name with a full
		// path information.
		//
		filename = "Klassifkation.xlsx";

	}

	public void readRawData() throws IOException {
		FileInputStream fis = null;
		try {
			//
			// Create a FileInputStream that will be use to read the
			// excel file.
			//
			fis = new FileInputStream(filename);

			//
			// Create an excel workbook from the file system.
			//
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//
			// Get the first sheet on the workbook.
			//
			XSSFSheet sheet = workbook.getSheetAt(1);

			//
			// When we have a sheet object in hand we can iterator on
			// each sheet's rows and on each row's cells. We store the
			// data read on an ArrayList so that we can printed the
			// content of the excel to the console.
			//
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();

				List<XSSFCell> data = new ArrayList<XSSFCell>();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					data.add(cell);
				}

				sheetData.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	public Literature generateLiteratureEntriesFromExcel() throws IOException {
		readRawData();
		Literature result = new Literature();
		for (int i = 3; i < sheetData.size(); i++) {
			List list = sheetData.get(i);
			LiteratureEntry literatureEntry = new LiteratureEntry();
			XSSFCell cell1 = (XSSFCell) list.get(1);
			XSSFCell cell2 = (XSSFCell) list.get(2);
			XSSFCell cell3 = (XSSFCell) list.get(3);
			XSSFCell cell4 = (XSSFCell) list.get(4);
			XSSFCell cell5 = (XSSFCell) list.get(5);
			literatureEntry.titel = cell1.getStringCellValue();
			literatureEntry.author = cell2.getStringCellValue();
			String band = cell3.getRawValue();
			if (band.startsWith("INFO")) {
				literatureEntry.paper = "INFO";  
			} else {
				literatureEntry.paper = "DDI";
			}
			literatureEntry.year = cell4.getRawValue();
			literatureEntry.klassifikationsnummer = i;	
			literatureEntry.abstractText = "";
			literatureEntry.volume = Integer.parseInt(band) + "";
			result.literatureEntries.add(literatureEntry);
		}
		return result;
	}

	public void showData() {
		for (int i = 3; i < sheetData.size(); i++) {
			List list = sheetData.get(i);
			for (int j = 0; j < list.size(); j++) {
				XSSFCell cell = (XSSFCell) list.get(j);
				if (!(cell.getCellType() == Cell.CELL_TYPE_STRING)) {
					System.out.print(cell.getRawValue());
				} else {
					System.out.println(cell.getStringCellValue());
				}
				if (j < list.size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("");
		}
	}

}
