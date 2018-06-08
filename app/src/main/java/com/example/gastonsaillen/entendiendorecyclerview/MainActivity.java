package com.example.gastonsaillen.entendiendorecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener {

    //Definimos la cantidad de items en la lista
    private static final int NUM_LIST_ITEMS = 100;
    // Llamamos a RecyclerAdapter para poder utilizarlo en nuestro RecyclerView
    private RecyclerAdapter mAdapter;
    //Creamos el recyclerView
    private RecyclerView mNumberList;
    //Creamos un Toast global para evitar que se muestren repetidamente cuando el usuario clica muy rapidamente en los elementos
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberList = (RecyclerView) findViewById(R.id.rv_numbers);
        //Creamos un divider por cada elemento del RV de manera vertical
        mNumberList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //Creamos un LinearLayout con el manager para mostrar el RecyclerView de forma de lista
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //Le seteamos al recyclerView el layoutManager, en este caso LinearLayout
        mNumberList.setLayoutManager(linearLayoutManager);
       //En true hacemos optimizacion de la UI en nuestro RV, evita que se invalide la layout cuando el contenido del adaptador cambia
        mNumberList.setHasFixedSize(true);
        //Le pasamos al adaptador la cantidad de items
        //Le pasamos this por que el listener necesita un contexto de donde tomar ese click, en este caso es esta activity
        mAdapter = new RecyclerAdapter(NUM_LIST_ITEMS,this);
        //Finalmente le seteamos al RV el adaptador
        mNumberList.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        //Con esto chequeamos que no se vayan encolando los textView y al clicar en distintos items solo nos muestre el que clicamos
        if(mToast!= null){
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked";
        mToast = Toast.makeText(this,toastMessage,Toast.LENGTH_LONG);
        mToast.show();

    }
}
