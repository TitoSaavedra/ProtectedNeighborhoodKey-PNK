package cl.inacap.pnk.ui.fragments.perfil;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.ui.Login;

/**
 * Clase del layout fragment_perfil_actualizar_contra
 * A simple {@link Fragment} subclass.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class PerfilActualizarContra extends Fragment {
    //Campos de la clase
    private ImageView imgPerfil;
    private TextView tvNombreCompleto;
    private EditText etContraActual;
    private EditText etContraNueva;
    private EditText etContraVerificacion;
    private Button btnActualizarClave;
    private Bundle bundle;

    //Constructor de la clase
    public PerfilActualizarContra() {
        // Required empty public constructor
    }

    /**
     * Metodo onCreate de la clase para la relacion vista y clase
     * @param inflater para realizar la relacion con el layout correcpondiente
     * @param container contenedor del layout
     * @param savedInstanceState instancia de la aplicación
     * @return la vista asignada
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cargarDatos();
        View root= inflater.inflate(R.layout.fragment_perfil_actualizar_contra, container, false);
        imgPerfil=(ImageView)root.findViewById(R.id.img_perfil);
        tvNombreCompleto=(TextView) root.findViewById(R.id.tv_nombre_completo);
        etContraActual=(EditText)root.findViewById(R.id.et_contra_actual);
        etContraNueva=(EditText)root.findViewById(R.id.et_contra_nueva);
        etContraVerificacion=(EditText)root.findViewById(R.id.et_confirmar_contra);
        btnActualizarClave=(Button)root.findViewById(R.id.btn_actualizar_clave);
        bundle=getArguments();

        tvNombreCompleto.setText(bundle.getString("nombre")+" "+bundle.getString("ape_paterno")+" "+bundle.getString("ape_materno"));
        byte[] bytes= Base64.decode(bundle.getString("foto"),Base64.DEFAULT);
        Bitmap imagen= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imgPerfil.setImageBitmap(imagen);

        btnActualizarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String claveActual=etContraActual.getText().toString();
                String claveNueva=etContraNueva.getText().toString();
                String claveVerficacion=etContraVerificacion.getText().toString();
                if(!claveActual.equals("") && !claveNueva.equals("") && !claveVerficacion.equals("")){
                    if(claveNueva.equals(claveVerficacion)){
                        btnActualizarClave.setClickable(false);
                        cambiarClave(claveNueva,claveActual);
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Debe completar los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }

    /**
     * Metodo para cargar los datos del usuario actual en la aplicacion
     */
    private void cargarDatos(){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id=preferences.getInt("id_persona",0);
        String url=getString(R.string.server)+"persona.php?opcion=1&id_persona="+id;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        js = response.getJSONObject(0);
                        tvNombreCompleto.setText(js.getString("NOMBRE")+" "+js.getString("APE_PATERNO")+" "+js.getString("APE_MATERNO"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest);
        int id_cuenta=preferences.getInt("id_cuenta",0);
        url=getString(R.string.server)+"cuenta.php?opcion=1&id_cuenta="+id_cuenta;
        JsonArrayRequest jsonArrayRequest1=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        js = response.getJSONObject(0);
                        byte[] bytes= Base64.decode(js.getString("FOTO"),Base64.DEFAULT);
                        Bitmap imagen= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        imgPerfil.setImageBitmap(imagen);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest1);
    }

    /**
     * Metodo para cambiar la Clave del usuario
     * @param claveNueva nueva clave para la cuenta
     * @param claveAntigua clave antigua de la cuenta
     */
    private void cambiarClave(String claveNueva,String claveAntigua){
        final SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"cuenta.php?opcion=2&id_cuenta="+id_cuenta+"&clave_nueva="+claveNueva+"&clave_antigua="+claveAntigua;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(String response) {
                String respuesta=response;
                    if(respuesta.equals("actualizado")){
                        Toast.makeText(getActivity().getApplicationContext(),"Clave cambiada, debe ingresar nuevamente",Toast.LENGTH_LONG).show();
                        preferences.edit().remove("id_persona").commit();
                        preferences.edit().remove("id_cuenta").commit();
                        startActivity(new Intent(getActivity().getApplicationContext(), Login.class));
                        getActivity().finish();
                    }else{
                        if(respuesta.equals("no actualizado")){
                            Toast.makeText(getActivity().getApplicationContext(),"Clave actual incorrecta",Toast.LENGTH_LONG).show();
                            btnActualizarClave.setClickable(true);
                        }
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
