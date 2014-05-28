package naumen;

import com.google.common.base.Strings;
import naumen.framework.base.BaseTest;
import naumen.framework.base.Browser;
import naumen.framework.base.CommonFunctions;
import naumen.framework.base.elements.Button;
import naumen.framework.base.entities.User;
import naumen.framework.base.entities.User.USER;
import naumen.framework.base.exceptions.*;
import naumen.framework.forms.ChooseFiltersForm;
import naumen.framework.forms.LoginForm;
import net.lightbody.bmp.core.har.Har;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Base test class
 *
 */
public abstract class NaumenTest extends BaseTest {
	protected static String userLogin, userPassword;
    protected static final int ReportLoadingTimeoutSec = 15;
//    protected static final double tol = 0.0019;
    protected static final double tol = 0.009;
	protected static User user = new User();
    protected JSONObject docTree;
    protected static String docTreeFileName;
    protected static final String configuratorFileNameUTF8 = System.getProperty("user.dir") + "/../src/test/resources/ConfiguratorUTF8.json";
    protected static final String configuratorFileNameWin1251 = System.getProperty("user.dir") + "/../src/test/resources/ConfiguratorWin1251.json";
    protected JSONObject filters = new JSONObject();
    protected Random rand = new Random();

    protected enum ReportType {
        TABLE("table"),
        CHART("chart"),
        SCHEDULER("scheduler"),
        FOLDER("folder");

        private String val;
        /** Устанавливает название элементу перечисления
         * @param name название
         */
        ReportType(String name) {
            val = name;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    /** возвращает пользователя
	 * @return пользователь
	 */
	public static User getUser(){
		return user;
	}

    /**
     * получить все фильтры из конфигуратора
     * @return JSONObject фильтров
     */
    public JSONObject getFilters(){
        return filters;
    }

    /**
     * получить фильтры отчета из конфигуратора
     * @param st sectionTitle
     * @param ft folderTitle
     * @param rt reportTitle
     * @return фильтры отчета, если найдены, либо пустой json
     */
    public JSONObject getFilters(String st, String ft, String rt){
        JSONObject fltrs;
        try{
            fltrs = (JSONObject)filters.get(st);
            if (!ft.equals("")){
                fltrs = (JSONObject)((JSONObject)fltrs.get(ft)).get("items");
            }
            fltrs = (JSONObject)fltrs.get(rt);
            int ind = ((Long)fltrs.get("index")).intValue();
            return (JSONObject)((JSONArray)fltrs.get("items")).get(ind);
        }
        catch(NullPointerException npe){
            logger.notify("Для отчета '" + rt + "' не найдено фильтров в конфигураторе");
            return new JSONObject();
        }
    }

    /**
     * установка фильтров из файла конфигуратора
     */
    public void setFilters(){
        String configuratorFileName;
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            configuratorFileName = configuratorFileNameWin1251;
        else
            configuratorFileName = configuratorFileNameUTF8;
        try {

            filters = getJsonFromFile(configuratorFileName);
        }
        catch(Exception e){
            logger.warn("Не удалось установить фильтры из файла конфигурации");
            logger.warn(e.toString());
        }
    }

	/** конструктор
	 */
	public NaumenTest(){
		userLogin = getProperty("Login");
		userPassword = getProperty("Password");
		user.setValue(USER.PASSWORD, userPassword);
		user.setValue(USER.LOGIN, userLogin);
	}

	/** возвращает значение по ключу
	 * @param key ключ
	 * @return значение по ключу
	 */
	public static String getProperty(String key){
		String value = System.getProperty(key);
		if(Strings.isNullOrEmpty(value)){
			value = Browser.props.getProperty(key);
		}
		return value;
	}

    /** задержка
     * @param sec время в секундах
     */
    @Deprecated
    public void sleep(int sec){
        try {
            Thread.sleep(sec * 1000);
        }
        catch (InterruptedException ie) {
            return;
        }
    }

    public void waitForLoading() {
        try {
            (new WebDriverWait(getDriver(), ReportLoadingTimeoutSec))
//                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadmask")));
                    .until(ExpectedConditions.invisibilityOfElementWithText(By.className("x-mask-loading"), "Загрузка..."));
        }
        catch (TimeoutException te) {
            if (clickOkIfNecessary())
                browser.refresh();
        }
    }

	/** возвращает login пользователя
	 * @return login пользователя
	 */
	public static String getUserLogin(){
		return userLogin;
	}

	/**  возвращает пароль пользователя
	 * @return пароль пользователя
	 */
	public static String getUserPassword(){
		return userPassword;
	}

    /* (non-Javadoc)
	 * @see webdriver.IAnalys#invokeAnalys(java.lang.Throwable, java.lang.String)
	 */
	@Override
	public void invokeAnalys(final Throwable exc, final String bodyText) throws Throwable {
	}

	/** логгирование шагов
	 * @param step номер шага
	 * @param info информация о выполняемых действиях
	 */
	public void logStep(String step, String info){
		logStep(Integer.parseInt(step));
		info("--------==[" + info + "]==--------");
	}

	@Override
	protected void info(String message) {
		if (System.getProperty("os.name").toLowerCase().contains("win"))
            super.info(message.replace("И", "и"));
        else
            super.info(message);
	}

    /* (non-Javadoc)
 * @see webdriver.IAnalys#shouldAnalys()
 */
    @Override
    public boolean shouldAnalys() {
        return false;
    }

    /** Возвращает текущий веб-драйвер
     * @return webDriver
     */
    public RemoteWebDriver getDriver() {
        return browser.getDriver();
    }

    /**
     * вход в систему
     * и инициализация Documents Tree
     */
    public void enterSystem() {
        LoginForm lf = new LoginForm();
        lf.login(user);
    }
    /** Тест
     * @throws Throwable Throwable
     */
    @Test
    public void initializeTestData() throws Throwable{
        docTreeFileName = System.getProperty("user.dir") + "/../DocumentTrees/" + getUserLogin() + getStand() + "DocumentsTree.json";
        xTest();
    }

    /**
     * вернуть json-объект секции из docTree
     * @param sectionName имя секции
     * @return объект секции
     * @throws NoSuchSectionException
     */
    public JSONObject getSection(String sectionName) throws NoSuchSectionException{
        Iterator it = docTree.values().iterator();
        while (it.hasNext()) {
            JSONObject section = (JSONObject) it.next();
            if (sectionName.equals(section.get("title")))
                return section;
        }
        throw new NoSuchSectionException();
    }

    /**
     * возвращает item с нужным title
     * @param items
     * @param itemTitle
     * @return
     * @throws ReportNotFoundException
     */
    public JSONObject getItem(JSONArray items, String itemTitle) throws ReportNotFoundException{
        Iterator it = items.iterator();
        while (it.hasNext()) {
            JSONObject item = (JSONObject) it.next();
            if (itemTitle.equals(item.get("title")))
                return item;
        }
        throw new ReportNotFoundException();
    }

    /**
     * возвращает json Отчета из documents tree
     * @param sectionTitle
     * @param folderTitle
     * @param reportTitle
     * @return reportJson
     */
    public JSONObject getReportJson(String sectionTitle, String folderTitle, String reportTitle) throws ReportNotFoundException{
        try{
            JSONArray sectionItems = (JSONArray)((JSONObject)getSection(sectionTitle)).get("items");
            if (!folderTitle.equals(""))
                sectionItems = (JSONArray)((JSONObject)getItem(sectionItems, folderTitle)).get("items");
            return getItem(sectionItems, reportTitle);
        }
        catch(Exception e){
            throw new ReportNotFoundException();
        }
    }

    /**
     * выводит в лог содержимое секции (по docTree)
     * @param items
     */
    public void logSectionItems(JSONArray items){
        logger.info("<b>-----------Данная секция (по documents_tree) содержит в себе следующие элементы: </b>");
        Iterator itemsIt = items.iterator();
        while(itemsIt.hasNext()){
            JSONObject item = (JSONObject)itemsIt.next();
            String title = item.get("title").toString();
            String type = item.get("type").toString();
            if (!type.equals("folder"))
                logger.info(title);
            else {
                logger.info(title + "/");
                JSONArray subItems = (JSONArray) item.get("items");
                Iterator subItemsIt = subItems.iterator();
                while(subItemsIt.hasNext()){
                    JSONObject subItem = (JSONObject)subItemsIt.next();
                    logger.info("---- " + subItem.get("title"));
                }
            }
        }
    }

    /**
     * открытие раздела левого навигационного меню
     *  @param sectionName название секции
     */
    public void openLeftNavMenuSection(String sectionName, String sectionLabel) throws NoSuchSectionException{
        if (sectionLabel.toLowerCase().contains(getUserLogin().toLowerCase()))
            sectionLabel = "favorite";
        Button LeftNavMenuBtn = new Button(By.className("b-" + sectionLabel), sectionName);
        try {
            LeftNavMenuBtn.click();
            new Actions(getDriver()).moveByOffset(200, 0).perform();
        }
        catch(NullPointerException npe){
            throw new NoSuchSectionException();
        }
    }

    /**
     * открыть папку
     * @param folderTitle
     */
    public void openFolder(String folderTitle) throws ReportNotFoundException {
        logger.info("-*-*-*---------- <b>Папка '" + folderTitle + "'</b> ----------*-*-*-");
        clickReportBtn(folderTitle);
    }

    /**
     * метод противоречит модели Page Object, но подходит для того,
     * чтобы убедиться, что форма ввода параметров для отчета присутствует на экране
     * @return
     */
    @Deprecated
    private boolean isCFFpresent(){
        return (getDriver().findElements(By.className("filter-panel")).size() > 0);
    }

    /**
     * открыть отчет
     * @param reportTitle
     * @param hasParameters есть ли в отчете фильтры по умолчанию или сохраненные пользовательские
     */
    public void openReport(String reportTitle, boolean hasParameters, JSONObject filters)
            throws ReportNotFoundException {
        logger.info("-*-*-*---------- <b>Отчет '" + reportTitle + "'</b> ----------*-*-*-");
        clickReportBtn(reportTitle);
        if (isCFFpresent())
        {
            ChooseFiltersForm cff = new ChooseFiltersForm();
            if (!hasParameters)
                cff.setParameters(filters);
            cff.buildBtnClick();
        }
        else if ((filters != null) && !filters.isEmpty())
            fillFilters(filters);
        waitForLoading();
    }

    /**
     * открыть отчет
     * @param reportTitle
     * @param hasParameters есть ли в отчете фильтры по умолчанию или сохраненные пользовательские
     * @param filters
     * @param harName
     */
    public void openReportWithNewHar(String reportTitle, boolean hasParameters, JSONObject filters, String harName)
            throws ReportNotFoundException {
        logger.info("-*-*-*---------- <b>Отчет '" + reportTitle + "'</b> ----------*-*-*-");
        clickReportBtn(reportTitle);
        if (isCFFpresent())
        {
            ChooseFiltersForm cff = new ChooseFiltersForm();
            if (!hasParameters)
                cff.setParameters(filters);

            Browser.newHar(harName, Thread.currentThread().getId());
            cff.buildBtnClick();
        }
        else if ((filters != null) && !filters.isEmpty())
            fillFilters(filters);
        waitForLoading();
    }

    /**
     * нажимаем по кнопке "Фильтры", если она есть, и вводим фильтры из конфигуратора
     * @param filters фильтры из конфигуратора
     */
    public void fillFilters(JSONObject filters){
        List<WebElement> fltrsEls = getDriver().findElements(By.className("i-filter"));
        if (fltrsEls.size() > 0)
        {
            Button fltrsBtn = new Button(By.className("i-filter"), "Фильтры (параметры)");
            fltrsBtn.click();
            ChooseFiltersForm cff = new ChooseFiltersForm();
            cff.setParameters(filters);
            Browser.newHar("!!!", Thread.currentThread().getId());
            cff.buildBtnClick();
        }
    }

    /**
     * открыть отчет
     * @param sectionTitle
     * @param folderTitle
     * @param reportTitle
     * @throws ReportNotFoundException
     */
    public void openReport(String sectionTitle, String sectionLabel, String folderTitle, String reportTitle, boolean hasParameters) throws ReportNotFoundException {
        try{
            openLeftNavMenuSection(sectionTitle, sectionLabel);
            if (folderTitle.intern() != "")
                openFolder(folderTitle);
            JSONObject fltrs = getFilters(sectionTitle, folderTitle, reportTitle);
            openReport(reportTitle, hasParameters, fltrs);
        }
        catch (NoSuchSectionException nsse) {
            logger.warn("На портале не найдена секция '" + sectionTitle + "'");
            warn();
            return;
        }
    }

    /**
     * открыть отчет с созданием нового har
     * @param sectionTitle
     * @param folderTitle
     * @param reportTitle
     * @param harName
     * @throws ReportNotFoundException
     */
    public void openReportWithNewHar(String sectionTitle, String sectionLabel, String folderTitle, String reportTitle, boolean hasParameters, String harName) throws ReportNotFoundException {
        try{
            openLeftNavMenuSection(sectionTitle, sectionLabel);
            if (folderTitle.intern() != "")
                openFolder(folderTitle);
            JSONObject fltrs = getFilters(sectionTitle, folderTitle, reportTitle);
            openReportWithNewHar(reportTitle, hasParameters, fltrs, harName);
        }
        catch (NoSuchSectionException nsse) {
            logger.warn("На портале не найдена секция '" + sectionTitle + "'");
            warn();
            return;
        }
    }

    /**
     * клик по кнопке отчета на панели
     * @param reportTitle
     * @throws ReportNotFoundException
     */
    public void clickReportBtn(String reportTitle) throws ReportNotFoundException {
        Button reportBtn = new Button(By.xpath(String.format("//*[contains(@class,'user-button-label') and contains(.,'%s')]", reportTitle)), reportTitle);
        try {
            new Actions(getDriver()).moveToElement(reportBtn.getElement()).perform();
            reportBtn.click();
        }
        catch (NullPointerException npe) {
            throw new ReportNotFoundException();
        }
        catch (ElementNotVisibleException enve) {
            throw new ReportNotFoundException();
        }
    }

    /**
     * нажать кнопку "Назад" на отчете,
     * если таковая имеется
     */
    public void closeReport(){
        Button backBtn = new Button(By.xpath("//button[contains(.,'Назад')]"), "Назад");
        if (backBtn.isPresent())
            backBtn.clickAndWait();
    }
    /**
     * нажать кнопку "Назад" в папке,
     */
    public void closeFolder(){
        closeReport();
    }


    /**
     * читает docTree из файла
     * @return docTree
     * @throws NoDocumentsTreeException
     */
   public JSONObject getDocTreeFromFile() throws NoDocumentsTreeException {
       try {
           return getJsonFromFile(docTreeFileName);
       }
       catch(Exception e) {
           throw new NoDocumentsTreeException();
       }
   }

    /**
     * читает JSON из файла
     * @param fileName
     * @return JSON из файла
     * @throws ParseException
     * @throws IOException
     */
   public JSONObject getJsonFromFile(String fileName) throws ParseException, IOException {
       JSONParser parser = new JSONParser();
       String text = CommonFunctions.readTextFromFile(fileName);
       return (JSONObject) parser.parse(text);
   }

    /**
     * нажимает на кнопку ОК на форме с сообщением об ошибке
     */
    public boolean clickOkIfNecessary() {
        boolean hasClicked = false;
        Button OK = new Button(By.xpath("//span[@class='x-btn-inner' and contains(@id, 'btn') and contains(.,'OK')]"), "OK");
        if (OK.waitForIsElementPresent()) {
            logger.warn("Возникло диалоговое окно с сообщением об ошибке");
            makeScreen();
            OK.click();
            hasClicked = true;
            warn();
        }
        return hasClicked;
    }

    /**
     * сохранить отчет в Excel
     * @param filename
     */
    public void exportXLS(String filename){
        Button XLSBtn = new Button(By.xpath("//div[starts-with(@id, 'button') and contains(@class, 'i-xls')]"), "Выгрузить в Excel");
        if (!XLSBtn.waitForIsElementPresent()){
            logger.warn("Не найдена кнопка 'Выгрузить в Excel'");
            return;
        }
        browser.newHar(browser.getStartBrowserURL());
        XLSBtn.clickAndWait();
        Har har = browser.getHar();

    }
    /**
     * вернуть JSON-объект отчета из секции
     * @param ReportName название отчета
     * @param section JSON-объект проверяемой секции
     * @return user
     */
    private JSONObject getReportJSON(String ReportName, JSONObject section){
        JSONArray items = (JSONArray)section.get("items");
        JSONObject report = null;
        Iterator itemsIt = items.iterator();
        while(itemsIt.hasNext()){
            JSONObject item = (JSONObject) itemsIt.next();
            if (ReportName.equals(item.get("title")))
                return item;
            if (item.get("type").equals("folder")) {
                report = getReportJSON(ReportName, item);
                if (report != null)
                    break;
            }
        }
        return report;
    }

    /**
     * вернуть JSON-объект отчета из docTree
     * @param ReportName название отчета
     * @return user
     */
    public JSONObject getReportJSON(String ReportName) {
        Iterator docTreeValuesIt = docTree.values().iterator();
        JSONObject report = null;
        while (docTreeValuesIt.hasNext()){
            JSONObject section = (JSONObject) docTreeValuesIt.next();
            report = getReportJSON(ReportName, section);
            if (report != null)
                break;
            else
                continue;
        }
        return report;
    }

    public void waitForElementToBeVisible(WebElement el){
        try {
            (new WebDriverWait(getDriver(), ReportLoadingTimeoutSec))
                    .until(ExpectedConditions.visibilityOf(el));
        }
        catch (TimeoutException te) {
            if (clickOkIfNecessary())
                browser.refresh();
        }
    }

    /**
     * парсит значение double из строки. Если пустая строка, то возвращается 0.
     * Запятые заменяются на точки
     * @param stringVal
     * @return
     */
    public static double parseDoubleValue(String stringVal){
        if (stringVal.contains(",")) {
            stringVal = stringVal.replace(',', '.');
        }
        if (stringVal.equals(""))
            return 0;
        else
            return Double.valueOf(stringVal.intern());
    }

}
