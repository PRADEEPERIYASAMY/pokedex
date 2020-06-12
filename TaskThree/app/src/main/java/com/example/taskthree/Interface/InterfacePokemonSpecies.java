package com.example.taskthree.Interface;

import com.example.taskthree.Species.PokemonSpecies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfacePokemonSpecies {
    @GET("pokemon-species/{id}")
    Call<PokemonSpecies> getPokemonSpecies( @Path("id") int id);
}
