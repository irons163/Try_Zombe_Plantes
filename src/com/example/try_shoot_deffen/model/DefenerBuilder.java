package com.example.try_shoot_deffen.model;

import com.example.try_shoot_deffen.effect.FireEffect;
import com.example.try_shoot_deffen.effect.FrozenEffect;
import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.effect.NormalEffect;
import com.example.try_shoot_deffen.model.BulletsBuilder.BulletEnum;
import com.example.try_shoot_deffen.utils.CommonUtil;

import android.content.Context;
import android.graphics.RectF;
import android.util.Log;

public class DefenerBuilder {
	public static int select = -1;
	
	public static BattleableSprite createBySelect(Context context, float x, float y){
		BattleableSprite defener = null;
		switch (select) {
		case 0:
			defener = createHamster1(context, x, y);
			break;
		case 1:
			defener = createHamster2(context, x, y);
			break;
		case 2:
			defener = createHamster3(context, x, y);
			break;
		case 3:
			defener = createHamster4(context, x, y);
			break;
		case 4:
			defener = createHamster5(context, x, y);
			break;
		case 5:
			defener = createHamster6(context, x, y);
			break;
		}
		
		return defener;
	}
	
	public static Defener createHamster1(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.FrozenBullets);
		shooterWeapen.setBattleRange(500);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster2(Context context, float x, float y){
//		Defener defener = new Defener(x, y, false);
//		Bullets bullet = new Bullets(context, x, y, false, 0);
//		bullet.setMoveRage(0, 0, CommonUtil.screenHeight,
//				CommonUtil.screenWidth);
//		bullet.setType(1);
//		defener.setWeapen(bullet);
//		return defener;
		
		final Defener defener = new Defener(x, y, true);
		
		final RangeMeleeWeapon shooterWeapen = new RangeMeleeWeapon(context, x, y, false, 0);
		shooterWeapen.setBattleRange(WeapenSprite.NO_ATK_RANGE);
		shooterWeapen.setWeapenEffect(new FireEffect());
		shooterWeapen.setNoATKRangeListener(new MeleeWeapon.NoATKRangeListener() {

			@Override
			public boolean isInNoATKBattleRange(
					BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				boolean isCollision = defener.getCollisionRectF().intersect(battleableSprite.getCollisionRectF());
				
				boolean isCollision = RectF.intersects(defener.getCollisionRectF(), battleableSprite.getCollisionRectF());
				
//				if(isCollision)
					Log.e("isCollision", "isCollision:"+isCollision);
				return isCollision;
			}

			@Override
			public void attack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				shooterWeapen.attack(battleableSprite);
			}

			@Override
			public void willDoEffect(IEffect effect,
					BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
				
			}
		});
		
//		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
//			
//			@Override
//			public void willAttack(BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
////				defener.attack(battleableSprite);
//			}
//		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster3(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.NormalBullets);
		shooterWeapen.setBattleRange(CommonUtil.screenWidth);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster4(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.StunBullets);
		shooterWeapen.setBattleRange(CommonUtil.screenWidth);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster5(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.RangeNormalBullets);
		shooterWeapen.setBattleRange(CommonUtil.screenWidth);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener2 createHamster6(Context context, float x, float y){
		final Defener2 defener = new Defener2(x, y, true);
		
		final MeleeWeapon shooterWeapen = new MeleeWeapon(context, x, y, false);
//		shooterWeapen.setBulletEnum(BulletEnum.RangeNormalBullets);
		shooterWeapen.setBattleRange(WeapenSprite.NO_ATK_RANGE);
		shooterWeapen.setWeapenEffect(new NormalEffect());
		shooterWeapen.setNoATKRangeListener(new MeleeWeapon.NoATKRangeListener() {

			@Override
			public boolean isInNoATKBattleRange(
					BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				boolean isCollision = defener.getCollisionRectF().intersect(battleableSprite.getCollisionRectF());
				
				boolean isCollision = RectF.intersects(defener.getCollisionRectF(), battleableSprite.getCollisionRectF());
				
//				if(isCollision)
					Log.e("isCollision", "isCollision:"+isCollision);
				return isCollision;
			}

			@Override
			public void attack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
//				shooterWeapen.attack(battleableSprite);
				defener.setAtkAction();
			}

			@Override
			public void willDoEffect(IEffect effect,
					BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
				
			}
		});
		
		defener.setWeapen(shooterWeapen);
		return defener;
	}
}
