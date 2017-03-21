import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Maria on 21.03.2017.
 */
public class MenuContent extends BasePage{

    private ElementsCollection filtersQuotas = $$(By.xpath("//header[@class='subhead']"));
    private ElementsCollection tableQuotas = $$(By.xpath("//header[@class='subhead']"));
    private ElementsCollection tableGroups = $$(By.xpath("//div[@class='table_wrapper']"));

    public int menuPlans (int logErrors)
    {

        logErrors = checkAndLog(filtersQuotas.isEmpty(), logErrors, "ОШИБКА: нет фильтров квот стран ", "Фильтры квот стран есть");
        logErrors = checkAndLog(tableQuotas.isEmpty(), logErrors, "ОШИБКА: нет таблицы квот стран ", "Таблица квот стран есть");
        logErrors = checkAndLog(tableGroups.isEmpty(), logErrors, "ОШИБКА: нет таблицы групп уровней образоания ", "Таблица групп уровней образования есть");

        return logErrors;


    }

    private ElementsCollection filtersOrg = $$(By.xpath("//div[@id='orgs-filter']"));
    private ElementsCollection tableOrg = $$(By.xpath("//div[@id='orgs']"));

    public int menuOrg (int logErrors)
    {

        logErrors = checkAndLog(filtersOrg.isEmpty(), logErrors, "ОШИБКА: нет фильтров организаций", "Фильтры организаций есть");
        logErrors = checkAndLog(tableOrg.isEmpty(), logErrors, "ОШИБКА: нет таблицы с организациями", "Таблица с организациями есть");

        return logErrors();
    }

}
