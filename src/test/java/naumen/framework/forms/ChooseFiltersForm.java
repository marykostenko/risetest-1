package naumen.framework.forms;

import naumen.framework.base.Browser;
import naumen.framework.base.elements.BaseElement;
import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.Label;
import naumen.framework.base.elements.TextBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Iterator;

public class ChooseFiltersForm extends BaseAppForm{
    private static final Button buildBtn = new Button(By.className("settings-button"), "Построить");
    private static final Button cancelBtn = new Button(By.className("cancel-button"), "Отмена");
    private static final Button dropFiltersBtn = new Button(By.className("param-clear"), "Сбросить выбранные параметры");

    protected enum filterType {
        MULTISELECT("multiselect"),
        SELECT("select"),
        INPUT("input"),
        TIMERANGE("timerange");

        private String val;
        /** Устанавливает название элементу перечисления
         * @param name название
         */
        filterType(String name) {
            val = name;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    public ChooseFiltersForm() {
//        super(By.xpath("//*[contains(@class, 'x-fieldset-header-text') and contains(.,'Параметры отчета')]"), "Параметры отчета");
        super(By.className("filter-panel"), "Параметры отчета");
    }
    /** возвращает локатор идентификации формы
     * @return локатор
     */
    public By getFormLocator(){
        return By.xpath("//div[contains(@class, 'x-fieldset-header-text') and contains(.,'Параметры отчета')]");
    }

    /**
     * есть ли на экране окно выбора параметров отчета?
     * @return
     */
    public boolean isPresent() {
        String text = "Параметры отчета";
        Label l = new Label(By.xpath("//*[contains(@class, 'x-fieldset-header-text') and contains(.,'" + text + "')]"), text);
        return l.waitForIsElementPresent(browser.getTimeoutShortInt());
    }
    /**
     * Нажимает по кнопке "Построить" и ждет
     */
    public void buildBtnClickAndWait() {
        buildBtn.clickAndWait();
    }

    /**
     * Нажимает по кнопке "Построить"
     */
    public void buildBtnClick() {
//        RemoteWebElement bb = getDriver().findElement()
        try{
            buildBtn.jsClick();
            buildBtn.waitForElementIsNotPresent();
        }
        catch(StaleElementReferenceException e){
            Button buildBtn = new Button(By.className("settings-button"), "Построить");
            buildBtn.jsClick();
        }
    }

    /**
     * Нажимает по кнопке "Построить", если она есть на форме
     */
    public void buildBtnClickAndWaitIfPresent() {
        if (buildBtn.isPresent())
            buildBtn.clickAndWait();
    }
    /**
     * Нажимает по кнопке "Отмена"
     */
    public void cancelBtnClickAndWait() {
        cancelBtn.clickAndWait();
    }

    /**
     * заполняет фильтр типа SELECT
     * @param filterName имя фильтра
     * @param value JSON с фильтрами
     */
    public void fillSelectFilter(String filterName, JSONObject value){
        logger.info("---установка значения фильтра '" + filterName + "'---");
        try{
            Label lab = new Label(By.xpath("//label[contains(.,'" + filterName + "')]"), filterName);
            lab.click();
            String val= (String) value.get("value");
            WebElement item = browser.getDriver().findElement(By.xpath("//li[contains(@class,'x-boundlist-item') and contains(.,'" + val + "')]"));
            BaseElement.clickViaJS(item);
            logger.info("Было выбрано значение '" + val + "'");
        }
        catch(Exception e){
            logger.notifyWithScreenShot("Не удалось установить значение фильтра");
        }
    }

    /**
     * сброс значений фильтра типа мультиселект, если такие имеются
     */
    private void clearMultiselectValues(){
        logger.info("----Сброс уже выбранных значений, если такие имеются----");
        Label checkAllLab = new Label(By.xpath("//span[contains(.,'Выбрать всё')]"), "Выбрать всё");
        checkAllLab.clickAndWait();
        checkAllLab.clickAndWait();
    }

    /**
     * выбирает значения фильтра типа мультиселект
     * @param value JSONObject
     */
    private void enablingConfiguratorValues(JSONObject value){
        logger.info("----Установка значений из конфигуратора----");
        JSONArray items = (JSONArray) value.get("items");
        Iterator it = items.iterator();
        while(it.hasNext()){
            Object val = it.next();
            Label item = new Label(By.xpath("//li[contains(@class,'x-boundlist-item') and contains(.,'" +
                    val.toString().trim() + "')]"), val.toString());
            if (!item.waitForIsElementPresent()){
                String xpath = "//li[contains(@class,'x-boundlist-item')";
                for(String cont:val.toString().split(" ")){
                    xpath = xpath + " and contains(.,'" + cont + "')";
                }
                xpath += "]";
                item = new Label(By.xpath(xpath), val.toString());
                if (!item.waitForIsElementPresent()){
                    logger.notify("пункт '" + val.toString() + "' не найден");
                    continue;
                }
            }
            String itemClass = item.getAttribute("class");
            if (!itemClass.contains("x-boundlist-selected")){
                BaseElement.clickViaJS(item.getElement());
                logger.info("Было выбрано значение '" + val.toString() + "'");
            }
            else{
                logger.notify("Уже была выбрана опция '" + val.toString() + "'");
            }
        }
    }

    /**
     * является ли фильтр мультиселект обязательным
     * @param filterName имя фильтра
     * @return да или нет
     */
    public boolean isFilterObligative(String filterName){
        return (filterName.contains("*"));
    }

    /**
     * проверить, есть ли первое значение обязательного фильтра
     * мультиселект в конфигураторе, и отключить его, если нет
     */
    public void checkFirstValue(JSONObject value){
        WebElement firstVal = browser.getDriver().findElements(By.className("x-boundlist-item")).get(0);
        if (firstVal.isSelected()){
            String firstValText = firstVal.getText();
            JSONArray items = (JSONArray) value.get("items");
            if ((!items.isEmpty()) && (!items.toJSONString().contains(firstValText))){
                logger.info("Обязательное значение '" + firstValText + "' отсутствует в конфигураторе и будет отключено");
                BaseElement.clickViaJS(firstVal);
            }
        }
    }

    /**
     * заполняет фильтр типа MULTISELECT
     * @param filterName имя фильтра
     * @param value JSON с фильтрами
     */
    public void fillMultiSelectFilter(String filterName, JSONObject value){
        logger.info("---установка значений фильтра '"+ filterName + "'---");
        Label lab = new Label(By.xpath("//label[contains(.,'" + filterName + "')]"), filterName);
        lab.click();
        boolean checkAll = Boolean.parseBoolean((String)value.get("checkAll"));
        if (checkAll){
            Label checkAllLab = new Label(By.xpath("//span[contains(.,'Выбрать всё')]"), "Выбрать всё");
            checkAllLab.click();
            logger.info("Было выбрано значение 'Выбрать всё'");
        }
        else{
            if (isFilterObligative(filterName))
                checkFirstValue(value);
            enablingConfiguratorValues(value);
            }
        Button OKbtn = new Button(By.xpath("//span[contains(@id,'button') and contains(.,'Ok')]"), "OK");
        OKbtn.click();
    }

    /**
     * заполняет фильтр типа INPUT
     * @param filterName имя фильтра
     * @param val JSON с фильтрами
     */
    public void fillInputFilter(String filterName, JSONObject val){
        logger.info("---установка значения фильтра '"+ filterName + "'---");
        Label nameLab = new Label(By.xpath("//tr[contains(.,'" + filterName + "')]"), filterName);
        WebElement input = nameLab.getElement().findElement(By.xpath(".//input[@*]"));
        input.sendKeys((String) val.get("value"));
        logger.info("Ввод текста: " + val.get("value"));
    }

    /**
     * заполняет фильтр типа TIMERANGE
     * @param filterName имя фильтра
     * @param val JSON с фильтрами
     */
    public void fillTimeRangeFilter(String filterName, JSONObject val){
        logger.info("---установка значения фильтра '"+ filterName + "'---");
        String[] rus = new String[]{"с:", "по:"};
        String[] eng = new String[]{"from", "to"};
        Label nameLab = new Label(By.xpath("//div[contains(.,'" + filterName + "')]"), filterName);
        for(int i=0; i<2; i++){
            try{
                String text = (String)val.get(eng[i]);
                if (text.equals(null))
                    throw new NullPointerException();
                WebElement pointLab = nameLab.getElement().findElement(By.xpath(".//table[contains(@class,'field-" + eng[i] + "')]"));
                WebElement input = pointLab.findElement(By.xpath(".//input"));
                input.sendKeys(text);
                logger.info("Ввод значения '" + rus[i] + "' " + text);
            }
            catch (NullPointerException npe){
                logger.notify("В конфигураторе не указано значение " + rus[i]);
                continue;
            }
        }
    }

    /**
     * Выбор фильтров из JSON-а на окне выбора фильтров
     * @param filters JSONObject с фильтрами для открываемого отчета
     */
    public void chooseFilters(JSONObject filters){
        if ((filters == null) || filters.isEmpty())
            return;
        Iterator keysIt = filters.keySet().iterator();
        while (keysIt.hasNext()){
            String filterName = (String) keysIt.next();
            JSONObject value = (JSONObject)filters.get(filterName);
            String type = (String) value.get("type");
            switch(filterType.valueOf(type.toUpperCase())) {
                case SELECT:
                    fillSelectFilter(filterName, value);
                    break;
                case MULTISELECT:
                    fillMultiSelectFilter(filterName, value);
                    break;
                case INPUT:
                    fillInputFilter(filterName, value);
                    break;
                case TIMERANGE:
                    fillTimeRangeFilter(filterName, value);
                    break;
                default:
                    logger.warn("неизвестный тип фильтра");
            }
        }
    }


    /**
     * Устанавливает значения фильтров при построении отчета
     * @param filters JSONObject фильтры
     */
    public void setParameters(JSONObject filters){
        dropFilters();
        chooseFilters(filters);
    }

    /**
     * вставляет в поле "Поиск" искомый текст
     * @param searchText
     */
    public void setSearchText(String searchText) {
        TextBox searchTB = new TextBox(By.xpath("//*[contains(@name, 'text')]"), "Поиск");
        searchTB.setText(searchText);
    }

    /**
     * сбросить параметры
     */
    public void dropFilters(){
        dropFiltersBtn.click();
    }
}
