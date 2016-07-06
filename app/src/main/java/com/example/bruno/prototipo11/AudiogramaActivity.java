package com.example.bruno.prototipo11;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;


/**
 * Created by Guilherme on 23/06/16.
 */
public class AudiogramaActivity extends AppCompatActivity {

    private String duzentos,quinhentos, umk, doisk, tresk, quatrok, seisk, oitok,
            duzentosEsq,quinhentosEsq, umkEsq, doiskEsq,treskEsq, quatrokEsq, seiskEsq, oitokEsq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiograma);

        Intent i = this.getIntent();

        duzentos = i.getStringExtra("result25");
        quinhentos = i.getStringExtra("result0");
        umk = i.getStringExtra("result1");
        doisk = i.getStringExtra("result2");
        tresk = i.getStringExtra("result3");
        quatrok = i.getStringExtra("result4");
        seisk = i.getStringExtra("result6");
        oitok = i.getStringExtra("result8");

        duzentosEsq = i.getStringExtra("result250");
        quinhentosEsq = i.getStringExtra("result00");
        umkEsq = i.getStringExtra("result10");
        doiskEsq = i.getStringExtra("result20");
        treskEsq = i.getStringExtra("result30");
        quatrokEsq = i.getStringExtra("result40");
        seiskEsq = i.getStringExtra("result60");
        oitokEsq = i.getStringExtra("result80");

        int w = -(Integer.parseInt(duzentos));
        int x = -(Integer.parseInt(quinhentos));
        int y = -(Integer.parseInt(umk));
        int z = -(Integer.parseInt(doisk));
        int r = -(Integer.parseInt(tresk));
        int s = -(Integer.parseInt(quatrok));
        int k = -(Integer.parseInt(seisk));
        int t = -(Integer.parseInt(oitok));

        int w2 = -(Integer.parseInt(duzentosEsq));
        int x2 = -(Integer.parseInt(quinhentosEsq));
        int y2 = -(Integer.parseInt(umkEsq));
        int z2 = -(Integer.parseInt(doiskEsq));
        int r2 = -(Integer.parseInt(treskEsq));
        int s2 = -(Integer.parseInt(quatrokEsq));
        int k2 = -(Integer.parseInt(seisk));
        int t2 = -(Integer.parseInt(oitokEsq));



        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.setTitle("AUDIOGRAMA");

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2000,w),
                new DataPoint(4000, x),
                new DataPoint(6000, y),
                new DataPoint(8000, z),
                new DataPoint(9000, r),
                new DataPoint(10000, s),
                new DataPoint(11000, k),
                new DataPoint(12000, t)

        });
        series.setColor(Color.RED);
        series.setThickness(4);
        graph.addSeries(series);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2000,w2),
                new DataPoint(4000, x2),
                new DataPoint(6000, y2),
                new DataPoint(8000, z2),
                new DataPoint(9000, r2),
                new DataPoint(10000, s2),
                new DataPoint(11000,k2),
                new DataPoint(12000, t2)

        });


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));
        paint.setColor(Color.BLUE);
        series2.setCustomPaint(paint);
        series2.setThickness(4);

        graph.addSeries(series2);
        series2.setColor(Color.BLUE);

        PointsGraphSeries<DataPoint> seriesp1 = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2000,w),
                new DataPoint(4000, x),
                new DataPoint(6000, y),
                new DataPoint(8000, z),
                new DataPoint(9000, r),
                new DataPoint(10000, s),
                new DataPoint(11000, k),
                new DataPoint(12000, t)

        });
        seriesp1.setColor(Color.RED);
        seriesp1.setShape(PointsGraphSeries.Shape.POINT);
        seriesp1.setSize(12);
        graph.addSeries(seriesp1);

        PointsGraphSeries<DataPoint> seriesp2 = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(2000,w2),
                new DataPoint(4000, x2),
                new DataPoint(6000, y2),
                new DataPoint(8000, z2),
                new DataPoint(9000, r2),
                new DataPoint(10000, s2),
                new DataPoint(11000, k2),
                new DataPoint(12000, t2)

        });

        seriesp2.setColor(Color.BLUE);
        graph.addSeries(seriesp2);
        seriesp2.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(6);
                canvas.drawLine(x - 15, y - 15, x + 15, y + 15, paint);
                canvas.drawLine(x + 15, y - 15, x - 15, y + 15, paint);
            }
        });


//        series.setTitle("Direito");
//        series2.setTitle("Esquerdo");
//        seriesp1.setTitle("Direito");
//        seriesp2.setTitle("Esquerdo");
//        graph.getLegendRenderer().setVisible(true);
//        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {" ","250", "500", "1000", "2000","4000","8000"," "});
        staticLabelsFormatter.setVerticalLabels(new String[] {"80", "70", "60", "50","40","30","20","10","0","-10"});

        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Frequencia [Hz]");
        gridLabel.setVerticalAxisTitle("Intensidade [dB]");

        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(14000);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxY(10);
        graph.getViewport().setMinY(-80);
        graph.getViewport().setYAxisBoundsManual(true);
        //mudar maxy pra 10 e miny pra -80, mudar os valores nas atividades das frequencias

    }
}