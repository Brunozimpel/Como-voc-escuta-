package com.example.bruno.prototipo11;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
 * Created by Guilherme on 23/06/16.
 */
public class AudiogramaActivity extends AppCompatActivity {

    private String quinhentos, umk, doisk, quatrok, oitok, quinhentosEsq, umkEsq, doiskEsq, quatrokEsq, oitokEsq;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        quinhentosEsq = i.getStringExtra("result00");
        umkEsq = i.getStringExtra("result10");
        doiskEsq = i.getStringExtra("result20");
        quatrokEsq = i.getStringExtra("result40");
        oitokEsq = i.getStringExtra("result80");

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

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(500, x),
                new DataPoint(1000, y),
                new DataPoint(2000, z),
                new DataPoint(4000, s),
                new DataPoint(8000, t)
        });
        graph.addSeries(series);
        series.setColor(Color.RED);


        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(500, x2),
                new DataPoint(1000, y2),
                new DataPoint(2000, z2),
                new DataPoint(4000, s2),
                new DataPoint(8000, t2)
        });


        graph.addSeries(series2);
        series2.setColor(Color.BLUE);

        PointsGraphSeries<DataPoint> seriesp1 = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(500, x),
                new DataPoint(1000, y),
                new DataPoint(2000, z),
                new DataPoint(4000, s),
                new DataPoint(8000, t)

        });
        seriesp1.setColor(Color.RED);
        seriesp1.setShape(PointsGraphSeries.Shape.POINT);
        graph.addSeries(seriesp1);

        PointsGraphSeries<DataPoint> seriesp2 = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(500, x2),
                new DataPoint(1000, y2),
                new DataPoint(2000, z2),
                new DataPoint(4000, s2),
                new DataPoint(8000, t2)

        });

        seriesp2.setColor(Color.BLUE);
        graph.addSeries(seriesp2);
        seriesp2.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(10);
                canvas.drawLine(x - 20, y - 20, x + 20, y + 20, paint);
                canvas.drawLine(x + 20, y - 20, x - 20, y + 20, paint);
            }
        });


        seriesp1.setTitle("Direito");
        seriesp2.setTitle("Esquerdo");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


    }
}