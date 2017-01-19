package com.ticarte.rafa.demoandroidfragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventosAdapter extends ArrayAdapter<EventoItem> {

    private static String TAG = "EventosAdapter";
    private final Context context;
    private final ArrayList<EventoItem> items;

    public EventosAdapter(Context context, int resource, ArrayList<EventoItem> items) {
        super(context, resource, items);

        this.context = context;
        this.items = items;
    }

    /**
     * Personaliza la vista de cada elemento de la lista
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView...");

        // Carga en la vista el layout de la fila de la lista
        // y le asigna los datos
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.evento_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.evento_nombre);
        textView.setText(items.get(position).getNombre());
        return rowView;
    }
}
