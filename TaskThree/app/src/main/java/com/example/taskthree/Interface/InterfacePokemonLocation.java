package com.example.taskthree.Interface;

import com.example.taskthree.Items.PokemonRootItem;
import com.example.taskthree.Location.RootLocation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfacePokemonLocation {
    @GET("location?offset=0&limit=781")
    Call<RootLocation> getPokemonLocationList( );
}
