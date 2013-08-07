package com.rj.rjmathlinkgame.core;

import android.content.Context;

public class RJConfig {
	public static final int RJITEM_WIDTH = 40;
	public static final int RJITEM_HEIGHT = 40;
	
	private static final int DEFAULT_BEGIN_X = 2;
	private static final int DEFAULT_BEGIN_Y = 10;
	private static final int DEFAULT_LEVEL_TIME = 10000;
	private static final int FACTOR = 50;
	private static final int DEFAULT_COLUMNS = 0;
	private static final int DEFAULT_ROWS = 0;

	private int row;
	private int column;

	private int beginX;
	private int beginY;

	private long time;
	private Context context;

	public RJConfig(int x, int y, int co, int ro, long time, Context ctx) {
		beginX = x;
		beginY = y;
		column = co;
		row = ro;
		this.time = time;
		context = ctx;
	}
	
	public RJConfig (int columns, int rows, long time, Context ctx) {
		this.context  = ctx;
		this.column = columns;
		this.row = rows;
		this.time = time;
		this.beginX = DEFAULT_BEGIN_X;
		this.beginY = DEFAULT_BEGIN_Y;
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
		return time;
	}

	public Context getContext() {
		return context;
	}

	//TODO increase the columns & rows by the position
	public static RJConfig getTurnipConfig(int level) {
		int gameTime = DEFAULT_LEVEL_TIME;
		gameTime -= FACTOR * level;
		
		return new RJConfig(DEFAULT_COLUMNS, DEFAULT_ROWS, gameTime, null);
	}

	public static RJConfig getMahJongConfig(int position) {
		return null;
	}

	public static RJConfig getBrainStormConfig(int position) {
		return null;
	}
}
