package com.example.taskthree.Models;

import java.util.List;

public class Pokemons {

        public List<Ability> abilities;
        public int base_experience;
        public List <FormsItem> forms;
        public List <Game_indicesItem> game_indices;
        public int height;
        public List <Object> held_items;
        public int id;
        public String is_default;
        public String location_area_encounters;
        public List <MovesItem> moves;
        public String name;
        public int order;
        public Species species;
        public Sprites sprites;
        public List <StatsItem> stats;
        public List <TypesItem> types;
        public int weight;

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities( List<Ability> abilities ) {
        this.abilities = abilities;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience( int base_experience ) {
        this.base_experience = base_experience;
    }

    public List<FormsItem> getForms() {
        return forms;
    }

    public void setForms( List<FormsItem> forms ) {
        this.forms = forms;
    }

    public List<Game_indicesItem> getGame_indices() {
        return game_indices;
    }

    public void setGame_indices( List<Game_indicesItem> game_indices ) {
        this.game_indices = game_indices;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public List<Object> getHeld_items() {
        return held_items;
    }

    public void setHeld_items( List<Object> held_items ) {
        this.held_items = held_items;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default( String is_default ) {
        this.is_default = is_default;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public void setLocation_area_encounters( String location_area_encounters ) {
        this.location_area_encounters = location_area_encounters;
    }

    public List<MovesItem> getMoves() {
        return moves;
    }

    public void setMoves( List<MovesItem> moves ) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder( int order ) {
        this.order = order;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies( Species species ) {
        this.species = species;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites( Sprites sprites ) {
        this.sprites = sprites;
    }

    public List<StatsItem> getStats() {
        return stats;
    }

    public void setStats( List<StatsItem> stats ) {
        this.stats = stats;
    }

    public List<TypesItem> getTypes() {
        return types;
    }

    public void setTypes( List<TypesItem> types ) {
        this.types = types;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight( int weight ) {
        this.weight = weight;
    }
}
