package com.example.bruno.prototipo11;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/**
 * Created by Guilherme on 18/05/16.
 */
public class PrimeiroTesteActivity extends Activity {

    int flagOuviu, soundID_1kHz;
    float curVolume, maxVolume, volume, testVolume;
//
//    public int randomTime(){
//        int time;
//        double num = Math.random();
//        if (num < 0.3){
//            time = 2000;
//        } else if (num < 0.67 && num >= 0.3){
//            time = 2500;
//        }else{
//            time = 3000;
//        };
//        return time;
//    }
//
    SoundPool soundPool;
    SoundPool.Builder soundPoolBuilder;

    AudioAttributes attributes;
    AudioAttributes.Builder attributesBuilder;

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiroteste);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        createSoundPool();
        loadSounds();
        volumeSounds();
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
        testVolume = curVolume / maxVolume;
        volume = testVolume/2;
    }

    public void direito(View view) { //teste iniciado, começaremos pela orelha direita automaticamente
        try {Thread.sleep(1 * 1000);
        //try {Thread.sleep(randomTime());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        soundPool.play(soundID_1kHz,0,volume,0,0,1); //50dbNA

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (flagOuviu != 0) { //ouviu 50dbNA
                    flagOuviu = 0;
                    soundPool.play(soundID_1kHz, 0, volume /1.25F, 0, 0, 1); //40dbNA
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (flagOuviu != 0) { //ouviu 40dbNA
                                flagOuviu = 0;
                                soundPool.play(soundID_1kHz, 0, volume /1.67F, 0, 0, 1); //30dbNA
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flagOuviu != 0) { //ouviu 30dbNA
                                            flagOuviu = 0;
                                            soundPool.play(soundID_1kHz, 0, volume / 2.5F, 0, 0, 1); //20dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 20dbNA
                                                        flagOuviu = 0;
                                                        soundPool.play(soundID_1kHz, 0, volume / 5, 0, 0, 1); //10dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 10dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else{ //n ouviu 10dbNA
                                                                    soundPool.play(soundID_1kHz,0,volume/2.5F,0,0,1); //20 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else{ //n ouviu 20dbNA
                                                                                soundPool.play(soundID_1kHz,0,volume/1.67F,0,0,1); //30 dbNA
                                                                                handler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                            flagOuviu = 0;
                                                                                            mostrarDados();
                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                        }
                                                                                        else{ //n ouviu 30dbNA
                                                                                            soundPool.play(soundID_1kHz,0,volume/1.25F,0,0,1); //40 dbNA
                                                                                            handler.postDelayed(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                                        flagOuviu = 0;
                                                                                                        mostrarDados();
                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                    }
                                                                                                    else{ //n ouviu 40dbNA
                                                                                                        soundPool.play(soundID_1kHz,0,volume,0,0,1); //50 dbNA
                                                                                                        handler.postDelayed(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                if (flagOuviu != 0) { //ouviu 50dbNA
                                                                                                                    flagOuviu = 0;
                                                                                                                    mostrarDados();
                                                                                                                    // COLOCAR NO BANCO DE DADOS
                                                                                                                }
                                                                                                                else {
                                                                                                                    soundPool.play(soundID_1kHz, 0, volume * 1.2F, 0, 0, 1); //60 dbNA
                                                                                                                    handler.postDelayed(new Runnable() {
                                                                                                                        @Override
                                                                                                                        public void run() {
                                                                                                                            if (flagOuviu != 0) { //ouviu 60dbNA
                                                                                                                                flagOuviu = 0;
                                                                                                                                mostrarDados();
                                                                                                                                // COLOCAR NO BANCO DE DADOS
                                                                                                                            }
                                                                                                                            else {
                                                                                                                                soundPool.play(soundID_1kHz, 0, volume * 1.4F, 0, 0, 1); //70 dbNA
                                                                                                                                handler.postDelayed(new Runnable() {
                                                                                                                                    @Override
                                                                                                                                    public void run() {
                                                                                                                                        if (flagOuviu != 0) { //ouviu 70dbNA
                                                                                                                                            flagOuviu = 0;
                                                                                                                                            mostrarDados();
                                                                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                                                                        }
                                                                                                                                        else {
                                                                                                                                            soundPool.play(soundID_1kHz, 0, volume * 1.6F, 0, 0, 1); //80 dbNA
                                                                                                                                            handler.postDelayed(new Runnable() {
                                                                                                                                                @Override
                                                                                                                                                public void run() {
                                                                                                                                                    if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                                                                                        flagOuviu = 0;
                                                                                                                                                        mostrarDados();
                                                                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                                                                    }
                                                                                                                                                    else {
                                                                                                                                                        perdaAudicao();
                                                                                                                                                        //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }, 5 * 1000);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }, 5 * 1000);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }, 5 * 1000);
                                                                                                                }
                                                                                                            }
                                                                                                        }, 5 * 1000);
                                                                                                    }
                                                                                                }
                                                                                            }, 5 *1000);
                                                                                        }
                                                                                    }
                                                                                }, 5 * 1000);
                                                                            }
                                                                        }
                                                                    }, 5 * 1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                    else{ //n ouviu 20dbNA
                                                        soundPool.play(soundID_1kHz,0,volume/1.67F,0,0,1); //30 dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 40dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else{ //n ouviu 30dbNA
                                                                    soundPool.play(soundID_1kHz,0,volume/1.25F,0,0,1); //40 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else{ //n ouviu 40dbNA
                                                                                soundPool.play(soundID_1kHz,0,volume,0,0,1); //50 dbNA
                                                                                handler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (flagOuviu != 0) { //ouviu 50dbNA
                                                                                            flagOuviu = 0;
                                                                                            mostrarDados();
                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                        }
                                                                                        else {
                                                                                            soundPool.play(soundID_1kHz, 0, volume * 1.2F, 0, 0, 1); //60 dbNA
                                                                                            handler.postDelayed(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (flagOuviu != 0) { //ouviu 60dbNA
                                                                                                        flagOuviu = 0;
                                                                                                        mostrarDados();
                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                    }
                                                                                                    else {
                                                                                                        soundPool.play(soundID_1kHz, 0, volume * 1.4F, 0, 0, 1); //70 dbNA
                                                                                                        handler.postDelayed(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                if (flagOuviu != 0) { //ouviu 70dbNA
                                                                                                                    flagOuviu = 0;
                                                                                                                    mostrarDados();
                                                                                                                    // COLOCAR NO BANCO DE DADOS
                                                                                                                }
                                                                                                                else {
                                                                                                                    soundPool.play(soundID_1kHz, 0, volume * 1.6F, 0, 0, 1); //80 dbNA
                                                                                                                    handler.postDelayed(new Runnable() {
                                                                                                                        @Override
                                                                                                                        public void run() {
                                                                                                                            if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                                                                flagOuviu = 0;
                                                                                                                                mostrarDados();
                                                                                                                                // COLOCAR NO BANCO DE DADOS
                                                                                                                            }
                                                                                                                            else {
                                                                                                                                perdaAudicao();
                                                                                                                                //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }, 5 * 1000);
                                                                                                                }
                                                                                                            }
                                                                                                        }, 5 * 1000);
                                                                                                    }
                                                                                                }
                                                                                            }, 5 * 1000);
                                                                                        }
                                                                                    }
                                                                                }, 5 * 1000);
                                                                            }
                                                                        }
                                                                    }, 5 *1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                }
                                            }, 5 * 1000);
                                        }
                                        else{ //n ouviu 30dbNA
                                            soundPool.play(soundID_1kHz,0,volume/1.25F,0,0,1); //40 dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 40dbNA
                                                        flagOuviu = 0;
                                                        mostrarDados();
                                                        // COLOCAR NO BANCO DE DADOS
                                                    }
                                                    else{ //n ouviu 40dbNA
                                                        soundPool.play(soundID_1kHz,0,volume,0,0,1); //50 dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 50dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else {
                                                                    soundPool.play(soundID_1kHz, 0, volume * 1.2F, 0, 0, 1); //60 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 60dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else {
                                                                                soundPool.play(soundID_1kHz, 0, volume * 1.4F, 0, 0, 1); //70 dbNA
                                                                                handler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (flagOuviu != 0) { //ouviu 70dbNA
                                                                                            flagOuviu = 0;
                                                                                            mostrarDados();
                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                        }
                                                                                        else {
                                                                                            soundPool.play(soundID_1kHz, 0, volume * 1.6F, 0, 0, 1); //80 dbNA
                                                                                            handler.postDelayed(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                                        flagOuviu = 0;
                                                                                                        mostrarDados();
                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                    }
                                                                                                    else {
                                                                                                        perdaAudicao();
                                                                                                        //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                                                    }
                                                                                                }
                                                                                            }, 5 * 1000);
                                                                                        }
                                                                                    }
                                                                                }, 5 * 1000);
                                                                            }
                                                                        }
                                                                    }, 5 * 1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                }
                                            }, 5 *1000);
                                        }
                                    }
                                }, 5 *1000);
                            }
                            else{ //n ouviu 40dbNA
                                soundPool.play(soundID_1kHz,0,volume,0,0,1); //50 dbNA
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flagOuviu != 0) { //ouviu 50dbNA
                                            flagOuviu = 0;
                                            mostrarDados();
                                            // COLOCAR NO BANCO DE DADOS
                                        }
                                        else {
                                            soundPool.play(soundID_1kHz, 0, volume * 1.2F, 0, 0, 1); //60 dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 60dbNA
                                                        flagOuviu = 0;
                                                        mostrarDados();
                                                        // COLOCAR NO BANCO DE DADOS
                                                    }
                                                    else {
                                                        soundPool.play(soundID_1kHz, 0, volume * 1.4F, 0, 0, 1); //70 dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 70dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else {
                                                                    soundPool.play(soundID_1kHz, 0, volume * 1.6F, 0, 0, 1); //80 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else {
                                                                                perdaAudicao();
                                                                                //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                            }
                                                                        }
                                                                    }, 5 * 1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                }
                                            }, 5 * 1000);
                                        }
                                    }
                                }, 5 * 1000);
                            }
                        }
                    }, 5 * 1000);
                }
                else{ //n ouviu 50dbNA
                    soundPool.play(soundID_1kHz,0,volume*1.2F,0,0,1); //60 dbNA
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (flagOuviu != 0) { //ouviu 60dbNA
                                flagOuviu = 0;
                                mostrarDados();
                                // COLOCAR NO BANCO DE DADOS
                            }
                            else {
                                soundPool.play(soundID_1kHz,0,volume*1.4F,0,0,1); //70 dbNA
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flagOuviu != 0) { //ouviu 70dbNA
                                            flagOuviu = 0;
                                            mostrarDados();
                                            // COLOCAR NO BANCO DE DADOS
                                        }
                                        else {
                                            soundPool.play(soundID_1kHz,0,volume*1.6F,0,0,1); //80 dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 80dbNA
                                                        flagOuviu = 0;
                                                        mostrarDados();
                                                        // COLOCAR NO BANCO DE DADOS
                                                    }
                                                    else {
                                                        perdaAudicao();
                                                        //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                    }
                                                }
                                            },5*1000);
                                        }
                                    }
                                },5*1000);
                            }
                        }
                    },5*1000);
                }
            }
        }, 5 * 1000);
    }

    public void esquerdo(View view) {
        try {Thread.sleep(1 * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        soundPool.play(soundID_1kHz,volume,0,0,0,1); //50dbNA

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (flagOuviu != 0) { //ouviu 50dbNA
                    flagOuviu = 0;
                    soundPool.play(soundID_1kHz, volume /1.25F,0, 0, 0, 1); //40dbNA
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (flagOuviu != 0) { //ouviu 40dbNA
                                flagOuviu = 0;
                                soundPool.play(soundID_1kHz, volume /1.67F,0, 0, 0, 1); //30dbNA
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flagOuviu != 0) { //ouviu 30dbNA
                                            flagOuviu = 0;
                                            soundPool.play(soundID_1kHz, volume / 2.5F,0, 0, 0, 1); //20dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 20dbNA
                                                        flagOuviu = 0;
                                                        soundPool.play(soundID_1kHz, volume / 5,0, 0, 0, 1); //10dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 10dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else{ //n ouviu 10dbNA
                                                                    soundPool.play(soundID_1kHz,volume/2.5F,0,0,0,1); //20 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else{ //n ouviu 20dbNA
                                                                                soundPool.play(soundID_1kHz,volume/1.67F,0,0,0,1); //30 dbNA
                                                                                handler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                            flagOuviu = 0;
                                                                                            mostrarDados();
                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                        }
                                                                                        else{ //n ouviu 30dbNA
                                                                                            soundPool.play(soundID_1kHz,volume/1.25F,0,0,0,1); //40 dbNA
                                                                                            handler.postDelayed(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                                        flagOuviu = 0;
                                                                                                        mostrarDados();
                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                    }
                                                                                                    else{ //n ouviu 40dbNA
                                                                                                        soundPool.play(soundID_1kHz,volume,0,0,0,1); //50 dbNA
                                                                                                        handler.postDelayed(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                if (flagOuviu != 0) { //ouviu 50dbNA
                                                                                                                    flagOuviu = 0;
                                                                                                                    mostrarDados();
                                                                                                                    // COLOCAR NO BANCO DE DADOS
                                                                                                                }
                                                                                                                else {
                                                                                                                    soundPool.play(soundID_1kHz, volume * 1.2F,0, 0, 0, 1); //60 dbNA
                                                                                                                    handler.postDelayed(new Runnable() {
                                                                                                                        @Override
                                                                                                                        public void run() {
                                                                                                                            if (flagOuviu != 0) { //ouviu 60dbNA
                                                                                                                                flagOuviu = 0;
                                                                                                                                mostrarDados();
                                                                                                                                // COLOCAR NO BANCO DE DADOS
                                                                                                                            }
                                                                                                                            else {
                                                                                                                                soundPool.play(soundID_1kHz,  volume * 1.4F,0, 0, 0, 1); //70 dbNA
                                                                                                                                handler.postDelayed(new Runnable() {
                                                                                                                                    @Override
                                                                                                                                    public void run() {
                                                                                                                                        if (flagOuviu != 0) { //ouviu 70dbNA
                                                                                                                                            flagOuviu = 0;
                                                                                                                                            mostrarDados();
                                                                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                                                                        }
                                                                                                                                        else {
                                                                                                                                            soundPool.play(soundID_1kHz,  volume * 1.6F,0, 0, 0, 1); //80 dbNA
                                                                                                                                            handler.postDelayed(new Runnable() {
                                                                                                                                                @Override
                                                                                                                                                public void run() {
                                                                                                                                                    if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                                                                                        flagOuviu = 0;
                                                                                                                                                        mostrarDados();
                                                                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                                                                    }
                                                                                                                                                    else {
                                                                                                                                                        perdaAudicao();
                                                                                                                                                        //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }, 5 * 1000);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }, 5 * 1000);
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }, 5 * 1000);
                                                                                                                }
                                                                                                            }
                                                                                                        }, 5 * 1000);
                                                                                                    }
                                                                                                }
                                                                                            }, 5 *1000);
                                                                                        }
                                                                                    }
                                                                                }, 5 * 1000);
                                                                            }
                                                                        }
                                                                    }, 5 * 1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                    else{ //n ouviu 20dbNA
                                                        soundPool.play(soundID_1kHz,volume/1.67F,0,0,0,1); //30 dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 40dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else{ //n ouviu 30dbNA
                                                                    soundPool.play(soundID_1kHz,volume/1.25F,0,0,0,1); //40 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 40dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else{ //n ouviu 40dbNA
                                                                                soundPool.play(soundID_1kHz,volume,0,0,0,1); //50 dbNA
                                                                                handler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (flagOuviu != 0) { //ouviu 50dbNA
                                                                                            flagOuviu = 0;
                                                                                            mostrarDados();
                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                        }
                                                                                        else {
                                                                                            soundPool.play(soundID_1kHz, volume * 1.2F,0, 0, 0, 1); //60 dbNA
                                                                                            handler.postDelayed(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (flagOuviu != 0) { //ouviu 60dbNA
                                                                                                        flagOuviu = 0;
                                                                                                        mostrarDados();
                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                    }
                                                                                                    else {
                                                                                                        soundPool.play(soundID_1kHz, volume * 1.4F,0, 0, 0, 1); //70 dbNA
                                                                                                        handler.postDelayed(new Runnable() {
                                                                                                            @Override
                                                                                                            public void run() {
                                                                                                                if (flagOuviu != 0) { //ouviu 70dbNA
                                                                                                                    flagOuviu = 0;
                                                                                                                    mostrarDados();
                                                                                                                    // COLOCAR NO BANCO DE DADOS
                                                                                                                }
                                                                                                                else {
                                                                                                                    soundPool.play(soundID_1kHz, volume * 1.6F,0, 0, 0, 1); //80 dbNA
                                                                                                                    handler.postDelayed(new Runnable() {
                                                                                                                        @Override
                                                                                                                        public void run() {
                                                                                                                            if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                                                                flagOuviu = 0;
                                                                                                                                mostrarDados();
                                                                                                                                // COLOCAR NO BANCO DE DADOS
                                                                                                                            }
                                                                                                                            else {
                                                                                                                                perdaAudicao();
                                                                                                                                //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }, 5 * 1000);
                                                                                                                }
                                                                                                            }
                                                                                                        }, 5 * 1000);
                                                                                                    }
                                                                                                }
                                                                                            }, 5 * 1000);
                                                                                        }
                                                                                    }
                                                                                }, 5 * 1000);
                                                                            }
                                                                        }
                                                                    }, 5 *1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                }
                                            }, 5 * 1000);
                                        }
                                        else{ //n ouviu 30dbNA
                                            soundPool.play(soundID_1kHz,volume/1.25F,0,0,0,1); //40 dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 40dbNA
                                                        flagOuviu = 0;
                                                        mostrarDados();
                                                        // COLOCAR NO BANCO DE DADOS
                                                    }
                                                    else{ //n ouviu 40dbNA
                                                        soundPool.play(soundID_1kHz,volume,0,0,0,1); //50 dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 50dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else {
                                                                    soundPool.play(soundID_1kHz,  volume * 1.2F,0, 0, 0, 1); //60 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 60dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else {
                                                                                soundPool.play(soundID_1kHz,  volume * 1.4F,0, 0, 0, 1); //70 dbNA
                                                                                handler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        if (flagOuviu != 0) { //ouviu 70dbNA
                                                                                            flagOuviu = 0;
                                                                                            mostrarDados();
                                                                                            // COLOCAR NO BANCO DE DADOS
                                                                                        }
                                                                                        else {
                                                                                            soundPool.play(soundID_1kHz,  volume * 1.6F,0, 0, 0, 1); //80 dbNA
                                                                                            handler.postDelayed(new Runnable() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                                        flagOuviu = 0;
                                                                                                        mostrarDados();
                                                                                                        // COLOCAR NO BANCO DE DADOS
                                                                                                    }
                                                                                                    else {
                                                                                                        perdaAudicao();
                                                                                                        //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                                                    }
                                                                                                }
                                                                                            }, 5 * 1000);
                                                                                        }
                                                                                    }
                                                                                }, 5 * 1000);
                                                                            }
                                                                        }
                                                                    }, 5 * 1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                }
                                            }, 5 *1000);
                                        }
                                    }
                                }, 5 *1000);
                            }
                            else{ //n ouviu 40dbNA
                                soundPool.play(soundID_1kHz,volume,0,0,0,1); //50 dbNA
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flagOuviu != 0) { //ouviu 50dbNA
                                            flagOuviu = 0;
                                            mostrarDados();
                                            // COLOCAR NO BANCO DE DADOS
                                        }
                                        else {
                                            soundPool.play(soundID_1kHz,  volume * 1.2F,0, 0, 0, 1); //60 dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 60dbNA
                                                        flagOuviu = 0;
                                                        mostrarDados();
                                                        // COLOCAR NO BANCO DE DADOS
                                                    }
                                                    else {
                                                        soundPool.play(soundID_1kHz, volume * 1.4F,0, 0, 0, 1); //70 dbNA
                                                        handler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if (flagOuviu != 0) { //ouviu 70dbNA
                                                                    flagOuviu = 0;
                                                                    mostrarDados();
                                                                    // COLOCAR NO BANCO DE DADOS
                                                                }
                                                                else {
                                                                    soundPool.play(soundID_1kHz, volume * 1.6F,0, 0, 0, 1); //80 dbNA
                                                                    handler.postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (flagOuviu != 0) { //ouviu 80dbNA
                                                                                flagOuviu = 0;
                                                                                mostrarDados();
                                                                                // COLOCAR NO BANCO DE DADOS
                                                                            }
                                                                            else {
                                                                                perdaAudicao();
                                                                                //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                                            }
                                                                        }
                                                                    }, 5 * 1000);
                                                                }
                                                            }
                                                        }, 5 * 1000);
                                                    }
                                                }
                                            }, 5 * 1000);
                                        }
                                    }
                                }, 5 * 1000);
                            }
                        }
                    }, 5 * 1000);
                }
                else{ //n ouviu 50dbNA
                    soundPool.play(soundID_1kHz,volume*1.2F,0,0,0,1); //60 dbNA
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (flagOuviu != 0) { //ouviu 60dbNA
                                flagOuviu = 0;
                                mostrarDados();
                                // COLOCAR NO BANCO DE DADOS
                            }
                            else {
                                soundPool.play(soundID_1kHz,volume*1.4F,0,0,0,1); //70 dbNA
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flagOuviu != 0) { //ouviu 70dbNA
                                            flagOuviu = 0;
                                            mostrarDados();
                                            // COLOCAR NO BANCO DE DADOS
                                        }
                                        else {
                                            soundPool.play(soundID_1kHz,volume*1.6F,0,0,0,1); //80 dbNA
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flagOuviu != 0) { //ouviu 80dbNA
                                                        flagOuviu = 0;
                                                        mostrarDados();
                                                        // COLOCAR NO BANCO DE DADOS
                                                    }
                                                    else {
                                                        perdaAudicao();
                                                        //PERDA DE AUDIÇAO SUPERIOR AO SUPORTADO PELO APP
                                                    }
                                                }
                                            },5*1000);
                                        }
                                    }
                                },5*1000);
                            }
                        }
                    },5*1000);
                }
            }
        }, 5 * 1000);
    }

    public void ouviu(View view) {
        flagOuviu = 1;
    }

    protected void mostrarDados(){
        Intent intent = new Intent(this, Fim3Activity.class);
        startActivity(intent);
        finish();
    }

    protected void perdaAudicao(){
        Intent intent = new Intent(this, Fim2Activity.class);
        startActivity(intent);
        finish();
    }


}

