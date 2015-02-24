package com.example.try_shoot_deffen.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.example.try_shoot_deffen.MyGameModel;
import com.example.try_shoot_deffen.model.Bullet;
import com.example.try_shoot_deffen.model.Bullets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class ToolUtil {
//	BallView ballView;
	MyGameModel ballView;
	final int TYPE_NUM = 5;
	ToolType whichToolType;
	int toolObjLeft, toolObjTop;
	public Bitmap toolBitmap;
	Bitmap twinkleBitmap;
	RectF toolRect = new RectF();
//	boolean isStartDownTool = false;
	public final static int TOOL_DOWN_SPEED = 4;
	private TimerThread timerThread;
	private int saveStickLong;
	private int saveBallRadius;
	private int saveBallSpeedX;
	private int saveBallSpeedY;
//	private BallUtil ball;
	private Bullet ball;

	private final int twinkleFriquenceInterval = 30;
	private int twinkleCounter = 0;
	
	enum ToolType {
		DoubleBullet, TripleBullet, FrequenceUpBullet, DoublePowerBullet, CureAddHp
	}

	public ToolUtil(MyGameModel ballView, Bullets brickUtil) {
		this.ballView = ballView;		
		
		setRandomEffectType();
		setToolBitmap();
	}
	
	
	public void setXY(Bullets brickUtil){
		setToolObjectXY(brickUtil.dst.left, brickUtil.dst.top, brickUtil.dst.right,
				brickUtil.dst.bottom);
	}

	private void setRandomEffectType() {
		Random random = new Random();
		int whichType = random.nextInt(TYPE_NUM);
		ToolType[] toolTypes = ToolType.values();
		whichToolType = toolTypes[whichType];
		
//		whichToolType = ToolType.BallSpeedUp;
//		if(whichType%2==0)
//			whichToolType = ToolType.BallCountUpToThree;
//		else
//			whichToolType = ToolType.BallReset;
		
//		if(whichToolType == ToolType.BlackHole){
//			int randomBlackHoleMinY = ballView.getBricksAreaBottom();
//			int randomBlackHoleMaxY = (int) (ballView.heightScreen - ballView.THICK_OF_STICK - toolRect.height());
//			int randomBlackHoleY = random.nextInt(randomBlackHoleMaxY - randomBlackHoleMinY +1) + randomBlackHoleMinY;
//			toolObjTop = randomBlackHoleY;
//			int randomBlackHoleX = random.nextInt((int) (ballView.widthScreen - toolRect.width()));
//			toolRect.set(randomBlackHoleX, toolObjTop, randomBlackHoleX + toolRect.width(), toolObjTop + toolRect.height());
//		}
	}

	private void setToolObjectXY(float BrickLeft, float BrickTop, float BrickRight,
			float BrickBottom) {
		int height = (int) ((BrickBottom - BrickTop)*1/2);
		int width = height;
		toolObjTop = (int) BrickTop;
		toolObjLeft = (int) ((BrickLeft + BrickRight - width) / 2);
		toolRect.set(toolObjLeft, toolObjTop, toolObjLeft + width, toolObjTop
				+ height);
	}

	private void setToolBitmap() {
		
		
		switch (whichToolType) {
		case DoubleBullet:
			toolBitmap = BitmapUtil.tool_DoubleBullets_bmp;
			twinkleBitmap = BitmapUtil.tool_DoubleBullets_bmp02;
			break;
		case TripleBullet:
			toolBitmap = BitmapUtil.tool_TripleBullets_bmp;
			twinkleBitmap = BitmapUtil.tool_TripleBullets_bmp02;
			break;
		case FrequenceUpBullet:
			toolBitmap = BitmapUtil.tool_doubleSpeedBullets_bmp;
			twinkleBitmap = BitmapUtil.tool_doubleSpeedBullets_bmp02;
			break;
		case DoublePowerBullet:
			toolBitmap = BitmapUtil.tool_doublePowerBullets_bmp;
			twinkleBitmap = BitmapUtil.tool_doublePowerBullets_bmp02;
			break;
		case CureAddHp:
			toolBitmap = BitmapUtil.tool_Cure_AddHp_bmp;
			twinkleBitmap = BitmapUtil.tool_Cure_AddHp_bmp02;
			break;
		}
	}

	public void doTool(Bullet ball,
			ArrayList<ToolUtil> showToolEffectTime) {
		
		switch (whichToolType) {
		case DoubleBullet:
			// ball.setSpeedX(ball.getSpeedX() * 2);
			// ball.setSpeedY(ball.getSpeedY() * 2);
			
			this.ball = ball;
//			ballView.setBallSpeed(ballView.getBallSpeedX() * 2,
//					ballView.getBallSpeedY() * 2);
			
			float interval = new BigDecimal(1.0f/1.0f)
	        .setScale(2, BigDecimal.ROUND_HALF_UP)
	        .floatValue();
			ballView.setBallLevel(0);
			ballView.setWeapenChange2();
			ballView.player.attributeHelper.attribute.setInterval(interval);
//			timerThread = new TimerThread(10);
//			timerThread.start();
			showToolEffectTime.add(this);
			break;
		case TripleBullet:
			// ball.setSpeedX(ball.getSpeedX() / 2);
			// ball.setSpeedY(ball.getSpeedY() / 2);
			this.ball = ball;
			interval = new BigDecimal(1.0f/1.0f)
	        .setScale(2, BigDecimal.ROUND_HALF_UP)
	        .floatValue();
			ballView.setBallLevel(0);
			ballView.setWeapenChange3();
			ballView.player.attributeHelper.attribute.setInterval(interval);
//			timerThread = new TimerThread(10);
//			timerThread.start();
			showToolEffectTime.add(this);
			break;
		case FrequenceUpBullet:
			this.ball = ball;
			interval = new BigDecimal(0.7f/1.0f)
	        .setScale(2, BigDecimal.ROUND_HALF_UP)
	        .floatValue();
			ballView.setBallLevel(0);
			ballView.setWeapenChange();
			ballView.player.attributeHelper.attribute.setInterval(interval);
//			timerThread = new TimerThread(10);
//			timerThread.start();
			showToolEffectTime.add(this);
			break;
		case DoublePowerBullet:
			this.ball = ball;
			interval = new BigDecimal(1.0f/1.0f)
	        .setScale(2, BigDecimal.ROUND_HALF_UP)
	        .floatValue();
			ballView.setBallLevel(1);
			ballView.setWeapenChange5();
			ballView.player.attributeHelper.attribute.setInterval(interval);
//			timerThread = new TimerThread(10);
//			timerThread.start();
			showToolEffectTime.add(this);
			break;
		case CureAddHp:
			ballView.addHp();
			break;
		}
	}

	public RectF getToolRect() {
		return toolRect;
	}

//	public boolean isStartDownTool() {
//		return isStartDownTool;
//	}

	public void moveDownToolObj() {
//		if(whichToolType != ToolType.BlackHole){
		if(toolRect.bottom<CommonUtil.gameBoardHeight)
			toolRect.offset(0, TOOL_DOWN_SPEED);
//		else{
			
//			time.start();
			
//			if(timerThread.getCurrentTime() < 0){
//				
//			}
//		}
			
//		}	
	}
	
	public void toolTimerStart(){
		time.start();
	}
	
	public TimerThread getToolTimerThread(){
		return timerThread;
	}
	
	private int toolEffectTwinkingAlpha = 255;
	private int toolEffectFinishAlpha = 255;
	
	public int getFinishAlpha(){
		return toolEffectFinishAlpha;
	}
	
	public void setFinishAlpha(int toolEffectFinishAlpha){
		this.toolEffectFinishAlpha = toolEffectFinishAlpha;
	}
	
	public int getTwinklingAlpha(){
		return toolEffectTwinkingAlpha;
	}
	
	public void setTwinklingAlpha(int toolEffectTwinkingAlpha){
		this.toolEffectTwinkingAlpha = toolEffectTwinkingAlpha;
	}

	final int Init_Max_Stick_Long_Count = 20;
	int count = Init_Max_Stick_Long_Count;
	Thread time = new Thread(new Runnable() {
		public void run() {
			boolean flag = true;
			while (ballView.isGameRun() && flag) {
//				while (ballView.isGameRun()) {
				if(ballView.isBallRun())
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!flag) {
						break;
					}
					if (ballView.isGameRun()) {
						count--;
						if(count<=0)
							flag = false;
//						((MainActivity) ballView.context).handler
//								.sendEmptyMessage(6);
					}
//				}
			}
		}
	});
	
	public void twinkle(Canvas canvas){
		twinkleCounter++;
		if(twinkleCounter<twinkleFriquenceInterval/2){
			canvas.drawBitmap(toolBitmap, null, getToolRect(), null);
		}else{
			canvas.drawBitmap(twinkleBitmap, null, getToolRect(), null);
			if(twinkleCounter>=twinkleFriquenceInterval){
				twinkleCounter = 0;
			}
		}
	}
	
	public boolean timeOut(){
		return count<=0;
	}
}
