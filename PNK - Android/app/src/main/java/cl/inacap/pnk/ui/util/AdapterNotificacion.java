package cl.inacap.pnk.ui.util;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.entidadesDAO.Notificacion;

/**
 * Esta clase se utiliza para crear vistas del layout item_notificacion
 * segun la cantidad de la lista
 * @author: Pablo Ahumada Olivares
 * @version: 1.0
 */
public class AdapterNotificacion extends RecyclerView.Adapter<AdapterNotificacion.ViewHolder> {
    //Campos de la clase
    private List<Notificacion> notificacionList;

    /**
     * Constructor de la clase
     * @param notificacionList Lista de notificaciones segun los que se crearan los layout
     */
    public AdapterNotificacion(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    /**
     * Metodo para obtener la lista de notificaciones
     * @return La lista actual de notificaciones en el objeto
     */
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    /**
     * Metodo para colocar la lista de notificaciones
     * @param notificacionList Lista de notificaciones para colocar en el objeto
     */
    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    /**
     * Metodo que se utiliza para asignar el layout
     * @param parent El padre de la vista para encontrar el contexto
     * @param viewType Tipo de vista a utilizar
     * @return La vista ya asignada
     */
    @NonNull
    @Override
    public AdapterNotificacion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacion,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Metodo para darle los valores a la vista asignada
     * @param holder vista de un item_notificacion
     * @param position recibe cada posicion del campo notificacionList
     */
    @Override
    public void onBindViewHolder(@NonNull final AdapterNotificacion.ViewHolder holder, final int position) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String estado="";
        if(notificacionList.get(position).getTipoNotificacion()=="Solicitud de visita"){
            switch (notificacionList.get(position).getEstado()){
                case 1:
                    estado="Aceptado";
                    break;
                case 2:
                    estado="Rechazado";
                    break;
                case 3:
                    estado="En proceso";
                    break;
            }
            holder.imgSolicitud.setImageResource(R.drawable.ic_visitas_40dp);
            holder.tvIdSolicitud.setText(""+notificacionList.get(position).getIdNotificacion());
            holder.tvTipoSolo.setText("Solicitud de visita: "+estado);
            holder.tvDetalle.setText("Visitante:  "+notificacionList.get(position).getDetalleNotificacion());
            holder.tvFecha.setText("Fecha: "+dateFormat.format(notificacionList.get(position).getFechaNotificacion())+" "+notificacionList.get(position).getHoraNotificacion());
        }else{
            switch (notificacionList.get(position).getEstado()){
                case 1:
                    estado="Recibida";
                    break;
                case 2:
                    estado="Entregada";
                    break;
            }
            holder.imgSolicitud.setImageResource(R.drawable.ic_encomienda40dp);
            holder.tvIdSolicitud.setText(""+notificacionList.get(position).getIdNotificacion());
            holder.tvTipoSolo.setText("Encomienda "+estado);
            holder.tvDetalle.setText("Entrega de encomienda");
            holder.tvFecha.setText("Fecha: "+dateFormat.format(notificacionList.get(position).getFechaNotificacion())+" "+notificacionList.get(position).getHoraNotificacion());
        }
        holder.imgSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("id_notificacion",notificacionList.get(position).getIdNotificacion());
                bundle.putString("tipo_notificacion",notificacionList.get(position).getTipoNotificacion());
                try {
                    Navigation.findNavController(v).navigate(R.id.action_notificaciones_to_notificacionVista,bundle);
                }catch (Exception e){
                    Navigation.findNavController(v).navigate(R.id.action_nav_home_to_notificacionVista,bundle);
                }

            }
        });
    }

    /**
     * Metodo para obtener la cantidad de la lista
     * @return La cantidad de la lista
     */
    @Override
    public int getItemCount() {
        return notificacionList.size();
    }

    /**
     * Clase que contiene una vista del item_notificacion que extiende a la clase RecyclerView.ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Campos de la clase
        private ImageView imgSolicitud;
        private TextView tvIdSolicitud;
        private TextView tvTipoSolo;
        private TextView tvDetalle;
        private TextView tvFecha;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignación de los datos en el layout
            imgSolicitud=(ImageView)itemView.findViewById(R.id.img_solicitud);
            tvIdSolicitud=(TextView)itemView.findViewById(R.id.tv_id_solicitud);
            tvTipoSolo=(TextView)itemView.findViewById(R.id.tv_tipo_solicitud);
            tvDetalle=(TextView)itemView.findViewById(R.id.tv_detalle);
            tvFecha=(TextView)itemView.findViewById(R.id.tv_fecha);
            context=itemView.getContext();
        }
    }
}
