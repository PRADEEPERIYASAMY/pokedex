package com.example.taskthree.Interface;

import com.example.taskthree.Models.Pokemons;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfacePokemon {
    @GET("pokemon/{id}")
    Call<Pokemons> getListOfPokemon( @Path("id") int id);
}
