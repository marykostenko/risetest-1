package naumen.framework.forms;

import org.openqa.selenium.By;

public class LotsSubscriptionForm extends BaseAppForm{
    /**
     * Constructor by default
     */
    public LotsSubscriptionForm() {
        super(By.xpath("//h1[contains(.,'Подписка на конкурсы')]"), "Подписка на конкурсы");
    }

    /** возвращает локатор идентификации формы
     * @return локатор
     */
    public static By getFormLocator(){
        return By.xpath("//h1[contains(.,'Подписка на конкурсы')]");
    }
}
