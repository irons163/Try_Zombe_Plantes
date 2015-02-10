package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.framework.IActionListener;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.script.Script;
import com.example.try_gameengine.script.ScriptPaser;
import com.example.try_shoot_deffen.MyGameModel;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;
import com.example.try_shoot_deffen.utils.BitmapUtil;
import com.example.try_shoot_deffen.utils.CommonUtil;
import com.example.try_shoot_deffen.utils.EffectUtil;

public class Cat extends Sprite {
	List<MovementAction> movementActions = new ArrayList<MovementAction>();
	public List<Monster> hands = new CopyOnWriteArrayList<Monster>();
	MyGameModel gameModel;
	Context context;
	Attribute attribute;
	AttributeHelper attributeHelper;
	long lastShootTime;
	int catHp = MyGameModel.INIT_MAX_CAT_HP;
	boolean isInDing = false;
	MovementAction movementActionDead;
	MovementAction movementAction;
	public boolean isAnimation = false;
	
enum CatAction{

		
		Move(
		"MOVE",
		BitmapUtil.catBitmaps[0]),
		
		Injure(
				"Injure",
				BitmapUtil.catInjureBitmaps[0]),	
				
		Dead(
				"Dead",
				BitmapUtil.catDeadBitmaps[0]),
		;

		String name;
		Bitmap[] bitmaps;

		private CatAction(String name, Bitmap[] bitmaps) {
			this.name = name;
			this.bitmaps = bitmaps;
		}

		public String getName() {
			return name;
		}

		public Bitmap[] getBitmaps() {
			return bitmaps;
		}
		
		public void setBitmaps(Bitmap[] bitmaps){
			this.bitmaps = bitmaps;
		}
	}

	public Cat(MyGameModel gameModel, Context context, float x, float y,
			boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		
		this.gameModel = gameModel;
		this.context = context;

		Random random = new Random();
		int type = random.nextInt(BitmapUtil.catBitmaps.length);
		
		setBitmapAndAutoChangeWH(BitmapUtil.catBitmaps[type][0]);
		
		CatAction.Move.setBitmaps(BitmapUtil.catBitmaps[type]);
		CatAction.Injure.setBitmaps(BitmapUtil.catInjureBitmaps[type]);
		CatAction.Dead.setBitmaps(BitmapUtil.catDeadBitmaps[type]);
		
		addActionFPS(CatAction.Move.getName(), CatAction.Move.getBitmaps(), new int[]{30,30,30});
		addActionFPS(CatAction.Injure.getName(), CatAction.Injure.getBitmaps(), new int[]{0,70,30}, false, new IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
				move(0, 20);
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
				moveWithPx(0, -getY());
				setAction(CatAction.Move.getName());
			}
		});
		
		addActionFPS(CatAction.Dead.getName(), CatAction.Dead.getBitmaps(), new int[]{0,150,30}, false, new IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
//				setAction(CatAction.Move.getName());
				isInDing = false;
				setAction(CatAction.Move.getName());
			}
		});
		
		initAttribute();
		initCollisiontRectF();
		initMove(context);

	}

	private void initAttribute() {
		attribute = new Attribute();
		float interval = new BigDecimal(5.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}
	
	private void initCollisiontRectF(){
		setCollisionRectFEnable(true);
		float collisionWidth = w/4.0f*3;
		float collisionHitght = h/4.0f*3;
		float collisionOffsetX = w/2-collisionWidth/2;
		float collisionOffsetY = h/2-collisionHitght/2;
		setCollisionOffsetXY(collisionOffsetX, collisionOffsetY);
		setCollisionRectFWH(collisionWidth, collisionHitght);
		setCollisionRectF(getX()+collisionOffsetX, getY()+collisionOffsetY, getX()+collisionOffsetX+collisionWidth, getY()+collisionOffsetY+collisionHitght);
	}

	boolean isResum = true;

	private void initMove(Context context) {
		Script script = new Script();
		final ScriptPaser scriptPaser = new ScriptPaser();
		scriptPaser.paser(context, this, "111.txt");

		scriptPaser
				.setScriptTriggerLisener(new ScriptPaser.ScriptTriggerLisener() {

					@Override
					public void onTriggerBefforeCommand() {
						// TODO Auto-generated method stub
						if (scriptPaser.isMove()) {
							float dx = scriptPaser.getDx();
							Log.e("script dx", dx + "");
							if (scriptPaser.getDx() > 0) {
								if (getX() + w >= moveRage.right) {
									scriptPaser.setDx(-scriptPaser.getDx());
								}
							} else {
								if (getX() <= moveRage.left) {
									scriptPaser.setDx(-scriptPaser.getDx());
								}
							}
						}
					}

					@Override
					public void onTriggerAffterCommand() {
						// TODO Auto-generated method stub

					}
				});

		movementAction = new MovementActionSetWithThreadPool();
		MovementActionInfo info = new MovementActionInfo(50000000, 1, 5, 5, "",
				null, false, this, CatAction.Move.getName());
		MovementActionItemBaseReugularFPS action = new MovementActionItemBaseReugularFPS(
				info);
		movementAction.addMovementAction(action);
		movementAction.setIsLoop(true);

		movementActions.add(movementAction);

		movementAction
				.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {

					@Override
					public void onTick(float dx, float dy) {
						// TODO Auto-generated method stub
						scriptPaser.nextScriptLine();
						if (scriptPaser.isPause()) {
							if (scriptPaser.getDx() > 0) {
								// setAction(SheepMove.StandR.getName());
							} else {
								// setAction(SheepMove.Stand.getName());
							}

							isResum = true;
						} else if (isResum && scriptPaser.isMove()) {
							float dxx = scriptPaser.getDx();
							if (dxx > 0) {
								// setAction(SheepMove.MoveR.getName());
							} else {
								// setAction(SheepMove.Move.getName());
							}

							isResum = false;
						}

						scriptPaser.trigger(Cat.this);

					}
				});

		movementAction.initMovementAction();
		movementAction.start();
		setMovementAction(movementAction);
		
		
		movementActionDead = new MovementActionSetWithThreadPool();
		movementActionDead.setMovementActionController(new MovementAtionController());
		info = new MovementActionInfo(100, 5, 0, 5/100.0f*h, "",
				null, false, this, CatAction.Dead.getName());
		action = new MovementActionItemBaseReugularFPS(
				info);
		movementActionDead.addMovementAction(action);

		movementActions.add(movementActionDead);

		movementActionDead
				.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {

					@Override
					public void onTick(float dx, float dy) {
						// TODO Auto-generated method stub
						if(Cat.this.getY() > CommonUtil.cat_bg_height){
//							movementActionDead.controller.cancelAllMove();
							return;
						}
//						move(dx, dy);
						moveWithPx(dx, dy);
					}
				});

		movementActionDead.initMovementAction();
//		movementActionDead.start();
	}

	private final int Brick_Once = 0;
	private final int Brick_Twice = 1;
	private final int Brick_Three = 2;
	private final int Brick_Iron = 3;
	private final int Brick_Time = 4;
	private final int Brick_Tool = 5;
	private final int Brick_BallLevelUP = 6;

	int whichBrickType;

	EffectUtil effectUtil;

	enum ShootType {
		Normal, ThreeHit, SplitTwo, TripleTwoHit, Continue, Tool, Split
	}

	ShootType shootType = ShootType.Normal;
	int combo;
	
	public void shootHand() {
		if(isInDing)
			return;
		long currentTime = System.currentTimeMillis();
		if (!attributeHelper.isCanShoot(lastShootTime, currentTime)) {
			return;
		}

		lastShootTime = currentTime;
		Random random = new Random();
		int temp = random.nextInt(100);

		// if(temp < 20){
		// shootType = ShootType.ThreeHit;
		// }else if(temp < 40){
		// shootType = ShootType.TripleTwoHit;
		// }else if(temp < 60){
		// shootType = ShootType.Tool;
		// }else
		
			if (temp < 10) {
				temp = random.nextInt(100);
				if (temp < 40) {
					shootType = ShootType.ThreeHit;
					combo = 0;
				} else if (temp < 70) {
					shootType = ShootType.TripleTwoHit;
					combo = 0;
				} else if (temp < 90) {
					shootType = ShootType.TripleTwoHit;
					combo = 0;
				} else {
					combo++;
				}

			} else {
				shootType = ShootType.Normal;
				combo = 0;
			}

			// setEffect(whichBrickType);
			int comboCounter=combo;
			
			do{
				comboCounter = combo;
				if(comboCounter>0){
					temp = random.nextInt(100);
					if (temp < 40) {
						shootType = ShootType.ThreeHit;
						comboCounter--;
						combo = 0;
					} else if (temp < 70) {
						shootType = ShootType.TripleTwoHit;
						comboCounter--;
						combo = 0;
					} else if (temp < 90) {
						shootType = ShootType.TripleTwoHit;
						comboCounter--;
						combo = 0;
					} else {
						combo++;
						continue;
					}
				}
				
			switch (shootType) {
			case ThreeHit:
				Monster hand = new Monster(context, this.getCenterX() , this.getY()+h, false, Monster.TYPE_SINGLE_MID);
//				hand.setX(hand.getX() - hand.w/2);
				hand.setPosition(hand.getX() - hand.w/2, hand.getY() - hand.h/4);
				hand.set(0, gameModel);
				hand.setType(2);
				hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
						CommonUtil.screenWidth);
				hands.add(hand);
				break;
			case TripleTwoHit:
				hand = new Monster(context, this.getCenterX(), this.getY()+h, false, Monster.TYPE_THREE_HAND_LEFT);
//				hand.setX(hand.getX() - hand.w/2);
				hand.setPosition(hand.getX() - hand.w/2, hand.getY() - hand.h/4);
				hand.set(0, gameModel);
				hand.setType(1);
				hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
						CommonUtil.screenWidth);
				hands.add(hand);
				hand = new Monster(context, this.getCenterX(), this.getY()+h, false, Monster.TYPE_THREE_HAND_MID);
//				hand.setX(hand.getX() - hand.w/2);
				hand.setPosition(hand.getX() - hand.w/2, hand.getY() - hand.h/4);
				hand.set(0, gameModel);
				hand.setType(1);
				hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
						CommonUtil.screenWidth);
				hands.add(hand);
				hand = new Monster(context, this.getCenterX(), this.getY()+h, false, Monster.TYPE_THREE_HAND_RIGHT);
//				hand.setX(hand.getX() - hand.w/2);
				hand.setPosition(hand.getX() - hand.w/2, hand.getY() - hand.h/4);
				hand.set(0, gameModel);
				hand.setType(1);
				hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
						CommonUtil.screenWidth);
				hands.add(hand);
				break;
//			case Tool:
//				hand = new Hand(context, this.getCenterX(), this.getCenterY(), false, Hand.TYPE_SINGLE_MID);
//				hand.setX(hand.getX() - hand.w/2);
//				hand.set(0, gameModel);
//				hand.setType(4);
//				hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
//						CommonUtil.screenWidth);
//				hands.add(hand);
//				break;
//			case SplitTwo:
//				hand = new Hand(context, this.getCenterX(), this.getCenterY(), false, Hand.TYPE_SINGLE_MID);
//				hand.setX(hand.getX() - hand.w/2);
//				hand.set(0, gameModel);
//				hand.setType(7);
//				hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
//						CommonUtil.screenWidth);
//				hands.add(hand);
//				break;
			default:
				hand = new Monster(context, this.getCenterX(), this.getY()+h, false, Monster.TYPE_SINGLE_MID);
//				hand.setX(hand.getX() - hand.w/2);
				hand.setPosition(hand.getX() - hand.w/2, hand.getY() - hand.h/4);
				hand.set(0, gameModel);
				temp = random.nextInt(3);
				hand.setType(temp);
				hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
						CommonUtil.screenWidth);
				hands.add(hand);
				break;
			}
			
		}while(comboCounter>0);
			
		
		// setAction(SheepMove.Move.getName());
	}

	private void setEffect(int whichBrickType) {
		effectUtil = new EffectUtil(gameModel, this);
		effectUtil.setEffect(5);
	}

	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();

		if(!isInDing())
		for (Monster hand : hands) {
			hand.frameTrig();
		}
	}

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		boolean isQuakeLeft = true;
		if(gameModel.isAllScreenQuake){
			gameModel.allScrennQuakeTriggerCount++;
			if(gameModel.allScrennQuakeTriggerCount < gameModel.ALL_SCREEN_QUAKE_COUNT){
				if((gameModel.allScrennQuakeTriggerCount/(gameModel.ALL_SCREEN_QUAKE_COUNT/12))%2==0 ){
					isQuakeLeft = true;
				}else{
					isQuakeLeft = false;
				}
//				else if(allScrennQuakeTriggerCount < ALL_SCREEN_QUAKE_COUNT/4*2){
//					canvas.drawBitmap(bg, 10, 0, null);
//				}else if(allScrennQuakeTriggerCount < ALL_SCREEN_QUAKE_COUNT/4*3){
//					canvas.drawBitmap(bg, -10, 0, null);
//				}else {
//					canvas.drawBitmap(bg, 10, 0, null);
//				}
			}else{
				gameModel.isAllScreenQuake = false;
				gameModel.allScrennQuakeTriggerCount = 0;
			}
		}
		
		for (Monster hand : hands) {
			if(gameModel.isAllScreenQuake){
			if(isQuakeLeft)
				hand.drawOffsetX = -20;
			else
				hand.drawOffsetX = 20;
			}else{
				hand.drawOffsetX = 0;
			}
			hand.drawSelf(canvas, null);
		}
		
		super.drawSelf(canvas, paint);		
	}
	
	@Override
	public void customBitampSRCandDST(Rect src, RectF dst) {
		// TODO Auto-generated method stub
		src.bottom = (int) (CommonUtil.cat_bg_height - getY());
		dst.bottom = CommonUtil.cat_bg_height;
 	}
	
	public int getCatHp(){
		return catHp;
	}

	public void beHit(int bulletLevel){
		catHp -= (bulletLevel+1);
		if(catHp<=0){
			isInDing = true;
			isAnimation = true;
//			setAction(CatAction.Dead.getName());
			movementActionDead.start();
			setMovementAction(movementActionDead);
		}
		else
			setAction(CatAction.Injure.getName());
	}
	
	public boolean isInDing(){
		return isInDing;
	}
	
	public void reset(){
		initAttribute();
		resetCatType();
		resetMovementAction();
		
	}
	
	public void resetMovementAction(){
		setAction(CatAction.Move.getName());
		setMovementAction(movementAction);
	}
	
	public void resetCatType(){
		Random random = new Random();
		int type = random.nextInt(BitmapUtil.catBitmaps.length);
		
		setBitmapAndAutoChangeWH(BitmapUtil.catBitmaps[type][0]);
		
		CatAction.Move.setBitmaps(BitmapUtil.catBitmaps[type]);
		CatAction.Injure.setBitmaps(BitmapUtil.catInjureBitmaps[type]);
		CatAction.Dead.setBitmaps(BitmapUtil.catDeadBitmaps[type]);
		
		addActionFPS(CatAction.Move.getName(), CatAction.Move.getBitmaps(), new int[]{30,30,30});
		addActionFPS(CatAction.Injure.getName(), CatAction.Injure.getBitmaps(), new int[]{0,30,30}, false, new IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				move(0, 20);
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
				moveWithPx(0, -getY());
				setAction(CatAction.Move.getName());
			}
		});
		
		addActionFPS(CatAction.Dead.getName(), CatAction.Dead.getBitmaps(), new int[]{0,150,30}, false, new IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
//				setAction(CatAction.Move.getName());
				isInDing = false;
				setAction(CatAction.Move.getName());
			}
		});
	}
}
