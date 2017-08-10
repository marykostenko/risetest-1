import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class NewRegistrationTest extends BaseTest
{

    @Test(priority = 1)
    public void testRegistrationQuotaWithPartialFillingNEW() throws IOException, InterruptedException, MessagingException, SQLException {
        PageRegistration pageRegistration = new PageRegistration();

        log("Создаём рандомый email для регитсрации");
        String randomEmail = String.valueOf(pageRegistration.createRandomEmail());

        log("Сохраняем email для последующего входа под этим кандидатом");
        TestRandomUserData registrationQuotaPartialUserRandomEmail = new TestRandomUserData(getUserForRegistrationPartialQuotaId());
        registrationQuotaPartialUserRandomEmail.entryUserData(registrationQuotaPartialUserRandomEmail.getPartialQuotaRandomEmail(), randomEmail);

        log("Заполняем обязательные поля в POST запросе и отправляем данные на регистрацию");
        TestUserData registrationQuotaPartialUserData = new TestUserData(getUserForRegistrationPartialQuotaId());
        TestRequestsForHttp testRequestsForHttp = new TestRequestsForHttp();
        testRequestsForHttp.loginNew(registrationQuotaPartialUserData.getUserLastName(), registrationQuotaPartialUserData.getUserFirstName(),
                registrationQuotaPartialUserData.getSexEn(), registrationQuotaPartialUserData.getCountryId(), randomEmail, registrationQuotaPartialUserData.getUserPassword());

        log("Заходим в базу, берём активационный код");
        TestDatabaseConnection testDatabaseConnection = new TestDatabaseConnection();
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();
        testDatabaseConnection.connectionRiseDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(), testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(),
                testDatabaseConnectingData.getPasswordForDB());

        log("Формируем активационную ссылку и переходим по ней");

        log("Проверяем что открылась главная страница сайта");
        HomePageControl homePageControl = new HomePageControl();
        logErrors = homePageControl.isHomePage(logErrors);

        checkMistakes();

    }
}
