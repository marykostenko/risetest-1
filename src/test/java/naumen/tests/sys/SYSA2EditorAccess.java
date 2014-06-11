package naumen.tests.sys;

import naumen.NaumenTest;
import naumen.framework.base.entities.User;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SYSA2EditorAccess extends NaumenTest {

    User user1 = new User();
    List<String> checkTextList = new ArrayList<String>(Arrays.asList(
            "Система управления контентом"
    ));
    String urlSuffix = "/editor";

    // предполагаем, что в параметрах данные обычного пользователя типа autotest !!!
    @Parameters({"email", "password", "firstName", "lastName"})
    public SYSA2EditorAccess(String mail, String psw, String nam, String snam){
        super(mail, psw, nam, snam);
    }

    /**
     * default constructor
     */
    public SYSA2EditorAccess(){
        super();
    }

    public void runTest(){
        logStep("1", "SYS-A-2.1	Доступ к редакторской системе для администратора");
        user1.setEmail("help@fcntp.ru");
        user1.setPassword("manager1");
        login(user1);

        assertAccessGranted(urlSuffix, checkTextList);
        logout();

        logStep("2", "SYS-A-2.2	Доступ к редакторской системе для модератора");
        user1.setEmail("moderator@naumen.ru");
        user1.setPassword("123qwe123");
        login(user1);
        assertAccessGranted(urlSuffix, checkTextList);
        logout();

        logStep("3", "SYS-A-2.3	Нет доступа к редакторской системе для простого пользователя");
        login(user);
        assertAccessDenied(urlSuffix);
        logout();

        logStep("4", "SYS-A-2.4	Нет доступа к редакторской системе для внешнего пользователя");
        assertAccessDeniedForAnonymousUser(urlSuffix);
    }
}
