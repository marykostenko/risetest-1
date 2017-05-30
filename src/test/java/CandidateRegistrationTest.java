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
    public void testRegistrationQuotaWithPartialFilling() throws IOException, InterruptedException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
    {

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

        log("Сохраняем email для последующего входа под этим кандидатом");
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        registrationQuotaPartialUserData.entryUserData("userForRegistrationPartialQuotaRandomEmail", randomEmail);

        log("Заполняем обязательные поля");
        pageRegistration.partialFillingRegistrationForm(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(), registrationQuotaPartialUserData.getSex(),
                registrationQuotaPartialUserData.getCountry(), randomEmail, registrationQuotaPartialUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMailForRegistration();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        log("Проверяем письмо на почте, подтверждающее регистрацию");
        TestMail testMail1 = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationConfirmMail = testMail1.getEmailUserRegistration();
        logErrors = testMail1.checkAndLog(!testMail1.isSubjectCorrect(subjectRegistrationConfirmMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail1.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationConfirmMail);
        logErrors = testMail1.checkAndLog(!testMail1.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail1.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        checkMistakes();
    }

    //CAND-REG-2.2
    @Test(priority = 2)
    public void testRegistrationQuotaWithFullFilling() throws IOException, InterruptedException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
    {

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

        log("Сохраняем email для последующего входа под этим кандидатом");
        TestUserData registrationQuotaFullUserData = new TestUserData(getUserForRegistrationFullQuotaId());
        registrationQuotaFullUserData.entryUserData("userForRegistrationFullQuotaRandomEmail", randomEmail);

        log("Заполняем все поля (кроме полей, связанных с агентами)");

        pageRegistration.fullFillingRegistrationForm(registrationQuotaFullUserData.getUserLastName(), registrationQuotaFullUserData.getUserFirstName(),
              registrationQuotaFullUserData.getUserMiddleName(),  registrationQuotaFullUserData.getSex(), registrationQuotaFullUserData.getCountry(),
               randomEmail, registrationQuotaFullUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMailForRegistration();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        log("Проверяем письмо на почте, подтверждающее регистрацию");
        TestMail testMail1 = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationConfirmMail = testMail1.getEmailUserRegistration();
        logErrors = testMail1.checkAndLog(!testMail1.isSubjectCorrect(subjectRegistrationConfirmMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail1.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationConfirmMail);
        logErrors = testMail1.checkAndLog(!testMail1.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail1.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        checkMistakes();
    }

    //CAND-REG-2.3
    @Test(priority = 3)
    public void testRegistrationContractWithPartialFilling() throws IOException, InterruptedException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {

        log("Запущен тест CAND-REG-2.3");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Подать заявку в блоке Обучение по контракту");
        PageMain pageMain = new PageMain();
        pageMain.goToRegistrationFromBlockContractTraining();


        log("Проверяем, что открылась страница с url /registration?contract=true");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationContract(logErrors);

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Сохраняем email для последующего входа под этим кандидатом");
        TestUserData registrationContractPartialUserData = new TestUserData(getUserForRegistrationPartialContractId());
        registrationContractPartialUserData.entryUserData("userForRegistrationPartialContractRandomEmail", randomEmail);

        log("Заполняем обязательные поля");
        pageRegistration.partialFillingRegistrationForm(registrationContractPartialUserData.getUserLastName(), registrationContractPartialUserData.getUserFirstName(), registrationContractPartialUserData.getSex(),
                registrationContractPartialUserData.getCountry(), randomEmail, registrationContractPartialUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMailForRegistration();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        log("Проверяем письмо на почте, подтверждающее регистрацию");
        TestMail testMail1 = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationConfirmMail = testMail1.getEmailUserRegistration();
        logErrors = testMail1.checkAndLog(!testMail1.isSubjectCorrect(subjectRegistrationConfirmMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail1.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationConfirmMail);
        logErrors = testMail1.checkAndLog(!testMail1.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail1.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        checkMistakes();
    }

    //CAND-REG-2.3
    @Test(priority = 4)
    public void testRegistrationContractWithFullFilling() throws IOException, InterruptedException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException
    {

        log("Запущен тест CAND-REG-2.4");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Подать заявку в блоке Обучение по контракту");
        PageMain pageMain = new PageMain();
        pageMain.goToRegistrationFromBlockContractTraining();


        log("Проверяем, что открылась страница с url /registration?contract=true");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationContract(logErrors);

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Сохраняем email для последующего входа под этим кандидатом");
        TestUserData registrationContractFullUserData = new TestUserData(getUserForRegistrationFullContractId());
        registrationContractFullUserData.entryUserData("userForRegistrationFullContractRandomEmail", randomEmail);

        log("Заполняем все поля (кроме полей, связанных с агентами)");
        pageRegistration.fullFillingRegistrationForm(registrationContractFullUserData.getUserLastName(), registrationContractFullUserData.getUserFirstName(),
                registrationContractFullUserData.getUserMiddleName(),  registrationContractFullUserData.getSex(), registrationContractFullUserData.getCountry(), randomEmail,
                registrationContractFullUserData.getUserPassword());

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationMail = testMail.getEmailUserRegistration();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRegistrationMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        log("Находим ссылку из последнего письма в ящике");
        String linkRegistration = testMail.getLinkFromLastMailForRegistration();

        open(linkRegistration);
        log("Проверяем url страницы");
        logErrors = pageRegistration.checkUrlFirstRegistrationPage(logErrors);

        log("Проверяем письмо на почте, подтверждающее регистрацию");
        TestMail testMail1 = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
        String subjectRegistrationConfirmMail = testMail1.getEmailUserRegistration();
        logErrors = testMail1.checkAndLog(!testMail1.isSubjectCorrect(subjectRegistrationConfirmMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail1.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationConfirmMail);
        logErrors = testMail1.checkAndLog(!testMail1.isAddresseeCorrect(randomEmail), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail1.getAddresseeLastMail() + ". Ожидался: " + randomEmail);

        checkMistakes();
    }
}
