package com.example.bruno.prototipo11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Guilherme on 19/05/16.
 */
public class Fim3Activity extends Activity {

    TextView result500,result1k,result2k,result4k,result8k;

    private String quinhentos,umk,doisk,tresk,quatrok,seisk,oitok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim3);
        result500 = (TextView) findViewById(R.id.quinhentos);
        result1k = (TextView) findViewById(R.id.umKHz);
        result2k = (TextView) findViewById(R.id.doisKHz);
        result4k = (TextView) findViewById(R.id.quatroKHz);
        result8k = (TextView) findViewById(R.id.oitoKHz);



        Intent i=this.getIntent();

        quinhentos=i.getStringExtra("result0");
        umk=i.getStringExtra("result1");
        doisk=i.getStringExtra("result2");
        quatrok=i.getStringExtra("result4");
        oitok=i.getStringExtra("result8");

        result500.setText("500Hz:"+quinhentos);
        result1k.setText("1KHz:"+umk);
        result2k.setText("2KHz:"+umk);
        result4k.setText("4KHz:"+umk);
        result8k.setText("8KHz:"+umk);
       }

    }


//q