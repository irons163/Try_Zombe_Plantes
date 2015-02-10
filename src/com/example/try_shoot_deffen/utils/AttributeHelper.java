package com.example.try_shoot_deffen.utils;

import android.util.Log;

public class AttributeHelper {
	Attribute attribute;
	
	public AttributeHelper(Attribute attribute){
		this.attribute = attribute;
	}
	
	public boolean isCanShoot(long lastShootTime, long currentTime){
		boolean isCanShoot = false;
		float interval = attribute.getInterval();
		long nextShootTime = lastShootTime + (long) (interval*1000);
		Log.e("interval", interval+"x");
		if(nextShootTime < currentTime ){
			isCanShoot = true;
		}
		
		return isCanShoot;
	}
}
