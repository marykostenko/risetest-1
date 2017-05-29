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
    public void testRegistrationQuotaWithPartialFilling() throws IOException, InterruptedException, MessagingException {

        log("Запущен тест CAND-REG-2.1");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Регистрация");
        pageTopBottom.goToRegistration();


        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Сохраняем email для последующего входа под данным кандидатом");
        pageRegistration.saveContractMail1(randomEmail);

        log("Заполняем обязательные поля");
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        pageRegistration.partialFillingRegistrationForm(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(), registrationQuotaPartialUserData.getSex(),
                registrationQuotaPartialUserData.getCountry(), randomEmail, registrationQuotaPartialUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о восстановлении правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMail();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        checkMistakes();
    }

    //CAND-REG-2.2
    @Test(priority = 2)
    public void testRegistrationQuotaWithFullFilling() throws IOException, InterruptedException, MessagingException {

        log("Запущен тест CAND-REG-2.2");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Регистрация");
        pageTopBottom.goToRegistration();


        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Заполняем все поля (кроме полей, связанных с агентами)");
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        pageRegistration.fullFillingRegistrationForm(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(),
              registrationQuotaPartialUserData.getUserMiddleName(),  registrationQuotaPartialUserData.getSex(), registrationQuotaPartialUserData.getCountry(),
               randomEmail, registrationQuotaPartialUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о восстановлении правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMail();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        checkMistakes();
    }

    //CAND-REG-2.3
    @Test(priority = 3)
    public void testRegistrationContractWithPartialFilling() throws IOException, InterruptedException, MessagingException {

        log("Запущен тест CAND-REG-2.3");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Подать заявку в блоке Обучение по контракту");
        pageTopBottom.goToRegistration();


        log("Проверяем, что открылась страница с url /registration?contract=true");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationContract(logErrors);

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Заполняем обязательные поля");
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        pageRegistration.partialFillingRegistrationForm(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(), registrationQuotaPartialUserData.getSex(),
                registrationQuotaPartialUserData.getCountry(), randomEmail, registrationQuotaPartialUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о восстановлении правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMail();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        checkMistakes();
    }

    //CAND-REG-2.3
    @Test(priority = 4)
    public void testRegistrationContractWithFullFilling() throws IOException, InterruptedException, MessagingException {

        log("Запущен тест CAND-REG-2.4");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Регистрация");
        pageTopBottom.goToRegistration();


        log("Проверяем, что открылась страница с url /registration?contract=true");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationContract(logErrors);

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Заполняем все поля (кроме полей, связанных с агентами)");
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        pageRegistration.fullFillingRegistrationForm(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(),
                registrationQuotaPartialUserData.getUserMiddleName(),  registrationQuotaPartialUserData.getSex(), registrationQuotaPartialUserData.getCountry(), randomEmail,
                registrationQuotaPartialUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о восстановлении правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMail();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        checkMistakes();
    }
}
