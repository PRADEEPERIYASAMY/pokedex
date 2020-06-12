package com.example.taskthree.Interface;

import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Types.PokemonTypeRoot;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfacePokemonRootTyres {
    @GET("type")
    Call<PokemonTypeRoot> getListOfPokemonRootTypeList( );
}
