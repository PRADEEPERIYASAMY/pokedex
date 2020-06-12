package com.example.taskthree.PokemonsFromType;

import java.util.List;

public class PokemonsFromType {
    public Damage_relations damage_relations;
    public List<Game_indicesFromType> game_indices;
    public GenerationFromType generation;
    public int id;
    public Move_damage_class move_damage_class;
    public List<MovesFromType> moves;
    public String name;
    public List<NamesFromType> names;
    public List<PokemonType> pokemon;

    public Damage_relations getDamage_relations() {
        return damage_relations;
    }

    public void setDamage_relations( Damage_relations damage_relations ) {
        this.damage_relations = damage_relations;
    }

    public List<Game_indicesFromType> getGame_indices() {
        return game_indices;
    }

    public void setGame_indices( List<Game_indicesFromType> game_indices ) {
        this.game_indices = game_indices;
    }

    public GenerationFromType getGeneration() {
        return generation;
    }

    public void setGeneration( GenerationFromType generation ) {
        this.generation = generation;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Move_damage_class getMove_damage_class() {
        return move_damage_class;
    }

    public void setMove_damage_class( Move_damage_class move_damage_class ) {
        this.move_damage_class = move_damage_class;
    }

    public List<MovesFromType> getMoves() {
        return moves;
    }

    public void setMoves( List<MovesFromType> moves ) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<NamesFromType> getNames() {
        return names;
    }

    public void setNames( List<NamesFromType> names ) {
        this.names = names;
    }

    public List<PokemonType> getPokemon() {
        return pokemon;
    }

    public void setPokemon( List<PokemonType> pokemon ) {
        this.pokemon = pokemon;
    }
}
