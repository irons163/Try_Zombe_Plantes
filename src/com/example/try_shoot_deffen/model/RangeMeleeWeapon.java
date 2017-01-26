package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.model.ShooterWeapen.ShooterEventListener;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;

public class RangeMeleeWeapon extends MeleeWeapon{

	private Context context;
	
	public RangeMeleeWeapon(Context context, float x, float y, boolean autoAdd) {
		super(context, x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}
	
	public RangeMeleeWeapon(Context context, float x, float y, boolean autoAdd, float interval) {
		super(context, x, y, autoAdd, interval);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isInBattleRange(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		
//		for(Bullets bullets : bulletsList){
//			Boolean isInBattleRange = bullets.isInBattleRange(battleable);
//			if(isInBattleRange){
//				bullets.attack(battleable);
//			}
//		}
		
		return super.isInBattleRange(battleable);
	}
	
	interface ShooterEventListener{
		void willAttack(BattleableSprite battleableSprite);
	}

	ShooterEventListener shooterEventListener;

	public void setShooterEventListener(ShooterEventListener shooterEventListener){
		this.shooterEventListener = shooterEventListener;
	}

	@Override
	public void attack(BattleableSprite battleable) {
		
		IEffect effect = getWeapenEffect();
		if(getWeapenEffect()!=null)
			effect.doEffect(this, battleable);
		
//		final Bullets bullets = BulletsBuilder.createFrozenBullets(context, getX(), getY());
//		bullets.setBulletsEventListener(new Bullets.BulletsEventListener() {
//			
//			@Override
//			public void willAttack(BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
//				shooterEventListener.willAttack(battleableSprite);
//				
//				bulletsList.remove(bullets);
//			}
//		});
//		bulletsList.add(bullets);
	}
	
	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		
		long currentTime = System.currentTimeMillis();
		if (!attributeHelper.isCanShoot(lastShootTime, currentTime)) {
			return false;
		}
		
		lastShootTime = currentTime;
		
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
			if(isInBattleRange(battleableSprite))
				attack(battleableSprite);
		}
	}
	
	@Override
	protected void initAttribute() {
		attribute = new Attribute();
		
		if(interval<=-1){
			interval = new BigDecimal(2.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}

}
