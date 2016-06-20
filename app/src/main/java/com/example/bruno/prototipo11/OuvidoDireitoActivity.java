package com.example.bruno.prototipo11;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Created by Guilherme on 18/05/16.
 */
public class OuvidoDireitoActivity extends Activity {

    Thread t;
    boolean isRunning = true;
    int flagOuviu = 0;
    int duration = 3; //segundos
    int sr = 8000;
    int buffsize = duration * sr;
    int dB = 5;
    String valor1000Hz;

    public int randomTime(){
        int time;
        double num = Math.random();
        if (num < 0.2){
            time = 2000;
        } else if (num < 0.4 && num >= 0.2){
            time = 2500;
        }else if(num < 0.6 && num >= 0.4){
            time = 3000;
        }else if (num < 0.8 && num >= 0.6) {
            time = 3500;
        }else {
            time = 4000;
        }
        return time;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ouvidodireito);
        t = new Thread() {
            public void run() {
                // set process priority
                setPriority(Thread.MAX_PRIORITY);
                // create an audiotrack object
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                        sr, AudioFormat.CHANNEL_OUT_MONO,
                        AudioFormat.ENCODING_PCM_16BIT, buffsize,
                        AudioTrack.MODE_STREAM);
                audioTrack.setStereoVolume(0,1);

                short samples[] = new short[buffsize];
                int amp = 7000;
                double twopi = 8.*Math.atan(1.);
                double fr = 440.f;
                double ph = 0.0;

                // start audio
                audioTrack.play();

                // synthesis loop
                while (isRunning) {
                    if (amp <= 0) {
                        isRunning = false;
                        valor1000Hz = "-10dB";
                        mandarResultado();
                    }else if(amp >= 11000){
                        isRunning = false;
                        valor1000Hz = "80dB";
                        mandarResultado();
                    }
                    for (int i = 0; i < buffsize; i++) {
                        samples[i] = (short) (amp * Math.sin(ph));
                        ph += twopi * fr / sr;
                        if(flagOuviu != 0){
                            i = buffsize;
                        }
                    }
                    audioTrack.write(samples, 0, buffsize);

                    try {
                        Thread.sleep(randomTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (flagOuviu != 0) {
                        flagOuviu = 0;
                        amp -= 1000;
                    }else {
                        amp += 1000;
                    }

                }

                audioTrack.stop();
                audioTrack.release();
            }
        };
        t.start();
    }


    public void onPause(){
        super.onPause();
        isRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }

    public void onDestroy(){
        super.onDestroy();
        isRunning = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t = null;
    }

    public void ouviu(View view) {
        flagOuviu = 1;
    }

    private void mandarResultado(){


    }


}



