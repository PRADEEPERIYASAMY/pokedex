package com.example.taskthree.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskthree.Database.PokemonDatabase;
import com.example.taskthree.Main4Activity;
import com.example.taskthree.R;

public class StarredAdapter extends RecyclerView.Adapter<StarredAdapter.MyViewHolder> {

    Context context;

    public StarredAdapter( Context context ) {
        this.context = context;
    }

    @NonNull
    @Override
    public StarredAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.pokemon_item ,parent,false);
        return new MyViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder( @NonNull StarredAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        PokemonDatabase pokemonDatabase = new PokemonDatabase ( context );
        Cursor cursor = pokemonDatabase.FetchData ();
        if (cursor.getCount ()!= 0){
            cursor.moveToPosition ( position );
                holder.pokemon_item_id.setText ( cursor.getString ( 2 ) );
                holder.pokemon_item_name.setText ( cursor.getString ( 1 ) );
                if ((Integer.parseInt ( cursor.getString ( 2 ) )) <= 9){
                    Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+cursor.getString ( 2 )+".png" ).into ( holder.pokemon_item_image );
                }
                else if ((Integer.parseInt ( cursor.getString ( 2 ) )) <=99){
                    Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+cursor.getString ( 2 )+".png" ).into ( holder.pokemon_item_image );
                }
                else {
                    Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+cursor.getString ( 2 )+".png" ).into ( holder.pokemon_item_image );

            }
        }
    }

    @Override
    public int getItemCount() {
        PokemonDatabase pokemonDatabase = new PokemonDatabase ( context );
        Cursor cursor = pokemonDatabase.FetchData ();
        return cursor.getCount ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            Intent intent = new Intent ( context , Main4Activity.class );
            intent.putExtra ("ID",pokemon_item_id.getText ().toString ()  );
            context.startActivity ( intent );
        }
    }
}
