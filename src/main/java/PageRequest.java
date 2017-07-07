
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Thread.sleep;


/**
 * Created by Maria on 09.06.2017.
 */
public class PageRequest extends BasePage
{
    /**
     * добавляет копию паспорта
     */

    public void loadingCopyOfPassport() throws InterruptedException
    {
        File file = new File("src/main/resources/cote.jpg");
        $x("//td[contains(text(),'Копия паспорта')]//following::td[2]//child::a[1]//child::input").uploadFile(file);
    }

    public void loadingCopyOfTheDocumentOnEducation()
    {
        File file = new File("src/main/resources/cote.jpg");
        $x("//td[contains(text(),'Копия документа об образовании')]//following::td[2]//child::a[1]//child::input").uploadFile(file);
    }

    public void goToPayServiceFee() throws InterruptedException {
        $(By.xpath("//a[contains(text(),'Личные данные')]")).click();
        $(By.xpath("//a[contains(@id,'FILLING_APPLICATION_to_CONTRACT_ACCEPTED')]")).click();
        sleep(2000);
    }
}
