import org.apache.commons.lang3.ObjectUtils;
import org.testng.log4testng.Logger;
import sun.util.logging.PlatformLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseConnection
{

    public static void connectionRiseDatabase(String host, String port, String database, String userName, String password) throws SQLException {

            System.out.println("Проверка подключения к базе данных");

            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://"+ host +":" + port + "/" + database, userName, password);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (null != connection) {
                System.out.println("✓ Подключение установлено");
            } else {
                System.out.println("✗ Подключение НЕ установлено");
            }
        }

    /**
     * достаёт из таблицы активационный код
     */
    public void selectActivateCode (String userEmail, String host, String port, String database, String userName, String password) throws SQLException {
        String query = "SELECT User.activationString FROM public.User WHERE User.email = " + userEmail;

    }


}

