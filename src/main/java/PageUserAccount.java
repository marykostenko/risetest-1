import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

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
}
