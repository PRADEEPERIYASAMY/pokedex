package com.example.taskthree.Interface;

import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Models.Pokemons;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfacePokemonCount {
    @GET("pokemon")
    Call<PokemonsCount> getListOfPokemonCount( @Query ( "limit" ) int limit,@Query ( "offset" ) int offset );
}
