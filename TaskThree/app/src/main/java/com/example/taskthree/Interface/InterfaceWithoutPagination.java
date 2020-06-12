package com.example.taskthree.Interface;

import com.example.taskthree.Count.PokemonsCount;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  InterfaceWithoutPagination {
    @GET("pokemon?limit=807&offset=0")
    Call<PokemonsCount> getListOfPokemonCountWithoutPagination( );
}
