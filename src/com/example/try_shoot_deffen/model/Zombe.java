package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.framework.IActionListener;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;
import com.example.try_shoot_deffen.utils.BattleUtil;
import com.example.try_shoot_deffen.utils.BitmapUtil;
import com.example.try_shoot_deffen.utils.ColorFilterBuilder;

public class Zombe extends BattleableSprite{
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
	
	public Zombe(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
//		setBitmapAndFrameWH(bitmap, frameWidth, frameHeight);
		
		battleableSpriteType = BattleableSpriteType.Enemy;
		
		setBitmapAndFrameWH(BitmapUtil.hamster, BitmapUtil.hamster.getWidth()/7, BitmapUtil.hamster.getHeight()/2);
		
//		addActionFPSFrame(SheepMove.Shoot.getName(), new int[]{0,10,0,1}, BattleUtil.changeToNew(new int[]{0,5,5,5}, getSpeed()), true, new IActionListener() {
		addActionFPSFrame(SheepMove.Shoot.getName(), new int[]{11,12,13,11}, new int[]{4,4,4,4}, true, new IActionListener() {
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
		
		addActionFPS(SheepMove.Injure.getName(), SheepMove.Injure.getBitmaps(), new int[]{0}, false);
		
		setAction(SheepMove.Shoot.getName());
		
		MovementAction movementActionShoot;
		movementActionShoot = new MovementActionSetWithThreadPool();
		movementActionShoot.setMovementActionController(new MovementAtionController());
		MovementActionInfo info = new MovementActionInfo(3000, BattleUtil.changeToNew(1, getSpeed()), -2, 0, "", null, false);
		MovementActionItemBaseReugularFPS reFlectaction = new MovementActionItemBaseReugularFPS(info);
		movementActionShoot.addMovementAction(reFlectaction);
		
		movementActionShoot.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				

				
				move(dx, dy);
				
//				scriptPaser.nextScriptLine();

				
//				scriptPaser.triggerAndDoCommandInSprite();
				
			}
		});
		
		movementActionShoot.initMovementAction();
		
		movementActionShoot.start();
		
		setMovementAction(movementActionShoot);
		
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
		
		if(weapen!=null)
			weapen.frameTrig();
		
		if(getAttributeInfo().getHp() <= 0 && !isInjuring()){
//			isInjure = true;
			beHit();
		}
		
		if(isInjuring()){
			getAttributeInfo().removeAllFromEffectStatusList();
			action.controller.cancelAllMove();
			Log.e("zoobe", "die");
		}else {
			getAttributeInfo().checkAllEffect(this);
		}
		
		injuring();
		
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
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
//		if(battleSpriteInjureType == BattleSpriteInjureType.Frozen){
		
		if(getAttributeInfo().checkHasEffectOrNotByEffectType(BattleSpriteInjureType.Fire)){
			
			float frameWidth = BitmapUtil.invincibel.getWidth()/4.0f;
			float frameHeight = BitmapUtil.invincibel.getHeight();
			canvas.save();
			float newX = getX() - (BitmapUtil.invincibel.getWidth()/4.0f - w)/2.0f;
			float newY = getY() + h - BitmapUtil.invincibel.getHeight();
			canvas.clipRect(getX() - (BitmapUtil.invincibel.getWidth()/4.0f - w)/2.0f, getY() + h - BitmapUtil.invincibel.getHeight(), getX() + w + (BitmapUtil.invincibel.getWidth()/4.0f - w)/2.0f, getY() + h);
			
			canvas.drawBitmap(BitmapUtil.invincibel, newX-(newcurrentFrame%(BitmapUtil.invincibel.getWidth()/(int)frameWidth))*frameWidth, 
					newY - (newcurrentFrame/(BitmapUtil.invincibel.getWidth()/(int)frameWidth))*frameHeight, paint);
			canvas.restore();
			
			if (alpha > 0) {
				alpha -= 50;
				if (alpha <= 0){
					alpha = 0;	
					newcurrentFrame++;
					if(newcurrentFrame >= 4)
						newcurrentFrame=0;
				}
			}else{
				alpha = 255;
//				paint.setAlpha(alpha);
			}
					
			invincibling();
			
			if(paint==null)
				paint = new Paint();
			paint.setColorFilter(ColorFilterBuilder.getEffectColor(battleSpriteInjureType));
			
			Log.e("zoobe", "draw fire");
		}
		
		if(getAttributeInfo().checkHasEffectOrNotByEffectType(BattleSpriteInjureType.Frozen)){
			if(paint==null)
				paint = new Paint();
//			paint.setColor(Color.RED);
//			paint.setAlpha(150);
			
//			ImageView Sun = (ImageView)findViewById(R.id.sun);
//			Sun.setColorFilter();
			
			float[] matrix = { 
			        1, 1, 1, 1, 1, //red
			        0, 0, 0, 0, 0, //green
			        0, 0, 0, 0, 0, //blue
			        1, 1, 1, 1, 1 //alpha
			    };
			
//			ColorMatrix colorMatrix = new ColorMatrix(matrix);
//			
//			ColorFilterGenerator.adjustHue(colorMatrix, 162);
//			
//			ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
//			
//			paint.setColorFilter(colorMatrixColorFilter);
			
//			paint.setColorFilter(ColorFilterGenerator.adjustHue(162));
			
//			ColorMatrix cm = new ColorMatrix();
//			
//			ColorFilterGenerator.adjustHue(cm, -50);
//			
//			ColorFilterGenerator.adjustBrightness(cm, -30);
//			
//			ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(cm);
//			
//			paint.setColorFilter(colorMatrixColorFilter);
			
			paint.setColorFilter(ColorFilterBuilder.getEffectColor(battleSpriteInjureType));
			
			// all pix change to red
//			ColorMatrixColorFilter c = new ColorMatrixColorFilter(matrix);
//			paint.setColorFilter(c);
			
		}	
		
		if(getAttributeInfo().checkHasEffectOrNotByEffectType(BattleSpriteInjureType.Normal)){
			if(paint==null)
				paint = new Paint();
			
			paint.setColorFilter(ColorFilterBuilder.getEffectColor(BattleSpriteInjureType.Normal));
		}
			
		super.drawSelf(canvas, paint);
		
		if(weapen!=null)
			weapen.drawSelf(canvas, paint);
	}
	
	int HAMSTER_INVINCIBLE_TIME = 200;
	int hamsterInvincibleCounter;
	int alpha = 255;
	int newcurrentFrame;
	
	private void invincibling(){
		hamsterInvincibleCounter++;
		if(HAMSTER_INVINCIBLE_TIME <= hamsterInvincibleCounter){
			isInvincible = false;
			hamsterInvincibleCounter = 0;
			alpha = 255;
		}
	}
	
	private void injuring(){
		if(!isInjure)
			return;
		hamsterInjureCounter++;
		if(HAMSTER_INJURE_TIME <= hamsterInjureCounter){
//			isInjure = false;
			isInvincible = true;
			hamsterInjureCounter = 0;
			bitmap = BitmapUtil.hamster;
			isNeedRemove = true;
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
	
	WeapenSprite weapen;
	
	public void setWeapen(WeapenSprite weapen){
		this.weapen = weapen;
	}
	
	@Override
	public void setSpeed(float speeed){
		battleInviable = speeed;
		setNew();
	}
	
	public void setNew(){
		actions.get(SheepMove.Shoot.getName()).frameTime = BattleUtil.changeToNew(actions.get(SheepMove.Shoot.getName()).frameTime, getSpeed());
		actions.get(SheepMove.Shoot.getName()).initUpdateTime();
		for(MovementActionInfo info : getMovementAction().getMovementInfoList()){
			int newDelay = BattleUtil.changeToNew((int)info.getDelay(), getSpeed());
			Log.e("newDelay", "newDelay:"+newDelay);
			info.setDelay(newDelay);
		}
		battleInviable = 1.0f;
	}
	
	public void beHit(){
		synchronized (this) {
			setAction(SheepMove.Injure.getName());
//			bitmap = BitmapUtil.hp;
			isInjure = true;
		}
	}
	
	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub

		if(isInjuring()){
			Log.e("zombe", "no battle");
			return false;
		}
		
		if(weapenSprite!=null)
			return weapenSprite.checkIfInBattleRangeThenAttack(battleables);
		else
		for(BattleableSprite battleableSprite : battleables){
//			boolean isInBattleRange = isInBattleRange(battleableSprite);
//			if(isInBattleRange){
//				attack(battleableSprite);
//			}
			
			
				collisionRectF.contains(battleableSprite.getCollisionRectF());
		}
		return false;
	}
	
	@Override
	public boolean isBattleable() {
		// TODO Auto-generated method stub
		return !isInjuring() && isBattleable;
	}
	
	public void destory(){
//		movementAction.controller.cancelAllMove();
	}
}
