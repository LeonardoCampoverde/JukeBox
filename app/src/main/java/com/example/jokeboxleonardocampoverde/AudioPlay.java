package com.example.jokeboxleonardocampoverde;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AudioPlay {

    public static MediaPlayer mediaPlayer;
    public static boolean isplayingAudio=false;


    public AudioPlay(Context c,int res) {
        mediaPlayer=MediaPlayer.create(c,res);
    }

    public static void playAudio(){
            isplayingAudio=true;
            mediaPlayer.start();
    }

    public static void pauseAudio(){

            isplayingAudio=false;
            mediaPlayer.pause();

    }
    public static void destroy(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    public static void automaticAudio(){

        if(isplayingAudio){
            isplayingAudio=false;
            mediaPlayer.pause();
        }
        else{
            isplayingAudio=true;
            mediaPlayer.start();
        }

    }

    public static int duration(){
        return mediaPlayer.getDuration();
    }
    public static int currentPosition(){
        return mediaPlayer.getCurrentPosition();
    }

    public static void setSeek(int i){
        mediaPlayer.seekTo(i);
    }

    public static void stopAudio(){
        isplayingAudio=false;
        mediaPlayer.stop();
    }


    public static MediaPlayer getMP(){
        return mediaPlayer;
    }




}
