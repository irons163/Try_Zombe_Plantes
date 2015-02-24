package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_deffen.model.Bullets.BulletsEventListener;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ShooterWeapen extends WeapenSprite{
	protected List<Bullets> bulletsList = new ArrayList<Bullets>();
	private Context context;
	
	public ShooterWeapen(Context context, float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		this.context = context;
		initAttribute();
	}
	
	@Override
	public boolean isInBattleRange(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		
		for(Bullets bullets : bulletsList){
			Boolean isInBattleRange = bullets.isInBattleRange(battleable);
			if(isInBattleRange){
				bullets.attack(battleable);
			}
		}
		
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
		
		final Bullets bullets = BulletsBuilder.createFrozenBullets(context, getX(), getY());
		bullets.setBulletsEventListener(new Bullets.BulletsEventListener() {
			
			@Override
			public void willAttack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
				shooterEventListener.willAttack(battleableSprite);
				
				bulletsList.remove(bullets);
			}
		});
		bulletsList.add(bullets);
	}
	
	
	Attribute attribute;
	AttributeHelper attributeHelper;
	long lastShootTime;
	
	private void initAttribute() {
		attribute = new Attribute();
		float interval = new BigDecimal(2.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
		for(Bullets bullets : bulletsList){
			bullets.frameTrig();
		}
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
//		super.drawSelf(canvas, paint);
		
		for(Bullets bullets : bulletsList){
			bullets.drawSelf(canvas, paint);
		}
	}
}
