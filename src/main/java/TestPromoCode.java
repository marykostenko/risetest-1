import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class TestPromoCode
{
    private WebElement promoCodeAgent = $(By.xpath("//div[contains(text(),'Промо-коды')]//following::div[1]"));

    public String selectPromoCodeForCountry(String adminLogin, String adminPass, String country)
    {
        PageLogin pageLogin = new PageLogin();
        PageTopBottom pageTopBottom = new PageTopBottom();
        MenuContent menuContent = new MenuContent();

        String promoCode = null;

        pageTopBottom.goToLogin();
        pageLogin.isLoginForm();
        pageLogin.fillLoginForm(adminLogin, adminPass);
        pageLogin.pushLoginButton();
        menuContent.checkAgent(country);
        $(By.xpath("//tr[1]//child::a[contains(@href,'/agent/')]")).click();

        if (promoCodeAgent.getText() == null)
        {
            $(By.id("add-promo-code-js")).click();
            $(By.xpath("//button[contains(text(),'Ок')]")).click();
            promoCode = promoCodeAgent.getText();
        } else
            promoCode = promoCodeAgent.getText();

        pageTopBottom.logout();

        return promoCode;
    }
}
