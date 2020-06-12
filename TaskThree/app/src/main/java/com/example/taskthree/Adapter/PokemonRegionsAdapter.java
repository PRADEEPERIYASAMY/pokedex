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
import com.example.taskthree.Regions.RootRegions;
import com.example.taskthree.subRegionsFragment;

public class PokemonRegionsAdapter extends RecyclerView.Adapter<PokemonRegionsAdapter.MyViewHolder> {

    Context context;
    RootRegions rootRegions;
    FragmentTransaction fragmentTransaction;
    FragmentTransaction fragmentTransaction1;
    FragmentTransaction fragmentTransaction4;

    public PokemonRegionsAdapter( Context context, RootRegions rootRegions, FragmentTransaction fragmentTransaction,FragmentTransaction fragmentTransaction1 ) {
        this.context = context;
        this.rootRegions = rootRegions;
        this.fragmentTransaction = fragmentTransaction;
        this.fragmentTransaction1 = fragmentTransaction1;
    }

    @NonNull
    @Override
    public PokemonRegionsAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.location_item ,parent,false);
        return new MyViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonRegionsAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.locationCount.setText ( String.valueOf ( position+1 ) );
        holder.location.setText ( rootRegions.getResults ().get ( position ).getName () );
    }

    @Override
    public int getItemCount() {
        return rootRegions.getResults ().size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView locationCount;
        TextView location;
        MediaPlayer mediaPlayer;

        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );

            locationCount = itemView.findViewById ( R.id.location_count );
            location = itemView.findViewById ( R.id.location_item );
            mediaPlayer = MediaPlayer.create ( context,R.raw.click );
            itemView.setOnClickListener ( this );

        }

        @Override
        public void onClick( View v ) {
            mediaPlayer.start ();
            fragmentTransaction.replace ( R.id.fragment_container,new subRegionsFragment ( getAdapterPosition ()+1,fragmentTransaction1 ) ).commit ();
        }
    }

}
