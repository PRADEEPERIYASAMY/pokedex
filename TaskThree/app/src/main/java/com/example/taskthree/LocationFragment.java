package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.taskthree.Adapter.PokemonLocationAdapter;
import com.example.taskthree.Adapter.PokemonLocationAdapter;
import com.example.taskthree.Interface.InterfacePokemonItem;
import com.example.taskthree.Interface.InterfacePokemonLocation;
import com.example.taskthree.Items.PokemonRootItem;
import com.example.taskthree.Items.ResultsOfItem;
import com.example.taskthree.Location.ResultLocation;
import com.example.taskthree.Location.RootLocation;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {
    
    RecyclerView recyclerView;
    Retrofit retrofit;
    MaterialSearchBar searchBar;
    private static RootLocation rootLocation;
    private static List<ResultLocation> resultLocation;
    PokemonLocationAdapter pokemonLocationAdapter,searchAdapter;
    List<String> last_suggest = new ArrayList<> (  );
    ProgressBar progressBar;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_location, container, false );

        searchBar = view.findViewById ( R.id.search_bar_location );
        progressBar = view.findViewById ( R.id.progressBarlocation);
        progressBar.setMax ( 100 );
        progressBar.setVisibility ( View.VISIBLE );

        searchBar.addTextChangeListener ( new TextWatcher () {
            @Override
            public void beforeTextChanged( CharSequence s, int start, int count, int after ) {

            }

            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count ) {
                List<String> suggest = new ArrayList<> (  );
                for (String search:last_suggest){
                    if (search.contains ( searchBar.getText () )){
                        suggest.add ( search );
                    }
                }
                searchBar.setLastSuggestions ( suggest );
            }

            @Override
            public void afterTextChanged( Editable s ) {

            }
        } );

        searchBar.setOnSearchActionListener ( new MaterialSearchBar.OnSearchActionListener () {
            @Override
            public void onSearchStateChanged( boolean enabled ) {
                pokemonLocationAdapter = new PokemonLocationAdapter ( getActivity (),resultLocation );
                recyclerView.setAdapter ( pokemonLocationAdapter );
            }

            
            @Override
            public void onSearchConfirmed( CharSequence text ) {
                List<ResultLocation> resultSearch = new ArrayList<> (  );
                for ( ResultLocation resultsLocation :resultLocation){
                    if (resultsLocation.getUrl ().split ( "/" )[6].contains ( text.toString () )){
                        resultSearch.add ( resultsLocation);
                    }
                }
                searchAdapter = new PokemonLocationAdapter ( getActivity (),resultSearch );
                recyclerView.setAdapter ( searchAdapter );
            }

            @Override
            public void onButtonClicked( int buttonCode ) {

            }
        } );

        recyclerView = view.findViewById ( R.id.recycler_location_item );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );

        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        FetchData ();
        
        return view;
    }
    
    private void FetchData(){
        InterfacePokemonLocation service2 = retrofit.create ( InterfacePokemonLocation.class );
        Call<RootLocation> RootLocationCall = service2.getPokemonLocationList ();
        RootLocationCall.enqueue ( new Callback<RootLocation> () {
            @Override
            public void onResponse( Call<RootLocation> call, Response<RootLocation> response ) {
                if(!response.isSuccessful ()){
                    return;
                }
                rootLocation = response.body ();
                resultLocation = rootLocation.getResults ();
                PokemonLocationAdapter pokemonLocationAdapter = new PokemonLocationAdapter ( getActivity (), resultLocation );
                recyclerView.setAdapter (pokemonLocationAdapter );

                last_suggest.clear ();

                for (int i = 0;i<response.body ().getCount ();i++){
                    last_suggest.add ( String.valueOf ( i+1 ) );
                }

                searchBar.setVisibility ( View.VISIBLE );
                searchBar.setLastSuggestions ( last_suggest );

                progressBar.setVisibility ( View.INVISIBLE );


            }

            @Override
            public void onFailure( Call<RootLocation> call, Throwable t ) {

            }
        } );
    }
}
