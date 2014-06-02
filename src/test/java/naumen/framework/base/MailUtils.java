package naumen.framework.base;

import junit.framework.Assert;
import naumen.NaumenTest;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/** work with mail
 */
public class MailUtils {

	private String host, username, password;
	private Properties properties = new Properties();
	private MAIL_PROTOCOLS protocol;
	private final long timeToWaitMail = 24000;
	private Store store;

	/** construct mail connector
	 * @param host host
	 * @param username username
	 * @param password password
	 * @param protocol protocol of mail server
	 */
	public MailUtils(String host, String username, String password, MAIL_PROTOCOLS protocol){
		this.host = host;
	    this.username = username;
	    this.password = password;
	    this.protocol = protocol;
	    store = connect();
	}

	/** default connect
	 */
	public MailUtils(){
//		this("imap.gmail.com", NaumenTest.getRedirectEmail(), NaumenTest.getRedirectEmailPass(), MAIL_PROTOCOLS.IMAP);
	}

	/** construct mail connector
	 * @param host host
	 * @param username username
	 * @param password password
	 * @param protocol protocol of mail server
	 * @param properties properties
	 */
	public MailUtils(String host, String username, String password, MAIL_PROTOCOLS protocol, Properties properties){
		this(host, username, password, protocol);
		this.properties = properties;
	}

	/** available protocols
	 */
	public enum MAIL_PROTOCOLS{
		POP3("pop3"), SMPT("smpt"), IMAP("imaps");

		private String protocol;

		/** constructor
		 * @param name mail protocol name
		 */
		MAIL_PROTOCOLS(String name){
			protocol = name;
		}

		@Override
		public String toString() {
			return protocol;
		}
	}

	/**
	 * @param folderName name of folder in mailbox
	 * @param permissions permissions for access to folder(user Folder.READ_ONLY and e.i.)
	 * @param folder folder
	 * @return messages
	 */
	public Message[] getMessage(String folderName, int permissions, Folder folder){
		// Get folder
		Message[] messages = null;
    	try {
			folder.open(Folder.READ_ONLY);
			// Get directory
			messages = folder.getMessages();
		} catch (MessagingException e) {
			Logger.getInstance().warn("Impossible to get messages: " + e.getMessage());
			e.printStackTrace();
		}
	    return messages;
	}

	/** Get link from the letter
	 * @param subject subject
	 * @return link
	 */
	public String getMessageContent(String subject){
		try {
			Multipart part = (Multipart) waitForLetter(subject).getContent();
			return (String) part.getBodyPart(0).getContent();
			//link = CommonFunctions.regexGetMatchGroup((String) part2.getBodyPart(0).getContent(), "(http://smail.railsware.*)\"", 1);
		} catch (IOException e) {
			Logger.getInstance().warn("It is impossible to get conetent of message: " + e.getMessage());
		} catch (MessagingException e) {
			Logger.getInstance().warn("It is impossible to get content of message: " + e.getMessage());
		}
		return null;
	}

	/** Get link from the letter
	 * @param subject subject
	 * @param text text
	 * @return link
	 */
	public String getMessageContent(String subject, String text){
		try {
			Multipart part = (Multipart) waitForLetter(subject, text).getContent();
			return (String) part.getBodyPart(0).getContent();
			//link = CommonFunctions.regexGetMatchGroup((String) part2.getBodyPart(0).getContent(), "(http://smail.railsware.*)\"", 1);
		} catch (IOException e) {
			Logger.getInstance().warn("It is impossible to get content of message: " + e.getMessage());
		} catch (MessagingException e) {
			Logger.getInstance().warn("It is impossible to get content of message: " + e.getMessage());
		}
		return null;
	}

	/** wait for letter with necessary subject is present in mailbox
	 * @param subject subject of letter
	 * @return message
	 * @throws MessagingException MessagingException
	 */
	private Message waitForLetter(String subject) throws MessagingException{
		Message[] messages = null;
		// waiting
		long start = System.currentTimeMillis();
		do{
			Folder folder = store.getFolder("INBOX");
			messages = getMessage(folder);
			for (Message m : messages) {
				try {

                    if(m.getSubject().contains(subject)){
						return m;
					}
				} catch (MessagingException e) {
					Logger.getInstance().warn("It is impossible to get subject of message: " + e.getMessage());
				}
			}
			try {
				folder.close(false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}while((start + timeToWaitMail) >= System.currentTimeMillis());
		Logger.getInstance().fatal(String.format("Mailbox not contains letter with subject '%1$s'. There was waiting: %2$s mills", subject, timeToWaitMail));
		return null;
	}

	/** wait for letter with necessary subject and address is present in mailbox
	 * @param subject subject of letter
	 * @param text text that message contains
	 * @return message
	 * @throws MessagingException MessagingException
	 */
	private Message waitForLetter(String subject, String text) throws MessagingException{
		Message[] messages = null;
		// waiting
		long start = System.currentTimeMillis();
		do{
			Folder folder = getConnectedMail().getFolder("INBOX");
			messages = getMessage(folder);
			for (Message m : messages) {
				try {
					String content = (String) ((Multipart) m.getContent()).getBodyPart(0).getContent();
					if(m.getSubject().contains(subject) && content.contains(text)){
						return m;
					}
				} catch (Exception e) {
					Logger.getInstance().warn("It is impossible to get subject of message: " + e.getMessage());
				}
			}
			try {
				folder.close(false);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}while((start + timeToWaitMail) >= System.currentTimeMillis());
		Logger.getInstance().fatal(String.format("Mailbox not contains letter with subject '%1$s'. There was waiting: %2$s mills", subject, timeToWaitMail));
		return null;
	}

	/**
	 * Возвращает подключенную сессию
	 * @return
	 */
	private Store getConnectedMail() {
		if (store.isConnected()) {
			return store;
		} else {
			return connect();
		}

	}

	/** by default folder "INBOX" and permissions Folder.READ_ONLY
	 * @param folder folder
	 * @return messages
	 */
	public Message[] getMessage(Folder folder){
		return getMessage("INBOX", Folder.READ_ONLY, folder);
	}

	/** connect to mailbox
	 * @return Store
	 */
	private Store connect(){
		// Get session
		properties.setProperty("mail.store.protocol", protocol.toString());
		Session session = Session.getDefaultInstance(properties, null);
	    // Get the store
	    try {
	    	store = session.getStore(protocol.toString());
	    	store.connect(host, username, password);
	    } catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return store;
	}

	/** Удаляет все сообщения с ящика
	 * @param folderName название папки с письмами(например "INBOX")
	 */
	public void deleteAllMessages(String folderName){
		//Проверка, что соединение установлено
		Assert.assertNotNull("Не удалось установить соединения с почтовым ящиком.", store);
		try{
			Folder inbox = store.getFolder(folderName);
			inbox.open(Folder.READ_WRITE);
			//Получаем все сообщения из папки
			Message[] messages = inbox.getMessages();
			for(Message message:messages) {
				message.setFlag(Flags.Flag.DELETED, true);
			}
			inbox.close(true);
			//Проверяем что все сообщения удалены
			Assert.assertEquals("Все сообщения не были удалены c " +  username + ".", 0, inbox.getMessageCount());
			Logger.getInstance().info("Все сообщения были успешно удалены c " + username);
		}catch(MessagingException e){
			Logger.getInstance().warn("Ошибка чтения сообщения: " + e.getMessage());
		}
	}

    /**
     * удаляет сообщения с темой subject из ящика
     * @param from - адрес, с которого пришли письма (например, хотим удалить только письма, относящиеся к стенду автотестов)
     * @param subject тема сообщений, которые хотим удалить
     */
    public void deleteMessagesFromInbox(String from, String subject){
        //Проверка, что соединение установлено
        Assert.assertNotNull("Не удалось установить соединения с почтовым ящиком.", store);
        try{
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            //Получаем все сообщения из папки
            Message[] messages = inbox.getMessages();
            for(Message message:messages) {
                if (message.getSubject().contains(subject)){
                    for(Address adr:  message.getFrom()){
                        if (adr.toString().contains(from)){
                            message.setFlag(Flags.Flag.DELETED, true);
                            break;
                        }
                    }
                }
            }
            inbox.close(true);
            Logger.getInstance().info("Все сообщения с темой '" + subject + "' и адресом отправления '" + from + "' были успешно удалены c " + username);
        }catch(MessagingException e){
            Logger.getInstance().warn("Ошибка чтения сообщения: " + e.getMessage());
        }
    }

	/** close store
	 */
	public void closeStore(){
		try {
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
