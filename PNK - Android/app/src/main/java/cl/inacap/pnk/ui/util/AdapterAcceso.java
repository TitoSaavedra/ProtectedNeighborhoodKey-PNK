package cl.inacap.pnk.ui.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cl.inacap.pnk.R;
import cl.inacap.pnk.io.entidadesDAO.Acceso;

/**
 * Esta clase se utiliza para crear vistas del layout item_acceso segun la cantidad
 * de la lista
 * @author: Pablo Ahumada Olivares
 * @version: 1.0
 */
public class AdapterAcceso extends RecyclerView.Adapter<AdapterAcceso.ViewHolder> {
    //Campos de la clase
    private List<Acceso> accesoList;

    /**
     * Constructor principal de la clase
     * @param accesoList Define la lista que se utilizara para crear los layout
     */
    public AdapterAcceso(List<Acceso> accesoList) {
        this.accesoList = accesoList;
    }//Cierre del constructor

    /**
     * Metodo para obtener la lista
     * @return La lista actual del objeto
     */
    public List<Acceso> getAccesoList() {
        return accesoList;
    }

    /**
     * Metodo para colocar una lista en el objeto
     * @param accesoList La lista que se desea asignar
     */
    public void setAccesoList(List<Acceso> accesoList) {
        this.accesoList = accesoList;
    }


    /**
     * Metodo que se utiliza al crear el objeto para cada item
     * @param parent El padre de la vista para encontrar el contexto
     * @param viewType Tipo de vista a utilizar
     * @return La vista ya asignada
     */
    @NonNull
    @Override
    public AdapterAcceso.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_acceso,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Metodo para darle los valores a la vista creada de cada item_acceso
     * @param holder vista de un item_acceso
     * @param position recibe cada posicion del campo accesoList
     */
    @Override
    public void onBindViewHolder(@NonNull AdapterAcceso.ViewHolder holder, int position) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String estado="",metodo="";
        switch (accesoList.get(position).getEstado()){
            case 1:
                estado="Aceptado";
                break;
            case 2:
                estado="Negado";
                break;
        }
        switch (accesoList.get(position).getTipoAcceso()){
            case 1:
                metodo="Aplicación móvil";
                break;
            case 2:
                metodo="Tarjeta NFC";
                break;
        }
        if (estado.equals("Aceptado")){
            holder.imgAcceso.setImageResource(R.drawable.ic_check_accept);
        }else{
            holder.imgAcceso.setImageResource(R.drawable.ic_negacion_60dp);
        }
        holder.tvIdAcceso.setText(""+accesoList.get(position).getIdAcceso());
        holder.tvTitulo.setText("Acceso "+estado);
        holder.tvMetodo.setText("Metodo: "+metodo);
        holder.tvFecha.setText(dateFormat.format(accesoList.get(position).getFechaAcceso())+" "+accesoList.get(position).getHoraAcceso());
    }

    /**
     * Metodo para obtener la cantidad de la lista
     * @return La cantidad de la lista
     */
    @Override
    public int getItemCount() {
        return accesoList.size();
    }

    /**
     * Clase que contiene una vista del item_acceso que extiende a la clase RecyclerView.ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Campos de la clase
        private ImageView imgAcceso;
        private TextView tvIdAcceso;
        private TextView tvTitulo;
        private TextView tvMetodo;
        private TextView tvFecha;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignación de los datos en el layout
            imgAcceso=(ImageView)itemView.findViewById(R.id.img_acceso);
            tvIdAcceso=(TextView)itemView.findViewById(R.id.tv_id_acceso);
            tvTitulo=(TextView)itemView.findViewById(R.id.tv_titulo_acceso);
            tvMetodo=(TextView)itemView.findViewById(R.id.tv_metodo);
            tvFecha=(TextView)itemView.findViewById(R.id.tv_fecha_acceso);
            context=itemView.getContext();
        }
    }
}
