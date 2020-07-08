package com.example.taskthree;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taskthree.Images.PokemonImages;
import com.example.taskthree.Models.Pokemons;
import com.example.taskthree.Models.StatsItem;
import com.example.taskthree.Species.PokemonSpecies;

import java.util.List;

import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    ImageView secondPokemonImage;
    Retrofit retrofit;
    TextView name;
    TextView weight;
    TextView height;
    TextView id;
    TextView description;
    RelativeLayout hp;
    RelativeLayout attack;
    RelativeLayout defense;
    RelativeLayout specialAttack;
    RelativeLayout specialDefense;
    RelativeLayout speed;
    RelativeLayout total;
    TextView hpValue;
    TextView attackValue;
    TextView defenseValue;
    TextView specialAttackValue;
    TextView specialDefenseValue;
    TextView speedValue;
    TextView totalValue;
    TextView captureRate;
    TextView happiness;
    TextView habitat;
    TextView color;
    TextView shape;
    TextView genderDifference;
    TextView growthRate;
    TextView hatchCounter;
    TextView genderRate;

    int Id;
    static List<StatsItem> stat;
    static Integer values;
    static Pokemons pokemons;

    ImageView spriteOne;
    ImageView spriteTwo;
    ImageView spriteThree;
    ImageView spriteFour;

    Button download;

    PokemonSpecies pokemonSpecies;
    public static String string = "";
    static FragmentTransaction fragmentTransaction;


    public DataFragment( int id, Pokemons pokemons, PokemonSpecies pokemonSpecies, FragmentTransaction fragmentTransaction ) {
        Id = id;
        this.pokemons = pokemons;
        this.pokemonSpecies = pokemonSpecies;
        this.fragmentTransaction = fragmentTransaction;
    }

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_data, container, false );

        secondPokemonImage = view.findViewById ( R.id.second_pokemon_image );

        name = view.findViewById ( R.id.pokemon_data_name );
        height = view.findViewById ( R.id.pokemon_data_height );
        weight = view.findViewById ( R.id.pokemon_data_weight );
        id = view.findViewById ( R.id.pokemon_data_id );
        description = view.findViewById ( R.id.pokemon_data_description );
        hp = view.findViewById ( R.id.pokemon_data_hp );
        attack = view.findViewById ( R.id.pokemon_data_attack );
        defense = view.findViewById ( R.id.pokemon_data_defense );
        specialAttack = view.findViewById ( R.id.pokemon_data_special_attack );
        specialDefense = view.findViewById ( R.id.pokemon_data_special_defense );
        speed = view.findViewById ( R.id.pokemon_data_speed );
        total = view.findViewById ( R.id.pokemon_data_total );
        hpValue = view.findViewById ( R.id.pokemon_data_hp_value );
        attackValue = view.findViewById ( R.id.pokemon_data_attack_value );
        defenseValue = view.findViewById ( R.id.pokemon_data_defense_value );
        specialAttackValue = view.findViewById ( R.id.pokemon_data_special_attack_value );
        specialDefenseValue = view.findViewById ( R.id.pokemon_data_special_defense_value );
        speedValue = view.findViewById ( R.id.pokemon_data_speed_value );
        totalValue = view.findViewById ( R.id.pokemon_data_total_value );
        spriteOne = view.findViewById ( R.id.sprite_image );
        spriteTwo = view.findViewById ( R.id.sprite_image_two );
        spriteThree = view.findViewById ( R.id.sprite_image_three );
        spriteFour = view.findViewById ( R.id.sprite_image_four );

        captureRate = view.findViewById ( R.id.pokemon_data_capture_rate );
        happiness = view.findViewById ( R.id.pokemon_data_baseHappiness );
        habitat = view.findViewById ( R.id.pokemon_data_habitat );
        color = view.findViewById ( R.id.pokemon_data_color );
        shape = view.findViewById ( R.id.pokemon_data_shape );
        genderDifference = view.findViewById ( R.id.pokemon_data_gender_difference );
        growthRate = view.findViewById ( R.id.pokemon_data_growth_rate );
        hatchCounter = view.findViewById ( R.id.pokemon_data_hatch_counter );
        genderRate = view.findViewById ( R.id.pokemon_data_gender_rate );

        download = view.findViewById ( R.id.download );

        download.setOnClickListener ( new View.OnClickListener () {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick( View v ) {
                String url = new PokemonImages ( Id-1 ).getImagesSelected ();

                setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition( R.transition.transition));
                setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.transition));

                Fragment fragment = new FragmentImageDownload ("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+ url.split ( "/" )[8]);
                fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.transition));
                fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.transition));
                fragmentTransaction.replace(R.id.second_container, fragment).addSharedElement(secondPokemonImage, "image_transaction");
                fragmentTransaction.commit();

            }
        } );

        values = 0;

        try {
            if (pokemonSpecies.getFlavor_text_entries () != null &&pokemonSpecies.getFlavor_text_entries ().size ()>0 && pokemonSpecies.getFlavor_text_entries ().get ( 0 ).getLanguage ().getName ().contains ( "en" )){
                description.setText ( pokemonSpecies.getFlavor_text_entries ().get ( 0 ).getFlavor_text () );
            }
            else {
                description.setText ( "Null");
            }

            name.setText ( pokemons.getName () );
            height.setText ( String.valueOf ( pokemons.getHeight () ) );
            weight.setText ( String.valueOf ( pokemons.getWeight () ) );
            id.setText ( String.valueOf ( Id ) );

            stat = pokemons.getStats ();

            for (int i = 0; i<6;i++){
                if(values < stat.get ( i ).getBase_stat () ){
                    values = stat.get ( i ).getBase_stat ();
                }
            }

            hpValue.setText ( String.valueOf ( stat.get ( 5 ).getBase_stat () ) );
            attackValue.setText ( String.valueOf ( stat.get ( 4 ).getBase_stat () ) );
            defenseValue.setText ( String.valueOf ( stat.get ( 3 ).getBase_stat () ) );
            specialAttackValue.setText ( String.valueOf ( stat.get ( 2 ).getBase_stat () ) );
            specialDefenseValue.setText ( String.valueOf ( stat.get ( 1 ).getBase_stat () ) );
            speedValue.setText ( String.valueOf ( stat.get ( 0 ).getBase_stat () ) );
            totalValue.setText ( String.valueOf ( stat.get ( 5 ).getBase_stat ()+stat.get ( 4 ).getBase_stat ()+stat.get ( 3 ).getBase_stat ()+stat.get ( 2 ).getBase_stat ()+stat.get ( 1 ).getBase_stat ()+stat.get ( 0 ).getBase_stat () ) );

            hp.getLayoutParams ().width = (hp.getLayoutParams ().width/255)* Integer.parseInt ( hpValue.getText ().toString () );
            attack.getLayoutParams ().width = (attack.getLayoutParams ().width/190)* Integer.parseInt ( attackValue.getText ().toString () );
            defense.getLayoutParams ().width = (defense.getLayoutParams ().width/250)* Integer.parseInt ( defenseValue.getText ().toString () );
            specialAttack.getLayoutParams ().width = (specialAttack.getLayoutParams ().width/194)* Integer.parseInt ( specialAttackValue.getText ().toString () );
            specialDefense.getLayoutParams ().width = (specialDefense.getLayoutParams ().width/250)* Integer.parseInt ( specialDefenseValue.getText ().toString () );
            speed.getLayoutParams ().width = (speed.getLayoutParams ().width/180)* Integer.parseInt ( speedValue.getText ().toString () );
            total.getLayoutParams ().width = (total.getLayoutParams ().width/130)* (Integer.parseInt ( totalValue.getText ().toString () )/6);

            Glide.with ( getActivity () ).load ( new PokemonImages ( Id-1 ).getImagesSelected () ).into ( secondPokemonImage );


            Glide.with ( getActivity () ).load ( "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/"+String.valueOf ( Id )+".png" ).into ( spriteOne );
            Glide.with ( getActivity () ).load ( "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/"+String.valueOf (Id)+".png" ).into ( spriteTwo );
            Glide.with ( getActivity () ).load ( "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+String.valueOf ( Id )+".png" ).into ( spriteThree );
            Glide.with ( getActivity () ).load ( "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"+String.valueOf ( Id )+".png" ).into ( spriteFour );

            if (pokemonSpecies.getHabitat () != null){
                habitat.setText ( String.valueOf ( pokemonSpecies.getHabitat ().getName () ) );
            }
            else {
                habitat.setText ( "null" );
            }

            captureRate.setText ( String.valueOf ( pokemonSpecies.getCapture_rate () ) );
            happiness.setText ( String.valueOf ( pokemonSpecies.getBase_happiness () ) );
            growthRate.setText ( String.valueOf ( pokemonSpecies.getGrowth_rate ().getName () ) );
            color.setText ( String.valueOf ( pokemonSpecies.getColor ().getName () ) );
            shape.setText ( String.valueOf ( pokemonSpecies.getShape ().getName () ) );
            genderDifference.setText ( String.valueOf ( pokemonSpecies.has_gender_differences ) );
            hatchCounter.setText ( String.valueOf ( pokemonSpecies.getHatch_counter () ) );
            genderRate.setText ( String.valueOf ( pokemonSpecies.getGender_rate () ) );
        }
        catch (NullPointerException e){
            e.printStackTrace ();
        }

        return view;
    }
}
