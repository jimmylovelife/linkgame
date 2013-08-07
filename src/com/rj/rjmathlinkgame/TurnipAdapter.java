package com.rj.rjmathlinkgame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rj.rjmathlinkgame.core.RJConfig;

public class TurnipAdapter extends BaseAdapter {
	public static final int LEVEL_COUNTS = 100;
	
	private Context context;
	
	public TurnipAdapter(Context ctx) {
		this.context = ctx;
	}

	@Override
	public int getCount() {
		return LEVEL_COUNTS;
	}

	@Override
	public Object getItem(int position) {
		if (position >= 0 && position <= LEVEL_COUNTS)
			return RJConfig.getTurnipConfig(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
}
