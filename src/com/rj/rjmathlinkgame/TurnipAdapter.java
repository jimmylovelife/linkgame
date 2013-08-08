package com.rj.rjmathlinkgame;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rj.rjmathlinkgame.core.RJConfig;

public class TurnipAdapter extends BaseAdapter {
	public static final int LEVEL_COUNTS = 100;
	public static final String PREF_KEY = "TURNIP_LEVELS";
	
	private static Bitmap TURNIP_IMAGE = null;
	public static final Bitmap MAHJONG_IMAGE = null;
	public static final Bitmap BRAINSTORM_IMAGE = null;
	private List<RJLevelItem> levels = new ArrayList<RJLevelItem>();
	private Context context;
	private LayoutInflater inflater;

	public TurnipAdapter(Context ctx) {
		this.context = ctx;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		SharedPreferences prefs = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(ctx);
		String levelString = prefs.getString(PREF_KEY, null);
		parse(levelString);
	}

	private void parse(String levelString) {
		int index = 1, flag, bestTime;
		if (levelString != null) {
			String[] strs = levelString.split(" ");
			String[] pair;
			for (String str : strs) {
				pair = str.split(",");
				if (pair.length == 2) {
					flag = Integer.parseInt(pair[0]);
					bestTime = Integer.parseInt(pair[1]);
					levels.add(new RJLevelItem(RJConfig.getTurnipConfig(index), bestTime, (flag==1), index, true));
				}
				index++;
			}
		}
		boolean canPlay = true;
		for (; index <= LEVEL_COUNTS; index++) {
			levels.add(new RJLevelItem(RJConfig.getTurnipConfig(index), 0, false, index, canPlay));
			canPlay = false;
		}
	}

	@Override
	public int getCount() {
		return LEVEL_COUNTS;
	}

	@Override
	public Object getItem(int position) {
		if (position >= 0 && position < LEVEL_COUNTS)
			return levels.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RJLevelView item = null;
		RJLevelItem level = (RJLevelItem) getItem(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.rj_level_item, null);
			item = new RJLevelView();
			item.view = (ImageView) convertView.findViewById(R.id.levelIcon);
			item.level = (TextView) convertView.findViewById(R.id.levelNum);
			//item.bestTime = (TextView) convertView.findViewById(R.id.bestTime);
			convertView.setTag(item);
		} else {
			item = (RJLevelView) convertView.getTag();
		}
		setTurnipImage(item, level.canPlay());
		item.level.setText("第"+level.getLevel()+"关");
		//item.bestTime.setText("最佳成绩: " + level.getBestTime())
		return convertView;
	}

	@SuppressLint("NewApi")
	private void setTurnipImage(RJLevelView item, boolean canPlay) {
		if (TURNIP_IMAGE == null) {
			TURNIP_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		}
		if (item != null) {
			item.view.setImageBitmap(TURNIP_IMAGE);
			if (!canPlay) {
				item.view.setAlpha(0.5f);
			}
		}
	}
}
