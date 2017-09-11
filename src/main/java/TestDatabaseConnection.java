import java.sql.*;

public class TestDatabaseConnection
{

    private String columnActivation = "activationString";

    public String getColumnActivation() { return columnActivation; }

    private String columnCandidateId = "id";

    public String getColumnCandidateId() { return columnCandidateId; };

    /**
     * Получает запрос к базе и название колонки и возвращает ячейку (НАДО ПЕРЕПИСАТЬ НА МАССИВ)
     */
    public String selectFromDatabase(String host, String port, String database, String userName, String password, String query, String columnLabel) throws SQLException {

            System.out.println("Подключение к базе данных");
            String result = null;

            Connection connection = null;
            try
            {
                connection = DriverManager.getConnection("jdbc:postgresql://"+ host +":" + port + "/" + database, userName, password);
                Statement statement = connection.createStatement();
                ResultSet executeQuery = statement.executeQuery(query);

                while (executeQuery.next()) {
                    result = executeQuery.getString(columnLabel);
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
    public String requestSelectActivationCode(String userEmail) throws SQLException
    {
        String query = "SELECT \"User\".\"activationString\" FROM public.\"User\" WHERE \"User\".email = '" + userEmail + "'";
        return query;
    }

    /**
     * Запрос на id кандидата
     */
    public String requestSelectCandidateId(String userEmail) throws SQLException
    {
        String query = "SELECT \n" +
                "  \"Candidate\".id\n" +
                "FROM \n" +
                "  public.\"Candidate\", \n" +
                "  public.\"User\"\n" +
                "WHERE \n" +
                "  \"Candidate\".\"userId\" = \"User\".uuid\n" +
                "  AND \"User\".email = '" + userEmail +"';";
        return query;
    }

}

