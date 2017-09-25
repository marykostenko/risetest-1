import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 24.05.2017.
 */
public class CandidateRegistrationTest extends BaseTest
{

//Самостоятельная регистрация кандидата

    //CAND-REG-2.1
    @Test(priority = 1)
    public void testRegistrationQuotaWithPartialFilling() throws IOException, InterruptedException, MessagingException
    {

        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        TestRandomUserData registrationPartialQuotaUserRandomEmail = new TestRandomUserData(getUserForRegistrationPartialQuotaId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = true;
        boolean contract = false;

        log("Запущен тест CAND-REG-2.1");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(),null,
                registrationQuotaPartialUserData.getSexEn(), registrationQuotaPartialUserData.getCountry(), registrationQuotaPartialUserData.getUserPassword(), contract, partial));

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
    }

    //CAND-REG-2.2
    @Test(priority = 2)
    public void testRegistrationQuotaWithFullFilling() throws IOException, InterruptedException, MessagingException
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationFullQuotaUserData = new TestUserData(getUserForRegistrationFullQuotaId());
        TestRandomUserData registrationFullQuotaUserRandomEmail = new TestRandomUserData(getUserForRegistrationFullQuotaId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = false;
        boolean contract = false;

        log("Запущен тест CAND-REG-2.2");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationFullQuotaUserData.getUserLastName(), registrationFullQuotaUserData.getUserFirstName(),null,
                registrationFullQuotaUserData.getSexEn(), registrationFullQuotaUserData.getCountry(), registrationFullQuotaUserData.getUserPassword(), contract, partial));

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
    }

    //CAND-REG-2.3
    @Test(priority = 3)
    public void testRegistrationContractWithPartialFilling() throws IOException, InterruptedException, MessagingException
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationPartialContractUserData = new TestUserData(getUserForRegistrationPartialContractId());
        TestRandomUserData registrationPartialContractUserRandomEmail = new TestRandomUserData(getUserForRegistrationPartialContractId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = true;
        boolean contract = true;

        log("Запущен тест CAND-REG-2.3");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationPartialContractUserData.getUserLastName(), registrationPartialContractUserData.getUserFirstName(),null,
                registrationPartialContractUserData.getSexEn(), registrationPartialContractUserData.getCountry(), registrationPartialContractUserData.getUserPassword(), contract, partial));

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
    }

    //CAND-REG-2.4
    @Test(priority = 4)
    public void testRegistrationContractWithFullFilling() throws IOException, InterruptedException, MessagingException
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        TestUserData registrationFullContractUserData = new TestUserData(getUserForRegistrationFullContractId());
        TestRandomUserData registrationFullContractUserRandomEmail = new TestRandomUserData(getUserForRegistrationFullContractId());
        TestMail testMail = new TestMail();
        TestRandomUserData testRandomUserData = new TestRandomUserData();

        boolean partial = false;
        boolean contract = true;

        log("Запущен тест CAND-REG-2.4");

        log("Переходим на главную страницу");
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        String randomEmail = (testMail.independentRegistrationCandidate(registrationFullContractUserData.getUserLastName(), registrationFullContractUserData.getUserFirstName(),null,
                registrationFullContractUserData.getSexEn(), registrationFullContractUserData.getCountry(), registrationFullContractUserData.getUserPassword(), contract, partial));


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
    }
}