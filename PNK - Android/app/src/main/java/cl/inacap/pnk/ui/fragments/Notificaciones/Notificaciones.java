package cl.inacap.pnk.ui.fragments.Notificaciones;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Notificacion;
import cl.inacap.pnk.ui.util.AdapterNotificacion;

/**
 * Clase del layout fragment_notificaciones
 * A simple {@link Fragment} subclass.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Notificaciones extends Fragment {
    //Campos de la clase
    private RecyclerView recyclerView;
    private AdapterNotificacion adapterNotificacion;
    private LottieAnimationView lottieAnimationView;
    private Spinner spinnerFiltro;
    private List<Notificacion> notificacionList;

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
        //Asignación de la relación vista y clase
        View root=inflater.inflate(R.layout.fragment_notificaciones, container, false);
        lottieAnimationView=(LottieAnimationView)root.findViewById(R.id.animationView);
        spinnerFiltro=(Spinner)root.findViewById(R.id.sp_filtro);
        recyclerView=(RecyclerView)root.findViewById(R.id.recyclerNotificaciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificacionList=new ArrayList<>();

        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        adapterNotificacion=new AdapterNotificacion(notificacionList);
                        recyclerView.setAdapter(adapterNotificacion);
                        break;
                    case 1:
                        List<Notificacion> list=new ArrayList<>();
                        for(int i=0;i<notificacionList.size();i++){
                            if(notificacionList.get(i).getTipoNotificacion().equals("Solicitud de visita")){
                                list.add(notificacionList.get(i));
                            }
                        }
                        if(list.size()!=0){
                            adapterNotificacion=new AdapterNotificacion(list);
                            recyclerView.setAdapter(adapterNotificacion);
                        }else{
                            Toast.makeText(getContext(),"Sin notificaciones de encomienda",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        List<Notificacion> list2=new ArrayList<>();
                        for(int i=0;i<notificacionList.size();i++){
                            if(!notificacionList.get(i).getTipoNotificacion().equals("Solicitud de visita")){
                                list2.add(notificacionList.get(i));
                            }
                        }
                        if(list2.size()!=0){
                            adapterNotificacion=new AdapterNotificacion(list2);
                            recyclerView.setAdapter(adapterNotificacion);
                        }else{
                            Toast.makeText(getContext(),"Sin notificaciones de encomienda",Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cargarVisitas();
        return root;
    }

    private void cargarVisitas(){
        final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"solicitud_visitas.php?opcion=1&id_cuenta="+id_cuenta;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(JSONArray response) {
                cargarEncomiendas();
                JSONObject js = null;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        js = response.getJSONObject(i);
                        Notificacion notificacion=new Notificacion();
                        notificacion.setIdNotificacion(js.getInt("ID_SOLICITUD_VISITA"));
                        notificacion.setEstado(js.getInt("ESTADO_SOLICITUD_VISITA"));
                        notificacion.setDetalleNotificacion(js.getString("NOMBRE")+" "+js.getString("APE_PATERNO"));
                        notificacion.setFechaNotificacion(dateFormat.parse(js.getString("FECHA_VISITA")));
                        notificacion.setHoraNotificacion(js.getString("HORA_VISITA"));
                        notificacion.setTipoNotificacion("Solicitud de visita");
                        notificacionList.add(notificacion);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
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

    private void cargarEncomiendas(){
        final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"solicitud_encomienda.php?opcion=1&id_cuenta="+id_cuenta;
        JsonArrayRequest jsonArrayRequest2=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        js = response.getJSONObject(i);
                        Notificacion notificacion=new Notificacion();
                        notificacion.setIdNotificacion(js.getInt("ID_SOLICITUD_ENCOMIENDA"));
                        notificacion.setEstado(js.getInt("ESTADO_SOLICITUD_ENCOMIENDA"));
                        notificacion.setFechaNotificacion(dateFormat.parse(js.getString("FECHA_ENTREGA")));
                        notificacion.setHoraNotificacion(js.getString("HORA_ENTREGA"));
                        notificacion.setTipoNotificacion("Solicitud de encomienda");
                        notificacionList.add(notificacion);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Collections.sort(notificacionList, new Comparator<Notificacion>() {
                    public int compare(Notificacion o1, Notificacion o2) {
                        int resultado =o1.getFechaNotificacion().compareTo(o2.getFechaNotificacion());
                        if(resultado!=0){
                            return resultado;
                        }
                        resultado=o1.getHoraNotificacion().compareTo(o2.getHoraNotificacion());
                        if(resultado!=0){
                            return resultado;
                        }
                        return resultado;
                    }
                });
                Collections.reverse(notificacionList);
                adapterNotificacion=new AdapterNotificacion(notificacionList);
                recyclerView.setAdapter(adapterNotificacion);
                lottieAnimationView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
                Collections.sort(notificacionList, new Comparator<Notificacion>() {
                    public int compare(Notificacion o1, Notificacion o2) {
                        int resultado =o1.getFechaNotificacion().compareTo(o2.getFechaNotificacion());
                        if(resultado!=0){
                            return resultado;
                        }
                        resultado=o1.getHoraNotificacion().compareTo(o2.getHoraNotificacion());
                        if(resultado!=0){
                            return resultado;
                        }
                        return resultado;
                    }
                });
                Collections.reverse(notificacionList);
                adapterNotificacion=new AdapterNotificacion(notificacionList);
                recyclerView.setAdapter(adapterNotificacion);
                lottieAnimationView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest2);
    }

}
