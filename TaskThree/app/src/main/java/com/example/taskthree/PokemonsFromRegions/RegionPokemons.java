package com.example.taskthree.PokemonsFromRegions;

import java.util.List;

public class RegionPokemons {
    public List<Description> descriptions;
    public int id;
    public boolean is_main_series;
    public String name;
    public List<Name> names;
    public List<PokemonEntry> pokemon_entries;
    public Region region;
    public List<VersionGroup> version_groups;

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions( List<Description> descriptions ) {
        this.descriptions = descriptions;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public boolean isIs_main_series() {
        return is_main_series;
    }

    public void setIs_main_series( boolean is_main_series ) {
        this.is_main_series = is_main_series;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames( List<Name> names ) {
        this.names = names;
    }

    public List<PokemonEntry> getPokemon_entries() {
        return pokemon_entries;
    }

    public void setPokemon_entries( List<PokemonEntry> pokemon_entries ) {
        this.pokemon_entries = pokemon_entries;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion( Region region ) {
        this.region = region;
    }

    public List<VersionGroup> getVersion_groups() {
        return version_groups;
    }

    public void setVersion_groups( List<VersionGroup> version_groups ) {
        this.version_groups = version_groups;
    }
}
