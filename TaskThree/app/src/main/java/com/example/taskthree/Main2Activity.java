package com.example.taskthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private ConstraintLayout fullBall;
    private ConstraintLayout firstBall;
    private ConstraintLayout secondBall;
    private ConstraintLayout pokePikachu;
    private ConstraintLayout pokemon;
    private ConstraintLayout pokemonHero;
    private ConstraintLayout conGo;
    private Button go;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView ( R.layout.activity_main2 );
        fullBall = findViewById ( R.id.full_ball );
        firstBall = findViewById ( R.id.ball_first );
        secondBall = findViewById ( R.id.ball_second );
        pokePikachu = findViewById ( R.id.pokepikachu );
        pokemon = findViewById ( R.id.pokemon );
        pokemonHero = findViewById ( R.id.pokemon_hero );
        conGo = findViewById ( R.id.con_go );
        final Animation rotate = AnimationUtils.loadAnimation ( getApplicationContext (),R.anim.rotate );
        final Animation left = AnimationUtils.loadAnimation ( getApplicationContext (),R.anim.move_left );
        final Animation right = AnimationUtils.loadAnimation ( getApplicationContext (),R.anim.move_right );
        final Animation zoomIn = AnimationUtils.loadAnimation ( getApplicationContext (),R.anim.zoom_in );
        final Animation zoomOut = AnimationUtils.loadAnimation ( getApplicationContext (),R.anim.zoom_out );
        pokemon.startAnimation ( zoomIn );
        pokePikachu.startAnimation ( zoomIn );
        Animation.AnimationListener animationListener = new Animation.AnimationListener () {
            @Override
            public void onAnimationStart( Animation animation ) {

            }

            @Override
            public void onAnimationEnd( Animation animation ) {
                firstBall.setVisibility ( View.INVISIBLE );
                secondBall.setVisibility ( View.INVISIBLE );
                pokemonHero.startAnimation ( zoomIn );
                conGo.startAnimation ( zoomIn );
                pokemonHero.setVisibility ( View.VISIBLE );
                conGo.setVisibility ( View.VISIBLE );
                new Handler (  ).postDelayed ( new Runnable () {
                    @Override
                    public void run() {
                        Intent intent = new Intent ( getApplicationContext (),Main3Activity.class );
                        startActivity ( intent );
                        finish ();
                    }
                },SPLASH_TIME_OUT );

            }

            @Override
            public void onAnimationRepeat( Animation animation ) {

            }
        };
        left.setAnimationListener ( animationListener );
        right.setAnimationListener ( animationListener );
        rotate.setAnimationListener ( new Animation.AnimationListener () {
            @Override
            public void onAnimationStart( Animation animation ) {

            }

            @Override
            public void onAnimationEnd( Animation animation ) {
                fullBall.setVisibility ( View.INVISIBLE );
                firstBall.startAnimation ( left );
                secondBall.startAnimation ( right );
                pokePikachu.startAnimation ( zoomOut );
                pokePikachu.setVisibility ( View.INVISIBLE );

            }

            @Override
            public void onAnimationRepeat( Animation animation ) {

            }

        } );
        go = findViewById ( R.id.go );
        go.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick( View v ) {
                fullBall.startAnimation ( rotate );
                go.setEnabled ( false );
                go.setVisibility ( View.INVISIBLE );

            }
        } );

    }
    protected void onPause() {
        super.onPause ();
        overridePendingTransition ( android.R.anim.fade_in,android.R.anim.fade_out );
    }
}
