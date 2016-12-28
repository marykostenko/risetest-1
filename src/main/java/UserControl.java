import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 22.12.2016.
 */
public class UserControl extends BasePage{

    //открывает страницу с пользователями
    public void usersPage(){

        $(By.xpath("//a[@href='/users']")).click();

    }
    //заполняет поле email для поиска пользователя
    public void fillUserEmail(String userEmail){
        WebElement emailField = $(By.id("email"));
        emailField.sendKeys(userEmail);
    }

    //нажимает кнопку "Поиск" для того чтобы отфильтровать пользователей
    public void userSearchButton(){

        $(By.xpath("//button[@id='submit_search']")).click();

    }

    //выбирает отфильтрованного пользователя
    public void filteredUser(){

        $(By.xpath("//td//child::a[contains(@href,'/user')]")).click();

    }

    //кнопка сброса попыток входа
    public void resetLoginAttempts(){

        $(By.xpath("//a[contains(@href,'/dropUserLoginAttempts/')]")).click();

    }
}
