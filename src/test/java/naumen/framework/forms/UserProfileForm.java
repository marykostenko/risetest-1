package naumen.framework.forms;


import naumen.framework.base.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UserProfileForm extends BaseAppForm {
    public static Label userProfile = new Label(By.className("username"), "Профиль пользователя");
    public Label lots = new Label(By.xpath("//a[contains(.,'Конкурсы')]"), "Конкурсы");
    public Label messages = new Label(By.xpath("//a[contains(.,'Сообщения')]"), "Сообщения");
    public Label discussions = new Label(By.xpath("//a[contains(.,'Дискуссии')]"), "Дискуссии");
    public Label notifications = new Label(By.xpath("//a[contains(.,'Уведомления')]"), "Уведомления");
    public Label subscriptions = new Label(By.xpath("//a[contains(.,'Подписки')]"), "Подписки");
    private List<WebElement> subscriptionsList = null;


    public UserProfileForm() {
        super(By.className("user-content"), "Профиль пользователя");
    }

    public static void openUserProfile(){
        userProfile.click();
    }

    /**
     * инициализирует список веб-элементов подписок
     */
    private void initSubscriptionsList(){
        if (subscriptions.getElement().getAttribute("class").equals("active"))
            subscriptionsList = getDriver().findElements(By.className("subscriptions-block"));
        else{
            logger.notify("Вкладка 'Подписки' неактивна, поэтому список подписок пуст");
            subscriptionsList = new ArrayList<WebElement>();
        }
    }
    /**
     * возвращает список веб-элементов с названиями подписок на странице подписок
     * @return
     */
    public List<WebElement> getSubscriptionsList(){
        if ((subscriptionsList == null) || (subscriptionsList.isEmpty()))
            initSubscriptionsList();
        return subscriptionsList;
    }

    /**
     * возвращает значение поля "Тип" у подписки в списке подписок
     * @param subscription
     * @return
     */
    public String getSubscrType(WebElement subscription){
        WebElement el = subscription.findElement(By.className("col-xs-8"));
        if (el.getText().startsWith("Тип")){
            return el.getText().substring(4);
        }
        else
            return el.getText();
    }

}
