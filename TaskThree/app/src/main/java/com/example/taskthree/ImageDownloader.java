package com.example.taskthree;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class ImageDownloader implements Target {

    private Context context;
    private WeakReference<ContentResolver> contentResolverWeakReference;
    private String name;
    private String desc;

    public ImageDownloader(Context context, ContentResolver contentResolver, String name, String desc ) {
        this.context = context;
        this.contentResolverWeakReference = new WeakReference<ContentResolver> ( contentResolver );
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void onBitmapLoaded( Bitmap bitmap, Picasso.LoadedFrom from ) {
        ContentResolver r = contentResolverWeakReference.get ();
        if (r != null){
            MediaStore.Images.Media.insertImage ( r,bitmap,name,desc );
        }
        Toast.makeText ( context,"Downloaded",Toast.LENGTH_LONG ).show ();
    }

    @Override
    public void onBitmapFailed( Drawable errorDrawable ) {

    }

    @Override
    public void onPrepareLoad( Drawable placeHolderDrawable ) {

    }
}
