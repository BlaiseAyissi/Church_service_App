package com.example.church.view.bible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.church.R;
import com.example.church.outils.RecyclerViewAdapter;
import com.example.church.outils.RecyclerViewInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class newTestament extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<String> bible = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_testament);

        recyclerView = findViewById(R.id.recyclerNT);
        searchView = findViewById(R.id.searchNT);

        fetchdata("");
        setupSearchV();
    }

    private void setupSearchV(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fetchdata(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fetchdata(s);
                return false;
            }
        });
    }

    public void fetchdata(String value){
        bible.clear();
        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("bible.txt"), StandardCharsets.ISO_8859_1)
            );
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                if(counter > 38){
                    int splitter = line.indexOf('[');
                    line = line.substring(0,splitter);
                    bible.add(line);
                }
                line = reader.readLine();
                counter++;
            }
        }catch (Exception e){
            Toast.makeText(this,  e.toString(), Toast.LENGTH_LONG).show();
        }

        if(!value.equals("")){
            int counter = 0;
            for( int i = 0; i<26 ; i++){
                if(bible.get(counter).toLowerCase().indexOf(value.toLowerCase()) != -1){
                    counter++;
                }
                else{
                    bible.remove(counter);
                }
            }
        }
        loader();
    }
    public void loader(){
        adapter = new RecyclerViewAdapter(this, this, bible);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int position) {
        try{
            String name = bible.get(position);
            Intent intent = new Intent(newTestament.this, bChapters.class);
            intent.putExtra("name", name);

            startActivity(intent);

            // intent to the main hl page
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}