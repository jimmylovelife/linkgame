package com.rj.rjmathlinkgame;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.rj.rjmathlinkgame.core.RJConfig;
import com.rj.rjmathlinkgame.core.RJDaemonService;
import com.rj.rjmathlinkgame.view.RJGameView;

public class RJGameActivity extends Activity {
	
	protected static final int MINUS_TIMER = 0x100;
	private RJGameView gameView;
	private Button btRefresh;
	private Button btPrompt;
	private Button btStart;
	
	private RJConfig conf = null;
	private String bestTime = null;
	private RJDaemonService service = null;
	
	private boolean started = false;
	private long remainTime;
	private Timer timer;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MINUS_TIMER:
				//TODO SHOW PROGRESS BAR & TXT
				remainTime--;
				if (remainTime < 0 ) {
					timer.cancel();
					timer = null;
					started = false;
					//TODO SHOW lost game
				}
				break;
			}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rj_game_view);

		Bundle extras = getIntent().getExtras();
		conf = (RJConfig) extras.getParcelable("config");
		bestTime = "最佳时间： " + extras.getLong("bestTime", 0);
		service = new RJDaemonService(conf, this);
		gameView = (RJGameView) findViewById(R.id.gameView);
		gameView.setGameService(service);
		
		btRefresh = (Button) findViewById(R.id.bt_refresh);
		btRefresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btPrompt = (Button) findViewById(R.id.bt_prompt);
		btPrompt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btStart = (Button) findViewById(R.id.bt_start);
		btStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startGame();
			}
		});
		
	}

	protected void startGame() {
		service.startGame();
		started = true;
		gameView.postInvalidate();
		
		this.remainTime = conf.getTime();
		
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(MINUS_TIMER);
			}
			
		}, 0, remainTime);
	}

}
