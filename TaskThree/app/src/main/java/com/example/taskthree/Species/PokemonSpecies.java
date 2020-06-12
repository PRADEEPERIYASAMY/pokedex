package com.example.taskthree.Species;

import java.util.List;

public class PokemonSpecies {

    public int base_happiness;
    public int capture_rate;
    public Color color;
    public List<EggGroup> egg_groups;
    public EvolutionChain evolution_chain;
    public EvolvesFromSpecies evolves_from_species;
    public List<FlavorTextEntry> flavor_text_entries;
    public List<Object> form_descriptions;
    public boolean forms_switchable;
    public int gender_rate;
    public List<Genera> genera;
    public Generation generation;
    public GrowthRate growth_rate;
    public Habitat habitat;
    public boolean has_gender_differences;
    public int hatch_counter;
    public int id;
    public boolean is_baby;
    public String name;
    public List<Name> names;
    public int order;
    public List<PalParkEncounter> pal_park_encounters;
    public List<PokedexNumber> pokedex_numbers;
    public Shape shape;
    public List<Variety> varieties;


    public int getBase_happiness() {
        return base_happiness;
    }

    public void setBase_happiness( int base_happiness ) {
        this.base_happiness = base_happiness;
    }

    public int getCapture_rate() {
        return capture_rate;
    }

    public void setCapture_rate( int capture_rate ) {
        this.capture_rate = capture_rate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor( Color color ) {
        this.color = color;
    }

    public List<EggGroup> getEgg_groups() {
        return egg_groups;
    }

    public void setEgg_groups( List<EggGroup> egg_groups ) {
        this.egg_groups = egg_groups;
    }

    public EvolutionChain getEvolution_chain() {
        return evolution_chain;
    }

    public void setEvolution_chain( EvolutionChain evolution_chain ) {
        this.evolution_chain = evolution_chain;
    }

    public EvolvesFromSpecies getEvolves_from_species() {
        return evolves_from_species;
    }

    public void setEvolves_from_species( EvolvesFromSpecies evolves_from_species ) {
        this.evolves_from_species = evolves_from_species;
    }

    public List<FlavorTextEntry> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries( List<FlavorTextEntry> flavor_text_entries ) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public List<Object> getForm_descriptions() {
        return form_descriptions;
    }

    public void setForm_descriptions( List<Object> form_descriptions ) {
        this.form_descriptions = form_descriptions;
    }

    public boolean isForms_switchable() {
        return forms_switchable;
    }

    public void setForms_switchable( boolean forms_switchable ) {
        this.forms_switchable = forms_switchable;
    }

    public int getGender_rate() {
        return gender_rate;
    }

    public void setGender_rate( int gender_rate ) {
        this.gender_rate = gender_rate;
    }

    public List<Genera> getGenera() {
        return genera;
    }

    public void setGenera( List<Genera> genera ) {
        this.genera = genera;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration( Generation generation ) {
        this.generation = generation;
    }

    public GrowthRate getGrowth_rate() {
        return growth_rate;
    }

    public void setGrowth_rate( GrowthRate growth_rate ) {
        this.growth_rate = growth_rate;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat( Habitat habitat ) {
        this.habitat = habitat;
    }

    public boolean isHas_gender_differences() {
        return has_gender_differences;
    }

    public void setHas_gender_differences( boolean has_gender_differences ) {
        this.has_gender_differences = has_gender_differences;
    }

    public int getHatch_counter() {
        return hatch_counter;
    }

    public void setHatch_counter( int hatch_counter ) {
        this.hatch_counter = hatch_counter;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public boolean isIs_baby() {
        return is_baby;
    }

    public void setIs_baby( boolean is_baby ) {
        this.is_baby = is_baby;
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

    public int getOrder() {
        return order;
    }

    public void setOrder( int order ) {
        this.order = order;
    }

    public List<PalParkEncounter> getPal_park_encounters() {
        return pal_park_encounters;
    }

    public void setPal_park_encounters( List<PalParkEncounter> pal_park_encounters ) {
        this.pal_park_encounters = pal_park_encounters;
    }

    public List<PokedexNumber> getPokedex_numbers() {
        return pokedex_numbers;
    }

    public void setPokedex_numbers( List<PokedexNumber> pokedex_numbers ) {
        this.pokedex_numbers = pokedex_numbers;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape( Shape shape ) {
        this.shape = shape;
    }

    public List<Variety> getVarieties() {
        return varieties;
    }

    public void setVarieties( List<Variety> varieties ) {
        this.varieties = varieties;
    }

    public class Color
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

    public class EggGroup
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

    public class EvolutionChain
    {
        public String url;

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
    public class Languagetwo
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

    public class Version
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

    public class FlavorTextEntry
    {
        public String flavor_text;
        public Language language;
        public Version version;

        public String getFlavor_text() {
            return flavor_text;
        }

        public void setFlavor_text( String flavor_text ) {
            this.flavor_text = flavor_text;
        }

        public Language getLanguage() {
            return language;
        }

        public void setLanguage( Language language ) {
            this.language = language;
        }

        public Version getVersion() {
            return version;
        }

        public void setVersion( Version version ) {
            this.version = version;
        }
    }

    public class Genera
    {
        public String genus;
        public Language language;

        public String getGenus() {
            return genus;
        }

        public void setGenus( String genus ) {
            this.genus = genus;
        }

        public Language getLanguage() {
            return language;
        }

        public void setLanguage( Language language ) {
            this.language = language;
        }
    }

    public class Generation
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

    public class GrowthRate
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

    public class Habitat
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

    public class Name
    {
        public Languagetwo  language;
        public String name;

        public Languagetwo getLanguage() {
            return language;
        }

        public void setLanguage( Languagetwo language ) {
            this.language = language;
        }

        public String getName() {
            return name;
        }

        public void setName( String name ) {
            this.name = name;
        }
    }

    public class Area
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

    public class PalParkEncounter
    {
        public Area area;
        public int base_score;
        public int rate;

        public Area getArea() {
            return area;
        }

        public void setArea( Area area ) {
            this.area = area;
        }

        public int getBase_score() {
            return base_score;
        }

        public void setBase_score( int base_score ) {
            this.base_score = base_score;
        }

        public int getRate() {
            return rate;
        }

        public void setRate( int rate ) {
            this.rate = rate;
        }
    }

    public class Pokedex
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

    public class PokedexNumber
    {
        public int entry_number;
        public Pokedex pokedex;

        public int getEntry_number() {
            return entry_number;
        }

        public void setEntry_number( int entry_number ) {
            this.entry_number = entry_number;
        }

        public Pokedex getPokedex() {
            return pokedex;
        }

        public void setPokedex( Pokedex pokedex ) {
            this.pokedex = pokedex;
        }
    }

    public class Shape
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

    public class Pokemon
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

    public class Variety
    {
        public boolean is_default;
        public Pokemon pokemon;

        public boolean isIs_default() {
            return is_default;
        }

        public void setIs_default( boolean is_default ) {
            this.is_default = is_default;
        }

        public Pokemon getPokemon() {
            return pokemon;
        }

        public void setPokemon( Pokemon pokemon ) {
            this.pokemon = pokemon;
        }
    }

    public class EvolvesFromSpecies
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
