import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by user nkorobicina on 07.12.2016.
 */
//USER-L-1
public class LoginSuccessfulTest extends BaseTest {

    //USER-L-1.1
    @Test(priority = 1)
    public void testAdminLogin() throws IOException {
        log("Запущен тест USER-L-1.1");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminData = new TestUserData(getAdminId());
        pageLogin.fillLoginForm(testAdminData.getUserLogin(), testAdminData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFILoggedUser());
        logErrors = pageTopBottom.assertFIOLoggedIn(testAdminData.getUserFirstName() + " " + testAdminData.getUserLastName(), logErrors);

        log("Проверяем доступные пункты меню");
        logErrors = pageTopBottom.assertAdminMenu(logErrors);

        log("Проверяем ФИО пользователя в карточке");
        PageUserProfile pageAdminProfile = new PageUserProfile();
        log(pageAdminProfile.getUserFio());
        logErrors = pageAdminProfile.assertUserFIO(testAdminData.getUserLastName() + " " + testAdminData.getUserFirstName() + " " + testAdminData.getUserMiddleName(), logErrors);

        checkMistakes();

        log("Тест USER-L-1.1 завершен");
    }

//USER-L-1.2 логин кандидата
    @Test(priority = 2)
    public void testCandidateLogin() throws IOException {
        log("Запущен тест USER-L-1.2");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testCandidateData = new TestUserData(getCandidateId());
        pageLogin.fillLoginForm(testCandidateData.getUserLogin(), testCandidateData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя кандидата в шапке:");
        String fioLoggedIn = pageTopBottom.getFICandidate();
        log(fioLoggedIn);
        logErrors = pageTopBottom.assertFIOCandidateLoggedIn(testCandidateData.getUserFirstName() + " " + testCandidateData.getUserLastName(), logErrors);

        log("Проверяем доступные кандидату пункты меню");
        logErrors = pageTopBottom.assertCandidateMenu(logErrors);

        log("Проверяем пункты меню в карточке кандидата");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.assertCandidateCardMenu(logErrors);

        checkMistakes();

        log("Тест USER-L-1.2 завершен");
    }

    //USER-L-1.3
    @Test(priority = 3)
    public void testRepresentativeLogin() throws IOException {
        log("Запущен тест USER-L-1.3");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        pageLogin.fillLoginForm(testRepresentativeData.getUserLogin(), testRepresentativeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFILoggedUser());
        logErrors = pageTopBottom.assertFIOLoggedIn(testRepresentativeData.getUserFirstName() + " " + testRepresentativeData.getUserLastName(), logErrors);

        log("Проверяем доступные пункты меню");
        logErrors = pageTopBottom.assertRepresentativeMenu(logErrors);

        log("Проверяем доступные вкладки открывшейся карточки органа власти");
        PageGovernmentCard pageGovernmentCard = new PageGovernmentCard();
        logErrors = pageGovernmentCard.assertMinimalMenu(logErrors);

        checkMistakes();
        log("Тест USER-L-1.3 завершен");
    }

    //USER-L-1.4
    @Test(priority = 4)
    public void testDistributiveLogin() throws IOException {
        log("Запущен тест USER-L-1.4");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testDistributiveData = new TestUserData(getDistributiveId());
        pageLogin.fillLoginForm(testDistributiveData.getUserLogin(), testDistributiveData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFILoggedUser());
        logErrors = pageTopBottom.assertFIOLoggedIn(testDistributiveData.getUserFirstName() + " " + testDistributiveData.getUserLastName(), logErrors);

        log("Проверяем доступные пункты меню");
        logErrors = pageTopBottom.assertDistributiveMenu(logErrors);

        log("Проверяем доступные вкладки открывшейся карточки органа власти");
        PageGovernmentCard pageGovernmentCard = new PageGovernmentCard();
        logErrors = pageGovernmentCard.assertMinimalMenu(logErrors);

        checkMistakes();
        log("Тест USER-L-1.4 завершен");
    }

    //USER-L-1.5
    @Test(priority = 5)
    public void testAgreementLogin() throws IOException {
        log("Запущен тест USER-L-1.5");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAgreementData = new TestUserData(getAgreementId());
        pageLogin.fillLoginForm(testAgreementData.getUserLogin(), testAgreementData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFILoggedUser());
        logErrors = pageTopBottom.assertFIOLoggedIn(testAgreementData.getUserFirstName() + " " + testAgreementData.getUserLastName(), logErrors);

        log("Проверяем доступные пункты меню");
        logErrors = pageTopBottom.assertAgreementMenu(logErrors);

        log("Проверяем доступные вкладки открывшейся карточки органа власти");
        PageGovernmentCard pageGovernmentCard = new PageGovernmentCard();
        logErrors = pageGovernmentCard.assertMinimalMenu(logErrors);

        checkMistakes();
        log("Тест USER-L-1.5 завершен");
    }

    //USER-L-1.6
    @Test(priority = 6)
    public void testOrganizationAdministratorLogin() throws IOException {
        log("Запущен тест USER-L-1.6");

        log("Переключаем язык страницы на русский");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        TestUserData testAdminOrgData = new TestUserData(getAdminOrgId());
        pageLogin.fillLoginForm(testAdminOrgData.getUserLogin(), testAdminOrgData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Проверяем имя пользователя в шапке:");
        log(pageTopBottom.getFILoggedUser());
        logErrors = pageTopBottom.assertFIOLoggedIn(testAdminOrgData.getUserFirstName() + " " + testAdminOrgData.getUserLastName(), logErrors);

        log("Проверяем доступные пункты меню");
        logErrors = pageTopBottom.assertAdminOrganizationMenu(logErrors);

        log("Проверяем доступные вкладки открывшейся карточки образовательной организации");
        PageOrganizationCard pageOrganizationCard = new PageOrganizationCard();
        logErrors = pageOrganizationCard.assertMinimalAdminOrgMenuAppear(logErrors);

        checkMistakes();
        log("Тест USER-L-1.6 завершен");
    }
}
