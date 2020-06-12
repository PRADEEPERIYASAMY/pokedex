package com.example.taskthree.Images;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PokemonImages {
    private static List<String> images = new ArrayList<> (  );

    public String imagesSelected;
    private int id;

    public PokemonImages( int id ) {
        this.id = id;
    }


    public String getImagesSelected() {
        images = getImages ();
        imagesSelected = images.get ( id );
        return imagesSelected;
    }


    private static List<String> getImages() {
        for (int i = 0;i< 807;i++){
            if ((i+1) <= 9){
                images.add (i, "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+String.valueOf ( i+1 )+".png" );
            }
            else if ((i+1) <=99){
                images.add (i,"https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+String.valueOf ( i+1 )+".png" );
            }
            else {
                images.add (i,"https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+String.valueOf ( i+1 )+".png" );
            }
        }
        return images;
    }



}
