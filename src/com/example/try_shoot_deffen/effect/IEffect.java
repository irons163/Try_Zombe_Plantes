package com.example.try_shoot_deffen.effect;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.WeapenSprite;

public interface IEffect {
	
	public void doEffect(WeapenSprite weapenSprite, BattleableSprite battleableSpriteBeAttacked);
	
}
