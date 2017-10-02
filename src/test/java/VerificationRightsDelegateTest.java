import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class VerificationRightsDelegateTest extends BaseTest
{
    //USER-Rights-RS-1

    @Test (priority = 1)
    public void testVerificationQuotaCandidates() throws IOException, SQLException {

        TestCandidatesData testCandidatesData = new TestCandidatesData();
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();

        log("Для выполнения данного теста добавляю тестовых кандидатов в систему:");

        testCandidatesData.createCandidateInAllStatues((getStandUrl("Ext")));

        log("Запущен тест USER-Rights-1.1");

        log("Открываем главную страницу");
        open(getStandUrl("Ext"));

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку \"Вход\"");
        pageTopBottom.goToLogin();

        log("Проверяем, что открылась страница с url /login");
        log("Url страницы: " + url());
        logErrors = pageLogin.assertLoginUrl(logErrors);

        log("Проверяем, что есть форма логина");
        pageLogin.isLoginForm();

        log("Заполняем форму логина");
        pageLogin.fillLoginForm(testRepresentativeData.getUserLogin(), testRepresentativeData.getUserPassword());

        log("Нажимаем кнопку Войти");
        pageLogin.pushLoginButton();

        log("Проверяем, выполнен ли вход");
        logErrors = pageTopBottom.assertLoggingIn(logErrors);



    }
}
