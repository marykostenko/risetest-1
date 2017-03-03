import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Maria on 28.12.2016.
 */
public class HomePageControl  extends BasePage{

    // проверяет отображение шапки на главной странице и наличие в ней логотипа Россотрудничества, названия сайта, кнопок смены языка, вход и регистрация, кнопка корзины
    private ElementsCollection logo = $$(By.xpath("//a[@class='header_logo_link']"));
    private ElementsCollection logoTop = $$(By.xpath("//div[@class='logo-top']"));
    private ElementsCollection siteNameLogo = $$(By.xpath("//div[@class='logo-bottom']"));
    private ElementsCollection loginButton = $$(By.xpath("//nav[@class='login_nav login_nav_header']//child::a[contains(@href,'/login')]"));
    private ElementsCollection registrationButton = $$(By.xpath("//nav[@class='login_nav login_nav_header']//child::a[@href='/registration']"));
    private ElementsCollection basketButton = $$(By.xpath("//div[@class='rs-basket']"));
    private ElementsCollection basketButtonIcon = $$(By.xpath("//img[@src='/assets/images/basket.svg']"));

    public int hatHomePage (int logErrors){

        logErrors = checkAndLog(logo.isEmpty(), logErrors, "Ошибка: нет логотипа Россотрудничества", "Логотип Россотрудничества есть");
        logErrors = checkAndLog(logoTop.isEmpty(), logErrors, "Ошибка: нет логотипа сайта RUSSIA.STUDY", "Логотип сайта RUSSIA.STUDY есть");
        logErrors = checkAndLog(siteNameLogo.isEmpty(), logErrors, "Ошибка: название сайта отсутсвует", "Название сайта есть");
        logErrors = checkAndLog(loginButton.isEmpty(), logErrors, "Ошибка: нет кнопки Вход", "Кнопка Вход есть");
        logErrors = checkAndLog(registrationButton.isEmpty(), logErrors, "Ошибка: нет кнопки Регистрация", "Кнопка Регистрация есть");
        logErrors = checkAndLog(basketButton.isEmpty(), logErrors, "Ошибка: нет кнопки корзины", "Кнопка корзины есть");
        logErrors = checkAndLog(basketButtonIcon.isEmpty(), logErrors, "Ошибка: нет иконки корзины", "Иконка корзины есть.");

        return logErrors;

    }

    // проверяет отображение подвала на главной странице и наличие в нём корректных данных, таких как название сайта, электронную почту и ссылки на другие сайты
    private ElementsCollection emaiRussiaStudy = $$(By.xpath("//a[@href='mailto:info@russia.study']"));
    private ElementsCollection displayEmailRussiaStudy = $$(By.xpath("//strong[contains(text(),'info@russia.study')]"));
    private ElementsCollection linkToRossotrudnichestvo = $$(By.xpath("//a[@href='http://rs.gov.ru/']"));
    private ElementsCollection linkToMinobr = $$(By.xpath("//a[@href='http://минобрнауки.рф/']"));

    public int basementHomePage(int logErrors){

        logErrors = checkAndLog(emaiRussiaStudy.isEmpty(), logErrors, "Ошибка: нет ссылки на Emai", "Ссылка на Email есть");
        logErrors = checkAndLog(displayEmailRussiaStudy.isEmpty(), logErrors, "Ошибка: Email не отображается", "Email отображается");
        logErrors = checkAndLog(linkToRossotrudnichestvo.isEmpty(), logErrors, "Ошибка: ссылки на сайт Россотрудничества нет", "Ссылка на сайт Россотрудничества есть");
        logErrors = checkAndLog(linkToMinobr.isEmpty(), logErrors, "Ошибка: ссылки на сайт Минобрнауки нет", "Ссылка на сайт Минобрнауки есть");

        return logErrors;
    }
    // проверяет кнопки и названия в шапке и подвале на русскоязычность
    private ElementsCollection siteNameLogoRu = $$(By.xpath("//div[contains(text(),'Официальный сайт для отбора иностранных граждан на обучение в Российской Федерации')]"));
    private ElementsCollection loginButtonRu = $$(By.xpath("//a[contains(text(),'Вход')]"));
    private ElementsCollection registrationButtonRu = $$(By.xpath("//a[contains(text(),'Регистрация')]"));
    private ElementsCollection basketButtonRu = $$(By.linkText("Ваш выбор"));
    private ElementsCollection rossotrudnichestvoRu = $$(By.xpath("//div[contains(text(),'РОССОТРУДНИЧЕСТВО')]"));
    private ElementsCollection textRossotrudnichestvoRu = $$(By.linkText("Федеральное агентство по делам Содружества Независимых государств, соотечественников, проживающих за рубежом, и по международному гуманитарному сотрудничеству "));
    private ElementsCollection agencyRu = $$(By.xpath("//div[contains(text(),'АГЕНТСТВО ПО СОТРУДНИЧЕСТВУ В ОБРАЗОВАНИИ')]"));
    private ElementsCollection textAgencyRu = $$(By.xpath("//span[contains(text(),'Оператор портала russia.study')]"));
    private ElementsCollection minobrRu = $$(By.xpath("//div[contains(text(),'МИНОБРНАУКИ РОССИИ')]"));
    private ElementsCollection textMinobrRu = $$(By.linkText("Министерство образования и науки Российской Федерации"));

    public int textPageRu(int logErrors){

        logErrors = checkAndLog(siteNameLogoRu.isEmpty(), logErrors, "Ошибка: Название сайта в шапке неверное", "Название сайта в шапке верное");
        logErrors = checkAndLog(loginButtonRu.isEmpty(), logErrors, "Ошибка: невеное название кнопки Вход", "Название кнопки Вход верное");
        logErrors = checkAndLog(registrationButtonRu.isEmpty(), logErrors, "Ошибка: неверное название кнопки Регистрация", "Название кнопки Регистрация верное");
        logErrors = checkAndLog(basketButtonRu.isEmpty(), logErrors, "Ошибка: неверный текст на кнопке корзины", "Текст на кнопке корзины верный");
        logErrors = checkAndLog(rossotrudnichestvoRu.isEmpty(), logErrors, "Ошибка: РОССОТРУДНИЧЕСТВО в подвале написано неверно", "РОСОТРУДНИЧЕСВО в подвале написано верно");
        logErrors = checkAndLog(textRossotrudnichestvoRu.isEmpty(), logErrors, "Ошибка: текст описания РОССОТРУДНИЧЕСТВА написан неверно", "Текст описания РОССОТРУДНИЧЕСТВА написан верно");
        logErrors = checkAndLog(agencyRu.isEmpty(), logErrors, "Ошибка: АГЕНТСТВО ПО СОТРУДНИЧЕСТВУ В ОБРАЗОВАНИИ написано неверно", "АГЕНТСТВО ПО СОТРУДНИЧЕСТВУ В ОБРАЗОВАНИИ написано верно");
        logErrors = checkAndLog(textAgencyRu.isEmpty(), logErrors, "Ошибка: \"Оператор портала russia.study\" написано неверно", "\"Оператор портала russia.study\" написано верно");
        logErrors = checkAndLog(minobrRu.isEmpty(), logErrors, "Ошибка: МИНОБРНАУКИ РОССИИ написано неверно", "МИНОБРНАУКИ РОССИИИ написано верно");
        logErrors = checkAndLog(textMinobrRu.isEmpty(), logErrors, "Ошибка: описание МИНОБРНАУКИ написано неверно", "Описание МИНОБРНАУКИ написано верно");


        return logErrors;
    }

    // проверяет кнопки и названия в шапке и подвале на англоязычность
    private ElementsCollection siteNameLogoEn = $$(By.xpath("//div[contains(text(),'Official Website for Foreign Nationals Enrollment for Study')]"));
    private ElementsCollection loginButtonEn = $$(By.xpath("//a[contains(text(),'Login')]"));
    private ElementsCollection registrationButtonEn = $$(By.xpath("//a[contains(text(),'Register')]"));
    private ElementsCollection basketButtonEn = $$(By.linkText("Your choice"));
    private ElementsCollection siteNameEn = $$(By.xpath("//p[contains(text(),'Official Website for Foreign Nationals Enrollment for Study')]"));
    private ElementsCollection rossotrudnichestvoEn = $$(By.xpath("//div[contains(text(),'ROSSOTRUDNICHESTVO')]"));
    private ElementsCollection textRossotrudnichestvoEn = $$(By.linkText("The Federal Agency for the Commonwealth of Independent States, Compatriots Living Abroad, and International Humanitarian Cooperation "));
    private ElementsCollection agencyEn = $$(By.xpath("//div[contains(text(),'AGENCY FOR COOPERATION IN EDUCATION')]"));
    private ElementsCollection textAgencyEn = $$(By.xpath("//span[contains(text(),'The russia.study Portal Managing Operator')]"));
    private ElementsCollection minobrEn = $$(By.xpath("//div[contains(text(),'Minobrnauki of Russia')]"));
    private ElementsCollection textMinobrEn = $$(By.linkText("The Ministry of Science and Education of Russian Federation"));

    public int textPageEn(int logErrors) {

        logErrors = checkAndLog(siteNameLogoEn.isEmpty(), logErrors, "Ошибка: Название сайта в шапке неверное", "Название сайта в шапке верное");
        logErrors = checkAndLog(loginButtonEn.isEmpty(), logErrors, "Ошибка: невеное название кнопки Вход", "Название кнопки Вход верное");
        logErrors = checkAndLog(registrationButtonEn.isEmpty(), logErrors, "Ошибка: неверное название кнопки Регистрация", "Название кнопки Регистрация верное");
        logErrors = checkAndLog(basketButtonEn.isEmpty(), logErrors, "Ошибка: неверный текст на кнопке корзины", "Текст на кнопке корзины верный");
        logErrors = checkAndLog(siteNameEn.isEmpty(), logErrors, "Ошибка: название сайта в подвале неверное", "Название сайта в подвале верное");
        logErrors = checkAndLog(rossotrudnichestvoEn.isEmpty(), logErrors, "Ошибка: РОССОТРУДНИЧЕСТВО в подвале написано неверно", "РОСОТРУДНИЧЕСВО в подвале написано верно");
        logErrors = checkAndLog(textRossotrudnichestvoEn.isEmpty(), logErrors, "Ошибка: текст описания РОССОТРУДНИЧЕСТВА написан неверно", "Текст описания РОССОТРУДНИЧЕСТВА написан верно");
        logErrors = checkAndLog(agencyEn.isEmpty(), logErrors, "Ошибка: AGENCY FOR COOPERATION IN EDUCATION написано неверно", "AGENCY FOR COOPERATION IN EDUCATION написано верно");
        logErrors = checkAndLog(textAgencyEn.isEmpty(), logErrors, "Ошибка: \"The russia.study Portal Managing Operator\" написано неверно", "\"The russia.study Portal Managing Operator\" написано верно");
        logErrors = checkAndLog(minobrEn.isEmpty(), logErrors, "Ошибка: Minobrnauki of Russia написано неверно", "Minobrnauki of Russia написано верно");
        logErrors = checkAndLog(textMinobrEn.isEmpty(), logErrors, "Ошибка: описание Minobrnauki написано неверно", "Описание Minobrnauki написано верно");

        return logErrors;
    }
}
