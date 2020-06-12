package com.example.taskthree.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PokemonDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pokemon.db";
    public static final String TABLE_NAME = "pokemon_info";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "IDP";
    public static long result;
    public static boolean need ;

    public PokemonDatabase( @Nullable Context context ) {
        super ( context, DATABASE_NAME, null, 1 );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL ( "create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,IDP INTEGER)" );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL ( "DROP TABLE IF EXISTS "+TABLE_NAME );
        onCreate ( db );
    }

    public boolean Insert(String name ,int idp){
        SQLiteDatabase db = this.getWritableDatabase ();

        need = Checker ( idp );

        if (!need){
            ContentValues values = new ContentValues (  );
            values.put ( COL_2,name );
            values.put ( COL_3,idp );
            result = db.insert ( TABLE_NAME,null,values );
        }
        else {
            return false;
        }
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor FetchData(){
        SQLiteDatabase db = this.getWritableDatabase ();
        Cursor res = db.rawQuery ( "select * from "+TABLE_NAME+" order by IDP",null );
        return res;
    }

    public Integer DeleteData (String name){
        SQLiteDatabase db = this.getWritableDatabase ();
        return db.delete ( TABLE_NAME,"NAME = ?",new String[] {name} );
    }

    public boolean Checker( int idp){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_NAME + " where IDP  = " +idp;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }
        else
        {
            cursor.close();
            return true;
        }
    }
}
