package com.example.try_shoot_deffen.effect;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.WeapenSprite;

public interface IEffect {
	
	interface EffectListener{
		void didEffect(BattleableSprite battleableSpriteByEffecten);
	}
	
	public void doEffect(WeapenSprite weapenSprite, BattleableSprite battleableSpriteBeAttacked);
	public void checkEffect(BattleableSprite battleableSprite);
	
	public IEffect cloneEffect();
	
	public void setEffectListener(EffectListener effectListener);
}
