package com.example.taskthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.taskthree.Common.Common;
import com.example.taskthree.Interface.InterfacePokemon;
import com.example.taskthree.Interface.InterfacePokemonSpecies;
import com.example.taskthree.Models.Pokemons;
import com.example.taskthree.Species.PokemonSpecies;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main4Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    public static int Id;
    Retrofit retrofit;
    Retrofit retrofit1;
    Pokemons pokemons = null;
    Fragment fragment;
    private static DataFragment dataFragment;

    public static PokemonSpecies pokemonSpecies;
    @Override
    protected void onCreate( final Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView ( R.layout.activity_main4 );
        Intent intent = getIntent ();
        Id = intent.getIntExtra ( "ID",1 );


        bottomNavigationView = findViewById ( R.id.bottom_nav );


        retrofit1 = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        InterfacePokemonSpecies interfacePokemonSpecies = retrofit1.create ( InterfacePokemonSpecies.class );
        Call<PokemonSpecies> pokemonSpeciesCall = interfacePokemonSpecies.getPokemonSpecies ( Id );
        pokemonSpeciesCall.enqueue ( new Callback<PokemonSpecies> () {
            @Override
            public void onResponse( Call<PokemonSpecies> call, Response<PokemonSpecies> response ) {
                if (!response.isSuccessful ()){
                    return;
                }
                pokemonSpecies = response.body ();
            }

            @Override
            public void onFailure( Call<PokemonSpecies> call, Throwable t ) {

            }
        } );

        retrofit = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        InterfacePokemon service = retrofit.create ( InterfacePokemon.class );
        Call<Pokemons> pokemon = service.getListOfPokemon (Id);
        pokemon.enqueue ( new Callback<Pokemons> () {
            @Override
            public void onResponse( Call<Pokemons> call, final Response<Pokemons> response ) {
                if(!response.isSuccessful () && pokemonSpecies == null){
                    return;
                }


                if(savedInstanceState == null  ){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
                    getSupportFragmentManager ().beginTransaction ().replace ( R.id.second_container,new DataFragment (Id,response.body (),pokemonSpecies,fragmentTransaction)).commit ();
                }

                bottomNavigationView.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener () {
                    @Override
                    public boolean onNavigationItemSelected( @NonNull MenuItem item ) {
                        fragment = null;
                        switch (item.getItemId ()){
                            case R.id.bot_data:
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
                                dataFragment = new DataFragment (Id,response.body (),pokemonSpecies,fragmentTransaction);
                                fragment = new DataFragment (Id,response.body (),pokemonSpecies,fragmentTransaction);
                                getSupportFragmentManager ().beginTransaction ().replace ( R.id.second_container,fragment ).commit ();
                                break;
                            case R.id.bot_evolutions:
                                fragment = new EvolutionFragment (Id,pokemonSpecies);
                                getSupportFragmentManager ().beginTransaction ().replace ( R.id.second_container,fragment ).commit ();
                                break;
                            case R.id.bot_type:
                                fragment = new TypeInsideFragment (Id,response.body ());
                                getSupportFragmentManager ().beginTransaction ().replace ( R.id.second_container,fragment ).commit ();
                                break;
                            case R.id.bot_moves:
                                fragment = new MovesFragment (Id,response.body ());
                                getSupportFragmentManager ().beginTransaction ().replace ( R.id.second_container,fragment ).commit ();
                                break;
                        }
                        item.setChecked ( true );
                        return true;
                    }
                } );


            }


            @Override
            public void onFailure( Call<Pokemons> call, Throwable t ) {

            }
        } );


    }

}
