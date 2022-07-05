package cl.inacap.pnk.ui.fragments.perfil;


import android.content.Context;
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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;

/**
 * Clase del layout fragment_perfil_actualizar
 * A simple {@link Fragment} subclass.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class PerfilActualizar extends Fragment {

    private ImageView imgPerfil;
    private TextView tvNombreCompleto;
    private EditText etEmailNuevo;
    private EditText etTelefonoNuevo;
    private TextView tvEmailActual;
    private TextView tvTelefonoActual;
    private Button btnActualizar;
    private Bundle bundle;

    //Constructor de la clase
    public PerfilActualizar() {
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
        final View root= inflater.inflate(R.layout.fragment_perfil_actualizar, container, false);
        imgPerfil=(ImageView)root.findViewById(R.id.img_perfil);
        etEmailNuevo=(EditText)root.findViewById(R.id.et_email_nuevo);
        etTelefonoNuevo=(EditText)root.findViewById(R.id.et_telefono_nuevo);
        tvEmailActual=(TextView)root.findViewById(R.id.tv_email_actual);
        tvTelefonoActual=(TextView)root.findViewById(R.id.tv_telefono_actual);
        tvNombreCompleto=(TextView)root.findViewById(R.id.tv_nombre_completo);
        btnActualizar=(Button)root.findViewById(R.id.btn_actualizarDatosPerfil);
        bundle=getArguments();


        final SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        cargarDatosLocal();
        cargarDatos();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=preferences.getInt("id_persona",0);
                String correo=etEmailNuevo.getText().toString();
                String telefono=etTelefonoNuevo.getText().toString();
                if(!correo.equals("") || !telefono.equals("")){
                    modificarPerfil(id,correo,telefono);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Complete almenos un campo",Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

    private void cargarDatosLocal(){
        tvEmailActual.setText("Email: "+bundle.getString("email"));
        tvTelefonoActual.setText("Telefono: "+bundle.getInt("telefono"));
        tvNombreCompleto.setText(bundle.getString("nombre")+" "+bundle.getString("ape_paterno")+" "+bundle.getString("ape_materno"));
        byte[] bytes= Base64.decode(bundle.getString("foto"),Base64.DEFAULT);
        Bitmap imagen= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imgPerfil.setImageBitmap(imagen);
    }

    private void cargarDatos() {
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
                        tvEmailActual.setText("Email: "+js.getString("EMAIL"));
                        tvTelefonoActual.setText("Telefono: "+js.getString("TELEFONO"));
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

    private void modificarPerfil(final int id, final String correo, final String telefono){
        String url=getString(R.string.server)+"persona.php?";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(String response) {
                cargarDatos();
                Toast.makeText(getActivity().getApplicationContext(),"Actualización exitosa",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<>();
                parametros.put("id_persona",id+"");
                if (correo.length()>1 && telefono.length()>1) {
                    parametros.put("opcion",""+4);
                    parametros.put("telefono",telefono);
                    parametros.put("correo",correo);
                    etEmailNuevo.setText("");
                    etTelefonoNuevo.setText("");
                }else{
                    if(correo.length()>1){
                        parametros.put("opcion",""+3);
                        parametros.put("correo",correo);
                    }else{
                        if(telefono.length()>1){
                            parametros.put("opcion",""+2);
                            parametros.put("telefono",telefono);
                        }
                    }
                }
                return parametros;
            }
        };
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
