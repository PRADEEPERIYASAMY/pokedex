package com.example.taskthree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskthree.Items.PokemonRootItem;
import com.example.taskthree.Items.ResultsOfItem;
import com.example.taskthree.R;

import java.util.List;

public class PokemonItemAdapter extends RecyclerView.Adapter<PokemonItemAdapter.MyViewHolder> {

    Context context;
    List<ResultsOfItem> resultsOfItem;

    public PokemonItemAdapter( Context context, List<ResultsOfItem> resultsOfItem ) {
        this.context = context;
        this.resultsOfItem = resultsOfItem;
    }

    @NonNull
    @Override
    public PokemonItemAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.items_item ,parent,false);
        return new MyViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonItemAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.item.setText ( resultsOfItem.get ( position ).getName () );
        holder.count.setText ( resultsOfItem.get ( position ).getUrl ().split ( "/" )[6] );
        Glide.with ( context ).load ("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/"+resultsOfItem.get ( position).getName ()+".png" ).into ( holder.itemImg );
    }

    @Override
    public int getItemCount() {
        return resultsOfItem.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        ImageView itemImg;
        TextView count;
        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            item = itemView.findViewById ( R.id.item_item );
            itemImg = itemView.findViewById ( R.id.item_image );
            count = itemView.findViewById ( R.id.item_count );
        }
    }
}
