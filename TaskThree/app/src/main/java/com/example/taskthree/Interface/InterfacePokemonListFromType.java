package com.example.taskthree.Interface;

import com.example.taskthree.Models.Pokemons;
import com.example.taskthree.PokemonsFromType.PokemonsFromType;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfacePokemonListFromType {
    @GET("type/{id}")
    Call<PokemonsFromType> getListOfPokemonFromType( @Path("id") int id);
}
