package com.salesianostriana.dam.a17_downloadfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ¡OJO! Para ver este ejemplo completo, no solo hay que consultar este fichero, sino también el manifiesto,
 * donde se establece el proveedor de contenido, así como el fichero res > xml > provider_paths.xml. Para
 * más información a este respecto se puede consultar la siguiente URL: https://code.i-harness.com/es/q/246e3da
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new DownloadTask().execute("https://rutacultural.com/wp-content/uploads/2017/06/iglesia-santa-maria-de-los-reales-alcazares-ubeda.jpg");

    }


    /**
     * Clase que es capaz de descagar un fichero desde una URL,
     * y lo trata de compartir.
     */
    private class DownloadTask extends AsyncTask<String, Void, File> {

        @Override
        protected File doInBackground(String... strings) {
            String url = strings[0];
            File result = null;
            try {
                result = downloadFile(url, MainActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return result;
            }
        }

        @Override
        protected void onPostExecute(File file) {

            // Obtenemos la URI a exponer a partir del fichero
            Uri myPhotoFileUri = FileProvider.getUriForFile(MainActivity.this, MainActivity.this.getApplicationContext().getPackageName() + ".provider", file);

            // Compartimos
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Este es un texto adjunto");
            shareIntent.putExtra(Intent.EXTRA_STREAM, myPhotoFileUri);
            // Esta parte sirve para obtener, a partir de la extensión del fichero, el tipo mime
            String type = null;
            String extension = MimeTypeMap.getFileExtensionFromUrl(file.getAbsolutePath());
            if (extension != null) {
                type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            }
            shareIntent.setType(type);
            startActivity(Intent.createChooser(shareIntent, "Enviar"));
        }
    }

    /**
     * Método que es capaz de descargar el fichero ubicado en la URL, y lo guarda en un fichero temporal en la caché
     * @param url
     * @param ctx
     * @return
     * @throws IOException
     */
    public File downloadFile(String url, Context ctx) throws IOException {
        final OkHttpClient client = new OkHttpClient();

        // Montamos la petición
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Ejecutamos la petición
        try (Response response = client.newCall(request).execute()) {
            // Si hay error, excepción
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // En otro caso...

            // Obtenemos, a partir de las cabeceras, el tipo mime, y de él, la extensión
            Headers headers = response.headers();
            String contentType = headers.get("Content-Type");
            String suffix = "." + MimeTypeMap.getSingleton().getExtensionFromMimeType(contentType);

            // "Armamos" lo necesario para leer el fichero vía flujos
            InputStream is = response.body().byteStream();
            // Se guarda en un fichero temporal, dentro de la caché externa
            File file = File.createTempFile("img", suffix, ctx.getExternalCacheDir());
            BufferedInputStream input = new BufferedInputStream(is);
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));

            // Bucle clásico de lectura de un fichero en grupos de 4KB
            byte[] data = new byte[4*1024];

            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
            }

            // Cierre de flujos
            output.flush();
            output.close();
            input.close();

            return file;

        }
    }

}
