package cl.inacap.pnk.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.VolleySingleton;
import cl.inacap.pnk.io.entidadesDAO.Acceso;
import cl.inacap.pnk.io.entidadesDAO.Sesion;
import cl.inacap.pnk.ui.fragments.seguridad.SeguridadFragment;

/**
 * Esta clase se utiliza para crear vistas del layout item_acceso segun la cantidad
 * de la lista
 * @author: Pablo Ahumada Olivares
 * @version: 1.0
 */
public class AdapterSesion extends RecyclerView.Adapter<AdapterSesion.ViewHolder> {
    //Campos de la clase
    private List<Sesion> sesionList;

    /**
     * Constructor principal de la clase
     * @param sesionList Define la lista que se utilizara para crear los layout
     */
    public AdapterSesion(List<Sesion> sesionList) {
        this.sesionList = sesionList;
    }//Cierre del constructor

    /**
     * Metodo para obtener la lista
     * @return La lista actual del objeto
     */
    public List<Sesion> getSesionList() {
        return sesionList;
    }

    /**
     * Metodo para colocar una lista en el objeto
     * @param sesionList La lista que se desea asignar
     */
    public void setSesionList(List<Sesion> sesionList) {
        this.sesionList = sesionList;
    }

    /**
     * Metodo que se utiliza al crear el objeto para cada item
     * @param parent El padre de la vista para encontrar el contexto
     * @param viewType Tipo de vista a utilizar
     * @return La vista ya asignada
     */
    @NonNull
    @Override
    public AdapterSesion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sesion,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Metodo para darle los valores a la vista creada de cada item_acceso
     * @param holder vista de un item_acceso
     * @param position recibe cada posicion del campo accesoList
     */
    @Override
    public void onBindViewHolder(@NonNull final AdapterSesion.ViewHolder holder, final int position) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        holder.tvidSesion.setText(""+sesionList.get(position).getIdSesion());
        holder.tvDispositivo.setText("Dispositivo: "+sesionList.get(position).getDispositivo());
        holder.tvFechaHora.setText("Fecha: "+dateFormat.format(sesionList.get(position).getFechaSesion())+" "+sesionList.get(position).getHoraSesion());
        holder.tvDireccionIp.setText("Direccion IP: "+sesionList.get(position).getDireccionIp());
        holder.btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion(sesionList.get(position).getIdSesion(),holder.context);
                Navigation.findNavController(v).navigate(R.id.action_nav_seguridad_self);
            }
        });
    }

    /**
     * Metodo para obtener la cantidad de la lista
     * @return La cantidad de la lista
     */
    @Override
    public int getItemCount() {
        return sesionList.size();
    }

    /**
     * Clase que contiene una vista del item_acceso que extiende a la clase RecyclerView.ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Campos de la clase
        private ImageView imgSesion;
        private TextView tvidSesion;
        private TextView tvDispositivo;
        private TextView tvFechaHora;
        private TextView tvDireccionIp;
        private Button btnCerrarSesion;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignación de los datos en el layout
            imgSesion=(ImageView)itemView.findViewById(R.id.img_sesion);
            tvidSesion=(TextView)itemView.findViewById(R.id.tv_id_sesion);
            tvDispositivo=(TextView)itemView.findViewById(R.id.tv_dispositivo);
            tvFechaHora=(TextView)itemView.findViewById(R.id.tv_fecha_hora);
            tvDireccionIp=(TextView)itemView.findViewById(R.id.tv_direccion_ip);
            btnCerrarSesion=(Button)itemView.findViewById(R.id.btn_cerrar_sesion);
            context=itemView.getContext();
        }
    }

    private void cerrarSesion(int idSesion, final Context context){
        String url="http://34.232.87.118/BD_PNK/sesion.php?opcion=3&id_sesion="+idSesion;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,"Sesion cerrada",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
