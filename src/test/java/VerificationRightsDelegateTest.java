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
        TestUserData testAdminData = new TestUserData(getAdminId());
        TestUserData testUserData = new TestUserData();
        TestUserData testRepresentativeData = new TestUserData(getRepresentativeId());
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        MenuContent menuContent = new MenuContent();
        PageCandidateCard pageCandidateCard = new PageCandidateCard();

        TestCandidatesData inputCandidate = new TestCandidatesData(getInputId());
        TestCandidatesData onCheckCandidate = new TestCandidatesData(getOnCheckId());
        TestCandidatesData inroducedCandidate = new TestCandidatesData(getInroducedId());
        TestCandidatesData invitedForTestsCandidate = new TestCandidatesData(getInvitedForTestsId());
        TestCandidatesData testsDoneCandidate = new TestCandidatesData(getTestsDoneId());
        TestCandidatesData selectedForQuotaCandidate = new TestCandidatesData(getSelectedForQuotaId());
        TestCandidatesData secondDistributionLevelCandidate = new TestCandidatesData(getSecondDistributionLevelId());
        TestCandidatesData dossierFormedCandidate = new TestCandidatesData(getDossierFormedId());
        TestCandidatesData distributedQuotaCandidate = new TestCandidatesData(getDistributedQuotaId());
        TestCandidatesData directedQuotaCandidate = new TestCandidatesData(getDirectedQuotaId());
        TestCandidatesData enrolledQuotaCandidate = new TestCandidatesData(getEnrolledQuotaId());
        TestCandidatesData completedQtCandidate = new TestCandidatesData(getСompletedQtId());
        TestCandidatesData enrolledSubfacultyQCandidate = new TestCandidatesData(getEnrolledSubfacultyQId());
        TestCandidatesData distributedSubfacultyQCandidate = new TestCandidatesData(getDistributedSubfacultyQId());
        TestCandidatesData expelledQuotaCandidate = new TestCandidatesData(getExpelledQuotaId());
        TestCandidatesData notPassedForQuotaCandidate = new TestCandidatesData(getNotPassedForQuotaId());

        log("Идёт подготовка тестовых данных для прохождения теста провекрки прав представителя...");
        log("Для выполнения данного теста добавляю тестовых кандидатов в систему:");
        testCandidatesData.createCandidateInAllStatues((getStandUrl("Ext")));

        log("В данном тесте будут использованы данные реальных пользователей. Сменим пароль пользователя на тестовый");
        testUserData.changePassForTest(testAdminData.getUserLogin(), testAdminData.getUserPassword(), testRepresentativeData.getId(), testRepresentativeData.getUserPassword());

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

        log("Проверим, что можем открыть карточки всех квотных кандидатов и не увидим в них информации об агентах");

        logErrors = pageCandidateCard.checkQuotaCandidateCard(inputCandidate.getCandidateRegNumber(), inputCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(onCheckCandidate.getCandidateRegNumber(), onCheckCandidate.getCandidateStateName(), logErrors);
        logErrors = pageCandidateCard.checkQuotaCandidateCard(inroducedCandidate.getCandidateRegNumber(), inroducedCandidate.getCandidateStateName(), logErrors);

        checkMistakes();
    }
}
