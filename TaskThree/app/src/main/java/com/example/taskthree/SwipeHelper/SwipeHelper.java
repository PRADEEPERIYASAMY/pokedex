package com.example.taskthree.SwipeHelper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskthree.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public abstract class SwipeHelper extends ItemTouchHelper.SimpleCallback {

    int buttonWidth;
    private RecyclerView recyclerView;
    private List<MyButton> buttonList;
    private GestureDetector gestureDetector;
    private int swipePosition = -1;
    private float swipeThreshold = 0.5f;
    private Map<Integer,List<MyButton>> buttonBuffer;
    private Queue<Integer> removerQueue;
    private Context context;
    private MediaPlayer mediaPlayer;

    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener (){
        @Override
        public boolean onSingleTapUp( MotionEvent e ) {

            for (MyButton button:buttonList){
                if (button.onClick ( e.getX (),e.getY () )){
                    break;
                }
            }

            return true;
        }
    };

    private View.OnTouchListener onTouchListener = new View.OnTouchListener () {
        @Override
        public boolean onTouch( View v, MotionEvent event ) {
            if (swipePosition <0){
                return false;
            }
            Point point = new Point ( (int)event.getRawX (),(int)event.getRawY () );

            RecyclerView.ViewHolder swipeViewHolder = recyclerView.findViewHolderForAdapterPosition ( swipePosition );
            View swipedItem = swipeViewHolder.itemView;
            Rect rect = new Rect (  );
            swipedItem.getGlobalVisibleRect ( rect );

            if (event.getAction () == MotionEvent.ACTION_DOWN || event.getAction () == MotionEvent.ACTION_UP || event.getAction () == MotionEvent.ACTION_MOVE){
                if (rect.top < point.y && rect.bottom > point.y){
                    gestureDetector.onTouchEvent ( event );
                }
                else {
                    removerQueue.add ( swipePosition );
                    swipePosition = -1;
                    recoverSwipedItem();
                }
            }
            return false;
        }
    };

    public SwipeHelper( final Context context, RecyclerView recyclerView, int buttonWidth ) {
        super ( 0, ItemTouchHelper.LEFT );
        this.recyclerView = recyclerView;
        this.buttonList = new ArrayList<> (  );
        this.gestureDetector = new GestureDetector ( context,gestureListener );
        this.recyclerView.setOnTouchListener ( onTouchListener );
        this.buttonBuffer = new HashMap<> (  );
        this.buttonWidth = buttonWidth;
        this.context = context;

        removerQueue = new LinkedList<Integer> (  ){
          @Override
          public boolean add(Integer integer){
              if (contains ( integer)){
                  return false;
              }
              else {
                  return super.add ( integer );
              }
          }
        };

        attachSwipe();
    }

    private void attachSwipe() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper ( this );
        itemTouchHelper.attachToRecyclerView ( recyclerView );
    }

    private synchronized void recoverSwipedItem() {
        while (!removerQueue.isEmpty ()){
            int pos = removerQueue.poll ();
            if (pos > -1){

            }
        }
    }

    @Override
    public boolean onMove( @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target ) {
        return false;
    }

    @Override
    public void onSwiped( @NonNull RecyclerView.ViewHolder viewHolder, int direction ) {
        mediaPlayer = MediaPlayer.create (context , R.raw.swipe );
        mediaPlayer.start ();
        int pos = viewHolder.getAdapterPosition ();
        if (swipePosition != pos) {
            removerQueue.add ( swipePosition );
        }
        swipePosition = pos;
        if (buttonBuffer.containsKey ( swipePosition )){
            buttonList = buttonBuffer.get ( swipePosition );
        }
        else {
            buttonList.clear ();
            buttonBuffer.clear ();
            swipeThreshold = 0.5f*buttonList.size ()*buttonWidth;
            recoverSwipedItem ();
        }
    }

    public class MyButton {
        private String text;
        private int imageResId,textSize,color,pos;
        private RectF clickRegion;
        private MyButtonOnClickListener listener;
        private Context context;
        private Resources resources;

        public MyButton(Context context , String text, int textSize,int imageResId, int color, MyButtonOnClickListener listener) {
            this.context = context;
            this.text = text;
            this.textSize = textSize;
            this.imageResId = imageResId;
            this.color = color;
            this.listener = listener;
            resources =context.getResources ();
        }

        public boolean onClick(float x,float y){
            if (clickRegion != null && clickRegion.contains ( x,y )){
                listener.onClick ( pos );
                return true;
            }
            return false;
        }

        public void onDraw( Canvas canvas,RectF rectF,int pos ){
            Paint p = new Paint (  );
            p.setColor ( color );
            canvas.drawRect (rectF,p);
            p.setColor ( Color.WHITE );
            p.setTypeface ( Typeface.DEFAULT_BOLD );
            p.setTextSize ( textSize );

            Rect rect = new Rect (  );
            float cHeight = rectF.height ();
            float cWidth = rectF.width ();
            p.setTextAlign ( Paint.Align.LEFT );
            p.getTextBounds ( text,0,text.length (),rect );
            float x= 0,y=0;
            if (imageResId == 0){
                x= cWidth/2f - rect.width ()/2f - rect.left;
                y = cHeight/2f + rect.height ()/2f - rect.bottom;
                canvas.drawText ( text,rectF.left+x,rectF.top+y,p );
            }
            else {
                x= cWidth/2f - rect.width ()/2f - rect.left;
                y = cHeight/2f + rect.height ()/2f - rect.bottom;
                canvas.drawText ( text,rectF.left+x,rectF.top+y,p );

                Drawable d = ContextCompat.getDrawable ( context,imageResId );
                Bitmap bitmap = drawableToBitmap(d);
                canvas.drawBitmap ( bitmap, ((rectF.left+x)/2),(rectF.top+y)/2,p );
            }
            clickRegion = rectF;
            this.pos = pos;
        }

    }
    private Bitmap drawableToBitmap( Drawable d ) {
        if (d instanceof BitmapDrawable){
            return ((BitmapDrawable)d).getBitmap ();
        }
        Bitmap bitmap = Bitmap.createBitmap ( d.getIntrinsicWidth (),
                d.getIntrinsicHeight (),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas ( bitmap );
        d.setBounds ( 0,0,canvas.getWidth (),canvas.getHeight () );
        d.draw ( canvas );
        return bitmap;
    }

    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder){
        return swipeThreshold;
    }

    @Override
    public float getSwipeEscapeVelocity( float defaultValue ) {
        return 0.1f*defaultValue;
    }

    @Override
    public float getSwipeVelocityThreshold( float defaultValue ) {
        return 5.0f*defaultValue;
    }

    @Override
    public void onChildDraw( @NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive ) {
        int pos = viewHolder.getAdapterPosition ();
        float translationX = dX;
        View itemView = viewHolder.itemView;
        if (pos <0){
            swipePosition = pos;
            return;
        }
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if (dX<0){
                List<MyButton> buffer = new ArrayList<> (  );
                if (!buttonBuffer.containsKey ( pos )){
                    instantiateButton(viewHolder,buffer);
                    buttonBuffer.put ( pos,buffer );
                }
                else {
                    buffer = buttonBuffer.get ( pos );
                }
                translationX = dX*buffer.size ()*buttonWidth / itemView.getWidth ();
                drawButton(c,itemView,buffer,pos,translationX);
            }

        }
        super.onChildDraw ( c,recyclerView,viewHolder,translationX,dY,actionState,isCurrentlyActive );
    }

    private void drawButton( Canvas c, View itemView, List<MyButton> buffer, int pos, float translationX ) {
        float right = itemView.getRight ();
        float dButtonWidth = -1*translationX/buffer.size ();
        for (MyButton button:buffer){
            float left = right-dButtonWidth;
            button.onDraw ( c,new RectF (  left,itemView.getTop (),right,itemView.getBottom ()),pos );
            right = left;

        }
    }

    public abstract void instantiateButton( RecyclerView.ViewHolder viewHolder, List<MyButton> buffer ) ;
}

