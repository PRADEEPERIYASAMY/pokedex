package com.example.taskthree.Interface;

import com.example.taskthree.Generation.PokemonGeneration;
import com.example.taskthree.RegionsDetials.RegionsDetials;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfacePokemonGeneration {
    @GET("generation/{id}")
    Call<PokemonGeneration> getListOfPokemonFromGen( @Path("id") int id);
}
