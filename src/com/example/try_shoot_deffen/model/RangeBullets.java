package com.example.try_shoot_deffen.model;

import java.util.List;

import android.content.Context;
import android.util.Log;

public class RangeBullets extends Bullets{
	private float AOERange = 100; 
	public RangeBullets(Context context, float x, float y, boolean autoAdd,
			int type_direction) {
		super(context, x, y, autoAdd, type_direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		boolean isInBattleRange = false;
		for(BattleableSprite battleableSprite : battleables){
			isInBattleRange = isInBattleRange(battleableSprite);
			if(isInBattleRange){	
				break;
			}	
		}
		
		checkIfInAreaOfEffectRangeThenAttackByAOE(battleables);
	}
	
	private void checkIfInAreaOfEffectRangeThenAttackByAOE(List<BattleableSprite> battleables){
		for(BattleableSprite battleableSprite : battleables){
			if(isInAreaOfEffectRange(battleableSprite))
				attack(battleableSprite);
		}
	}
	
	private boolean isInAreaOfEffectRange(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		double distance = Math.pow(getCenterX() - battleable.getCenterX(), 2) + Math.pow(getCenterY() - battleable.getCenterY(), 2);
		distance = Math.sqrt(distance);
		if(distance <= AOERange){
			return true;
		}
		return false;
	}
}
