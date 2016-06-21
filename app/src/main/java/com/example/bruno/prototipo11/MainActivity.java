package com.example.bruno.prototipo11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoprecalibracao(View view){
        Intent intent = new Intent(this, DadosActivity.class);
        startActivity(intent);
    }

    public void teste(View view) {
        Intent intent = new Intent(this, OuvidoDireitoActivity.class);
        startActivity(intent);
    }
}


//testando