package com.example.taskthree.PokemonsFromRegions;

public class PokemonEntry {
    public int entry_number;
    public PokemonSpecies pokemon_species;

    public int getEntry_number() {
        return entry_number;
    }

    public void setEntry_number( int entry_number ) {
        this.entry_number = entry_number;
    }

    public PokemonSpecies getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species( PokemonSpecies pokemon_species ) {
        this.pokemon_species = pokemon_species;
    }
}
