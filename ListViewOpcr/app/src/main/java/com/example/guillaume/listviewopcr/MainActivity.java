package com.example.guillaume.listviewopcr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> listeSexe = {"Féminin", "Masculin"};
        ArrayList<String> listeLang = {"C++", "Java", "Python", "Ruby"};

        ListView listSexeView = (ListView) findViewById(R.id.list_sexe);
        listSexeView.setAdapter(new ArrayAdapter<String>(this, listeSexe, android.R.layout.simple_list_item_single_choice));

        ListView listLangView = (ListView) findViewById(R.id.list_language);
    }
}
