package com.example.try_shoot_deffen.model;

import android.content.Context;

import com.example.try_shoot_deffen.effect.FrozenEffect;
import com.example.try_shoot_deffen.effect.NormalEffect;
import com.example.try_shoot_deffen.effect.StunEffect;
import com.example.try_shoot_deffen.utils.CommonUtil;

public class BulletsBuilder {
	
	public enum BulletEnum{
		FrozenBullets, NormalBullets, StunBullets, RangeNormalBullets
	}
	
	public static BaseBullet createBulletByBulletEnum(BulletEnum bulletEnum, Context context, float x, float y){
		BaseBullet baseBullet = null;
		
		switch (bulletEnum) {
		case FrozenBullets:
			baseBullet = createFrozenBullets(context, x, y);
			break;

		case NormalBullets:
			baseBullet = createNormalBullets(context, x, y);
			break;
			
		case StunBullets:
			baseBullet = createStunBullets(context, x, y);
			break;
			
		case RangeNormalBullets:
			baseBullet = createRangeNormalBullets(context, x, y);
			break;
			
		default:
			break;
		}
		
		return baseBullet;
	}
	
	public static Bullets createFrozenBullets(Context context, float x, float y){
		Bullets bullet = new Bullets(context, x, y, false, 0);
//		bullet.setX(bullet.getX() - bullet.w/2);
		bullet.setPosition(bullet.getX(), bullet.getY());
//		bullet.setMoveRage(0, 0, CommonUtil.screenHeight,
//				CommonUtil.screenWidth);
		bullet.setType(0);
		bullet.setWeapenEffect(new FrozenEffect());
		bullet.setBattleRange(100);
		return bullet;
	}
	
	public static Bullet createNormalBullets(Context context, float x, float y){
		Bullet bullet = new Bullet(x, y, false, 4);
//		bullet.setX(bullet.getX() - bullet.w/2);
		bullet.setPosition(bullet.getX(), bullet.getY());
//		bullet.setMoveRage(0, 0, CommonUtil.screenHeight,
//				CommonUtil.screenWidth);
//		bullet.setType(0);
		bullet.setWeapenEffect(new FrozenEffect());
		bullet.setBattleRange(100);
		return bullet;
	}
	
	public static Bullet createStunBullets(Context context, float x, float y){
		Bullet bullet = new Bullet(x, y, false, 0);
//		bullet.setX(bullet.getX() - bullet.w/2);
		bullet.setPosition(bullet.getX(), bullet.getY());
//		bullet.setMoveRage(0, 0, CommonUtil.screenHeight,
//				CommonUtil.screenWidth);
//		bullet.setType(0);
		bullet.setWeapenEffect(new StunEffect());
		bullet.setBattleRange(100);
		return bullet;
	}
	
	public static RangeBullets createRangeNormalBullets(Context context, float x, float y){
		RangeBullets bullet = new RangeBullets(context, x, y, false, 0);
		bullet.setPosition(bullet.getX(), bullet.getY());
		bullet.setType(0);
		bullet.setWeapenEffect(new NormalEffect());
		bullet.setBattleRange(100);
		return bullet;
	}
}
