import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by user nkorobicina on 28.12.2016.
 */
//Страницы восстановления пароля: которая по ссылке забыли пароль и по ссылке из письма
public class PagePasswordRecovery extends BasePage {

    //проверка, что страница - та, на которую переход по ссылке забыли пароль
    private boolean isFirstRecoveryPage(){
        return url().contains("/password/recovery");
    }

    //проверка, что урл страницы содержит password/recovery. Если нет, то увеличивается число найденных ошибок и пишется в лог
    public int checkUrlFirstRecoveryPage(int logErrors){
        return checkAndLog(!isFirstRecoveryPage(), logErrors, "Ошибка: url неверный -" + url());
    }

    //проверка, что страница - та, на которкую ведет ссылка из письма
    private boolean isLinkRecoveryPage() {
        return url().contains("/passwordRecovery");
    }

    public int checkUrlLinkRecoveryPage(int logErrors){
        return checkAndLog(!isLinkRecoveryPage(), logErrors, "Ошибка: url неверный - " + url());
    }

    public void clickCancelButton() throws InterruptedException {
        $(By.xpath(".//form[@action='/password/recovery']//a[@href='/']")).click();
    }

    private WebElement emailRecovery = $(By.id("email"));
    private WebElement textMessageOnPage = $(By.xpath("//div[@class = 'comment-style']"));


    //заполнить поле Email
    public void fillEmail(String email){
        emailRecovery.clear();
        emailRecovery.sendKeys(email);
        textMessageOnPage.click();
    }

    //нажать кнопку Отправить
    public void clickSendEmail() throws InterruptedException {
        $(By.xpath("//input[@class = 'btn btn-primary']")).click();
        $(By.id("email")).waitUntil(Condition.disappear, waitTime);
    }

    private String getAlertMailSending(){
        return textMessageOnPage.getText();
    }

    private String alertMailSending = "На указанный email было отправлено письмо. Перейдите по ссылке, указанной в письме.";

    //Проверка сообщения о том, что письмо для восстановления было отправлено
    public int checkTextAlertMailSending(int logErrors){
        boolean textComparing = getAlertMailSending().equals(alertMailSending);
        return checkAndLog(!textComparing, logErrors, "Ошибка: отображается текст " + getAlertMailSending() + ", а ожидался текст: " + alertMailSending);
    }

    //поле с новым паролем
    private WebElement registrationPassword = $(By.xpath("//input[@id = 'registrationPassword']"));

    //ввод нового пароля
    public void fillNewPassword(String newPassword) {
        registrationPassword.clear();
        registrationPassword.sendKeys(newPassword);
        registrationPassword.click();
        sleep(3000);
    }

    //поле с подтверждением нового пароля
    private WebElement registrationPasswordConfirm = $(By.xpath("//input[@id = 'registrationPasswordConfirm']"));

    //подтверждение нового пароля
    public void fillPasswordConfirmation(String password){
        registrationPasswordConfirm.clear();
        registrationPasswordConfirm.sendKeys(password);
        registrationPasswordConfirm.click();
        sleep(3000);
    }

    //нажимаем сохранить
    public void clickSaveNewPassword(){
        $(By.xpath("//input[@value='Сохранить']")).click();
    }

    //ищем текст Пароль был успешно изменен
    private String messageSuccessful = "Пароль был успешно изменен";

    //проверка сообщения, что пароль был успешно изменен
    public int checkMessageSuccessfulAppear(int logErrors){
        String messageAppeared = $(By.xpath("//div[@class='hero']//p")).text();
        return checkAndLog(!messageAppeared.contains(messageSuccessful), logErrors, "Ошибка: сообщение неправильное: " + messageAppeared + ". ожидалось: " + messageSuccessful);
    }

    //красный алерт-неудачный пароль
    private ElementsCollection alertError = $$(By.xpath("//div[@class = 'alert alert-error']"));

    //проверка, что сообщение про неверный формат почты появилось
    public int checkMessageIncorrectEmailAppear(int logErrors){
        String expectedMessage = "Неверный формат электронной почты";
        String messageAppeared = alertError.get(0).text();
        return checkAndLog(!messageAppeared.contains(expectedMessage), logErrors, "Ошибка: сообщение неправильное: " + messageAppeared + ". ожидалось: " + expectedMessage);
    }

    private String styleField;

    //проверка, что поле для email красное
    public int checkEmailFieldRed(int logErrors){
        styleField = emailRecovery.getAttribute("style");
        return checkAndLog(!styleField.contains(redFieldStyle), logErrors, "Ошибка: поле не красное");
    }

    //проверка, что поле для email зеленое
    public int checkEmailFieldGreen(int logErrors){
        styleField = emailRecovery.getAttribute("style");
        return checkAndLog(!styleField.contains(greenFieldStyle), logErrors, "Ошибка: поле не зеленое");
    }

    //проверка сообщения, что пароль короткий
    public int checkMessageShortPasswordAppeared(int logErrors){
        String expectedMessage = "Длина пароля должна быть не менее 8 символов";
        String messageAppeared = alertError.get(0).text();
        return checkAndLog(!messageAppeared.contains(expectedMessage), logErrors, "Ошибка: сообщение неправильное: " + messageAppeared + ". ожидалось: " + expectedMessage);
   }

   //проверка, что поле с новым паролем красное
   public int checkNewPasswordRed(int logErrors){
       styleField = registrationPassword.getAttribute("style");
       return checkAndLog(!styleField.contains(redFieldStyle), logErrors, "Ошибка: поле не красное");
   }

   //проверка, что поле с новым паролем желтое
    public int checkNewPasswordYellow(int logErrors){
        styleField = registrationPassword.getAttribute("style");
        return checkAndLog(!styleField.contains(yellowFieldStyle), logErrors, "Ошибка: поле не желтое");
    }

    //проверка, что поле с новым паролем зеленое
    public int checkNewPasswordGreen(int logErrors){
        styleField = registrationPassword.getAttribute("style");
        return checkAndLog(!styleField.contains(greenFieldStyle), logErrors, "Ошибка: поле не зеленое");
    }

    //проверка, что поле с новым паролем ярко-зеленое
    public int checkNewPasswordVeryGreen(int logErrors){
        styleField = registrationPassword.getAttribute("style");
        return checkAndLog(!styleField.contains(veryGreenFieldStyle), logErrors, "Ошибка: поле не ярко-зеленое");
    }

    //проверка, что поле с подтверждением пароля красное
    public int checkNewPasswordConfirmRed(int logErrors){
        styleField = registrationPasswordConfirm.getAttribute("style");
        return checkAndLog(!styleField.contains(redFieldStyle), logErrors, "Ошибка: поле не красное");
    }

    //проверка, что поле с подтвеждением пароля ярко-зеленое
    public int checkNewPasswordConfirmVeryGreen(int logErrors){
        styleField = registrationPasswordConfirm.getAttribute("style");
        return checkAndLog(!styleField.contains(veryGreenFieldStyle), logErrors, "Ошибка: поле не ярко-зеленое");
    }

    private String lowerCaseCharacters = "строчные буквы";
    private String upperCaseCharacters = "прописные буквы";
    private String nonAlfabeticSymbols = "неалфавитные символы (например, !, $, #, %)";

    //сравнивалка сообщений
    private boolean compareMessages(String[] expectedMessage, String messageAppeared){
        int i = 0;
        boolean res = true;
        while ((res) & (i < expectedMessage.length)){
            res = messageAppeared.contains(expectedMessage[i]);
            i++;
        }
        return res;
    }

    //проверка сообщения о слабом пароле
    public int checkMessageWeakPasswordAppeared(int logErrors){
        String expectedMessage[] = {"Слабый пароль.",  "Такие пароли запрещены к использованию.", "Чтобы усилить его попробуйте добавить:", lowerCaseCharacters, upperCaseCharacters, nonAlfabeticSymbols};
        String messageAppeared = alertError.get(0).text();
        boolean comparing = compareMessages(expectedMessage, messageAppeared);
        return checkAndLog(!comparing, logErrors, "Ошибка: сообщение неправильное: " + messageAppeared);
    }

    //голубой алерт
    ElementsCollection alertUndefined = $$(By.xpath("//div[@class = 'alert undefined']"));

    //проверка сообщения о среднем пароле
    public int checkMessageMiddlePasswordAppeared(int logErrors){
        String expectedMessage[] = {"Средний пароль.", "Такие пароли возможны, но мы рекомендуем усилить его, добавив:", upperCaseCharacters, nonAlfabeticSymbols};
        String messageAppeared = alertUndefined.get(0).text();
        boolean comparing = compareMessages(expectedMessage, messageAppeared);
        return checkAndLog(!comparing, logErrors, "Ошибка: сообщение неправильное: " + messageAppeared);
    }

    //зеленый алерт
    ElementsCollection alertSuccess = $$(By.xpath("//div[@class = 'alert alert-success']"));

    //проверка сообщения о сильном пароле
    public int checkMessageStrongPasswordAppeared(int logErrors){
        String expectedMessage[] = {"Сильный пароль.", "Возможно вы захотите усилить его, добавив:", nonAlfabeticSymbols};
        String messageAppeared = alertSuccess.get(0).text();
        boolean comparing = compareMessages(expectedMessage, messageAppeared);
        return checkAndLog(!comparing, logErrors, "Ошибка: сообщение неправильное: " + messageAppeared);
    }

    //проверка сообщения об очень сильном пароле
    public int checkMessageVeryStrongPasswordAppeared(int logErrors){
        String expectedMessage[] = {"Очень сильный пароль.", "Спасибо, что заботитесь о своей безопасности"};
        String messageAppeared = alertSuccess.get(0).text();
        boolean comparing = compareMessages(expectedMessage, messageAppeared);
        return checkAndLog(!comparing, logErrors, "Ошибка: сообщение неправильное: " + messageAppeared);
    }

}
