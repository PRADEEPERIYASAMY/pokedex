package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskthree.Adapter.RegionsPokedexAdapter;
import com.example.taskthree.Interface.InterfacePokemonRegionsDetails;
import com.example.taskthree.RegionsDetials.RegionsDetials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class subRegionsFragment extends Fragment {

    RecyclerView recyclerview;
    Retrofit retrofit;
    public static int ID;
    FragmentTransaction fragmentTransaction;
    
    public subRegionsFragment( int id,FragmentTransaction fragmentTransaction) {
        this.ID = id;
        this.fragmentTransaction = fragmentTransaction;
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_sub_regions, container, false );
        recyclerview = view.findViewById ( R.id.recycler_type_pokedexes);
        recyclerview.setHasFixedSize ( true );
        recyclerview.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );


        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        InterfacePokemonRegionsDetails interfacePokemonRegionsDetails = retrofit.create ( InterfacePokemonRegionsDetails.class );
        Call<RegionsDetials> regionsDetialsCall = interfacePokemonRegionsDetails.getPokemonRegionsDetails ( ID );
        regionsDetialsCall.enqueue ( new Callback<RegionsDetials> () {
            @Override
            public void onResponse( Call<RegionsDetials> call, Response<RegionsDetials> response ) {
                if (!response.isSuccessful ()){
                    return;
                }
                RegionsPokedexAdapter regionsPokedexAdapter = new RegionsPokedexAdapter ( getActivity (),response.body (), fragmentTransaction );
                recyclerview.setAdapter ( regionsPokedexAdapter );
            }

            @Override
            public void onFailure( Call<RegionsDetials> call, Throwable t ) {

            }
        } );

        return view;
    }
}
