import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница шапка, подвал и строка главного меню
 * Created by user nkorobicina on 07.12.2016.
 */

public class PageTopBottom extends BasePage
{

    // открывает главную страницу сайта
    public void goToHomePage ()
    {
        $(By.xpath("//a[@class='header_logo_link']")).click();
    }

    //открывает страницу логина
    public void goToLogin(){
        $(By.xpath("//a[contains(@href,'login')]")).click();
    }

    //открывает страницу регистрации
    public void goToRegistration() { $(By.xpath(" //nav[@class='login_nav login_nav_header']//child::a[@href='/registration'] ")).click(); }

    //открывает корзину
    public void openBasket() { $(By.xpath("//div[@class='rs-basket']//child::div[@class='dropdown-btn']")).click(); }

    //нажимоет кнопку в корзине
    public void pushTheButtonInBasket() { $(By.xpath("//div[@class='fmstyle']")).click(); }

    public void login(String userLogin, String userPassword) throws InterruptedException
    {
        goToLogin();
        PageLogin pageLogin = new PageLogin();
        pageLogin.fillLoginForm(userLogin, userPassword);
        pageLogin.pushLoginButton();
    }

    /**
     * метод возвращает имя и фамилию, которые видны в шапке, когда пользователь залогинен
     */
    public String getFILoggedUser()
    {
       return $(By.xpath("//div[@class='login_nav login_nav_header']//child::a[contains(@href,'/user/')]")).text();
    }

    /**
     * возвращает имя и фамилию кандидата, которые видны в шапке
     * @return
     */
    public String getFICandidate()
    {
        return $(By.xpath("//div[@class='login_nav login_nav_header']//a[contains(@href,'/candidates/')]")).text();
    }

    private ElementsCollection logoutButton = $$(By.xpath("//a[contains(@href, 'logout')]"));

    /**
     * возвращает true, если залогинены
     */

    public boolean isLoggedIn()
    {
        if(logoutButton.isEmpty())
            return false;
        else
            return true;
    }


    public void logout()
    {
        logoutButton.get(0).click();
        $(By.xpath("//div[@class='login_nav_item']/a[@href='/registration']")).waitUntil(appear, waitTime);
    }


    /**
     * пункты главного меню
     */
    private String[] menuLine = {"План приема", "Организации", "Кандидаты", "Страны", "Группы", "Агенты", "Администрирование" };


    /**
     * возвращаетс true, если найден пункт меню с заданным номером
     */
    protected boolean isAnyMenuAppear(int i)
    {
        ElementsCollection anyMenu = $$(By.linkText(menuLine[i]));
        return !anyMenu.isEmpty();
    }

    /**
     * на входе - количество некритичных ошибок в тесте, на выходе logErrors увеличивается на количество не найденных пунктов меню.
     * проверяются все пункты меню - для админа
     */

    public int assertAdminMenu(int logErrors)
    {
        int menu = menuLine.length;
        for(int i = 0; i <  menu; i++)
        {
            logErrors = checkAndLog(!isAnyMenuAppear(i), logErrors, "Ошибка: Пункт меню " + menuLine[i] + " недоступен",
                    "Пункт меню " + menuLine[i] + " доступен");
        }
        return logErrors;
    }

    /**
     * открывает меню администратора
     */
    public void openAdminMenu()
    {
        $(By.xpath("//div[@class='clearfix']//child::a[@class='dropdown-toggle padding-right-0']")).click();
    }

    //возвращает истину, если язык страницы русский
    protected boolean isRuLang(){
        ElementsCollection ruActive = $$(By.xpath("//div[@class='dropdown-btn']//child::img[@src='/assets/images/ru.jpg']"));
        if(ruActive.isEmpty()){
            return false;
        }
        else
            return true;
    }

    //переключает язык страницы на русский
    public void switchToRu(){
        if(!isRuLang()){
            $(By.xpath("//div[@class='rs-language']")).click();
            $(By.xpath("//div[@data-language='ru-RU']")).click();
        }
    }

    /**
     * иконка-домик слева, для пользователей-не админов всегда есть
     */
    //возвращает истину, если язык страницы английский
    protected boolean isEnLang(){
        ElementsCollection enActive = $$(By.xpath("//a[contains(@class, 'eng active')]"));
        if(enActive.isEmpty()){
            return false;
        }
        else
            return true;
    }
    //переключает язык страницы на английский
    public void switchToEn(){
        if(!isEnLang()){
            $(By.xpath("//div[@class='rs-language']")).click();
            $(By.xpath("//div[@data-language='en-EN']")).click();
        }
    }

    private ElementsCollection homeLogo = $$(By.xpath("//i[@class='fa fa-home']"));

    /**
     * пункт меню "Поиск образовательных программ"
     */
    private ElementsCollection navMenu = $$(By.xpath("//a[contains(@href, '/navigator#')]"));

    public void goToNavigator() { $(By.xpath("//nav[@class='main_nav main_nav_header']//child::div[@class='main_nav_item mobile_nav_5 rs-swap-target rs-swap-enabled']")).click(); }

    /**
     * проверка, что главное меню кандидата содержит Личный кабинет, иконку-домик, поиск образовательных программ
     */
    public int assertCandidateMenu(int logErrors)
    {

        logErrors = checkAndLog(homeLogo.isEmpty(), logErrors, "Ошибка: нет иконки Личный кабинет");

        ElementsCollection privateOffice = $$(By.linkText("Личный кабинет"));
        logErrors = checkAndLog(privateOffice.isEmpty(), logErrors, "Ошибка: нет текста Личный кабинет");

        logErrors = checkAndLog(navMenu.isEmpty(), logErrors, "Ошибка: нет пункта меню Поиск образовательных программ");

        return logErrors;
    }

    /**
     * проверка, что вход выполнен: если не найдена кнопка выхода из системы, количество ошибок увеличивается и логируется текст ошибки
     */
    public int assertLoggingIn(int logErrors)
    {
        return checkAndLog(!isLoggedIn(), logErrors, "Ошибка: не выполнен вход в систему - нет кнопки выхода из системы");
    }

    /**
     * проверка, что Фамилия и Имя пользователя в шапке соответствует ожидаемому.
     * Если нет, то увеличивается число ошибок и логируется найденное имя пользователя
     */
    public int assertFIOLoggedIn(String expectedFIO, int logErrors)
    {
        return checkAndLog(!getFILoggedUser().equals(expectedFIO), logErrors,
                "Ошибка: В шапке неправильное имя пользователя - " + getFILoggedUser() + "; ожидалось - " + expectedFIO);
    }

    public int assertFIOCandidateLoggedIn(String expectedFIO, int logErrors)
    {
        return checkAndLog(!getFICandidate().equals(expectedFIO), logErrors,
                "Ошибка: В шапке неправильное имя кандидата - " + getFICandidate() + "; ожидалось - " + expectedFIO);
    }

    private ElementsCollection governmentMenu = $$(By.xpath("//ul[@class='nav pull-left']//child::a[contains(@href,'/government/')]"));

    /**
     * проверка, что главное меню представителя в стране содержит иконку-домик, меню Россотрудничество, Кандидаты, Страны
     */
    public int assertRepresentativeMenu(int logErrors)
    {
        logErrors = checkAndLog(homeLogo.isEmpty(), logErrors, "Ошибка: нет иконки домика", "Иконка-домик отображается");

        logErrors = checkAndLog(governmentMenu.isEmpty(), logErrors, "Ошибка: нет пункта меню Органа власти", "Пункт главного меню Орган власти доступен");

        int j[] = {2, 3};
        for(int i = 0; i <= 1; i++)
        {
            logErrors = checkAndLog(!isAnyMenuAppear(j[i]), logErrors, "Ошибка: Пункт меню " + menuLine[j[i]] + " недоступен",
                    "Пункт меню " + menuLine[j[i]] + " доступен");
        }
        return logErrors;
    }

    /**
     * проверка, что главное меню распределения кандидатов содержит иконку-домик, меню перехода на карточку органа власти, Организации, Кандидаты, Страны, Визы
     */
    public int assertDistributiveMenu(int logErrors)
    {

        logErrors = checkAndLog(homeLogo.isEmpty(), logErrors, "Ошибка: нет иконки домика", "Иконка-домик отображается");

        logErrors = checkAndLog(governmentMenu.isEmpty(), logErrors, "Ошибка: нет пункта меню Органа власти", "Пункт главного меню Орган власти доступен");

        int j[] = {1, 2, 3};
        for(int i = 0; i <= 2; i++){
            logErrors = checkAndLog(!isAnyMenuAppear(j[i]), logErrors, "Ошибка: Пункт меню " + menuLine[j[i]] + " недоступен",
                    "Пункт меню " + menuLine[j[i]] + " доступен");
        }
        return logErrors;
    }

    //проверка, что главное меню согласования кандидатов содержит иконку-домик, меню Россотрудничество,  Кандидаты, Страны
    public int assertAgreementMenu(int logErrors){

        logErrors = checkAndLog(homeLogo.isEmpty(), logErrors, "Ошибка: нет иконки домика", "Иконка-домик отображается");

        logErrors = checkAndLog(governmentMenu.isEmpty(), logErrors, "Ошибка: нет пункта меню Органа власти", "Пункт главного меню Орган власти доступен");

        int j[] = {2, 3};
        for(int i = 0; i <= 1; i++){
            logErrors = checkAndLog(!isAnyMenuAppear(j[i]), logErrors, "Ошибка: Пункт меню " + menuLine[j[i]] + " недоступен",
                    "Пункт меню " + menuLine[j[i]] + " доступен");
        }
        return logErrors;
    }

    private ElementsCollection organizationMenu = $$(By.xpath("//ul[@class = 'nav pull-left']/li/a[contains(@href, '/organization')]"));

    /**
     * проверка, что главное меню администратора организации содержит иконку-домик и ссылку на домашнюю страницу
     */

    public int assertAdminOrganizationMenu(int logErrors)
    {

        logErrors = checkAndLog(homeLogo.isEmpty(), logErrors, "Ошибка: нет иконки домика", "Иконка-домик отображается");

        logErrors = checkAndLog(organizationMenu.isEmpty(), logErrors, "Ошибка: нет пункта меню организации", "Пункт главного меню организации доступен");

        return logErrors;
    }

    /**
     * Переходим в профиль пользователя
     */

    public void goToUserProfile()
    {
        $(By.xpath("//div[@class='rs-row']//child::a[contains(@href,'/user/')]")).click();
    }


    /**
     *открывает страницу с пользователями
     */
    public void goToUsersList()
    {
        $(By.xpath("//a[@href='/users']")).click();
    }


    public void openEdInRus() { $(By.xpath("//nav[@class='main_nav main_nav_header']//child::div[@class='main_nav_item mobile_nav_1 rs-swap-target rs-swap-enabled']")).click(); }
    /**
     * переходит на публичную страницу "Как поступить"
     */
    public void goToPublicPageHowToApply() { $(By.xpath("//nav[@class='main_nav main_nav_header']//child::a[contains(@href,'/ru/public-material/how-to-apply')]")).click(); }

}
