package com.rj.rjmathlinkgame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BrainAdapter extends BaseAdapter {
	public static final int LEVEL_COUNTS = 100;
	private Context context;
	
	public BrainAdapter(Context ctx) {
		this.context = ctx;
	}
	
	@Override
	public int getCount() {
		return LEVEL_COUNTS;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
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
