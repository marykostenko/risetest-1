import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 22.12.2016.
 */
public class PageUsersList extends BasePage{



    /**
     *заполняет поле email для поиска пользователя
     */
    public void fillUserEmail(String userEmail)
    {
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(userEmail);
    }

    /**
     *нажимает кнопку "Поиск" для того чтобы отфильтровать пользователей
     */
    public void clickUserSearchButton()
    {
        $(By.xpath("//button[@id='submit_search']")).click();
    }

    /**
     * выбирает отфильтрованного пользователя
     */

    public void chooseFilteredUser()
    {
        $(By.xpath("//td//child::a[contains(@href,'/user')]")).click();
    }


}
