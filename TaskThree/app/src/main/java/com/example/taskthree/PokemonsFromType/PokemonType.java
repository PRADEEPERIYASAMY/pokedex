package com.example.taskthree.PokemonsFromType;

public class PokemonType {
    public PokemonSubFromType pokemon;
    public int slot;

    public PokemonSubFromType getPokemon() {
        return pokemon;
    }

    public void setPokemon( PokemonSubFromType pokemon ) {
        this.pokemon = pokemon;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot( int slot ) {
        this.slot = slot;
    }
}
