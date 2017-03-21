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

        return logErrors;
    }

    private ElementsCollection filtersCand = $$(By.xpath("//div[@id='candidate-filter']"));
    private ElementsCollection tableCand = $$(By.xpath("//div[@id='candidates']"));

    public int menuCand (int logErrors)
    {

        logErrors = checkAndLog(filtersCand.isEmpty(), logErrors, "ОШИБКА: нет фильтров кандидатов", "Фильтры кандидатов есть");
        logErrors = checkAndLog(tableCand.isEmpty(), logErrors, "ОШИБКА: нет таблицы с кандидатами", "Таблица с кандидатами есть");

        return logErrors;
    }

    private ElementsCollection filtersCountry = $$(By.xpath("//div[@id='country-filters']"));
    private ElementsCollection tableCountries = $$(By.xpath("//div[@id='countries']"));

    public int menuCoutntries (int logErrors)
    {

        logErrors = checkAndLog(filtersCountry.isEmpty(), logErrors, "ОШИБКА: нет фильтров стран", "Фильтры стран есть");
        logErrors = checkAndLog(tableCountries.isEmpty(), logErrors, "ОШИБКА: нет таблицы со странами", "Таблица со странами есть");

        return logErrors;

    }

    private ElementsCollection filtersAgents = $$(By.xpath("//div[@id='agents-filter']"));
    private ElementsCollection tableAgents = $$(By.xpath("//div[@id='agents']"));

    public int menuAgents (int logErrors)
    {

        logErrors = checkAndLog(filtersAgents.isEmpty(), logErrors, "ОШИБКА: нет фильтров стран", "Фильтры стран есть");
        logErrors = checkAndLog(tableAgents.isEmpty(), logErrors, "ОШИБКА: нет таблицы со странами", "Таблица со странами есть");

        return logErrors;

    }
}
