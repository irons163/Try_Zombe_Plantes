package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_gameengine.framework.IActionListener;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;
import com.example.try_shoot_deffen.utils.BattleUtil;
import com.example.try_shoot_deffen.utils.BitmapUtil;

public class Defener2 extends BattleableSprite{
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
	
	public Defener2(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
//		setBitmapAndFrameWH(bitmap, frameWidth, frameHeight);
		
		setBitmapAndFrameWH(BitmapUtil.hamster, BitmapUtil.hamster.getWidth()/7, BitmapUtil.hamster.getHeight()/2);
		
		addActionFPSFrame(SheepMove.Shoot.getName(), new int[]{0,10,11,1}, BattleUtil.changeToNew(new int[]{0,5,5,5}, getSpeed()), false, new IActionListener() {
			
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
		
		initCollisiontRectF();
	}
	
	private void initCollisiontRectF(){
		setCollisionRectFEnable(true);
		float collisionWidth = w;
		float collisionHitght = h;
		float collisionOffsetX = w/2-collisionWidth/2;
		float collisionOffsetY = h/2-collisionHitght/2;
		setCollisionOffsetXY(collisionOffsetX, collisionOffsetY);
		setCollisionRectFWH(collisionWidth, collisionHitght);
		setCollisionRectF(getX()+collisionOffsetX, getY()+collisionOffsetY, getX()+collisionOffsetX+collisionWidth, getY()+collisionOffsetY+collisionHitght);
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
		if(weapenSprite!=null)
			weapenSprite.frameTrig();
		
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
		
		if(weapenSprite!=null)
			weapenSprite.drawSelf(canvas, paint);
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
	
//	WeapenSprite weapen;
	
	public void setWeapen(WeapenSprite weapen){
		this.weapenSprite = weapen;
	}
	
	public void setAtkAction(){
		setAction(SheepMove.Shoot.getName());
	}
	
	public void setStopAtkAction(){
		setAction(null);
	}
	
//	@Override
//	public void attack(BattleableSprite battleable) {
//		// TODO Auto-generated method stub
//		shoot();
//		
//		AttributeInfo attributeInfoNew;
//		if(weapenSprite!=null){
//			weapenSprite.attack(battleable);
//			attributeInfoNew = weapenSprite.getNewAttributeInfo(attributeInfo);
//			
//		}
//		AttributeInfo attributeInfoBeAttacked = battleable.getAttributeInfo();
//		
//		
//	}
}
