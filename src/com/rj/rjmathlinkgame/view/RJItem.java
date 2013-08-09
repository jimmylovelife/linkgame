package com.rj.rjmathlinkgame.view;

import com.rj.rjmathlinkgame.core.RJConfig;

import android.graphics.Bitmap;
import android.graphics.Point;

public class RJItem {
	private Point center;
	private Bitmap bitmap = null;
	private int startX;
	private int startY;
	
	public RJItem(Point center, Bitmap bitmap) {
		this.center = center;
		this.bitmap = bitmap;
		setStartX(center.x - RJConfig.RJITEM_WIDTH/2);
		setStartY(center.y - RJConfig.RJITEM_HEIGHT/2);
	}
	
	/**
	 * have same images
	 * @param right
	 * @return
	 */
	public boolean sameWith(RJItem target) {
		return bitmap.equals(target.getBitmap());
	}
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

}
