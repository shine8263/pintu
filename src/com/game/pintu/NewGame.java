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
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_game);
		
		nandu = (TextView) findViewById(R.id.nandu);
		
		time = (TextView) findViewById(R.id.time);
		Config.startTime = System.currentTimeMillis();
		handler.removeCallbacks(runnable);
		handler.postDelayed(runnable,50); 
		
		switch(Config.nandu){
		case 3:
			nandu.setText("�Ѷ�:��");
			break;
		case 4:
			nandu.setText("�Ѷ�:һ��");
			break;
		case 5:
			nandu.setText("�Ѷ�:����");
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
    	time.setText("ʱ��:"+Config.time);
    	
    	
    }






}


