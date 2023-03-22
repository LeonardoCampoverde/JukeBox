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
        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);

        Intent intent = new Intent(MainActivity.this, numGenerato.class);
        intent.putExtra("num",Integer.toString(random_int));
        startActivity(intent);
    }
}