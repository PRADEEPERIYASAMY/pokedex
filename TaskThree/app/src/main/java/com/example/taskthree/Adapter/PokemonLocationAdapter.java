package com.example.taskthree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskthree.Location.ResultLocation;
import com.example.taskthree.Location.RootLocation;
import com.example.taskthree.R;

import java.util.List;

public class PokemonLocationAdapter extends RecyclerView.Adapter<PokemonLocationAdapter.MyViewHolder> {

    Context context;
    List<ResultLocation> resultLocations;

    public PokemonLocationAdapter( Context context, List<ResultLocation> resultLocations ) {
        this.context = context;
        this.resultLocations = resultLocations;
    }

    @NonNull
    @Override
    public PokemonLocationAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.location_item ,parent,false);
        return new MyViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonLocationAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.locationCount.setText ( resultLocations.get ( position ).getUrl ().split ( "/" )[6]  );
        holder.location.setText ( resultLocations.get ( position ).getName () );
    }

    @Override
    public int getItemCount() {
        return resultLocations.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView locationCount;
        TextView location;

        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );

            locationCount = itemView.findViewById ( R.id.location_count );
            location = itemView.findViewById ( R.id.location_item );

        }
    }
}
