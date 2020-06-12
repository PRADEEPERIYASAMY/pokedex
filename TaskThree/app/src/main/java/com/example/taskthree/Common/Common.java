package com.example.taskthree.Common;

import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Count.ResultsItem;
import com.example.taskthree.Models.Pokemons;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static Pokemons pokemons;
    public static List<ResultsItem> resultsItemList = new ArrayList<> (  );

    public static Pokemons getPokemons() {
        return pokemons;
    }

    public static void setPokemons( Pokemons pokemons ) {
        Common.pokemons = pokemons;
    }

    public static List<ResultsItem> getResultsItemList() {
        return resultsItemList;
    }

    public static void setResultsItemList( List<ResultsItem> resultsItemList ) {
        Common.resultsItemList = resultsItemList;
    }
}
