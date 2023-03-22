package com.example.jokeboxleonardocampoverde;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class numGenerato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_generato);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setContentView(R.layout.num_generato);
        TextView tv1 = (TextView)findViewById(R.id.num);
        Bundle extras = getIntent().getExtras();
        String num= extras.getString("num");
        if (num!=null){
            tv1.setText(num);
        }

        else{
            tv1.setText("OMIODDIO UN ERRORE");
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}