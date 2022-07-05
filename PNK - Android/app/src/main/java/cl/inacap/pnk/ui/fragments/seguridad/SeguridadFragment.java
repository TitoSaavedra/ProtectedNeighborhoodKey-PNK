package cl.inacap.pnk.ui.fragments.seguridad;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Sesion;
import cl.inacap.pnk.ui.util.AdapterSesion;

/**
 * Clase del layaout fragment_seguridad
 * {@link Fragment} sub clase.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class SeguridadFragment extends Fragment {

    //Propiedades
    private TextView tvEstado;
    private Button btnDesbloquear;
    private Button btnBloquear;
    private AdapterSesion adapterSesion;
    private RecyclerView recyclerView;
    private List<Sesion> sesionList;

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
        View root=inflater.inflate(R.layout.fragment_seguridad, container, false);
        tvEstado=root.findViewById(R.id.tv_estado);
        btnBloquear=root.findViewById(R.id.btn_bloquear);
        btnDesbloquear=root.findViewById(R.id.btn_desbloquear);
        recyclerView=(RecyclerView)root.findViewById(R.id.recyclerSesion);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sesionList=new ArrayList<>();
        cargarSesiones();
        cargarDatosTarjeta();

        btnBloquear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloquear();
            }
        });

        btnDesbloquear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desbloquear();
            }
        });

        return root;
    }

    private void bloquear(){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String uid=preferences.getString("uid","");
        String url=getString(R.string.server)+"tarjeta_nfc.php?opcion=3&uid="+uid;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                btnBloquear.setEnabled(false);
                btnDesbloquear.setEnabled(true);
                btnDesbloquear.getBackground().setTint(getResources().getColor(R.color.colorVerde));
                btnBloquear.getBackground().setTint(getResources().getColor(R.color.colorRojoDegradado));
                tvEstado.setText("Estado: Bloqueado");
                Toast.makeText(getActivity().getApplicationContext(),"Tarjeta NFC bloqueada",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void desbloquear(){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String uid=preferences.getString("uid","");
        String url=getString(R.string.server)+"tarjeta_nfc.php?opcion=2&uid="+uid;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                btnBloquear.setEnabled(true);
                btnDesbloquear.setEnabled(false);
                btnDesbloquear.getBackground().setTint(getResources().getColor(R.color.colorVerdeDegradado));
                btnBloquear.getBackground().setTint(getResources().getColor(R.color.colorRojo));
                tvEstado.setText("Estado: Activo");
                Toast.makeText(getActivity().getApplicationContext(),"Tarjeta NFC desbloqueada",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void cargarDatosTarjeta(){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String uid=preferences.getString("uid","");
        String url=getString(R.string.server)+"tarjeta_nfc.php?opcion=1&uid="+uid;
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
                        int estado=js.getInt("ESTADO_TARJETA_NFC");
                        if(estado==1){
                            tvEstado.setText("Estado: Activo");
                            btnDesbloquear.setEnabled(false);
                            btnBloquear.setEnabled(true);
                            btnDesbloquear.getBackground().setTint(getResources().getColor(R.color.colorVerdeDegradado));
                        }else{
                            tvEstado.setText("Estado: Bloqueado");
                            btnBloquear.setEnabled(false);
                            btnDesbloquear.setEnabled(true);
                            btnBloquear.getBackground().setTint(getResources().getColor(R.color.colorRojoDegradado));
                        }
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
    }

    public void cargarSesiones(){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int idCuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"sesion.php?opcion=2&id_cuenta="+idCuenta;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    for (int i = 0; i < response.length(); i++) {
                        js = response.getJSONObject(i);
                        Sesion sesion=new Sesion();
                        sesion.setIdSesion(js.getInt("ID_SESION"));
                        sesion.setDispositivo(js.getString("DISPOSITIVO"));
                        sesion.setFechaSesion(dateFormat.parse(js.getString("FECHA_SESION")));
                        sesion.setHoraSesion(js.getString("HORA_SESION"));
                        sesion.setDireccionIp(js.getString("DIRECCION_IP"));
                        sesion.setIdCuenta(js.getInt("ID_CUENTA"));
                        sesionList.add(sesion);
                    }
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
                adapterSesion=new AdapterSesion(sesionList);
                recyclerView.setAdapter(adapterSesion);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

}
