package com.game.pintu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.game.config.Config;

public class MainActivity extends Activity implements OnClickListener {
	private Button[] button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Config.metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(Config.metrics);
		
		
		setContentView(R.layout.activity_main);
		button = new Button[4];
		
		button[0] = (Button) findViewById(R.id.newGame);
		//button[1] = (Button) findViewById(R.id.ranked);	
		//button[2] = (Button) findViewById(R.id.help);
		button[1] = (Button) findViewById(R.id.exit);
		for(int i = 0; i < 2; i++){
			button[i].setOnClickListener(this);
		}
		
		
		
		
		//Toast.makeText(getBaseContext(), Config.metrics.widthPixels + " "+Config.metrics.heightPixels, 0).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		Intent intent;
		
		
		switch(v.getId()){
		case R.id.newGame:
			intent = new Intent(this,SelectImage.class);
			startActivity(intent);
			break;
		
		
		case R.id.exit:
			finish();
			break;
		
		
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	

}
