package com.example.gastonsaillen.entendiendorecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NumberViewHolder> {


    /** Declaramos la cantidad de vistas que vamos a tener */
    private int mNumberItems;

    /** Creamos el Listener que va a escuchar por la posicion a clicar en la lista */
    final private ListItemClickListener mOnClickListener;

    /**Vamos a utilizar este constructor para pasar un entero de la cantidad de vistas que queremos y asignarselo a mNumberItems*/
    public RecyclerAdapter(int numberOfItems, ListItemClickListener listener){
    //Poblamos la cantidad de vistas que queremos mostrar
        mNumberItems = numberOfItems;
     //Le decimos al adaptador que item clicamos
    mOnClickListener = listener;
    }

    /**Creamos una interfaz que va a definir el listener*/
    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context mContext = parent.getContext(); //Pasamos el contexto de donde se va a inflar la vista
        int layoutIdForListItem = R.layout.number_list_item; //buscamos el layout de la vista para el viewHolder
        LayoutInflater inflater = LayoutInflater.from(mContext); // Creamos un layoutInflater que sirve para bindear esa vista a cada elemento del Recycler
        boolean shouldAttachToParentImmediately = false; //Sirve para no poblar rapidamente la vista al padre

        View view = inflater.inflate(layoutIdForListItem,parent,shouldAttachToParentImmediately); //Creamos una vista donde inflamos la layout ya antes llamada , le pasamos el parent donde se va a mostrar y atachamos el boolean
        NumberViewHolder viewHolder = new NumberViewHolder(view); //Llamamos a la clase NumberViewHolder y le pasamos la vista para cada elemento

        return viewHolder; //retornamos un View
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position); //Usamos el NumberViewHolder para poder bindear datos adentro de cada vista

    }

    @Override
    public int getItemCount() {
        return mNumberItems; //Devolvemos la cantidad de items que va a tener el recycler a especificar
    }


    /**Creamos el viewHolder para llamar al texto que creamos en el number_list_item
     * Implementamos la interfaz onClickListener para que se invoque en cada vista que clicamos*/

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView listItemNumberView;

        public NumberViewHolder(View itemView) {
            super(itemView);

            //Encontramos el textview que creamos en el layout
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            //Le decimos que se utilize clickListener en este constructor
            itemView.setOnClickListener(this);

        }

        /**Nos va a servir para bindear datos a las vistas creadas con el viewHolder*/
        void bind(int listIndex){

            //Le seteamos al textview buscado en el viewHolder el valor de listIndex que es un entero
            listItemNumberView.setText(String.valueOf(listIndex));
        }

        @Override
        public void onClick(View view) {
            //Obtenemos la posicion en el adaptador que clicamos
            int clickedPosition = getAdapterPosition();
            //Al final le pasamos al click listener la posicion que acabamos de clicar
            mOnClickListener.onListItemClick(clickedPosition);


        }
    }
}
