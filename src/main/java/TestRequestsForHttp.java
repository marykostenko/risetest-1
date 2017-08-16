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

    public static int postRequestfillCandidateRequest (String urlForRequest, String lastName, String firstName, String placeOfBirth, String dateOfBirth, String sexEn, String email,
                                                       String educationLevelId, String previousEduOrganization, String countryOfFinishedEducationOrganisationId) throws IOException
    {
        HttpPost loginRequest = new HttpPost(urlForRequest);
        List<NameValuePair> credentials = new ArrayList<NameValuePair>();
        credentials.add(new BasicNameValuePair("sourceOfSearch", ""));
        credentials.add(new BasicNameValuePair("searchSourceDetails", ""));
        credentials.add(new BasicNameValuePair("visaObtainingCountry", ""));
        credentials.add(new BasicNameValuePair("visaObtainingRussianMissionId", ""));
        credentials.add(new BasicNameValuePair("lastNameEng", ""));
        credentials.add(new BasicNameValuePair("firstNameEng", ""));
        credentials.add(new BasicNameValuePair("lastNameRus", lastName));
        credentials.add(new BasicNameValuePair("firstNameRus", firstName));
        credentials.add(new BasicNameValuePair("middleNameRus", ""));
        credentials.add(new BasicNameValuePair("placeOfBirth", placeOfBirth));
        credentials.add(new BasicNameValuePair("dateOfBirth", dateOfBirth));
        credentials.add(new BasicNameValuePair("sex", sexEn));
        credentials.add(new BasicNameValuePair("addressCountryId", ""));
        credentials.add(new BasicNameValuePair("addressIndex", ""));
        credentials.add(new BasicNameValuePair("addressRegion", ""));
        credentials.add(new BasicNameValuePair("addressCity", ""));
        credentials.add(new BasicNameValuePair("address", ""));
        credentials.add(new BasicNameValuePair("email", email));
        credentials.add(new BasicNameValuePair("phone", ""));
        credentials.add(new BasicNameValuePair("identityCardNumber", ""));
        credentials.add(new BasicNameValuePair("identityCardDate", ""));
        credentials.add(new BasicNameValuePair("identityCardExpirationDate", ""));
        credentials.add(new BasicNameValuePair("educationLevelId", educationLevelId));
        credentials.add(new BasicNameValuePair("previousEduOrganization", previousEduOrganization));
        credentials.add(new BasicNameValuePair("countryOfFinishedEducationOrganisation", countryOfFinishedEducationOrganisationId));
        credentials.add(new BasicNameValuePair("cityOfFinishedEducationOrganisation", ""));
        credentials.add(new BasicNameValuePair("addressOfFinishedEducationOrganisation", ""));
        credentials.add(new BasicNameValuePair("endOfPreviousEduYear", ""));
        credentials.add(new BasicNameValuePair("goToRequest", "true"));

        loginRequest.setEntity(new UrlEncodedFormEntity(credentials, Consts.UTF_8));
        HttpResponse response = getHttpClient().execute(loginRequest);
        return response.getStatusLine().getStatusCode();
    }

}
