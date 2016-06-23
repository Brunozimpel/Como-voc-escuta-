package com.example.bruno.prototipo11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bruno on 22/06/2016.
 */
public class FimEsqActivity extends AppCompatActivity {

    TextView result500,result1k,result2k,result4k,result8k;

    private String quinhentosEsq,umkEsq,doiskEsq,quatrokEsq,oitokEsq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim3esq);
        result500 = (TextView) findViewById(R.id.quinhentosEsq);
        result1k = (TextView) findViewById(R.id.umKHzEsq);
        result2k = (TextView) findViewById(R.id.doisKHzEsq);
        result4k = (TextView) findViewById(R.id.quatroKHzEsq);
        result8k = (TextView) findViewById(R.id.oitoKHzEsq);



        Intent i=this.getIntent();

        quinhentosEsq=i.getStringExtra("result00");
        umkEsq=i.getStringExtra("result10");
        doiskEsq=i.getStringExtra("result20");
        quatrokEsq=i.getStringExtra("result40");
        oitokEsq=i.getStringExtra("result80");

        result500.setText("500Hz:"+quinhentosEsq + "dB");
        result1k.setText("1KHz:"+umkEsq + "dB");
        result2k.setText("2KHz:"+doiskEsq + "dB");
        result4k.setText("4KHz:"+quatrokEsq + "dB");
        result8k.setText("8KHz:"+oitokEsq + "dB");


    }


    public void audiograma(View view) {

        String result0 = getIntent().getExtras().getString("result0");
        String result1 = getIntent().getExtras().getString("result1");
        String result2 = getIntent().getExtras().getString("result2");
        String result4 = getIntent().getExtras().getString("result4");
        String result8 = getIntent().getExtras().getString("result8");

        String result00 = getIntent().getExtras().getString("result00");
        String result10 = getIntent().getExtras().getString("result10");
        String result20 = getIntent().getExtras().getString("result20");
        String result40 = getIntent().getExtras().getString("result40");
        String result80 = getIntent().getExtras().getString("result80");

        Intent intent = new Intent(this, AudiogramaActivity.class);

        intent.putExtra("result0", result0);
        intent.putExtra("result1", result1);
        intent.putExtra("result2", result2);
        intent.putExtra("result4", result4);
        intent.putExtra("result8", result8);


        intent.putExtra("result00", result00);
        intent.putExtra("result10", result10);
        intent.putExtra("result20", result20);
        intent.putExtra("result40", result40);
        intent.putExtra("result80", result80);

        startActivity(intent);

    }
}
