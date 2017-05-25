import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by Maria on 24.05.2017.
 */
public class CandidateRegistrationTest extends BaseTest
{

//Самостоятельная регистрация кандидата


    //CAND-REG-1.1
    @Test(priority = 1)
    public void testGoToRegistrationFromHat() throws IOException
    {

        log("Запущен тест CAND-REG-1.1");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Регистрация");
        pageTopBottom.goToRegistration();


        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Проверяем, что есть форма регистрации");
        pageRegistration.isRegistrationForm();

        checkMistakes();

        log("Тест CAND-REG-1.1 завершен");
    }


    //CAND-REG-1.2
    @Test(priority = 2)
    public void testGoToRegistrationFromFooter() throws IOException
    {

        log("Запущен тест CAND-REG-1.2");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Зарегистрироваться под футером");
        PageMain pageMain = new PageMain();
        pageMain.goToRegistrationFromFooter();


        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Проверяем, что есть форма регистрации");
        pageRegistration.isRegistrationForm();

        checkMistakes();

        log("Тест CAND-REG-1.2 завершен");
    }

    //CAND-REG-1.3
    @Test(priority = 3)
    public void testGoToRegistrationFromBlockLearnFree() throws IOException
    {

        log("Запущен тест CAND-REG-1.3");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку 'Подать заявку' в блоке 'Учись беслпатно!'");
        PageMain pageMain = new PageMain();
        pageMain.goToRegistrationFromBlockLearnFree();


        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Проверяем, что есть форма регистрации");
        pageRegistration.isRegistrationForm();

        checkMistakes();

        log("Тест CAND-REG-1.3 завершен");
    }

    //CAND-REG-1.4
    @Test(priority = 4)
    public void testGoToRegistrationFromBlockContractTraining() throws IOException
    {

        log("Запущен тест CAND-REG-1.4");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку 'Подать заявку' в блоке 'Обучение по контракту'");
        PageMain pageMain = new PageMain();
        pageMain.goToRegistrationFromBlockContractTraining();


        log("Проверяем, что открылась страница с url /registration?contract=true");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationContract(logErrors);

        log("Проверяем, что есть форма регистрации");
        pageRegistration.isRegistrationContractForm();

        checkMistakes();

        log("Тест CAND-REG-1.4 завершен");
    }

    //CAND-REG-1.5
    @Test(priority = 5)
    public void testGoToRegistrationFromPublicPage() throws IOException
    {

        log("Запущен тест CAND-REG-1.5");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Открываем страницу  'Как поступить'");
        pageTopBottom.openEdInRus();
        pageTopBottom.goToPublicPageHowToApply();

        log("Нажимаем кнопку 'Отправить заявку'");
        PagePublicHowToApply pagePublicHowToApply = new PagePublicHowToApply();
        pagePublicHowToApply.goToRegistration();

        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Проверяем, что есть форма регистрации");
        pageRegistration.isRegistrationForm();

        checkMistakes();

        log("Тест CAND-REG-1.5 завершен");
    }

    //CAND-REG-1.6
    @Test(priority = 6)
    public void testGoToRegistrationFromBasket() throws IOException
    {

        log("Запущен тест CAND-REG-1.6");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Переходим в навигатор образовательных программ");
        pageTopBottom.goToNavigator();

        log("Добавляем первую ОП из списка");
        PageNavigator pageNavigator = new PageNavigator();
        pageNavigator.addFirstEP();

        log("Открыть корзину");
        pageTopBottom.openBasket();

        log("Перейти к заполнению заявки");
        pageTopBottom.pushTheButtonInBasket();

        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Проверяем, что есть форма регистрации");
        pageRegistration.isRegistrationForm();

        checkMistakes();

        log("Тест CAND-REG-1.6 завершен");
    }

    //CAND-REG-1.7
    @Test(priority = 7)
    public void testRegistrationQuotaWithPartitalFilling() throws IOException
    {

        log("Запущен тест CAND-REG-1.1");

        log("Переходим на главную страницу");
        PageTopBottom pageTopBottom = new PageTopBottom();
        pageTopBottom.goToHomePage();

        log("Переключаем язык страницы на русский");
        pageTopBottom.switchToRu();

        log("Нажимаем кнопку Регистрация");
        pageTopBottom.goToRegistration();


        log("Проверяем, что открылась страница с url /registration");
        PageRegistration pageRegistration = new PageRegistration();
        log("Url страницы: " + url());
        logErrors = pageRegistration.assertRegistrationQuota(logErrors);

        log("Заполняем обязательбные поля");
        TestUserData registrationQuotaPartial = new TestUserData(getUserForRegistrationPartialQuotaId());
        pageRegistration.partialFillingRegistrationForm(registrationQuotaPartial.getUserLastName(), registrationQuotaPartial.getUserFirstName(), registrationQuotaPartial.getSex(),
                registrationQuotaPartial.getCountry(), registrationQuotaPartial.getUserLogin(), registrationQuotaPartial.getUserPassword());


    }

}
