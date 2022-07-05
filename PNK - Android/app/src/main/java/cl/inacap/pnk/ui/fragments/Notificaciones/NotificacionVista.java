package cl.inacap.pnk.ui.fragments.Notificaciones;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import java.util.Date;
import java.util.List;

import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Notificacion;

/**
 * Clase del layout fragment_notificacion_visita
 * A simple {@link Fragment} subclass.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class NotificacionVista extends Fragment {
    //Campos de la clase
    private TextView tvTipoNotificacion;
    private TextView tvTituloNotificacion;
    private TextView tvDetalle1;
    private TextView tvDetalle2;
    private TextView tvDetalle3;
    private TextView tvResultado;
    private TextView tvFecha;
    private TextView tvHora;
    private ImageView imgNotificacion;
    private ImageView imgResultado;
    private LottieAnimationView lottieAnimationView;
    private RelativeLayout pantalla_notificacion;

    //Constructor de la clase
    public NotificacionVista() {
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
        //Asignación de la relación vista y clase
        final View root= inflater.inflate(R.layout.fragment_notificacion_vista, container, false);
        tvTipoNotificacion=(TextView)root.findViewById(R.id.tv_tipo_notificacion);
        tvTituloNotificacion=(TextView)root.findViewById(R.id.tv_titulo_noti);
        tvDetalle1=(TextView)root.findViewById(R.id.tv_detalle1);
        tvDetalle2=(TextView)root.findViewById(R.id.tv_detalle2);
        tvDetalle3=(TextView)root.findViewById(R.id.tv_detalle3);
        tvResultado=(TextView)root.findViewById(R.id.tv_notificacion_resultado);
        tvFecha=(TextView)root.findViewById(R.id.tv_fecha_retiro);
        tvHora=(TextView)root.findViewById(R.id.tv_hora_retiro);
        imgNotificacion=(ImageView)root.findViewById(R.id.imgNotificacion);
        imgResultado=(ImageView)root.findViewById(R.id.imgResultado);
        lottieAnimationView=(LottieAnimationView)root.findViewById(R.id.animationView2);
        pantalla_notificacion=(RelativeLayout)root.findViewById(R.id.pantalla_notificacion);
        pantalla_notificacion.setVisibility(View.GONE);

        int idNotificacion=getArguments().getInt("id_notificacion");
        if(getArguments().getString("tipo_notificacion").equals("Solicitud de visita")){
            cargarSolicitudVisita(idNotificacion);
        }else{
            cargarSolicitudEncomienda(idNotificacion);
        }
        return root;
    }

    private void cargarSolicitudVisita(int id_solicitud_visita){
        final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String url=getString(R.string.server)+"solicitud_visitas.php?opcion=5&id_solicitud_visita="+id_solicitud_visita;
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
                        js = response.getJSONObject(i);
                        tvTipoNotificacion.setText("Solicitud de visita");
                        tvTituloNotificacion.setText("Datos de visita");
                        tvDetalle1.setText("Rut: "+js.getString("RUT"));
                        tvDetalle2.setText("Nombre: "+js.getString("NOMBRE"));
                        tvDetalle3.setText("Apellidos: "+js.getString("APE_PATERNO")+" "+js.getString("APE_MATERNO"));
                        int estado=js.getInt("ESTADO_SOLICITUD_VISITA");
                        switch (estado){
                            case 1:
                                tvResultado.setText("Solicitud aceptada");
                                imgResultado.setImageResource(R.drawable.ic_check_accept);
                                break;
                            case 2:
                                tvResultado.setText("Solicitud rechazada");
                                imgResultado.setImageResource(R.drawable.ic_negacion_60dp);
                                break;
                            case 3:
                                tvResultado.setText("Solicitud en espera");
                                imgResultado.setImageResource(R.drawable.ic_cargando);
                                break;
                        }
                        Date fechaT = null;
                        try {
                            fechaT = dateFormat.parse(js.getString("FECHA_VISITA"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat formatF=new SimpleDateFormat("dd/MM/yyy");
                            String fechaF=formatF.format(fechaT);
                            tvFecha.setText("Fecha de ingreso: "+fechaF);

                        tvHora.setText("Hora de ingreso: "+js.getString("HORA_VISITA"));
                        lottieAnimationView.setVisibility(View.GONE);
                        pantalla_notificacion.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Problemas de conexión a internet",Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void cargarSolicitudEncomienda(int id_solicitud_encomienda){
        final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String url=getString(R.string.server)+"solicitud_encomienda.php?opcion=2&id_solicitud_encomienda="+id_solicitud_encomienda;
        System.out.println(url);
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
                        js = response.getJSONObject(i);
                        imgNotificacion.setImageResource(R.drawable.ic_encomienda_80dp);
                        tvTipoNotificacion.setText("Encomienda");
                        tvTituloNotificacion.setText("Datos de encomienda");
                        tvDetalle1.setText("Nombre: "+js.getString("NOMBRE"));
                        tvDetalle2.setText("Descripcion: "+js.getString("DESCRIPCION"));
                        tvDetalle3.setVisibility(View.GONE);
                        int estado=js.getInt("ESTADO_SOLICITUD_ENCOMIENDA");
                        switch (estado){
                            case 1:
                                tvResultado.setText("Solicitud aceptada");
                                imgResultado.setImageResource(R.drawable.ic_check_accept);
                                break;
                            case 2:
                                tvResultado.setText("Solicitud rechazada");
                                imgResultado.setImageResource(R.drawable.ic_negacion_60dp);
                                break;
                            case 3:
                                tvResultado.setText("Solicitud en espera");
                                imgResultado.setImageResource(R.drawable.ic_cargando);
                                break;
                        }
                        Date fechaT = null;
                        try {
                            fechaT = dateFormat.parse(js.getString("FECHA_ENTREGA"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat formatF=new SimpleDateFormat("dd/MM/yyy");
                        String fechaF=formatF.format(fechaT);
                        tvFecha.setText("Fecha de ingreso: "+fechaF);

                        tvHora.setText("Hora de ingreso: "+js.getString("HORA_ENTREGA"));
                        lottieAnimationView.setVisibility(View.GONE);
                        pantalla_notificacion.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Problemas de conexión a internet",Toast.LENGTH_LONG).show();
            }
        });
       VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }
}
