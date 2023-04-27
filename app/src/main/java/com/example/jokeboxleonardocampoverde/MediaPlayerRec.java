package com.example.jokeboxleonardocampoverde;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MediaPlayerRec extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        AudioPlay.automaticAudio();
        if(context instanceof MainActivity){
            Log.d("MPDESTROY","CHIAMATA LA FUNZIONE");
            AudioPlay.destroy();
        }




    }
}