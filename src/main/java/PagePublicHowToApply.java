import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 24.05.2017.
 */
public class PagePublicHowToApply extends BasePage
{
    public void goToRegistration() { $(By.xpath(" //div[@class='rs-request-button-wrapper']//child::a[@href='/registration']")).click(); }

}
