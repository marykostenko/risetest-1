import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestRequestsForHttp
{
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
     * */
    public static int postRequestForRegistrationWithPartialFilling(String urlForRequest, String lastName, String firstName, String sex, String countryId, String regEmail, String registrationPassword) throws IOException
    {

        HttpPost loginRequest = new HttpPost(urlForRequest);
        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        credentials.add(new BasicNameValuePair("lastName", lastName));
        credentials.add(new BasicNameValuePair("firstName", firstName));
        credentials.add(new BasicNameValuePair("middleName", ""));
        credentials.add(new BasicNameValuePair("sex", sex));
        credentials.add(new BasicNameValuePair("countryId", countryId));
        credentials.add(new BasicNameValuePair("registrationEmail", regEmail));
        credentials.add(new BasicNameValuePair("registrationPassword", registrationPassword));
        credentials.add(new BasicNameValuePair("registrationPasswordConfirm", registrationPassword));
        credentials.add(new BasicNameValuePair("promoCode", ""));
        credentials.add(new BasicNameValuePair("licenseAgreement", "true"));
        credentials.add(new BasicNameValuePair("pdnAgreement", "true"));

        loginRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));
        HttpResponse response = getHttpClient().execute(loginRequest);
        return response.getStatusLine().getStatusCode();
    }

    /**
     * Заполнчет персональные данные кандидата
     */
    public static int postRequestFillCandidatePersonalData(String urlForRequest, String lastName, String firstName, String placeOfBirth, String dateOfBirth, String sexEn, String email,
                                                           String educationLevelId, String previousEduOrganization, String countryOfFinishedEducationOrganisationId, String sourceOfSearch)
            throws IOException
    {
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
        credentials.add(new BasicNameValuePair("identityCardExpirationDate", email));
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
        System.out.println(response);
        return response.getStatusLine().getStatusCode();
    }

    /**
     * Заполняет заявку кандидата
     */
    public static int postRequestFillCandidateRequest(String urlForRequest, String agreeToContract, String candidateStateCode, String eduDirId, String educationForm, String languagesWithDegrees,
                                                      String languagesWithDegreesDegree, String languagesWithDegreesLanguage, String levelId, String selectedOrgId) throws IOException
    {
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
        return response.getStatusLine().getStatusCode();
    }

}
