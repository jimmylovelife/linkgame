package com.rj.rjmathlinkgame;

//TODO to extend in future;
public class RJLevelItem {
	private int columns;
	private int rows;

	public RJLevelItem(int cols, int rows) {
		this.columns = cols;
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
