package Controllers;

import FighterZ.Rest.FighterZServerResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RESTController {
    private final Gson gson = new Gson();
    private static final String URL = "http://localhost:8088/FighterZ";

    public FighterZServerResponse executeQueryPost(Object object, String queryPost){
        final String query = URL + queryPost;

        // Execute the HTTP POST request
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;

        try {
            params = new StringEntity(gson.toJson(object));
            httpPost.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            System.out.println((ex));
            ex.printStackTrace();
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            return gson.fromJson(entityString, FighterZServerResponse.class);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return executeHttpUriRequest(httpPost);
    }

    private FighterZServerResponse executeHttpUriRequest(HttpUriRequest httpUriRequest) {

        // Execute the HttpUriRequest
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            return gson.fromJson(entityString, FighterZServerResponse.class);
        }
        catch (IOException e) {
            System.out.println("IO Exception: " + e);
            FighterZServerResponse chessServerResponse = new FighterZServerResponse();
            chessServerResponse.setSuccess(false);
            return chessServerResponse;
        }
        catch (JsonSyntaxException e) {
            System.out.println("JSON Syntax Exception: " + e);
            FighterZServerResponse fighterZServerResponse = new FighterZServerResponse();
            fighterZServerResponse.setSuccess(false);
            return fighterZServerResponse;
        }
    }

}
