package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Nuevo_Registro extends AppCompatActivity {

    public static final
    String IP = "10.0.2.2";
    public static final String sitio = "webservice";


    Button btnconsultar, btnGuardar;
    EditText codigo, nombre, apellido,area,marca,serial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo__registro);


        //Poner el icono

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher1);


        codigo = findViewById(R.id.cod);
        nombre = findViewById(R.id.nom);
        apellido = findViewById(R.id.ape);
        area = findViewById(R.id.are);
        marca = findViewById(R.id.marc);
        serial= findViewById(R.id.ser);
        btnGuardar =findViewById(R.id.btnGuardar);
        int c = 1;

        btnGuardar.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              new Nuevo_Registro.CargarDatos().execute("http://" + IP + "/" + sitio +
                                                      "/guardar_perfil2.php?" +
                                                      "&cod="+ codigo.getText().toString() +
                                                      "&nom=" + nombre.getText().toString()+
                                                      "&ape=" + apellido.getText().toString() +
                                                      "&area=" + area.getText().toString()+
                                                      "&marc=" + marca.getText().toString() +
                                                      "&serial=" + serial.getText().toString()

                                              );

                                          }
                                      }
        );
    }
    private class ConsultarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
// params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }@Override
        protected void onPostExecute(String result) {
            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                codigo.setText(ja.getString(0));
                nombre.setText(ja.getString(1));
                apellido.setText(ja.getString(2));
                area.setText(ja.getString(3));
                marca.setText(ja.getString(4));
                serial.setText(ja.getString(5));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
// params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "Los datos se guardaron Ã©xitosamente", Toast.LENGTH_LONG).show();
        }
    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
// Only display the first 500 characters of the retrieved
// web page content.
        int len = 500;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
// Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();
// Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;
// Makes sure that the InputStream is closed after the app is
// finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}

