import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by user nkorobicina on 07.12.2016.
 */
public class PageLogin extends BasePage{

    //возвращает true, если адрес страницы содержит /login
    private boolean isLoginPage(){
        String pageUrl = url();
        return pageUrl.contains("/login");
    }


    //проверка, что на странице есть форма логина. Если нет, тест упадет
    public void isLoginForm(){

        $(By.xpath("//form[contains(@action, 'login')]")).shouldBe(Condition.appear);
    }

    //заполняет поле логин
    private void fillLogin(String userLogin){
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(userLogin);
    }

    //заполняет поле пароль
    private void fillPassword(String userPassword){
        WebElement passwordField = $(By.id("password"));
        passwordField.sendKeys(userPassword);
    }

    //заполняет поля логин и пароль
    public void fillLoginForm(String userLogin, String userPassword){
        fillLogin(userLogin);
        fillPassword(userPassword);
    }

    //нажимает кнопку Войти под формой логина
    public void pushLoginButton(){
        $(By.id("loginbutton")).click();
    }

    //проверка, что адрес страницы содержит /login, и запись в лог в противном случае
    public int assertLoginUrl(int logErrors){
        return checkAndLog(!isLoginPage(), logErrors, "Ошибка: url неверный ");
    }

    //проверка алерта при неудачной авторизации
    private ElementsCollection loginFail = $$(By.xpath("//*[contains(text(),'Неверный Email или пароль')]"));

    public int alertLoginFail(int logErrors){

        logErrors = checkAndLog(loginFail.isEmpty(), logErrors, "Ошибка: нет алерта \"Неверный Email или пароль\"");
        return logErrors;

        }

    //проверка алерта при превышении количетва неверных попыток входа
    private ElementsCollection excessLoginUnsuccessfulAttempts = $$(By.xpath("//*[contains(text(),'Вы превысили допустимое число попыток входа. Попробуйте повторить попытку через 1 час')]"));
    public int alertExcessLoginUnsuccessfulAttempts(int logErrors){

        logErrors = checkAndLog(excessLoginUnsuccessfulAttempts.isEmpty(), logErrors, "Ошибка: нет алерта о превышении количества попыток входа");
        return logErrors;

         }

    //проверка появления капчи
    private ElementsCollection Capcha = $$(By.xpath("//div[@id='captchadiv']"));

    public int isCapcha(int logErrors){

        logErrors = checkAndLog(Capcha.isEmpty(), logErrors, "Ошибка: нет капчи", "Капча есть");
        return logErrors;

    }

}


