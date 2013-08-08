package com.rj.rjmathlinkgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class RJLevelActivity extends Activity {
	enum GameType {
		TURNIP, MAHJONG, BRAINSTORM
	};

	private GameType gameClass;

	private BaseAdapter levelAdapter;
	private GridView view;

	//TODO make grey and not clickable
	private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View clickView, int position,
				long arg3) {
			
			RJLevelItem selected = (RJLevelItem) levelAdapter.getItem(position);
			//RJLevelItem preLevel = (RJLevelItem) levelAdapter.getItem(position - 1);
			if (selected != null && selected.canPlay()) {
				Intent intent = new Intent(RJLevelActivity.this, RJGameMainView.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable("config", selected.getConf());
				intent.putExtra("bestTime", selected.getBestTime());
				intent.putExtras(bundle);
				startActivity(intent);
			} else {
				//do nothing
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rj_level_list);
		view = (GridView) findViewById(R.id.level_list_grid);
		view.setOnItemClickListener(clickListener);
		
		Intent intent = this.getIntent();
		String type = intent.getStringExtra("type");
		setGameClass(type);
		
		setContentViewByType();
	}

	private void setContentViewByType() {
		switch (gameClass) {
		case TURNIP:
			levelAdapter = new TurnipAdapter(this);
			break;
		case MAHJONG:
			levelAdapter = new MahJongAdapter(this);
			break;
		case BRAINSTORM:
			levelAdapter = new BrainAdapter(this);
			break;
		}
		view.setAdapter(levelAdapter);
	}

	private void setGameClass(String type) {
		if (type.equals("turnip")) {
			gameClass = GameType.TURNIP;
		}
		if (type.equals("mahjong")) {
			gameClass = GameType.MAHJONG;
		}
		if (type.equals("brainstorm")) {
			gameClass = GameType.BRAINSTORM;
		}
	}

}
