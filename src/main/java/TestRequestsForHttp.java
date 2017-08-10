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

    public static int loginNew(String lastName, String firstName, String sex, String countryId, String regEmail, String registrationPassword) throws IOException
    {

        HttpPost loginRequest = new HttpPost("http://rise-dev.naumen.ru/registration");
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

}
