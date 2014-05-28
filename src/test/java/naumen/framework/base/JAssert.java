package naumen.framework.base;

import junit.framework.Assert;

/**
 * Обертка для JUnit assert
 */
public class JAssert {
	private Logger logger = Logger.getInstance();
	private final String messageTemplate = "%s содержит ";
	private final String incorrectM = "некорректный текст: ";
	private final String correctM = "корректный текст: ";
	private static JAssert instance = null;

	/**
	 * @param entityName название сущности, текст внутри которой проверяется. Например: Колонка или Поле
	 * @param expected ожидаемый текст
	 * @param actual актуальный текст
	 * @param printSuccessResult устанавливается в true, если есть необходимость выводить сообщение о корректном тексте в сущности
	 */
	public final void assertText(final String entityName, final String expected,  String actual, final Boolean printSuccessResult, BaseTest test){
		if (!expected.equals(actual)) {
			test.warn();
			logger.warn(String.format(messageTemplate, entityName) + incorrectM + actual + " ; Ожидалось: " + expected);
		} else {
			if(printSuccessResult){
				logger.info(String.format(messageTemplate, entityName) + correctM + "'" + expected + "'"); "3\\(действующая\\)".equals(actual);
			}
		}
	}

	/**
	 * @param entityName название сущности, текст внутри которой проверяется. Например: Колонка или Поле
	 * @param expected ожидаемый текст
	 * @param actual актуальный текст
	 */
	public final void assertTextCustomLogs(final String entityName, final String expected,  String actual, BaseTest test){
		if (!expected.equals(actual)) {
			test.warn();
			logger.warn(String.format("%s \t.......... ОШИБКА!", entityName) + "  ---->Ожидалось: " + expected + "  ---->а было: " + actual);
		} else {
			logger.info(String.format("%s \t.......... ОК!", entityName) + String.format(" (%s) ",actual)) ;
		}
	}

	/**
	 * @param entityName название сущности, текст внутри которой проверяется. Например: Колонка или Поле
	 * @param expected ожидаемый текст
	 * @param actual актуальный текст
	 * @param printSuccessResult устанавливается в true, если есть необходимость выводить сообщение о корректном тексте в сущности
	 */
	public final void assertTextContains(final String entityName, final String expected,  final String actual, final Boolean printSuccessResult, BaseTest test){
		if (!actual.contains(expected)) {
			logger.warn(String.format(messageTemplate, entityName) + incorrectM + actual + "; Ожидалось наличие текста: " + expected);
			test.warn();
		} else {
			if(printSuccessResult){
				logger.info(String.format(messageTemplate, entityName) + correctM + expected);
			}
		}
	}

	/**
	 * @param entityName название сущности, текст внутри которой проверяется. Например: Колонка или Поле
	 * @param expected ожидаемый текст
	 * @param actual актуальный текст
	 * @param printSuccessResult устанавливается в true, если есть необходимость выводить сообщение о корректном тексте в сущности
	 */
	public final void assertTextContains(final String entityName, final String[] expected,  final String actual, final Boolean printSuccessResult, BaseTest test){
		for(int i = 0; i < expected.length; i++){
			assertTextContains(entityName,expected[i],actual,printSuccessResult, test);
		}
	}

	/** Проверяет что актуальное значение верное
	 * @param errorMessage сообщение если не true
	 * @param successMessage сообщение если true
	 * @param actual актуальное значение
	 * @param printSuccessResult выводить инфу об успешной проверке в лог?
	 */
	public final void assertTrue(final String errorMessage, final String successMessage, final Boolean actual, final Boolean printSuccessResult, BaseTest test){
		if(actual){
			if(printSuccessResult){
				logger.info(successMessage);
			}
		}else{
			logger.warn(errorMessage);
			test.warn();
		}
	}

	/** Проверяет что актуальное значение верное
	 * @param errorMessage сообщение если не true
	 * @param successMessage сообщение если true
	 * @param actual актуальное значение
	 */
	public final void checkIsTrue(final String errorMessage, final String successMessage, final Boolean actual, BaseTest test){
		if(actual){
			logger.info(successMessage);
		}else{
			logger.warn(errorMessage);
			test.warn();
		}
	}

	/** Проверяет что актуальное значение неверное
	 * @param errorMessage сообщение если не false
	 * @param successMessage сообщение если false
	 * @param actual актуальное значение
	 * @param printSuccessResult выводить инфу об успешной проверке в лог?
	 */
	public final void assertFalse(final String errorMessage, final String successMessage, final Boolean actual, final Boolean printSuccessResult){
		Assert.assertFalse(errorMessage, actual);
		if(printSuccessResult){
			logger.info(successMessage);
		}
	}

	/** Проверяет что отклонение по времени не является критичным
	 * @param template шаблон строки с времение, например "Время %s"
	 * @param times массив времен-отклонения
	 * @param actualValue актуальный результат, например Время 15:22
	 * @param entityName наименование проверяемого поля
	 */
	public void assertTime(String template, String[] times, String actualValue, String entityName, BaseTest test){
		logger.info("");
		for(int i = 0; i < times.length; i++){
			if(actualValue.contains(String.format(template, times[i]).replace(" (МСК)", ""))){
				logger.info("Дата и время создания ЭП корректны: " + String.format(template, times[i]).replace(" (МСК)", ""));
				//assertTextContains(entityName, String.format(template, times[i]), actualValue, false);
				return;
			}
		}
		assertTextContains(entityName, String.format(template, times[0]), actualValue, true, test);
	}

	/**
	 * Возвращает статический экзепляр класса
	 * @return статический экзепляр класса
	 */
	public static JAssert getInstance(){
		if(instance == null){
			instance = new JAssert();
		}
		return instance;
	}

}
