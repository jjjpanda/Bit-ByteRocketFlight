package orangeboat.bit_byterocketflight.Inputs;

import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Sensor implements SensorEventListener {
    Vibrator v;
    public static float lastX;
    private SensorManager sensorManager;
    private android.hardware.Sensor rotation;

    public Sensor(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        rotation = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_GAME);
        v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);
    }
    @Override
    public void onAccuracyChanged(android.hardware.Sensor arg0, int arg1) {
        // TODO
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        lastX = event.values[0];
        if (lastX < .05f && lastX > -.05f) {
            lastX = 0;
        }
        if (lastX > 3f) {
            lastX = 3f;
        }
        if (lastX < -3f) {
            lastX = -3f;
        }
    }
    public void register() {
        sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_GAME);
   }
    public void unregister() {
        sensorManager.unregisterListener(this);
    }
}
