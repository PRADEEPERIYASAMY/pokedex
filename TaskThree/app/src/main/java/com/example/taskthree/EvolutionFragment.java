package com.example.taskthree;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taskthree.Adapter.PokemonListItemAdapter;
import com.example.taskthree.Common.Common;
import com.example.taskthree.Count.PokemonsCount;
import com.example.taskthree.Images.PokemonImages;
import com.example.taskthree.Interface.InterfacePokemonChain;
import com.example.taskthree.Interface.InterfacePokemonCount;
import com.example.taskthree.Interface.InterfacePokemonSpecies;
import com.example.taskthree.PokemonEvolutionChain.PokemonEvolutionChain;
import com.example.taskthree.Species.PokemonSpecies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvolutionFragment extends Fragment {

    int Id;
    PokemonSpecies pokemonSpecies;
    PokemonEvolutionChain pokemonEvolutionChain;
    Retrofit retrofit1;

    ConstraintLayout oneChain;
    ConstraintLayout twoChain;
    ConstraintLayout threeChain;
    ConstraintLayout fourChain;

    TextView oneNameOne;
    TextView twoNameOne;
    TextView twoNameTwo;
    TextView threeNameOne;
    TextView threeNameTwo;
    TextView threeNameThree;
    TextView fourNameOne;
    TextView fourNameTwo;
    TextView fourNameThree;
    TextView fourNameFour;

    ImageView oneImageOne;
    ImageView twoImageOne;
    ImageView twoImageTwo;
    ImageView threeImageOne;
    ImageView threeImageTwo;
    ImageView threeImageThree;
    ImageView fourImageOne;
    ImageView fourImageTwo;
    ImageView fourImageThree;
    ImageView fourImageFour;

    static int foundId;


    public EvolutionFragment( int id,PokemonSpecies pokemonSpecies ) {
        this.Id = id;
        this.pokemonSpecies = pokemonSpecies;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_evolution, container, false );

        oneChain = view.findViewById ( R.id.one_chain );
        twoChain = view.findViewById ( R.id.two_chain );
        threeChain = view.findViewById ( R.id.three_chain );
        fourChain = view.findViewById ( R.id.four_chain );

        oneNameOne = view.findViewById ( R.id.one_name_one  );
        twoNameOne = view.findViewById ( R.id.two_name_one  );
        twoNameTwo = view.findViewById ( R.id.two_name_two  );
        threeNameOne = view.findViewById ( R.id.three_name_one  );
        threeNameTwo = view.findViewById ( R.id.three_name_two  );
        threeNameThree = view.findViewById ( R.id.three_name_three  );
        fourNameOne = view.findViewById ( R.id.four_name_one  );
        fourNameTwo = view.findViewById ( R.id.four_name_two  );
        fourNameThree = view.findViewById ( R.id.four_name_three  );
        fourNameFour = view.findViewById ( R.id.four_name_four  );

        oneImageOne = view.findViewById ( R.id.one_image_one  );
        twoImageOne = view.findViewById ( R.id.two_image_one  );
        twoImageTwo = view.findViewById ( R.id.two_image_two  );
        threeImageOne = view.findViewById ( R.id.three_image_one  );
        threeImageTwo = view.findViewById ( R.id.three_image_two  );
        threeImageThree = view.findViewById ( R.id.three_image_three  );
        fourImageOne = view.findViewById ( R.id.four_image_one  );
        fourImageTwo = view.findViewById ( R.id.four_image_two  );
        fourImageThree = view.findViewById ( R.id.four_image_three  );
        fourImageFour = view.findViewById ( R.id.four_image_four  );

        oneChain.setVisibility ( View.INVISIBLE );
        twoChain.setVisibility ( View.INVISIBLE );
        threeChain.setVisibility ( View.INVISIBLE );
        fourChain.setVisibility ( View.INVISIBLE );

        retrofit1 = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();

        InterfacePokemonChain interfacePokemonChain = retrofit1.create ( InterfacePokemonChain.class );
        final Call<PokemonEvolutionChain> pokemonEvolutionChainCall = interfacePokemonChain.getPokemonEvolutionChain ( Integer.parseInt ( pokemonSpecies.getEvolution_chain ().getUrl ().split ( "/" )[6] ) );
        pokemonEvolutionChainCall.enqueue ( new Callback<PokemonEvolutionChain> () {
            @Override
            public void onResponse( Call<PokemonEvolutionChain> call, Response<PokemonEvolutionChain> response ) {
                if (!response.isSuccessful ()){
                    return;
                }
                pokemonEvolutionChain = response.body ();

                if (pokemonEvolutionChain.getChain ().getEvolves_to ().size () == 0){
                    oneChain.setVisibility ( View.VISIBLE );
                    oneNameOne.setText ( pokemonEvolutionChain.getChain ().getSpecies ().getName () );
                    Glide.with ( getActivity () ).load ( image ( pokemonEvolutionChain.getChain ().getSpecies ().getUrl ().split ( "/" )[6] ) ).into ( oneImageOne );
                }
                else if (pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().size () == 0){
                    twoChain.setVisibility ( View.VISIBLE );
                    twoNameOne.setText ( pokemonEvolutionChain.getChain ().getSpecies ().getName () );
                    twoNameTwo.setText ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getSpecies ().getName () );
                    Glide.with ( getActivity () ).load ( image( pokemonEvolutionChain.getChain ().getSpecies ().getUrl ().split ( "/" )[6] ) ).into ( twoImageOne );
                    Glide.with ( getActivity () ).load ( image ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getSpecies ().getUrl ().split ( "/" )[6] ) ).into ( twoImageTwo );
                }
                else if (pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().size () == 1){
                    threeChain.setVisibility ( View.VISIBLE );
                    threeNameOne.setText ( pokemonEvolutionChain.getChain ().getSpecies ().getName () );
                    threeNameTwo.setText ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getSpecies ().getName () );
                    threeNameThree.setText ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().get ( 0 ).getSpecies ().getName () );
                    Glide.with ( getActivity () ).load ( image ( pokemonEvolutionChain.getChain ().getSpecies ().getUrl ().split ( "/" )[6] ) ).into ( threeImageOne );
                    Glide.with ( getActivity () ).load ( image ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getSpecies ().getUrl ().split ( "/" )[6]  ) ).into ( threeImageTwo );
                    Glide.with ( getActivity () ).load ( image ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().get ( 0 ).getSpecies ().getUrl ().split ( "/" )[6] )).into ( threeImageThree );
                }
                else if (pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().size () == 2){
                    fourChain.setVisibility ( View.VISIBLE );
                    fourNameOne.setText ( pokemonEvolutionChain.getChain ().getSpecies ().getName () );
                    fourNameTwo.setText ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getSpecies ().getName () );
                    fourNameThree.setText ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().get ( 0 ).getSpecies ().getName () );
                    fourNameFour.setText ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().get ( 1 ).getSpecies ().getName () );
                    Glide.with ( getActivity () ).load ( image (pokemonEvolutionChain.getChain ().getSpecies ().getUrl ().split ( "/" )[6]) ).into ( fourImageOne );
                    Glide.with ( getActivity () ).load (  image (pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getSpecies ().getUrl ().split ( "/" )[6] ) ).into ( fourImageTwo );
                    Glide.with ( getActivity () ).load (  image (pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().get ( 0 ).getSpecies ().getUrl ().split ( "/" )[6]) ).into ( fourImageThree );
                    Glide.with ( getActivity () ).load (  image ( pokemonEvolutionChain.getChain ().getEvolves_to ().get ( 0 ).getEvolves_to ().get ( 1 ).getSpecies ().getUrl ().split ( "/" )[6]) ).into ( fourImageFour );
                }

            }

            @Override
            public void onFailure( Call<PokemonEvolutionChain> call, Throwable t ) {

            }
        } );


        return view;
    }

    public int numFinder( final String string){
       Retrofit retrofit2 = new Retrofit.Builder ()
                .baseUrl ( "https://pokeapi.co/api/v2/" )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        InterfacePokemonCount service = retrofit2.create ( InterfacePokemonCount.class );
        Call<PokemonsCount> pokemonCountCall = service.getListOfPokemonCount (20,20);
        pokemonCountCall.enqueue ( new Callback<PokemonsCount> () {
            @Override
            public void onResponse( Call<PokemonsCount> call, Response<PokemonsCount> response ) {
                if(!response.isSuccessful ()){

                    return;
                }

                for (int i = 0;i<response.body ().getCount ();i++){

                    if (string == response.body ().getResults ().get ( i ).getName ()){
                        foundId = i+1;
                    }

                }

            }

            @Override
            public void onFailure( Call<PokemonsCount> call, Throwable t ) {
            }
        } );
        return foundId;
    }
    public String image(String idNum){
        if (Integer.parseInt ( idNum ) <= 9){
            return  "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+idNum+".png";
        }
        else if (Integer.parseInt ( idNum ) <=99){
            return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+idNum+".png" ;
        }
        else {
            return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+idNum+".png" ;
        }
    }
}
