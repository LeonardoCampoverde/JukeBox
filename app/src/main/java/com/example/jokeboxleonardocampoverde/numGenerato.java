package com.example.jokeboxleonardocampoverde;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class numGenerato extends AppCompatActivity {

    //lista delle canzoni
    final String[] canzoni= new String[]{
            "Brividi",
            "As it was",
            "Giovani Wannabe",
            "Cuff it",
            "Shakerando",
            "Importante",
            "The Loneliest",
            "Non lo sai",
            "Le pietre non volano",
            "Bam bam",
            "Bellissima",
            "Bloody Mary",
            "Buonanotte",
            "Despechà",
            "Titi me preguntò",
            "BZRP Music Sessions #52",
            "Bagno a mezzanotte",
            "Running up that hill",
            "Cold Heart",
            "La dolce vita",
    };


    //lista dei link alle canzoni
    final String[] link= {
            "https://www.youtube.com/watch?v=MA_5P3u0apQ",
            "https://www.youtube.com/watch?v=H5v3kku4y6Q",
            "https://www.youtube.com/watch?v=4GXDFtuG9Xo",
            "https://www.youtube.com/watch?v=yrtWLyp5gLI",
            "https://www.youtube.com/watch?v=U6VMjHJ18a0",
            "https://www.youtube.com/watch?v=tTaEfEyEGrQ",
            "https://www.youtube.com/watch?v=odWKEfp2QMY",
            "https://www.youtube.com/watch?v=JQgHPTTXr0I",
            "https://www.youtube.com/watch?v=kEn6YsuH0KE",
            "https://www.youtube.com/watch?v=-8VfKZCOo_I",
            "https://www.youtube.com/watch?v=qz88Dx-_lA4",
            "https://www.youtube.com/watch?v=MsXdUtlDVhk",
            "https://www.youtube.com/watch?v=osuKmCiGy1w",
            "https://www.youtube.com/watch?v=5g2hT4GmAGU",
            "https://www.youtube.com/watch?v=Cr8K88UcO0s",
            "https://www.youtube.com/watch?v=A_g3lMcWVy0",
            "https://www.youtube.com/watch?v=XSHcZ4rcBDk",
            "https://www.youtube.com/watch?v=wp43OdtAAkM",
            "https://www.youtube.com/watch?v=qod03PVTLqk",
            "https://www.youtube.com/watch?v=TX_csdgqhxA",
    };

    //dichiaro l'oggetto Button che mi servirà per aggiungere il listener
    private Button ascolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_generato);



        //trovo tramite id la textview in cui stamperò il numero ottenuto
        TextView numero = (TextView)findViewById(R.id.num);
        Bundle extras = getIntent().getExtras();
        String num= extras.getString("num"); //ottengo il numero
        if (num!=null){
            numero.setText(num);//lo stampo dentro la textview
        }

        else{
            numero.setText("OMIODDIO UN ERRORE");
            return;
        }

        //ottengo il valore NUMERICO (int) del numero
        int intNum=extras.getInt("intNum");

        //trovo tramite id la textview che in cui ci sarà il nome della canzone
        TextView canzone = (TextView)findViewById(R.id.nome_canzone);
        canzone.setText(canzoni[intNum]);//imposto il nome della canzone
        //trovo tramite id il bottone in cui assocerò il link da aprire della rispettiva canzone
        ascolta = findViewById(R.id.ascolta);
        //imposto la funzione da chiamare quando il bottone è premuto
        ascolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ascoltaMusica();//chiamo la funzione
            }
        });



    }




    public void ascoltaMusica(){
        //creo una nuova activity e apro il link della canzone
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link[getIntent().getIntExtra("intNum",0)]));
        //avvio l'activity
        startActivity(intent);
    }




    //crea la freccetta in alto a sinistra per andare indietro

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}