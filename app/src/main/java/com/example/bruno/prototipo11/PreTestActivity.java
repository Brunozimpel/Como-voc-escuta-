package com.example.bruno.prototipo11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by bruno on 05/07/2016.
 */
public class PreTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretest);
    }


    public void testeok (View view) {
        Intent intent = new Intent(this, OuvidoDireito1kActivity.class);
        startActivity(intent);
    }
}

