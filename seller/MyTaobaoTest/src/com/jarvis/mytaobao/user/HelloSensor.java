package com.jarvis.mytaobao.user;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.jarvis.mytaobaotest.R;


/**
 * 本机拥有的传感器数量界面
 * @author http://yecaoly.taobao.com
 *
 */
public class HelloSensor extends Activity {       
    Sensor sensor ;       
    private float x, y, z;       
    /** Called when the activity is first created. */       
    @SuppressWarnings("deprecation")
	@Override       
    public void onCreate(Bundle savedInstanceState) {       
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.hellosener);       
       
        //准备显示信息的UI组建       
        final TextView tx1 = (TextView) findViewById(R.id.textView1);       
       
        //从系统服务中获得传感器管理器       
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);       
       
        //从传感器管理器中获得全部的传感器列表       
        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);       
		
        //显示有多少个传感器       
        tx1.setText("经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n");       
       
        //显示每个传感器的具体信息       
        for (Sensor s : allSensors) {       
       
            String tempString = "\n" + "  设备名称：" + s.getName() + "\n" + "  设备版本：" + s.getVersion() + "\n" + "  供应商："       
                    + s.getVendor() + "\n";       
       
            switch (s.getType()) {       
            case Sensor.TYPE_ACCELEROMETER:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 加速度传感器accelerometer" + tempString);       
                break;       
            case Sensor.TYPE_GRAVITY:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 重力传感器gravity API 9" + tempString);       
                break;       
            case Sensor.TYPE_GYROSCOPE:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 陀螺仪传感器gyroscope" + tempString);       
                break;       
            case Sensor.TYPE_LIGHT:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 环境光线传感器light" + tempString);       
                break;       
            case Sensor.TYPE_LINEAR_ACCELERATION:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 线性加速器LINEAR_ACCELERATION API 9" + tempString);       
                break;       
            case Sensor.TYPE_MAGNETIC_FIELD:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 电磁场传感器magnetic field" + tempString);       
                break;       
            case Sensor.TYPE_ORIENTATION:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 方向传感器orientation" + tempString);       
                break;       
            case Sensor.TYPE_PRESSURE:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 压力传感器pressure" + tempString);       
                break;       
            case Sensor.TYPE_PROXIMITY:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 距离传感器proximity" + tempString);       
                break;       
            case Sensor.TYPE_ROTATION_VECTOR:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 旋转向量ROTATION" + tempString);       
                break;       
            case Sensor.TYPE_TEMPERATURE:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 温度传感器temperature" + tempString);       
                break;       
            default:       
                tx1.setText(tx1.getText().toString() + s.getType() + " 未知传感器" + tempString);       
                break;       
            }       
        }       
               
        //在title上显示重力传感器的变化       
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);       
        SensorEventListener lsn = new SensorEventListener() {       
           
			public void onSensorChanged(SensorEvent e) {       
                x = e.values[SensorManager.DATA_X];       
                y = e.values[SensorManager.DATA_Y];       
                z = e.values[SensorManager.DATA_Z];       
                setTitle("x=" + (int) x + "," + "y=" + (int) y + "," + "z="+ (int) z);       
            }       
       
            public void onAccuracyChanged(Sensor s, int accuracy) {       
            }       
        };       
        // 注册listener，第三个参数是检测的精确度       
        sm.registerListener(lsn, sensor, SensorManager.SENSOR_DELAY_GAME);       
       
    }       
}  