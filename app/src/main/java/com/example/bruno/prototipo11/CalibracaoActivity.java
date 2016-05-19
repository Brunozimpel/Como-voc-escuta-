package com.example.bruno.prototipo11;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.io.IOException;


/**
 * Created by bruno on 18/05/2016.
 */
public class CalibracaoActivity extends AppCompatActivity {

    int soundID_1kHz;

    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributesBuilder;

    AudioManager audioManager;
    float curVolume, maxVolume, volume;

    private static final String LOG_TAG = "Calibracao";
    private static String mFileName = null;
    private MediaRecorder mRecorder = true; //comecar já gravando

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibracao);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        createSoundPool();
        loadSounds();
        volumeSounds();

    }

    @Override
    protected void onPause() {
       super.onPause();
       soundPool.release();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
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









}


