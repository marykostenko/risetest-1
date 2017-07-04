import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Thread.sleep;

/**
 * Created by Maria on 09.06.2017.
 */
public class PageRequest extends BasePage
{
    /**
     * добавляет копию паспорта
     */
    public void loadingCopyOfPassport() throws InterruptedException {
        Actions actions = new Actions(driver);
        log("Наводим курсор на элемент");
        WebElement element = driver.findElement(By.xpath("//.[contains(text(),'Копия паспорта')]"));
        actions.moveToElement(element).build().perform();
        log("Жмакаем ан элемент");
        $(By.xpath("//td[contains(text(),'Копия паспорта')]//following::i[1]")).click();
        //WebElement fileInput = driver.findElement(By.xpath("//td[contains(text(),'Копия паспорта')]//following::input[2]"));
       // fileInput.sendKeys("C:/1.png");
    }
}
