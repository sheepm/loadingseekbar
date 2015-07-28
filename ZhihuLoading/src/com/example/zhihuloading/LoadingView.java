package com.example.zhihuloading;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class LoadingView extends View {
	
	private Paint mBgPaint;
	private Paint mBlockPaint1;
	private int width;
	private int height;
	
	private Timer timer;
	private Rect rect1;
	private Rect rect2;
	private Rect rect3;
	private Rect rect4;

	int left1,right1;
	int left2,right2;
	int left3,right3;
	int left4,right4;
	
	public LoadingView(Context context) {
		super(context ,null);
	}
	
	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.i("---LoadingView", "LoadingView");
		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
		Log.i("---width", "width:"+width);
		Log.i("---height", "height:"+height);
		left1 =0;
		right1 =10;
		left2 = width/4;
		right2 =width/4 +10;
		left3 =width/2;
		right3 = width/2 +10;
		left4 = width*3/4;
		right4 = width*3/4+10;
		
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
		int bgColor = array.getColor(R.styleable.LoadingView_bgcolor, 0xFF0000FF);
		
		int blockColor = array.getColor(R.styleable.LoadingView_blockcolor, 0xFFFFFFFF);
		
		mBgPaint = new Paint();
		mBgPaint.setAntiAlias(true);
		mBgPaint.setColor(bgColor);
		
		mBlockPaint1= new Paint();
		mBlockPaint1.setAntiAlias(true);
		mBlockPaint1.setColor(blockColor);
		
		array.recycle();
		
		
		rect1 = new Rect(left1, 0, right1, 10);
		rect2 = new Rect(left2, 0, right2, 10);
		rect3 = new Rect(left3, 0,right3, 10);
		rect4 = new Rect(left4, 0, right4, 10);
		timer = new Timer();
		newThread();
	}
	
	public void newThread(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				
				if (right4 < width) {
					left1 =left1+20;
					left2 =left2+20;
					left3 =left3+20;
					left4 = left4+20;
					right1 =right1+20;
					right2=right2+20;
					right3 = right3+20;
					right4 =right4+20;
				}else {
					left1= 0;
					right1 = 10;
					left2=width/4;
					right2 = width/4+10;
					left3= width/2;
					right3 = width/2+10;
					left4 =width*3/4;
					right4 =width*3/4+10;
				}
				rect1 = new Rect(left1, 0, right1, 10);
				rect2 = new Rect(left2, 0, right2, 10);
				rect3 = new Rect(left3, 0, right3, 10);
				rect4 = new Rect(left4, 0, right4, 10);
				
				postInvalidate();
			}
		}, 0, 30); //设置延迟是为了使onDraw先调用
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(new Rect(0, 0,width, 10), mBgPaint);
		canvas.drawRect(rect1, mBlockPaint1);
		canvas.drawRect(rect2, mBlockPaint1);
		canvas.drawRect(rect3, mBlockPaint1);
		canvas.drawRect(rect4, mBlockPaint1);
	}
}
