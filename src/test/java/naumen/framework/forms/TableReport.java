package naumen.framework.forms;

import naumen.framework.base.elements.BaseElement;
import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.Form;
import naumen.framework.base.exceptions.NoTotalTrException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * форма табличного отчета
 */

public class TableReport extends BaseAppForm{

    public static final Button backBtn = new Button(By.xpath("//button[contains(.,'Назад')]"), "Назад");
    private static final Form tableHead = new Form(By.className("tableHead"), "Шапка отчета");
    private static final Form tableBody = new Form(By.className("mainTableTbody"), "Тело отчета");
    private static final Button reportLinkBtn = new Button(By.xpath("//div[starts-with(@id, 'button') and contains(@class, 'i-link')]"), "Ссылка на отчет");
    private Form tableTotalTr;
    private Form tableTotalPaidTr;
    private String title;
    private boolean hasPlusMinus;

    /**
     * Constructor by default
     */
    public TableReport() {
        super(By.className("tableHead"), "Шапка отчета");
        setHasPlusMinus();
    }

    public TableReport(String title) {
        super(By.className("tableHead"), "Шапка отчета");
        setTitle(title);
        setHasPlusMinus();
    }

    /** возвращает локатор идентификации формы
     * @return локатор
     */
    public static By getFormLocator(){
        return By.className("tableHead");
    }

    public String getTitle(){
        return title;
    }

    private void setHasPlusMinus(){
        hasPlusMinus = (tableBody.findElements(By.className("plusminus-img")).size() > 0);
    }

    public void setTitle(String str){
        title = str;
    }

    public void closeReport(){
        backBtn.clickAndWait();
    }

    public static void backBtnClick(){
        backBtn.click();
    }

    public static void backBtnClickAndWait(){
        backBtn.clickAndWait();
    }

    public static void backBtnClickAndWaitWithoutNotification(){
        backBtn.clickAndWaitWithoutNotification();
    }

    public static void backBtnClickViaJS(){
//        BaseElement.clickViaJS(backBtn.getElement());
        for(int i=0;i<3;i++){
            try{
                Button btn = new Button(By.xpath("//button[contains(.,'Назад')]"), "Назад");
                BaseElement.clickViaJS(btn.getElement());
                return;
            }
            catch (StaleElementReferenceException sere){
                continue;
            }
        }
    }

    private void setTableTotalTr()throws NoTotalTrException{
        List<WebElement> totalTrs = browser.getDriver().findElements(By.xpath("//tr[contains(@class,'tableTotalTr') and @xindex]"));
        if (totalTrs.size() > 1){
            tableTotalTr = new Form(By.xpath("//tr[contains(@class,'tableTotalTr') and @xindex=1]"), "Строка 'итого'");
            tableTotalPaidTr = new Form(By.xpath("//tr[contains(@class,'tableTotalTr') and @xindex=0]"), "Строка 'итого оплачено'");
        }
        else if (totalTrs.size() == 1){
            tableTotalTr = new Form(By.xpath("//tr[contains(@class,'tableTotalTr') and @xindex]"), "Строка 'итого'");
        }
        else
            throw new NoTotalTrException();
    }

    /**
     * возвращает WebElement строки Итого
     * @return
     */
    public WebElement getTotalTr(){
        try{
            if (tableTotalTr == null)
                setTableTotalTr();
            return tableTotalTr.getElement();
        }
        catch(NoTotalTrException ntte){
            return null;
        }
    }

    /**
     * возвращает массив строк по числу ячеек строки Итого. В случае пустых ячеек возвращается пустая строка
     * @return список строк из ячеек строки Итого
     */
    public List<String> getTableTotalTrStringList() throws NoTotalTrException{
        try{
            setTableTotalTr();
            return  getRowStringList(getTotalTr());
        }
        catch(NullPointerException npe){
            logger.notify(npe.toString());
            return null;
        }
    }

    /**
     * возвращает массив строк по числу ячеек строки Итого. В случае пустых ячеек возвращается пустая строка
     * @return список строк из ячеек строки Итого
     */
    public List<String> getTableTotalPaidTrStringList() throws NoTotalTrException{
        try{
            setTableTotalTr();
            return  getRowStringList(tableTotalPaidTr.getElement());
        }
        catch(NullPointerException npe){
            throw new NoTotalTrException();
        }
    }

    /**
     * возвращает список строк из ячеек строки row
     * @param row WebElement
     * @return rowStringList
     */
    public List<String> getRowStringList(WebElement row){
        List<String> rowStringList = new ArrayList<String>();
        List<WebElement> tds = getTds(row);
        Iterator it = tds.iterator();
        while(it.hasNext()){
            WebElement td = (WebElement)it.next();
            rowStringList.add(getTextViaJS(td).toString());
        }
        return rowStringList;
    }

    /**
     * возвращает список строк отчета, если есть агрегирующие, то только их
     * @return List<WebElement>
     */
    public List<WebElement> getTableTrs(){
        if(!hasPlusMinus())
            return tableBody.getElement().findElements(By.tagName("tr"));
        else
            return tableBody.getElement().findElements(By.xpath(".//tr[@uuid = '']"));
    }

    /**
     * возвращает список всех строк отчета
     * @return List<WebElement>
     */
    public List<WebElement> getAllTableTrs(){
        return tableBody.getElement().findElements(By.tagName("tr"));
    }

    /**
     * возвращает список веб=элементов ячеек строки row
     * @param row веб-элемент - строка таблицы
     * @return
     */
    public static List<WebElement> getTds(WebElement row){
        return row.findElements(By.tagName("td"));
    }

    /**
     * возвращает список ячеек шапки отчета
     * @return
     */
    public List<WebElement> getTableHeadThs(){
        return tableHead.getElement().findElements(By.tagName("th"));
    }

    /**
     * возвращает текст из таблицы отчета (без строки Итого)
     * @return текст из таблицы
     */
    public StringBuffer getTextFromTable() {
        return getTextViaJS(tableBody.getElement());
    }

    /**
     * есть ли на форме агрегирующие строки (с выпадающим списком)
     * @return true, если есть
     */
    public boolean hasPlusMinus(){
        return hasPlusMinus;
    }

    public Form getTableBody(){
        return tableBody;
    }

    /**
    * агрегирующая ли строка
     * @return true, если да
     */
    public boolean isRowAggregating(WebElement row){
        return (row.findElements(By.className("plusminus-img")).size() > 0);
    }

    /**
     * возвращает итоговое значение, если оно присутсвует, иначе -1
     * @return
     */
    public int getTotalValue() throws NoTotalTrException{
        int totalValue = -1;
        List<String> totalTrList = getTableTotalTrStringList();
        logger.info("totalTrString=" + totalTrList.toString());
        Iterator it = totalTrList.iterator();
        while(it.hasNext()){
            String str = ((String)it.next()).toLowerCase().trim().replaceAll("\u00A0", "");
            if ((str.contains("итого")) || (str.contains("всего"))){
                String[] strArr = str.split(" ");
                try{
                    totalValue = Integer.parseInt(strArr[strArr.length - 1]);
                }
                catch (NumberFormatException nfe){
                    break;
                }
            }
        }
        return totalValue;
    }

    /**
     * возвращает список всех ячеек строки, содержащих ссылки
     * @param tr строка
     * @return список ячеек из строки со ссылками
     */
    public List<WebElement> getCellsWithLinks(WebElement tr){
        return tr.findElements(By.xpath(".//a[@href]"));
    }

    /**
     * презентует отчет в логе со скриншотом
     */
    public void presentate(){
        logger.info("<b>----Отчет '" + title + "'----</b>");
        makeScreen();
    }

    /**
     * пуст ли отчет (есть ли строки)?
     * @return
     */
    public boolean isEmpty(){
        return ((!getTableBody().waitForIsElementPresent()) || (getAllTableTrs().isEmpty()));
    }

    /**
     * количество строк в таблице
     * @return
     */
    public int rowSize(){
        try{
            return getAllTableTrs().size();
        }
        catch (NullPointerException npe){
            return 0;
        }
    }

    /**
     * количество столбцов в таблице
     * @return
     */
    public int columnSize(){
        try{
            return getTds(getAllTableTrs().get(0)).size();
        }
        catch (NullPointerException npe){
            logger.notify("Не удалось получить размеры");
            return 0;
        }
    }
    /**
     * вывести в лог размеры отчета по столбцам и строкам
     */
    public void logSizes(){
        List<WebElement> rows = getAllTableTrs();
        int rowSize = rows.size();
        int tdSize = getTds(rows.get(0)).size();
        logger.info("В данном отчете " + rowSize + " строк");
        logger.info("В одной строке " + tdSize + " ячеек");
    }

    /**
     * вывести в лог текст из строки row с выделением ячейки col
     * @param row
     */
    public void logRowText(int row, int col){
        String text = "";
        Iterator it = getRowStringList(getRow(row)).iterator();
        int ind = 0;
        while (it.hasNext()){
            if (col == ind)
                text += "<b>" + it.next() + "</b>  ";
            else
                text += it.next() + "  ";
            ind++;
        }
        logger.info(text);
    }

    /**
     * возвращает строку с индексом row, нумерация с 0
     * @param row
     * @return веб-элемент строки
     */
    public WebElement getRow(int row){
        return getAllTableTrs().get(row);
    }

}
