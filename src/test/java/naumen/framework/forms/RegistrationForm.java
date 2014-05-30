package naumen.framework.forms;

import naumen.framework.base.elements.Button;
import naumen.framework.base.elements.CheckBox;
import naumen.framework.base.elements.TextBox;
import org.openqa.selenium.By;
import sun.security.util.Password;

public class RegistrationForm extends BaseAppForm {
    private final TextBox emailTxb = new TextBox(By.id("registrationEmail"), "Email");
    private final TextBox firstNameTxb = new TextBox(By.id("firstName"), "Имя");
    private final TextBox middleNameTxb = new TextBox(By.id("middleName"), "Отчество");
    private final TextBox lastNameTxb = new TextBox(By.id("lastName"), "Фамилия");
    private final TextBox passwordTxb = new TextBox(By.id("registrationPassword"), "Пароль");
    private final CheckBox showPasswordCheckBox = new CheckBox(By.name("showPassword"), "Показать пароль");
    private final CheckBox acceptTermsCheckBox = new CheckBox(By.name("acceptTerms"), "Я принимаю условия использования системы Экспир");
    private final Button submitBtn = new Button(By.xpath("//button[contains(.,'Зарегистрироваться')]"));

    public RegistrationForm() {
        super(By.xpath("//h1[contains(.,'Регистрация')]"), "Форма регистрации");
    }

    /** возвращает локатор идентификации формы
     * @return локатор
     */
    public static By getFormLocator(){
        return By.xpath("//h1[contains(.,'Регистрация')]");
    }

    /**
     * ввод e-mail
     * @param email
     */
    public void setEmail(String email){
        emailTxb.setText(email);
    }

    public void setFirstName(String firstName){
        firstNameTxb.setText(firstName);
    }

    public void setMiddleName(String middleName){
        middleNameTxb.setText(middleName);
    }

    public void setLastName(String lastName){
        lastNameTxb.setText(lastName);
    }

    public void setPassword(String password){
        passwordTxb.setText(password);
    }

    public void showPassword(){
        if (!showPasswordCheckBox.isChecked())
            showPasswordCheckBox.check();
    }

    public void acceptTerms(){
        if (!acceptTermsCheckBox.isChecked())
            acceptTermsCheckBox.check();
    }

    public void submitRegistration(){
        submitBtn.click();
    }
}
