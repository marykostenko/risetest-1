import java.io.IOException;
import java.sql.*;

public class TestDatabaseConnection
{

    private String columnActivation = "activationString";
    public String getColumnActivation() { return columnActivation; }

    private String elementId = "id";
    public String getElementId() { return elementId; };

    private String columnRegNumber = "registrationNumber";
    public String getColumnRegNumber() { return  columnRegNumber; }

    /**
     * Получает запрос на выборку к базе и название колонки и возвращает ячейку (НАДО ПЕРЕПИСАТЬ НА МАССИВ)
     */
    public String selectFromDatabase(String host, String port, String database, String userName, String password, String query, String columnLabel) throws SQLException {

            String result = null;

            Connection connection = null;
            try
            {
                connection = DriverManager.getConnection("jdbc:postgresql://"+ host +":" + port + "/" + database, userName, password);

                Statement statement = connection.createStatement();

                ResultSet executeQuery = statement.executeQuery(query);

                while (executeQuery.next())
                {
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
            if (null == connection)
            {
                System.out.println("✗ Подключение НЕ установлено");
            }
        return result;
    }

    /**
     * Получает запрос и обновляет данные в базе
     */
    public void updateInDatabase(String host, String port, String database, String userName, String password, String query)
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://"+ host +":" + port + "/" + database, userName, password);

            Statement statement = connection.createStatement();

            statement.executeUpdate(query);

            // Закрываем соединение
            statement.close();
            connection.close();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        if (null == connection)
        {
            System.out.println("✗ Подключение НЕ установлено");
        }
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
     * Запрос на регномер кандидата
     */
    public String requestSelectCandidateRegNumber(String idCandidate)
    {
        String query = "SELECT \n" +
                "  \"Candidate\".\"registrationNumber\"\n" +
                "FROM \n" +
                "  public.\"Candidate\"\n" +
                "WHERE\n" +
                "  \"Candidate\".id = '"+ idCandidate +"';";
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
     * Запрос на очистку списка отмеченных кандидатов для пользователя
     */
    private String requestDeleteCandidatesFromSelection (String userId)
    {
        String query = "DELETE FROM \n" +
                "  public.\"CandidateListSelection\"\n" +
                "  WHERE\n" +
                "  \"CandidateListSelection\".\"userId\" = '" + userId + "';\n";
        return query;
    }

    /**
     * Запрос на countryId по названибю страны
     */
    private String requestCountryId(String country)
    {
        String query = "SELECT \n" +
                "  \"FSMCountry\".id\n" +
                "FROM \n" +
                "  public.\"FSMCountry\"\n" +
                "WHERE \n" +
                "\"FSMCountry\".name LIKE '%"+ country +"%';\n";
        return query;
    }

    /**
     * Вытаскивает countryId
     */
    public String selectCountryId(String country) throws IOException, SQLException {

        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();

        String queryCountryId = requestCountryId(country);
        String countryId = selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(), testDatabaseConnectingData.getDatabase(),
                testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryCountryId, getElementId());

        return countryId;
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

        String queryCandidateId = requestSelectCandidateId(candidateEmail);
        String candidateId = selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(), testDatabaseConnectingData.getDatabase(),
                testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(),queryCandidateId, getElementId());

        return candidateId;
    }

    /**
     * Вытаскивает регномер кандидата, используя его id
     */
    public String selectCandidateRegNumber(String idCandidate) throws IOException, SQLException {

        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();

        String querySelectCandidateRegNumber = requestSelectCandidateRegNumber(idCandidate);
        String candidateRegNumber = selectFromDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(), testDatabaseConnectingData.getDatabase(),
                testDatabaseConnectingData.getUserNameForDB(), testDatabaseConnectingData.getPasswordForDB(), querySelectCandidateRegNumber, getColumnRegNumber());

        return candidateRegNumber;
    }


    /**
     * Меняет состяоние кандидату
     * @param newState
     * @param candidateId
     * @throws SQLException
     * @throws IOException
     */
    public void changeCandidateState(String newState, String candidateId) throws SQLException, IOException
    {
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();

        String queryChangeCandidateState = requestUpdateCandidateState(newState, candidateId);
        updateInDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(), testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(),
                testDatabaseConnectingData.getPasswordForDB(),queryChangeCandidateState);

    }

    /**
     * Чистит список отмеченных кандидатов для пользователя
     */
    public void deleteCandidatesFromSelection(String userId) throws IOException
    {
        TestDatabaseConnectingData testDatabaseConnectingData = new TestDatabaseConnectingData();

        String queryDelete = requestDeleteCandidatesFromSelection(userId);
        updateInDatabase(testDatabaseConnectingData.getHost(), testDatabaseConnectingData.getPort(), testDatabaseConnectingData.getDatabase(), testDatabaseConnectingData.getUserNameForDB(),
                testDatabaseConnectingData.getPasswordForDB(),queryDelete);
        System.out.println();
    }

}

