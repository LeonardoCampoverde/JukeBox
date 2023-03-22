package com.example.jokeboxleonardocampoverde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void genera(View view){

        int max=20;
        int min=0;
        int random_int = (int)Math.floor(Math.random() * (max - min) + min);//creo numero random
        //creo un oggetto che Intent che mi passerà dall'activity main alla generazione del numero passandogli il numero in formato stringa e in formato numerico
        Intent intent = new Intent(MainActivity.this, numGenerato.class);
        intent.putExtra("num",Integer.toString(random_int));
        intent.putExtra("intNum",random_int);

        //avvio l'activity nuova
        startActivity(intent);
    }
}