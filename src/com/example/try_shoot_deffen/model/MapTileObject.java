package com.example.try_shoot_deffen.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_gameengine.framework.Sprite;

public class MapTileObject extends Sprite{
	private BattleableSprite battleableSprite;
	
	public MapTileObject(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}

	public void setSprite(BattleableSprite battleableSprite){
		this.battleableSprite = battleableSprite;
	}
	
	public BattleableSprite getSprite(){
		return battleableSprite;
	}
	
	public boolean isTouchXY(float x, float y){
		if(getX() < x && getX() + w > x && getY() < y && getY() + h > y){
			return true;
		}
		return false;
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
		if(battleableSprite!=null)
		battleableSprite.drawSelf(canvas, paint);
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		if(battleableSprite!=null)
		battleableSprite.frameTrig();
	}
}
