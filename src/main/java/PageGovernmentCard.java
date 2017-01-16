import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by user nkorobicina on 14.12.2016.
 * Карточка органа власти
 */

public class PageGovernmentCard extends BasePage
{

    private ElementsCollection generalInformation = $$(By.linkText("Общая информация"));
    private ElementsCollection employee = $$(By.linkText("Сотрудники"));


    /**
     * проверка, что отображается минимальное меню в карточке органа власти: Общая информация и Сотрудники
     * @param logErrors
     * @return
     */
    public int assertMinimalMenu(int logErrors)
    {
        logErrors = checkAndLog(generalInformation.isEmpty(), logErrors, "Ошибка: нет пункта меню Общая информация");
        logErrors = checkAndLog(employee.isEmpty(), logErrors, "Ошибка: нет пункта меню Сотрудники");
        return logErrors;
    }
}
