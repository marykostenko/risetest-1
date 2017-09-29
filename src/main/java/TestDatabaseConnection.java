import java.io.IOException;
import java.sql.*;

public class TestDatabaseConnection
{

    private String columnActivation = "activationString";
    public String getColumnActivation() { return columnActivation; }

    private String elementId = "id";
    public String getElementId() { return elementId; };

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

    /**
     * Запрос на id элемента справочника
     */
    public String requestSelectCatalogElementId (String elementCode) throws SQLException
    {
        String query = "SELECT \n" +
                "  \"CatalogItem\".id\n" +
                "FROM \n" +
                "  public.\"CatalogItem\"\n" +
                "  WHERE\n" +
                " \"CatalogItem\".code = '" + elementCode + "';";
        return query;
    }

    /**
     * Запрос на смену статуса кандидата по его id
     */
    public String requestUpdateCandidateState (String newState, String idCandidate)
    {
        String query = "UPDATE\n" +
                " public.\"Candidate\"\n" +
                " SET\n" +
                "   \"stateCode\" = '"+ newState +"'\n" +
                "  WHERE \n" +
                "   \"Candidate\".id = '" + idCandidate + "';";

        return query;
    }

    /**
     * Вытаскивает id кандидата, используя его email
     * @param candidateEmail
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public String selectCandidateId(String candidateEmail) throws IOException, SQLException {

        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();

        String queryOnCheckCandidateId = requestSelectCandidateId(candidateEmail);
        String candidateId = selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(),
                testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryOnCheckCandidateId, getElementId());

        return candidateId;
    }

    public void changeCandidateState(String newState, String candidateId) throws SQLException, IOException
    {
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();

        String queryChangeCandidateState = requestUpdateCandidateState(newState, candidateId);
        selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(), testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(),
                testDatabaseConnectingData.getPasswordForDB(),queryChangeCandidateState, getElementId());

    }

}

