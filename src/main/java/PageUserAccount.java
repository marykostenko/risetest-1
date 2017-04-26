import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by user nkorobicina on 11.01.2017.
 * Страница настроек аккаунта пользователя
 */
public class PageUserAccount extends BasePage
{

    public void changePassword(String currentPassword, String newPassword){
        $(By.id("oldPassword")).sendKeys(currentPassword);
        $(By.id("registrationPassword")).sendKeys(newPassword);
        $(By.id("registrationPasswordConfirm")).sendKeys(newPassword);
        $(By.xpath("//form[contains(@action, '/password-change')]/input[@type='submit']")).click();
        $(By.xpath("//div[contains(@class, 'alert-success')]")).waitUntil(Condition.appear, waitTime);
    }

    /**
     * заполняет поле Новый email
     */
    public void fillNewLogin (String userEmail)
    {
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(userEmail);
    }

    /**
     * Нажимает кнопку сохранить для изменения логина
     */
    public void clickSaveLogin() { $(By.xpath("//form[contains(@action,'/request-email-change')]//child::input[@value='Сохранить']")).click(); }

    /**
     * заполняет поле старого пароля
     */
    private void fillOldPassword (String userOldPassword)
    {
        WebElement oldPasswordField = $(By.id("oldPassword"));
        oldPasswordField.sendKeys(userOldPassword);
    }

    /**
     * заполняет поле нового пароля
     */
    private void fillNewPassword (String userNewPassword)
    {
        WebElement newPasswordField = $(By.id("registrationPassword"));
        newPasswordField.sendKeys(userNewPassword);
    }

    /**
     * заполняет поле повторного нового пароля
     */
    private void fillRepeatNewPassword (String userRepeatNewPassword)
    {
        WebElement repeatNewPasswordField = $(By.id("registrationPasswordConfirm"));
        repeatNewPasswordField.sendKeys(userRepeatNewPassword);
    }

    /**
     * заполняет поля старого пароля, нового, подтверждение нового пароля
     */
    public void fillPasswords (String userOldPassword, String userNewPassword, String userRepeatNewPassword)
    {
        fillOldPassword(userOldPassword);
        fillNewPassword(userNewPassword);
        fillRepeatNewPassword(userRepeatNewPassword);
    }

    /**
     * Нажимает кнопку Сохранить для изменения пароля
     */
    public void clickSavePassword() { $(By.xpath("//form[contains(@action,'password-change')]//child::input[@value='Сохранить']")).click(); }
}
