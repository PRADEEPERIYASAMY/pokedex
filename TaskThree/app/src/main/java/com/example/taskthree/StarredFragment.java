package com.example.taskthree;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taskthree.Adapter.PokemonListItemAdapter;
import com.example.taskthree.Adapter.StarredAdapter;
import com.example.taskthree.Common.Common;
import com.example.taskthree.Database.PokemonDatabase;
import com.example.taskthree.SwipeHelper.MyButtonOnClickListener;
import com.example.taskthree.SwipeHelper.SwipeHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.http.Url;


/**
 * A simple {@link Fragment} subclass.
 */
public class StarredFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1000;
    RecyclerView recyclerView;
    TextView pokemonName;
    TextView pokemonId;
    private static String url;
    public static StarredAdapter starredAdapter;
    ProgressBar progressBar;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    
    public StarredFragment() {
        // Required empty public constructor
    }

    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults ) {
        super.onRequestPermissionsResult ( requestCode, permissions, grantResults );
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
            {
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText ( getActivity (),"Permission Granted",Toast.LENGTH_LONG ).show ();
                }
                else {
                    Toast.makeText ( getActivity (),"Permission Denied",Toast.LENGTH_LONG ).show ();
                }
                break;
            }
        }
    }


    @Override
    public View onCreateView( final LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_starred, container, false );
        recyclerView = view.findViewById ( R.id.recycler_starred_item );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity ()) );
        starredAdapter = new StarredAdapter ( getActivity () );
        recyclerView.setAdapter ( starredAdapter );

        progressBar = view.findViewById ( R.id.progressBarstarred);
        progressBar.setMax ( 100 );
        progressBar.setVisibility ( View.VISIBLE );


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        if (ActivityCompat.checkSelfPermission ( getActivity (), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            requestPermissions ( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE );
        }

        SwipeHelper swipeHelper = new SwipeHelper (getActivity (),recyclerView,400) {
            @Override
            public void instantiateButton( final RecyclerView.ViewHolder viewHolder, List<MyButton> buffer ) {
                buffer.add ( new MyButton ( getContext (),"Share",100,R.drawable.ic_search_black_24dp, Color.rgb ( 103,58,183 ), new MyButtonOnClickListener () {
                    @Override
                    public void onClick( int pos ) {
                        ImageView image = viewHolder.itemView.findViewById ( R.id.pokemon_item_image );
                        final TextView name = viewHolder.itemView.findViewById ( R.id.pokemon_item_name );
                        final TextView id = viewHolder.itemView.findViewById ( R.id.pokemon_item_id );
                        mediaPlayer = MediaPlayer.create (getActivity () , R.raw.clicktwo );
                        mediaPlayer.start ();

                        if ((Integer.parseInt (id.getText ().toString () )) <= 9){
                            url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/00"+ id.getText ().toString ()+".png";
                        }
                        else if ((Integer.parseInt (  id.getText ().toString ())) <=99){
                            url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/0"+ id.getText ().toString ()+".png";
                        }
                        else {
                            url = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+ id.getText ().toString ()+".png";
                        }

                        if (ActivityCompat.checkSelfPermission ( getActivity (), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
                            Toast.makeText ( getActivity (),"you should grant permission",Toast.LENGTH_LONG ).show ();
                            requestPermissions ( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE );
                            return;
                        }
                        else {
                            Picasso.with(getActivity ()).load(url).into(new Target() {
                                @Override
                                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                    Intent intent = new Intent( Intent.ACTION_SEND);
                                    intent.setType("*/*");
                                    intent.putExtra( Intent.EXTRA_STREAM, getlocalBitmapUri(bitmap));
                                    intent.putExtra ( Intent.EXTRA_TEXT,"NAME:"+name.getText ().toString ()+", "+"ID:"+id.getText ().toString () );
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(Intent.createChooser(intent, "share"));

                                }

                                @Override
                                public void onBitmapFailed(Drawable errorDrawable) {

                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {

                                }
                            });
                        }
                    }
                } ) );
            }
        };

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT) {
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                if (swipeDir == ItemTouchHelper.RIGHT){
                    mediaPlayer2 = MediaPlayer.create (getActivity () , R.raw.delete );
                    mediaPlayer2.start ();
                    TextView name = viewHolder.itemView.findViewById ( R.id.pokemon_item_name );
                    int position = viewHolder.getAdapterPosition ();
                    PokemonDatabase pokemonDatabase = new PokemonDatabase ( getActivity () );
                    pokemonDatabase.DeleteData ( name.getText ().toString () );
                    recyclerView.getAdapter ().notifyItemRemoved ( position );
                    Toast.makeText ( getActivity (),"Removed"+" "+name.getText ().toString ()+" "+"from starred",Toast.LENGTH_LONG ).show ();
                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        progressBar.setVisibility ( View.INVISIBLE );

        return view;
    }


    private Uri getlocalBitmapUri(Bitmap bitmap) {
        Uri bmuri = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress( Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.close();
            bmuri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return bmuri;
    }

}
