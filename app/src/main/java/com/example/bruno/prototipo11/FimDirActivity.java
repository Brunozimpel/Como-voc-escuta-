package com.example.bruno.prototipo11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Guilherme on 19/05/16.
 */
public class FimDirActivity extends AppCompatActivity {

    TextView result250, result500, result1k, result2k, result3k, result4k, result6k, result8k;

    private String duzentos,quinhentos, umk, doisk, tresk, quatrok, seisk, oitok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim3);

        result250 = (TextView) findViewById(R.id.duzentos);
        result500 = (TextView) findViewById(R.id.quinhentos);
        result1k = (TextView) findViewById(R.id.umKHz);
        result2k = (TextView) findViewById(R.id.doisKHz);
        result3k = (TextView) findViewById(R.id.tresKHz);
        result4k = (TextView) findViewById(R.id.quatroKHz);
        result6k = (TextView) findViewById(R.id.seisKHz);
        result8k = (TextView) findViewById(R.id.oitoKHz);


        Intent i = this.getIntent();

        duzentos = i.getStringExtra("result25");
        quinhentos = i.getStringExtra("result0");
        umk = i.getStringExtra("result1");
        doisk = i.getStringExtra("result2");
        tresk = i.getStringExtra("result3");
        quatrok = i.getStringExtra("result4");
        seisk = i.getStringExtra("result6");
        oitok = i.getStringExtra("result8");

        result250.setText("250Hz:" + duzentos + "dB");
        result500.setText("500Hz:" + quinhentos + "dB");
        result1k.setText("1KHz:" + umk + "dB");
        result2k.setText("2KHz:" + doisk + "dB");
        result3k.setText("3KHz:" + tresk + "dB");
        result4k.setText("4KHz:" + quatrok + "dB");
        result6k.setText("6KHz:" + seisk + "dB");
        result8k.setText("8KHz:" + oitok + "dB");


    }

    public void testeEsquerdo(View view) {

        String result25 = getIntent().getExtras().getString("result25");
        String result0 = getIntent().getExtras().getString("result0");
        String result1 = getIntent().getExtras().getString("result1");
        String result2 = getIntent().getExtras().getString("result2");
        String result3 = getIntent().getExtras().getString("result3");
        String result4 = getIntent().getExtras().getString("result4");
        String result6 = getIntent().getExtras().getString("result6");
        String result8 = getIntent().getExtras().getString("result8");

        Intent intent = new Intent(this, OuvidoEsquerdo1kActivity.class);

        intent.putExtra("result25",result25);
        intent.putExtra("result0", result0);
        intent.putExtra("result1", result1);
        intent.putExtra("result2", result2);
        intent.putExtra("result3", result3);
        intent.putExtra("result4", result4);
        intent.putExtra("result6", result6);
        intent.putExtra("result8", result8);

        startActivity(intent);
    }
}
//q