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

import com.example.taskthree.Adapter.PokemonItemAdapter;
import com.example.taskthree.Adapter.PokemonListItemAdapter;
import com.example.taskthree.Interface.InterfacePokemonItem;
import com.example.taskthree.Items.PokemonRootItem;
import com.example.taskthree.Items.ResultsOfItem;
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
public class ItemFragment extends Fragment {
    
    Retrofit retrofit;
    RecyclerView recyclerView;
    MaterialSearchBar searchBar;
    private static PokemonRootItem pokemonRootItem;
    PokemonItemAdapter pokemonItemAdapter,searchAdapter;
    List<String> last_suggest = new ArrayList<> (  );
    ProgressBar progressBar;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_item, container, false );

        searchBar = view.findViewById ( R.id.search_bar_item );
        progressBar = view.findViewById ( R.id.progressBaritem );
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
                recyclerView.setAdapter ( pokemonItemAdapter );
            }

            @Override
            public void onSearchConfirmed( CharSequence text ) {
                List<ResultsOfItem> resultSearch = new ArrayList<> (  );
                for ( ResultsOfItem resultsItem :pokemonRootItem.getResults ()){
                    if (resultsItem.getUrl ().split ( "/" )[6].contains ( text.toString () )){
                        resultSearch.add ( resultsItem);
                    }
                }
                searchAdapter = new PokemonItemAdapter ( getActivity (),resultSearch );
                recyclerView.setAdapter ( searchAdapter );
            }

            @Override
            public void onButtonClicked( int buttonCode ) {

            }
        } );

        recyclerView = view.findViewById ( R.id.recycler_item_item );
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
        InterfacePokemonItem service2 = retrofit.create ( InterfacePokemonItem.class );
        Call<PokemonRootItem> pokemonRootItemCall = service2.getPokemonItemList ();
        pokemonRootItemCall.enqueue ( new Callback<PokemonRootItem> () {
            @Override
            public void onResponse( Call<PokemonRootItem> call, Response<PokemonRootItem> response ) {
                if(!response.isSuccessful ()){
                    return;
                }
                pokemonRootItem = response.body ();
                pokemonItemAdapter = new PokemonItemAdapter ( getActivity (), response.body ().getResults () );
                recyclerView.setAdapter (pokemonItemAdapter );


                last_suggest.clear ();

                for (int i = 0;i<response.body ().getCount ();i++){
                    last_suggest.add ( String.valueOf ( i+1 ) );
                }

                searchBar.setVisibility ( View.VISIBLE );
                searchBar.setLastSuggestions ( last_suggest );

                progressBar.setVisibility ( View.INVISIBLE );
            }

            @Override
            public void onFailure( Call<PokemonRootItem> call, Throwable t ) {

            }
        } );
    }
}
