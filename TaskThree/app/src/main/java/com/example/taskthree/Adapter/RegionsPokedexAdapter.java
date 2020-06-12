package com.example.taskthree.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskthree.R;
import com.example.taskthree.RegionsDetials.RegionsDetials;
import com.example.taskthree.SubPokemonFromRegions;

public class RegionsPokedexAdapter extends RecyclerView.Adapter<RegionsPokedexAdapter.MyViewHolder> {

    Context context;
    RegionsDetials regionsDetials;
    FragmentTransaction fragmentTransaction;

    public RegionsPokedexAdapter( Context context, RegionsDetials regionsDetials , FragmentTransaction fragmentTransaction ) {
        this.context = context;
        this.regionsDetials = regionsDetials;
        this.fragmentTransaction = fragmentTransaction;
    }

    @NonNull
    @Override
    public RegionsPokedexAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.regions_pokedex_item,parent,false);
        return new  MyViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder( @NonNull RegionsPokedexAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.regionsPokedexCount.setText ( String.valueOf ( position+1 ) );
        holder.regionsPokedexName.setText ( String.valueOf ( regionsDetials.getPokedexes ().get ( position ).getName () ) );
    }

    @Override
    public int getItemCount() {
        return regionsDetials.getPokedexes ().size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView regionsPokedexCount;
        TextView regionsPokedexName;
        MediaPlayer mediaPlayer;
        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            regionsPokedexCount = itemView.findViewById ( R.id.region_pokedex_count );
            regionsPokedexName = itemView.findViewById ( R.id.region_pokedex_name );
            mediaPlayer = MediaPlayer.create ( context,R.raw.click );
            itemView.setOnClickListener ( this );
        }

        @Override
        public void onClick( View v ) {
            mediaPlayer.start ();
            fragmentTransaction.replace ( R.id.fragment_container,new SubPokemonFromRegions ( regionsPokedexName.getText ().toString () ) ).commit ();
        }
    }
}
