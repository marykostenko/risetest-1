import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by user nkorobicina on 17.11.2016.
 */
public abstract class BasePage {
    protected WebDriverWait wait;
    protected static final int waitTime = 300;
    protected WebDriver driver;

    public void log(String textLog){
        System.out.println(textLog);
    }

    //метод берет проверяемое условие, текущее количество найденных ошибок и сообщение об ошибке.
    //если условие выполняется, то в лог пишется сообщение и увеличивается число ошибок
    protected int checkAndLog(boolean condition, int logErrors, String messageError){
        if(condition){
            log(messageError);
            logErrors++;
        }
        return logErrors;
    }

    //метод берет проверяемое условие, текущее количество найденных ошибок и сообщение об ошибке и о верном результате.
    //если условие выполняется, в лог пишется сообщение об ошибке и увеличивается число ошибок; иначе - в лог пишется сообщение о верном результате
    protected int checkAndLog(boolean condition, int logErrors, String messageError, String messageSuccess){
        if(condition){
            log(messageError);
            logErrors++;
        }
        else
            log(messageSuccess);
        return logErrors;
    }


}
