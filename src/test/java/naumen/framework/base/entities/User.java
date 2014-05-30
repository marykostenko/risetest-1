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
        NAME("Имя"), SURNAME("Фамилия");

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

    public String getEmail(){
        return this.getValue(USER.EMAIL);
    }

    public String getPassword(){
        return this.getValue(USER.PASSWORD);
    }

    public String getName(){
        return this.getValue(USER.NAME);
    }

    public String getSurname(){
        return this.getValue(USER.SURNAME);
    }
}
