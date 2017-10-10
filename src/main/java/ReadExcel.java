import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


public class ReadExcel
{

    public static void main (String[] args) throws Exception
    {

        String state = "Ошибка";
        int n = 0;
        ArrayList<String> statesList = new ArrayList<String>();

        File src = new File("D://candidates.xlsx");

        FileInputStream fis = new FileInputStream(src);

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet1 = wb.getSheetAt(0);


        System.out.println("Состояния каниддатов из выгрузки: ");
        while (state != null)
        {
            state = sheet1.getRow(6 + n).getCell(21).getStringCellValue();
            System.out.println(state);
            for (int i = 0; i < n; n++)
                statesList.add(state);
        }



    }
}
