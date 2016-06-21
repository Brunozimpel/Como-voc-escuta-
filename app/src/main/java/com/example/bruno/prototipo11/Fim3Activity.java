package com.example.bruno.prototipo11;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Guilherme on 19/05/16.
 */
public class Fim3Activity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim3);
        textView = (TextView) findViewById(R.id.resultado);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            textView.setText(extras.getString("result"));
        }

    }



}
