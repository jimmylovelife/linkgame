package com.rj.rjmathlinkgame.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rj.rjmathlinkgame.core.RJSearchOBJ.Direction;
import com.rj.rjmathlinkgame.view.RJItem;

import android.graphics.Point;

public class RJDaemonService {
	RJConfig config;
	private RJItem[][] items;
	private int[][] mapDatas;
	private int itemNum;
	private List<RJSearchOBJ> will = new ArrayList<RJSearchOBJ>();
	private List<RJSearchOBJ> has = new ArrayList<RJSearchOBJ>();

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
		items = new RJItem[config.getRow()][config.getColumn()];
		mapDatas = new int[config.getRow()][config.getColumn()];
		int i,j;
		for (i = 0; i < config.getRow(); i++)
			for (j = 0; j < config.getColumn(); j++) 
				mapDatas[i][j] = 1;
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

	public RJItem findTouchedRJItem(float x, float y) {
		int innerX, innerY;
		innerX = (int) x - config.getBeginX();
		innerY = (int) y - config.getBeginY();

		if (innerX < 0 || innerY < 0) {
			return null;
		}

		int indexX = getIndex(innerX, RJConfig.RJITEM_WIDTH);
		int indexY = getIndex(innerY, RJConfig.RJITEM_HEIGHT);
		if (indexX < 0 || indexY < 0)
			return null;

		if (indexX >= config.getColumn() || indexY >= config.getRow())
			return null;

		return items[indexX][indexY];
	}

	public int getIndex(int relative, int size) {
		int index = relative / size;
		if (relative % size == 0) {
			return index - 1;
		} else {
			return index;
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public LinkPoints link(RJItem source, RJItem target) {
		if (source.equals(target))
			return null;
		if (! source.sameWith(target))
			return null;
		Point sPoint = source.getCenter();
		Point tPoint = target.getCenter();
		
		return AStarSearch(sPoint, tPoint);
		/*
		//horizon
		if (sPoint.y == tPoint.y) {
			return horizonLink(sPoint, tPoint);
		}
		//vertical
		if (sPoint.x == tPoint.x) {
			return verticalLink(sPoint, tPoint);
		}
		//one corner
		LinkPoints linkPoints = oneCornerLink(sPoint, tPoint);
		
		if (linkPoints == null) {
			linkPoints = twoCornerLink(sPoint, tPoint);
		}
		
		return linkPoints;
		*/
	}
	
	/**
	 * extend next will to will list (not in has list)
	 * @param obj
	 * @param target
	 * @return
	 */
	private void extend (RJSearchOBJ obj, Point target) {
		List<RJSearchOBJ> nexts = getNextSearchOBJs(obj);
		for (RJSearchOBJ next : nexts) {
			if(!has.contains(next) && will.contains(next) && next.isValid()) {
				will.add(next);
			}
		}
	}
	
	/**
	 * return current point's next search points list
	 * @param current
	 * @return
	 */
	private List<RJSearchOBJ> getNextSearchOBJs(RJSearchOBJ current) {
		List<RJSearchOBJ> next = new ArrayList<RJSearchOBJ>();
		int x = current.getCurrent().x;
		int y = current.getCurrent().y;
		if ( (x-1) == -1 || ( (x-1) >= 0 && mapDatas[x-1][y] == 0) ) {
			Point left = new Point(x-1, y);
			RJSearchOBJ obj = new RJSearchOBJ(current, left, current.getSource(), current.getTarget());
			obj.setDire(Direction.LEFT);
			if (current.getDire() != null && obj.getDire() != current.getDire())
				obj.setChangeDirections(current.getChangeDirections() + 1);
			next.add(obj);
		}
		if ( (x+1) == config.getColumn() || ( (x+1) < config.getColumn() && mapDatas[x+1][y] == 0) ) {
			Point right = new Point(x+1, y);
			RJSearchOBJ obj = new RJSearchOBJ(current, right, current.getSource(), current.getTarget());
			obj.setDire(Direction.RIGHT);
			if (current.getDire() != null && obj.getDire() != current.getDire())
				obj.setChangeDirections(current.getChangeDirections() + 1);
			next.add(obj);
		}
		if ( (y+1) == config.getRow() || ( (y+1) < config.getRow() && mapDatas[x][y+1] == 0)) {
			Point up = new Point(x, y+1);
			RJSearchOBJ obj = new RJSearchOBJ(current, up, current.getSource(), current.getTarget());
			obj.setDire(Direction.UP);
			if (current.getDire() != null && obj.getDire() != current.getDire())
				obj.setChangeDirections(current.getChangeDirections() + 1);
			next.add(obj);
		}
		if ( (y-1) == -1 || ((y-1) >= 0 && mapDatas[x][y-1] == 0) ) {
			Point down = new Point (x, y-1);
			RJSearchOBJ obj = new RJSearchOBJ(current, down, current.getSource(), current.getTarget());
			obj.setDire(Direction.DOWN);
			if (current.getDire() != null && obj.getDire() != current.getDire())
				obj.setChangeDirections(current.getChangeDirections() + 1);
			next.add(obj);
		}
	
		return next;
	}

	private LinkPoints AStarSearch(Point sPoint, Point tPoint) {
		RJSearchOBJ start = new RJSearchOBJ(null, sPoint, sPoint,  tPoint);
		addToWill(start);
		while(true) {
			RJSearchOBJ obj = selectFromWill();
			if (obj == null) {
				return null;
			} 
			if (obj.isTarget()) {
				return getLinkPoints(obj);
			}
			extend(obj, tPoint);
			addToHas(obj);
		}
		
	}
	
	private LinkPoints getLinkPoints(RJSearchOBJ obj) {
		LinkPoints ret = new LinkPoints();
		RJSearchOBJ cur = obj;
		RJSearchOBJ pre = obj.getParent();
		while (pre != null) {
			ret.addPoint(pre.getCurrent(), cur.getCurrent());
			cur = pre;
			pre = cur.getParent();
		}
		return ret;
	}

	private void addToHas(RJSearchOBJ obj) {
		if (!has.contains(obj)) 
			has.add(obj);
	}

	private RJSearchOBJ selectFromWill() {
		if (will.size() == 0)
			return null;
		Collections.sort(will);
		return will.remove(0);
	}

	private void addToWill(RJSearchOBJ obj) {
		if (!will.contains(obj))
			will.add(obj);
	}

	private LinkPoints twoCornerLink(Point sPoint, Point tPoint) {
		return null;
	}

	private LinkPoints oneCornerLink(Point sPoint, Point tPoint) {
		Point sXtYPoint = new Point(sPoint.x, tPoint.y);
		Point sYtXPoint = new Point(sPoint.y, tPoint.x);
		if (mapDatas[sXtYPoint.y][sXtYPoint.x] == 0) {
			LinkPoints hLinkPoints = horizonLink(sXtYPoint, tPoint);
			LinkPoints vLinkPoints = verticalLink(sXtYPoint, sPoint);
			if (hLinkPoints != null && vLinkPoints != null) {
				 return hLinkPoints.addPoints(vLinkPoints.getPoints());
			}
				
		}
		
		if (mapDatas[sYtXPoint.y][sYtXPoint.x] == 0) {
			LinkPoints hLinkPoints = horizonLink(sYtXPoint, sPoint);
			LinkPoints vLinkPoints = verticalLink(sYtXPoint, tPoint);
			if (hLinkPoints != null && vLinkPoints != null) {
				 return hLinkPoints.addPoints(vLinkPoints.getPoints());
			}
		}
		
		return null;
	}

	private LinkPoints verticalLink(Point sPoint, Point tPoint) {
		int x = sPoint.x;
		int up, down;
		if (sPoint.y > tPoint.y) {
			up = sPoint.y;
			down = tPoint.y;
		} else {
			up = tPoint.y;
			down = sPoint.y;
		}
		
		for (down = down+1; down < up; down++) {
			if (mapDatas[x][down] == 1)
				return null;
		}
		return new LinkPoints().addPoint(sPoint, tPoint);
	}

	private LinkPoints horizonLink(Point sPoint, Point tPoint) {
		int y = sPoint.y;
		int left, right;
		if (sPoint.x > tPoint.x) {
			left = tPoint.x;
			right = sPoint.x;
		} else {
			left = sPoint.x;
			right = tPoint.x;
		}
		
		for (left=left+1; left < right; left++) {
			if (mapDatas[y][left] == 1)
				return null;
		}
		return new LinkPoints().addPoint(sPoint, tPoint);
	}
}
