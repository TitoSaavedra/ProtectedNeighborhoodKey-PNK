package cl.inacap.pnk.ui.fragments.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.MyFirebaseMessagingService;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Notificacion;
import cl.inacap.pnk.io.entidadesDAO.Persona;
import cl.inacap.pnk.ui.Inicio;
import cl.inacap.pnk.ui.Login;
import cl.inacap.pnk.ui.util.AdapterNotificacion;
import cl.inacap.pnk.ui.util.Utilidades;

/**
 * Clase del layout fragment_home
 * {@link Fragment} sub clase
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class HomeFragment extends Fragment {
    //Campos de la clase
    private RecyclerView recyclerView;
    private AdapterNotificacion adapterNotificacion;
    private Button btnAcceder, btnCerrar, btnVerMas;
    private ImageView imgPerfil;
    private TextView tv_nombre_completo, tv_email, tv_titulo_acceso,tv_notificaciones_msg;
    private Bundle bundle;
    private LottieAnimationView lottieAnimationView;
    private Boolean band;
    List<Notificacion> notificacionList;

    /**
     * Metodo onCreate de la clase para la relacion vista y clase
     * @param inflater para realizar la relacion con el layout correcpondiente
     * @param container contenedor del layout
     * @param savedInstanceState instancia de la aplicación
     * @return la vista asignada
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Asignación de relación vista y clase
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        lottieAnimationView=(LottieAnimationView)root.findViewById(R.id.animationView);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnAcceder = (Button) root.findViewById(R.id.btn_acceder);
        btnCerrar = (Button) root.findViewById(R.id.btn_cerrar);
        btnVerMas = (Button) root.findViewById(R.id.btn_ver_mas);
        tv_notificaciones_msg=(TextView)root.findViewById(R.id.tv_notificaciones_msg);
        tv_titulo_acceso = (TextView) root.findViewById(R.id.tv_titulo_acceso);
        band=true;

        Utilidades utilidades=new Utilidades();
        utilidades.verificarSesion(getContext(),getActivity());

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

        notificacionList = new ArrayList<>();
        bundle = new Bundle();

        //Asignación de relación del drawer_layout y la imagen de perfil
        final DrawerLayout drawer = getActivity().findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        View headView = navigationView.getHeaderView(0);
        imgPerfil = (ImageView) headView.findViewById(R.id.img_perfil);
        tv_nombre_completo = (TextView) headView.findViewById(R.id.tv_nombre_completo);
        tv_email = (TextView) headView.findViewById(R.id.tv_email);

        cargarVisitas();

        adapterNotificacion = new AdapterNotificacion(notificacionList);
        recyclerView.setAdapter(adapterNotificacion);
        cargarDatos();

        //Asignación de onClick de los botones
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_titulo_acceso.getText().equals("Acceso: Disponible")){
                    Utilidades utilidades=new Utilidades();
                    String hora= utilidades.getHoraActual();
                    String fecha=utilidades.getFechaActual();
                    btnAcceder.setEnabled(false);
                    band=false;
                    ingresarAcceso(fecha,hora,1);
                }else{
                    Toast.makeText(getContext(),"Acceso no disponible...",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_titulo_acceso.getText().equals("Acceso: Disponible")){
                    cerrarAcceso(1);
                }else{
                    Toast.makeText(getContext(),"Acceso no disponible...",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_notificaciones);
            }
        });

        final SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        //Asignación onClick de la imagen de perfil
        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getParentFragment().getView()).navigate(R.id.action_global_perfil,bundle);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.pagina_web:
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("http://34.232.87.118/PNK/"));
                        startActivity(viewIntent);
                        break;
                    case R.id.nav_seguridad:
                        Navigation.findNavController(getParentFragment().getView()).navigate(R.id.action_global_nav_seguridad);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.cerrarSesion:
                        Utilidades utilidades=new Utilidades();
                        utilidades.cerrarSesion(getContext(),getActivity());
                        preferences.edit().remove("id_persona").commit();
                        preferences.edit().remove("id_cuenta").commit();
                        startActivity(new Intent(getActivity().getApplicationContext(),Login.class));
                        getActivity().finish();
                        break;
                }
                return true;
            }
        });
        enviarTokenAlServer(preferences.getString("token",""));
        return root;
    }

    public void enviarTokenAlServer(final String token) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://34.232.87.118/BD_PNK/sesion.php?opcion=5", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(),"Se registro exitosamente",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Problemas de conexión a internet",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                SharedPreferences preferencess= getContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                parametros.put("token",token);
                parametros.put("id_cuenta",""+preferencess.getInt("id_cuenta",0));
                parametros.put("id_sesion",""+preferencess.getInt("id_sesion",0));
                return parametros;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                   // tv_titulo_acceso.setText("Mi direccion es: \n"+ DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo para cargar los datos del usuario actual en la aplicacion
     */
    private void cargarDatos() {
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id_persona=preferences.getInt("id_persona",0);
        String url=getString(R.string.server)+"persona.php?opcion=1&id_persona="+id_persona;
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
                        tv_nombre_completo.setText(js.getString("NOMBRE")+" "+js.getString("APE_PATERNO")+" "+js.getString("APE_MATERNO"));
                        tv_email.setText(js.getString("EMAIL"));
                        bundle.putString("rut",js.getString("RUT"));
                        bundle.putString("nombre",js.getString("NOMBRE"));
                        bundle.putString("seg_nombre",js.getString("SEG_NOMBRE"));
                        bundle.putString("ape_paterno",js.getString("APE_PATERNO"));
                        bundle.putString("ape_materno",js.getString("APE_MATERNO"));
                        bundle.putInt("telefono",js.getInt("TELEFONO"));
                        bundle.putString("email",js.getString("EMAIL"));
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
                        bundle.putString("foto",js.getString("FOTO"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getActivity().getApplicationContext(),"Problemas de conexión a internet",Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest1);
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
                tv_notificaciones_msg.setVisibility(View.VISIBLE);
                lottieAnimationView.setAnimation(R.raw.empty_data);
                lottieAnimationView.playAnimation();
                //Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
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
                List<Notificacion> listFinal=new ArrayList<>();
                listFinal.add(notificacionList.get(notificacionList.size()-1));
                listFinal.add(notificacionList.get(notificacionList.size()-2));
                adapterNotificacion=new AdapterNotificacion(listFinal);
                recyclerView.setAdapter(adapterNotificacion);
                lottieAnimationView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                btnVerMas.setVisibility(View.VISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
                List<Notificacion> listFinal=new ArrayList<>();
                listFinal.add(notificacionList.get(notificacionList.size()-1));
                listFinal.add(notificacionList.get(notificacionList.size()-2));
                adapterNotificacion=new AdapterNotificacion(listFinal);
                recyclerView.setAdapter(adapterNotificacion);
                lottieAnimationView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                btnVerMas.setVisibility(View.VISIBLE);
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest2);
    }

    private void ingresarAcceso(String fecha,String hora,int idBarrera){
        SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        int id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"acceso.php?opcion=2&id_cuenta="+id_cuenta+"&fecha_acceso="+fecha+"&hora_acceso="+hora+"&id_barrera="+idBarrera;
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
                        String resultado=js.getString("RESULTADO");
                        Toast.makeText(getActivity().getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
                        if(!resultado.equals("El acceso ya esta abierto")){
                            CountDownTimer countDownTimer = new CountDownTimer(15000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    tv_titulo_acceso.setText("Estado: Esperar "+String.format(Locale.getDefault(), "%d s...", millisUntilFinished / 1000L));
                                }

                                public void onFinish() {
                                    tv_titulo_acceso.setText("Estado: Disponible");
                                    band=true;
                                    btnAcceder.setEnabled(true);
                                }
                            }.start();
                        }
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

    private void cerrarAcceso(int idBarrera){
        String url=getString(R.string.server)+"barrera.php?opcion=2&id_barrera="+idBarrera;
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
                        String resultado=js.getString("RESULTADO");
                        Toast.makeText(getActivity().getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
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

    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        HomeFragment homeFragment;

        public HomeFragment getHomeFragment() {
            return homeFragment;
        }

        public void setMainActivity(HomeFragment homeFragment) {
            this.homeFragment = homeFragment;
        }


        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            //loc.getLatitude();
            //loc.getLongitude();
            LatLng barreraLoc=new LatLng(-30.237808, -71.082774);
            LatLng dispostivioLoc=new LatLng(loc.getLatitude(), loc.getLongitude());
            double dist=calcularDistancia(barreraLoc,dispostivioLoc);
            if(band){
                if (dist<=20){
                    tv_titulo_acceso.setText("Acceso: Disponible");
                }else{
                    tv_titulo_acceso.setText("Acceso: Demasiado lejos");
                }
            }
            //this.homeFragment.setLocation(loc);
        }
        public double calcularDistancia(LatLng a, LatLng b) {
            if (a != null && b != null) {
                // pasamos las diferencias de las coordenadas a radianes
                double difLat = toRadianes(b.latitude - a.latitude);
                double difLong = toRadianes(b.longitude - a.longitude);

                double dato1 = Math.sin(difLat / 2) * Math.sin(difLat / 2) + Math.cos(toRadianes(a.latitude)) *
                        Math.cos(toRadianes(b.latitude)) * Math.sin(difLong / 2) * Math.sin(difLong / 2);

                double dato2 = 2 * Math.atan2(Math.sqrt(dato1), Math.sqrt(1 - dato1));

                double dato3 = 6371 * dato2;

                //Log.v("tag", "Resutlado final (metros) " + dato3 * 1000);

                return dato3 * 1000;// lo pasamos a metros
            }

            return 0.0;// si a y b son nulos
        }

        private double toRadianes(double coord) {
            return ((coord * Math.PI) / 180);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            //mensaje1.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //mensaje1.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

}