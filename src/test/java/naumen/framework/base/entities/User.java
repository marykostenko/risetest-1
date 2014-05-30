package naumen.framework.base.entities;

import java.io.Serializable;

/** Описывает сущность - пользователь
 */
@SuppressWarnings("serial")
public class User extends Entity implements Serializable, Cloneable{

	/** Перечень полей
	 */
	public enum USER{
		EMAIL("E-mail"), PASSWORD("Пароль"),
        FIRSTNAME("Имя"), MIDDLENAME("Отчество"),
        LASTNAME("Фамилия");

		private String val;

		/** Устанавливает название элементу перечисления
		 * @param name название
		 */
		USER(String name) {
			val = name;
		}

		@Override
		public String toString() {
			return val;
		}
	}

	/** конструктор
	 * @param prefix префикс к сериализационному файлу
	 */
	public User(String prefix) {
		super(prefix);
	}

	/** конструктор
	 */
	public User() {
		super();
	}

    /** конструктор (без Отчества)
     */
    public User(String email, String password, String fname, String lname) {
        super();
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(fname);
        this.setMiddleName("");
        this.setLastName(lname);
    }

    /** конструктор (все данные)
     */
    public User(String email, String password, String fname, String mname, String lname) {
        super();
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(fname);
        this.setMiddleName(mname);
        this.setLastName(lname);
    }

    public void setEmail(String email){
        this.setValue(USER.EMAIL, email);
    }

    public void setPassword(String password){
        this.setValue(USER.PASSWORD, password);
    }

    public void setFirstName(String fname){
        this.setValue(USER.FIRSTNAME, fname);
    }

    public void setMiddleName(String mname){
        this.setValue(USER.MIDDLENAME, mname);
    }

    public void setLastName(String lname){
        this.setValue(USER.LASTNAME, lname);
    }

    public String getEmail(){
        return this.getValue(USER.EMAIL);
    }

    public String getPassword(){
        return this.getValue(USER.PASSWORD);
    }

    public String getFirstName(){
        return this.getValue(USER.FIRSTNAME);
    }

    public String getMiddleName(){
        return this.getValue(USER.MIDDLENAME);
    }

    public String getLastName(){
        return this.getValue(USER.LASTNAME);
    }
}
