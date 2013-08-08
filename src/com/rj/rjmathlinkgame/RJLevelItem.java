package com.rj.rjmathlinkgame;

import com.rj.rjmathlinkgame.core.RJConfig;

public class RJLevelItem {
	private RJConfig conf;
	private long bestTime;
	private boolean finished;
	private int level;
	private boolean canPlay;

	public RJLevelItem(RJConfig conf, long bestTime, boolean finished, int level, boolean canPlay) {
		this.conf = conf;
		this.bestTime = bestTime;
		this.finished = finished;
		this.level = level;
		this.canPlay = canPlay;
	}

	public RJConfig getConf() {
		return conf;
	}

	public void setConf(RJConfig conf) {
		this.conf = conf;
	}

	public long getBestTime() {
		return bestTime;
	}

	public void setBestTime(long bestTime) {
		this.bestTime = bestTime;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getLevel() {
		return this.level;
	}

	public boolean canPlay() {
		return this.canPlay;
	}

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}
}
