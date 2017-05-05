import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 05.05.2017.
 */
public class PageActions extends BasePage
{

    /**
     * заполняет айди пользователя при смене пароля админитсратором
     */
    private void fillUserId(String userId)
    {
        WebElement userIdField = $(By.id("userId"));
        userIdField.sendKeys(userId);
    }

    /**
     * заполняет тестовый пароль при смене пароля администратором
     */
    public void fillNewPassword(String testPassword)
    {
        WebElement newPasswordField = $(By.id("password"));
        newPasswordField.sendKeys(testPassword);

    }

    public void changeUserPassword(String userId, String testPassword)
    {
        log("Переход из системных действий к смене пароля пользователю");
        $(By.xpath("//a[@href='/admin/changeUserPassword']")).click();
        log("Заполняем айди пользователя и пароль");
        fillUserId(userId);
        fillNewPassword(testPassword);
        log("Нажимаем кнопку сохранить");
        $(By.xpath("//input[@value='Сохранить']"));
    }

}
