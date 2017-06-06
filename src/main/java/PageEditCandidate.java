import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Maria on 06.06.2017.
 */
public class PageEditCandidate extends BasePage
{

    /**
     * заполняет поле "Место рождения"
     * @param userPlaceOfBirth
     */
    private void fillPlaceOfBirth(String userPlaceOfBirth)
    {
        WebElement placeOfBirthField = $(By.id("placeOfbirth"));
        placeOfBirthField.sendKeys(userPlaceOfBirth);
    }

    /**
     * Заполняет поле "Дата рождения"
     * @param userDateOfBirth
     */
    private void fillDateOfBirth(String userDateOfBirth)
    {
        WebElement dateOfBirthBirthField = $(By.id("dateOfBirth"));
        dateOfBirthBirthField.sendKeys(userDateOfBirth);
    }

    /**
     * Заполняет поле "Уровень полученнного (имеющегося) образования"
     * @param userEducationLevelId
     */
    private void fillEducationLevelId(String userEducationLevelId)
    {
        WebElement educationLevelIdField = $(By.id("educationLevelId"));
        educationLevelIdField.sendKeys(userEducationLevelId);
    }

    /**
     * Заполняет поле "Наименоваие оконченного учебного заведения"
     * @param userPreviousEduOrganization
     */
    private void fillPreviousEduOrganization(String userPreviousEduOrganization)
    {
        WebElement previousEduOrganizationField = $(By.id("previousEduOrganization"));
        previousEduOrganizationField.sendKeys(userPreviousEduOrganization);
    }

    /**
     * Заполняет поле "Местонахождения оконченного учебного заведения (Страна)"
     * @param userCountryOfFinishedEducationOrganisation
     */
    private void fillCountryOfFinishedEducationOrganisation(String userCountryOfFinishedEducationOrganisation)
    {
        WebElement countryOfFinishedEducationOrganisationField = $(By.id("countryOfFinishedEducationOrganisation"));
        countryOfFinishedEducationOrganisationField.sendKeys(userCountryOfFinishedEducationOrganisation);
    }

    /**
     * Заполняет уровень образования, который кандидат хочет получить
     * @param userLevelId
     */
    private void fillLevelId(String userLevelId)
    {
        WebElement levelIdField = $(By.id("levelId"));
        levelIdField.sendKeys(userLevelId);
    }
    /**
     * метод, заполняющий все обязательные поля заявки кандидата
     */
    public void fillCadidateRequest(String userPlaceOfBirth, String userDateOfBirth, String userEducationLevelId, String userPreviousEduOrganization,
                                    String userCountryOfFinishedEducationOrganisation, String userLevelId )
    {
        log("Заполняем обязательные поля заявки кандидата");
        $(By.xpath("//div[@id='sourceOfSearch']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("//a[contains(text(),'Росатом')]")).click();
        fillPlaceOfBirth(userPlaceOfBirth);
        fillDateOfBirth(userDateOfBirth);
        fillEducationLevelId(userEducationLevelId);
        fillPreviousEduOrganization(userPreviousEduOrganization);
        fillCountryOfFinishedEducationOrganisation(userCountryOfFinishedEducationOrganisation);
        $(By.xpath("//button[@data-go-to-request='true']")).click();

    }

}
