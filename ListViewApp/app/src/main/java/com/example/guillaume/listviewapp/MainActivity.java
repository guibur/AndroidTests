package com.example.guillaume.listviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.HashMap;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    private ListView v = null;
    private TextView t = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.v = (ListView) findViewById(R.id.list);
        LinkedList<HashMap<String, String>> list = new LinkedList<HashMap<String,String>>();

        for(int i = 0 ; i < 20 ; i++){
            HashMap<String, String> element = new HashMap<String, String>();
            element.put("number", Integer.toString(i));
            element.put("name", "person " + Integer.toString(i));
            element.put("age", Integer.toString(i) + " years old");
            list.add(element);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.person_item,
                new String[]{"number", "name", "age"},
                new int[]{R.id.number, R.id.name, R.id.age});

        this.v.setAdapter(adapter);

        this.v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView) findViewById(R.id.res);
                t.setText("Click sur élément " + Integer.toString(position) +
                        " identifiant : " + Long.toString(id)
                );
            }
        });

        this.t = (TextView) findViewById(R.id.check);
        this.t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                long idx = MainActivity.this.v.getCheckedItemPosition();
                MainActivity.this.t.setText("checked : " + String.valueOf(idx));

            }
        });
    }
}
