package com.example.compassapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var magField: Sensor
    lateinit var accel: Sensor
    var accelValues = FloatArray(3)
    var magFieldValues = FloatArray(3)
    val orientationMatrix = FloatArray(16)
    val azimuth: Float
    val pitch: Float
    val roll: 



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sMgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accel = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magField = sMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sMgr.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int){

    }

    override fun onSensorChanged(ev: SensorEvent) {
        if(ev.sensor == accel) {
            tvXaxis.text = ev.values[0].toString()
            tvYaxis.text = ev.values[1].toString()
            tvZaxis.text = ev.values[2].toString()
            accelValues = ev.values.clone()
        }
        else if(ev.sensor == magField){
            tvAzimuth.text = ev.values[0].toString()
            tvP.text = ev.values[1].toString()
            tvZaxis.text = ev.values[2].toString()
            magFieldValues = ev.values.clone()

        }

        SensorManager.getRotationMatrix(orientationMatrix, null, accelValues, magFieldValues)

    }
}
