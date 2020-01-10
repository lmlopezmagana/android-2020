package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.texto1);

        //txt.setText("Hola Dammers!!!");

/*
        Thread contador = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <= 10; i++) {
                    txt.setText(Integer.toString(i));
                    Log.d("Valor", "Contador: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        );

        contador.start();
*/


        new CounterTask().execute();

    }


    private class CounterTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            txt.setText("");
        }


        @Override
        protected Boolean doInBackground(Void... voids) {

            for(int i = 0; i <= 10; i++) {
                publishProgress(i);
                Log.d("Valor", "Contador: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return true;

        }

        @Override
        protected void onProgressUpdate(Integer... pepes) {
            txt.setText(""+pepes[0]);
        }



        @Override
        protected void onPostExecute(Boolean bool) {
            txt.setText("FINAL");
        }
    }

    class OtroTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}
