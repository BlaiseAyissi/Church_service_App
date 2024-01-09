package com.example.church.view.HL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.church.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HLnumber extends AppCompatActivity {

    String Title;
    String Number;
    TextView textViewTitle;
    TextView textViewHlcomplet;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlnumber);


        Title = getIntent().getStringExtra("Title");
        Number = getIntent().getStringExtra("Number");

        textViewTitle = findViewById(R.id.textViewTitleHl);
        textViewHlcomplet = findViewById(R.id.textViewHLcomplet);

        String text = getHL();
        textViewHlcomplet.setText(Html.fromHtml(text));

    }
    public String getHL(){
       String text = "";
       Number = Number+".txt";
       try {
           BufferedReader reader = new BufferedReader(
                   new InputStreamReader(getAssets().open("HL/"+Number), "UTF-8")
           );
           String line = reader.readLine();
           int i = 0;

           while(line != null){
               if (line.matches("1|2|3|4|5|6|7|8|9|10|11|Refrain")) {
                   text = text +"<br>"+ "<b>" + line +"</b>";
                   line = reader.readLine();
               } else if (line.matches(Title)) {
                   text = text + "<br>"+ "<b>" + line +"</b>";
                   line = reader.readLine();
               }else{
                   text = text + "<br>" + line;
                   line = reader.readLine();
               }
               i = 1;
           }

       }catch (Exception e){
           //catch exception
           Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
       }

       return text;
    }
}