package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import com.example.try_gameengine.framework.IActionListener;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;
import com.example.try_shoot_deffen.utils.BitmapUtil;

public class Player extends BattleableSprite{
	Attribute attribute;
	public AttributeHelper attributeHelper;
	long lastShootTime;
	boolean isPrepareToShoot = false;
	boolean isShooting = false;
	boolean isMoveing = false;
	int timeCounter;
	int shootBulletLevel = 0;
	List<Bullet> bullets = new ArrayList<Bullet>();
	boolean isInjure = false;
	int HAMSTER_INJURE_TIME = 40;
	int hamsterInjureCounter;
	boolean isInvincible = false;
	
	enum SheepMove {

		Shoot(
				"Shoot",
				new Bitmap[] {
						
						BitmapUtil.hamster,
						BitmapUtil.hamsterShoot,
						BitmapUtil.hamsterShoot2
				}),
				
		Move(
		"RABIT_MOVE",
		new Bitmap[] {
				
				BitmapUtil.hamster,
				BitmapUtil.hamsterShoot,
				BitmapUtil.hamsterShoot2
		}),
		Injure(
				"Injure",
				new Bitmap[] {
						
						BitmapUtil.hamster_injure
				}),		
		;

		String name;
		Bitmap[] bitmaps;

		private SheepMove(String name, Bitmap[] bitmaps) {
			this.name = name;
			this.bitmaps = bitmaps;
		}

		public String getName() {
			return name;
		}

		public Bitmap[] getBitmaps() {
			return bitmaps;
		}
	}
	
	public Player(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
//		setBitmapAndFrameWH(bitmap, frameWidth, frameHeight);
		
		setBitmapAndFrameWH(BitmapUtil.hamster, BitmapUtil.hamster.getWidth()/7, BitmapUtil.hamster.getHeight()/2);
		
		addActionFPSFrame(SheepMove.Shoot.getName(), new int[]{0,10,0,1}, new int[]{0,5,5,5}, true, new IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
//				if(periousFrameId==1)
//					isShooting = false;
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
				isShooting = false;
			}
		});
		
		setAction(SheepMove.Shoot.getName());
	}
	
	private void initAttribute(){
		attribute = new Attribute();
		float interval = new BigDecimal(1.0f/1.0f)
        .setScale(2, BigDecimal.ROUND_HALF_UP)
        .floatValue();
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}

	private void shoot(){
		long currentTime = System.currentTimeMillis();
		if(!attributeHelper.isCanShoot(lastShootTime, currentTime)){
			return;
		}
		isShooting = true;
		isMoveing = false;
		lastShootTime = currentTime;
		
		if(shootBulletLevel==2){
			if(timeCounter<=0){
				shootBulletLevel = 0;
			}
		}
		
		if(shootBulletLevel==0 || shootBulletLevel==4){
			Bullet bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel);
//			Bullet bullet = new Bullet(this.getX(), this.getY(), false, shootBulletLevel);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
		}else if(shootBulletLevel==1){
			Bullet bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel+4);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
			bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel+5);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
		}else if(shootBulletLevel==2){
			Bullet bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
			bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel-1);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
			bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel-2);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
		}

		setAction(SheepMove.Shoot.getName());
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
//		if(isPrepareToShoot){
//			this.shoot();
//			isPrepareToShoot = false;
//			
//		}
//		
//		if(shootBulletLevel==2){
//			timeCounter--;
//		}
//			
//		injuring();
//		
//		if(!isInjuring()){
//			for(Bullet bullet : bullets){
//				bullet.frameTrig();
//			}	
//		}
	}
	
	private void injuring(){
		if(!isInjure)
			return;
		hamsterInjureCounter++;
		if(HAMSTER_INJURE_TIME <= hamsterInjureCounter){
			isInjure = false;
			isInvincible = true;
			hamsterInjureCounter = 0;
			bitmap = BitmapUtil.hamster;
		}
	}
	
	public boolean isInjuring(){
		return isInjure;
	}
	
	public void setBulletLevel(){
		shootBulletLevel = 0;
	}
	
	public void setBulletLevel2(){
		shootBulletLevel = 1;
	}
	
	public void setBulletLevel3(){
		shootBulletLevel = 2;
		timeCounter = 1000;
	}
	
	public void setBulletLevel5(){
		shootBulletLevel = 4;
	}
}
