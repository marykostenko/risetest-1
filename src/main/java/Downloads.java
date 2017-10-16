import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Downloads
{
    public int checkUploadingStatesCandidates(int logErrors) throws IOException {
        TestCandidatesData testCandidatesData = new TestCandidatesData();

        String state = "Ошибка";
        int n = 0;

        ArrayList<String> statesListInUploading = new ArrayList<String>();
        ArrayList<String> quotaStatesList = testCandidatesData.quotaStates();

        File src = new File("src/main/resources/downloads/candidates.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet1 = wb.getSheetAt(0);

        System.out.println("Состояния каниддатов из выгрузки: ");
        while (state != null) {
            try {
                state = sheet1.getRow(6 + n).getCell(21).getStringCellValue();
            } catch (Exception e) {
                state = null;
                System.out.println();
            }

            if (state != null)
            {
                n++;
                System.out.println(state);
                statesListInUploading.add(state);
            }
        }

        System.out.println("Массив квотных состояний, ожидаемых в выгрузке");
        System.out.println(quotaStatesList);
        System.out.println();
        System.out.println("Массив состояний из выгрузки");
        System.out.println(statesListInUploading);

        logErrors = excessRecordsInUploading(statesListInUploading, logErrors);

        return logErrors;
    }

    /**
     * Сравнивает массив из выгрузки с ожидаемым массивом значений
     */
    public int excessRecordsInUploading (ArrayList uploadingArray, int logErrors) throws IOException
    {
        TestCandidatesData testCandidatesData = new TestCandidatesData();
        TestCandidatesData testCandidatesData1 = new TestCandidatesData();

        ArrayList lacking = testCandidatesData.quotaStates();

        lacking.removeAll(uploadingArray);

        if (!lacking.isEmpty())
        {
            System.out.println("Ошибка! В выгрузке нехватает кандидатов в состояниях: ");
            System.out.println(lacking);
            logErrors++;
        }

        ArrayList excess = testCandidatesData1.quotaStates();

        uploadingArray.removeAll(excess);

        if (!uploadingArray.isEmpty())
        {
            System.out.println("Ошибка! Выгрузка содержит кандидатов не в квотных состояниях: ");
            System.out.println(uploadingArray);
            logErrors++;
        }
        return logErrors;
    }

    /**
     * Удаляет все загруженные файлы из папки
     */
    public void deleteAllDownloads()
    {
        for (File myFile : new File("src/main/resources/downloads").listFiles())
            if (myFile.isFile()) myFile.delete();
    }
}