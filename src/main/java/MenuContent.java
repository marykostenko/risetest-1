import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Maria on 21.03.2017.
 */
public class MenuContent extends BasePage
{

    PageTopBottom pageTopBottom = new PageTopBottom();
    private ElementsCollection filtersQuotas = $$(By.xpath("//header[@class='subhead']"));
    private ElementsCollection tableQuotas = $$(By.xpath("//header[@class='subhead']"));
    private ElementsCollection tableGroups = $$(By.xpath("//div[@class='table_wrapper']"));


    public int checkMenuPlans(int logErrors) {

        if (pageTopBottom.isAnyMenuAppear(0))
        {

            log("Пункт 'План приема' есть");
            $(By.xpath("//a[contains(@href,'/plans/')]")).click();

            logErrors = checkAndLog(filtersQuotas.isEmpty(), logErrors, "ОШИБКА: нет фильтров квот стран ", "Фильтры квот стран есть");
            logErrors = checkAndLog(tableQuotas.isEmpty(), logErrors, "ОШИБКА: нет таблицы квот стран ", "Таблица квот стран есть");
            logErrors = checkAndLog(tableGroups.isEmpty(), logErrors, "ОШИБКА: нет таблицы групп уровней образоания ", "Таблица групп уровней образования есть");

        } else
        {

            logErrors++;
            log("ОШИБКА: Пункт 'План приема' не найден");
        }

        return logErrors;


    }

    private ElementsCollection filtersOrg = $$(By.xpath("//div[@id='orgs-filter']"));
    private ElementsCollection tableOrg = $$(By.xpath("//div[@id='orgs']"));

    public int checkMenuOrg(int logErrors)
    {
        if (pageTopBottom.isAnyMenuAppear(1))

        {

            log("Пункт 'Организации' есть");
            $(By.xpath("//a[contains(@href,'/organizations')]")).click();

            logErrors = checkAndLog(filtersOrg.isEmpty(), logErrors, "ОШИБКА: нет фильтров организаций", "Фильтры организаций есть");
            logErrors = checkAndLog(tableOrg.isEmpty(), logErrors, "ОШИБКА: нет таблицы с организациями", "Таблица с организациями есть");

        } else
        {
            logErrors++;
            log("ОШИБКА: Пункт 'Организации' не найден");
        }

        return logErrors;

    }

    private ElementsCollection filtersCand = $$(By.xpath("//div[@id='candidate-filter']"));
    private ElementsCollection tableCand = $$(By.xpath("//div[@id='candidates']"));

    public int checkMenuCand(int logErrors)
    {

        if (pageTopBottom.isAnyMenuAppear(2))
        {

            log("Пункт 'Кандидаты' есть");
            $(By.xpath("//a[@href='/candidates']")).click();

            logErrors = checkAndLog(filtersCand.isEmpty(), logErrors, "ОШИБКА: нет фильтров кандидатов", "Фильтры кандидатов есть");
            logErrors = checkAndLog(tableCand.isEmpty(), logErrors, "ОШИБКА: нет таблицы с кандидатами", "Таблица с кандидатами есть");

        } else
        {
            logErrors++;
            log("ОШИБКА: Пункт 'Кандидаты' не найден");
        }
        return logErrors;
    }

    private ElementsCollection filtersCountry = $$(By.xpath("//div[@id='country-filters']"));
    private ElementsCollection tableCountries = $$(By.xpath("//div[@id='countries']"));

    public int checkMenuCountries(int logErrors)
    {

        if (pageTopBottom.isAnyMenuAppear(3))
        {
            log("Пункт 'Страны' есть");
            $(By.xpath("//a[@href='/countries']")).click();

            logErrors = checkAndLog(filtersCountry.isEmpty(), logErrors, "ОШИБКА: нет фильтров стран", "Фильтры стран есть");
            logErrors = checkAndLog(tableCountries.isEmpty(), logErrors, "ОШИБКА: нет таблицы со странами", "Таблица со странами есть");

        } else
        {
            logErrors++;
            log("ОШИБКА: Пункт 'Страны' не найден");
        }

        return logErrors;

    }


    private ElementsCollection filtersVisa = $$(By.xpath("//div[@id='visaRequest-filter']"));
    private ElementsCollection tableVisa = $$(By.xpath("//div[@id='visaRequests']"));

    public int checkMenuVisa(int logErrors)
    {

        if (pageTopBottom.isAnyMenuAppear(4))
        {

            log("Пункт 'Визы' есть");
            $(By.xpath("//a[@href='/visaRequest/list']")).click();

            logErrors = checkAndLog(filtersVisa.isEmpty(), logErrors, "ОШИБКА: нет фильтров виз", "Фильтры виз есть");
            logErrors = checkAndLog(tableVisa.isEmpty(), logErrors, "ОШИБКА: нет таблицы виз", "Таблица виз есть");

        } else
        {

            logErrors++;
            log("ОШИБКА: Пункт 'Визы' не найден");

        }

        return logErrors;

    }

    private ElementsCollection filtersAgents = $$(By.xpath("//div[@id='agents-filter']"));
    private ElementsCollection tableAgents = $$(By.xpath("//div[@id='agents']"));

    public int checkMenuAgents(int logErrors)
    {

        if (pageTopBottom.isAnyMenuAppear(5))
        {

            log("Пункт 'Агенты' есть");
            $(By.xpath("//a[@href='/agent/list']")).click();

            logErrors = checkAndLog(filtersAgents.isEmpty(), logErrors, "ОШИБКА: нет фильтров стран", "Фильтры стран есть");
            logErrors = checkAndLog(tableAgents.isEmpty(), logErrors, "ОШИБКА: нет таблицы со странами", "Таблица со странами есть");

        } else
        {

            logErrors++;
            log("ОШИБКА: Пункт 'Агенты' не найден");

        }

        return logErrors;

    }

    private ElementsCollection menuUsers = $$(By.xpath("//a[@href='/users']"));
    private ElementsCollection menuCatalogs = $$(By.xpath("//a[@href='/catalogs']"));
    private ElementsCollection menuCyclesWithPlans = $$(By.xpath("//a[@href='/cycleswithplans']"));
    private ElementsCollection menuGovernment = $$(By.xpath("//a[@href='/government/list']"));
    private ElementsCollection menuJournal = $$(By.xpath("//a[@href='/journal']"));
    private ElementsCollection menuContentManagement = $$(By.xpath("//a[@href='/contentManagement']"));
    private ElementsCollection menuMailTemplates = $$(By.xpath("//a[@href='/admin/mailtemplates']"));

    public int checkMenuAdmin(int logErrors)
    {

        if (pageTopBottom.isAnyMenuAppear(6))
        {
            log("Пункт 'Администрирование' есть");
            $(By.xpath("//a[@class='dropdown-toggle padding-right-0']")).click();

            logErrors = checkAndLog(menuUsers.isEmpty(), logErrors, "ОШИБКА: нет меню 'Пользователи'", "Меню 'Пользователи' есть");
            logErrors = checkAndLog(menuCatalogs.isEmpty(), logErrors, "ОШИБКА: нет меню 'Справочники'", "Меню 'Справочники' есть");
            logErrors = checkAndLog(menuCyclesWithPlans.isEmpty(), logErrors, "ОШИБКА: нет меню 'Циклы приёма'", "Меню 'Циклы приема' есть");
            logErrors = checkAndLog(menuGovernment.isEmpty(), logErrors, "ОШИБКА: нет меню 'Органы власти'", "Меню 'Органы власти' есть");
            logErrors = checkAndLog(menuJournal.isEmpty(), logErrors, "ОШИБКА: нет меню 'Журнал'", "Меню 'Журнал' есть");
            logErrors = checkAndLog(menuContentManagement.isEmpty(), logErrors, "ОШИБКА: нет меню 'Управление контентом'", "Меню 'Управление контентом' есть");
            logErrors = checkAndLog(menuMailTemplates.isEmpty(), logErrors, "ОШИБКА: нет меню 'Шаблоны'", "Меню 'Шаблоны' есть");

        } else
            {

                logErrors++;
                log("ОШИБКА: Пункт 'Администрирование' не найден");

            }

        return logErrors;

    }

    public int checkMenuAdminForCurator(int logErrors)
    {

        if (pageTopBottom.isAnyMenuAppear(6))
        {

            log("Пункт 'Администрирование' есть");
            $(By.xpath("//a[@class='dropdown-toggle padding-right-0']")).click();

            logErrors = checkAndLog(menuGovernment.isEmpty(), logErrors, "ОШИБКА: нет меню 'Органы власти'", "Меню 'Органы власти' есть");

        } else
            {

                logErrors++;
                log("ОШИБКА: Пункт 'Администрирование' не найден");

            }

        return logErrors;

    }

    private ElementsCollection menuOrgSev = $$(By.xpath("//div[@class='clearfix']//child::li"));
    private ElementsCollection titleSev = $$(By.xpath("//div[@class='user-title']"));
    private ElementsCollection contentSev = $$(By.xpath("//div[@class='content']"));

    public int checkMenuAdminOrg(int logErrors) {

        if (!menuOrgSev.isEmpty()) {

            logErrors = checkAndLog(titleSev.isEmpty(), logErrors, "ОШИБКА: нет названия вуза", "Название вуза есть");
            logErrors = checkAndLog(contentSev.isEmpty(), logErrors, "ОШИБКА: нет общей информации о вузе", "Общая информация о вузе есть");

        } else
        {

            logErrors++;
            log("ОШИБКА: Пункт меню образовательной организации не найден");

        }

        return logErrors;
    }

    /**
     * переход в меню Действия
     */
    public void goToActions()
    {
        $(By.xpath("//a[@class='dropdown-toggle padding-right-0']")).click();
        $(By.xpath("//a[@href='/admin/systemactions']")).click();
    }

}
