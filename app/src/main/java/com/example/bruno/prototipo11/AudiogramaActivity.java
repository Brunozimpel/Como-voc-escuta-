package com.example.bruno.prototipo11;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Guilherme on 23/06/16.
 */
public class AudiogramaActivity extends AppCompatActivity {

    private String quinhentos, umk, doisk, quatrok, oitok, quinhentosEsq,umkEsq,doiskEsq,quatrokEsq,oitokEsq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiograma);

        Intent i = this.getIntent();

        quinhentos = i.getStringExtra("result0");
        umk = i.getStringExtra("result1");
        doisk = i.getStringExtra("result2");
        quatrok = i.getStringExtra("result4");
        oitok = i.getStringExtra("result8");

        quinhentosEsq=i.getStringExtra("result00");
        umkEsq=i.getStringExtra("result10");
        doiskEsq=i.getStringExtra("result20");
        quatrokEsq=i.getStringExtra("result40");
        oitokEsq=i.getStringExtra("result80");

        int x = Integer.parseInt(quinhentos);
        int y = Integer.parseInt(umk);
        int z = Integer.parseInt(doisk);
        int s = Integer.parseInt(quatrok);
        int t = Integer.parseInt(oitok);

        int x2 = Integer.parseInt(quinhentosEsq);
        int y2 = Integer.parseInt(umkEsq);
        int z2 = Integer.parseInt(doiskEsq);
        int s2 = Integer.parseInt(quatrokEsq);
        int t2 = Integer.parseInt(oitokEsq);



        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(500, x),
                new DataPoint(1000, y),
                new DataPoint(2000, z),
                new DataPoint(4000, s),
                new DataPoint(8000, t)
        });
        graph.addSeries(series);


        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(500, x2),
                new DataPoint(1000, y2),
                new DataPoint(2000, z2),
                new DataPoint(4000, s2),
                new DataPoint(8000, t2)
        });
        graph.addSeries(series2);
        series2.setColor(Color.RED);

        series.setTitle("Direito");
        series2.setTitle("Esquerdo");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

    }
}
