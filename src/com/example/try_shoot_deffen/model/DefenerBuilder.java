package com.example.try_shoot_deffen.model;

import com.example.try_shoot_deffen.effect.FireEffect;
import com.example.try_shoot_deffen.effect.FrozenEffect;
import com.example.try_shoot_deffen.utils.CommonUtil;

import android.content.Context;

public class DefenerBuilder {
	public static int select = -1;
	
	public static Defener createBySelect(Context context, float x, float y){
		Defener defener = null;
		switch (select) {
		case 0:
			defener = createHamster1(context, x, y);
			break;
		case 1:
			defener = createHamster2(context, x, y);
			break;
		}
		
		return defener;
	}
	
	public static Defener createHamster1(Context context, float x, float y){
		final Defener defener = new Defener(x, y, false);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
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
		
		final Defener defener = new Defener(x, y, false);
		
		final MeleeWeapon shooterWeapen = new MeleeWeapon(context, x, y, false);
		shooterWeapen.setBattleRange(WeapenSprite.NO_ATK_RANGE);
		shooterWeapen.setWeapenEffect(new FireEffect());
		shooterWeapen.setNoATKRangeListener(new MeleeWeapon.NoATKRangeListener() {

			@Override
			public boolean isInNoATKBattleRange(
					BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
				return defener.getCollisionRectF().intersect(battleableSprite.getCollisionRectF());
			}

			@Override
			public void attack(BattleableSprite battleableSprite) {
				// TODO Auto-generated method stub
				shooterWeapen.attack(battleableSprite);
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
	
}
