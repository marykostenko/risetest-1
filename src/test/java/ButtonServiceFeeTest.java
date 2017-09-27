import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by user nkorobicina on 25.09.2017.
 */
public class ButtonServiceFeeTest extends BaseTest {


    //Service-Fee-2.1
    @Test(priority = 1)
    public void testCheckPaymentButtonApplicationForAContractIsAcceptedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест Service-Fee-2.1 Проверка кнопки Оплата у кандидатов в состоянии Заявка на контракт принята, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Заявка на контракт принята', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForAContractIsAccepted();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест Service-Fee-2.1 завершен");
    }

    //Service-Fee-2.2
    @Test(priority = 2)
    public void testCheckPaymentButtonApplicationForOnUniApprovalUnpaidServiceFee() throws IOException
    {

        log("Запущен тест Service-Fee-2.2 Проверка кнопки Оплата у кандидатов в состоянии На рассмотрении вуза, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'На рассмотрении вуза', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForOnUniApproval();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест Service-Fee-2.2 завершен");
    }

    //Service-Fee-2.3
    @Test(priority = 3)
    public void testCheckPaymentButtonApplicationForDistributedUnpaidServiceFee() throws IOException
    {

        log("Запущен тест Service-Fee-2.3 Проверка кнопки Оплата у кандидатов в состоянии Распределён, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Распределён', сервисный сбор 'Не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForDistributed();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест Service-Fee-2.3 завершен");
    }

    //Service-Fee-2.4
    @Test(priority = 4)
    public void testCheckPaymentButtonApplicationForAContractIsAcceptedPaidServiceFee() throws IOException
    {

        log("Запущен тест Service-Fee-2.4 Проверка отсутсвия кнопки Оплата у кандидатов в состоянии Заявка на контракт принята, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Заявка на контракт принята', сервисный сбор 'Оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForAContractIsAccepted();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест Service-Fee-2.4 завершен");
    }

    //Service-Fee-2.5
    @Test(priority = 5)
    public void testCheckPaymentButtonApplicationForOnUniApprovalPaidServiceFee() throws IOException
    {

        log("Запущен тест Service-Fee-2.5 Проверка отсутствия кнопки Оплата у кандидатов в состоянии На рассмотрении вуза, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'На рассмотрении вуза', сервисный сбор 'оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForOnUniApproval();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест Service-Fee-2.5 завершен");
    }

    //Service-Fee-2.6
    @Test(priority = 6)
    public void testCheckPaymentButtonApplicationForDistributedPaidServiceFee() throws IOException
    {

        log("Запущен тест Service-Fee-2.6 Проверка отсутствия кнопки Оплата у кандидатов в состоянии Распределён, у которых оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Распределён', сервисный сбор 'Оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForDistributed();
        pageCandidateList.filterServiceFeePaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем отсутствие кнопки 'Оплатить'");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkLackOfPaymentButton(logErrors);

        checkMistakes();

        log("Тест Service-Fee-2.6 завершен");
    }

    //Service-Fee-2.7
    @Test(priority = 7)
    public void testCheckPaymentButtonApplicationForAContractFillingUnpaidServiceFee() throws IOException
    {

        log("Запущен тест Service-Fee-2.7 Проверка кнопки Оплата у кандидатов в состоянии Заполнение заявки на контракт, у которых не оплачен сервисный сбор");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
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

        log("Переходим в список кандидатов");
        MenuContent menuContent = new MenuContent();
        menuContent.goToCandidatesList();

        log("Находим кандидата в состоянии 'Заявка на контракт принята', сервисный сбор 'не оплачен'");
        PageCandidateList pageCandidateList = new PageCandidateList();
        pageCandidateList.filterApplicationForAContractFilling();
        pageCandidateList.filterServiceFeeUnpaid();
        pageCandidateList.selectFirstCandidate();

        log("Проверяем наличие кнопки 'Оплатить', нажимаем на неё, проверяем форму оплаты");
        PageCandidateCard pageCandidateCard = new PageCandidateCard();
        logErrors = pageCandidateCard.checkPaymentButton(logErrors);
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        checkMistakes();

        log("Тест Service-Fee-2.7 завершен");
    }
}
