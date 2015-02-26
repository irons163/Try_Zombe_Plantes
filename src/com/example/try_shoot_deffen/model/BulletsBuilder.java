package com.example.try_shoot_deffen.model;

import android.content.Context;

import com.example.try_shoot_deffen.effect.FrozenEffect;
import com.example.try_shoot_deffen.utils.CommonUtil;

public class BulletsBuilder {
	
	public static Bullets createFrozenBullets(Context context, float x, float y){
		Bullets bullet = new Bullets(context, x, y, false, 0);
//		bullet.setX(bullet.getX() - bullet.w/2);
		bullet.setPosition(bullet.getX(), bullet.getY());
		bullet.setMoveRage(0, 0, CommonUtil.screenHeight,
				CommonUtil.screenWidth);
		bullet.setType(0);
		bullet.setWeapenEffect(new FrozenEffect());
		return bullet;
	}
	
}
