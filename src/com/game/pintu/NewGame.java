package com.game.pintu;

import java.util.TimerTask;

import com.game.config.Config;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class NewGame extends Activity  {
	
	private TextView nandu,time;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_game);
		
		nandu = (TextView) findViewById(R.id.nandu);
		
		time = (TextView) findViewById(R.id.time);
		Config.startTime = System.currentTimeMillis();
		handler.removeCallbacks(runnable);
		handler.postDelayed(runnable,50); 
		
		switch(Config.nandu){
		case 3:
			nandu.setText("难度:简单");
			break;
		case 4:
			nandu.setText("难度:一般");
			break;
		case 5:
			nandu.setText("难度:困难");
			break;
	
		}
		
		

		//handler.removeCallbacks(runnable);

	}
	
	
    private Runnable runnable = new Runnable() {
         public void run () {
             update();
         handler.postDelayed(this,50); 
         
      }
    };

    private void update(){
    	
    	Config.time = (int)((System.currentTimeMillis() - Config.startTime) / 1000);
    	time.setText("时间:"+Config.time);
    	
    	
    }






}


