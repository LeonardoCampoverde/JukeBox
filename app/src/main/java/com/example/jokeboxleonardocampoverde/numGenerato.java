package com.example.jokeboxleonardocampoverde;


import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


public class numGenerato extends AppCompatActivity {
    String CHANNEL_ID = "player";
    int CHANNEL_INT=1;


    SeekBar s;

    private AudioPlay mediaPlayer;
    private ImageButton player;
    int intNum;

    Canzone[] c = Canzone.init();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_generato);


        player = findViewById(R.id.player);
        s = findViewById(R.id.seekBar);

        //trovo tramite id la textview in cui stamperò il numero ottenuto
        TextView numero = findViewById(R.id.num);
        Bundle extras = getIntent().getExtras();
        String num = extras.getString("num"); //ottengo il numero
        if (num != null) {
            numero.setText(num);//lo stampo dentro la textview
        } else {
            numero.setText("OMIODDIO UN ERRORE");
            return;
        }

        //ottengo il valore NUMERICO (int) del numero

        intNum = getIntent().getIntExtra("intNum", 0)-1;

        //trovo tramite id la textview che in cui ci sarà il nome della canzone
        TextView canzone = (TextView) findViewById(R.id.nome_canzone);
        canzone.setText(c[intNum].nome);//imposto il nome della canzone
        //trovo tramite id il bottone in cui assocerò il link da aprire della rispettiva canzone
        //dichiaro l'oggetto Button che mi servirà per aggiungere il listener
        Button ascolta = findViewById(R.id.ascolta);
        //imposto la funzione da chiamare quando il bottone è premuto
        ascolta.setOnClickListener(v -> {
            ascoltaMusica();//chiamo la funzione
        });

        mediaPlayer=new AudioPlay(this, c[intNum].path);
        //audioPlay= new AudioPlay(mediaPlayer);
        player.setImageResource(R.drawable.play);

        s.setMax(mediaPlayer.duration());



        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                s.setProgress(mediaPlayer.currentPosition());
            }
        },0,900);


        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(b)
                        mediaPlayer.setSeek(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                s.setProgress(1);
                player.setImageResource(R.drawable.play);
                mediaPlayer.pauseAudio();
            }

        });




        ImageView im = findViewById(R.id.imageView);
        im.setImageResource(c[intNum].img);


    }



    public void lyrics(View v){
        Intent intent = new Intent(getApplicationContext(),Lyrics.class);
        intent.putExtra("resTitle",c[intNum].nameAsset);
        startActivity(intent);
    }
    @Override
    public void onResume(){
        super.onResume();
        if(mediaPlayer.isplayingAudio){
            player.setImageResource(R.drawable.pause);
        }
        else{
            player.setImageResource(R.drawable.play);
        }
    }

    public void ascoltaMusica() {
        int intNum = getIntent().getIntExtra("intNum", 0);
        //creo una nuova activity e apro il link della canzone
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c[intNum].link));
        //avvio l'activity
        startActivity(intent);
    }

    public void riproduci(View view) {

        if (mediaPlayer.isplayingAudio) {
            mediaPlayer.pauseAudio();
            player.setImageResource(R.drawable.play);
        } else {
            mediaPlayer.playAudio();
            player.setImageResource(R.drawable.pause);

        }
    }


    public void releasePlayer() {
        try {
            mediaPlayer.pauseAudio();
            mediaPlayer.stopAudio();

        } catch (java.lang.IllegalStateException e) {

        }

    }
    @Override
    protected void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
        notifica();

    }

    //crea la freccetta in alto a sinistra per andare indietro

    @Override
    public boolean onSupportNavigateUp() {
        releasePlayer();
        onBackPressed();


        return true;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "player";
            String description = "DESCRIZIONE";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notifica() {

        createNotificationChannel();


// Create an explicit intent for an Activity in your app
        Intent intent2 = new Intent(this, MediaPlayerRec.class);
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Media Player")
                .setContentText(c[intNum].nome)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.play,"play",p);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);



// notificationId is a unique int for each notification that you must define
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(CHANNEL_INT, builder.build());




    }





}