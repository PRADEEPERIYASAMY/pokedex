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

public class PokemonAbilityAdapter extends RecyclerView.Adapter<PokemonAbilityAdapter.MyViewHolder> {

    Context context;
    Pokemons pokemons;

    public PokemonAbilityAdapter( Context context, Pokemons pokemons ) {
        this.context = context;
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonAbilityAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.ability_item ,parent,false);
        return new MyViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonAbilityAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.abilities.setText ( pokemons.getAbilities ().get ( position ).getAbility ().getName () );
    }

    @Override
    public int getItemCount() {
        return pokemons.getAbilities ().size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView abilities;
        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            abilities = itemView.findViewById ( R.id.abilities );
        }
    }
}
