package com.rj.rjmathlinkgame.core;

public class RJDaemonService {
	RJConfig config;
	private RJItem[][] items;
	private int itemNum;
	
	
	/**
	 *
	 * @param row
	 * @param column
	 */
	public RJDaemonService(RJConfig conf) {
		config = conf;
	}
	
	/**
	 * create the items and shuttle it
	 */
	public void startGame() {
		
	}
	
	public RJItem[][] getRJItems() {
		return items;
	}
	
	/*
	 * return true for all items have been linked
	 */
	public boolean hasFinished() {
		if (itemNum == 0)
			return true;
		return false;
	}
	
	public RJItem findTouchedRJItem (float x, float y) {
		int innerX, innerY;
		innerX = (int) x - config.getBeginX();
		innerY = (int) y - config.getBeginY();
		
		if (innerX < 0 || innerY < 0) {
			return null;
		}
		
		int indexX = getIndex(innerX, RJConfig.RJITEM_WIDTH);
		int indexY = getIndex(innerY, RJConfig.RJITEM_HEIGHT);
		
		return null;
	}
	
	public int getIndex (int relative, int size) {
		int index = relative / size;
		if (relative % size == 0) {
			return index - 1;
		} else {
			return index;
		}
	}
}
