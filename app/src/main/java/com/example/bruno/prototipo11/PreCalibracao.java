package com.example.bruno.prototipo11;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by bruno on 18/05/2016.
 */
public class PreCalibracao extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precalibracao);
    }

    public void gotocalibracao(View view){
        Intent intent = new Intent(this, CalibracaoActivity.class);
        startActivity(intent);
    }








}
