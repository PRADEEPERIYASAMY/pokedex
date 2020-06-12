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
import com.example.taskthree.Count.ResultsItem;
import com.example.taskthree.Interface.InterfacePokemonCount;
import com.example.taskthree.Main4Activity;
import com.example.taskthree.PokemonsFromType.PokemonsFromType;
import com.example.taskthree.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypePokemonsAdapter extends RecyclerView.Adapter<TypePokemonsAdapter.MyViewHolder> {

    Context context;
    PokemonsFromType pokemonsFromType;
    Retrofit retrofit2;
    static int Id;
    List<String> id= new ArrayList<> (  );

    public TypePokemonsAdapter( Context context, PokemonsFromType pokemonsFromType ,List<String> id) {
        this.context = context;
        this.pokemonsFromType = pokemonsFromType;
        this.id = id;
    }

    @NonNull
    @Override
    public TypePokemonsAdapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from ( context ).inflate ( R.layout.pokemons_from_type ,parent,false);
        return new MyViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull TypePokemonsAdapter.MyViewHolder holder, int position ) {
        holder.itemView.setAnimation ( AnimationUtils.loadAnimation ( context,R.anim.recycler ) );
        holder.count.setText ( id.get ( position ) );
        holder.name.setText ( pokemonsFromType.getPokemon ().get ( position ).getPokemon ().getName ());
        if ((Integer.parseInt ( id.get ( position ) ) ) <= 9){
            Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+ id.get ( position ) +".png" ).into ( holder.imageView );
        }
        else if ((Integer.parseInt ( id.get ( position ))) <=99){
            Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+ id.get ( position ) +".png" ).into ( holder.imageView);
        }
        else {
            Glide.with ( context ).load ("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+ id.get ( position ) +".png" ).into ( holder.imageView );
        }
    }

    @Override
    public int getItemCount() {
        return id.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView count;
        TextView name;
        ImageView imageView;
        MediaPlayer mediaPlayer;
        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            count = itemView.findViewById ( R.id.moves_from_type_count );
            name = itemView.findViewById ( R.id.moves_from_type_name );
            imageView = itemView.findViewById ( R.id.pokemon_from_type_image );
            mediaPlayer = MediaPlayer.create ( context,R.raw.click );
            itemView.setOnClickListener ( this );
        }

        @Override
        public void onClick( View v ) {
            final String string = "pikachu";

            mediaPlayer.start ();

            retrofit2 = new Retrofit.Builder ()
                    .baseUrl ( "https://pokeapi.co/api/v2/" )
                    .addConverterFactory ( GsonConverterFactory.create () )
                    .build ();
            InterfacePokemonCount service = retrofit2.create ( InterfacePokemonCount.class );
            Call<PokemonsCount> pokemonCountCall = service.getListOfPokemonCount (807,0);
            pokemonCountCall.enqueue ( new Callback<PokemonsCount> () {
                @Override
                public void onResponse( Call<PokemonsCount> call, Response<PokemonsCount> response ) {
                    if(!response.isSuccessful ()){

                        return;
                    }


                    for (int i = 1;i<=807;i++){
                        if (name.getText ().toString ().equals ( response.body ().getResults ().get ( i-1 ).getName () )){
                            Id = i;
                        }
                    }

                    Intent intent = new Intent ( context, Main4Activity.class );
                    intent.putExtra ("ID",Id );
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity ( intent );
                    
                }

                @Override
                public void onFailure( Call<PokemonsCount> call, Throwable t ) {
                }
            } );

        }
    }
}
