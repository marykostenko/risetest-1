import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.WebDriverRunner.webdriverContainer;

/**
 * Created by Maria on 06.06.2017.
 */
public class PayServiceFeeTest extends BaseTest
{
    //PAYNENT-1. Оплата сервисного сбора

    //PAYMENT-1.1
    @Test(priority = 1)
    public void testRegistrationQuotaWithPartialFilling() throws IOException, InterruptedException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        log("Запущен тест CAND-REG-2.1");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Регистрируем тестового контрактного кандидата для оплаты сервисного сбора");
        TestUserData testUserData = new TestUserData();
        TestUserData userDataForPayFee = new TestUserData(getUserForPayFeeId());
        TestRandomUserData userDataForPayFeeRandomEmail = new TestRandomUserData(getUserForPayFeeId());
        testUserData.registrationCandidateForPayFeeTest(userDataForPayFeeRandomEmail.getUserForPayFeeRandomEmail(),userDataForPayFee.getUserLastName(), userDataForPayFee.getUserFirstName(), userDataForPayFee.getSex(), userDataForPayFee.getCountry(),
                userDataForPayFee.getUserPassword());

        log("Входим под зарегистрированным пользователем");
        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        PageLogin pageLogin = new PageLogin();
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(userDataForPayFeeRandomEmail.getRandomEmail(), userDataForPayFee.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);

        log("Заполняем обязательные поля в заявке кандидата и сохраняем заявку");
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        pageEditCandidate.fillCandidateRequest(userDataForPayFee.getPlaceOfBirth(), userDataForPayFee.getDateOfBirth(), userDataForPayFee.getEducationLvl(),
                userDataForPayFee.getPreviousEduOrganization(), userDataForPayFee.getCountryOfFinishedEducationOrganisation(), userDataForPayFee.getLvlId(), userDataForPayFee.getEduDirId());

        PageRequest pageRequest = new PageRequest();
        log("Добавляем копию паспорта");
        pageRequest.loadingCopyOfPassport();

        log("Добавлем копию документа об образовании");
        pageRequest.loadingCopyOfTheDocumentOnEducation();

        log("Переходим к форме оплаты сервисного сбора");
        pageRequest.goToPayServiceFee();

        log("Проверяем наличие айфрейма Тинькофф");
        PagePaymentServiceFee pagePaymentServiceFee = new PagePaymentServiceFee();
        logErrors = pagePaymentServiceFee.checkIframe(logErrors);

        log("Вводим тестовые данные для успешной оплаты");

        checkMistakes();

    }
}
