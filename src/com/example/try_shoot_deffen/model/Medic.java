package com.example.try_shoot_deffen.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.try_gameengine.action.MathUtil;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.framework.IActionListener;
import com.example.try_shoot_deffen.model.Defener2.SheepMove;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;
import com.example.try_shoot_deffen.utils.BattleUtil;
import com.example.try_shoot_deffen.utils.BitmapUtil;

public class Medic extends DefenerMoveable{
	Attribute attribute;
	public AttributeHelper attributeHelper;
	long lastShootTime;
	boolean isPrepareToShoot = false;
	boolean isShooting = false;
	boolean isMoveing = false;
	int timeCounter;
	int shootBulletLevel = 0;
	List<Bullet> bullets = new ArrayList<Bullet>();
	boolean isInjure = false;
	int HAMSTER_INJURE_TIME = 40;
	int hamsterInjureCounter;
	boolean isInvincible = false;
	
	enum SheepMove {

		Shoot(
				"Shoot",
				new Bitmap[] {
						
						BitmapUtil.hamster,
						BitmapUtil.hamsterShoot,
						BitmapUtil.hamsterShoot2
				}),
				
		Move(
		"RABIT_MOVE",
		new Bitmap[] {
				
				BitmapUtil.hamster,
				BitmapUtil.hamsterShoot,
				BitmapUtil.hamsterShoot2
		}),
		Injure(
				"Injure",
				new Bitmap[] {
						
						BitmapUtil.hamster_injure
				}),		
		;

		String name;
		Bitmap[] bitmaps;

		private SheepMove(String name, Bitmap[] bitmaps) {
			this.name = name;
			this.bitmaps = bitmaps;
		}

		public String getName() {
			return name;
		}

		public Bitmap[] getBitmaps() {
			return bitmaps;
		}
	}
	
	public Medic(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
//		setBitmapAndFrameWH(bitmap, frameWidth, frameHeight);
		
		setBitmapAndFrameWH(BitmapUtil.hamster, BitmapUtil.hamster.getWidth()/7, BitmapUtil.hamster.getHeight()/2);
		
		addActionFPSFrame(SheepMove.Shoot.getName(), new int[]{0,10,11,1}, BattleUtil.changeToNew(new int[]{0,5,5,5}, getSpeed()), false, new IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
//				if(periousFrameId==1)
//					isShooting = false;
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
				isShooting = false;
			}
		});
		
		initCollisiontRectF();
	}
	
	private void initCollisiontRectF(){
		setCollisionRectFEnable(true);
		float collisionWidth = w;
		float collisionHitght = h;
		float collisionOffsetX = w/2-collisionWidth/2;
		float collisionOffsetY = h/2-collisionHitght/2;
		setCollisionOffsetXY(collisionOffsetX, collisionOffsetY);
		setCollisionRectFWH(collisionWidth, collisionHitght);
		setCollisionRectF(getX()+collisionOffsetX, getY()+collisionOffsetY, getX()+collisionOffsetX+collisionWidth, getY()+collisionOffsetY+collisionHitght);
	}
	
	MovementAction movementActionShoot;
	
	public void setMovementAction(final BattleableSprite targetBattleableSprite){
		
		if(movementActionShoot!=null)
			return;
			
		movementActionShoot = new MovementActionSetWithThreadPool();
		movementActionShoot.setMovementActionController(new MovementAtionController());
		MovementActionInfo info = new MovementActionInfo(3000, BattleUtil.changeToNew(1, getSpeed()), -2, 0, "", null, false);
		MovementActionItemBaseReugularFPS reFlectaction = new MovementActionItemBaseReugularFPS(info);
		movementActionShoot.addMovementAction(reFlectaction);
		
		movementActionShoot.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				
				if(targetBattleableSprite == null || !targetBattleableSprite.isBattleable()){
					Log.e("Warrior", "isBattleable:" + targetBattleableSprite.isBattleable());
					movementActionShoot.controller.cancelAllMove();
					movementActionShoot = null;
					return;
				}
				
				float targetX = targetBattleableSprite.getX(); 
				float targetY = targetBattleableSprite.getY(); 
				
				final MathUtil mathUtil = new MathUtil();
				final float angle = mathUtil.getNewAngleTowardsPointF(targetX, targetY, getCenterX(), getCenterY());
					
				mathUtil.setXY(dx, dy);
				
				mathUtil.setINITSPEEDX(mathUtil.genTotalSpeed());
				
				dx = mathUtil.getSpeedX(angle);
				
				dy = mathUtil.getSpeedY(angle);
				
				move(dx, dy);
				
				Log.e("Warrior", "move:dx:" + dx + " dy:" + dy);
				
//				scriptPaser.nextScriptLine();

				
//				scriptPaser.triggerAndDoCommandInSprite();
				
			}
		});
		
		movementActionShoot.initMovementAction();
		
		movementActionShoot.start();
		
		setMovementAction(movementActionShoot);
	}
	
	public boolean checkIfInBattleRangeThenAttackSelf(List<BattleableSprite> battleablesSelf){
		boolean isAttack = super.checkIfInBattleRangeThenAttack(battleablesSelf);
		
		Log.e("isAttack", "[isAttack]:"+isAttack+"");
		
		if(!isAttack)
			checkIfInWatchRangeThenMove(battleablesSelf);
//		else
//			cancelMovementAction();
		
		return isAttack;
	}
	
	
	
	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		
		boolean isAttack = super.checkIfInBattleRangeThenAttack(battleables);
		
		Log.e("isAttack", "[isAttack]:"+isAttack+"");
		
		if(!isAttack)
			checkIfInWatchRangeThenMove(battleables);
//		else
//			cancelMovementAction();
		
		return isAttack;
	}
	
	private void checkIfInWatchRangeThenMove(List<BattleableSprite> battleables){
		for(BattleableSprite battleableSprite : battleables){
			boolean isInWatchRange = isInWatchRange(battleableSprite);
			if(isInWatchRange){ 
				setMovementAction(battleableSprite);
			}
		}
	}
	
	private boolean isInWatchRange(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		if(!battleable.isBattleable()){
			return false;
		}
		
		double distance = Math.pow(getCenterX() - battleable.getCenterX(), 2) + Math.pow(getCenterY() - battleable.getCenterY(), 2);
		distance = Math.sqrt(distance);
//		Log.e("distance", distance+"");
		float watchR = 500;
		if(distance <= watchR){
			return true;
		}
		return false;
	}
	
//	@Override
//	public void frameTrig() {
//		// TODO Auto-generated method stub
//		super.frameTrig();
//		
//		if(weapenSprite!=null)
//			weapenSprite.frameTrig();
//		
//	}
	
	public boolean isInjuring(){
		return isInjure;
	}
	
	public void setBulletLevel(){
		shootBulletLevel = 0;
	}
	
	public void setBulletLevel2(){
		shootBulletLevel = 1;
	}
	
	public void setBulletLevel3(){
		shootBulletLevel = 2;
		timeCounter = 1000;
	}
	
	public void setBulletLevel5(){
		shootBulletLevel = 4;
	}
	
	public void setWeapen(WeapenSprite weapen){
		this.weapenSprite = weapen;
	}
	
	public void setAtkAction(){
		setAction(SheepMove.Shoot.getName());
	}
	
	public void setStopAtkAction(){
		setAction(null);
	}
	
	public void cancelMovementAction(){
		if(movementActionShoot!=null){
			movementActionShoot.controller.cancelAllMove();
			movementActionShoot = null;
		}
	}
	
}
