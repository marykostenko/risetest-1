import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class VerificationRightsDelegateTest extends BaseTest
{
    //USER-Rights-RS-1

    @Test (priority = 1)
    public void testVerificationQuotaCandidates() throws IOException, SQLException {
        TestCandidatesData testCandidatesData = new TestCandidatesData();

        log("Для выполнения данного теста добавляю тестовых кандидатов в систему:");

        testCandidatesData.createCandidateInAllStatues((getStandUrl("Ext")));

        log("Запущен тест USER-Rights-1.1");


    }
}
