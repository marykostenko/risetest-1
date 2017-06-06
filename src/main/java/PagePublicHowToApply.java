import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 24.05.2017.
 *
 * Публичная страница "Как поступить"
 */
public class PagePublicHowToApply extends BasePage
{
    /**
     * Переход на страницу регистрации, нажатием на кнопку Отправить заявку
     */
    public void goToRegistration() { $(By.xpath(" //div[@class='rs-request-button-wrapper']//child::a[@href='/registration']")).click(); }

}
