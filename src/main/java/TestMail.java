import javax.mail.*;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

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
    private static final String emailUserRegistration = "Регистрация на russia.study / russia.study Registration";
    private static final String passwordRecoveryMailHead = "Восстановление пароля russia.study / russia.study password restore";

    public String getPasswordRecoveryMailHead()
    {
        return passwordRecoveryMailHead;
    }

    public String getEmailUserRegistration() { return emailUserRegistration; }

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
     * берет ссылку из письма для подтверждения регистрации
     */
    public String getLinkFromMailForRegistration(String text) throws IOException, MessagingException
    {
        String link = getTextMail(text, "перейдите по <a href=", ">ссылке</a>");
        if(!link.isEmpty())
            link = link.substring(1, link.length()-1);
        log("Ссылка из письма: " + link);
        return link;
    }

    /**
     * берет ссылку из письма, которое подтверждает успешную регистрацию
     */
    public String getLinkFromMailOfSuccessfilRegistration(String text) throws IOException, MessagingException
    {
        String link = getTextMail(text, "Спасибо, вы успешно зарегистрированы на <a href=", ">официальном сайте для отбора иностранных граждан на обучение в Российской Федерации</a>");
        if(!link.isEmpty())
            link = link.substring(1, link.length()-1);
        log("Ссылка из письма: " + link);
        return link;
    }

    /**
     * Выдает ссылку на главную страницу сайта из последнего письма, которое подверждает успешную регистрацию
     */
    public String getLinkFromLastMailOfSuccessfilRegistration() throws IOException, MessagingException
    {
        return getLinkFromMailOfSuccessfilRegistration(getHtmlTextLastMail());
    }

    /**
     * выдает ссылку для подтверждения регистрации из последнего найденного письма
     */
    public String getLinkFromLastMailForRegistration() throws IOException, MessagingException
    {
        sleep(5000);
        return getLinkFromMailForRegistration(getHtmlTextLastMail());
    }

    /**
     * выдает ссылку из последнего найденного письма
     */
    public String getLinkFromLastMail() throws IOException, MessagingException
    {
        return getLinkFromMail(getHtmlTextLastMail());
    }

    /**
     * выдает ссылку из предпоследнего письма
     */
    public String getLinkFromPenultMail() throws IOException, MessagingException
    {
        int letterCount = getLettersCount();
        return getLinkFromMail(getHtmlTextMail(letterCount -1));
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
     *
     */

    /**
     * Проверяет последнее и предпоследнее письмо на наличие в нем уведомления и подтверждения смены пароля, переходит по ссылке для смены
     */
    public boolean checkMailAndChangeLogin (String newLogin) throws MessagingException, IOException
    {
        PageUserAccount pageUserAccount = new PageUserAccount();
        boolean checkMail = false;
        int i = 0;

        String lastActualMailName = getSubjectLastMail();
        int lettersCount = getLettersCount();
        String penultActualMailName = getSubjectMail(lettersCount - 1);
        String expectedNotificationLetter = getEmailChangeNotification();
        String expectedRequestLetter = getEmailChangeRequest();

        while ((i < 4)&&(!checkMail))
        {
            log("Вводим новый логин");
            pageUserAccount.fillNewLogin(newLogin);
            log("Нажимаем кнопку Сохранить");
            pageUserAccount.clickSaveLogin();
            sleep(10000);

            if (penultActualMailName.equals(expectedRequestLetter))
            {
                log("Подтверждение найдено в предпоследнем письме");
                checkMail = true;
                log("Находим ссылку из предпоследнего письма в ящике");
                String linkRecovery = getLinkFromPenultMail();
                log("Переходим по ссылке");
                open(linkRecovery);
                if (lastActualMailName.equals(expectedNotificationLetter))
                {
                    log("Уведомление найдено в последнем письме");
                } else
                    {
                        log("Уведомление не найдено в последнем письме");
                        checkMail = false;
                    }
            } else if (lastActualMailName.equals(expectedRequestLetter))
            {
                log("Подтверждение найдено в последнем письме");
                checkMail = true;
                log("Находим ссылку из предпоследнего письма в ящике");
                String linkRecovery = getLinkFromLastMail();
                log("Переходим по ссылке");
                open(linkRecovery);

                if (penultActualMailName.equals(expectedNotificationLetter))
                {
                    log("Уведомление найдено в предпоследнем письме");
                } else
                    {
                        log("Уведомление не найдено в предпоследнем письме");
                        checkMail = false;
                    }
            } else
            {
                log("Все письма для смены логина не были получены");
                checkMail = false;
            }
            sleep(1000);
            i++;
        }
        return checkMail;
    }

    /**
     * Регистрация кандидата и её подтверждение, в случае неполученных писем регистрация перезапускается
     */
    public String independentRegistrationCandidate(String userLastName, String userFirstName, String userMiddleName, String userSex, String userCountry, String userPassword,
                                                   boolean contract, boolean partial)
            throws MessagingException, IOException, InterruptedException
    {

        PageTopBottom pageTopBottom = new PageTopBottom();
        PageRegistration pageRegistration = new PageRegistration();
        TestRandomUserData testRandomUserData = new TestRandomUserData();
        PageMain pageMain = new PageMain();
        TestMail testMail = new TestMail();
        HomePageControl homePageControl = new HomePageControl();

        int i = 0;
        boolean conditionsFulfilled = false;
        String randomEmail = null;

        while ((i < 4)&&(!conditionsFulfilled))
        {

            log("Создаём рандомный email для регистрации кандитата");
            randomEmail = String.valueOf(testRandomUserData.createRandomEmail());
            log(randomEmail);

            log("Переходим на страницу регитсрации кандидата");
            if (!contract)
                pageTopBottom.goToRegistration();
            else
            {
                log("Переходим на главную страницу");
                pageTopBottom.goToHomePage();
                pageMain.goToRegistrationFromBlockContractTraining();
            }

            if (partial)
            {
                log("Заполняем обязательные поля для регистрации");
                pageRegistration.partialFillingRegistrationForm(userLastName, userFirstName, userSex, userCountry, randomEmail, userPassword);
            } else
            {
                log("Заполняем все поля для регитсрации (кроме полей, связанных с агентами)");
                pageRegistration.fullFillingRegistrationForm(userLastName, userFirstName, userMiddleName,  userSex, userCountry, randomEmail, userPassword);
                sleep(100000);
            }

            log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
            String subjectRegistrationMail = testMail.getEmailUserRegistration();
            if (!testMail.isSubjectCorrect(subjectRegistrationMail))
            {
                log("Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationMail);
                conditionsFulfilled = false;
            } else {
                conditionsFulfilled = true;
                if (!testMail.isAddresseeCorrect(randomEmail)) {
                    log("Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);
                    conditionsFulfilled = false;
                } else {
                    log("Письмо для подтверджения регистрации получено");

                    log("Находим ссылку из последнего письма в ящике");
                    String linkRegistration = testMail.getLinkFromLastMailForRegistration();

                    open(linkRegistration);

                    log("Проверяем письмо на почте, подтверждающее регистрацию");
                    log("Проверяем, что последнее письмо в ящике - письмо о регистрации правильному адресату");
                    String subjectRegistrationConfirmMail = testMail.getEmailUserRegistration();

                    if (!testMail.isSubjectCorrect(subjectRegistrationConfirmMail)) {
                        log("Ошибка: неправильный заголовок последнего письма - " + testMail.getSubjectLastMail() + ". Ожидался: " + subjectRegistrationConfirmMail);
                        conditionsFulfilled = false;
                    } else {
                        if (!testMail.isAddresseeCorrect(randomEmail)) {
                            log("Ошибка: неправильный адресат в последнем письме - " + testMail.getAddresseeLastMail() + ". Ожидался: " + randomEmail);
                            conditionsFulfilled = false;
                        } else {
                            log("Письмо, подтверждающее регистрацию получено");
                            log("Находим ссылку из последнего письма, подтверждающего успешную регистрацию");
                            String linkFromMailOfSuccessfulRegistration = testMail.getLinkFromLastMailOfSuccessfilRegistration();
                            open(linkFromMailOfSuccessfulRegistration);

                            log("Проверяем что открылась главная страница сайта");
                            if (!homePageControl.isHomePage()) {
                                log("Главная не открылась");
                                conditionsFulfilled = false;
                            }
                        }
                    }
                }
            }
            i++;
        }

        if (!conditionsFulfilled)
            randomEmail = null;

        return randomEmail;
    }


    /**
     * Проверка письма о восстановлении пароля пользователя
     */
    public boolean checkRecoveryMail(String userLogin) throws InterruptedException, MessagingException, IOException
    {
        PageTopBottom pageTopBottom = new PageTopBottom();
        PageLogin pageLogin = new PageLogin();
        PagePasswordRecovery pagePasswordRecovery = new PagePasswordRecovery();

        boolean checkRecoveryMail = false;
        int i = 0;

        while ((!checkRecoveryMail)&&(i < 4))
        {
            log("Переходим на форму логина");
            pageTopBottom.goToLogin();

            log("Нажимаем на ссылку 'Забыли пароль?'");
            pageLogin.goToRecoveryPage();

            log("Заполняем поле Email");
            pagePasswordRecovery.fillEmail(userLogin);

            log("Нажимаем кнопку Отправить");
            pagePasswordRecovery.clickSendEmail();

            sleep(10000);

            log("Проверяем заголовок последнего письма в отладочном почтовом ящике");
            String subjectRecoveryMail = getPasswordRecoveryMailHead();
            if (!isSubjectCorrect(subjectRecoveryMail)) {
                log("Ошибка: неправильный заголовок последнего письма - " + getSubjectLastMail() + ". Ожидался: " + subjectRecoveryMail);
            } else {
                log("Проверяем адресата письма");
                if (!isAddresseeCorrect(userLogin)) {
                    log("Ошибка: неправильный адресат в последнем письме - " + getAddresseeLastMail() + ". Ожидался: " + userLogin);
                } else checkRecoveryMail = true;
            }
            i++;
        }
        return checkRecoveryMail;
    }

}

