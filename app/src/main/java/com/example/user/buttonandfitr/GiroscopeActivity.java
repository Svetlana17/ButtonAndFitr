package com.example.user.buttonandfitr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GiroscopeActivity extends   AppCompatActivity  implements SensorEventListener, View.OnClickListener {
        private SensorManager mSensorManager;


        private static final int GIROSCOPE = 2;


        Sensor sensorGiroscope;
        GraphView graph;
        private double graph2LastXValue = 5d;
        private double graph2LastYValue = 5d;
        private double graph2LastZValue = 5d;
        private Double[] dataPoints;
        LineGraphSeries<DataPoint> series;
        LineGraphSeries<DataPoint> seriesX;
        LineGraphSeries<DataPoint> seriesZ;

        private Thread thread;
        private boolean plotData = true;
        float xx;
        float yy;
        float zz;
        private boolean graficflag = false;

        //    Spinner spinner;
//    String[] acxios = {"ускорение  по х", "ускорение по y ", "ускорение по z "};
        Button mButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_giroscope);
                mButton = (Button) findViewById(R.id.buttonXX);
//                mButton.setOnClickListener((View.OnClickListener) this);
//                state = false;
//        spinner = (Spinner)findViewById(R.id.spinner);

                mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                sensorGiroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);



                System.out.println(sensorGiroscope);
                graph = (GraphView) findViewById(R.id.graph);


                seriesX = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(0, 0),

                });
                seriesX.setColor(Color.YELLOW);

                seriesZ = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(0, 0),
                });
                seriesZ.setColor(Color.RED);

                graph = (GraphView) findViewById(R.id.graph);
                series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                        new DataPoint(0, 0),
                });
                series.setColor(Color.BLUE);


                graph.addSeries(seriesX);
                graph.addSeries(series);
                graph.addSeries(seriesZ);

                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setMinX(0);
                graph.getViewport().setMaxX(20);
                feedMultiple();
        }

        public void addEntry(SensorEvent event) {
                /*     LineGraphSeries<DataPoint> series = new LineGraphSeries<>();*/
                float[] values = event.values;
                // Movement
                float x = values[0];
                System.out.println(x);
                float y = values[1];
                System.out.println(y);
                float z = values[2];
                System.out.println(z);



                graph2LastXValue += 1d;
                graph2LastYValue += 1d;
                graph2LastZValue += 1d;

                series.appendData(new DataPoint(graph2LastYValue, y), true, 20);
                seriesX.appendData(new DataPoint(graph2LastXValue, x), true, 20);
                seriesZ.appendData(new DataPoint(graph2LastZValue, z), true, 20);

                graph.addSeries(series);
                graph.addSeries(seriesX);
                graph.addSeries(seriesZ);


                //*добавление фильтра
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
//                new DataPoint(x, y),
//        });
//        graph.addSeries(series);
        }

        private void addDataPoint(double acceleration) {
                dataPoints[499] = acceleration;
        }

        private void feedMultiple() {

                if (thread != null) {
                        thread.interrupt();
                }

                thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                                while (true) {
                                        plotData = true;
                                        try {
                                                Thread.sleep(10);
                                        } catch (InterruptedException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                        }
                                }
                        }
                });

                thread.start();
        }


        @Override
        protected void onPause() {
                super.onPause();

                if (thread != null) {
                        thread.interrupt();
                }
                mSensorManager.unregisterListener(this);

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
                if (plotData) {
                        addEntry(event);
                        plotData = false;
                }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        protected void onResume() {
                super.onResume();
                mSensorManager.registerListener(this, sensorGiroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected void onDestroy() {
                mSensorManager.unregisterListener(GiroscopeActivity.this);
                thread.interrupt();
                super.onDestroy();
        }

//        public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(this, AccelerometrXX.class);
//
//                startActivity(intent);
//                finish();
//        }
//        public void onClicks(View v) {
//                Intent intent = new Intent();
//                intent.setClass(this, ActivityYY.class);
//
//                startActivity(intent);
//                finish();
//        }
//        public void onClickss(View v) {
//                Intent intent = new Intent();
//                intent.setClass(this, ActivityZZ.class);
//
//                startActivity(intent);
//                finish();
//        }

        public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(this, GiroscopeXX.class);

                startActivity(intent);
                finish();
        }
        public void onClicks(View v) {
                Intent intent = new Intent();
                intent.setClass(this, GiroscopeYY.class);

                startActivity(intent);
                finish();
        }
        public void onClickss(View v) {
                Intent intent = new Intent();
                intent.setClass(this, GiroscopeZZ.class);

                startActivity(intent);
                finish();
        }
        public void onClickButtonGiroscope(View v) {
                Intent intent = new Intent();
                intent.setClass(this, GiroscopeActivity.class);

                startActivity(intent);
                finish();
        }
}




