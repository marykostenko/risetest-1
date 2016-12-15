import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by user nkorobicina on 15.12.2016.
 */
public class PageOrganizationCard extends BasePage {

    private String[] minimalAdminOrgMenuLine = {"Общая информация", "Руководитель", "Расположение", "Сотрудники", "Филиалы"};

    //возвращает true, если найден пункт меню с нужным номером
    private boolean isMenuPointAppear(int i){
        ElementsCollection menu = $$(By.linkText(minimalAdminOrgMenuLine[i]));
        return !menu.isEmpty();
    }

    public int assertMinimalAdminOrgMenuAppear(int logErrors){
        int menu = minimalAdminOrgMenuLine.length;
        for(int i = 0; i <  menu; i++){
            logErrors = checkAndLog(!isMenuPointAppear(i), logErrors, "Ошибка: Пункт меню " + minimalAdminOrgMenuLine[i] + " недоступен",
                    "Пункт меню " + minimalAdminOrgMenuLine[i] + " доступен");
        }
        return logErrors;
    }
}
