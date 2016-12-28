import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Maria on 28.12.2016.
 */
public class HomePage  extends BasePage{

    // проверяет отображение шапки на главной странице и наличие в ней логотипа Россотрудничества, названия сайта, кнопок смены языка, вход и регистрация, кнопка корзины
    private ElementsCollection logo = $$(By.xpath("//a[@class='logo']"));
    private ElementsCollection logoTop = $$(By.xpath("//div[@class='logo-top']"));
    private ElementsCollection siteNameLogo = $$(By.xpath("//div[contains(text(),'Официальный сайт для отбора иностранных граждан на обучение в Российской Федерации')]"));
    private ElementsCollection ruLangButton = $$(By.xpath("//a[contains(@class,'btn-rus')]"));
    private ElementsCollection enLangButton = $$(By.xpath("//a[contains(@class,'btn-eng')]"));
    private ElementsCollection loginButton = $$(By.xpath("//a[@href='/login']"));
    private ElementsCollection registrationButton = $$(By.xpath("//a[contains(@class,'colorbox-registration')]"));
    private ElementsCollection basketButton = $$(By.xpath("//a[@data-toggle='dropdown']"));
    private ElementsCollection basketButtonIcon = $$(By.xpath("//i[contains(@class,'icon-briefcase')]"));

    public int hatHomePage (int logErrors){

        logErrors = checkAndLog(logo.isEmpty(), logErrors, "Ошибка: нет логотипа Россотрудничества", "Логотип Россотрудничества есть");
        logErrors = checkAndLog(logoTop.isEmpty(), logErrors, "Ошибка: нет логотипа сайта RUSSIA.STUDY", "Логотип сайта RUSSIA.STUDY есть");
        logErrors = checkAndLog(siteNameLogo.isEmpty(), logErrors, "Ошибка: название сайта отсутсвует", "Название сайта есть");
        logErrors = checkAndLog(ruLangButton.isEmpty(), logErrors, "Ошибка: нет кнопки переключения на русский язык", "Кнопка переключения на русский язык есть");
        logErrors = checkAndLog(enLangButton.isEmpty(), logErrors, "Ошибка: нет кнопки переключения на английский язык", "Кнопка переключения на английский язык есть");
        logErrors = checkAndLog(loginButton.isEmpty(), logErrors, "Ошибка: нет кнопки Вход", "Кнопка Вход есть");
        logErrors = checkAndLog(registrationButton.isEmpty(), logErrors, "Ошибка: нет кнопки Регистрация", "Кнопка Регистрация есть");
        logErrors = checkAndLog(basketButton.isEmpty(), logErrors, "Ошибка: нет кнопки корзины", "Кнопка корзины есть");
        logErrors = checkAndLog(basketButtonIcon.isEmpty(), logErrors, "Ошибка: нет иконки портфеля на кнопке корзины", "Иконка портфеля на кнопке корзины есть");

        return logErrors;

    }

    // проверяет отображение подвала на главной странице и наличие в нём корректных данных, таких как название сайта, электронную почту и ссылки на другие сайты
    private ElementsCollection siteName = $$(By.xpath("//p[contains(text(),'Официальный сайт для отбора иностранных граждан на обучение в Российской Федерации')]"));
    private ElementsCollection emaiRussiaStudy = $$(By.xpath("//a[@href='mailto:info@russia.study']"));
    private ElementsCollection displayEmailRussiaStudy = $$(By.xpath("//strong[contains(text(),'info@russia.study')]"));
    private ElementsCollection linkToRossotrudnichesvo = $$(By.xpath("//a[@href='http://rs.gov.ru/']"));
    private ElementsCollection linkToMinobr = $$(By.xpath("//a[@href='http://минобрнауки.рф/']"));

    public int basementHomePage(int logErrors){

        logErrors = checkAndLog(siteName.isEmpty(), logErrors, "Ошибка: в подвале нет названия сайта", "Название сайта в подвале есть");
        logErrors = checkAndLog(emaiRussiaStudy.isEmpty(), logErrors, "Ошибка: нет ссылки на Emai", "Ссылка на Email есть");
        logErrors = checkAndLog(displayEmailRussiaStudy.isEmpty(), logErrors, "Ошибка: Email не отображается", "Email отображается");
        logErrors = checkAndLog(linkToRossotrudnichesvo.isEmpty(), logErrors, "Ошибка: ссылки на сайт Россотрудничества нет", "Ссылка на сайт Россотрудничества есть");
        logErrors = checkAndLog(linkToRossotrudnichesvo.isEmpty(), logErrors, "Ошибка: ссылки на сайт Минобрнауки нет", "Ссылка на сайн Минобрнауки есть");

        return logErrors;
    }
    // проверяет кнопки и названия в шапке и подвале на русскоязычность

    // проверяет кнопки и названия в шапке и подвале на англоязычность

}
