package com.example.try_shoot_deffen.utils;

import java.util.ArrayList;
import java.util.Random;

import com.example.try_shoot_deffen.MyGameModel;
import com.example.try_shoot_deffen.model.Bullet;
import com.example.try_shoot_deffen.model.Cat;
import com.example.try_shoot_deffen.model.Monster;

public class EffectUtil {
	// BallView ballView;
	MyGameModel ballView;
	final int TYPE_NUM = 7;
	EffectType whichEffectType;
	int needHitCount;
	final int TIME_EFFECT_COUNT = 60;
	int timeCount = TIME_EFFECT_COUNT;
	ToolUtil toolUtil;
	Monster brickUtil;
	public boolean ironsCombo = false;
//	private Bullet ball;
	Cat cat;
	int hp;
	
	enum EffectType {
		Once, Twice, Three, Iron, Time, Tool, BallLevelUP, Split
	}

	public EffectUtil(MyGameModel ballView, Monster brickUtil) {
		this.ballView = ballView;
		this.brickUtil = brickUtil;
		// setRandomEffectType();
	}

	public EffectUtil(MyGameModel ballView, Cat cat) {
		this.ballView = ballView;
		this.cat = cat;
		// setRandomEffectType();
	}
	
	// private void setRandomEffectType(){
	// Random random = new Random();
	// int whichType = random.nextInt(TYPE_NUM-1);
	//
	// EffectType[] effectTypes = EffectType.values();
	// whichEffectType = effectTypes[whichType];
	// setInit();
	// }

	public void setEffect(int whichType) {
		EffectType[] effectTypes = EffectType.values();
		whichEffectType = effectTypes[whichType];
		switch (whichEffectType) {
		case Once:
			needHitCount = 1;
			break;
		case Twice:
			needHitCount = 2;
			break;
		case Three:
			needHitCount = 3;
			break;
		case Iron:
			needHitCount = -2;
			break;
		case Time:
			needHitCount = 1;
			break;
		case Tool:
			needHitCount = 1;
			break;
		case BallLevelUP:
			needHitCount = 1;
			break;
		case Split:
			needHitCount = 1;
			break;
		}
		
		Random random = new Random();
		int temp = random.nextInt(100);
		if(temp<10 && whichEffectType==EffectType.Once){
			setToolEffect();
		}
		
	}

	// private void setInit(){
	// switch (whichEffectType) {
	// case Once:
	// needHitCount = 1;
	// break;
	// case Twice:
	// needHitCount = 2;
	// break;
	// case Three:
	// needHitCount = 3;
	// break;
	// case Iron:
	// needHitCount = -2;
	// break;
	// case Time:
	// needHitCount = 1;
	// break;
	// case Tool:
	// needHitCount = 1;
	// break;
	// case Weapen:
	// needHitCount = 1;
	// break;
	// }
	// }

	public void doEffect() {
//		this.ball = ball;
		switch (whichEffectType) {
		case Once:
//			doHitDetermineHP();
//			break;
		case Twice:
		case Three:
//			doHitDetermine();
			doHitDetermineHP();
			break;
		case Iron:
//			doHitIronDetermine();
			break;
		case Time:
//			doHitDetermine();
//			doTimeCountEffect(showTimeBrickEffectTime);
			break;
		case Tool:
			doHitDetermine();
			setToolEffect();
//			startDownTool();
			break;
		case BallLevelUP:
//			doHitDetermine();
//			doBallLevelUPEffect(ball);
			break;
		case Split:
//			doHitDetermine();
			doHitDetermineHP();
			
			break;
		}
	}
	
	private void newSplitHand(int hp1, int hp2){
		Monster hand = brickUtil.newHand(Monster.TYPE_TWO_HAND_LEFT_UP);
		hand.set(0, ballView);
		hand.setType(hp1);
//		hand.setMoveRage(brickUtil.);
		ballView.cat.hands.add(hand);
		
		hand = brickUtil.newHand(Monster.TYPE_TWO_HAND_RIGHT_UP);
		hand.set(0, ballView);
		hand.setType(hp2);
//		hand.setMoveRage(0,0,CommonUtil.screenHeight,CommonUtil.screenWidth);
		ballView.cat.hands.add(hand);
	}

	private void doHitDetermine() {
		if (needHitCount > 0) {
			int hit = ballView.getBallLevel() > 1 ? 2
					: ballView.getBallLevel() + 1;
			if (needHitCount == 2) {
				ballView.hitBrickLevelDownCount += 1;
			} else if (needHitCount == 3) {
				ballView.hitBrickLevelDownCount += hit;
			}
			needHitCount = needHitCount - hit > 0 ? needHitCount - hit : 0;
		}
	}
	
	interface Callback {
		public void newSliptHand();
	}
	
	private void doHitDetermineHP() {
		if (needHitCount > 0) {
			int hit = ballView.getBallLevel() > 1 ? 2
					: ballView.getBallLevel() + 1;
			if (needHitCount > 1) {
//				ballView.hitBrickLevelDownCount += 1;
				ballView.hitBrickLevelDownCount += hit;
			} else if (needHitCount == 3) {
				ballView.hitBrickLevelDownCount += hit;
			}
			needHitCount = needHitCount - hit > 0 ? needHitCount - hit : 0;
			
			if(needHitCount>=1){
//				newSplitHand();
//				needHitCount--;
				newSplitHand(needHitCount-1, needHitCount-1);
//				if(isHasTool())
//					toolUtil = null;
				needHitCount = 0;
			}
				
		}
	}

//	private void doHitIronDetermine() {
//		if (ballView.getBallLevel() == 2) {
//			if (needHitCount == -2) {
//				ballView.hitIronBrickLevelDownCount += 1;
//			}
//			needHitCount++;
//			ironsCombo = true;
//		} else {
//			ironsCombo = false;
//		}
//	}

	private void doTimeCountEffect(ArrayList<EffectUtil> showTimeBrickEffectTime) {

		// handler.postDelayed(runnable, 1000);
		if (showTimeBrickEffectTime.size() != 0) {
			int currentTime = showTimeBrickEffectTime.get(0)
					.getToolTimerThread().getCurrentTime();
			if (currentTime != 0) {
				int newTime = currentTime - TIME_EFFECT_COUNT / 2;
				if (newTime < 0)
					newTime = 0;
				showTimeBrickEffectTime.get(0).getToolTimerThread()
						.setCurrentTime(newTime);
				return;
			}
		}
		timerThread = new TimerThread(TIME_EFFECT_COUNT);
		timerThread.start();
		showTimeBrickEffectTime.add(this);
	}

//	Handler handler = new Handler();
//	Runnable runnable = new Runnable() {
//		@Override
//		public void run() {
//			ballView.setBallTime(timeCount);
//			if (timeCount > 0) {
//				timeCount--;
//				handler.postDelayed(this, 1000);
//			}
//		}
//	};

	public TimerThread getToolTimerThread() {
		return timerThread;
	}

	private TimerThread timerThread;
	private int toolEffectTwinkingAlpha = 255;
	private int toolEffectFinishAlpha = 255;

	public int getFinishAlpha() {
		return toolEffectFinishAlpha;
	}

	public void setFinishAlpha(int toolEffectFinishAlpha) {
		this.toolEffectFinishAlpha = toolEffectFinishAlpha;
	}

	private void setToolEffect() {
		toolUtil = new ToolUtil(ballView, brickUtil);
	}

	public int getTwinklingAlpha() {
		return toolEffectTwinkingAlpha;
	}

	public void setTwinklingAlpha(int toolEffectTwinkingAlpha) {
		this.toolEffectTwinkingAlpha = toolEffectTwinkingAlpha;
	}

//	public void doEffectFinish(ArrayList<Bullet> ballUtils) {
//		for (Bullet ball : ballUtils) {
//			ball.setBallLevel(-5);
//		}
//		// ball.setBallLevel(-5);
//	}

	// private void downTool(){
	// toolUtil.getToolRect();
	// }

//	private void doBallLevelUPEffect(Bullet ball) {
//		// int ballLevel = ballView.getBallLevel()>1 ?
//		// -1:ballView.getBallLevel()+1;
//		// ballView.setBallLevel(ballLevel);
//		int ballLevel = ball.getBallLevel() > 1 ? -1 : ball.getBallLevel() + 1;
//		ball.setBallLevel(ballLevel);
//	}

	public int getNeedHitCount() {
		return needHitCount;
	}

	public boolean isHasTool() {
		return toolUtil != null;
	}

	public ToolUtil getToolObj() {
		if(isHasTool())
			toolUtil.setXY(brickUtil);
		return toolUtil;
	}

//	public void startDownTool() {
//		toolUtil.isStartDownTool = true;
//	}

	// class EffectOnce{
	//
	// private final static EffectOnce II = new EffectOnce();
	//
	// private EffectOnce(){}
	//
	// public static EffectOnce getInstance(){
	// return EFFECTONCE;
	// }
	//
	// }
}
