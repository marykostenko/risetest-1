import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 20.04.2017.
 */
public class PageEditJob extends BasePage
{

    /**
     * чистит поля места работы
     */
    public void clearFieldsJob()
    {
        $(By.id("post")).clear();
        $(By.id("workPhone")).clear();
    }
    /**
     * заполняет поле должность
     */
    public void fillPost (String changedPost)
    {
        WebElement postField = $(By.id("post"));
        postField.sendKeys(changedPost);
    }

    /**
     * заполнение рабочего телефона
     */
    private void fillWorkPhone (String changePhone)
    {
        WebElement phoneField = $(By.id("workPhone"));
        phoneField.sendKeys(changePhone);
    }

    /**
     * заполняет форму места работы
     */
    public void fillJobForm(String userPost, String userWorkPhone)
    {
        fillPost(userPost);
        fillWorkPhone(userWorkPhone);
    }

    /**
     * нажимает кнопку сохранить
     */
    public void saveJobInfoChanges() { $(By.xpath("//button[@class='btn btn-primary']")).click(); }
}
