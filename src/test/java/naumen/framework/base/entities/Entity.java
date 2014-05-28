package naumen.framework.base.entities;

import com.rits.cloning.Cloner;
import naumen.framework.base.BaseEntity;
import naumen.framework.base.Logger;
import org.testng.SkipException;

import java.io.*;
import java.util.HashMap;

/**
 * Базовый класс для сущностей бизнес логики
 *
 */
@SuppressWarnings("serial")
public class Entity extends BaseEntity implements Serializable, Cloneable {

	@Override
	protected final String formatLogMsg(String message) {
		return "Сущность";
	}

	/**
	 * Базовый
	 */
	public Entity() {
		prefix = this.getClass().getSimpleName();
	}

	/**
	 * Конструктор + экстра префикс
	 * @param prefixExtra prefixExtra
	 */
	public Entity(String prefixExtra) {
		prefix = this.getClass().getSimpleName() + "_" + prefixExtra;
	}


	/** Возвращает значение поля
	 * @param item поле
	 * @return значение
	 */
	public final String getValue(Enum<?> item){
		String value = map.get(item.toString());
		System.out.println("Получение данных " + item.toString() + ":[" + value +"]");
		return value;
	}

	private HashMap<String, String> map = new HashMap<String, String>();

	/** возвращает HashMap
	 * @return HashMap
	 */
	public HashMap<String, String> getMap() {
		return map;
	}

	/** присваивает map
	 * @param m map
	 */
	@SuppressWarnings("unchecked")
	public void setMap(HashMap<String, String> m) {
		map = (HashMap<String, String>)m.clone();
	}

	private String prefix = "";

	/**
	 * getPrefix
	 * @return String
	 */
	public final String getPrefix() {
		return prefix;
	}

	/**
	 * setPrefix
	 * @param pr pr
	 */
	public final void setPrefix(String pr) {
		prefix = this.getClass().getSimpleName() + "_" + pr;
	}

	/** Устанавливает значение для поля
	 * @param item поле
	 * @param value значение
	 */
	public final void setValue(Enum<?> item, String value){
		System.out.println("Внесение данных " + item.toString() + ":[" + value + "]");
		map.put(item.toString(), value);
	}

	/** Сериализация основных сущностей
	 */
	public final void serialize(){
		try{
			FileOutputStream fileOut = new FileOutputStream("../" + this.getPrefix() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}catch(IOException i){
			logger.info("Возникла ошибка при сериализации объекта " + i.getStackTrace());
		}
	}

	/** удаляет серилизационный файл
	 */
	public final void remove(){
		File file = new File("../" + this.getPrefix() + ".ser");
		if(file.exists()){
			if(file.delete()){
				logger.info("Файл " + "../" + this.getPrefix() + ".ser был успешно удален");
			}else{
				logger.info("Файл " + "../" + this.getPrefix() + ".ser не был удален");
			}
		}
	}

	/** Десериализирует объект из файла
	 * @return сущность
	 */
	public Entity getEntity(){
		try{
			String path = "../" + this.getPrefix() + ".ser";
			File file = new File(path);
			path = file.getAbsolutePath();
			FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object obj = in.readObject();
            in.close();
            fileIn.close();
            this.setMap(((Entity)obj).getMap());
            return (Entity) obj;
        }catch(IOException i){
            throw new SkipException(Logger.convertSimple("Не удалось десериализировать файл " + i.toString()));
        }catch(ClassNotFoundException c){
        	throw new SkipException(Logger.convertSimple("Не удалось десериализировать файл " + c.toString()));
        }
	}

	/** инициализирует сущность из файла
	 * @param entity сущость
	 */
	public static void getEntity(Entity entity){
		try{
			String path = "../" + entity.getPrefix() + ".ser";
			File file = new File(path);
			path = file.getAbsolutePath();
			FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object obj = in.readObject();
            in.close();
            fileIn.close();
            //entity.setMap(((Entity)obj).getMap());
            entity = (Entity) obj;
        }catch(IOException i){
            throw new SkipException("Не удалось десериализировать файл " + i.toString());
        }catch(ClassNotFoundException c){
        	throw new SkipException("Не удалось десериализировать файл " + c.toString());
        }
	}

	/** Делает клон объекта
	 * @return клон объекта
	 */
	@Override
	public Object clone(){
		try{
			Entity e = (Entity)super.clone();
			Cloner cloner=new Cloner();
			Entity clone = cloner.deepClone(e);
			//e.map = new HashMap<String, String>();
			//e.map.putAll(this.getMap());
			return clone;
		}catch(CloneNotSupportedException e){
			logger.fatal("Не удалось склонировать сущность");
			return null;
		}
	}
}
