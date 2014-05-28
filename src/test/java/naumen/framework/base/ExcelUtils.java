package naumen.framework.base;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;

public class ExcelUtils {


	public LinkedHashMap<String,String> readRowIFExists(String sheetName) {

		URL myTestURL = ClassLoader.getSystemResource("mapping.xls");
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(myTestURL.toURI()));
		} catch (Exception e1) {
			Logger.getInstance().debug(e1.getMessage());
		}
		// my excel sheet name is contacts, iam given the complete path.
		Sheet sheet = workbook.getSheet(sheetName); // iam reading data from the sheet1
		// here iam reading the data of 1st column data up three cells
		 for(int i=0;i<sheet.getRows();i++)
	       {
             Cell one=sheet.getCell(0,i);
             Cell two=sheet.getCell(1,i);
             results.put(one.getContents(), two.getContents());
//	         System.out.println(String.format("Проверка:   %1$s   %2$s \n",one.getContents(),two.getContents()));
	       }

		return results;
	}

}
