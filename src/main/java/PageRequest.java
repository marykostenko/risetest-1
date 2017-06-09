import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
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
        WebElement kop = driver.findElement(By.xpath("//tr[8]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(kop);
        actions.perform();
        sleep(1000);
        $(By.xpath("//td[contains(text(),'Копия паспорта')]//following::i[1]")).click();
        WebElement fileInput = driver.findElement(By.xpath("//td[contains(text(),'Копия паспорта')]//following::input[2]"));
        fileInput.sendKeys("C:/1.png");
    }
}
