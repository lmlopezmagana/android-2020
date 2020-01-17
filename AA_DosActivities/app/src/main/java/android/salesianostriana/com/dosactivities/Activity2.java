package android.salesianostriana.com.dosactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        String mensaje = getIntent().getExtras().getString("msg");

        TextView txt = findViewById(R.id.txt2);
        txt.setText(mensaje);
    }
}
