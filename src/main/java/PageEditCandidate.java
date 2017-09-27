import org.openqa.selenium.By;
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
        WebElement placeOfBirthField = $(By.id("placeOfBirth"));
        placeOfBirthField.sendKeys(userPlaceOfBirth);
    }

    /**
     * Заполняет поле "Дата рождения"
     * @param userDateOfBirth
     */
    private void fillDateOfBirth(String userDateOfBirth)
    {
        WebElement dateOfBirthBirthField = $(By.xpath("//input[@name='dateOfBirth']"));
        dateOfBirthBirthField.sendKeys(userDateOfBirth);
    }

    /**
     * Заполняет поле "Уровень полученнного (имеющегося) образования"
     * @param userEducationLevelId
     */
    private void fillEducationLevelId(String userEducationLevelId)
    {
        WebElement educationLevelIdField = $(By.xpath("//div[@id='educationLevelId']//child::input[@class='span4 add-scroll']"));
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
        WebElement countryOfFinishedEducationOrganisationField = $(By.xpath("//div[@id='countryOfFinishedEducationOrganisation']//child::input[@class='span4 add-scroll']"));
        countryOfFinishedEducationOrganisationField.sendKeys(userCountryOfFinishedEducationOrganisation);
    }

    /**
     * Заполняет уровень образования, который кандидат хочет получить
     * @param userLevelId
     */
    private void fillLevelId(String userLevelId)
    {
        WebElement levelIdField = $(By.xpath("//div[@id='levelId']//child::input[@class='span4 add-scroll']"));
        levelIdField.sendKeys(userLevelId);
    }

    private void fillEduDirId(String userEduDirId)
    {
        WebElement eduDirIdField = $(By.xpath("//div[@id='eduDirId']//child::input[@class='span4 add-scroll']"));
        eduDirIdField.sendKeys(userEduDirId);
    }

    /**
     * метод, заполняющий все обязательные поля заявки кандидата
     */
    public void fillCandidateRequest(String userPlaceOfBirth, String userDateOfBirth, String userEducationLevelId, String userPreviousEduOrganization,
                                     String userCountryOfFinishedEducationOrganisation, String userLevelId, String userEduDirId )
    {
        log("Заполняем обязательные поля заявки кандидата");
        $(By.xpath("//div[@id='sourceOfSearch']//child::span[@class='add-on dropdown-toggle']")).click();
        $(By.xpath("html/body/ul[1]/li[2]/a")).click();
        fillPlaceOfBirth(userPlaceOfBirth);
        fillDateOfBirth(userDateOfBirth);
        fillEducationLevelId(userEducationLevelId);
        $(By.xpath("//strong[contains(text(),'"+userEducationLevelId+"')]")).click();
        fillPreviousEduOrganization(userPreviousEduOrganization);
        fillCountryOfFinishedEducationOrganisation(userCountryOfFinishedEducationOrganisation);
        $(By.xpath("//strong[contains(text(),'"+userCountryOfFinishedEducationOrganisation+"')]")).click();
        $(By.xpath("//button[@data-go-to-request='true']")).click();
        fillLevelId(userLevelId);
        $(By.xpath("//strong[contains(text(),'"+userLevelId+"')]")).click();
        fillEduDirId(userEduDirId);
        $(By.xpath("//strong[contains(text(),'"+userEduDirId+"')]")).click();
        $(By.xpath("//div[@id='selectedOrgId_field']//div[contains(text(),'1.')]//following::div[3]//child::span")).click();
        $(By.xpath("html/body/ul[9]/li[2]/a")).click();
        $(By.xpath("//button[@class='btn btn-success']")).click();

    }

    /**
     * Формирует адрес, на который будем отправлять пост запрос при регистрации контрактного кандидата
     */
    public String createUrlRequestForRegistrationContract (String standUrl)
    {
        String urlRequestForRegistrationContract = standUrl + "/registration?contract=true";
        return urlRequestForRegistrationContract;
    }

    /**
     * Формирует адрес, на который будем отправлять пост запрос при регистрации квотного кандидата
     */
    public String createUrlRequestForRegistrationQuota (String standUrl)
    {
        String urlRequestForRegistrationContract = standUrl + "/registration?contract=false";
        return urlRequestForRegistrationContract;
    }


    /**
     * формирует адрес для отправки POST запроса с персональными данными кандидата
     * @param standUrl
     * @param candidateId берётся из базы
     * @param candidateFormAndCardTpl шаблон карточки кандидата берем один и тот же
     * @param nationalSelection берётся из базы
     * @return
     */
    public String createUrlRequestForEditPersonalData (String standUrl, String candidateId, String candidateFormAndCardTpl, String nationalSelection)
    {
        String urlRequestForEditPersonalData = standUrl + "candidate/" + candidateId + "/personalData/"+ candidateFormAndCardTpl + "/nationalSelection/" + nationalSelection;
        return urlRequestForEditPersonalData;
    }

    /**
     * Формирует адрес для отправки POST запроса с заявкой кандидата
     */
    public String createUrlRequestForEditRequest (String standUrl, String candidateId, String candidateFormAndCardTpl, String nationalSelection)
    {
        String urlRequestForEditRequest = standUrl + "candidate/" + candidateId + "/request/edit/"+ candidateFormAndCardTpl + "/nationalSelection/" + nationalSelection;
        return urlRequestForEditRequest;
    }

    /**
     * Формирует алрес для отправки POST запроса при логине
     */
    public String createUrlRequestForLogin (String standUrl)
    {
        String urlRequestForLogin = standUrl + "login";
        return urlRequestForLogin;
    }

    /**
     * Формирут адрес для отправки POST запроса при загрузке файлов (копий документов)
     */
    public String createUrlRequestForUploadFile (String standUrl, String candidateId, String fileId)
    {
        String urlRequestForUploadFile = standUrl + "candidates/" + candidateId + "/" + fileId + "/uploadFile";
        return urlRequestForUploadFile;
    }

    /**
     * Формирует адрес для отправки POST запроса с фото кандидата на сервер
     */
    public String createUrlRequestForUploadPhoto (String standUrl, String candidateId)
    {
        String urlRequestForUploadPhoto = standUrl + "candidate/" + candidateId + "/upload-photo-ajax";
        return urlRequestForUploadPhoto;
    }

    /**
     * Формирует алрес для POST запроса сохпранения фото кандидата
     */
    public String createUrlRequestForSavePhoto (String standUrl, String candidateId)
    {
        String urlRequestForSavePhoto = standUrl + "candidate/" + candidateId + "/save-photo";
        return urlRequestForSavePhoto;
    }
}
