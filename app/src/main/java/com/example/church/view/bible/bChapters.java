package com.example.church.view.bible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.church.R;
import com.example.church.outils.RecyclerViewAdapter;
import com.example.church.outils.RecyclerViewInterface;
import com.example.church.view.HL.HLnumber;
import com.example.church.view.HL.Hl_main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class bChapters extends AppCompatActivity implements RecyclerViewInterface {

    String name;
    ArrayList<String> verses = new ArrayList<>();
    RecyclerView recyclerView;
    TextView textView;
    String chapters;
    ArrayList<Integer> number = new ArrayList<Integer>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_chapters);

        name = getIntent().getStringExtra("name");

        recyclerView = findViewById(R.id.recyclerVerses);
        textView = findViewById(R.id.txtVerses);

        textView.setText(name);

        fetchdata();
    }

    private void fetchdata(){
        verses.clear();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("bible.txt"), StandardCharsets.ISO_8859_1)
            );
            String line  = reader.readLine();
            while(line != null){
                if(line.contains(name)){
                    int splitter = line.indexOf('[');
                    chapters = line.substring(splitter);
                    chapters = chapters.substring(1, chapters.length()-1);
                    String[] numbers = chapters.split(",");

                    for(int i = 0; i < numbers.length; i++){
                        String v = name + " " + numbers[i];
                        number.add(Integer.parseInt(numbers[i]));
                        verses.add(v);
                    }
                    break;
                }
                line = reader.readLine();
            }
            loader();
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void loader(){
        adapter = new RecyclerViewAdapter(this, this, verses);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int position) {
        try{
            String complet_verse = verses.get(position);
            String names = name;
            String chapter = number.get(position).toString();

            Intent intent = new Intent(bChapters.this, verse.class);
            intent.putExtra("complet_verse", complet_verse);
            intent.putExtra("names", names);
            intent.putExtra("chapter", chapter);
            startActivity(intent);
        }catch (Exception e){
            //error
        }
    }
}