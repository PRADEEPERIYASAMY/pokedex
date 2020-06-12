package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.taskthree.Adapter.TypePokemonsAdapter;
import com.example.taskthree.Interface.InterfacePokemonListFromType;
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
public class SubTypeFragment extends Fragment {

    RecyclerView recyclerview;
    Retrofit retrofit;
    static List<String> id;
    public static int Id;
    public SubTypeFragment(int id) {
        // Required empty public constructor
        this.Id = id;
    }


    @Override
    public View onCreateView( final LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_sub_type, container, false );
        recyclerview = view.findViewById ( R.id.recycler_type_from_type);
        recyclerview.setHasFixedSize ( true );
        recyclerview.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );


        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        InterfacePokemonListFromType service2 = retrofit.create ( InterfacePokemonListFromType.class );
        Call<PokemonsFromType> pokemonsFromTypeCall = service2.getListOfPokemonFromType ( Id );
        pokemonsFromTypeCall.enqueue ( new Callback<PokemonsFromType> () {
            @Override
            public void onResponse( Call<PokemonsFromType> call, Response<PokemonsFromType> response ) {
                if(!response.isSuccessful ()){
                    return;
                }

                id = new ArrayList<> (  );

                for (int i = 0; i<response.body ().getPokemon ().size ();i++){
                    if (Integer.parseInt ( response.body ().getPokemon ().get ( i ).getPokemon ().getUrl ().split ( "/" )[6] ) <= 807){
                        id.add ( response.body ().getPokemon ().get ( i ).getPokemon ().getUrl ().split ( "/" )[6] );
                    }
                }


                TypePokemonsAdapter typePokemonsAdapter = new TypePokemonsAdapter ( getActivity (), response.body (),id );
                recyclerview.setAdapter ( typePokemonsAdapter );

            }

            @Override
            public void onFailure( Call<PokemonsFromType> call, Throwable t ) {

            }
        } );
        return view;
    }
}
