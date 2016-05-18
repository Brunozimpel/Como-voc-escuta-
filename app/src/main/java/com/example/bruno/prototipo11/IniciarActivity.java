package com.example.bruno.prototipo11;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Guilherme on 18/05/16.
 */

// Activity para Iniciar a Calibração ou o Teste
public class IniciarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        TextView tv = (TextView)findViewById(R.id.txt_iniciar);


    }
}
