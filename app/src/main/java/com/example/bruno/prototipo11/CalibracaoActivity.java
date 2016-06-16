package com.example.bruno.prototipo11;


import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;


/**
 * Created by bruno on 18/05/2016.
 */
public class CalibracaoActivity extends AppCompatActivity {
//
//    private MediaRecorder myRecorder;
//    private String outputFile = null;

    private final int duration = 2; // seconds
    private final int sampleRate = 4000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    private final double freqOfTone = 1000; // hz

    private final byte generatedSnd[] = new byte[2 * numSamples];









    Handler handler = new Handler();

//    int getAmplitude = myRecorder.getMaxAmplitude();
//
//    public int resultado(){
//        if (getAmplitude != 0) {
//            return 1;
//        }else {
//            return 0;
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibracao);

//        outputFile = Environment.getExternalStorageDirectory().
//            getAbsolutePath() + "/teste.3gpp";
//
//        myRecorder = new MediaRecorder();
//        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
//        myRecorder.setOutputFile(outputFile);
//
//
//        Intent intent;
//        if(resultado()==1){
//            intent = new Intent(this, PrimeiroTesteActivity.class);
//        }else{
//            intent = new Intent(this, Fim1Activity.class);
//        }

    }

//    void start_gravacao() {
//        try {
//            myRecorder.prepare();
//            myRecorder.start();
//        } catch (IllegalStateException e) {
//            // start:it is called before prepare()
//            // prepare: it is called after start() or before setOutputFormat()
//            e.printStackTrace();
//        } catch (IOException e) {
//            // prepare() fails
//            e.printStackTrace();
//        }
//    }
//
//    void stop_gravacao(){
//
//        try {
//            myRecorder.stop();
//            myRecorder.release();
//            myRecorder  = null;
//        } catch (IllegalStateException e) {
//            //  it is called before start()
//            e.printStackTrace();
//        } catch (RuntimeException e) {
//            // no valid audio/video data has been received
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        // Use a new tread as this can take a while
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                genTone();
                handler.post(new Runnable() {

                    public void run() {
                        playSound();

                    }
                });
            }
        });
        thread.start();
//                start_gravacao();
//                SystemClock.sleep(3000);
//                stop_gravacao();
    }


    void genTone() {
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    void playSound() {
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT, generatedSnd.length,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }

}




