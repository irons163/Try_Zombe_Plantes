package com.example.try_shoot_deffen.model;

import com.example.try_gameengine.framework.Sprite;

public class BattleableSprite extends Sprite implements Battleable{
	protected float battleRange = 50.0f;
	protected float battleInviable = 0.9f;
	
	public BattleableSprite(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isInBattleRange(Battleable battleable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void attack(Battleable battleable) {
		// TODO Auto-generated method stub
		
	}

}
