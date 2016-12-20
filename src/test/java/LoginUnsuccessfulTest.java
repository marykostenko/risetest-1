import org.testng.annotations.Test;

/**
 * Created by Maria on 20.12.2016.
 */
public class LoginUnsuccessfulTest extends BaseTest {

    @Test(priority =1)
    public void testUnsuccessfulLogin(){
        log("Завершен тест USER-L-2-1");
        logErrors++;
    }
}
