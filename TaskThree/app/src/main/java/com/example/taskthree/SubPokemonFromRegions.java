package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskthree.Adapter.PokemonFromRegionsAdapter;
import com.example.taskthree.Adapter.TypePokemonsAdapter;
import com.example.taskthree.Interface.InterfacePokemonListFromType;
import com.example.taskthree.Interface.InterfacePokemonsFromRegions;
import com.example.taskthree.PokemonsFromRegions.RegionPokemons;
import com.example.taskthree.PokemonsFromType.PokemonsFromType;

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
public class SubPokemonFromRegions extends Fragment {

    RecyclerView recyclerview;
    Retrofit retrofit;
    String name;
    List<String> id;

    public SubPokemonFromRegions( String name) {
        // Required empty public constructor
        this.name = name;
    }


    @Override
    public View onCreateView( final LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_sub_pokemon_from_regions, container, false );
        recyclerview = view.findViewById ( R.id.recycler_pokemons_from_regions);
        recyclerview.setHasFixedSize ( true );
        recyclerview.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );


        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();


        InterfacePokemonsFromRegions interfacePokemonsFromRegions = retrofit.create ( InterfacePokemonsFromRegions.class );
        Call<RegionPokemons> regionPokemonsCall = interfacePokemonsFromRegions.getListofPokemonsFromREgions ( name );
        regionPokemonsCall.enqueue ( new Callback<RegionPokemons> () {
            @Override
            public void onResponse( Call<RegionPokemons> call, Response<RegionPokemons> response ) {
                if (!response.isSuccessful ()){
                    return;
                }

                id = new ArrayList<> (  );

                for (int i = 0;i<response.body ().getPokemon_entries ().size ();i++){
                    if (Integer.parseInt ( response.body ().getPokemon_entries ().get ( i ).getPokemon_species ().getUrl ().split ( "/" )[6] ) <= 807){
                        id.add ( response.body ().getPokemon_entries ().get ( i ).getPokemon_species ().getUrl ().split ( "/" )[6] );
                    }
                }

                PokemonFromRegionsAdapter pokemonFromRegionsAdapter = new PokemonFromRegionsAdapter ( getActivity (),response.body (),id );
                recyclerview.setAdapter ( pokemonFromRegionsAdapter );
            }

            @Override
            public void onFailure( Call<RegionPokemons> call, Throwable t ) {

            }
        } );


        return view;
    }
}
