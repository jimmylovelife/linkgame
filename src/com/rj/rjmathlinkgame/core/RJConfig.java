package com.rj.rjmathlinkgame.core;

import android.content.Context;

public class RJConfig {
	static public final int RJITEM_WIDTH = 40;
	static public final int RJITEM_HEIGHT = 40;
	
	private int row;
	private int column;
	
	private int beginX;
	private int beginY;
	
	private long rtime;
	private Context context;
	
	public RJConfig(int x, int y, int co, int ro, long time, Context ctx) {
		beginX = x;
		beginY = y;
		column = co;
		row = ro;
		rtime = time;
		context = ctx;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getBeginX() {
		return beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public long getRtime() {
		return rtime;
	}

	public Context getContext() {
		return context;
	}
}
