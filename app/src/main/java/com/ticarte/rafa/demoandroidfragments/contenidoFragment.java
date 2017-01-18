package com.ticarte.rafa.demoandroidfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class contenidoFragment extends Fragment {
    int mCurrentPosition = -1;
    String id;

    public contenidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("contenidoFragment", "onCreateView...");

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt("position");
            id = savedInstanceState.getString("id");
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contenido, container, false);
    }

    @Override
    public void onStart() {
        Log.d("contenidoFragment", "onStart...");
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateView(args.getInt("position"), args.getString("id"));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateView(mCurrentPosition, id);
        }
    }

    public void updateView(int position, String id){
        Log.d("contenidoFragment", "updateView...");
        TextView article = (TextView) getActivity().findViewById(R.id.textView);
        article.setText("Position: "+position+" | Id: "+id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("contenidoFragment", "onSaveInstanceState...");
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt("position", mCurrentPosition);
        outState.putString("id", id);
    }
}
