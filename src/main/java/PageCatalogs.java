import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Maria on 04.04.2017.
 */
public class PageCatalogs extends BasePage
{

    private ElementsCollection testPost = $$(By.xpath("//a[contains(text(),'Тестовая должность')]"));


    /**
     * переход к справочнику должностей
     */
    public void goToPostCatalog()
    {
        $(By.xpath("//a[@class='dropdown-toggle padding-right-0']")).click();
        $(By.xpath("//a[@href='/catalogs']")).click();
        $(By.xpath("//div[@class='span4']//child::a[@href='/catalog/generator/posts']")).click();
    }


    /**
     *  проверить новую тестовую должность в справочнике должностей, удалить в случае нахождения
     */
    public void checkAbsenceNewPost ()
    {
        if (testPost.isEmpty())
            log("Новая тестовая должность в справочнике не найдена. Продолжаем выполнение теста");
        else {
            log("В справочнике была найдена должность, которую мы хотим добавить. Спрерва удалим её");
            $(By.xpath("//a[contains(text(),'Тестовая должность')]")).click();
            $(By.xpath("//a[contains(@href,'/delete')]")).click();
            $(By.xpath("//button[contains(@class,'btn-danger')]")).click();
            log("Тестовая должность удалена. Продолжаем выполнение теста");
        }
    }

    /**
     * проверить, что тестовую должность добавили, удалить в случае нахождения. Если должность не добавлена, функция возвращает ошибку
     */
    public int checkNewPost (int logErrors)
    {
        if (testPost.isEmpty()) {
            logErrors++;
            log("ОШИБКА: Новая должность не была добавлена");
        } else {
            log("Новая должность была добавлена в справочник");
            $(By.xpath("//a[contains(text(),'Тестовая должность')]")).click();
            $(By.xpath("//a[contains(@href,'/delete')]")).click();
            $(By.xpath("//button[contains(@class,'btn-danger')]")).click();
            log("Тестовая должность удалена из справочника");

        }
        return logErrors;
    }

}
