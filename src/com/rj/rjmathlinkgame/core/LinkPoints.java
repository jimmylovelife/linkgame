package com.rj.rjmathlinkgame.core;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

public class LinkPoints {
	class LinkPoint {
		Point left;
		Point right;
		public LinkPoint(Point l, Point r) {
			left = l;
			right = r;
		}
	};
	private List<LinkPoint> points = new ArrayList<LinkPoint>();
	
	public LinkPoints addPoint(Point left, Point right) {
		points.add(new LinkPoint(left, right));
		return this;
	}
	
	public LinkPoints addLinkPoint(LinkPoint lPoint) {
		points.add(lPoint);
		return this;
	}
	public LinkPoints addPoints(List<LinkPoint> sPoints) {
		this.points.addAll(sPoints);
		return this;
	}
	
	public List<LinkPoint> getPoints() {
		return points;
	}
}
