package com.example.taskthree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskthree.Adapter.PokemonListItemAdapter;
import com.example.taskthree.Adapter.PokemonMovesAdapter;
import com.example.taskthree.Common.Common;
import com.example.taskthree.Models.Pokemons;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovesFragment extends Fragment {

    int Id;
    RecyclerView pokemon_moves_recyclerview;
    PokemonMovesAdapter pokemonMovesAdapter;
    Pokemons pokemons;

    public MovesFragment( int id ,Pokemons pokemons ) {
        Id = id;
        this.pokemons = pokemons;
    }
    

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_moves, container, false );
        pokemon_moves_recyclerview = view.findViewById ( R.id.recycler_moves_item );
        pokemon_moves_recyclerview.setHasFixedSize ( true );
        pokemon_moves_recyclerview.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );
        pokemonMovesAdapter = new PokemonMovesAdapter ( getActivity (),pokemons  );
        pokemon_moves_recyclerview.setAdapter ( pokemonMovesAdapter );
        return view;
    }
}
