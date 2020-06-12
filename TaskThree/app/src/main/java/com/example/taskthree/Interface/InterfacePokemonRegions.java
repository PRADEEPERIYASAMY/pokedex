package com.example.taskthree.Interface;

import com.example.taskthree.Location.RootLocation;
import com.example.taskthree.Regions.RootRegions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfacePokemonRegions {
    @GET("region")
    Call<RootRegions> getPokemonRegionList( );
}
