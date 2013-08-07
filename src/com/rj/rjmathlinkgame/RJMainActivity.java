package com.rj.rjmathlinkgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RJMainActivity extends Activity {

	private Button turnip;
	private Button mahJong;
	private Button brainStorm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rj_index);

		turnip = (Button) findViewById(R.id.bt_turnip);
		turnip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevelList("turnip");

			}
		});

		mahJong = (Button) findViewById(R.id.bt_mahjong);
		mahJong.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevelList("mahjong");
			}
		});

		brainStorm = (Button) findViewById(R.id.bt_brainstorm);
		brainStorm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevelList("brainstorm");
			}
		});
	}

	protected void startLevelList(String typeName) {
		Intent intent = new Intent(RJMainActivity.this, RJLevelActivity.class);
		intent.putExtra("type", typeName);
		startActivity(intent);
	}
}
