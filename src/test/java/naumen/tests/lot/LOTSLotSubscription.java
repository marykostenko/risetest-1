package naumen.tests.lot;

import naumen.NaumenTest;
import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.CheckBox;
import naumen.framework.base.elements.Label;
import naumen.framework.forms.MainForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;

import java.util.List;

public class LOTSLotSubscription extends NaumenTest {

    private MainForm mf;

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

        logStep("2", "LOT-S-1	Подписаться на новые конкурсы через форму подписки");
            login(user);
            mf = new MainForm();
            mf.financeLblClick();
            mf.lotSubscriptionClick();
            mf.checkTextOnForm("Подписка на конкурсы", this);
            CheckBox cb = new CheckBox(By.xpath("//label[contains(.,'2.1 Проведение исследований в рамках международного многостороннего и двустороннего сотрудничества')]/input[@type='checkbox']"),
                    "2.1 Проведение исследований в рамках международного многостороннего и двустороннего сотрудничества");
            if (!cb.isChecked())
                cb.check();
            Button subscrBtn = new Button(By.xpath("//button[@type='submit' and contains(.,'Подписаться')]"), "Подписаться");
            subscrBtn.click();
            mf.checkTextOnForm("Вы успешно подписались на уведомления о появлении новых конкурсов", this);
            mf.checkTextOnForm("2.1 Проведение исследований в рамках международного многостороннего и двустороннего сотрудничества", this);
            makeScreen();
            logout();

        logStep("3", "LOT-S-1	Подписаться на новые конкурсы через профилирование");
            login(user);
            mf.financeLblClick();
            mf.financeSearchClick();
            mf.checkTextOnForm("Поиск финансирования", this);
            Label lab = new Label(By.xpath("//div[@class='title_small' and contains(.,'2.1 Проведение исследований в рамках международного многостороннего и двустороннего сотрудничества')]"),
                    "2.1 Проведение исследований в рамках международного многостороннего и двустороннего сотрудничества");

    }
}
