package com.openclassrooms.ham.editeurtexte;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class Slider extends RelativeLayout {
    private boolean isOpen = true;
    private RelativeLayout toHide = null;
    private static long SPEED = 1000L;

    static public boolean OPEN = true;
    static public boolean CLOSED = false;

    public Slider(Context context) {
        super(context);
        this.init();
    }

    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public Slider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public Slider(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    private void init(){
    }

    // Returns false if t
    public boolean toggle(){
        if (this.toHide == null){
            this.toHide = findViewById(R.id.top_menu);
        }
        if (this.isOpen){
            Animation animation = new TranslateAnimation(0.0F, 0.0F, 0.0F, - this.toHide.getHeight());
            animation.setDuration(Slider.SPEED);
            this.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    toHide.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            this.isOpen = false;
        }else{
            Animation animation = new TranslateAnimation(0.0F, 0.0F, -this.toHide.getHeight(), 0.0F);
            animation.setDuration(Slider.SPEED);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    toHide.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            this.startAnimation(animation);
            this.isOpen = true;
        }
        return this.isOpen;

    }
}
