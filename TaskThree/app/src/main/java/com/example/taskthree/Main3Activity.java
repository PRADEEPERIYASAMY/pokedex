package com.example.taskthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.taskthree.Common.Common;
import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Interface.InterfacePokemon;
import com.example.taskthree.Interface.InterfacePokemonCount;
import com.example.taskthree.Models.Pokemons;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private static Fragment fragment;
    private NavigationView navigationView;
    private static FragmentTransaction fragmentTransaction;
    private static FragmentTransaction fragmentTransaction1;
    private static Toolbar toolbar;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView ( R.layout.activity_main3 );

        mediaPlayer = MediaPlayer.create ( getApplicationContext (),R.raw.clicktwo );

        toolbar = findViewById ( R.id.toolbar );
        toolbar.setTitle ( "Pokemons" );
        setSupportActionBar ( toolbar );

        drawerLayout = findViewById ( R.id.drawer_layout );

        navigationView = findViewById ( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener ( this );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle ( this,drawerLayout,toolbar,R.string.open,R.string.close );

        drawerLayout.addDrawerListener ( toggle );
        toggle.syncState ();

        if(savedInstanceState == null){
            getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,new PokemonFragment () ).commit ();
            navigationView.setCheckedItem ( R.id.nav_pokemon );
        }


    }
    protected void onPause() {
        super.onPause ();
        overridePendingTransition ( android.R.anim.fade_in,android.R.anim.fade_out );
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen ( GravityCompat.START )){
            drawerLayout.closeDrawer ( GravityCompat.START );
        }
        else {
            if (fragment instanceof PokemonFragment){
                super.onBackPressed ();
            }
            else {
                fragment = new PokemonFragment ();
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,fragment ).commit ();
                toolbar.setTitle ( "Pokemons" );
            }
        }

    }
    @Override
    public boolean onNavigationItemSelected( @NonNull MenuItem item ) {
        switch (item.getItemId ()){
            case R.id.nav_pokemon:
                mediaPlayer.start ();
                fragment = new PokemonFragment ();
                toolbar.setTitle ( "Pokemons" );
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,fragment ).commit ();
                break;
            case R.id.nav_item:
                mediaPlayer.start ();
                fragment = new ItemFragment ();
                toolbar.setTitle ( "Items" );
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,fragment ).commit ();
                break;
            case R.id.nav_location:
                mediaPlayer.start ();
                fragment = new LocationFragment ();
                toolbar.setTitle ( "Locations" );
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,fragment ).commit ();
                break;
            case R.id.nav_type:
                mediaPlayer.start ();
                fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
                toolbar.setTitle ( "Types" );
                fragment = new TypeFragment ( fragmentTransaction);
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,fragment ).commit ();
                break;
            case R.id.nav_region:
                mediaPlayer.start ();
                fragmentTransaction = getSupportFragmentManager ().beginTransaction ();
                fragmentTransaction1 = getSupportFragmentManager ().beginTransaction ();
                toolbar.setTitle ( "Regions" );
                fragment = new RegionsFragment ( fragmentTransaction,fragmentTransaction1);
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,fragment ).commit ();
                break;
            case R.id.nav_starred:
                mediaPlayer.start ();
                fragment = new StarredFragment ();
                toolbar.setTitle ( "Starred" );
                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container,fragment ).commit ();
                break;

        }
        drawerLayout.closeDrawer ( GravityCompat.START );
        navigationView.setCheckedItem ( item );
        return true;
    }

}
