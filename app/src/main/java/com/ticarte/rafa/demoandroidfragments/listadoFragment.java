package com.ticarte.rafa.demoandroidfragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListadoFragment extends ListFragment {

    private static String TAG = "ListadoFragment";
    private ArrayList<EventoItem> eventoItems;
    private ListView listView;
    private OnListadoFragmentListener myListener;

    /**
     * El constructor público vacío es obligatorio
     */
    public ListadoFragment() {}

    /**
     * OnCreate no debe crear vistas del fragmento
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate...");

        // Recupera los datos extras que nos envíe la actividad
        if (getArguments() != null) {
            //myParam = getArguments().getString(ARG_PARAM);
        }
    }

    /**
     * OnCreateView crea las vistas del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView...");

        // Carga el layout del fragmento y recupera el ListView para modificarlo posteriormente
        View rootView = inflater.inflate(R.layout.fragment_listado, container, false);
        listView = (ListView) rootView.findViewById(android.R.id.list);
        return rootView;
    }

    /**
     * OnActivityCreated se llama cuando OnCreate de la actividad ha terminado
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated...");

        // Crea los elementos a mostrar en la lista
        eventoItems = new ArrayList<EventoItem>();
        eventoItems.add(new EventoItem("1", "Primer evento"));
        eventoItems.add(new EventoItem("2", "Segundo evento"));
        eventoItems.add(new EventoItem("3", "Tercer evento"));
        eventoItems.add(new EventoItem("4", "Cuarto evento"));
        eventoItems.add(new EventoItem("5", "Quinto evento"));
        eventoItems.add(new EventoItem("6", "Sexto evento"));
        eventoItems.add(new EventoItem("7", "Séptimo evento"));
        eventoItems.add(new EventoItem("8", "Octavo evento"));

        // Crea el adaptador con los elementos y se lo asigna al ListView
        // Utiliza el layout predefinido de Android para la lista
        EventosAdapter eventosAdapter = new EventosAdapter(getActivity(), android.R.layout.simple_list_item_activated_1, eventoItems);
        listView.setAdapter(eventosAdapter);

        // Si están cargados los fragmentos "listado" y "contenido" al mismo tiempo
        // configura la lista para que sólo se pueda elegir un elemento
        if (getFragmentManager().findFragmentById(R.id.contenido_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, "onListItemClick...");

        // Notifica a la actividad el elemento seleccionado
        if (myListener != null) {
            myListener.onListadoInteraction(position, eventoItems.get(position));
        }

        // Marca como activo el elemento seleccionad
        // En este caso no funciona al utilizar un adaptador personalizado
        getListView().setItemChecked(position, true);
    }

    /**
     * Se ejecuta cuando se asocia el fragmento a la actividad
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach...");

        // Comprueba si está implementado el interfaz en la actividad
        // y se lo asigna
        if (context instanceof OnListadoFragmentListener) {
            myListener = (OnListadoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListadoFragmentListener");
        }
    }

    /**
     * Se ejecuta cuando se desasocia el fragmento de la actividad
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach...");

        // Elimina el interfaz de la actividad
        myListener = null;
    }

    /**
     * Interfaz que debe definir en su declaración la actividad
     */
    public interface OnListadoFragmentListener {
        void onListadoInteraction(int position, EventoItem item);
    }
}
