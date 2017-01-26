package com.example.try_shoot_deffen.effect;

import java.math.BigDecimal;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.WeapenSprite;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;
import com.example.try_shoot_deffen.utils.IntervalTimer;

public class NormalEffect extends BaseEffect {
	
//	@Override
//	public void doEffect(BattleableSprite battleableSpriteAttacker,
//			BattleableSprite battleableSpriteBeAttacked) {
//		// TODO Auto-generated method stub
//		
//	}

	public NormalEffect(){
		float interval = new BigDecimal(1.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		intervalTimer = new IntervalTimer(interval);
		init();
	}
	
	private void init(){
		battleSpriteInjureType = BattleSpriteInjureType.Normal;
		effectTimes = 1;
		currentEffectTimes = 0;
	}
	
	public void cancelEffect(){
		
	}

	@Override
	public IEffect cloneEffect() {
		// TODO Auto-generated method stub
		final EffectListener effectListener2 = new EffectListener() {
			
			@Override
			public void didEffect(BattleableSprite battleableSpriteByEffecten) {
				// TODO Auto-generated method stub
				effectListener.didEffect(battleableSpriteByEffecten);
			}
		};
		
		NormalEffect normalEffect = new NormalEffect();
		normalEffect.setEffectListener(effectListener2);
		return normalEffect;
	}

	@Override
	protected void addEffectAndDoEffectDetail(WeapenSprite weapenSprite,
			BattleableSprite battleableSpriteBeAttacked) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void removeEffectAndDoEffectDetail(
			BattleableSprite battleableSprite) {
		// TODO Auto-generated method stub
		
	}

}
