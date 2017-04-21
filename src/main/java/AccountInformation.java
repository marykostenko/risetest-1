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

    /**
     * возвращает фамилию из профиля пользователя
     * */
    public String getLastNameUser ()
    {
        return $(By.xpath("//div[contains(text(),'Фамилия')]//following::div[1]")).text();
    }

    /**
     * возвращает имя из профиля пользователя
     */
    public String getFirstNameUser()
    {
        return $(By.xpath("//div[contains(text(),'Имя')]//following::div[1]")).text();
    }

    /**
     * возвращает отчетсво из профиля пользователя
     **/
    public String getMiddleNameUser()
    {
        return $(By.xpath("//div[contains(text(),'Отчество')]//following::div[1]")).text();
    }

    /**
     * сверяет фамилию в профиле с ожидаемой
     */
    public int checkLastName (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getLastNameUser().equals(expectedFIO), logErrors,
                "Ошибка: В шапке неправильное имя пользователя - " + getLastNameUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет имя в профиле с ожидаемым
     */
    public int checkFirstName (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getFirstNameUser().equals(expectedFIO), logErrors,
                "Ошибка: В шапке неправильное имя пользователя - " + getFirstNameUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * сверяет отчество в профиле с ожидаемым
     */
    public int checkMiddleName (String expectedFIO, int logErrors)
    {
        return checkAndLog(!getMiddleNameUser().equals(expectedFIO), logErrors,
                "Ошибка: В шапке неправильное имя пользователя - " + getMiddleNameUser() + "; ожидалось - " + expectedFIO);
    }

    /**
     * кнопки в профиле пользователя
     */
    private ElementsCollection buttonEditJob = $$(By.xpath("//a[contains(@href,'/editJob')]"));
    private ElementsCollection buttonEditPersonalInfo = $$(By.xpath("//a[contains(@href,'/editPersonalInfo')]"));
    private ElementsCollection buttonEditContactInfo = $$(By.xpath("//a[contains(@href,'/editContactInfo')]"));
    private ElementsCollection buttonAddRole = $$(By.xpath("//a[@href='#add_modal_']"));

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
        //    fillFirstName(testEditedUserForEditPersonalData);
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
        WebElement phoneField = $(By.id("phone"));
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
  //          fillEmail("izmenenie@mail.com");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
            log("Нажимаем кнопку Сохранить");
        } else
        {
            logErrors++;
            log("Нет пиктограммы 'Редактировать' в блоке контактной информации ");
        }
        return logErrors;
    }

    private ElementsCollection checkPhone = $$(By.xpath("//div[contains(text(),'+79789789789')]"));;
    private ElementsCollection checkEmail = $$(By.xpath("//div[contains(text(),'izmenenie@mail.com')]"));;


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

    private ElementsCollection checkEditFirstNameFromAdmin = $$(By.xpath("//div[contains(text(),'Измененная')]"));;
    private ElementsCollection checkEditLastNameFromAdmin = $$(By.xpath("//div[contains(text(),'Личная')]"));;
    private ElementsCollection checkEditMiddleNameFromAdmin = $$(By.xpath("//div[contains(text(),'Информация')]"));;
    private ElementsCollection checkEditFirstNameEngFromAdmin = $$(By.xpath("//div[contains(text(),'Edit')]"));;
    private ElementsCollection checkEditLastNameEngFromAdmin = $$(By.xpath("//div[contains(text(),'Data')]"));;

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
            $(By.xpath("//strong[contains(text(),'Директор')]")).click();
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
        } else
        {
            logErrors++;
            log("Нет пиктограммы 'Редактировать' в блоке место работы");
        }
        return logErrors;
    }

    private ElementsCollection editedPost =  $$(By.xpath("//div[contains(text(),'Директор+')]"));;
    private ElementsCollection editedPhone = $$(By.xpath("//div[contains(text(),'555557')]"));;

    /**
     * заполнение рабочего телефона
     */
    private void fillWorkPhone (String changePhone)
    {
        WebElement phoneField = $(By.id("workPhone"));
        phoneField.sendKeys(changePhone);
    }
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
            fillWorkPhone("7");
            log("Нажимаем кнопку Сохранить");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
            log("Проверяем изменения");
            logErrors = checkAndLog(editedPost.isEmpty(), logErrors, "ОШИБКА: должность не отредактирована либо отредактирована неверно", "Должность отредактирована");
            logErrors = checkAndLog(editedPhone.isEmpty(), logErrors, "ОШИБКА: рабочий телефон не отредактирован либо отредактирован неверно", "Рабочий телефон отредактирован");
            log("Возвращаем данные в первоначальное состояние");
            $(By.xpath("//a[contains(@href,'/editJob')]")).click();
            clearFieldsJob();
            fillPost("Директор");
            fillWorkPhone("55555");
            $(By.xpath("//button[contains(@class,'btn-primary')]")).click();
        }  else
        {
            logErrors++;
            log("Нет пиктограммы 'Редактировать' в блоке место работы");
        }
        return logErrors;
    }

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
