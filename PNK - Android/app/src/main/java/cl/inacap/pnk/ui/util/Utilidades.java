package cl.inacap.pnk.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.ui.Inicio;
import cl.inacap.pnk.ui.Login;

public class Utilidades {
    public String obtenerNombreDeDispositivo() {
        String fabricante = Build.MANUFACTURER;
        String modelo = Build.MODEL;
        if (modelo.startsWith(fabricante)) {
            return primeraLetraMayuscula(modelo);
        } else {
            return primeraLetraMayuscula(fabricante) + " " + modelo;
        }
    }

    private String primeraLetraMayuscula(String cadena) {
        if (cadena == null || cadena.length() == 0) {
            return "";
        }
        char primeraLetra = cadena.charAt(0);
        if (Character.isUpperCase(primeraLetra)) {
            return cadena;
        } else {
            return Character.toUpperCase(primeraLetra) + cadena.substring(1);
        }
    }

    public String getPublicIPAddress(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = cm.getActiveNetworkInfo();

        RunnableFuture<String> futureRun = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                if ((info != null && info.isAvailable()) && (info.isConnected())) {
                    StringBuilder response = new StringBuilder();

                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) (
                                new URL("http://checkip.amazonaws.com/").openConnection());
                        urlConnection.setRequestProperty("User-Agent", "Android-device");
                        //urlConnection.setRequestProperty("Connection", "close");
                        urlConnection.setReadTimeout(15000);
                        urlConnection.setConnectTimeout(15000);
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setRequestProperty("Content-type", "application/json");
                        urlConnection.connect();

                        int responseCode = urlConnection.getResponseCode();

                        if (responseCode == HttpURLConnection.HTTP_OK) {

                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                            String line;
                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }

                        }
                        urlConnection.disconnect();
                        return response.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //Log.w(TAG, "No network available INTERNET OFF!");
                    return null;
                }
                return null;
            }
        });

        new Thread(futureRun).start();

        try {
            return futureRun.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getHoraActual(){
        TimeZone tz = TimeZone.getTimeZone("GMT-04:00");
        Calendar c = Calendar.getInstance(tz);
        String hora= c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
        return hora;
    }

    public String getFechaActual(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date =new Date();
        String fecha=dateFormat.format(date);
        return fecha;
    }

    public void cerrarSesion(final Context context, Activity activity){
        SharedPreferences preferences=activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id_sesion=preferences.getInt("id_sesion",0);
        String url="http://34.232.87.118/BD_PNK/sesion.php?opcion=3&id_sesion="+id_sesion;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,"Sesion cerrada",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void verificarSesion(final Context context, final Activity activity){
        final SharedPreferences preferences=activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id_sesion=preferences.getInt("id_sesion",0);
        int id_cuenta=preferences.getInt("id_cuenta",0);
        String url="http://34.232.87.118/BD_PNK/sesion.php?opcion=4&id_sesion="+id_sesion+"&id_cuenta="+id_cuenta;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        js = response.getJSONObject(i);
                        int id_sesion=js.getInt("ID_SESION");
                        if(id_sesion==0){
                            cerrarSesion(context,activity);
                            preferences.edit().remove("id_persona").commit();
                            preferences.edit().remove("id_cuenta").commit();
                            activity.startActivity(new Intent(context, Login.class));
                            activity.finish();
                            Toast.makeText(context,"Tu sesión a caducado",Toast.LENGTH_LONG).show();
                        }else{

                        }
                    } catch (JSONException e) {
                        Toast.makeText(context,"Error: "+e,Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
