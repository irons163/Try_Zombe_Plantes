package com.example.try_shoot_deffen.model;

import java.util.List;

public interface Battleable {
//	public boolean isInBattleRange(BattleableSprite battleable);
//	public void attack(BattleableSprite battleable);
	
	public boolean checkIfInBattleRangeThenAttack(List<BattleableSprite> battleables);
	
	public void beAttacked(WeapenSprite weapenSprite);
	
	public void setBattleRange(float atkR);
	
	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo);
}
