package com.example.church.view.HL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import android.text.Html;
import android.widget.Toast;

import com.example.church.R;
import com.example.church.outils.RecyclerViewAdapter;
import com.example.church.outils.RecyclerViewInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Hl_main extends AppCompatActivity implements RecyclerViewInterface
{

    ArrayList<String> hl = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hl_main);

        recyclerView = findViewById(R.id.recyclerHL);
        searchView = findViewById(R.id.searchHL);
        fetchData("");
        setupSerachView();

    }

    private void setupSerachView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fetchData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fetchData(s);
                return false;
            }
        });
    }

    public  void  fetchData(String value){
        hl.clear();
        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("Hl_refs.txt"), StandardCharsets.ISO_8859_1)
            );
            String line = reader.readLine();
            while (line != null) {
                hl.add(line);
                line = reader.readLine();

            }
        }catch (Exception e){
            Toast.makeText(this,  e.toString(), Toast.LENGTH_LONG).show();
        }

        if(!value.equals("")){
            int counter = 0;
            for( int i = 0; i < 654 ; i++){
                if(hl.get(counter).toLowerCase().indexOf(value.toLowerCase()) != -1){
                    counter++;
                }
                else{
                    hl.remove(counter);
                }
            }
        }
        loader();
    }

    public void loader(){
        adapter = new RecyclerViewAdapter(this, this, hl);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int position) {
        try{
            String number = "H"+hl.get(position).substring(0,3);
            Intent intent = new Intent(Hl_main.this, HLnumber.class);
            intent.putExtra("Title", hl.get(position));
            intent.putExtra("Number", number);
            startActivity(intent);
        }catch (Exception e){
            //error
        }
    }

}