package naumen.tests.user;

import naumen.NaumenTest;
import org.testng.annotations.Parameters;

public class R1UserRegistartion extends NaumenTest {
    @Parameters({"email", "password", "name", "surname"})
    public R1UserRegistartion(String mail, String psw, String nam, String snam){
        super(mail, psw, nam, snam);
    }

    /**
     * default constructor
     */
    public R1UserRegistartion(){
        super();
    }

    public void runTest(){

    }
}
