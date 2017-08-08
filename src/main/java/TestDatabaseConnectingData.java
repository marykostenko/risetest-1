import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestDatabaseConnectingData
{
    private String host;
    private String port;
    private String userName;
    private String password;
    private String database;

    public TestDatabaseConnectingData() throws IOException
    {
        host = this.initDatabaseConnectingData("host");
        port = this.initDatabaseConnectingData("port");
        userName = this.initDatabaseConnectingData("userName");
        password = this.initDatabaseConnectingData("password");
        database = this.initDatabaseConnectingData("database");
    }

    protected String initDatabaseConnectingData(String fieldKey) throws IOException
    {
        Properties userData = new Properties();
        File propertyFile = new File("src/main/resources/databaseConnectingData.properties");
        userData.load(new FileReader(propertyFile));
        return userData.getProperty(fieldKey);
    }

    public String getDatabase() { return database;}
    public String getHost() { return host; }
    public String getPasswordForDB() { return password; }
    public String getPort() { return port; }
    public String getUserNameForDB() { return userName; }
}
