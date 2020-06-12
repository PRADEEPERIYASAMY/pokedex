package com.example.taskthree.Interface;

import com.example.taskthree.PokemonEvolutionChain.PokemonEvolutionChain;
import com.example.taskthree.Species.PokemonSpecies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfacePokemonChain {
    @GET("evolution-chain/{id}")
    Call<PokemonEvolutionChain> getPokemonEvolutionChain( @Path("id") int id);
}
