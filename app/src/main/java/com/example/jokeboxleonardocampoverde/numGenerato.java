package com.example.jokeboxleonardocampoverde;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class numGenerato extends AppCompatActivity {







    //dichiaro l'oggetto Button che mi servirà per aggiungere il listener
    private Button ascolta;


    private MediaPlayer mediaPlayer;
    private boolean riproduzione = false;
    private ImageButton player;

    Canzone [] c = Canzone.init();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_generato);


        player=findViewById(R.id.player);




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

        int intNum=getIntent().getIntExtra("intNum",0);

        //trovo tramite id la textview che in cui ci sarà il nome della canzone
        TextView canzone = (TextView)findViewById(R.id.nome_canzone);
        canzone.setText(c[intNum].nome);//imposto il nome della canzone
        //trovo tramite id il bottone in cui assocerò il link da aprire della rispettiva canzone
        ascolta = findViewById(R.id.ascolta);
        //imposto la funzione da chiamare quando il bottone è premuto
        ascolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ascoltaMusica();//chiamo la funzione
            }
        });
        mediaPlayer = MediaPlayer.create(this, c[intNum].path);
        player.setImageResource(R.drawable.play);



    }




    public void ascoltaMusica(){
        int intNum=getIntent().getIntExtra("intNum",0);
        //creo una nuova activity e apro il link della canzone
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c[intNum].link));
        //avvio l'activity
        startActivity(intent);
    }

    public void riproduci(View view){
        Log.d("NUMGENERATO","FUNZIONE CHIAMATA: riproduci");
        if(riproduzione){
            mediaPlayer.pause();
            riproduzione=false;
            player.setImageResource(R.drawable.play);
            Log.d("NUMGENERATO",String.valueOf(player.getId()));
        }
        else{
            mediaPlayer.start();
            riproduzione=true;
            player.setImageResource(R.drawable.pause);

        }
    }





    @Override
    protected void onDestroy(){
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    //crea la freccetta in alto a sinistra per andare indietro

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}