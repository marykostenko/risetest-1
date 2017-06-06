import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by user nkorobicina on 28.12.2016.
 * Главная страница
 */
public class PageMain extends BasePage
{
    private ElementsCollection navigatorLanding = $$(By.xpath("//div[@class='rs-program']"));

    /**
     * возвращает true, если на странице есть навигатор, то есть открыта главная страница
     */
    private boolean isMainPage()
    {
        return !navigatorLanding.isEmpty();
    }

    public int checkMainPage(int logErrors)
    {
        return checkAndLog(!isMainPage(), logErrors, "Ошибка: открылась не главная страница");
    }

    public void goToRegistrationFromFooter() { $(By.xpath("//section[@class='cta_section theme_white cta_section_steps']//child::a[@class='rs-request-button']")).click();}

    public void goToRegistrationFromBlockLearnFree() { $(By.xpath("//section[@class='text_image_section theme_gray padded_top_none']//child::a[contains(text(),'Подать заявку')]")).click(); }

    public void goToRegistrationFromBlockContractTraining() { $(By.xpath("//div[@class='rs-contract']//child::a[contains(text(),'Подать заявку')]")).click(); }
}
