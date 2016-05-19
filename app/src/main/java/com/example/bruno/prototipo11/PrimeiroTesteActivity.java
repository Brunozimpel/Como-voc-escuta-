package com.example.bruno.prototipo11;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Guilherme on 18/05/16.
 */
public class PrimeiroTesteActivity extends Activity {

    int soundID_1kHz;

    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributesBuilder;

    AudioManager audioManager;
    float curVolume, maxVolume, volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiroteste);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        createSoundPool();
        loadSounds();
        volumeSounds();
        play();
            }

    private void play() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundPool.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createSoundPool();
        loadSounds();
    }

    protected void createSoundPool(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            attributesBuilder = new AudioAttributes.Builder();
            attributesBuilder.setUsage(AudioAttributes.USAGE_GAME);
            attributesBuilder.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
            attributes = attributesBuilder.build();

            soundPoolBuilder = new SoundPool.Builder();
            soundPoolBuilder.setAudioAttributes(attributes);
            soundPool = soundPoolBuilder.build();
        }
        else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }
    }

    protected void loadSounds() {
        soundID_1kHz = soundPool.load(this,R.raw.som1,1);
    }

    protected void volumeSounds(){
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        curVolume = (float)audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float)audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = curVolume / maxVolume;
    }

    public void play(View view) {
        soundPool.play(soundID_1kHz,1,1,0,0,1);
    }
}

