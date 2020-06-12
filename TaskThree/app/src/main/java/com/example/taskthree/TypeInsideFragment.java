package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taskthree.Adapter.PokemonAbilityAdapter;
import com.example.taskthree.Adapter.PokemonInnerTypeAdapter;
import com.example.taskthree.Adapter.PokemonMovesAdapter;
import com.example.taskthree.Models.Pokemons;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeInsideFragment extends Fragment {

    int Id;
    static Pokemons pokemons;
    RecyclerView pokemon_type_recyclerview;
    RecyclerView recyclerView;
    TextView baseExperience;

    public TypeInsideFragment( int id, Pokemons pokemons ) {
        Id = id;
        this.pokemons = pokemons;
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_type_inside, container, false );

        recyclerView = view.findViewById ( R.id.recycler_inner_type_abilities );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity () ) );
        PokemonAbilityAdapter pokemonAbilityAdapter = new PokemonAbilityAdapter ( getActivity (),pokemons );
        recyclerView.setAdapter ( pokemonAbilityAdapter );

        pokemon_type_recyclerview = view.findViewById ( R.id.recycler_inner_type );
        pokemon_type_recyclerview.setHasFixedSize ( true );
        pokemon_type_recyclerview.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );
        PokemonInnerTypeAdapter pokemonInnerTypeAdapter = new PokemonInnerTypeAdapter ( getActivity (), pokemons );
        pokemon_type_recyclerview.setAdapter ( pokemonInnerTypeAdapter );

        baseExperience = view.findViewById ( R.id.base_experience );
        baseExperience.setText ( String.valueOf ( pokemons.getBase_experience () ) );

        return view;
    }
}
