package examples;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RedmineClient {

    private String url;
    private OkHttpClient client;

    public RedmineClient(String url) {
        this.url = url;
        client = new OkHttpClient();
    }

    public int getIssueCount() throws IOException, JSONException {
        Request request = new Request.Builder()
                .url(url + "/issues.json")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject.getInt("total_count");
    }
}