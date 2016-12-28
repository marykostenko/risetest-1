import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Maria on 28.12.2016.
 */

//SYS-MP-1
public class SystemOperationRu extends BaseTest {

        //USER L-2.1
        @Test(priority =1)
        public void checkTheHomePage() throws IOException {
            log("Запущен тест SYS-MP-1.1");

            log("Переключаем язык страницы на русский");
            PageTopBottom pageTopBottom = new PageTopBottom();
            pageTopBottom.switchToRu();

            log("Проверяем шапку главной страницы");
            HomePage homePage = new HomePage();
            logErrors = homePage.hatHomePage(logErrors);

            log("Проверяем подвал главной страницы");
            logErrors = homePage.basementHomePage(logErrors);

            checkMistakes();

            log("Тест SYS-MP-1.1 завершен");
        }
}
