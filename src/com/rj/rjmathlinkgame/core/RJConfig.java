package com.rj.rjmathlinkgame.core;

import android.os.Parcel;
import android.os.Parcelable;

public class RJConfig implements Parcelable{
	
	public static final int RJITEM_WIDTH = 40;
	public static final int RJITEM_HEIGHT = 40;
	
	private static final int DEFAULT_BEGIN_X = 2;
	private static final int DEFAULT_BEGIN_Y = 10;
	private static final long DEFAULT_LEVEL_TIME = 10000;
	private static final int FACTOR = 50;
	private static final int DEFAULT_COLUMNS = 6;
	private static final int DEFAULT_ROWS = 10;

	private int row;
	private int column;

	private int beginX;
	private int beginY;

	private long time;

	public RJConfig(int x, int y, int co, int ro, long time) {
		beginX = x;
		beginY = y;
		column = co;
		row = ro;
		this.time = time;
	}
	
	public RJConfig (int columns, int rows, long time) {
		this.column = columns;
		this.row = rows;
		this.time = time;
		this.beginX = DEFAULT_BEGIN_X;
		this.beginY = DEFAULT_BEGIN_Y;
	}

	public RJConfig(int time) {
		this.column = DEFAULT_COLUMNS;
		this.row = DEFAULT_ROWS;
		this.time = time;
		this.beginX = DEFAULT_BEGIN_X;
		this.beginY = DEFAULT_BEGIN_Y;
	}
	
	public RJConfig () {
		
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

	public long getTime() {
		return time;
	}
	
	//TODO increase the columns & rows by the position
	public static RJConfig getTurnipConfig(int level) {
		long gameTime = DEFAULT_LEVEL_TIME;
		gameTime -= FACTOR * level;
		
		return new RJConfig(DEFAULT_COLUMNS, DEFAULT_ROWS, gameTime);
	}

	public static RJConfig getMahJongConfig(int position) {
		return null;
	}

	public static RJConfig getBrainStormConfig(int position) {
		return null;
	}

	public static final Parcelable.Creator<RJConfig> CREATOR = new Creator<RJConfig>() {

		@Override
		public RJConfig createFromParcel(Parcel source) {
			RJConfig conf = new RJConfig();
			conf.beginX = source.readInt();
			conf.beginY = source.readInt();
			conf.column = source.readInt();
			conf.row = source.readInt();
			conf.time = source.readLong();
			
			return conf;
		}

		@Override
		public RJConfig[] newArray(int size) {
			return new RJConfig[size];
		}
		
	};
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel target, int arg1) {
		target.writeInt(beginX);
		target.writeInt(beginY);
		target.writeInt(column);
		target.writeInt(row);
		target.writeLong(time);
		
	}
}
