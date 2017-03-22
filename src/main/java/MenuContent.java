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

    private ElementsCollection menuUsers = $$(By.xpath("//a[@href='/users']"));
    private ElementsCollection menuCatalogs = $$(By.xpath("//a[@href='/catalogs']"));
    private ElementsCollection menuCyclesWithPlans = $$(By.xpath("//a[@href='/cycleswithplans']"));
    private ElementsCollection menuGovernment = $$(By.xpath("//a[@href='/government/list']"));
    private ElementsCollection menuJournal = $$(By.xpath("//a[@href='/journal']"));
    private ElementsCollection menuContentManagement = $$(By.xpath("//a[@href='/contentManagement']"));
    private ElementsCollection menuMailTemplates = $$(By.xpath("//a[@href='/admin/mailtemplates']"));

    public int menuAdmin (int logErrors)
    {

        logErrors = checkAndLog(menuUsers.isEmpty(), logErrors, "ОШИБКА: нет меню 'Пользователи'", "Меню 'Пользователи' есть");
        logErrors = checkAndLog(menuCatalogs.isEmpty(), logErrors, "ОШИБКА: нет меню 'Справочники'", "Меню 'Справочники' есть");
        logErrors = checkAndLog(menuCyclesWithPlans.isEmpty(), logErrors, "ОШИБКА: нет меню 'Циклы приёма'", "Меню 'Циклы приема' есть");
        logErrors = checkAndLog(menuGovernment.isEmpty(), logErrors, "ОШИБКА: нет меню 'Органы власти'", "Меню 'Органы власти' есть");
        logErrors = checkAndLog(menuJournal.isEmpty(), logErrors, "ОШИБКА: нет меню 'Журнал'", "Меню 'Журнал' есть");
        logErrors = checkAndLog(menuContentManagement.isEmpty(), logErrors, "ОШИБКА: нет меню 'Управление контентом'", "Меню 'Управление контентом' есть");
        logErrors = checkAndLog(menuMailTemplates.isEmpty(), logErrors, "ОШИБКА: нет меню 'Шаблоны'", "Меню 'Шаблоны' есть");

        return logErrors;

    }

    public int menuAdminForCurator (int logErrors)
    {

        logErrors = checkAndLog(menuGovernment.isEmpty(), logErrors, "ОШИБКА: нет меню 'Органы власти'", "Меню 'Органы власти' есть");

        return logErrors;

    }

    private ElementsCollection menuOrgSev = $$(By.xpath("//div[@class='clearfix']//child::li"));
    private ElementsCollection titleSev = $$(By.xpath("//div[@class='user-title']"));
    private ElementsCollection contentSev = $$(By.xpath("//div[@class='content']"));

    public int menuAdminOrg (int logErrors)
    {

        logErrors = checkAndLog(menuOrgSev.isEmpty(), logErrors, "ОШИБКА: нет меню 'СевГУ'", "Меню 'СевГУ' есть");
        logErrors = checkAndLog(titleSev.isEmpty(), logErrors, "ОШИБКА: нет названия вуза", "Название вуза есть");
        logErrors = checkAndLog(contentSev.isEmpty(), logErrors, "ОШИБКА: нет общей информации о вузе", "Общая информация о вузе есть");

        return logErrors;

    }

}
