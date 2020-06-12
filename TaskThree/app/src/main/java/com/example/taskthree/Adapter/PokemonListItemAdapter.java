package com.example.taskthree.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskthree.Count.ResultsItem;
import com.example.taskthree.Images.PokemonImages;
import com.example.taskthree.Main4Activity;
import com.example.taskthree.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonListItemAdapter extends RecyclerView.Adapter<PokemonListItemAdapter.MyViewHolder> {

    Context context;
    List<ResultsItem> resultsItems;
    public static List<String> id;

    public PokemonListItemAdapter( Context context) {
        this.context = context;
        resultsItems = new ArrayList<> (  );
        id = new ArrayList<> (  );
    }

    @NonNull
    @Override
    public PokemonListItemAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.pokemon_item ,parent,false);
        return new MyViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonListItemAdapter.MyViewHolder holder, int position ) {


            holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );

            holder.pokemon_item_id.setText ( id.get ( position ));
            holder.pokemon_item_name.setText ( resultsItems.get ( Integer.parseInt ( id.get ( position ) )-1).getName () );
            if ((Integer.parseInt (   id.get ( position ) )) <= 9){
                Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+ id.get ( position ) +".png" ).into ( holder.pokemon_item_image );
            }
            else if ((Integer.parseInt (   id.get ( position ) )) <=99){
                Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+id.get ( position ) +".png" ).into ( holder.pokemon_item_image );
            }
            else {
                Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+id.get ( position ) +".png" ).into ( holder.pokemon_item_image );
            }

    }

    @Override
    public int getItemCount() {
        return id.size ();
    }

    public void allList( List<ResultsItem> results,List<String> ids ) {
        resultsItems.addAll ( results );
        id.addAll ( ids );
        notifyDataSetChanged ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView pokemon_item_image;
        TextView pokemon_item_name;
        TextView pokemon_item_id;
        MediaPlayer mediaPlayer;

        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            pokemon_item_image = itemView.findViewById ( R.id.pokemon_item_image );
            pokemon_item_id = itemView.findViewById ( R.id.pokemon_item_id );
            pokemon_item_name = itemView.findViewById ( R.id.pokemon_item_name );
            mediaPlayer = MediaPlayer.create ( context,R.raw.click );
            itemView.setOnClickListener ( this );
        }

        @Override
        public void onClick( View v ) {
            mediaPlayer.start ();
            Intent intent = new Intent ( context, Main4Activity.class );
            intent.putExtra ("ID",Integer.parseInt (id.get ( getAdapterPosition () ) ));
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation ( (Activity) context,pokemon_item_image, ViewCompat.getTransitionName ( pokemon_item_image));
            context.startActivity ( intent ,optionsCompat.toBundle ());
        }
    }

    public void pokemonClear(){
        resultsItems.clear ();
        id.clear ();
    }
}
