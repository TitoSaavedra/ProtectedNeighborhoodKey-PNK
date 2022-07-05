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
import cl.inacap.pnk.io.entidadesDAO.SolicitudVisita;

/**
 * Esta clase se utiliza para crear vistas del layuot item_visita
 * segun la cantidad de la lista
 * @author Pablo Ahumada Olivares
 * @version 1.0
 */
public class AdapterVisitas extends RecyclerView.Adapter<AdapterVisitas.ViewHolder> {
    //Campos de la clase
    private List<SolicitudVisita> solicitudVisitaList;

    /**
     * Constructor de la clase
     * @param solicitudVisitaList Define la lista que se utilizara para crear los layout
     */
    public AdapterVisitas(List<SolicitudVisita> solicitudVisitaList) {
        this.solicitudVisitaList = solicitudVisitaList;
    }//Cierre del constructor

    /**
     * Metodo para obtener la lista en el objeto
     * @return La lista actual del objeto
     */
    public List<SolicitudVisita> getSolicitudVisitaList() {
        return solicitudVisitaList;
    }

    /**
     * Metodo para colocar la lista en el objeto
     * @param solicitudVisitaList La lista a colocar en el objeto
     */
    public void setSolicitudVisitaList(List<SolicitudVisita> solicitudVisitaList) {
        this.solicitudVisitaList = solicitudVisitaList;
    }

    /**
     * Metodo que se utiliza para crear el objeto de cada item
     * @param parent El padre de la vista para encontrar el contexto
     * @param viewType Tipo de vista a utilizar
     * @return La vista ya asignada
     */
    @NonNull
    @Override
    public AdapterVisitas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visita,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Metodo para darles los valores a la vista creada de cada item_visita
     * @param holder vista de un item_visita
     * @param position cada posicion del campo solicitudVisitaList
     */
    @Override
    public void onBindViewHolder(@NonNull final AdapterVisitas.ViewHolder holder, int position) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        holder.tvIdVisita.setText(""+solicitudVisitaList.get(position).getVisita().getIdPersona());
        holder.tvRutVisita.setText("Rut: "+solicitudVisitaList.get(position).getVisita().getRut());
        holder.tvNombreVisita.setText("Nombre: "+solicitudVisitaList.get(position).getVisita().getNombre()+" "+solicitudVisitaList.get(position).getVisita().getApellidoPa());
        holder.imgVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("id_persona",Integer.parseInt(holder.tvIdVisita.getText().toString()));
                Navigation.findNavController(v).navigate(R.id.action_nav_visitas_to_visitaPerfil,bundle);
            }
        });
    }

    /**
     * Metodo para obtener la cantidad de la lista
     * @return La cantidad de la lista
     */
    @Override
    public int getItemCount() {
        return solicitudVisitaList.size();
    }

    /**
     * Clase que contiene una vista del item_visita que extiende a la clase RecyclerView.ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        //Campos de la clase
        private ImageView imgVisita;
        private TextView tvIdVisita;
        private TextView tvRutVisita;
        private TextView tvNombreVisita;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Asignación de los datos en el layout
            imgVisita=(ImageView)itemView.findViewById(R.id.img_visita);
            tvIdVisita=(TextView)itemView.findViewById(R.id.tv_id_visita);
            tvRutVisita=(TextView)itemView.findViewById(R.id.tv_rut_visita);
            tvNombreVisita=(TextView)itemView.findViewById(R.id.tv_nombre_visita);
            context=itemView.getContext();
        }
    }
}
