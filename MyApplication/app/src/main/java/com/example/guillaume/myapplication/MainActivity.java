package com.example.guillaume.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.list);
        LinkedList<HashMap<String, String>> list = new LinkedList<HashMap<String,String>>();

        for(int i = 0 ; i < 20 ; i++){
            HashMap<String, String> element = new HashMap<String, String>();
            element.put("number", Integer.toString(i));
            element.put("name", "person " + Integer.toString(i));
            element.put("age", Integer.toString(i) + " years old");
            list.add(element);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.person_item_layout,
                new String[]{"number", "name", "age"},
                new int[]{R.id.number, R.id.name, R.id.age});

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView) findViewById(R.id.res);
                t.setText("Click sur élément " + Integer.toString(position) +
                    " identifiant : " + Long.toString(id));
            }
        });
    }
}
