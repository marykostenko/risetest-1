import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.management.Notification;

/**
 * Created by user nkorobicina on 28.12.2016.
 * методы, открывающие письма в почтовом ящике, вход в почтовый ящик
 */

public class TestMail extends BasePage
{
    public TestMail() throws InterruptedException
    {
        //ожидание прихода писем
        Thread.sleep(10000);
    }

    private static final String emailChangeNotification = "Уведомление о смене электронного адреса (логина)";
    private static final String emailChangeRequest = "Подтверждение смены электронного адреса (логина)";
    private static final String loginMail = "fsmnaumen@yandex.ru";
    private static final String passwdMail = "manager1";
    private static final String emailUserRegistration = "Приглашение в информационную систему формирования и распределения квоты приема иностранных студентов";
    private static final String passwordRecoveryMailHead = "Восстановление пароля russia.study / russia.study password restore";

    public String getPasswordRecoveryMailHead()
    {
        return passwordRecoveryMailHead;
    }

    public String getEmailUserRegistration()
    {
        return emailUserRegistration;
    }

    public String getEmailChangeNotification()
    {
        return emailChangeNotification;
    }

    public String getEmailChangeRequest()
    {
        return emailChangeRequest;
    }

    private Folder initInboxReadOnly() throws MessagingException
    {
        //устанавливаем свойства протокола
        Properties prop = System.getProperties();
        prop.setProperty("mail.store.protocol", "imaps");
//создаем сессию с выбранными свойствами почтового протокола
        Session session = Session.getInstance(prop, null);
        Store store = session.getStore();
        //соединяемся с почтовым сервером и открываем входящие
        store.connect("imap.yandex.ru", loginMail, passwdMail);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        return inbox;
    }


    /**
     * Метод получает текст последнего полученного письма
     */
    public String getLastMail() throws MessagingException, IOException
    {
        Folder inbox = initInboxReadOnly();
        //получаем последнее входящее письмо
        Message msg = inbox.getMessage(inbox.getMessageCount());
        Multipart mp = (Multipart) msg.getContent();
        BodyPart bp = mp.getBodyPart(0);
        Multipart mmp = (Multipart) bp.getContent();
        BodyPart bp0 = mmp.getBodyPart(0);
        //   BodyPart bp1 = mmp.getBodyPart(1); //тут лежит html-текст сообщения
        return (String)bp0.getContent();
    }

    /**
     * метод, получающий весь plain текст письма по его номеру
     */
    public String getPlainTextMail(int num) throws MessagingException, IOException
    {
        Folder inbox = initInboxReadOnly();
        //получаем последнее входящее письмо
        Message msg = inbox.getMessage(num);
        Multipart mp = (Multipart) msg.getContent();
        BodyPart bp = mp.getBodyPart(0);
        return (String)bp.getContent();
    }

    /**
     * метод получает plain-текст последнего письма в ящике
     */
    public String getPlainTextLastMail() throws MessagingException, IOException
    {
        return getPlainTextMail(getLettersCount());
    }

    /**
     * метод, получающий html-текст письма по его номеру
     */
    public String getHtmlTextMail(int num) throws MessagingException, IOException
    {
        Folder inbox = initInboxReadOnly();
        //получаем последнее входящее письмо
        Message msg = inbox.getMessage(num);
        Multipart mp = (Multipart) msg.getContent();
        BodyPart bp = mp.getBodyPart(1);
        return (String)bp.getContent();
    }

    /**
     * метод получает html-текст последнего письма в ящике
     */
    public String getHtmlTextLastMail() throws MessagingException, IOException
    {
        return getHtmlTextMail(getLettersCount());
    }


    /**
     * есть ли новое сообщение
     */
    public boolean ifHasNewMessage() throws MessagingException
    {
        Folder inbox = initInboxReadOnly();

        if (inbox.hasNewMessages())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Метод, получающий заголовок письма по номеру письма в ящике
     */
    public String getSubjectMail(int num) throws MessagingException
    {
        Folder inbox = initInboxReadOnly();
        Message msg = inbox.getMessage(num);
        //     log("SUBJECT: " + msg.getSubject());
        return msg.getSubject();
    }


    /**
     *     Метод, получающий заголовок последнего письма в ящике
     */
    public String getSubjectLastMail()throws MessagingException
    {
        Folder inbox = initInboxReadOnly();
        Message msg = inbox.getMessage(inbox.getMessageCount());
        //       log("SUBJECT: " + msg.getSubject());
        return msg.getSubject();

    }

    /**
     * Количество писем в ящике
     */
    public int getLettersCount() throws MessagingException
    {
        Folder inbox = initInboxReadOnly();
        return inbox.getMessageCount();
    }

    /**
     * Метод, который находит адресата тестового письма по номеру письма
     */
    public String getAddresseeMail(int num) throws IOException, MessagingException
    {
        String plainText = getPlainTextMail(num);
        String Addresse = "";
        Pattern pattern = Pattern.compile("Кому: (.*)");
        Matcher matcher = pattern.matcher(plainText);
        if (matcher.find())
        {
            Addresse = matcher.group(1);
        }
        else
        {
            log("Не нашел адресата");
        }
        //       log("Адресат письма: " + Addresse);
        return Addresse;

    }

    /**
     *     Метод получает адресата последнего письма в ящике
     */
    public String getAddresseeLastMail() throws MessagingException, IOException
    {
        return getAddresseeMail(getLettersCount());
    }


    /**
     * получает кусок текста из письма по тексту письма и по предшествующему и последующему тексту
     */
    public String getTextMail(String text, String before, String after) throws IOException, MessagingException
    {
        String result = "";
        Pattern pattern = Pattern.compile(before + "(.*)" + after);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            result = matcher.group(1);
        }
        else
        {
            log("Не нашли текст в письме");
        }
//        log("Требуемый текст из письма: " + result);
        return result;
    }

    /**
     * метод получает кусок текста из письма по предыдущему тексту и по количеству нужных символов
     */
    public String getTextMailByNumberOfLetters(String text, String before, int num)
    {
        String result = "";
        Pattern pattern = Pattern.compile(before + "(.{"+ num + "})");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            result = matcher.group(1);
            //     log(result);
        }
        else
        {
            log("Не нашли текст в письме");

        }
        //       log("Требуемый текст из письма: " + result);
        return result;
    }

    /**
     * берет ссылку и обрезает кавычки
     */
    public String getLinkFromMail(String text) throws IOException, MessagingException
    {
        String link = getTextMail(text, "пройдите по следующей <a href=", ">ссылке</a>");
        if(!link.isEmpty())
            link = link.substring(1, link.length()-1);
        log("Ссылка из письма: " + link);
        return link;
    }

    /**
     * выдает ссылку из последнего найденного письма
     */
    public String getLinkFromLastMail() throws IOException, MessagingException
    {
        return getLinkFromMail(getHtmlTextLastMail());
    }

    /**
     * Метод, проверяющий заголовок последнего письма в ящике
     */
    public boolean isSubjectCorrect(String subject) throws MessagingException
    {
        String subjectLastMail = getSubjectLastMail();
        if(subjectLastMail.equals(subject))
        {
            return true;
        }
        else return false;
    }

    /**
     * Метод проверяет адресата последнего письма в ящике
     */
    public boolean isAddresseeCorrect(String addressee) throws MessagingException, IOException
    {
        String addresseeLastMail = getAddresseeLastMail();
        if(addresseeLastMail.equals(addressee))
        {
            return true;
        }
        else return false;
    }

    /**
     * Проверяет является ли последнее письмо уведомлением или подтверждением смены логина
     */
    public int checkLastMail (String lastMail, int logErrors, String actualMail, String requestMail, String notificationMail) throws MessagingException
    {
        if (actualMail = notificationMail)
            lastMail = "Уведомление";
        if (actualMail = requestMail)
                lastMail = "Подтверждение";
        else
        {
            logErrors++;
            log("ОШИБКА: Последние письмо не уведомление и не подверждение смены логина");
        }

        return logErrors;
    }

    /**
     * Проверяет является ли предпоследнее письмо уведомлением или подтверждением смены логина
     */
    public int checkPenultMail (String penultMail, int logErrors, int lettersCount) throws MessagingException
    {
        lettersCount = getLettersCount();

        if (getSubjectMail(lettersCount - 1))

    }
}

