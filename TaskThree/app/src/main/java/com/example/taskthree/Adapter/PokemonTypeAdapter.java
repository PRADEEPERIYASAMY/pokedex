package com.example.taskthree.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskthree.R;
import com.example.taskthree.SubTypeFragment;
import com.example.taskthree.Types.PokemonTypeRoot;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder> {

    Context context;
    PokemonTypeRoot pokemonTypeRoot;
    FragmentTransaction fragmentTransaction;

    public PokemonTypeAdapter( Context context, PokemonTypeRoot pokemonTypeRoot,FragmentTransaction fragmentTransaction) {
        this.context = context;
        this.pokemonTypeRoot = pokemonTypeRoot;
        this.fragmentTransaction = fragmentTransaction;
    }

    @NonNull
    @Override
    public PokemonTypeAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.pokemon_type_item ,parent,false);
        return new MyViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull PokemonTypeAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.textView.setText ( pokemonTypeRoot.getResults ().get ( position ).getName () );
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "normal" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/e/e6/NormalIC_Masters.png/64px-NormalIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "fighting" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/0/06/FightingIC_Masters.png/64px-FightingIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "flying" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/2/2f/FlyingIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "poison" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/f/f3/PoisonIC_Masters.png/64px-PoisonIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "ground" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/7/74/GroundIC_Masters.png/64px-GroundIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "rock" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/9/9e/RockIC_Masters.png/64px-RockIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "bug" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/8/82/BugIC_Masters.png/64px-BugIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "ghost" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/6/68/GhostIC_Masters.png/64px-GhostIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "steel" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/3/39/SteelIC_Masters.png/64px-SteelIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "fire" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/2/2e/FireIC_Masters.png/64px-FireIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "water" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/3/3f/WaterIC_Masters.png/64px-WaterIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "grass" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/3/32/GrassIC_Masters.png/64px-GrassIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "electric" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/1/1a/ElectricIC_Masters.png/64px-ElectricIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "psychic" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/9/99/PsychicIC_Masters.png/64px-PsychicIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "ice" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/9/9b/IceIC_Masters.png/64px-IceIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "dragon" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/d/d7/DragonIC_Masters.png/64px-DragonIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "dark" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/4/43/DarkIC_Masters.png/64px-DarkIC_Masters.png" ).into ( holder.imageView );
        }
        if (pokemonTypeRoot.getResults ().get ( position ).getName ().equals ( "fairy" )){
            Glide.with ( context ).load ( "https://cdn.bulbagarden.net/upload/thumb/f/fa/FairyIC_Masters.png/64px-FairyIC_Masters.png" ).into ( holder.imageView );
        }

        switch (pokemonTypeRoot.getResults ().get ( position ).getName ()){
            case "normal":

            case "fighting":

            case "flying":

            case "poison":

            case "ground":

            case "rock":

            case "bug":
                  case "ghost":

            case "steel":

            case "fire":

            case "water":

            case "grass":

            case "electric":

            case "psychic":

            case "ice":

            case "dragon":

            case "dark":

            case "fairy":

        }
    }

    @Override
    public int getItemCount() {
        return pokemonTypeRoot.getResults ().size ()-2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView imageView;
        MediaPlayer mediaPlayer;
        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            textView = itemView.findViewById ( R.id.type_item );
            imageView = itemView.findViewById ( R.id.type_item_image );
            mediaPlayer = MediaPlayer.create ( context,R.raw.click );
            itemView.setOnClickListener ( this );
        }

        @Override
        public void onClick( View v ) {
            mediaPlayer.start ();
            fragmentTransaction.replace ( R.id.fragment_container,new SubTypeFragment ( getAdapterPosition ()+1 ) ).commit ();
        }
    }
}
