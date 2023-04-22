package com.example.jokeboxleonardocampoverde;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lyrics extends AppCompatActivity {


    TextView lyrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        lyrics=findViewById(R.id.lyrics);
        String nameAsset= getIntent().getStringExtra("resTitle");
        String textLyrics="";
        try {
            String buff;
            BufferedReader bf =new BufferedReader ( new InputStreamReader( getAssets().open(nameAsset) ) );
            while((buff= bf.readLine())!=null){
                textLyrics+=buff+"\n";
            }

        } catch (IOException e) {
            Log.e("ERRORE","NON TROVATO FILE: "+nameAsset);
        }
        Log.d("VALORE LYRICS",textLyrics);

        lyrics.setText(textLyrics);
        lyrics.setMovementMethod(new ScrollingMovementMethod());
    }
}