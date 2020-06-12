package com.example.taskthree.Interface;

import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Items.PokemonRootItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfacePokemonItem {
    @GET("item/?offset=0&limit=807")
    Call<PokemonRootItem> getPokemonItemList( );
}
