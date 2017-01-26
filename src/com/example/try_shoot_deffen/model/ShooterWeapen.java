package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.try_shoot_deffen.model.Bullets.BulletsEventListener;
import com.example.try_shoot_deffen.model.BulletsBuilder.BulletEnum;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;
import com.example.try_shoot_deffen.utils.CommonUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ShooterWeapen extends WeapenSprite{
	protected List<BaseBullet> bulletsList = new CopyOnWriteArrayList<BaseBullet>();
	private Context context;
	private BulletEnum bulletEnum;
	
	public ShooterWeapen(Context context, float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		this.context = context;
//		initAttribute();
	}
	
	public ShooterWeapen(Context context, float x, float y, boolean autoAdd, float interval) {
		super(x, y, autoAdd, interval);
		// TODO Auto-generated constructor stub
		this.context = context;
//		initAttribute();
	}
	
	public void setBulletEnum(BulletEnum bulletEnum){
		this.bulletEnum = bulletEnum;
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
		// TODO Auto-generated method stub
//		for(Bullets bullets : bulletsList){
//			bullets.attack(battleable);
//		}
		
		long currentTime = System.currentTimeMillis();
		if (!attributeHelper.isCanShoot(lastShootTime, currentTime)) {
			return;
		}
		
		lastShootTime = currentTime;
		
//		final BaseBullet bullets = BulletsBuilder.createFrozenBullets(context, getX(), getY());
		final BaseBullet bullets = BulletsBuilder.createBulletByBulletEnum(bulletEnum, context, getX(), getY());
		bullets.setBulletsEventListener(new BaseBullet.BulletsEventListener() {
			
			@Override
			public void willAttack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
				shooterEventListener.willAttack(battleableSprite);
				
				bulletsList.remove(bullets);
			}
		});
		bulletsList.add(bullets);
	}
	
	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		boolean isAtLeastOneTargetInBattleRange = false;
		for(BattleableSprite battleableSprite : battleables){
			boolean isInBattleRange = isInBattleRange(battleableSprite);
			if(isInBattleRange){
				attack(battleableSprite);
				isAtLeastOneTargetInBattleRange = true;
			}
		}
		
		for(BaseBullet bullets : bulletsList){
			bullets.checkIfInBattleRangeThenAttack(battleables);
		}
		
		return isAtLeastOneTargetInBattleRange;
	}
	
	protected void initAttribute() {
		attribute = new Attribute();
		
		if(interval<=-1){
			interval = new BigDecimal(3.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
		for(BaseBullet bullets : bulletsList){
			if(bullets.getX() > CommonUtil.screenWidth)
				bulletsList.remove(bullets);
		}
		
		for(BaseBullet bullets : bulletsList){
			bullets.frameTrig();
		}
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
//		super.drawSelf(canvas, paint);
		
		for(BaseBullet bullets : bulletsList){
			bullets.drawSelf(canvas, paint);
		}
	}
}
