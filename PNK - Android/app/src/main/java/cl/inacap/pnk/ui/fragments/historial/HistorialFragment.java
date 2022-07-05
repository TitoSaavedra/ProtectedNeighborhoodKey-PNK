package cl.inacap.pnk.ui.fragments.historial;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Acceso;
import cl.inacap.pnk.io.entidadesDAO.Cuenta;
import cl.inacap.pnk.io.entidadesDAO.SolicitudVisita;
import cl.inacap.pnk.io.entidadesDAO.TipoPersona;
import cl.inacap.pnk.io.entidadesDAO.Visita;
import cl.inacap.pnk.ui.util.AdapterAcceso;
import cl.inacap.pnk.ui.util.AdapterVisitas;

/**
 * Clase del layout fragment_historial
 * A simple {@link Fragment} subclass.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class HistorialFragment extends Fragment {

    //Campos de la clase
    private RecyclerView recyclerView;
    private AdapterAcceso adapterAcceso;
    private TextView tvMensaje;
    private LottieAnimationView lottieAnimationView;

    //Constructor de la clase
    public HistorialFragment() {
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
        //Asignación de relacion vista y clase
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_historial, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.recyclerAcceso);
        tvMensaje=(TextView) root.findViewById(R.id.tv_mensaje);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lottieAnimationView=(LottieAnimationView)root.findViewById(R.id.animationView);
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"acceso.php?opcion=1&id_cuenta="+id;
        historialAcceso(url);
        return root;
    }

    /**
     * Metodo para cargar el historial de acceso el cual llena una lista para colocarla en un {@link RecyclerView}
     * @param URL {@link String} con la direccion del webservice que devuelve los datos en json para llenar la lista
     */
    private void historialAcceso(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                List<Acceso> accesoList=new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        js = response.getJSONObject(i);
                        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        Acceso acceso=new Acceso(js.getInt("ID_ACCESO"));
                        acceso.setEstado(js.getInt("ESTADO_ACCESO"));
                        acceso.setFechaAcceso(dateFormat.parse(js.getString("FECHA_ACCESO")));
                        acceso.setHoraAcceso(js.getString("HORA_ACCESO"));
                        acceso.setTipoAcceso(js.getInt("TIPO_ACCESO"));
                        accesoList.add(acceso);
                    } catch (JSONException e) {
                        Toast.makeText(getActivity().getApplicationContext(),"error 1: "+e.toString(),Toast.LENGTH_LONG).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    adapterAcceso=new AdapterAcceso(accesoList);
                    recyclerView.setAdapter(adapterAcceso);
                    lottieAnimationView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvMensaje.setVisibility(View.VISIBLE);
                lottieAnimationView.setAnimation(R.raw.empty_data);
                lottieAnimationView.playAnimation();
                //Toast.makeText(getActivity().getApplicationContext(),"error 2: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

}
