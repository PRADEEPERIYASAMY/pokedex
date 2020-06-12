package com.example.taskthree.Generation;

import java.util.List;

public class PokemonGeneration {

    public List <AbilitiesItem> abilities;
    public int id;
    public Main_region main_region;
    public List <MovesItem> moves;
    public String name;
    public List <NamesItem> names;
    public List <Pokemon_speciesItem> pokemon_species;
    public List <TypesItem> types;
    public List <Version_groupsItem> version_groups;

    public List<AbilitiesItem> getAbilities() {
        return abilities;
    }

    public void setAbilities( List<AbilitiesItem> abilities ) {
        this.abilities = abilities;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Main_region getMain_region() {
        return main_region;
    }

    public void setMain_region( Main_region main_region ) {
        this.main_region = main_region;
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

    public List<NamesItem> getNames() {
        return names;
    }

    public void setNames( List<NamesItem> names ) {
        this.names = names;
    }

    public List<Pokemon_speciesItem> getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species( List<Pokemon_speciesItem> pokemon_species ) {
        this.pokemon_species = pokemon_species;
    }

    public List<TypesItem> getTypes() {
        return types;
    }

    public void setTypes( List<TypesItem> types ) {
        this.types = types;
    }

    public List<Version_groupsItem> getVersion_groups() {
        return version_groups;
    }

    public void setVersion_groups( List<Version_groupsItem> version_groups ) {
        this.version_groups = version_groups;
    }

    public class AbilitiesItem
    {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }

    public class Main_region
    {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }

    public class MovesItem
    {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }

    public class Language
    {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }

    public class NamesItem
    {
        public Language language;
        public String name;

        public Language getLanguage() {
            return language;
        }

        public void setLanguage( Language language ) {
            this.language = language;
        }

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }
    }

    public class Pokemon_speciesItem
    {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }

    public class TypesItem
    {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }

    public class Version_groupsItem
    {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }
    
    

}
