import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by user nkorobicina on 16.01.2017.
 */
public class PasswordRecoveryMessagesTest extends BaseTest {

    @Test(priority =  1)
    public void testMessagesOnFormNewPassword() throws IOException, InterruptedException, MessagingException {
        log("Запущен тест USER-ACC-1.4");

        log("Переходим на форму логина");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToLogin();

        log("Нажимаем на ссылку 'Забыли пароль?'");
        PageLogin pageLogin = new PageLogin();
        pageLogin.goToRecoveryPage();
        TestUserData testUserData = new TestUserData(getMailRecoveryId());

        log("Заполняем поле Email");
        PagePasswordRecovery pagePasswordRecovery = new PagePasswordRecovery();
        pagePasswordRecovery.fillEmail(testUserData.getUserLogin());

        log("Нажимаем кнопку Отправить");
        pagePasswordRecovery.clickSendEmail();

        TestMail testMail = new TestMail();
        log("Проверяем, что последнее письмо в ящике - письмо о восстановлении правильному адресату");
        String subjectRecoveryMail = testMail.getPasswordRecoveryMailHead();
        logErrors = testMail.checkAndLog(!testMail.isSubjectCorrect(subjectRecoveryMail), logErrors,
                "Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRecoveryMail);
        logErrors = testMail.checkAndLog(!testMail.isAddresseeCorrect(testUserData.getUserLogin()), logErrors,
                "Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + testUserData.getUserLogin());

        log("Находим ссылку из последнего письма в ящике");
        String linkRecovery = testMail.getLinkFromLastMail();

        log("Переходим по ссылке");
        open(linkRecovery);

        log("Вводим короткий пароль");
        TestPasswordData testPasswordData = new TestPasswordData();
        pagePasswordRecovery.fillNewPassword(testPasswordData.getShortPassword());

        log("Проверяем, что есть красный алерт с текстом про короткий пароль");
        logErrors = pagePasswordRecovery.checkMessageShortPasswordAppeared(logErrors);

        log("Проверяем, что поле с новым паролем становится красным");
        logErrors = pagePasswordRecovery.checkNewPasswordRed(logErrors);

        log("Вводим слабый пароль");
        pagePasswordRecovery.fillNewPassword(testPasswordData.getWeakPassword());

        log("Проверяем, что есть красный алерт с текстом про слабый пароль");
        logErrors = pagePasswordRecovery.checkMessageWeakPasswordAppeared(logErrors);

        log("Проверяем, что поле с новым паролем осталось красным");
        logErrors = pagePasswordRecovery.checkNewPasswordRed(logErrors);

        log("Вводим средний пароль");
        pagePasswordRecovery.fillNewPassword(testPasswordData.getMiddlePassword());

        log("Проверяем, что появляется алерт с текстом про средний пароль");
        logErrors = pagePasswordRecovery.checkMessageMiddlePasswordAppeared(logErrors);

        log("Проверяем, что поле стало желтым");
        logErrors = pagePasswordRecovery.checkNewPasswordYellow(logErrors);

        log("Вводим сильный пароль");
        pagePasswordRecovery.fillNewPassword(testPasswordData.getStrongPassword());

        log("Проверяем, что появляется алерт с текстом про сильный пароль");
        logErrors = pagePasswordRecovery.checkMessageStrongPasswordAppeared(logErrors);

        log("Проверяем, что поле стало зеленым");
        logErrors = pagePasswordRecovery.checkNewPasswordGreen(logErrors);

        log("Вводим очень сильный пароль");
        pagePasswordRecovery.fillNewPassword(testPasswordData.getVeryStrongPassword());

        log("Проверяем, что появляется алерт с текстом про очень сильный пароль");
        logErrors = pagePasswordRecovery.checkMessageVeryStrongPasswordAppeared(logErrors);

        log("Проверяем, что поле стало ярко-зеленым");
        logErrors = pagePasswordRecovery.checkNewPasswordVeryGreen(logErrors);

        log("Вводим несовпадающее подтверждение с паролем");
        pagePasswordRecovery.fillPasswordConfirmation(testPasswordData.getShortPassword());

        log("Проверяем, что поле с подтверждением пароля становится красным");
        logErrors = pagePasswordRecovery.checkNewPasswordConfirmRed(logErrors);

        log("Вводим правильное подтверждение пароля");
        pagePasswordRecovery.fillPasswordConfirmation(testPasswordData.getVeryStrongPassword());

        log("Проверяем, что поле с подтверждением пароля становится ярко-зеленым");
        logErrors = pagePasswordRecovery.checkNewPasswordConfirmVeryGreen(logErrors);

        checkMistakes();
        log("Тест USER-ACC-1.4 завершен");
    }
}
