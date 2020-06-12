package com.example.taskthree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskthree.Models.Pokemons;
import com.example.taskthree.R;

public class PokemonMovesAdapter extends RecyclerView.Adapter<PokemonMovesAdapter.MyViewHolder> {

    Context context;
    Pokemons pokemons;

    public PokemonMovesAdapter( Context context, Pokemons pokemons ) {
        this.context = context;
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonMovesAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.moves_item ,parent,false);
        return new  MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonMovesAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.moves.setText ( pokemons.getMoves ().get ( position ).getMove ().getName () );
    }

    @Override
    public int getItemCount() {
        return pokemons.getMoves ().size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView moves;

        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            moves = itemView.findViewById ( R.id.moves );
        }
    }
}
