package cl.inacap.pnk.ui.fragments.visitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Cuenta;
import cl.inacap.pnk.io.entidadesDAO.Persona;
import cl.inacap.pnk.io.entidadesDAO.SolicitudVisita;
import cl.inacap.pnk.io.entidadesDAO.TarjetaNFC;
import cl.inacap.pnk.io.entidadesDAO.TipoPersona;
import cl.inacap.pnk.io.entidadesDAO.Visita;
import cl.inacap.pnk.ui.Inicio;
import cl.inacap.pnk.ui.util.AdapterVisitas;

/**
 * Clase del layout fragment_visitantes
 * {@link Fragment} sub clase
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class VisitasFragment extends Fragment {

    //Campos de la clase
    private RecyclerView recyclerView;
    private AdapterVisitas adapterVisita;
    private TextView tvMensaje;
    private LottieAnimationView lottieAnimationView;

    /**
     * Metodo onCreate de la clase para la relacion vista y clase
     * @param inflater para realizar la relacion con el layout correcpondiente
     * @param container contenedor del layout
     * @param savedInstanceState instancia de la aplicación
     * @return la vista asignada
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Asignación de relacion de vista y clase
        View root = inflater.inflate(R.layout.fragment_visitantes, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.recyclerVisitas);
        tvMensaje=(TextView)root.findViewById(R.id.tv_mensaje);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lottieAnimationView=(LottieAnimationView)root.findViewById(R.id.animationView);


        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"solicitud_visitas.php?opcion=2&id_cuenta="+id;
        buscarVisitas(url);
        return root;
    }


    private void buscarVisitas(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                List<SolicitudVisita> solicitudVisitaList=new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        js = response.getJSONObject(i);
                        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        Visita visita=new Visita(js.getInt("ID_PERSONA"),js.getInt("ESTADO_PERSONA"),js.getString("RUT"),
                                js.getString("NOMBRE"),js.getString("APE_PATERNO"),
                                js.getString("APE_MATERNO"),new TipoPersona(3));
                        SolicitudVisita solicitudVisita=new SolicitudVisita();
                        solicitudVisita.setVisita(visita);
                        solicitudVisitaList.add(solicitudVisita);
                    } catch (JSONException e) {
                        Toast.makeText(getActivity().getApplicationContext(),"error 1: "+e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                tvMensaje.setVisibility(View.GONE);
                adapterVisita=new AdapterVisitas(solicitudVisitaList);
                recyclerView.setAdapter(adapterVisita);
                lottieAnimationView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
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