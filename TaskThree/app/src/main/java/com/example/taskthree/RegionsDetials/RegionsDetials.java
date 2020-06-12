package com.example.taskthree.RegionsDetials;

import java.util.List;

public class RegionsDetials {
    public int id;
    public List<LocationsRegionsDetails> locations;
    public Main_generationRegionsDetails main_generation;
    public String name;
    public List<NamesRegionsDetails> names;
    public List<PokedexesRegionsDetails> pokedexes;
    public List<Version_groupsRegionsDEtails> version_groups;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public List<LocationsRegionsDetails> getLocations() {
        return locations;
    }

    public void setLocations( List<LocationsRegionsDetails> locations ) {
        this.locations = locations;
    }

    public Main_generationRegionsDetails getMain_generation() {
        return main_generation;
    }

    public void setMain_generation( Main_generationRegionsDetails main_generation ) {
        this.main_generation = main_generation;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<NamesRegionsDetails> getNames() {
        return names;
    }

    public void setNames( List<NamesRegionsDetails> names ) {
        this.names = names;
    }

    public List<PokedexesRegionsDetails> getPokedexes() {
        return pokedexes;
    }

    public void setPokedexes( List<PokedexesRegionsDetails> pokedexes ) {
        this.pokedexes = pokedexes;
    }

    public List<Version_groupsRegionsDEtails> getVersion_groups() {
        return version_groups;
    }

    public void setVersion_groups( List<Version_groupsRegionsDEtails> version_groups ) {
        this.version_groups = version_groups;
    }
}
