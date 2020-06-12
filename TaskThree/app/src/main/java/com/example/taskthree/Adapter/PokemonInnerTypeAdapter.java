package com.example.taskthree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskthree.Models.Pokemons;
import com.example.taskthree.R;

public class PokemonInnerTypeAdapter extends RecyclerView.Adapter<PokemonInnerTypeAdapter.MyViewHolder> {

    Context context;
    Pokemons pokemons;

    public PokemonInnerTypeAdapter( Context context, Pokemons pokemons ) {
        this.context = context;
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonInnerTypeAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.moves_item ,parent,false);
        return new MyViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonInnerTypeAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.moves.setText ( pokemons.getTypes ().get ( position ).getType ().getName () );
    }

    @Override
    public int getItemCount() {
        return pokemons.getTypes ().size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView moves;
        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            moves = itemView.findViewById ( R.id.moves );
        }
    }
}
