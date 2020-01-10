package com.example.webnavigator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText url;
    Button go;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = findViewById(R.id.editText);
        go = findViewById(R.id.button);
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadData("<html><body><h1>Hola a todos!!!</h1></body></html>", "text/html", "UTF-8");

        go.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Hola Mundo", Toast.LENGTH_LONG).show();

            if (url.getText().toString().length() == 0) {
                Toast.makeText(MainActivity.this,
                        "Debes escribir una URL para poder navegar",
                        Toast.LENGTH_SHORT).show();
            } else {

                try {
                    URL laUrl = new URL(url.getText().toString());
                    new DownloadWebTask().execute(url.getText().toString());
                } catch (MalformedURLException ex) {
                    Toast.makeText(MainActivity.this,
                            "La URL que has escrito no es válida",
                            Toast.LENGTH_SHORT).show();
                }

            }




        });

    }

    private class DownloadWebTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            OkHttpClient client = new OkHttpClient();
            String result = null;

            try {
                Response response = client.newCall(new Request.Builder()
                        .url(url)
                        .build())
                        .execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("HTML", s);
            webView.loadData(s,"text/html", "UTF-8");
        }

    }


}
