package com.example.perkkxapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

/**
 * Created by pc on 5/28/2015.
 */
public class reedem_popup extends Activity {
    Button redeem;

    //    private static final int SWIPE_MIN_DISTANCE =120;
//    private static final int SWIPE_THRESHOLD = 200;
//    private GestureDetector gestureDetector;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.redeem_popup);
//        redeem = (Button) findViewById(R.id.redeembtn);
//
//        gestureDetector =new GestureDetector(this,new GestureDetector.SimpleOnGestureListener());
//        redeem.onTouchEvent(new MotionEvent(){
//          @Override
//            public boolean onFling(MotionEvent e1,MotionEvent e2,
//                                   float velocityX, float velocityY){
//                                   float deltaX = e2.getX()- e1.getX();
//            if ((Math.abs(deltaX) < SWIPE_MIN_DISTANCE)
//            || (Math.abs(velocityX) < SWIPE_THRESHOLD)){
//            return false ;
//        } else {
//            if (deltaX < 0 ) { // le
//                handleSwipeRight();
//            } else { // right to lef
//                handleSwipeLeft();
//            }
//        }
//        return true ;
//    }
//
//private void handleSwipeLeft(){
////
//}
//private void handleSwipeRight(){
////
//        }
//
//        });
//        }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeem_popup);
        redeem = (Button) findViewById(R.id.redeembtn);
        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeListItem(view);
            }
        });
    }


    protected void removeListItem(final View view) {

        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_left_out);
        view.startAnimation(animation);

        android.os.Handler handle = new android.os.Handler();
        handle.postDelayed(new Runnable() {

            @Override
            public void run() {
                view.setVisibility(View.GONE);
                animation.cancel();
            }
        }, 1000);

    }
}






