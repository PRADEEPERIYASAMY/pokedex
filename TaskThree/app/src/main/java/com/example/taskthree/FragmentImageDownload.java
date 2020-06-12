package com.example.taskthree;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import dmax.dialog.SpotsDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentImageDownload extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1000;
    String imageUrl;
    ImageView downloadImage;
    Button downLoadButton;

    public FragmentImageDownload( String imageUrl) {
        // Required empty public constructor
        this.imageUrl = imageUrl;
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
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_image_download, container, false );
        downloadImage = view.findViewById ( R.id.download_image );
        downLoadButton = view.findViewById ( R.id.button_download );
        Glide.with ( getActivity () ).load ( imageUrl ).into ( downloadImage );


        if (ActivityCompat.checkSelfPermission ( getActivity (), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            requestPermissions ( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE );
        }
        downLoadButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick( View v ) {
                if (ActivityCompat.checkSelfPermission ( getActivity (), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
                    Toast.makeText ( getActivity (),"you should grant permission",Toast.LENGTH_LONG ).show ();
                    requestPermissions ( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE );
                    return;
                }
                else {
                    String fileName = UUID.randomUUID ().toString ()+".png";
                    Picasso.with ( getContext () ).load ( imageUrl ).into ( new ImageDownloader ( getActivity ().getBaseContext (),getActivity ().getContentResolver (),fileName,"image Description" ) );
                }
            }
        } );

        return view;
    }
}
