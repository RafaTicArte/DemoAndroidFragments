package com.ticarte.rafa.demoandroidfragments;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
    implements listadoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.unique_fragment) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            listadoFragment listadoFrag = new listadoFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            listadoFrag.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.unique_fragment, listadoFrag).commit();
        }
    }

    public void onFragmentInteraction(int position, EventoItem item){
        Log.d("MainActivity", "onFragmentInteraction...");
        // Capture the article fragment from the activity layout
        contenidoFragment contenidoFrag = (contenidoFragment)
                getSupportFragmentManager().findFragmentById(R.id.contenido_fragment);

        if (contenidoFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            contenidoFrag.updateView(position, item.getId());
        } else {
            // Create fragment and give it an argument for the selected article
            contenidoFragment newcontenidoFrag = new contenidoFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("id", item.getId());
            newcontenidoFrag.setArguments(args);

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.unique_fragment, newcontenidoFrag);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}
