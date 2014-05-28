package naumen.framework.forms;

import naumen.framework.base.elements.Button;
import org.openqa.selenium.By;

public class ReportForm extends BaseAppForm {
    Button backBtn = new Button(By.xpath("//button[contains(.,'Назад')]"), "Назад");

    public ReportForm() {
        super(By.className("i-favorite-plus"), "Форма отчета");
    }

    /** возвращает локатор идентификации формы
     * @return локатор
     */
    public static By getFormLocator(){
        return By.className("i-favorite-plus");
    }

    /**
     * нажать на кнопку "Назад"
     */
    public void backBtnClick(){
        backBtn.click();
    }

    /**
     * нажать на кнопку "Назад" и ждать перезагрузки страницы
     */
    public void backBtnClickAndWait(){
        backBtn.clickAndWait();
    }
}
