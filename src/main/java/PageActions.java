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
    private void fillNewPassword(String testPassword)
    {
        WebElement newPasswordField = $(By.id("password"));
        newPasswordField.sendKeys(testPassword);

    }

    public void changeUserPassword(String userId, String testPassword)
    {
        $(By.xpath("//a[@href='/admin/changeUserPassword']")).click();
        fillUserId(userId);
        fillNewPassword(testPassword);
        $(By.xpath("//input[@value='Сохранить']")).click();
    }

}
