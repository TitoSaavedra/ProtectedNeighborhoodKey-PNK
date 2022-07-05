package cl.inacap.pnk.ui.fragments.visitas;

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
import cl.inacap.pnk.io.entidadesDAO.Cuenta;
import cl.inacap.pnk.io.entidadesDAO.SolicitudVisita;
import cl.inacap.pnk.io.entidadesDAO.TipoPersona;
import cl.inacap.pnk.io.entidadesDAO.Visita;
import cl.inacap.pnk.ui.util.AdapterVisitas;
import cl.inacap.pnk.ui.util.AdapterVisitasHistorial;


public class HistorialVisitaFragment extends Fragment {

    //Campos de la clase
    private RecyclerView recyclerView;
    private AdapterVisitasHistorial adapterVisitasHistorial;
    private TextView tvMensaje;
    private LottieAnimationView lottieAnimationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=(View)inflater.inflate(R.layout.fragment_historial_visita, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.recyclerHistorialVisitas);
        tvMensaje=(TextView)root.findViewById(R.id.tv_mensaje);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lottieAnimationView=(LottieAnimationView)root.findViewById(R.id.animationView);

        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"solicitud_visitas.php?opcion=1&id_cuenta="+id;
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
                        Cuenta cuenta=new Cuenta(js.getInt("ID_CUENTA"));
                        Visita visita=new Visita(js.getInt("ID_PERSONA"),js.getInt("ESTADO_PERSONA"),js.getString("RUT"),
                                js.getString("NOMBRE"),js.getString("APE_PATERNO"),
                                js.getString("APE_MATERNO"),new TipoPersona(3));
                        SolicitudVisita solicitudVisita=new SolicitudVisita();
                        solicitudVisita.setCuenta(cuenta);
                        solicitudVisita.setIdSolicitudVisita(js.getInt("ID_SOLICITUD_VISITA"));
                        solicitudVisita.setFechaVisita(dateFormat.parse(js.getString("FECHA_VISITA")));
                        solicitudVisita.setHoraVisita(js.getString("HORA_VISITA"));
                        solicitudVisita.setEstado(js.getInt("ESTADO_SOLICITUD_VISITA"));
                        solicitudVisita.setVisita(visita);
                        if(solicitudVisita.getEstado()==1){
                            solicitudVisitaList.add(solicitudVisita);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getActivity().getApplicationContext(),"error 1: "+e.toString(),Toast.LENGTH_LONG).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                tvMensaje.setVisibility(View.GONE);
                adapterVisitasHistorial=new AdapterVisitasHistorial(solicitudVisitaList);
                recyclerView.setAdapter(adapterVisitasHistorial);
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