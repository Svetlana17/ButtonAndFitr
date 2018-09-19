package com.example.user.buttonandfitr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ActivityZZ extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

private SensorManager mSensorManager;
        Sensor sensorAccelerometr;
        GraphView graph;
private double graph2LastZValue = 5d;
private double graph2LastZZValue = 5d;
private Double[] dataPoints;
        LineGraphSeries<DataPoint> seriesZ;
        LineGraphSeries<DataPoint> seriesZZ;
private Thread thread;
private boolean plotData = true;
        float zz;
private float On_1 = 1;
private float altha = 0.1f;
private boolean state;
private int timer = 0;
        Button mButton;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy);
        mButton = (Button) findViewById(R.id.buttonZZ);
        mButton.setOnClickListener((View.OnClickListener) this);
        state = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometr = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        System.out.println(sensorAccelerometr);
        graph = (GraphView) findViewById(R.id.graph);
        seriesZ = new LineGraphSeries<DataPoint>(new DataPoint[]{
        new DataPoint(0, 0),

        });
        seriesZ.setColor(Color.BLACK);
        seriesZZ = new LineGraphSeries<DataPoint>(new DataPoint[]{
        new DataPoint(0, 0),

        });
        seriesZZ.setColor(Color.RED);
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
        if (state) {
        timer++;
        if (timer % 5 == 0) {
        System.out.println(timer);
        // saveText(event);
        }
        }
        graph2LastZValue += 1d;


       zz = (float) (On_1 + altha * (z - On_1));


        seriesZ.appendData(new DataPoint(graph2LastZValue, z), true, 20);
        seriesZZ.appendData(new DataPoint(graph2LastZZValue, zz), true, 20);
        graph.addSeries(seriesZ);
        graph.addSeries(seriesZZ);

//        if (!graficflag) {
//            graph.removeSeries(seriesXX);
//        } else {
//        graph.addSeries(seriesXX);
//        graph.addSeries(seriesX);

//        }
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

    }

    @Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
@Override
protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensorAccelerometr, SensorManager.SENSOR_DELAY_NORMAL);
        }
@Override
protected void onDestroy() {
        mSensorManager.unregisterListener(ActivityZZ.this);
        thread.interrupt();
        super.onDestroy();
        }

public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
        }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.Giroscope:
//                Intent intent = new Intent();
//                intent.setClass(this, GiroscopeActivity.class);
//
//                startActivity(intent);
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
///////

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        Log.d(LOG_TAG, "onPrepareOptionsMenu");
//        menu.removeItem(R.id.acselerometr);
//
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
////        menu.add(0, REFRESH_ID, 1, R.string.acselerometr);
//        menu.add(0, GIROSCOPE, 2, R.string.giroscope);
//        menu.add(0, MOVENENT,3,R.string.MOVENENT);
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
////            case R.id.acselerometr:
////                return true;
////            case 1:
//////                // acselerometr
//////                Log.d(LOG_TAG, "action: acselerometr");
//////                return true;
////                Intent intent = new Intent();
////                intent.setClass(this, MainActivity.class);
////
////                startActivity(intent);
////                finish();
//            case 1:
//                // load data
////                Log.d(LOG_TAG, "action: load data");
////                return true;
////            default:
////                return super.onOptionsItemSelected(item);
//                Intent intents = new Intent();
//                intents.setClass(this, AccelerometrXX.class);
//
//                startActivity(intents);
//                finish();
////            case 2:
////                // load data
//////                Log.d(LOG_TAG, "action: load data");
//////                return true;
//////            default:
//////                return super.onOptionsItemSelected(item);
////                Intent intentss = new Intent();
////                intentss.setClass(this, MovenentActivity.class);
////
////                startActivity(intentss);
////                finish();
//        }
//        return false;
//    }
////
//
        }
