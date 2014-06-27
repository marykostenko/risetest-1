package naumen.tests.lot;

import naumen.NaumenTest;
import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.CheckBox;
import naumen.framework.base.elements.Label;
import naumen.framework.forms.MainForm;
import naumen.framework.forms.UserProfileForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;

import java.util.List;

public class LOTSLotSubscription extends NaumenTest {

    private MainForm mf;
    private UserProfileForm upf;
    private String lotName1 = "2.1 Проведение исследований в рамках международного многостороннего и двустороннего сотрудничества";
    private String lotName2 = "3.2 Обеспечение развития информационной инфраструктуры";

    @Parameters({"email", "password", "firstName", "lastName"})
    public LOTSLotSubscription(String mail, String psw, String nam, String snam){
        super(mail, psw, nam, snam);
    }

    /**
     * default constructor
     */
    public LOTSLotSubscription(){
        super();
    }

    /**
     * проверяем, что подпсика прошла успешно
     * @param lotName
     */
    private void checkSubscription(String lotName){
        UserProfileForm.openUserProfile();
        upf = new UserProfileForm();
        String classAttr = upf.lots.getElement().getAttribute("class");
        doAssert(classAttr.equals("active"), "Атрибут class вкладки 'Конкурсы' имеет значение 'active'", String.format("Атрибут class вкладки 'Конкурсы' имеет значение '%s'", classAttr));
        upf.subscriptions.click();
        String upperSubscrText = upf.getSubscriptionsList().get(0).getText();
        doAssert(upperSubscrText.contains(lotName), "Верхней в списке отображается подписка с нужным названием", String.format("Верхней в списке отображается подписка с названием '%s'", upperSubscrText));
        String subscrType = upf.getSubscrType(upf.getSubscriptionsList().get(0));
        doAssert(subscrType.equals("Новые конкурсы"), "Тип подписки 'Новые конкурсы'", String.format("Тип подписки '%s'", subscrType));
        makeScreen();
    }

    public void runTest(){
        logStep("1", "LOT-S-1	Форма подписки для внешнего пользователя");
            mf = new MainForm();
            mf.financeLblClick();
            mf.lotSubscriptionClick();
            mf.checkTextOnForm("Подписка на конкурсы", this);
            mf.checkTextOnForm("Для того чтобы подписаться Вам необходимо", this);
            //скроллим вниз до надписи, если она не влезла на экран, чтобы она попала и на скриншот
            new Actions(getDriver()).moveToElement(getDriver().findElementByXPath("//p[contains(.,'Для того чтобы подписаться Вам необходимо')]")).perform();
            List<WebElement> subscrBtns = getDriver().findElementsByXPath("//button[@type='submit' and contains(.,'Подписаться')]");
            doAssert(subscrBtns.size() == 0, "Кнопка 'Подписаться' отсутствует", "Кнопка 'Подписаться' присутствует");
            makeScreen();

        logStep("2", "LOT-S-2	Подписаться на новые конкурсы через форму подписки");
            login(user);
            mf = new MainForm();
            mf.financeLblClick();
            mf.lotSubscriptionClick();
            mf.checkTextOnForm("Подписка на конкурсы", this);
            CheckBox cb = new CheckBox(By.xpath(String.format("//label[contains(.,'%s')]/input[@type='checkbox']", lotName1)), lotName1);
            if (!cb.isChecked())
                cb.check();
            Button subscrBtn = new Button(By.xpath("//button[@type='submit' and contains(.,'Подписаться')]"), "Подписаться");
            subscrBtn.click();
            mf.checkTextOnForm("Вы успешно подписались на уведомления о появлении новых конкурсов", this);
            mf.checkTextOnForm("Вы подписаны на следующие программные мероприятия:", this);
            mf.checkTextOnForm(lotName1, this);
            makeScreen();
            checkSubscription(lotName1);
            logout();

        logStep("3", "LOT-S-3	Подписаться на новые конкурсы через профилирование");
            login(user);
            mf.financeLblClick();
            mf.financeSearchClick();
            mf.checkTextOnForm("Поиск финансирования", this);
            Label lab = new Label(By.xpath(String.format("//div[@class='title_small' and contains(.,'%s')]", lotName2)), lotName2);
            lab.moveMouseToElement();
            WebElement el = lab.findElement(By.xpath(".."));
            el.findElement(By.className("show-more")).click();
            subscrBtn = new Button(By.xpath("//div[contains(@class,'btn') and contains(.,'Подписаться на конкурсы')]"), "Подписаться на конкурсы");
            subscrBtn.clickAndWait();
            mf.checkTextOnForm("Вы успешно подписались", this);
            sleep(2); //чтобы обновилась надпись об успешной подписке и на скриншоте
            makeScreen();
            checkSubscription(lotName2);
            logout();

    }
}
