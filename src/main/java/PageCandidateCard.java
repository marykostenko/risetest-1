import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by user nkorobicina on 14.12.2016.
 */
public class PageCandidateCard extends BasePage{

    //проверка, что меню карточки кандидата содержит нужные вкладки
    public int assertCandidateCardMenu(int logErrors){

        logErrors = checkAndLog($$(By.linkText("Личные данные")).isEmpty(), logErrors, "Ошибка: нет вкладки Личные данные");

        logErrors = checkAndLog($$(By.linkText("Заявка")).isEmpty(),logErrors,"Ошибка: нет вкладки Заявка");

        logErrors = checkAndLog($$(By.linkText("Сообщения")).isEmpty(),logErrors, "Ошибка: нет вкладки Сообщения");

        return logErrors;

    }
}
