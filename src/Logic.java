import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Logic {


    static String getRequest(String date, String currencySymbol) throws IOException {

        if (date.equals("")) {
            date = "latest";
        }
        String url = "http://data.fixer.io/api/" + date + "?access_key=5f197ac23be347b1a0531ff6faeb3d4f&symbols=" + currencySymbol;

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url);

        // add request header
        getRequest.addHeader("User-Agent", "Mozilla");
        HttpResponse getResponse = client.execute(getRequest);

        System.out.println("Response Code : "
                + getResponse.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(getResponse.getEntity().getContent()));

        StringBuffer queryResult = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            queryResult.append(line);
        }

        String apiResponse = queryResult.toString();
        JsonElement jElement = new JsonParser().parse(apiResponse);
        JsonObject jObject = jElement.getAsJsonObject();
        jObject = jObject.getAsJsonObject("rates");

        return jObject.get(currencySymbol).getAsString();
    }

}
