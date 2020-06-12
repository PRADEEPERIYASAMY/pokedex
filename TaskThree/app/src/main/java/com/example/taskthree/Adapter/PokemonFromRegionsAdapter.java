package com.example.taskthree.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Interface.InterfacePokemonCount;
import com.example.taskthree.Interface.InterfaceWithoutPagination;
import com.example.taskthree.Main4Activity;
import com.example.taskthree.PokemonsFromRegions.RegionPokemons;
import com.example.taskthree.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonFromRegionsAdapter extends RecyclerView.Adapter<PokemonFromRegionsAdapter.MyViewHolder> {

    Context context;
    RegionPokemons regionPokemons;
    Retrofit retrofit2;
    public static List<String> idList;

    public PokemonFromRegionsAdapter( Context context, final RegionPokemons regionPokemons,List<String> idList ) {
        this.context = context;
        this.regionPokemons = regionPokemons;
        this.idList = idList;

    }

    @NonNull
    @Override
    public PokemonFromRegionsAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        View view = LayoutInflater.from ( context ).inflate ( R.layout.pokemonfromregions_item,parent,false );

        return new MyViewHolder ( view );
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonFromRegionsAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
       if (idList != null){
           holder.pokemon_name.setText ( regionPokemons.getPokemon_entries ().get ( position ).getPokemon_species ().getName () );
           holder.pokemon_id.setText ( idList.get ( position ) );
           if ((Integer.parseInt ( idList.get ( position ) )) <= 9){
               Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+idList.get ( position )+".png" ).into ( holder.pokemon_image );
           }
           else if ((Integer.parseInt ( idList.get ( position ) )) <=99){
               Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+idList.get ( position )+".png" ).into ( holder.pokemon_image );
           }
           else {
               Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+idList.get ( position )+".png" ).into ( holder.pokemon_image);
           }
       }
    }

    @Override
    public int getItemCount() {
        if (regionPokemons != null){
            return regionPokemons.getPokemon_entries ().size ();
        }
        else {
            return 0 ;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pokemon_name;
        ImageView pokemon_image;
        TextView pokemon_id;
        MediaPlayer mediaPlayer;

        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            pokemon_name = itemView.findViewById ( R.id.pokemon_from_region_name );
            pokemon_id = itemView.findViewById ( R.id.pokemon_from_region_id );
            pokemon_image = itemView.findViewById ( R.id.pokemon_from_region_image );
            mediaPlayer = MediaPlayer.create ( context,R.raw.click );

            itemView.setOnClickListener ( this );
        }

        @Override
        public void onClick( View v ) {
            mediaPlayer.start ();
            Intent intent = new Intent ( context, Main4Activity.class );
            intent.putExtra ("ID",Integer.parseInt ( idList.get ( getAdapterPosition () ) ) );
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity ( intent );
        }
    }
}
