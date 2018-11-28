package com.openclassrooms.ham.appligrosbouton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event){
            Button button =(Button) v;
            float button_width = button.getWidth();
            float button_height = button.getHeight();
            float finger_x = event.getX();
            float finger_y = event.getY();
            button.setTextSize(Math.abs((button_height / 2 - finger_y)) +
                               Math.abs(button_width / 2 - finger_x));
            return true;
        }
    };

    Button b = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.bouton);

        b.setOnTouchListener(touchListener);
    }
}