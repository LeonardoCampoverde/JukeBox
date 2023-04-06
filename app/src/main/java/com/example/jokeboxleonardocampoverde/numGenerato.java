package com.example.jokeboxleonardocampoverde;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.Timer;
import java.util.TimerTask;


public class numGenerato extends AppCompatActivity {


    SeekBar s;

    //dichiaro l'oggetto Button che mi servirà per aggiungere il listener
    private Button ascolta;


    private MediaPlayer mediaPlayer;
    private boolean riproduzione = false;
    private ImageButton player;
    Timer t= new Timer();

    Canzone[] c = Canzone.init();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_generato);


        player = findViewById(R.id.player);
        s=findViewById(R.id.seekBar);

        //trovo tramite id la textview in cui stamperò il numero ottenuto
        TextView numero = (TextView) findViewById(R.id.num);
        Bundle extras = getIntent().getExtras();
        String num = extras.getString("num"); //ottengo il numero
        if (num != null) {
            numero.setText(num);//lo stampo dentro la textview
        } else {
            numero.setText("OMIODDIO UN ERRORE");
            return;
        }

        //ottengo il valore NUMERICO (int) del numero

        int intNum = getIntent().getIntExtra("intNum", 0);

        //trovo tramite id la textview che in cui ci sarà il nome della canzone
        TextView canzone = (TextView) findViewById(R.id.nome_canzone);
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

        s.setMax(mediaPlayer.getDuration());

        t.scheduleAtFixedRate(new TimerTask() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void run() {
                new AsyncTask() {
                    @Override
                    protected Integer doInBackground(Object[] objects) {
                        if(riproduzione) {
                            int percent;
                            int position = mediaPlayer.getCurrentPosition();
                            int timeRunning = mediaPlayer.getDuration();
                            if (timeRunning == 0 || position == 0) {
                                percent = 0;
                            } else {
                                percent = (position * 100) / timeRunning;
                            }

                            return percent;

                        }
                        return -1;
                    }

                    protected void onPostExecute(Integer result) {
                        if(result  != -1){
                            s.setProgress(result);}

                    }

                }.execute();
            }
        },0,1000);

        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ImageView im = findViewById(R.id.imageView);
        im.setImageResource(c[intNum].img);


    }




    public void ascoltaMusica() {
        int intNum = getIntent().getIntExtra("intNum", 0);
        //creo una nuova activity e apro il link della canzone
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(c[intNum].link));
        //avvio l'activity
        startActivity(intent);
    }

    public void riproduci(View view) {

        if (riproduzione) {
            mediaPlayer.pause();
            riproduzione = false;
            player.setImageResource(R.drawable.play);
        } else {
            mediaPlayer.start();
            riproduzione = true;
            player.setImageResource(R.drawable.pause);

        }
    }


    public void releasePlayer() {
        try {
            t.cancel();
            mediaPlayer.pause();
            mediaPlayer.stop();
            mediaPlayer.release();
        } catch (java.lang.IllegalStateException e) {

        }

    }

    @Override
    protected void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }

    //crea la freccetta in alto a sinistra per andare indietro

    @Override
    public boolean onSupportNavigateUp() {
        releasePlayer();
        onBackPressed();
        return true;
    }


    public void notifica(View v) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "notifica")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("LA NOTIFICA PIU FIGA")
                .setContentText("OMIODDIO UNA NOTIFICA")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("notifica", "nome", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("descrizione");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationManagerCompat nnotificationManager = NotificationManagerCompat.from(this);

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
        nnotificationManager.notify(1, builder.build());


    }





}