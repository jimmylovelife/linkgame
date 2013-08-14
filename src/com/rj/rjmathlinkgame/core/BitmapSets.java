package com.rj.rjmathlinkgame.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.rj.rjmathlinkgame.R;

public class BitmapSets {
	public final static int T_SIZE = 40;
	public final static int M_SIZE = 42;
	public final static int B_SIZE = 40;
	
	private List<Bitmap> T_sets = new ArrayList<Bitmap>();
	private List<Bitmap> M_sets = new ArrayList<Bitmap>();
	private List<Bitmap> S_sets = new ArrayList<Bitmap>();
	
	private Object T_lock;
	private Object M_lock;
	private Object S_lock;
	
	private Random random = new Random();
	private static BitmapSets instance = null;
	
	private Context ctx;
	class GenerateBitmap implements Runnable {

		@Override
		public void run() {
			int i;
			int start = R.drawable.m_0;
			Matrix matrix = new Matrix();
			matrix.postScale(0.5f, 0.3125f);
			synchronized(M_lock) {
				for (i=0; i<M_SIZE; i++) {
					Bitmap obj = BitmapFactory.decodeResource(ctx.getResources(), start);
					Bitmap target = Bitmap.createBitmap(obj, 0, 0, obj.getWidth(), obj.getHeight(), matrix, true);
					M_sets.add(target);
					start++;
				}
			}
			//TODO add other png
		}
		
	}
	
	GenerateBitmap  daemonThread = new GenerateBitmap();
	
	public BitmapSets(Context context) {
		this.ctx = context;
		T_lock = new Object();
		M_lock = new Object();
		S_lock = new Object();
		random = new Random();
		daemonThread.run();
	}
	
	public Bitmap getMBitmap() {
		int position = random.nextInt(M_SIZE);
		synchronized(M_lock) {
			return M_sets.get(position);
		}
	}
	
	public static BitmapSets getInstance(Context ctx) {
		if (instance == null) {
			instance = new BitmapSets(ctx);
		}
		
		return instance;
	}
}
