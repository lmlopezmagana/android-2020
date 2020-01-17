package android.salesianostriana.com.ejercicio_effects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.salesianostriana.com.ejercicio_effects.api.RandomPhotoApi;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView);

        new RandomPhotoTask().execute();


    }


    private class RandomPhotoTask extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            RandomPhotoApi api = new RandomPhotoApi("1ba15a0963d1cb11ce52aafd4c751da5bcc614f44524c0f6a79a9a957219bce1");

            String result = api.getRandomPhoto();

            Log.d("Photo", result);

            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            Glide.with(MainActivity.this).load(s).into(img);
        }
    }
}
