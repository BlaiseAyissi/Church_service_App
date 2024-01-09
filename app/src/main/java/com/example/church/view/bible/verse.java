package com.example.church.view.bible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import android.widget.Toast;

import com.example.church.R;
import com.example.church.outils.BibleRecyclerViewAdapter;
import com.example.church.outils.RecyclerViewAdapter;
import com.example.church.outils.RecyclerViewInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class verse extends AppCompatActivity implements RecyclerViewInterface{
    String complet_verse;
    String names;
    String chapter;
    TextView txtTitre;
    ArrayList<SpannableStringBuilder> verses = new ArrayList<>();
    RecyclerView recyclerVerses;
    BibleRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse);

        complet_verse = getIntent().getStringExtra("complet_verse");
        names = getIntent().getStringExtra("names");
        chapter = getIntent().getStringExtra("chapter");
        //Toast.makeText(this, complet_verse, Toast.LENGTH_SHORT).show();

        txtTitre = findViewById(R.id.txtVerses);
        txtTitre.setText(complet_verse);

        recyclerVerses = findViewById(R.id.recyclerVerses);
        fetchVerse();

    }

    public void fetchVerse(){
        verses.clear();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("LSG/"+names+"/"+complet_verse+".txt"), StandardCharsets.UTF_8)

            );
            String line = reader.readLine();
            while (line != null){
                String part[] = line.split(" ", 2);

                SpannableStringBuilder complet = new SpannableStringBuilder();

                SpannableString str1= new SpannableString(part[0]);
                str1.setSpan(new StyleSpan(Typeface.BOLD), 0, str1.length(), str1.SPAN_EXCLUSIVE_EXCLUSIVE);
                str1.setSpan(new ForegroundColorSpan(Color.RED), 0, str1.length(), 0);
                complet.append(str1);

                SpannableString str3= new SpannableString("  ");
                complet.append(str3);

                SpannableString str2= new SpannableString(part[1]);
                complet.append(str2);

                verses.add(complet);
                line = reader.readLine();
            }
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        loader();
    }

    public void loader(){
        adapter = new BibleRecyclerViewAdapter(this, this,verses);
        recyclerVerses.setAdapter(adapter);
        recyclerVerses.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onItemClick(int position) {

    }
}
