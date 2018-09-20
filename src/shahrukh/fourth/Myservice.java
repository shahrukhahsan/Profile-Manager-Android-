package shahrukh.fourth;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.widget.Toast;

public class Myservice extends Service implements SensorEventListener{
	public SensorManager mSensorManager;
	public Sensor mLight,mproximity,macclerator;
	static boolean facedown, reverse;
	public AudioManager mAudioManager;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		 mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	     mproximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	     macclerator = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	     
	     mSensorManager.registerListener(this, mproximity, SensorManager.SENSOR_DELAY_NORMAL);
		 mSensorManager.registerListener(this, macclerator, SensorManager.SENSOR_DELAY_NORMAL);
		 
		 mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mSensorManager.unregisterListener(this);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public int onStartCommand(Intent intent, int flags, int startId){
		return START_STICKY;
	}
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		switch (event.sensor.getType())
		   {
		   	case Sensor.TYPE_ACCELEROMETER:
		   		if(event.values[2] > 0)
		   		{
		   			reverse=false;
	            }
		   		else
	            {
		   			reverse=true;
	            }
		   		break;
		   	case Sensor.TYPE_PROXIMITY:
		   		if(event.values[0] == 0 )
		   		{
		   			facedown = true;
		   		}
		   		else
		   		{
		   			facedown = false;
		   		}
		   		break;
		   }
                 go();
	}
	public void go()
	{
	    try { 
	    	int mod=mAudioManager.getRingerMode();
			  if((facedown==true) && (reverse==true))// vibrate when reverse
			  {
				  if (mod!=AudioManager.RINGER_MODE_VIBRATE) {
                     mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                     Toast.makeText(getApplicationContext(), "Vibrate", Toast.LENGTH_SHORT).show();
                  } 	   
	   		  }
			  else if((facedown==true) && (reverse==false)) //vibrate while looking downward
			  {
				  if (mod!=AudioManager.RINGER_MODE_VIBRATE) {
                      mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                      Toast.makeText(getApplicationContext(), "Vibrate", Toast.LENGTH_SHORT).show();
                  } 			  
	
			  }
			  else if((facedown==false) && (reverse==true)) //silent while looking downward
			  {
				  if (mod!=AudioManager.RINGER_MODE_SILENT) {
                      mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                      Toast.makeText(getApplicationContext(), "Silent", Toast.LENGTH_SHORT).show();
                  }				  
	
			  }
	   		  else
	   		  {
	   			if (mod!=AudioManager.RINGER_MODE_NORMAL)
	   			{
	   				mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Toast.makeText(getApplicationContext(), "Normal", Toast.LENGTH_SHORT).show();
                }		    		
	   		  }
	    }
	    catch (Exception e) 
	    {
	              e.printStackTrace();
	     }
	

}
}