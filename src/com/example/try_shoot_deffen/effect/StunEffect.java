package com.example.try_shoot_deffen.effect;

import java.math.BigDecimal;
import java.text.Normalizer.Form;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;
import com.example.try_shoot_deffen.model.WeapenSprite;
import com.example.try_shoot_deffen.utils.IntervalTimer;

public class StunEffect extends BaseEffect{
	
//	@Override
//	public void doEffect(BattleableSprite battleableSpriteAttacker,
//			BattleableSprite battleableSpriteBeAttacked) {
//		// TODO Auto-generated method stub
//		battleableSpriteBeAttacked.setSpeed(battleableSpriteBeAttacked.getSpeed()*1.5f);
//	}
	
	public StunEffect(){
		float interval = new BigDecimal(2.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		intervalTimer = new IntervalTimer(interval);
		intervalTimer.isFirstTimeDelay(true);
		init();
	}
	
	private void init(){
		battleSpriteInjureType = BattleSpriteInjureType.Frozen;
		effectTimes = 1;
		currentEffectTimes = 0;
	}
	
	private float battleableSpriteBeAttackedSpeedBeforeDoEffect;
	
	@Override
	protected void addEffectAndDoEffectDetail(WeapenSprite weapenSprite,
			BattleableSprite battleableSpriteBeAttacked){
		battleableSpriteBeAttackedSpeedBeforeDoEffect = battleableSpriteBeAttacked.getSpeed();
		battleableSpriteBeAttacked.setSpeed(10000);
	}
	
	@Override
	protected void removeEffectAndDoEffectDetail(BattleableSprite battleableSprite){
		battleableSprite.setSpeed(1/10000.0f);
	}

	@Override
	public IEffect cloneEffect() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
