package com.rj.rjmathlinkgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.rj.rjmathlinkgame.core.RJConfig;

public class RJGameMainView extends Activity {
	
	private Button btRefresh;
	private Button btPrompt;
	private Button btStart;
	
	private RJConfig conf = null;
	private String bestTime = null;
	
	private boolean started = false;
	private long remainTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rj_game_view);

		Bundle extras = getIntent().getExtras();
		conf = (RJConfig) extras.getParcelable("config");
		bestTime = "最佳时间： " + extras.getInt("bestTime", 0); 
	}

}
