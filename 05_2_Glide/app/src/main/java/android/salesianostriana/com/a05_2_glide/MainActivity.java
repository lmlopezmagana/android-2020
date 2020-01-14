package android.salesianostriana.com.a05_2_glide;

import androidx.appcompat.app.AppCompatActivity;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    ImageView img1, img2, img3, img4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);


        // Original
        Glide
                .with(this)
                .load("https://uh.gsstatic.es/sfAttachPlugin/860817.jpg")
                .into(img1);

        // Una transformación
        Glide
                .with(this)
                .load("https://uh.gsstatic.es/sfAttachPlugin/860817.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(5, 2)))
                .into(img2);

        Glide
                .with(this)
                .load("https://uh.gsstatic.es/sfAttachPlugin/860817.jpg")
                .apply(RequestOptions.bitmapTransform(new SepiaFilterTransformation())) 
                .into(img3);

        Glide
                .with(this)
                .load("https://uh.gsstatic.es/sfAttachPlugin/860817.jpg")
                .apply(RequestOptions.bitmapTransform(new SketchFilterTransformation()))
                .into(img4);

    }
}
