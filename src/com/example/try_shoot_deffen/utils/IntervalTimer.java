package com.example.try_shoot_deffen.utils;

import android.util.Log;

public class IntervalTimer {
	private float interval;
	private long lastShootTime;
	private boolean isFirstTimeDelay = false;
	
	public IntervalTimer(float interval){
		this.interval = interval;
	}
	
	public void isFirstTimeDelay(boolean isFirstTimeDelay){
		this.isFirstTimeDelay = isFirstTimeDelay;
	}
	
	public boolean isCanShoot(){
		long currentTime = System.currentTimeMillis();
		boolean isCanShoot = false;
		long nextShootTime;
		
		if(isFirstTimeDelay){
			nextShootTime = currentTime + (long) (interval*1000);
			lastShootTime = currentTime;
			isFirstTimeDelay = false;
		}else
			nextShootTime = lastShootTime + (long) (interval*1000);
		
		Log.e("interval", interval+"x");
		if(nextShootTime < currentTime ){
			isCanShoot = true;
			lastShootTime = currentTime;
		}
		
		return isCanShoot;
	}
	
}
