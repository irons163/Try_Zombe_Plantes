package com.example.try_shoot_deffen;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.try_gameengine.framework.Data;
import com.example.try_gameengine.framework.GameModel;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.Cat;
import com.example.try_shoot_deffen.model.Defener;
import com.example.try_shoot_deffen.model.DefenerBuilder;
import com.example.try_shoot_deffen.model.MapTileObject;
import com.example.try_shoot_deffen.model.Warrior;
import com.example.try_shoot_deffen.model.Zombe;
import com.example.try_shoot_deffen.model.ZombeBuilder;
import com.example.try_shoot_deffen.summerzie.Summerize;
import com.example.try_shoot_deffen.utils.MapTileUtil;

public class MyGameModel extends GameModel{
	public Defener player;
//	Hamster hamster;
	public Cat cat;
	
//	Bullets bullets;
	
//	List<Bullets> bulletsList = new ArrayList<Bullets>();
	
	int ballLevel = 0;
	public int hitBrickLevelDownCount;
	public static boolean waitGameSuccessProcessing = false;
	static boolean waitHamsterInjureProcessing = false;
	public static boolean GAME_PAUSE_FLAG = false;
	
	public static boolean isAllScreenQuake = false;
	public int allScrennQuakeTriggerCount;
	public final int ALL_SCREEN_QUAKE_COUNT = 40; 
	
	public static final int INIT_MAX_CAT_HP = 15;
	
	public static boolean gameFlag = true;
	
	public static final Object LOCK = new Object();
	
	static final int GAME_MAX_HP = 3;
	int gameHp = GAME_MAX_HP;
	
	int stickMovePointIndex = 0;
	
	private boolean bStickTouched = false;
	
	private MapTileUtil mapTileUtil;
	
	private Zombe zombe;
	
	private List<BattleableSprite> zombes = new CopyOnWriteArrayList<BattleableSprite>();
	private List<BattleableSprite> fighters = new CopyOnWriteArrayList<BattleableSprite>();
	
	private ZombeBuilder zombeBuilder;
	
	BattleableSprite warrior;
	
	BattleableSprite medic;
	
	public MyGameModel(Context context, Data data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	
		player = new Defener(100, 100, false);
		player.setPosition(500, 1000);
		
//		bullets = new Bullets(context, 100, 100, false, 0);
//		bullets.setMoveRage(0, 0, CommonUtil.screenHeight,
//				CommonUtil.screenWidth);
//		bullets.setType(0);
		
		mapTileUtil = new MapTileUtil();
		
		zombe = new Zombe(800, 100, false);
		zombe.setPosition(1000, 500);
		zombes.add(zombe);
		
		zombeBuilder = new ZombeBuilder();
		
		warrior = (BattleableSprite) Summerize.summerize2(context, 500, 500, 0);
		
		medic = (BattleableSprite) Summerize.summerize3(context, 200, 500, 0);
		
		fighters.add(warrior);
		fighters.add(medic);
	}
	
	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		
		if (gameFlag) {
			int action = event.getAction();
			int pointerCount = event.getPointerCount();
			int pointerId = 0;
			if (pointerCount > 1) {
				pointerId = (action & MotionEvent.ACTION_POINTER_ID_MASK) >>> MotionEvent.ACTION_POINTER_ID_SHIFT;
			}

			if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_DOWN) {
				x = event.getX(pointerId);
				y = event.getY(pointerId);
					stickMovePointIndex = pointerId;
					bStickTouched = true;
			} else if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_MOVE) {
				System.out.println("bStickTouched= " + bStickTouched);
				System.out.println("pointerId= " + pointerId);
				boolean flag = false;
						
				for (int i = 0; i < pointerCount; ++i) {
					pointerId = event.getPointerId(i);
					Log.d("pointer id - move", Integer.toString(pointerId));

					if (bStickTouched && stickMovePointIndex == pointerId ) {
						Log.d("pointer id ", Integer.toString(pointerId)+"stickMovePointIndex"+stickMovePointIndex);
						
						if(pointerId>=pointerCount){
							x = event.getX(pointerId - ((pointerId+1)-pointerCount));
							y = event.getY(pointerId - ((pointerId+1)-pointerCount));
//							stickMovePointIndex = pointerCount;
						}else{
							x = event.getX(pointerId);
							y = event.getY(pointerId);
						}
						
						
					}
				}
			} else if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_UP) {
				if (bStickTouched && stickMovePointIndex == pointerId) {
					bStickTouched = false;
				}

			}
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			bStickTouched = false;
		}
		
		MapTileObject mapTileObject = mapTileUtil.checkTouchXY(x, y);
		if(mapTileObject != null){
//			mapTileObject.setSprite(new Defener(mapTileObject.getX(), mapTileObject.getY(), false));
//			mapTileObject.setSprite(DefenerBuilder.createHamster1(context, mapTileObject.getX(), mapTileObject.getY()));
			BattleableSprite defener = DefenerBuilder.createBySelect(context, mapTileObject.getX(), mapTileObject.getY());
			if(defener!=null)
					mapTileObject.setSprite(defener);
		}
		
		super.onTouchEvent(event);
	}
	
	private void checkMapDefenerInBattle(){
		mapTileUtil.checkMapDefenersBattleWithMonsters(zombes);
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		super.process();
		
//		bullets.frameTrig();
		
		Zombe zombeNew = zombeBuilder.createZombe();
		
		if(zombeNew!=null){
			zombes.add(zombeNew);
		}
		
//		zombe.frameTrig();
		
		for(BattleableSprite zombe : zombes){
			zombe.frameTrig();
		}
		
		warrior.frameTrig();
		
		medic.frameTrig();
		
		mapTileUtil.frameTrig();
		
//		BattleUtil.checkBattle(player, zombe);
		
//		BattleUtil.checkBattle(battleableSpriteDefeners, battleableSpriteMonsters);
		
		warrior.checkIfInBattleRangeThenAttack(zombes);
		
		medic.checkIfInBattleRangeThenAttack(fighters, zombes);
		
		checkMapDefenerInBattle();
		
		for(BattleableSprite zombe : zombes){
			if(zombe.getX() < 0 || zombe.isNeedRemove()){
				zombes.remove(zombe);
				
				zombe.setIsBattleable(false);
			}
		}
		
//		monsters.add(new Monster(context, x, y, autoAdd, type_direction);
	}
	
	@Override
	protected void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.doDraw(canvas);
		
		player.drawSelf(canvas, null);
		
//		bullets.drawSelf(canvas, null);
		
//		zombe.drawSelf(canvas, null);		
		
		mapTileUtil.drawSelf(canvas, null);
		
		LayerManager.getInstance().drawLayers(canvas, null);
		
		for(BattleableSprite zombe : zombes){
			zombe.drawSelf(canvas, null);
		}
		
		warrior.drawSelf(canvas, null);
		
		medic.drawSelf(canvas, null);
	}
	
	public int getBallLevel() {
		return ballLevel;
	}
	
	public void setBallLevel(int ballLevel) {
		this.ballLevel = ballLevel;
		// ball.setBallLevel(ballLevel);
	}
	
	public void setWeapenChange(){
		player.setBulletLevel();
	}
	
	public void setWeapenChange2(){
		player.setBulletLevel2();
	}
	
	public void setWeapenChange3(){
		player.setBulletLevel3();
	}
	
	public void setWeapenChange5(){
		player.setBulletLevel5();
	}
	
	public boolean isGameRun() {
		return isGameRun;
	}
	
	public boolean isBallRun() {
		return !isGameStop;
	}
	
	public void addHp(){
		if(gameHp < GAME_MAX_HP){
		gameHp++;
		}
	}
}