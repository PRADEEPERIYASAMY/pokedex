package com.example.taskthree;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;


import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskthree.Adapter.PokemonListItemAdapter;
import com.example.taskthree.Common.Common;
import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Count.ResultsItem;
import com.example.taskthree.Database.PokemonDatabase;
import com.example.taskthree.Generation.PokemonGeneration;
import com.example.taskthree.Interface.InterfacePokemonCount;
import com.example.taskthree.Interface.InterfacePokemonGeneration;
import com.example.taskthree.Interface.InterfaceWithoutPagination;
import com.example.taskthree.SwipeHelper.MyButtonOnClickListener;
import com.example.taskthree.SwipeHelper.SwipeHelper;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonFragment extends Fragment {

    RecyclerView pokemon_list_recyclerview;
    PokemonListItemAdapter pokemonListItemAdapter,search_adapter;
    private static Retrofit retrofit;
    private static Retrofit retrofitGen;
    List<String> last_suggest = new ArrayList<> (  );
    MaterialSearchBar searchBar;
    private static PokemonsCount pokemonsCounts;
    private static List<ResultsItem> resultsItem = new ArrayList<> (  );
    private int offset;
    private boolean paraLoading;
    ProgressBar progressBar;
    TextView pokemonName;
    TextView pokemonId;
    FloatingActionMenu floatingActionMenuOptional;

    FloatingActionButton floatingActionButton1;
    FloatingActionButton floatingActionButton2;
    FloatingActionButton floatingActionButton3;
    FloatingActionButton floatingActionButton4;
    FloatingActionButton floatingActionButton5;
    FloatingActionButton floatingActionButton6;
    FloatingActionButton floatingActionButton7;
    FloatingActionButton floatingActionButton8;
    FloatingActionMenu floatingActionMenu;

    static List<String> gen1 = new ArrayList<> (  );
    static List<String> gen2 = new ArrayList<> (  );
    static List<String> gen3 = new ArrayList<> (  );
    static List<String> gen4 = new ArrayList<> (  );
    static List<String> gen5 = new ArrayList<> (  );
    static List<String> gen6 = new ArrayList<> (  );
    static List<String> gen7 = new ArrayList<> (  );

    MediaPlayer mediaPlayer;

    public PokemonFragment() {
        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        retrofitGen = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        for (int i = 1;i<=7;i++){
            InterfacePokemonGeneration interfacePokemonGeneration = retrofitGen.create ( InterfacePokemonGeneration.class );
            Call<PokemonGeneration> pokemonGenerationCall = interfacePokemonGeneration.getListOfPokemonFromGen ( i );
            final int finalI = i;
            pokemonGenerationCall.enqueue ( new Callback<PokemonGeneration> () {
                @Override
                public void onResponse( Call<PokemonGeneration> call, Response<PokemonGeneration> response ) {
                    if (!response.isSuccessful ()){
                        return;
                    }
                    if (finalI == 1){
                        for (int i = 0; i<response.body ().getPokemon_species ().size ();i++){
                            gen1.add ( response.body ().getPokemon_species ().get ( i ).getUrl ().split ( "/" )[6] );
                            paraLoading = false;
                        }
                    }
                    if (finalI == 2){
                        for (int i = 0; i<response.body ().getPokemon_species ().size ();i++){
                            gen2.add ( response.body ().getPokemon_species ().get ( i ).getUrl ().split ( "/" )[6] );
                        }
                    }
                    if (finalI == 3){
                        for (int i = 0; i<response.body ().getPokemon_species ().size ();i++){
                            gen3.add ( response.body ().getPokemon_species ().get ( i ).getUrl ().split ( "/" )[6] );
                        }
                    }
                    if (finalI == 4){
                        for (int i = 0; i<response.body ().getPokemon_species ().size ();i++){
                            gen4.add ( response.body ().getPokemon_species ().get ( i ).getUrl ().split ( "/" )[6] );
                        }
                    }
                    if (finalI == 5){
                        for (int i = 0; i<response.body ().getPokemon_species ().size ();i++){
                            gen5.add ( response.body ().getPokemon_species ().get ( i ).getUrl ().split ( "/" )[6] );
                        }
                    }
                    if (finalI == 6){
                        for (int i = 0; i<response.body ().getPokemon_species ().size ();i++){
                            gen6.add ( response.body ().getPokemon_species ().get ( i ).getUrl ().split ( "/" )[6] );
                        }
                    }
                    if (finalI == 7){
                        for (int i = 0; i<response.body ().getPokemon_species ().size ();i++){
                            gen7.add ( response.body ().getPokemon_species ().get ( i ).getUrl ().split ( "/" )[6] );
                        }
                    }

                }

                @Override
                public void onFailure( Call<PokemonGeneration> call, Throwable t ) {

                }
            } );
        }
    }


    @Override
    public View onCreateView( final LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        final View view = inflater.inflate ( R.layout.fragment_pokemon, container, false );

        progressBar = view.findViewById ( R.id.progressBar2 );
        progressBar.setMax ( 100 );
        progressBar.setVisibility ( View.VISIBLE );

        mediaPlayer = MediaPlayer.create ( getActivity (),R.raw.clicktwo );

        pokemon_list_recyclerview = view.findViewById ( R.id.recycler_list_item );
        pokemon_list_recyclerview.setHasFixedSize ( true );
        final LinearLayoutManager layoutManager = new LinearLayoutManager ( getActivity ());
        pokemon_list_recyclerview.setLayoutManager ( layoutManager );

        searchBar = view.findViewById ( R.id.search_bar );
        searchBar.setHint ( "Enter Pokemon Id" );
        searchBar.setCardViewElevation ( 10 );

        floatingActionButton1 = view.findViewById ( R.id.one );
        floatingActionButton2 = view.findViewById ( R.id.two );
        floatingActionButton3 = view.findViewById ( R.id.three );
        floatingActionButton4 = view.findViewById ( R.id.four );
        floatingActionButton5 = view.findViewById ( R.id.five );
        floatingActionButton6 = view.findViewById ( R.id.six );
        floatingActionButton7 = view.findViewById ( R.id.seven );
        floatingActionButton8 = view.findViewById ( R.id.eight );

        floatingActionMenu = view.findViewById ( R.id.fab_menu );
        floatingActionMenuOptional = view.findViewById ( R.id.fab_menu_optional );
        floatingActionMenuOptional.setVisibility ( View.VISIBLE );

        try {
            searchBar.addTextChangeListener ( new TextWatcher () {
                @Override
                public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
                    floatingActionMenu.close ( true );
                }

                @Override
                public void onTextChanged( CharSequence s, int start, int before, int count ) {
                    floatingActionMenu.close ( true );
                    if (resultsItem.size () <807 && resultsItem.size ()!=807){
                        fetchData ( 807,0);
                    }
                    else {
                        List<String> suggest = new ArrayList<> (  );
                        for (String search:last_suggest){
                            if (search.contains ( searchBar.getText () )){
                                suggest.add ( search );
                            }
                        }
                        searchBar.setLastSuggestions ( suggest );
                    }

                }

                @Override
                public void afterTextChanged( Editable s ) {
                    floatingActionMenu.close ( true );
                }
            } );

            searchBar.setOnSearchActionListener ( new MaterialSearchBar.OnSearchActionListener () {
                @Override
                public void onSearchStateChanged( boolean enabled ) {
                    floatingActionMenu.close ( true );
                    if (!enabled){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        List<String> id = new ArrayList<> (  );
                        for (int i=1;i<=807;i++){
                            id.add ( String.valueOf ( i ) );
                        }
                        pokemonListItemAdapter.allList ( resultsItem,id );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                    }

                }

                @Override
                public void onSearchConfirmed( CharSequence text ) {
                    floatingActionMenu.close ( true );
                    if (resultsItem.size () == 807){
                        List<String> searchId= new ArrayList<> (  );
                        for (ResultsItem result:pokemonsCounts.getResults ()){
                            if (result.getUrl ().split ( "/" )[6].contains ( text.toString () )){
                                searchId.add ( result.getUrl ().split ( "/" )[6] );
                            }
                        }
                        search_adapter = new PokemonListItemAdapter ( getActivity () );
                        search_adapter.allList ( pokemonsCounts.getResults (),searchId );
                        pokemon_list_recyclerview.setAdapter ( search_adapter );
                    }
                }

                @Override
                public void onButtonClicked( int buttonCode ) {
                }

            } );

            floatingActionMenuOptional.setOnMenuButtonClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () != 807){
                        fetchData ( 807,0 );
                    }
                    floatingActionMenuOptional.setVisibility ( View.INVISIBLE );
                }
            } );



            floatingActionButton1.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen1.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (),gen1 );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
            floatingActionButton2.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen2.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (),gen2 );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
            floatingActionButton3.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen3.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (),gen3 );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
            floatingActionButton4.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen4.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (),gen4 );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
            floatingActionButton5.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen5.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (),gen5 );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
            floatingActionButton6.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen6.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (),gen6 );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
            floatingActionButton7.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen7.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (),gen7 );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
            floatingActionButton8.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick( View v ) {
                    pokemonListItemAdapter.pokemonClear ();
                    if (pokemonsCounts.getResults ().size () == 807 && gen1.size ()>0){
                        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
                        List<String> id = new ArrayList<> (  );
                        for (int i=1;i<=807;i++){
                            id.add ( String.valueOf ( i ) );
                        }
                        pokemonListItemAdapter.allList ( pokemonsCounts.getResults (), id );
                        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );
                        floatingActionMenu.close ( true );
                    }
                    else {
                        fetchData ( 807,0 );
                    }
                }
            } );
        }
        catch (Exception ignored){

        }




        pokemon_list_recyclerview.addOnScrollListener ( new RecyclerView.OnScrollListener () {
            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy ) {
                super.onScrolled ( recyclerView, dx, dy );
                if (dy > 0){
                    int visibleItemCount = layoutManager.getChildCount ();
                    int totalItemCount = layoutManager.getItemCount ();
                    int pastVisibleItem = layoutManager.findFirstVisibleItemPosition ();

                    if (paraLoading && (visibleItemCount+pastVisibleItem) == totalItemCount-3 && offset<750 && resultsItem.size ()!=807){
                        paraLoading = false;
                        offset=offset+50;
                        progressBar.setVisibility ( View.VISIBLE );
                        if (offset<750){
                            fetchData ( 50,offset );
                        }
                        else {
                            fetchData ( 57,offset );
                        }

                    }
                }
            }
        } );

        offset = 0;
        paraLoading = true;
        fetchData ( 50,offset );

        pokemonListItemAdapter = new PokemonListItemAdapter ( getActivity () );
        pokemon_list_recyclerview.setAdapter ( pokemonListItemAdapter );


        SwipeHelper swipeHelper = new SwipeHelper (getActivity (),pokemon_list_recyclerview,400) {
            @Override
            public void instantiateButton( final RecyclerView.ViewHolder viewHolder, List<SwipeHelper.MyButton> buffer ) {
                buffer.add ( new MyButton ( getContext (),"Add",100,0, Color.rgb ( 103,58,183 ), new MyButtonOnClickListener () {
                    @Override
                    public void onClick( int pos ) {
                        pokemonName = viewHolder.itemView.findViewById (R.id.pokemon_item_name  );
                        pokemonId = viewHolder.itemView.findViewById ( R.id.pokemon_item_id );
                        mediaPlayer.start ();
                        PokemonDatabase pokemonDatabase = new PokemonDatabase ( getActivity () );
                        boolean success = pokemonDatabase.Insert ( pokemonName.getText ().toString (),Integer.parseInt ( pokemonId.getText ().toString () ) );
                        if (success && !PokemonDatabase.need){
                            Toast.makeText ( getActivity (),pokemonName.getText ().toString ()+" added to starred",Toast.LENGTH_LONG ).show ();
                        }
                        else if (PokemonDatabase.need ){
                            Toast.makeText ( getActivity (),pokemonName.getText ().toString ()+" already starred",Toast.LENGTH_LONG ).show ();
                        }
                        else {
                            Toast.makeText ( getActivity (),pokemonName.getText ().toString ()+" failed to starred",Toast.LENGTH_LONG ).show ();
                        }

                    }
                } ) );

            }
        };



        return view;
    }



    public void fetchData( final int limit, final int offset){
        InterfacePokemonCount service2 = retrofit.create ( InterfacePokemonCount.class );
        Call<PokemonsCount> pokemonCountCall = service2.getListOfPokemonCount (limit,offset);
        pokemonCountCall.enqueue ( new Callback<PokemonsCount> () {
            @Override
            public void onResponse( Call<PokemonsCount> call, Response<PokemonsCount> response ) {
                paraLoading = true;
                if(!response.isSuccessful ()){
                    return;
                }
                pokemonsCounts =  response.body ();
                resultsItem = response.body ().getResults ();

                List<String> id = new ArrayList<> (  );

                for (int i=offset+1;i<=offset+limit;i++){
                    id.add ( String.valueOf ( i ) );
                }

                pokemonListItemAdapter.allList(response.body ().getResults (),id);

                last_suggest.clear ();

                for (int i = 0;i<807;i++){
                    last_suggest.add ( String.valueOf ( i+1 ) );
                }

                searchBar.setVisibility ( View.VISIBLE );
                searchBar.setLastSuggestions ( last_suggest );
                progressBar.setVisibility ( View.INVISIBLE );


            }

            @Override
            public void onFailure( Call<PokemonsCount> call, Throwable t ) {
                paraLoading = true;
            }
        } );
    }



}
