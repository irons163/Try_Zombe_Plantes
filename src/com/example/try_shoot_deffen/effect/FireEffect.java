package com.example.try_shoot_deffen.effect;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.WeapenSprite;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;

public class FireEffect implements IEffect  {
	private BattleSpriteInjureType battleSpriteInjureType = BattleSpriteInjureType.Fire;
	
//	@Override
//	public void doEffect(BattleableSprite battleableSpriteAttacker,
//			BattleableSprite battleableSpriteBeAttacked) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void doEffect(WeapenSprite weapenSprite,
			BattleableSprite battleableSpriteBeAttacked) {
		// TODO Auto-generated method stub
		battleableSpriteBeAttacked.injure(battleSpriteInjureType);
	}

}
