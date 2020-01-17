package android.salesianostriana.com.ejercicio_effects.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RandomPhotoApi {

    private String client_id;
    private OkHttpClient client;

    public RandomPhotoApi(String client_id) {
        this.client_id = client_id;
        client = new OkHttpClient();
    }

    public String getRandomPhoto() {

        String url = "https://api.unsplash.com/photos/random/?client_id=" + client_id;

        try {

            Response response = client.newCall(
                    new Request.Builder()
                    .url(url)
                    .build()
            ).execute();

            JSONObject jsonObject = new JSONObject(response.body().string());

            JSONObject json_urls = jsonObject.getJSONObject("urls");

            return json_urls.getString("regular");


        } catch (IOException ex) {
            Log.e("NASA", "IOException: " + ex.getMessage());
            return null;
        } catch (JSONException ex) {
            Log.e("NASA", "JSONException: " + ex.getMessage());
            return null;
        }



    }


}
