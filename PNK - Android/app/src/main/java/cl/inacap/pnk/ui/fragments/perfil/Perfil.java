package cl.inacap.pnk.ui.fragments.perfil;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.navigation.Navigation;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Cuenta;
import cl.inacap.pnk.io.entidadesDAO.Persona;
import cl.inacap.pnk.io.entidadesDAO.TipoPersona;
import cl.inacap.pnk.ui.Login;

/**
 * Clase del layout fragment_perfil
 * {@link Fragment} sub clase.
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class Perfil extends Fragment {
    //Campos de la clase
    private Button btnActualizarContra;
    private Button btnActualizarPerfil;
    private ImageView imgPerfil;
    private TextView tvNombreCompleto;
    private TextView tvRut;
    private TextView tvNombres;
    private TextView tvApellidos;
    private TextView tvEmail;
    private TextView tvTelefono;
    private TextView tvDireccion;
    private Spinner spDireccion;
    private Bundle bundle;
    private static final int COD_SELECCION=10;

    //Constructor de la clase
    public Perfil() {
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
        final View root =inflater.inflate(R.layout.fragment_perfil, container, false);
        btnActualizarContra=(Button)root.findViewById(R.id.btn_actualizarClave);
        btnActualizarPerfil=(Button)root.findViewById(R.id.btn_actualizarPerfil);
        imgPerfil=(ImageView)root.findViewById(R.id.img_perfil);
        tvNombreCompleto=(TextView)root.findViewById(R.id.tv_nombre_completo);
        tvRut=(TextView)root.findViewById(R.id.tv_rut);
        tvNombres=(TextView)root.findViewById(R.id.tv_nombres);
        tvApellidos=(TextView)root.findViewById(R.id.tv_apellidos);
        tvEmail=(TextView)root.findViewById(R.id.tv_email);
        tvTelefono=(TextView)root.findViewById(R.id.tv_telefono);
        tvDireccion=(TextView)root.findViewById(R.id.tv_direccion);
        spDireccion=(Spinner)root.findViewById(R.id.spDireccion);
        bundle=getArguments();

        cargarDatos();
        cargarDatosLocales();
        //Asginacion onClick del boton actualizar contraseña
        btnActualizarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_perfil_to_perfilActualizarContra,bundle);
            }
        });

        //Asginacion onClick del boton actualizar perfil
        btnActualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_perfil_to_perfilActualizar,bundle);
            }
        });

        imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                final int estadoDePermiso = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
                if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
                    // Aquí el usuario dio permisos para acceder a la cámara
                } else {
                    // Si no, entonces pedimos permisos...
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }
                final CharSequence[] opciones={"Cambiar foto de perfil","Cancelar"};
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Actualizar foto");
                builder.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(opciones[which].equals("Cambiar foto de perfil")){
                            if(estadoDePermiso==PackageManager.PERMISSION_GRANTED){
                                Intent intent=new Intent(Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/");
                                startActivityForResult(intent.createChooser(intent,"Seleccione foto"),COD_SELECCION);
                            }else{
                                Toast.makeText(getActivity().getApplicationContext(),"Debe otorgar permisos de almacenamiento",Toast.LENGTH_LONG).show();
                            }
                        }else{
                           dialog.dismiss();
                        }
                    }
                });
                builder.show();
                 */
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCION:
                if(data!=null){
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String path = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bm = BitmapFactory.decodeFile(path);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    bm.compress(Bitmap.CompressFormat.JPEG, 30, baos); //bm = Bitmap
                   // bm.createScaledBitmap(bm,100,100,true);
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    cambiarFoto(encodedImage);
                    imgPerfil.setImageBitmap(bm);
                }
                break;

        }
    }


    private void cargarDatosLocales() {
        Persona persona=new Persona();
        persona.setRut(bundle.getString("rut"));
        persona.setNombre(bundle.getString("nombre"));
        persona.setSegundoNom(bundle.getString("seg_nombre"));
        persona.setApellidoPa(bundle.getString("ape_paterno"));
        persona.setApellidoMa(bundle.getString("ape_materno"));
        persona.setCorreo(bundle.getString("email"));
        persona.setTelefono(bundle.getInt("telefono"));
        /*
        byte[] bytes= Base64.decode(bundle.getString("foto"),Base64.DEFAULT);
        Bitmap imagen= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        imgPerfil.setImageBitmap(imagen);
         */
        tvNombreCompleto.setText(persona.getNombre()+" "+persona.getApellidoPa()+" "+persona.getApellidoMa());
        tvRut.setText("Rut: "+persona.getRut());
        tvNombres.setText("Nombres: "+persona.getNombre()+" "+persona.getSegundoNom());
        tvApellidos.setText("Apellidos: "+persona.getApellidoPa()+" "+persona.getApellidoMa());
        tvTelefono.setText("Telefono: "+persona.getTelefono());
        tvEmail.setText("Email: "+persona.getCorreo());
    }

    /**
     * Metodo para cargar los datos del usuario actual en la aplicacion
     */
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
                        tvRut.setText("Rut: "+js.getString("RUT"));
                        tvNombres.setText("Nombres: "+js.getString("NOMBRE")+" "+js.getString("SEG_NOMBRE"));
                        tvApellidos.setText("Apellidos: "+js.getString("APE_PATERNO")+" "+js.getString("APE_MATERNO"));
                        tvEmail.setText("Email: "+js.getString("EMAIL"));
                        tvTelefono.setText("Telefono: "+js.getInt("TELEFONO"));
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

        url=getString(R.string.server)+"direccion.php?opcion=1&id_persona="+id;
        JsonArrayRequest jsonArrayRequest2=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(JSONArray response) {
                JSONObject js = null;
                ArrayList<String> direcciones=new ArrayList<String>();
                boolean band=false;
                ArrayAdapter<CharSequence> adapter=null;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        js = response.getJSONObject(i);
                        if(response.length()>1){
                            band=true;
                            direcciones.add("Block:"+js.getString("BLOCK")+" PISO:"+js.getString("PISO")+" N°:"+js.get("NUMERO"));
                        }else{
                            spDireccion.setVisibility(View.GONE);
                            tvDireccion.setText("Dirección: "+"Block:"+js.getString("BLOCK")+" Piso:"+js.getString("PISO")+" N°:"+js.get("NUMERO"));
                        }
                    }
                    if (band){
                        adapter=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,direcciones);
                        spDireccion.setVisibility(View.VISIBLE);
                        spDireccion.setAdapter(adapter);
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(),"Error response: "+e,Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonArrayRequest2);

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

    private void cambiarFoto(final String foto){
        final SharedPreferences preferences=getActivity().getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        final int id_cuenta=preferences.getInt("id_cuenta",0);
        String url=getString(R.string.server)+"cuenta.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            /**
             * Metodo que recibe la respuesta del servidor
             * @param response respuesta de tipo JSON con los datos de la consulta
             */
            @Override
            public void onResponse(String response) {
                String respuesta=response;
                if(respuesta.equals("actualizado")){
                    Toast.makeText(getActivity().getApplicationContext(),"Foto de perfil cambiada",Toast.LENGTH_LONG).show();
                }else{
                    if(respuesta.equals("no actualizado")){
                        Toast.makeText(getActivity().getApplicationContext(),"Foto no cambiada",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<>();
                params.put("opcion",3+"");
                params.put("id_cuenta",id_cuenta+"");
                params.put("foto",foto);
                return params;
            }
        };
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
