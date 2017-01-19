package com.ticarte.rafa.demoandroidfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContenidoFragment extends Fragment {

    private static String TAG = "ListadoFragment";
    private String myId = "";

    /**
     * El constructor público vacío es obligatorio
     */
    public ContenidoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView...");
        super.onCreateView(inflater, container, savedInstanceState);

        // Si la actividad se recrea (por ejemplo después de una rotación)
        // recupera los datos de estado guardados por "onSaveInstanceState".
        // Necesario cuando los fragmentos "listado" y "contenido" están visibles
        if (savedInstanceState != null) {
            myId = savedInstanceState.getString("id");
        }

        // Carga el layout del fragmento
        return inflater.inflate(R.layout.fragment_contenido, container, false);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart...");
        super.onStart();

        // Comprueba si se han pasado datos extras al fragmento
        // y se actualiza la vista en función de ellos
        Bundle args = getArguments();
        if (args != null) {
            // Actualiza el contenido en función de los datos extras recibidos
            updateContenidoFragment(args.getString("id"));
        } else if (!myId.equals("")) {
            // Set article based on saved instance state defined during onCreateView
            // Actualiza el contenido en función de los datos de estado guardados
            updateContenidoFragment(myId);
        }
    }

    /**
     * Actualiza los datos del fragmento
     */
    public void updateContenidoFragment(String id){
        Log.d(TAG, "updateContenidoFragment...");

        TextView article = (TextView) getActivity().findViewById(R.id.textViewContenido);
        article.setText("Id: "+id);
    }

    /**
     * Almacena el estado del fragmento para poder ser recuperado
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState...");
        super.onSaveInstanceState(outState);

        // Guarda los datos
        outState.putString("id", myId);
    }
}
