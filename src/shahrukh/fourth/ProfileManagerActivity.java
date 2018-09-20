package shahrukh.fourth;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileManagerActivity extends Activity {
	public SensorManager mSensorManager;
	public Sensor mproximity,macclerator;
	float lux;
	TextView  showResult1,showResult2,showResult3,showResult4,showResult5;
	Button btn1,btn2;
	Intent in;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        in = new Intent(this, Myservice.class);
        btn1=(Button) findViewById(R.id.button1);
        btn2=(Button) findViewById(R.id.button2);
    }
    public void startClicked(View v){
    	startService(in);
    }
    public void stopClicked(View v){
    	stopService(in);
    }
}