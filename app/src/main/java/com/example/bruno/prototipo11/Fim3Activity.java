package com.example.bruno.prototipo11;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Guilherme on 19/05/16.
 */
public class Fim3Activity extends Activity {


    Bundle B=getIntent().getExtras();
    if (B!=null){
        String Resultado_dir=B.getString("45 dB no ouvid direito");
    }
    textview.setText(Resultado_dir)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim3);
    }
}
