package cl.inacap.pnk.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Cuenta;
import cl.inacap.pnk.io.entidadesDAO.Persona;
import cl.inacap.pnk.io.entidadesDAO.TarjetaNFC;
import cl.inacap.pnk.io.entidadesDAO.TipoPersona;
import cl.inacap.pnk.ui.fragments.home.HomeFragment;
import cl.inacap.pnk.ui.util.Utilidades;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Clase del layout activity_login
 * @Autor Pablo Ahumada Olivares
 * @Version 1.0
 */
public class Login extends AppCompatActivity{
    //Campos de la clase
    private EditText etCorreo,etClave;
    private Button btnIniciarSesion;
    private LottieAnimationView lottieAnimationView;

    /**
     * Metodo de creacion de relacion clase y layout
     * @param savedInstanceState instacia de la aplicación
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Asignacion de relacion de vista y clase
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etCorreo=(EditText) findViewById(R.id.ed_correo);
        etClave=(EditText) findViewById(R.id.ed_clave);
        btnIniciarSesion=(Button) findViewById(R.id.btn_iniciar);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie_login);

        SharedPreferences sharedPreferences=cargarPreferencias();
        if(sharedPreferences.getInt("id_persona",0)!=0){
            startActivity(new Intent(getApplicationContext(),new Inicio().getClass()));
            finish();
        }
        /**
         * Metodo Asignacion de onClick del boton iniciar sesión
         * Para verificar si el correo y contraseña son validos con una cuenta
         */
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * Metodo onClick del botón iniciar sesión
             */
            public void onClick(View v) {
                if(etCorreo.getText().toString().equals("")|| etClave.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Complete los campos",Toast.LENGTH_LONG).show();
                }else{
                    String url=getString(R.string.server)+"login.php?correo="+etCorreo.getText().toString()+"&clave="+etClave.getText().toString();
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    etCorreo.setEnabled(false);
                    etClave.setEnabled(false);
                    btnIniciarSesion.setEnabled(false);
                    btnIniciarSesion.setText("");
                    iniciarSesion(url);
                }
            }
        });
    }

    /**
     * Metodo para comprobar los datos del login
     * @param URL Direccion al web service en el servidor
     */
    private void iniciarSesion(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        js = response.getJSONObject(i);
                        TipoPersona tipoPersona=new TipoPersona();
                        tipoPersona.setId_tipo_persona(js.getInt("ID_TIPO_PERSONA"));
                        Persona persona=new Persona(js.getInt("ID_PERSONA"),js.getInt("ESTADO_PERSONA"),js.getString("RUT"),
                                js.getString("NOMBRE"),js.getString("SEG_NOMBRE"),js.getString("APE_PATERNO"),
                                js.getString("APE_MATERNO"),js.getString("EMAIL"),js.getInt("TELEFONO"),tipoPersona);
                        TarjetaNFC tarjetaNFC=new TarjetaNFC(js.getString("UID"));
                        byte[] bytes= Base64.decode(js.getString("FOTO"),Base64.DEFAULT);
                        Bitmap imagen= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        Cuenta cuenta=new Cuenta(js.getInt("ID_CUENTA"),js.getInt("ESTADO_CUENTA"),js.getString("CLAVE"),imagen,persona,tarjetaNFC);
                        if(!persona.getCorreo().equals("")){
                            if(cuenta.getEstado()==1){
                                guardarPreferencias(persona.getIdPersona(),cuenta.getIdCuenta(),tarjetaNFC.getUID());
                                crearSesion(cuenta.getIdCuenta());
                            }else{
                                lottieAnimationView.setVisibility(View.GONE);
                                etCorreo.setEnabled(true);
                                etClave.setEnabled(true);
                                btnIniciarSesion.setEnabled(true);
                                btnIniciarSesion.setText(R.string.iniciarSesion);
                                Toast.makeText(getApplicationContext(),"Tu cuenta esta inhabilitada",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            lottieAnimationView.setVisibility(View.GONE);
                            etCorreo.setEnabled(true);
                            etClave.setEnabled(true);
                            btnIniciarSesion.setEnabled(true);
                            btnIniciarSesion.setText(R.string.iniciarSesion);
                            Toast.makeText(getApplicationContext(),"Correo y/o contraseña incorrectos",Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        lottieAnimationView.setVisibility(View.GONE);
                        etCorreo.setEnabled(true);
                        etClave.setEnabled(true);
                        btnIniciarSesion.setEnabled(true);
                        btnIniciarSesion.setText(R.string.iniciarSesion);
                        Toast.makeText(getApplicationContext(),"error 1: "+e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lottieAnimationView.setVisibility(View.GONE);
                etCorreo.setEnabled(true);
                etClave.setEnabled(true);
                btnIniciarSesion.setEnabled(true);
                btnIniciarSesion.setText(R.string.iniciarSesion);
                Toast.makeText(getApplicationContext(),"error 2: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void crearSesion(int idCuenta){
        Utilidades utilidades=new Utilidades();
        String ip=utilidades.getPublicIPAddress(getApplicationContext());
        String dispositivo=utilidades.obtenerNombreDeDispositivo();
        String hora=utilidades.getHoraActual();
        String fecha=utilidades.getFechaActual();
        String url=getString(R.string.server)+"sesion.php?opcion=1&id_cuenta="+idCuenta+"&fecha_sesion="+fecha+"&hora_sesion="+hora+"&ip="+ip+"&dispositivo="+dispositivo;
        System.out.println(url);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                JSONObject js = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        js = response.getJSONObject(i);
                        editor.putInt("id_sesion",js.getInt("ID_SESION"));
                        editor.commit();
                        startActivity(new Intent(getApplicationContext(),Inicio.class));
                        finish();
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"Error: "+e,Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error de conexion: "+error,Toast.LENGTH_LONG).show();
                System.out.println(error);
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    /**
     * Metodo para cargar los datos de la cuenta de usuario
     * @return Preferencias creadas
     */
    private SharedPreferences cargarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        //String nom_usuario=preferences.getString("id_persona","No existe la informacion");
        return preferences;
    }

    /**
     * Metodo para guardar los datos de la cuenta de usuario
     * @param id_persona Id de la persona
     * @param id_cuenta Id de la cuentaa
     */
    public void guardarPreferencias(int id_persona,int id_cuenta,String uid){
        SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("id_persona",id_persona);
        editor.putInt("id_cuenta",id_cuenta);
        editor.putString("uid",uid);
        editor.commit();
    }



}
