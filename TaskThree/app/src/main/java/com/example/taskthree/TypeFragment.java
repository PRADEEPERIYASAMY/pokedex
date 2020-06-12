package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.taskthree.Adapter.PokemonListItemAdapter;
import com.example.taskthree.Adapter.PokemonTypeAdapter;
import com.example.taskthree.Common.Common;
import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Interface.InterfacePokemonCount;
import com.example.taskthree.Interface.InterfacePokemonRootTyres;
import com.example.taskthree.Types.PokemonTypeRoot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends Fragment {


    Retrofit retrofit;
    RecyclerView pokemon_type_recyclerview;
    FragmentTransaction fragmentTransaction;
    ProgressBar progressBar;

    public TypeFragment(FragmentTransaction fragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction;
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_type, container, false );

        progressBar = view.findViewById ( R.id.progressBartype);
        progressBar.setMax ( 100 );
        progressBar.setVisibility ( View.VISIBLE );

        pokemon_type_recyclerview = view.findViewById ( R.id.recycler_type_item );
        pokemon_type_recyclerview.setHasFixedSize ( true );
        pokemon_type_recyclerview.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );

        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        InterfacePokemonRootTyres service2 = retrofit.create ( InterfacePokemonRootTyres.class );
        Call<PokemonTypeRoot> pokemonTypeRootCall = service2.getListOfPokemonRootTypeList ();
        pokemonTypeRootCall.enqueue ( new Callback<PokemonTypeRoot> () {
            @Override
            public void onResponse( Call<PokemonTypeRoot> call, Response<PokemonTypeRoot> response ) {
                if(!response.isSuccessful ()){
                    return;
                }
                PokemonTypeAdapter pokemonTypeAdapter = new PokemonTypeAdapter ( getActivity (), response.body (),fragmentTransaction);
                pokemon_type_recyclerview.setAdapter ( pokemonTypeAdapter );

                progressBar.setVisibility ( View.INVISIBLE );

            }

            @Override
            public void onFailure( Call<PokemonTypeRoot> call, Throwable t ) {

            }
        } );
        return view;
    }
}
