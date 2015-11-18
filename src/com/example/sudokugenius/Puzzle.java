package com.example.sudokugenius;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class Puzzle extends View{

	int[] px = new int[9*9];
	
	private final SudokuKernel Game;
		
		
	public Puzzle(Context context) {
		super(context);
	    this.Game = (SudokuKernel) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	
	private float width;
	private float height;
	private int selX;
	private int selY;
	
	private final Rect selRect = new Rect();
	
	protected void onSizeChanged(int w,int h,int oldw,int oldh){
		width = w / 9f;
		height = h / 9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	private void select(int x,int y){ 
		invalidate(selRect);
		selX = Math.min(Math.max(x,0), 8);
		selY = Math.min(Math.max(y,0), 8);
		getRect(selX,selY,selRect);
		invalidate(selRect);
	}
	
	private void getRect(int x,int y,Rect rect){ 
		rect.set((int)(x*width),(int)(y*height),(int)(x*width+width),(int)(y * height + height));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){ 
		
		if (event.getAction() != MotionEvent.ACTION_DOWN)
		return super.onTouchEvent(event); 
		
		select((int)(event.getX()/width),(int)(event.getY()/height));
		
		Game.showkeypad(selX,selY);
		
		return true;
		
	}
	
	int wrongtime = 0;
	
	public void setnum(int x){
		if (Game.Work(selX,selY,x)) invalidate();
		else{	startAnimation(AnimationUtils.loadAnimation(Game,R.layout.shake));
		wrongtime++;}
		
	}
	
	protected void onDraw(Canvas canvas){
		//Draw Backgroud
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.yellow));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);
		Paint linecolor = new Paint();
		linecolor.setColor(getResources().getColor(R.color.white));
		Paint Special = new Paint();
		Special.setColor(getResources().getColor(R.color.dark));
		Special.setStrokeWidth(6);
		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.bright));
		
		for (int i = 0;i < 9;i++){ 
		canvas.drawLine(0, i*height, getWidth(), i*height, light);
		canvas.drawLine(0, i*height+1, getWidth(), i*height+1, linecolor);
		canvas.drawLine(i*width, 0, i*width,getHeight(), light);
		canvas.drawLine(i*width+1, 0, i*width+1, getHeight(), linecolor);
		}
		
		for (int i = 0;i < 9;i++){ 
			if( i % 3 != 0) continue;
			canvas.drawLine(0, i*height, getWidth(), i*height, Special);
			canvas.drawLine(0, i*height+1, getWidth(), i*height+1, linecolor);
			canvas.drawLine(i*width,0,i*width,getHeight(),Special);
			canvas.drawLine(i*width+1,0,i*width+1,getHeight(),linecolor);
			
		}
		
		Paint selected = new Paint();
		selected.setColor(getResources().getColor(R.color.selected1));
		canvas.drawRect(selRect, selected);
		
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(getResources().getColor(R.color.selected));
		foreground.setStyle(Style.FILL);
		foreground.setTextSize(height*0.75f);
		foreground.setTextScaleX(width / height);
		foreground.setTextAlign(Paint.Align.CENTER);
		
		Paint foregroundnow = new Paint(Paint.ANTI_ALIAS_FLAG);
		foregroundnow.setColor(getResources().getColor(R.color.white));
		foregroundnow.setStyle(Style.FILL);
		foregroundnow.setTextSize(height*0.75f);
		foregroundnow.setTextScaleX(width / height);
		foregroundnow.setTextAlign(Paint.Align.CENTER);
		
		FontMetrics fm = foreground.getFontMetrics();
		
		float x = width/2;
		
		float y = height/2 - (fm.ascent + fm.descent)/2;
		
		for (int i = 0;i < 9;i++){ 
			for (int j = 0;j < 9;j++){
				String NUM = Game.get(i,j);
				if (NUM != "") 
				canvas.drawText(Game.get(i,j), i * width + x, j * height + y, foreground);
				else canvas.drawText(Game.getx(i,j), i * width + x, j * height + y, foregroundnow);
			  }
		}
		
	  
	
		
	
	}


}
