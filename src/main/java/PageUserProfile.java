import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by user nkorobicina on 07.12.2016.
 * профиль пользователя
 */
public class PageUserProfile extends BasePage
{

    /**
     * возвращает true, если есть пункт меню Профиль
     */
    public boolean isProfileMenuAppear()
    {
        ElementsCollection profileMenu = $$(By.linkText("Профиль"));
        if(profileMenu.isEmpty())
            return false;
        else
            return true;
    }

    /**
     * возвращает true, если есть пункт меню Настройки
     */
    public boolean isAccountSettingsMenuAppear()
    {
        ElementsCollection accountSettingsMenu = $$(By.linkText("Настройки"));
        if(accountSettingsMenu.isEmpty())
            return false;
        else
            return true;
    }

    /**
     * возвращает имя пользователя в заголовке карточки
     */
    public String getUserFio()
    {
        return $(By.xpath("//div[contains(@class, 'user-title')]/h1")).text();
    }

    /**
     * получает Фамилия Имя Отчество, возвращает истину, если в заголовке карточки такое же ФИО
     */
    public boolean isUserFioCorrect(String comparedFio)
    {
        if(getUserFio().equals(comparedFio))
            return true;
        else
            return false;
    }

    public int assertUserFIO(String expectedFIO, int logErrors)
    {
        return checkAndLog(!isUserFioCorrect(expectedFIO), logErrors, "Ошибка: ФИО пользователя неверное - " + getUserFio() + "; ожидалось: " + expectedFIO);
    }

    public void goToAccount()
    {
        $(By.xpath("//a[contains(@href, '/account')]")).click();

    }

    /**
     * кнопка сброса попыток входа
     */
    public void resetLoginAttempts()
    {
        $(By.xpath("//a[contains(@href,'/dropUserLoginAttempts/')]")).click();
    }

    /**
     * войти под пользователем (симулирование)
     */
    public void startSimulateUser()
    {
        $(By.xpath("//a[contains(@href,'/simulateUser/')]")).click();
    }

}
