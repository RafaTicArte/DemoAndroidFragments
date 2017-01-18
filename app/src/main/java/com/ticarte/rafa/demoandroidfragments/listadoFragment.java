package com.ticarte.rafa.demoandroidfragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link listadoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link listadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listadoFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView listView;
    private ArrayList<EventoItem> items;
    private EventosAdapter eventosAdapter;

    private OnFragmentInteractionListener mListener;

    public listadoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listadoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static listadoFragment newInstance(String param1, String param2) {
        Log.d("listadoFragment", "newInstance...");
        listadoFragment fragment = new listadoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("listadoFragment", "onCreate...");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("listadoFragment", "onCreateView...");

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_listado, container, false);

        View rootView = inflater.inflate(R.layout.fragment_listado, container, false);
        listView = (ListView) rootView.findViewById(android.R.id.list);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //int num = getArguments().getInt(ARG_SECTION_NUMBER);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        items = new ArrayList<EventoItem>();
        items.add(new EventoItem("1", "Primer acontecimiento"));
        items.add(new EventoItem("10", "Segundo acontecimiento"));
        eventosAdapter = new EventosAdapter(getActivity(), layout, items);
        listView.setAdapter(eventosAdapter);

    }

    @Override
    public void onStart() {
        Log.d("listadoFragment", "onStart...");
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.contenido_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    */

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("listadoFragment", "onListItemClick...");
        // Notify the parent activity of selected item
        if (mListener != null) {
            mListener.onFragmentInteraction(position, items.get(position));
        }

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onAttach(Context context) {
        Log.d("listadoFragment", "onAttach...");
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.d("listadoFragment", "onDetach...");
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position, EventoItem item);
    }
}
