package com.rj.rjmathlinkgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
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
			System.out.println("Begin to draw " + items.length);
			for (int i=0; i<items.length; i++) {
				for (int j=0; j<items[i].length; j++) {
					RJItem item = items[i][j];
					if (item != null) {
						canvas.drawBitmap(item.getBitmap(), item.getStartX(), item.getStartY(), null);
					}
				}
			}
		}
	}



	public void setGameService(RJDaemonService service) {
		this.service = service;
	}
	
	

}
