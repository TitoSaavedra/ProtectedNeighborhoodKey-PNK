package cl.inacap.pnk.ui.fragments.visitas;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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

import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;

/**
 * Clase del layout fragment_visita_perfil
 * {@link Fragment} sub clase.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class VisitaPerfil extends Fragment {


    //Propiedades
    private Bundle bundle;
    private ImageView imagePerfil;
    private TextView tvNombreCompleto,tvSolicicutd,tvRut, tvNombre,tvApellidos;
    private EditText etFechaAcceso,etHoraAcceso;
    private Button btnSolicitarAcceso,btnCancelarAcceso;
    private int dia,mes,year,hora,minutos;
    private int idSolicitud;
    private LottieAnimationView lottieAnimationView;
    private RelativeLayout pantalla_notificacion;


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
        View root = inflater.inflate(R.layout.fragment_visita_perfil, container, false);
        imagePerfil = (ImageView) root.findViewById(R.id.img_perfil);
        tvNombreCompleto = (TextView) root.findViewById(R.id.tv_nombre_completo);
        tvSolicicutd = (TextView) root.findViewById(R.id.tv_solicitud);
        tvRut = (TextView) root.findViewById(R.id.tv_rut);
        tvNombre = (TextView) root.findViewById(R.id.tv_nombres);
        tvApellidos = (TextView) root.findViewById(R.id.tv_apellidos);
        etFechaAcceso = (EditText) root.findViewById(R.id.et_fecha_acceso);
        etHoraAcceso = (EditText) root.findViewById(R.id.et_hora_acceso);
        btnSolicitarAcceso = (Button) root.findViewById(R.id.btn_solicitar_acceso);
        btnCancelarAcceso = (Button) root.findViewById(R.id.btn_cancelar_acceso);
        lottieAnimationView=(LottieAnimationView)root.findViewById(R.id.animationView3);
        pantalla_notificacion=(RelativeLayout)root.findViewById(R.id.pantalla_visita);
        pantalla_notificacion.setVisibility(View.GONE);

        idSolicitud=0;
        bundle = getArguments();
        cargarDatosVisita();
        buscarSolicitudActiva();

        etFechaAcceso.setEnabled(false);
        etHoraAcceso.setEnabled(false);
        btnSolicitarAcceso.setEnabled(false);
        btnCancelarAcceso.setEnabled(false);
        btnSolicitarAcceso.getBackground().setTint(getResources().getColor(R.color.colorVerdeDegradado));
        btnCancelarAcceso.getBackground().setTint(getResources().getColor(R.color.colorRojoDegradado));

        etFechaAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                dia=c.get(Calendar.DAY_OF_MONTH);
                mes=c.get(Calendar.MONTH);
                year=c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etFechaAcceso.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },dia,mes,year);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        etHoraAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c=Calendar.getInstance();
                hora=c.get(Calendar.HOUR);
                minutos=c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etHoraAcceso.setText(hourOfDay+":"+minute);
                    }
                },hora,minutos,false);
                timePickerDialog.show();
            }
        });

        btnSolicitarAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etFechaAcceso.getText().toString().equals("") && !etHoraAcceso.getText().toString().equals("")){
                    ingresarSolicitud(etFechaAcceso.getText().toString(),etHoraAcceso.getText().toString());
                }else{
                    Toast.makeText(getContext(),"Complete los campos primero",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelarAcceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarSolicitud();
            }
        });
        return root;
    }

    private void cargarDatosVisita(){
        int id=bundle.getInt("id_persona");
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
                        tvRut.setText("Rut: "+js.getString("RUT"));
                        tvNombre.setText("Nombre: "+js.getString("NOMBRE"));
                        tvApellidos.setText("Apellidos: "+js.getString("APE_PATERNO")+" "+js.getString("APE_MATERNO"));
                        pantalla_notificacion.setVisibility(View.VISIBLE);
                        lottieAnimationView.setVisibility(View.GONE);
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

    private void buscarSolicitudActiva(){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id=bundle.getInt("id_persona"),id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"solicitud_visitas.php?opcion=6&id_persona="+id+"&id_cuenta="+id_cuenta;
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
                        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat dateParse=new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaVisita=dateFormat.parse(js.getString("FECHA_VISITA"));
                        idSolicitud=js.getInt("ID_SOLICITUD_VISITA");
                        etFechaAcceso.setText(dateParse.format(fechaVisita));
                        etHoraAcceso.setText(js.getString("HORA_VISITA"));
                        btnCancelarAcceso.setEnabled(true);
                        btnCancelarAcceso.getBackground().setTint(getResources().getColor(R.color.colorRojo));
                        tvSolicicutd.setText("Solicitud en proceso");
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                etHoraAcceso.setEnabled(true);
                etFechaAcceso.setEnabled(true);
                btnSolicitarAcceso.setEnabled(true);
                btnSolicitarAcceso.getBackground().setTint(getResources().getColor(R.color.colorVerde));
                //Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void ingresarSolicitud(String fecha,String hora){
         SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaFormateada=dateFormat.parse(fecha);
            dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            fecha=dateFormat.format(fechaFormateada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fecha=fecha.replace('/','-');
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id=bundle.getInt("id_persona"),id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"solicitud_visitas.php?opcion=4&id_persona="+id+"&id_cuenta="+id_cuenta+"&fecha_visita="+fecha+"&hora_visita="+hora;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity().getApplicationContext(),"Solicitud registrada",Toast.LENGTH_LONG).show();
                tvSolicicutd.setText("Solicitud en proceso");
                etFechaAcceso.setEnabled(false);
                etHoraAcceso.setEnabled(false);
                btnCancelarAcceso.setEnabled(true);
                btnCancelarAcceso.getBackground().setTint(getResources().getColor(R.color.colorRojo));
                btnSolicitarAcceso.setEnabled(false);
                btnSolicitarAcceso.getBackground().setTint(getResources().getColor(R.color.colorVerdeDegradado));
                buscarSolicitudActiva();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast.makeText(getContext(),"Ha ocurrido un error, ¿Tienes acceso a internet?",Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void cancelarSolicitud(){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id=bundle.getInt("id_persona"),id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"solicitud_visitas.php?opcion=7&id_persona="+id+"&id_cuenta="+id_cuenta+"&id_solicitud_visita="+idSolicitud;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity().getApplicationContext(),"Solicitud cancelada",Toast.LENGTH_LONG).show();
                tvSolicicutd.setText("Solicitar acceso");
                etFechaAcceso.setText("");
                etHoraAcceso.setText("");
                etFechaAcceso.setEnabled(true);
                etHoraAcceso.setEnabled(true);
                btnSolicitarAcceso.setEnabled(true);
                btnCancelarAcceso.setEnabled(false);
                btnSolicitarAcceso.getBackground().setTint(getResources().getColor(R.color.colorVerde));
                btnCancelarAcceso.getBackground().setTint(getResources().getColor(R.color.colorRojoDegradado));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Ha ocurrido un error, ¿Tienes acceso a internet?",Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
