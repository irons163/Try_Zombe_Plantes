package com.example.try_shoot_deffen.model;

import java.util.List;

import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.script.ScriptPaser;
import com.example.try_shoot_deffen.model.Bullets.HandAnimaton;
import com.example.try_shoot_deffen.utils.BitmapUtil;
import com.example.try_shoot_deffen.utils.CommonUtil;

import android.content.Context;
import android.util.Log;

public class RangeBullets extends Bullets{
	private float AOERange = 200; 
	public RangeBullets(Context context, float x, float y, boolean autoAdd,
			int type_direction) {
		super(context, x, y, autoAdd, type_direction);
		// TODO Auto-generated constructor stub
		setBitmapAndFrameWH(BitmapUtil.hand, BitmapUtil.hand.getWidth()/7, BitmapUtil.hand.getHeight()/2);
		addActionFPSFrame(HandAnimaton.Move.getName(), new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13}, HandAnimaton.Move.getFramesTime());
		
//		setX(x - w/2);
		this.type_direction = type_direction;
		
//		if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
//			reflectCounter++;
//		}
		if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
			if(type==Top){
				type = Down;
			}else{
				type = Top;
			}
			limit = CommonUtil.cat_bg_height;
		}
		this.context = context;
		initCollisiontRectF();
		initMove();
	}
	
	private void initCollisiontRectF(){
		setCollisionRectFEnable(true);
		float collisionWidth = w/3.0f;
		float collisionHitght = h/3.0f;
		float collisionOffsetX = w/2-collisionWidth/2;
		float collisionOffsetY = h/2-collisionHitght/2;
		setCollisionOffsetXY(collisionOffsetX, collisionOffsetY);
		setCollisionRectFWH(collisionWidth, collisionHitght);
		setCollisionRectF(getX()+collisionOffsetX, getY()+collisionOffsetY, getX()+collisionOffsetX+collisionWidth, getY()+collisionOffsetY+collisionHitght);
	}

	private void initMove() {
		final ScriptPaser scriptPaser = new ScriptPaser();
		scriptPaser.paser(context, this, handScriteNames[type_direction]);
		
		scriptPaser.setScriptTriggerDoCommandLisener(new ScriptPaser.ScriptTriggerDoCommandLisener() {
			
			@Override
			public void onCommandPause() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCommandMove(float dx, float dy) {
				// TODO Auto-generated method stub
				if(!isMoveable)
					return;
				if(scriptPaser.isMove()){
					dx = scriptPaser.getDx();
					Log.e("script dx", dx+"");
					if(scriptPaser.getDx()>0){
						if(getX()+w >= moveRage.right){
							scriptPaser.setDx(-scriptPaser.getDx());
						}
					}else{
						if(getX() <= moveRage.left){
							scriptPaser.setDx(-scriptPaser.getDx());
						}
					}
				}
				
				dx = scriptPaser.getDx();
				
				
				RangeBullets.this.move(dx, dy);
			}
		});
		

		
		movementAction = new MovementActionSetWithThreadPool();
		movementAction.setMovementActionController(new MovementAtionController());
		MovementActionInfo info = new MovementActionInfo(2000, 1, 0, 4.0f, "", null, false, this, HandAnimaton.Move.getName());
		MovementActionItemBaseReugularFPS action = new MovementActionItemBaseReugularFPS(info);
		movementAction.addMovementAction(action);
		
		movementAction.isLoop = true;
		
		movementAction.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				if(!isMoveable)
					return;
				float bottom = RangeBullets.this.getY()+h;
				float top = RangeBullets.this.getY();
				Log.e("hand bottom", bottom + "");
				
//				if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP)
//					dy *= -1;
				
				
				
				if(type==Top){
					dy = -dy;
				}
				
				if( (bottom >= limit && type==Down) || (top <= limit && type==Top)){
//					dy = -dy;
//					if(type==Down || (type==Top &&( type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP))){
					if(type==Down){
							type=Top;
						if(reflectCounter==stopRlectCount){
							limit = moveRage.height() - moveRage.height()*Math.pow(0.66d, reflectCounter);
						}else{
							reflectCounter++;
							limit = moveRage.height() - moveRage.height()*Math.pow(0.66d, reflectCounter);
						}
							
						drawWithoutClip = true;
						isMoveable = false;
						
					}else{
						type=Down;
						limit = moveRage.bottom;
					}
					
				}
				
				
				move(dx, dy);
				
			}
		});
		
		movementAction.initMovementAction();
		
		movementAction.start();
		
//		setMovementAction(movementAction);
		
		
		
		movementActionShoot = new MovementActionSetWithThreadPool();
		info = new MovementActionInfo(2000, 1, 2, 0, "", null, false);
		MovementActionItemBaseReugularFPS reFlectaction = new MovementActionItemBaseReugularFPS(info);
		movementActionShoot.addMovementAction(reFlectaction);
		
		movementActionShoot.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				

				
				move(dx, dy);
				
//				scriptPaser.nextScriptLine();

				
//				scriptPaser.triggerAndDoCommandInSprite();
				
			}
		});
		
		movementActionShoot.initMovementAction();
		
		movementActionShoot.start();
		
		setMovementAction(movementActionShoot);

	}

	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		boolean isInBattleRange = false;
		for(BattleableSprite battleableSprite : battleables){
			isInBattleRange = isInBattleRange(battleableSprite);
			if(isInBattleRange){	
				break;
			}	
		}
		
		if(isInBattleRange)
			checkIfInAreaOfEffectRangeThenAttackByAOE(battleables);
		
		return isInBattleRange;
	}
	
	private void checkIfInAreaOfEffectRangeThenAttackByAOE(List<BattleableSprite> battleables){
		for(BattleableSprite battleableSprite : battleables){
			if(isInAreaOfEffectRange(battleableSprite))
				attack(battleableSprite);
		}
	}
	
	private boolean isInAreaOfEffectRange(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		double distance = Math.pow(getCenterX() - battleable.getCenterX(), 2) + Math.pow(getCenterY() - battleable.getCenterY(), 2);
		distance = Math.sqrt(distance);
		if(distance <= AOERange){
			return true;
		}
		return false;
	}
}
