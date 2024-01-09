package com.example.church.view.bible;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.church.R;

public class bible extends AppCompatActivity {

    ImageButton btnOt;
    ImageButton btnNt;
    ImageButton btnProgramme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible);

        btnOt = findViewById(R.id.BtnBibleOT);
        btnNt = findViewById(R.id.BtnBibleNt);

        btnOt.setOnClickListener(view -> {
            Intent intent = new Intent(bible.this , oldTestament.class);
            startActivity(intent);
        });

        btnNt.setOnClickListener(view -> {
            Intent intent = new Intent(bible.this , newTestament.class);
            startActivity(intent);
            //intent to the new testament page bla bla bla still not loading F*Ck
        });
    }

}