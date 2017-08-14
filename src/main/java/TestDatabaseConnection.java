import java.sql.*;

public class TestDatabaseConnection
{

    /**
     * Находит активационный код для кандидата
     */
    public static String selectActivationCodeFromDatabase(String host, String port, String database, String userName, String password, String query) throws SQLException {

            System.out.println("Подключение к базе данных");
            String result = null;

            Connection connection = null;
            try
            {
                connection = DriverManager.getConnection("jdbc:postgresql://"+ host +":" + port + "/" + database, userName, password);
                Statement statement = connection.createStatement();
                ResultSet executeQuery = statement.executeQuery(query);

                while (executeQuery.next()) {
                    result = executeQuery.getString("activationString");
                }
                // Закрываем соединение
                executeQuery.close();
                statement.close();
                connection.close();

            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            if (null != connection)
            {
                System.out.println("✓ Подключение установлено");
            }
            else
            {
                System.out.println("✗ Подключение НЕ установлено");
            }
        return result;
    }

    /**
     * Запрос на активационный код
     */
    public String requestSelectActivationCode(String userEmail) throws SQLException {

        String query = "SELECT \"User\".\"activationString\" FROM public.\"User\" WHERE \"User\".email = '" + userEmail + "'";
        return query;
    }


}

