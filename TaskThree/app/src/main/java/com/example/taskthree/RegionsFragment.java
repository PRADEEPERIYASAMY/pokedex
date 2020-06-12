package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.taskthree.Adapter.PokemonLocationAdapter;
import com.example.taskthree.Adapter.PokemonRegionsAdapter;
import com.example.taskthree.Interface.InterfacePokemonLocation;
import com.example.taskthree.Interface.InterfacePokemonRegions;
import com.example.taskthree.Regions.RootRegions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegionsFragment extends Fragment {
    
    Retrofit retrofit;
    RecyclerView recyclerView;
    FragmentTransaction fragmentTransaction;
    FragmentTransaction fragmentTransaction1;
    ProgressBar progressBar;

    public RegionsFragment( FragmentTransaction fragmentTransaction,FragmentTransaction fragmentTransaction1 ) {
        this.fragmentTransaction = fragmentTransaction;
        this.fragmentTransaction1 = fragmentTransaction1;
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_location, container, false );

        progressBar = view.findViewById ( R.id.progressBarlocation);
        progressBar.setMax ( 100 );
        progressBar.setVisibility ( View.VISIBLE );

        recyclerView = view.findViewById ( R.id.recycler_location_item );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );

        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        InterfacePokemonRegions service2 = retrofit.create ( InterfacePokemonRegions.class );
        Call<RootRegions> RootRegionsCall = service2.getPokemonRegionList ();
        RootRegionsCall.enqueue ( new Callback<RootRegions> () {
            @Override
            public void onResponse( Call<RootRegions> call, Response<RootRegions> response ) {
                if(!response.isSuccessful ()){
                    return;
                }
                PokemonRegionsAdapter pokemonRegionsAdapter = new PokemonRegionsAdapter ( getActivity (), response.body (),fragmentTransaction,fragmentTransaction1 );
                recyclerView.setAdapter (pokemonRegionsAdapter );

                progressBar.setVisibility ( View.INVISIBLE );

            }

            @Override
            public void onFailure( Call<RootRegions> call, Throwable t ) {

            }
        } );
        return view;
    }
}
