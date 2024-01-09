package com.example.church.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.church.R;
import com.example.church.view.HL.Hl_main;
import com.example.church.view.bible.bible;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButtonBible;
    ImageButton imageButtonHL;
    ImageButton imageButtonEDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonBible = findViewById(R.id.BtnBible);
        imageButtonHL = findViewById(R.id.BtnHL);
        imageButtonEDS = findViewById(R.id.BtnEDS);


        imageButtonBible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , bible.class);
                startActivity(intent);
            }
        });

        imageButtonHL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Hl_main.class);
                startActivity(intent);
            }
        });


    }

}