package com.example.try_shoot_deffen.model;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.action.RotationOnceController;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.model.Bullets.BulletsEventListener;
import com.example.try_shoot_deffen.utils.BitmapUtil;

public class Bullet extends BaseBullet{

	private int type;
	MovementAction movementAction;
	
enum BulletAnimaton {

		
		Move(
		"RABIT_MOVE",
		new int[] {
				
				10,
				10,
				10
		}),
		
		PeaNutMove(
				"PeaNutMove",
				new int[] {
						10,
						10,
						10,
						10,
						10,
						10,
						10,
						10
				}),
					
		;

		String name;
		int[] framesTime;

		private BulletAnimaton(String name, int[] framesTime) {
			this.name = name;
			this.framesTime = framesTime;
		}

		public String getName() {
			return name;
		}

		public int[] getFramesTime() {
			return framesTime;
		}
	}

	public Bullet(float x, float y, boolean autoAdd, int type) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		this.type = type;
//		setBitmapAndAutoChangeWH(BitmapUtil.bullet);
		
		setBitmapAndFrameWH(BitmapUtil.bullet, BitmapUtil.bullet.getWidth()/3, BitmapUtil.bullet.getHeight());
		addActionFPSFrame(BulletAnimaton.Move.getName(), new int[]{0,1,2}, BulletAnimaton.Move.getFramesTime());
		addActionFPSFrame(BulletAnimaton.PeaNutMove.getName(), new int[]{0,1,2,3,4,5,6,7}, BulletAnimaton.PeaNutMove.getFramesTime());
		
		initMove(type);
	}
	
	void initMove(int type){
		if(type == 0 || type == 4){
			movementAction = new MovementActionSetWithThreadPool();
			movementAction.setMovementActionController(new MovementAtionController());
			String actionName;
			if(type == 0)
				actionName = BulletAnimaton.Move.getName();
			else{
				actionName = BulletAnimaton.PeaNutMove.getName();
				setBitmapAndFrameWH(BitmapUtil.bullet_peanut, BitmapUtil.bullet_peanut.getWidth()/8, BitmapUtil.bullet_peanut.getHeight());
			}
			MovementActionInfo info = new MovementActionInfo(1000, 1, 10, 0, "", null, false, this, actionName);
			MovementAction action = new MovementActionItemBaseReugularFPS(info);
			movementAction.addMovementAction(action);
			
			movementAction.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
				
				@Override
				public void onTick(float dx, float dy) {
					// TODO Auto-generated method stub
					
					move(dx, dy);
					
				}
			});
			
			movementAction.initMovementAction();
			
			movementAction.start();
			
			setMovementAction(movementAction);
		}else if(type == 1){
			movementAction = new MovementActionSetWithThreadPool();
			movementAction.setMovementActionController(new MovementAtionController());
			MovementActionInfo info = new MovementActionInfo(1000, 1, 10, 0, "", new RotationOnceController(30), false, this, BulletAnimaton.Move.getName());
			MovementAction action = new MovementActionItemBaseReugularFPS(info);
			movementAction.addMovementAction(action);
				
				movementAction.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
					
					@Override
					public void onTick(float dx, float dy) {
						// TODO Auto-generated method stub
						
						move(dx, dy);
						
					}
				});
				
				movementAction.initMovementAction();
				
				movementAction.start();
				
				setMovementAction(movementAction);
		}else if(type == 2){
			movementAction = new MovementActionSetWithThreadPool();
			movementAction.setMovementActionController(new MovementAtionController());
			MovementActionInfo info = new MovementActionInfo(1000, 1, 10, 0, "", new RotationOnceController(-30), false, this, BulletAnimaton.Move.getName());
			MovementAction action = new MovementActionItemBaseReugularFPS(info);
			movementAction.addMovementAction(action);
				
				movementAction.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
					
					@Override
					public void onTick(float dx, float dy) {
						// TODO Auto-generated method stub
						
						move(dx, dy);
						
					}
				});
				
				movementAction.initMovementAction();
				
				movementAction.start();
				
				setMovementAction(movementAction);
		}else if(type == 5){
			movementAction = new MovementActionSetWithThreadPool();
			movementAction.setMovementActionController(new MovementAtionController());
			MovementActionInfo info = new MovementActionInfo(1000, 1, 10, 0, "", new RotationOnceController(20), false, this, BulletAnimaton.Move.getName());
			MovementAction action = new MovementActionItemBaseReugularFPS(info);
			movementAction.addMovementAction(action);
				
				movementAction.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
					
					@Override
					public void onTick(float dx, float dy) {
						// TODO Auto-generated method stub
						
						move(dx, dy);
						
					}
				});
				
				movementAction.initMovementAction();
				
				movementAction.start();
				
				setMovementAction(movementAction);
		}else if(type == 6){
			movementAction = new MovementActionSetWithThreadPool();
			movementAction.setMovementActionController(new MovementAtionController());
			MovementActionInfo info = new MovementActionInfo(1000, 1, 10, 0, "", new RotationOnceController(-20), false, this, BulletAnimaton.Move.getName());
			MovementAction action = new MovementActionItemBaseReugularFPS(info);
			movementAction.addMovementAction(action);
				
				movementAction.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
					
					@Override
					public void onTick(float dx, float dy) {
						// TODO Auto-generated method stub
						
						move(dx, dy);
						
					}
				});
				
				movementAction.initMovementAction();
				
				movementAction.start();
				
				setMovementAction(movementAction);
		}
		
	}
	
	private int ballLevel;
	
	public int getBallLevel(){
		return this.ballLevel;
	}
	
	public void setBallLevel(int ballLevel){
		this.ballLevel = ballLevel;
	}
	
	@Override
		public void drawSelf(Canvas canvas, Paint paint) {
			// TODO Auto-generated method stub
	
		if(type == 1){
			Matrix matrix = new Matrix();
//			matrix.mapRect(dst);
//			matrix.postTranslate(getX(), getY());
			matrix.postRotate(-30, getX()+w/2.0f,getY()+h/2.0f);
			spriteMatrix = matrix;
//			canvas.drawBitmap(bitmap, matrix, paint);
		}else if(type == 2){
			Matrix matrix = new Matrix();
//			matrix.mapRect(dst);
//			matrix.postTranslate(getX(), getY());
			matrix.postRotate(30, getX()+w/2.0f,getY()+h/2.0f);
			spriteMatrix = matrix;
//			canvas.drawBitmap(bitmap, matrix, paint);
		}else if(type == 5){
			Matrix matrix = new Matrix();
//			matrix.mapRect(dst);
//			matrix.postTranslate(getX(), getY());
			matrix.postRotate(-20, getX()+w/2.0f,getY()+h/2.0f);
			spriteMatrix = matrix;
//			canvas.drawBitmap(bitmap, matrix, paint);
		}else if(type == 6){
			Matrix matrix = new Matrix();
//			matrix.mapRect(dst);
//			matrix.postTranslate(getX(), getY());
			matrix.postRotate(20, getX()+w/2.0f,getY()+h/2.0f);
			spriteMatrix = matrix;
//			canvas.drawBitmap(bitmap, matrix, paint);
		}

		
			super.drawSelf(canvas, paint);
		}
	
	public void destory(){
		movementAction.controller.cancelAllMove();
	}

	interface BulletsEventListener{
		void willAttack(BattleableSprite battleableSprite);
	}

//	BulletsEventListener bulletsEventListener;
//
//	public void setBulletsEventListener(BulletsEventListener bulletsEventListener){
//		this.bulletsEventListener = bulletsEventListener;
//	}

	@Override
	public void attack(BattleableSprite battleable) {
		// TODO Auto-generated method stub
//		super.attack(battleable);
		
		bulletsEventListener.willAttack(battleable);
		
			IEffect effect = getWeapenEffect();
			if(getWeapenEffect()!=null)
				effect.doEffect(this, battleable);
	}

	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		boolean isInBattleRange = false;
		for(BattleableSprite battleableSprite : battleables){
			isInBattleRange = isInBattleRange(battleableSprite);
			if(isInBattleRange){
				attack(battleableSprite);
				break;
			}	
		}
		
		return isInBattleRange;
	}
}
