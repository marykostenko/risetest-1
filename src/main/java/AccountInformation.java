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

    private ElementsCollection checkFirstName = $$(By.linkText("Данных"));
    private ElementsCollection checkLastName = $$(By.linkText("Изменение"));
    private ElementsCollection checkMiddleName = $$(By.linkText("Личных"));


    public int checkPersonalInfo (int logErrors)
    {
        logErrors = checkAndLog(checkFirstName.isEmpty(), logErrors, "ОШИБКА: имя неверное", "Имя верное");
        logErrors = checkAndLog(checkLastName.isEmpty(), logErrors, "ОШИБКА: фимилияне верная", "Фамилия верная");
        logErrors = checkAndLog(checkMiddleName.isEmpty(), logErrors, "ОШИБКА: отчество неверное", "Отчество верное");

        return logErrors;
    }

    private ElementsCollection buttonEditJob = $$(By.xpath("//a[contains(@href,'/editJob')]"));
    private ElementsCollection buttonEditPersonalInfo = $$(By.xpath("//a[contains(@href,'/editPersonalInfo')]"));
    private ElementsCollection buttonEditContactInfo = $$(By.xpath("//a[contains(@href,'/editContactInfo')]"));
    private ElementsCollection buttonAddRole = $$(By.xpath("//a[@href='#add_modal_']"));


    /**
     * переход на страницу с пользователями
     */
    public void goToUsers()
    {

        $(By.xpath("//div[@class='clearfix']//child::a[@class='dropdown-toggle padding-right-0']")).click();
        $(By.xpath("//a[@href='/users']")).click();

    }

    /**
     *  заполняем email для поиска пользователя
     */
    public void fillEmail(String userEmail)
    {
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(userEmail);
    }

    /**
     * нажимает кнопку "Найти"
     */
    public void pushSearchButton()
    {
        $(By.id("submit_search")).click();
    }


    /**
     * переходит на страницу пользователя Изменение Данных
     */
    public void goToUserForEdit()
    {
        $(By.xpath("//td//child::a[contains(@href,'/user/')]")).click();
    }

    /**
     * чистит поля личной информации перед редактированием
     */
    private void clearFieldsPersonalInfo()
    {
        $(By.id("firstName")).clear();
        $(By.id("lastName")).clear();
        $(By.id("middleName")).clear();
        $(By.id("firstNameEng")).clear();
        $(By.id("lastNameEng")).clear();
    }

    /**
     *  заполняет поле имя при редактировании
     */
   private void fillFirstName (String changedFirstName)
    {
        WebElement firstNameField = $(By.id("firstName"));
        firstNameField.sendKeys(changedFirstName);
    }

    /**
     * заполняет поле фамилия при редактировании
     */
    private void fillLastName (String changedLastName)
    {
        WebElement lastNameField = $(By.id("lastName"));
        lastNameField.sendKeys(changedLastName);
    }

    /**
     * заполняет поле отчество при редактировании
     */
    private void fillMiddleName (String changedMaiddleName)
    {
        WebElement middleNameField = $(By.id("middleName"));
        middleNameField.sendKeys(changedMaiddleName);
    }

    /**
     * заполняет поле имя(eng) при редактировании
     */
    private void fillFirstNameEng (String changedFirstNameEng)
    {
        WebElement firstNameEngField = $(By.id("firstNameEng"));
        firstNameEngField.sendKeys(changedFirstNameEng);
    }

    /**
     * заполняет поле фамилия(eng) при редактировании
     */
    private void fillLastNameEng (String changeLastNameEng)
    {
        WebElement lastNameEngField = $(By.id("lastNameEng"));
        lastNameEngField.sendKeys(changeLastNameEng);
    }

    /**
     *  проверяет пиктограмму редактирования личной информации, в случае если пиктограмма есть, меняет личные данные и сохраняет их
     */
    public int editPersonalInfoFromAdmin (int logErrors)
    {
        if (!buttonEditPersonalInfo.isEmpty())
        {
            $(By.xpath("//a[contains(@href,'/editPersonalInfo')]")).click();
            log("Перешли на форму редактирования личных данных");
            log("Очистили поля перед редактированием");
            clearFieldsPersonalInfo();
            log("Меняем имя");
            fillFirstName("Измененная");
            log("Меняем фамилию");
            fillLastName("Личная");
            log("Меняем отчество");
            fillMiddleName("Информация");
            log("Меняем Имя(Eng)");
            fillFirstNameEng("Edit");
            log("Меняем Фамилию(Eng)");
            fillLastNameEng("Data");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();

        } else
        {
            logErrors++;
            log("Пиктограмы 'Редактировать' в блоке личной информации нет");
        }
        return logErrors;
    }

    /**
     *  чистит поля контактной информации перед редактированием
     */
    private void clearFieldsContatInfo()
    {

    }

    /**
     * редактирование и сохранение личных данных от лица пользователя
     */
    public int editPersonalInfoFromUser (int logErrors)
    {

        if (!buttonEditPersonalInfo.isEmpty())
        {
            $(By.xpath("//a[contains(@href,'/editPersonalInfo')]")).click();
            log("Перешли на форму редактирования личных данных");
            log("Очистили поля перед редактированием");
            clearFieldsPersonalInfo();
            log("Меняем имя");
            fillFirstName("Данных");
            log("Меняем фамилию");
            fillLastName("Личных");
            log("Меняем отчество");
            fillMiddleName("Изменение");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
            log("Нажимаем кнопку Сохранить");
        } else
        {
            logErrors++;
            log("Пиктограмы 'Редактировать' в блоке личной информации нет");
        }

        return logErrors;

    }

    /**
     * заполняет поле Мобильный телефон при редактировании контактной информации
     */
    private void fillPhone (String changePhone)
    {
        WebElement phoneField = $(By.id("phoone"));
        phoneField.sendKeys(changePhone);
    }

    /**
     * редактирование и сохранение контактных данных от лица администратора
     */
    public int editContactInfoFromAdmin (int logErrors)
    {
        if (!buttonEditContactInfo.isEmpty())
        {
            $(By.xpath("//a[contains(@href,'/editContactInfo')]")).click();
            log("Перешли на форму редактирования контактной информации");
            log("Добавляем номер телефона");
            fillPhone("+79789789789");
            log("Добавляем email");
            fillEmail("izmenenie@mail.com");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
            log("Нажимаем кнопку Сохранить");
        } else
        {
            logErrors++;
            log("Нет пиктограммы 'Редактировать' в блоке контактной информации ");
        }
        return logErrors;
    }

    private ElementsCollection checkPhone = $$(By.linkText("Изменение"));
    private ElementsCollection checkEmail = $$(By.linkText("Личных"));


    /**
     * проверяет изменения контактной информации
     */
    public int checkContactInfo (int logErrors)
    {
        logErrors = checkAndLog(checkPhone.isEmpty(), logErrors, "ОШИБКА: телефон не верный", "Телефон верный");
        logErrors = checkAndLog(checkEmail.isEmpty(), logErrors, "ОШИБКА: Email не верный", "Email верный");


        return logErrors;
    }

    /**
     * чистит поля телефон и email
     */
    private void clearFieldsContactInfo()
    {
        $(By.id("phone")).clear();
        $(By.id("email")).clear();
    }

    /**
     *  редактирование и сохранение контактнных данных от лица пользователя
     */
    public int editContactInfoFromUser (int logErrors)
    {
        if (!buttonEditContactInfo.isEmpty())
        {
            $(By.xpath("//a[contains(@href,'/editContactInfo')]")).click();
            log("Перешли на форму редактирования контактной информации");
            log("Стираем номер телефона и email");
            clearFieldsContactInfo();
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
            log("Нажимаем кнопку Сохранить");
        } else
        {
            logErrors++;
            log("Нет пиктограммы 'Редактировать' в блоке контактной информации ");
        }
        return logErrors;
    }

    private ElementsCollection checkEditFirstNameFromAdmin = $$(By.linkText("Измененная"));
    private ElementsCollection checkEditLastNameFromAdmin = $$(By.linkText("Личная"));
    private ElementsCollection checkEditMiddleNameFromAdmin = $$(By.linkText("Информация"));
    private ElementsCollection checkEditFirstNameEngFromAdmin = $$(By.linkText("Edit"));
    private ElementsCollection checkEditLastNameEngFromAdmin = $$(By.linkText("Data"));

    /**
     * проверка измененной личной информации
     */
    public int checkEditsPersonalInfoFromAdmin (int logErrors)
    {
        logErrors = checkAndLog(checkEditFirstNameFromAdmin.isEmpty(), logErrors, "ОШИБКА: имя не изменено", "Имя изменено");
        logErrors = checkAndLog(checkEditLastNameFromAdmin.isEmpty(), logErrors, "ОШИБКА: фимилия не изменена", "Фамилия изменена");
        logErrors = checkAndLog(checkEditMiddleNameFromAdmin.isEmpty(), logErrors, "ОШИБКА: отчество не изменено", "Отчество изменено");
        logErrors = checkAndLog(checkEditFirstNameEngFromAdmin.isEmpty(), logErrors, "ОШИБКА: имя ENG не изменено", "Имя ENG изменено");
        logErrors = checkAndLog(checkEditLastNameEngFromAdmin.isEmpty(), logErrors, "ОШИБКА: фамилия ENG не изменена", "Фамилия ENG изменена");

        return logErrors;
    }

    /**
     * чистит поля места работы
     */
    private void clearFieldsJob()
    {
        $(By.id("post")).clear();
        $(By.id("workPhone")).clear();
    }
    /**
     * заполняет поле должность
     */
    private void fillPost (String changedPost)
    {
        WebElement postField = $(By.id("post"));
        postField.sendKeys(changedPost);
    }

    /**
     *  добавление должности путем редактирования должности пользователя
     */
    public int addTestPostFromAdmin (int logErrors)
    {
        if (!buttonEditJob.isEmpty())
        {
            $(By.xpath("//a[contains(@href,'/editJob')]")).click();
            log("Перешли на форму редактирования места работы");
            log("Стираем Должность");
            $(By.id("post")).clear();
            log("Вводим название тестовой должности");
            fillPost("Тестовая должность");
            log("Нажимаем кнопку Сохранить");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
            log("Возвращаем должность пользователя в первоначальное состояние");
            $(By.xpath("//a[contains(@href,'/editJob')]")).click();
            $(By.id("post")).clear();
            fillPost("Директор");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
        } else
        {
            logErrors++;
            log("Нет пиктограммы 'Редактировать' в блоке место работы");
        }
        return logErrors;
    }

    private ElementsCollection editedPost =  $$(By.linkText("Директор+"));
    private ElementsCollection editedPhone = $$(By.linkText("555557"));

    /**
     * редактирование должности и рабочего телефона
     */
    public int editPostAndPhoneFromAdmin (int logErrors)
    {
        if (!buttonEditJob.isEmpty())
        {
            $(By.xpath("//a[contains(@href,'/editJob')]")).click();
            log("Перешли на форму редактирования места работы");
            log("Редактируем должность");
            fillPost("+");
            log("Редактируем рабочий телефон");
            fillPost("7");
            log("Нажимаем кнопку Сохранить");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
            log("Проверяем изменения");
            logErrors = checkAndLog(editedPost.isEmpty(), logErrors, "ОШИБКА: должность не отредактирована либо отредактирована неверно", "Должность отредактирована");
            logErrors = checkAndLog(editedPhone.isEmpty(), logErrors, "ОШИБКА: рабочий телефон не отредактирован либо отредактирован неверно", "Рабочий телефон отредактирован");
            log("Возвращаем данные в первоначальное состояние");
            $(By.xpath("//a[contains(@href,'/editJob')]")).click();
            clearFieldsJob();
            fillPost("Директор");
            fillPhone("55555");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
        }  else
        {
            logErrors++;
            log("Нет пиктограммы 'Редактировать' в блоке место работы");
        }
        return logErrors;
    }

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
     * выход из под симулирования пользователя
     */
    public void stopSimulateUser ()
    {
        $(By.xpath("//a[@href='/stopSimulateUser']")).click();
    }

    /**
     * удаляем роль
     */
    public void deleteRoleAdmin ()
    {
        $(By.xpath("//a[contains(@data-ajax,'/users/denyAdmin')]")).click();
    }
}
