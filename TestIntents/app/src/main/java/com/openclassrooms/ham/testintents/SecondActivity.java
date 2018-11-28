package com.openclassrooms.ham.testintents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
    TextView mtv1 = null;
    TextView mtv2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        mtv1 = (TextView) findViewById(R.id.tv1);
        mtv2 = (TextView) findViewById(R.id.tv2);

        Intent myIntent = getIntent();
        SuperClass mySC = myIntent.getParcelableExtra("super");

        mtv1.setText(String.valueOf(mySC.getmSubClass().getMi()));
        mtv2.setText(mySC.getmSubClass().getMs());
    }
}
