package com.example.bruno.prototipo11;

import android.app.Activity;
import android.content.Intent;
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

        String receivedAction = getIntent().getAction();

        if (receivedAction != null){

            if (receivedAction.equals(this.getString(R.string.ACTION_INICIAR_CALIBRACAO))){
                String data = getIntent().getStringExtra(Intent.EXTRA_TEXT);
                tv.setText(data);
            }

        }
        else{
            tv.setText("ERRO");
        }


    }
}
