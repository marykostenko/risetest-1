import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


/**
 * Created by Maria on 24.03.2017.
 */
public class AccountInformation extends BasePage
{

    /**-----------------------------Проверка личной информации пользователя в профиле

    /**
     * возвращает фамилию из профиля пользователя
     * */
    public String getLastNameUser () { return $(By.xpath("//div[contains(text(),'Фамилия')]//following::div[1]")).text(); }

    /**
     * возвращает имя из профиля пользователя
     */
    public String getFirstNameUser() { return $(By.xpath("//div[contains(text(),'Имя')]//following::div[1]")).text(); }

    /**
     * возвращает отчетсво из профиля пользователя
     **/
    public String getMiddleNameUser() { return $(By.xpath("//div[contains(text(),'Отчество')]//following::div[1]")).text(); }

    /**
     * возвращает фамилию Eng
     */
    public String getLastNameUserEng() { return $(By.xpath("//div[contains(text(),'Фамилия (Eng)')]//following::div[1]")).text(); }

    /**
     * возвращает имя Eng
     */
    public String getFirstNameUserEng() { return $(By.xpath("//div[contains(text(),'Имя (Eng)')]//following::div[1]")).text(); }


    /**
     * сверяет фамилию в профиле с ожидаемой
     */
    public int checkLastName (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getLastNameUser().equals(expectedFIO), logErrors,
                "Ошибка: Фамилия в профиле не верная - " + getLastNameUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет имя в профиле с ожидаемым
     */
    public int checkFirstName (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getFirstNameUser().equals(expectedFIO), logErrors,
                "Ошибка: Имя в профле не верное - " + getFirstNameUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет отчество в профиле с ожидаемым
     */
    public int checkMiddleName (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getMiddleNameUser().equals(expectedFIO), logErrors,
                "Ошибка: Отчество в профиле не верное - " + getMiddleNameUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет фамилию Eng в профиле с ожидаемой
     */
    public int checkLastNameEng (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getLastNameUserEng().equals(expectedFIO), logErrors,
                "Ошибка: Фамилия Eng в профиле не верная - " + getLastNameUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет имя Eng в профиле с ожидаемым
     */
    public int checkFirstNameEng (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getFirstNameUserEng().equals(expectedFIO), logErrors,
                "Ошибка: Имя Eng в профле не верное - " + getFirstNameUser() + "; ожидалось - " + expectedFIO);
    }


    /**-----------------------------Проверка контактной информацими в профиле пользователя

     /**
     * возвращает мобильный телефон из профиля пользователя
     **/
    public String getPhoneUser() { return $(By.xpath("//div[contains(text(),'Мобильный телефон: ')]//following::div[1]")).text(); }

    /**
     * возвращает Email из профиля пользователя
     **/
    public String getEmailUser() { return $(By.xpath("//div[contains(text(),'E-mail: ')]//following::div[1]")).text(); }

    /**
     * сверяет мобильный телефон в профиле с ожидаемым
     */
    public int checkPhoneUser (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getPhoneUser().equals(expectedFIO), logErrors,
                "Ошибка: Имя Eng в профле не верное - " + getPhoneUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет email в профиле с ожидаемым
     */
    public int checkEmailUser (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getEmailUser().equals(expectedFIO), logErrors,
                "Ошибка: Имя Eng в профле не верное - " + getEmailUser() + "; ожидалось - " + expectedFIO);
    }


    /**-----------------------------Проверка должности и рабочего телефона

    /**
     * возвращает должность из профиля пользователя
     **/
    public String getPostUser() { return $(By.xpath("//div[contains(text(),'Должность: ')]//following::div[1]")).text(); }

    /**
     * возвращает рабочий телефон из профиля пользователя
     **/
    public String getWorkPhoneUser() { return $(By.xpath("//div[contains(text(),'Рабочий телефон: ')]//following::div[1]")).text(); }

    /**
     * сверяет должность профиле с ожидаемой
     */
    public int checkPostUser (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getPostUser().equals(expectedFIO), logErrors,
                "Ошибка: Имя Eng в профле не верное - " + getPostUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет рабочий телефон в профиле с ожидаемым
     */
    public int checkWorkPhone (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getWorkPhoneUser().equals(expectedFIO), logErrors,
                "Ошибка: Имя Eng в профле не верное - " + getWorkPhoneUser() + "; ожидалось - " + expectedFIO);
    }
    /**-----------------------------Открытие страниц редактирования

     /**
     * открывает редактирование личной информации
     */
    public void goToEditPersonalInfo(){ $(By.xpath("//a[contains(@href,'/editPersonalInfo')]")).click(); }

    /**
     * открывает редактирование контактной информации
     */
    public void goToEditContactInfo () { $(By.xpath("//a[contains(@href,'/editContactInfo')]")).click(); }

    /**
     * открывает редактирование места работы
     */
    public void goToEditJob () { $(By.xpath("//a[contains(@href,'/editJob')]")).click(); }


    /**-----------------------------Проверка и добавление роли пользователю
     *
     */
    private ElementsCollection adminRole = $$(By.xpath("//div[contains(text(),'Администратор')]"));
    /**
     * проверка нет ли роли админа, если есть - удаляем
     */
    public void checkAndDeleteAdminRole ()
    {
        if(!adminRole.isEmpty())
        {
            log("У пользователя найдена роль администратора. Удалим её");
            deleteRoleAdmin();
            log("Ролль админисатратора удалена. продолжаем выполнение теста");
        } else log("Роль администратора у пользователя не найдена. Продолжаем выполнение теста");
    }

    private ElementsCollection buttonAddRole = $$(By.xpath("//a[@href='#add_modal_']"));
    /**
     * добавление и проверка роли от лица администратора
     */
    public int addAndCheckRoleFromAdmin (int logErrors)
    {
        if (!buttonAddRole.isEmpty())
        {
            $(By.xpath("//a[@href='#add_modal_']")).click();
            $(By.xpath("//span[@data-dropdown='dropdown']")).click();
            log("Выбираем роль Администратор");
            $(By.xpath("//a[@data-id='Admin']")).click();
            $(By.xpath("//button[@class='add-role btn btn-primary']")).click();
            log("Входим от лица пользователя");

        } else
        {
            logErrors++;
            log("Нет кнопки добавления роли");
        }
        return logErrors;
    }

    /**
     * удаляем роль
     */
    public void deleteRoleAdmin ()
    {
        $(By.xpath("//td//div[contains(text(),'Администратор')]//following::td[2]//child::a")).click();
        $(By.xpath("//td//div[contains(text(),'Администратор')]//following::td[2]//child::a//following::div[1]//child::button[contains(text(),'Да')]")).click();
    }
}
