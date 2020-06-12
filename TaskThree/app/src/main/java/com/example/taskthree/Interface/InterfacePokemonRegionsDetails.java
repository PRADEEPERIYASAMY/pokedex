package com.example.taskthree.Interface;

import com.example.taskthree.RegionsDetials.RegionsDetials;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfacePokemonRegionsDetails {
    @GET("region/{id}")
    Call<RegionsDetials> getPokemonRegionsDetails( @Path("id") int id);
}
