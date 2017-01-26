package com.example.try_shoot_deffen.model;

import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;
import com.example.try_shoot_deffen.model.Zombe.SheepMove;
import com.example.try_shoot_deffen.utils.BitmapUtil;
import com.example.try_shoot_deffen.utils.ColorFilterBuilder;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class DefenerMoveable extends BattleableSprite{

	public DefenerMoveable(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move(float dx, float dy) {
		// TODO Auto-generated method stub
		super.move(dx, dy);
		
		if(weapenSprite!=null)
			weapenSprite.move(dx, dy);
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		
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
		
		if(getAttributeInfo().checkHasEffectOrNotByEffectType(BattleSpriteInjureType.Heal)){
			if(paint==null)
				paint = new Paint();
			
			paint.setColorFilter(ColorFilterBuilder.getEffectColor(BattleSpriteInjureType.Heal));
		}
		
		super.drawSelf(canvas, paint);
		
		if(weapenSprite!=null)
			weapenSprite.drawSelf(canvas, paint);
	}
	
	int HAMSTER_INVINCIBLE_TIME = 200;
	int hamsterInvincibleCounter;
	int alpha = 255;
	int newcurrentFrame;
	boolean isInvincible = false;
	
	private void invincibling(){
		hamsterInvincibleCounter++;
		if(HAMSTER_INVINCIBLE_TIME <= hamsterInvincibleCounter){
			isInvincible = false;
			hamsterInvincibleCounter = 0;
			alpha = 255;
		}
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
		if(weapenSprite!=null)
			weapenSprite.frameTrig();
		
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
	
	boolean isInjure = false;
	int HAMSTER_INJURE_TIME = 40;
	int hamsterInjureCounter;

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
	
	public void beHit(){
		synchronized (this) {
			setAction(SheepMove.Injure.getName());
//			bitmap = BitmapUtil.hp;
			isInjure = true;
		}
	}
}
