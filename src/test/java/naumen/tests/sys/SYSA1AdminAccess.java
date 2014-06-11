package naumen.tests.sys;


import naumen.NaumenTest;
import naumen.framework.base.entities.User;
import naumen.framework.forms.LoginForm;
import naumen.framework.forms.MainForm;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SYSA1AdminAccess extends NaumenTest {

    private MainForm mf;
    User user1 = new User();

    // предполагаем, что в параметрах данные обычного пользователя типа autotest !!!
    @Parameters({"email", "password", "firstName", "lastName"})
    public SYSA1AdminAccess(String mail, String psw, String nam, String snam){
        super(mail, psw, nam, snam);
    }

    /**
     * default constructor
     */
    public SYSA1AdminAccess(){
        super();
    }

    protected void assertAdminAccessSucceed(){
        browser.navigate(getDriver().getCurrentUrl() + "/admin");
        mf = new MainForm();
        List<String> checkTextList = new ArrayList<String>(Arrays.asList(
                "Информация", "Действия", "Пользователи",
                "Модераторы", "Журнал", "Жалобы", "Справочники",
                "Шаблоны", "Системная информация"
                ));

        for(String str: checkTextList)
            mf.checkTextOnForm(str, this);
        makeScreen();
    }

    protected void assertAdminAccessFailed(){
        browser.navigate(getDriver().getCurrentUrl() + "/admin");
        mf = new MainForm();
//        List<WebElement> list = getDriver().findElementsByXPath("//h1[contains(.,'у вас нет прав на это действие')]");
//        doAssert(list.size() > 0, "", "");
        mf.checkTextOnForm("у вас нет прав на это действие", this);
        makeScreen();
    }

    protected void assertAdminAccessFailedForAnonymousUser(){
        browser.navigate(getDriver().getCurrentUrl() + "/admin");
        LoginForm lf = new LoginForm();
        makeScreen();
    }

    public void runTest(){
        logStep("1", "SYS-A-1.1	Доступ к админке для администратора");
            user1.setEmail("help@fcntp.ru");
            user1.setPassword("manager1");
            login(user1);
            assertAdminAccessSucceed();
            logout();

        logStep("2", "SYS-A-1.2	Нет доступа к админке для модератора");
            user1.setEmail("moderator@naumen.ru");
            user1.setPassword("123qwe123");
            login(user1);
            assertAdminAccessFailed();
            logout();

        logStep("3", "SYS-A-1.3	Нет доступа к админке для простого пользователя");
            login(user);
            assertAdminAccessFailed();
            logout();

        logStep("4", "SYS-A-1.4	Нет доступа к админке для внешнего пользователя");
            assertAdminAccessFailedForAnonymousUser();
    }
}
