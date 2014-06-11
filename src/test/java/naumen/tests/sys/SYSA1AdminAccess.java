package naumen.tests.sys;


import naumen.NaumenTest;
import naumen.framework.base.entities.User;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SYSA1AdminAccess extends NaumenTest {

    User user1 = new User();
    List<String> checkTextList = new ArrayList<String>(Arrays.asList(
            "Информация", "Действия", "Пользователи",
            "Модераторы", "Журнал", "Жалобы", "Справочники",
            "Шаблоны", "Системная информация"
    ));
    String urlSuffix = "/admin";

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

    public void runTest(){
        logStep("1", "SYS-A-1.1	Доступ к админке для администратора");
            user1.setEmail("help@fcntp.ru");
            user1.setPassword("manager1");
            login(user1);

            assertAccessGranted(urlSuffix, checkTextList);
            logout();

        logStep("2", "SYS-A-1.2	Нет доступа к админке для модератора");
            user1.setEmail("moderator@naumen.ru");
            user1.setPassword("123qwe123");
            login(user1);
            assertAccessDenied("/admin");
            logout();

        logStep("3", "SYS-A-1.3	Нет доступа к админке для простого пользователя");
            login(user);
            assertAccessDenied(urlSuffix);
            logout();

        logStep("4", "SYS-A-1.4	Нет доступа к админке для внешнего пользователя");
            assertAccessDeniedForAnonymousUser(urlSuffix);
    }
}
