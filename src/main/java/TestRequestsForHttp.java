import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sun.security.krb5.internal.PAData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.activation.registries.LogSupport.log;

public class TestRequestsForHttp {
    private static DefaultHttpClient httpClient;

    private static DefaultHttpClient getHttpClient() {
        if (httpClient == null) {
            PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
            cm.setMaxTotal(100);
            httpClient = new DefaultHttpClient(cm);
        }
        return httpClient;
    }

    /**
     * Заполняет форму регистрации
     */
    public static int postRequestForRegistrationWithPartialFilling(String urlForRequest, String lastName, String firstName, String sex, String countryId, String regEmail, String registrationPassword,
                                                                   String promoCode) throws IOException
    {
        HttpPost registrationRequest = new HttpPost(urlForRequest);
        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        credentials.add(new BasicNameValuePair("lastName", lastName));
        credentials.add(new BasicNameValuePair("firstName", firstName));
        credentials.add(new BasicNameValuePair("middleName", ""));
        credentials.add(new BasicNameValuePair("sex", sex));
        credentials.add(new BasicNameValuePair("countryId", countryId));
        credentials.add(new BasicNameValuePair("registrationEmail", regEmail));
        credentials.add(new BasicNameValuePair("registrationPassword", registrationPassword));
        credentials.add(new BasicNameValuePair("registrationPasswordConfirm", registrationPassword));
        credentials.add(new BasicNameValuePair("promoCode", promoCode));
        credentials.add(new BasicNameValuePair("licenseAgreement", "true"));
        credentials.add(new BasicNameValuePair("pdnAgreement", "true"));

        registrationRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));
        HttpResponse response = getHttpClient().execute(registrationRequest);
        System.out.println(response.getStatusLine());
        response.getEntity().getContent().close();
        return response.getStatusLine().getStatusCode();
    }

    /**
     * логинится
     */
    public static int postRequestForLogin(String urlForRequest, String email, String password) throws IOException {
        HttpPost loginRequest = new HttpPost(urlForRequest);
        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        credentials.add(new BasicNameValuePair("email", email));
        credentials.add(new BasicNameValuePair("password", password));
        credentials.add(new BasicNameValuePair("rememberMe", "true"));
        loginRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));

        HttpResponse response = getHttpClient().execute(loginRequest);
        System.out.println(response.getStatusLine());
        response.getEntity().getContent().close();

        return response.getStatusLine().getStatusCode();
    }

    /**
     * Заполняет персональные данные кандидата
     */
    public static int postRequestFillCandidatePersonalData(String urlForRequest, String lastName, String firstName, String placeOfBirth,
                                                           String dateOfBirth, String sexEn, String email, String educationLevelId, String previousEduOrganization,
                                                           String countryOfFinishedEducationOrganisationId, String sourceOfSearch)
            throws IOException {

        HttpPost personalDataRequest = new HttpPost(urlForRequest);
        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        credentials.add(new BasicNameValuePair("address", ""));
        credentials.add(new BasicNameValuePair("addressCity", ""));
        credentials.add(new BasicNameValuePair("addressCountryId", ""));
        credentials.add(new BasicNameValuePair("addressIndex", ""));
        credentials.add(new BasicNameValuePair("addressOfFinishedEducationOrganisation", ""));
        credentials.add(new BasicNameValuePair("addressRegion", ""));
        credentials.add(new BasicNameValuePair("cityOfFinishedEducationOrganisation", ""));
        credentials.add(new BasicNameValuePair("countryOfFinishedEducationOrganisation", countryOfFinishedEducationOrganisationId));
        credentials.add(new BasicNameValuePair("dateOfBirth", dateOfBirth));
        credentials.add(new BasicNameValuePair("educationLevelId", educationLevelId));
        credentials.add(new BasicNameValuePair("email", email));
        credentials.add(new BasicNameValuePair("endOfPreviousEduYear", ""));
        credentials.add(new BasicNameValuePair("firstNameEng", firstName));
        credentials.add(new BasicNameValuePair("firstNameRus", firstName));
        credentials.add(new BasicNameValuePair("goToRequest", "true"));
        credentials.add(new BasicNameValuePair("identityCardDate", ""));
        credentials.add(new BasicNameValuePair("identityCardExpirationDate", ""));
        credentials.add(new BasicNameValuePair("identityCardNumber", ""));
        credentials.add(new BasicNameValuePair("lastNameEng", lastName));
        credentials.add(new BasicNameValuePair("lastNameRus", lastName));
        credentials.add(new BasicNameValuePair("middleNameRus", ""));
        credentials.add(new BasicNameValuePair("phone", ""));
        credentials.add(new BasicNameValuePair("placeOfBirth", placeOfBirth));
        credentials.add(new BasicNameValuePair("previousEduOrganization", previousEduOrganization));
        credentials.add(new BasicNameValuePair("searchSourceDetails", ""));
        credentials.add(new BasicNameValuePair("sex", sexEn));
        credentials.add(new BasicNameValuePair("sourceOfSearch", sourceOfSearch));
        credentials.add(new BasicNameValuePair("visaObtainingCountry", ""));
        credentials.add(new BasicNameValuePair("visaObtainingRussianMissionId", ""));

        personalDataRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));
        HttpResponse response = getHttpClient().execute(personalDataRequest);
        System.out.println(response.getStatusLine());
        response.getEntity().getContent().close();

        return response.getStatusLine().getStatusCode();
    }

    /**
     * Заполняет заявку кандидата
     */
    public static int postRequestFillCandidateRequest(String urlForRequest, String agreeToContract, String candidateStateCode, String eduDirId, String educationForm, String languagesWithDegrees,
                                                      String languagesWithDegreesDegree, String languagesWithDegreesLanguage, String levelId, String selectedOrgId) throws IOException {
        HttpPost candidateRequest = new HttpPost(urlForRequest);
        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        credentials.add(new BasicNameValuePair("achievementsInfo", ""));
        credentials.add(new BasicNameValuePair("agreeToContract", agreeToContract));
        credentials.add(new BasicNameValuePair("candidateStateCode", candidateStateCode));
        credentials.add(new BasicNameValuePair("eduDirId", eduDirId));
        credentials.add(new BasicNameValuePair("educationForm", educationForm));
        credentials.add(new BasicNameValuePair("languagesWithDegrees", languagesWithDegrees));
        credentials.add(new BasicNameValuePair("languagesWithDegrees", "-"));
        credentials.add(new BasicNameValuePair("languagesWithDegrees", "-"));
        credentials.add(new BasicNameValuePair("languagesWithDegrees", "-"));
        credentials.add(new BasicNameValuePair("languagesWithDegrees", "-"));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-degree", languagesWithDegreesDegree));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-degree", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-degree", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-degree", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-degree", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-language", languagesWithDegreesLanguage));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-language", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-language", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-language", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-language", ""));
        credentials.add(new BasicNameValuePair("languagesWithDegrees-summary", languagesWithDegreesLanguage + ",,,,"));
        credentials.add(new BasicNameValuePair("levelId", levelId));
        credentials.add(new BasicNameValuePair("needPreparatoryTraining", "true"));
        credentials.add(new BasicNameValuePair("needTF", "true"));
        credentials.add(new BasicNameValuePair("programDpo", ""));
        credentials.add(new BasicNameValuePair("researchTheme", ""));
        credentials.add(new BasicNameValuePair("selectedOrgId", selectedOrgId));
        credentials.add(new BasicNameValuePair("selectedOrgId", ""));
        credentials.add(new BasicNameValuePair("selectedOrgId", ""));
        credentials.add(new BasicNameValuePair("selectedOrgId", ""));
        credentials.add(new BasicNameValuePair("selectedOrgId", ""));
        credentials.add(new BasicNameValuePair("selectedOrgId", ""));
        credentials.add(new BasicNameValuePair("semestrId", ""));
        credentials.add(new BasicNameValuePair("sportAchievements", "##"));
        credentials.add(new BasicNameValuePair("sportAchievements", "##"));
        credentials.add(new BasicNameValuePair("sportAchievements", "##"));
        credentials.add(new BasicNameValuePair("sportAchievements-achievement", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-achievement", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-achievement", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-sportType", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-sportType", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-sportType", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-year", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-year", ""));
        credentials.add(new BasicNameValuePair("sportAchievements-year", ""));
        credentials.add(new BasicNameValuePair("trainingPeriod", ""));

        candidateRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));
        HttpResponse response = getHttpClient().execute(candidateRequest);
        System.out.println(response.getStatusLine());
        response.getEntity().getContent().close();
        return response.getStatusLine().getStatusCode();
    }


    /**
     * Отправляет файл на сервер
     *
     * @param urlForRequest
     * @return
     * @throws IOException
     */
    public static int postRequestForUploadFile(String urlForRequest) throws IOException {
        File file = new File("src/main/resources/cote.jpeg");
        HttpPost uploadFileRequest = new HttpPost(urlForRequest);
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart("file", new FileBody(file));

        uploadFileRequest.setEntity(multipartEntity);
        HttpResponse response = getHttpClient().execute(uploadFileRequest);

        System.out.println(response.getStatusLine());
        response.getEntity().getContent().close();
        return response.getStatusLine().getStatusCode();
    }

    /**
     * Загружет фото на сервер, парсит ответ POST запроса и возвращает временную переменную, которую будем использовать в запросе на созранение фото кандидата
     * @param urlForRequest
     * @return
     * @throws IOException
     */
    public String postRequestForUploadPhoto(String urlForRequest) throws IOException {

        String uuid = null;

        File file = new File("src/main/resources/cote.jpeg");
        HttpPost uploadPhotoRequest = new HttpPost(urlForRequest);
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart("file", new FileBody(file));

        uploadPhotoRequest.setEntity(multipartEntity);
        HttpResponse response = getHttpClient().execute(uploadPhotoRequest);

        HttpEntity httpEntity = response.getEntity();

        if (httpEntity != null) {
            JSONObject json = new JSONObject(EntityUtils.toString(httpEntity));
           uuid = json.getJSONArray("files").getJSONObject(0).getString("uuid");

        }

        System.out.println(response.getStatusLine());
        response.getEntity().getContent().close();
        System.out.println(uuid);
        return uuid;
    }

    /**
     * сохраняет фото кандидата
     */
    public static int postRequestForSavePhoto(String urlForRequest, String temporaryImage) throws IOException {

        HttpPost savePhotoRequest = new HttpPost(urlForRequest);

        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        credentials.add(new BasicNameValuePair("height", "500"));
        credentials.add(new BasicNameValuePair("temporaryImage", temporaryImage));
        credentials.add(new BasicNameValuePair("width", "375"));
        credentials.add(new BasicNameValuePair("x", "0"));
        credentials.add(new BasicNameValuePair("y", "0"));
        savePhotoRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));

        HttpResponse response = getHttpClient().execute(savePhotoRequest);
        System.out.println(response.getStatusLine());

        response.getEntity().getContent().close();
        return response.getStatusLine().getStatusCode();
    }

    /**
     * Проверяет успешность POST запроса
     */
    public static boolean successPostRequest(int statusCode)
    {
        if (statusCode != 200)
        {
            System.out.println("ОШИБКА! POST запрос выполнен неуспешно");
            return false;
        }
        else return true;
    }

    /**
     * Отправляет пост запрос для регистрации кандидата
     */
    public boolean registrationCandidateByPostRequest(boolean contract,String standUrl, String lastName, String firstName, String sexEn, String countryId, String email, String password, String promoCode)
            throws IOException
    {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();

        String urlForRequestRegistration;

        System.out.println("Формируем адрес для POST запроса на регистрацию");
        if (contract)
        {
            urlForRequestRegistration = pageEditCandidate.createUrlRequestForRegistrationContract(standUrl);
        } else
        {
            urlForRequestRegistration = pageEditCandidate.createUrlRequestForRegistrationQuota(standUrl);
        }

        System.out.println("Заполняем поля в POST запросе и отправляем данные на регистрацию");
        boolean successPostRequest = successPostRequest(postRequestForRegistrationWithPartialFilling(urlForRequestRegistration, lastName, firstName, sexEn, countryId, email, password, promoCode));

        return successPostRequest;
    }

    /**
     * Отправляет пост запрос для логина
     */
    public boolean loginByPostRequest(String standUrl, String email, String password) throws IOException {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();

        System.out.println("Формируем адрес для POST запроса на логин");
        String urlForRequestLogin = pageEditCandidate.createUrlRequestForLogin(standUrl);

        System.out.println("Отправлем POST запрос с логином");
        boolean successPostRequest = successPostRequest(postRequestForLogin(urlForRequestLogin, email, password));

        return successPostRequest;
    }

    /**
     * Отправляет пост запрос для заполнения персональных данных кандидата
     */
    public boolean fillingPersonalDataByPostRequest(String standUrl, String candidateId, String formAndCardTpl, String nationalSelectionId, String lastName, String firstName, String placeOfBirth,
                                                    String dateOfBirth, String sexEn, String email, String lvlId, String previousEduOrganization, String countryOfFinishedEducationOrganisationId,
                                                    String sourceOfSearchId) throws IOException {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();

        System.out.println("Формируем адрес для POST запроса на отправку персональных данных кандидата");
        String urlForRequestFillCandidatePersonalData = pageEditCandidate.createUrlRequestForEditPersonalData(standUrl, candidateId,  formAndCardTpl, nationalSelectionId);

        System.out.println("Отправляем POST запрос с персональными данными кандидата");
        boolean successPostRequest = successPostRequest(postRequestFillCandidatePersonalData(urlForRequestFillCandidatePersonalData,  lastName, firstName, placeOfBirth,  dateOfBirth, sexEn, email,
                lvlId, previousEduOrganization, countryOfFinishedEducationOrganisationId, sourceOfSearchId));

        return successPostRequest;
    }

    /**
     * Отправляет пост запрос для заполнения заявки кандидата
     */
    public boolean fillCandidateRequestByPostRequest(String standUrl, String candidateId, String formAndCardTpl, String nationalSelectionId, String agreeToContract, String candidateStateCode,
                                                     String eduDirId, String educationForm, String languagesWithDegrees, String languagesWithDegreesDegree,  String languagesWithDegreesLanguage,
                                                     String lvlId,  String selectedOrgId) throws IOException {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();

        System.out.println("Формируем адрес для POST зароса на отправку заявки кандидата");
        String urlForRequestFillCandidateRequest = pageEditCandidate.createUrlRequestForEditRequest(standUrl, candidateId, formAndCardTpl, nationalSelectionId);

        System.out.println("Отправлем POST запрос с данными о заявке кандидата");
        boolean successPostRequest = successPostRequest(postRequestFillCandidateRequest(urlForRequestFillCandidateRequest, agreeToContract, candidateStateCode,  eduDirId, educationForm,
                languagesWithDegrees, languagesWithDegreesDegree,  languagesWithDegreesLanguage, lvlId,  selectedOrgId));
        return successPostRequest;
    }

    /**
     * Отправляет пост запрос с копией пасспорта кандидата
     */
    public boolean fillCopyPassportByPostRequest(String standUrl, String candidateId, String documentOfPassportId) throws IOException {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();

        System.out.println("Формируем адрес для POST зароса на отправку копии пасспорта");
        String urlForRequestForUploadCopyPassport = pageEditCandidate.createUrlRequestForUploadFile(standUrl, candidateId,  documentOfPassportId);

        System.out.println("Отправляем POST запрос с копией пасспорта");
        boolean successPostRequest = successPostRequest(postRequestForUploadFile(urlForRequestForUploadCopyPassport));

        return successPostRequest;
    }

    /**
     * Отправляет пост запрос с копией документа об образовании
     */
    public boolean fillCopyEduCertificate(String standUrl, String candidateId, String documentCopyOfTheEduCertificate) throws IOException {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();

        System.out.println("Формируем адрес для POST зароса на отправку копии документа об образовании");
        String urlForRequestForUploadCopyOfTheEduCertificate = pageEditCandidate.createUrlRequestForUploadFile(standUrl, candidateId,  documentCopyOfTheEduCertificate);

        System.out.println("Отправляем POST запрос с копией документа об образовании");
        boolean successPostRequest = successPostRequest(postRequestForUploadFile(urlForRequestForUploadCopyOfTheEduCertificate));

        return successPostRequest;
    }

    /**
     * Отправляет пост запрос с фото кандидата
     */
    public boolean fillCandidatePhoto(String standUrl, String candidateId) throws IOException {
        PageEditCandidate pageEditCandidate = new PageEditCandidate();

        System.out.println("Формируем адрес для POST запроса на отправку фото кандидата на сервер");
        String urlForRequestForUploadPhoto = pageEditCandidate.createUrlRequestForUploadPhoto(standUrl, candidateId);

        System.out.println("Отправляем POST запрос с фото и сохраняем временную переменную из ответа для сохранения фото");
        String temporaryImage = postRequestForUploadPhoto(urlForRequestForUploadPhoto);

        System.out.println("Формируем POST запрос для сохранения фото кандитата");
        String urlForRequestForSavePhoto = pageEditCandidate.createUrlRequestForSavePhoto(standUrl, candidateId);

        System.out.println("Сохраняем фото кандитата");
        boolean successPostRequest = successPostRequest(postRequestForSavePhoto(urlForRequestForSavePhoto, temporaryImage));

        return successPostRequest;
    }
}

