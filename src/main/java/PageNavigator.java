import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 24.05.2017.
 */
public class PageNavigator extends BasePage
{
    /**
     * добавляет первую ОП в корзину
     */
    public void addFirstEP() { $(By.xpath("//div[@id='navigator-organizations-js']/div[3]/div[2]/div[1]/a[@class='select-organization-js btn btn-primary pull-right']")).click(); }
}
