import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Maria on 24.05.2017.
 */
public class CandidateRegistrationTest extends BaseTest
{

//Самостоятельная регистрация кандидата

    //CAND-REG-2.1
    @Test(priority = 1)
    public void testCheckRegistrationMail() throws InterruptedException, IOException, MessagingException
    {
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        TestMail testMail = new TestMail();
        PageRegistration pageRegistration = new PageRegistration();

        log("Запущен тест CAND-REG-2.1");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Генирируем рандомный email");
        String randomEmail = String.valueOf(testRandomUserData.createRandomEmail());

        log("Переходим на страницу регитсрации кандидата");
        pageTopBottom.goToRegistration();

        log("Заполняем обязательные поля для регистрации");
        pageRegistration.partialFillingRegistrationForm(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(),
                registrationQuotaPartialUserData.getSexRu(), registrationQuotaPartialUserData.getCountry(), randomEmail, registrationQuotaPartialUserData.getUserPassword());

        if (!testMail.checkRegistrationMail(randomEmail))
        {
            logErrors++;
        }

        checkMistakes();
        log("Тест CAND-REG-2.1 завершен");
    }

    //CAND-REG-2.2
    @Test(priority = 2)
    public void testCheckConfirmationRegistrationMail() throws InterruptedException, IOException, MessagingException, SQLException {
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        TestMail testMail = new TestMail();
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        PageEditCandidate pageEditCandidate = new PageEditCandidate();
        TestUserData testUserData = new TestUserData();

        log("Запущен тест CAND-REG-2.1");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Генирируем рандомный email");
        String randomEmail = String.valueOf(testRandomUserData.createRandomEmail());

        System.out.println("Формируем адрес для POST запроса на регистрацию");
        String urlForRequestRegistration = pageEditCandidate.createUrlRequestForRegistrationContract(getStandUrl(flagForStandUrl));

        log("Отправляем пост завтрос с данными на регистрацию тетсовго кандидата");
        testRequestsForHttp.successPostRequest(testRequestsForHttp.postRequestForRegistrationWithPartialFilling(urlForRequestRegistration, registrationQuotaPartialUserData.getUserLastName(),
                registrationQuotaPartialUserData.getUserFirstName(), registrationQuotaPartialUserData.getSexEn(), registrationQuotaPartialUserData.getCountryId(), randomEmail,
                registrationQuotaPartialUserData.getUserPassword(), null));

        log("Активируем кандидата");
        if (testUserData.activationCandidate(getStandUrl(flagForStandUrl), randomEmail))
        {
            logErrors++;
        }

        if (!testMail.checkConfirmationRegistrationMail(randomEmail, getStandUrl(flagForStandUrl)))
        {
            logErrors++;
        }

        log("Тест CAND-REG-2.1 завершен");
        checkMistakes();
    }


    //CAND-REG-2.3
    @Test(priority = 3)
    public void testRegistrationQuotaWithPartialFilling() throws IOException, InterruptedException, MessagingException
    {

        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        TestRandomUserData registrationPartialQuotaUserRandomEmail = new TestRandomUserData(getUserForRegistrationPartialQuotaId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = true;
        boolean contract = false;

        log("Запущен тест CAND-REG-2.3");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(),null,
                registrationQuotaPartialUserData.getSexRu(), registrationQuotaPartialUserData.getCountry(), registrationQuotaPartialUserData.getUserPassword(), contract, partial, getStandUrl(flagForStandUrl)));

        if (testRandomUserData.randomEmailNotNull(randomEmail))
        {
            log("Сохраняем email для последующего входа под этим кандидатом");
            registrationPartialQuotaUserRandomEmail.entryUserData(registrationPartialQuotaUserRandomEmail.getFullQuotaRandomEmail(), randomEmail);
        } else
        {
            log("ОШИБКА! Тест не может быть выполнен, так как не удалось получить письмо на электронную почту");
            logErrors++;
        }
        checkMistakes();
        log("Тест CAND-REG-2.3 завершен");
    }

    //CAND-REG-2.4
    @Test(priority = 4)
    public void testRegistrationQuotaWithFullFilling() throws IOException, InterruptedException, MessagingException
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationFullQuotaUserData = new TestUserData(getUserForRegistrationFullQuotaId());
        TestRandomUserData registrationFullQuotaUserRandomEmail = new TestRandomUserData(getUserForRegistrationFullQuotaId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = false;
        boolean contract = false;

        log("Запущен тест CAND-REG-2.4");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationFullQuotaUserData.getUserLastName(), registrationFullQuotaUserData.getUserFirstName(),
                registrationFullQuotaUserData.getUserMiddleName(), registrationFullQuotaUserData.getSexRu(), registrationFullQuotaUserData.getCountry(),
                registrationFullQuotaUserData.getUserPassword(), contract, partial, getStandUrl(flagForStandUrl)));

        if (testRandomUserData.randomEmailNotNull(randomEmail))
        {
            log("Сохраняем email для последующего входа под этим кандидатом");
            registrationFullQuotaUserRandomEmail.entryUserData(registrationFullQuotaUserRandomEmail.getFullQuotaRandomEmail(), randomEmail);
        } else
        {
            log("ОШИБКА! Тест не может быть выполнен, так как не удалось получить письмо на электронную почту");
            logErrors++;
        }

        checkMistakes();
        log("Тест CAND-REG-2.4 завершен");
    }

    //CAND-REG-2.5
    @Test(priority = 5)
    public void testRegistrationContractWithPartialFilling() throws IOException, InterruptedException, MessagingException
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationPartialContractUserData = new TestUserData(getUserForRegistrationPartialContractId());
        TestRandomUserData registrationPartialContractUserRandomEmail = new TestRandomUserData(getUserForRegistrationPartialContractId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = true;
        boolean contract = true;

        log("Запущен тест CAND-REG-2.5");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationPartialContractUserData.getUserLastName(), registrationPartialContractUserData.getUserFirstName(),null,
                registrationPartialContractUserData.getSexRu(), registrationPartialContractUserData.getCountry(), registrationPartialContractUserData.getUserPassword(), contract, partial, getStandUrl(flagForStandUrl)));

        if (testRandomUserData.randomEmailNotNull(randomEmail))
        {
            log("Сохраняем email для последующего входа под этим кандидатом");
            registrationPartialContractUserRandomEmail.entryUserData(registrationPartialContractUserRandomEmail.getFullQuotaRandomEmail(), randomEmail);
        } else
        {
            log("ОШИБКА! Тест не может быть выполнен, так как не удалось получить письмо на электронную почту");
            logErrors++;
        }
        checkMistakes();
        log("Тест CAND-REG-2.5 завершен");
    }

    //CAND-REG-2.6
    @Test(priority = 6)
    public void testRegistrationContractWithFullFilling() throws IOException, InterruptedException, MessagingException
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationFullContractUserData = new TestUserData(getUserForRegistrationFullContractId());
        TestRandomUserData registrationFullContractUserRandomEmail = new TestRandomUserData(getUserForRegistrationFullContractId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = false;
        boolean contract = true;

        log("Запущен тест CAND-REG-2.6");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationFullContractUserData.getUserLastName(),
                registrationFullContractUserData.getUserFirstName(),registrationFullContractUserData.getUserMiddleName(), registrationFullContractUserData.getSexRu(),
                registrationFullContractUserData.getCountry(), registrationFullContractUserData.getUserPassword(), contract, partial, getStandUrl(flagForStandUrl)));


        if (testRandomUserData.randomEmailNotNull(randomEmail))
        {
            log("Сохраняем email для последующего входа под этим кандидатом");
            registrationFullContractUserRandomEmail.entryUserData(registrationFullContractUserRandomEmail.getFullQuotaRandomEmail(), randomEmail);
        } else
        {
            log("ОШИБКА! Тест не может быть выполнен, так как не удалось получить письмо на электронную почту");
            logErrors++;
        }
        checkMistakes();
        log("Тест CAND-REG-2.6 завершен");
    }
}