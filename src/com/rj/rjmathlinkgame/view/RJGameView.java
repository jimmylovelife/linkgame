package com.rj.rjmathlinkgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.rj.rjmathlinkgame.core.RJDaemonService;

public class RJGameView extends View {
	private RJDaemonService service;
	public RJGameView(Context context, AttributeSet attr) {
		super(context, attr);
	}

	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (service == null)
			return;
		RJItem[][] items = service.getRJItems();
		if (items != null) {
			for (int i=0; i<items.length; i++) {
				for (int j=0; j<items[i].length; j++) {
					RJItem item = items[i][j];
					if (item != null) {
						canvas.drawBitmap(item.getBitmap(), item.getStartX(), item.getStartY(), null);
						//System.out.println(item.getBitmap().getHeight() + " " + item.getBitmap().getWidth());
						System.out.println(item.getStartX() + " " + item.getStartY());
					}
				}
			}
		}
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("touched x " + event.getX() + " y " + event.getY());
		RJItem selected = service.findTouchedRJItem(event.getX(), event.getY());
		if (selected != null)
			System.out.println("Select " + selected.getStartX());
		return true;
	}



	public void setGameService(RJDaemonService service) {
		this.service = service;
	}
	
	

}
