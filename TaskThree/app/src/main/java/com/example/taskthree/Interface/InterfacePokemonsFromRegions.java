package com.example.taskthree.Interface;

import com.example.taskthree.PokemonsFromRegions.RegionPokemons;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfacePokemonsFromRegions {
    @GET("pokedex/{string}")
    Call<RegionPokemons> getListofPokemonsFromREgions( @Path  ( "string" ) String string);
}
