package com.example.try_shoot_deffen.effect;

import java.text.Normalizer.Form;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;
import com.example.try_shoot_deffen.model.WeapenSprite;

public class FrozenEffect implements IEffect{
	private BattleSpriteInjureType battleSpriteInjureType = BattleSpriteInjureType.Frozen;
	
//	@Override
//	public void doEffect(BattleableSprite battleableSpriteAttacker,
//			BattleableSprite battleableSpriteBeAttacked) {
//		// TODO Auto-generated method stub
//		battleableSpriteBeAttacked.setSpeed(battleableSpriteBeAttacked.getSpeed()*1.5f);
//	}

	@Override
	public void doEffect(WeapenSprite weapenSprite,
			BattleableSprite battleableSpriteBeAttacked) {
		// TODO Auto-generated method stub
		if(battleableSpriteBeAttacked.getSpeed() <= 1.0f)
			battleableSpriteBeAttacked.setSpeed(battleableSpriteBeAttacked.getSpeed()*1.5f);
		battleableSpriteBeAttacked.injure(battleSpriteInjureType);
	}
	
}
