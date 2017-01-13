import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by user nkorobicina on 28.12.2016.
 */
public class PageMain extends BasePage {

    private ElementsCollection navigatorLanding = $$(By.xpath("//div[contains(@class, 'programs-search')]"));
    //возвращает true, если на странице есть навигатор, то есть открыта главная страница
    private boolean isMainPage(){
        return !navigatorLanding.isEmpty();
    }

    public int checkMainPage(int logErrors){
        return checkAndLog(!isMainPage(), logErrors, "Ошибка: открылась не главная страница");
    }
}
