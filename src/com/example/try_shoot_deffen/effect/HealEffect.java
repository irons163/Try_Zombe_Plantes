package com.example.try_shoot_deffen.effect;

import java.math.BigDecimal;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.WeapenSprite;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;
import com.example.try_shoot_deffen.utils.IntervalTimer;

public class HealEffect extends BaseEffect  {
	
//	@Override
//	public void doEffect(BattleableSprite battleableSpriteAttacker,
//			BattleableSprite battleableSpriteBeAttacked) {
//		// TODO Auto-generated method stub
//		
//	}

	public HealEffect(){
		float interval = new BigDecimal(1.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		intervalTimer = new IntervalTimer(interval);
		init();
	}
	
	private void init(){
		battleSpriteInjureType = BattleSpriteInjureType.Heal;
		effectTimes = 1;
		currentEffectTimes = 0;
		dmg = 5;
	}
	
	@Override
	public void doEffect(WeapenSprite weapenSprite,
			BattleableSprite battleableSpriteBeAttacked) {
		// TODO Auto-generated method stub
		if(!battleableSpriteBeAttacked.getAttributeInfo().checkIsAlreadyHasTheSameEffect(this)){
			battleableSpriteBeAttacked.getAttributeInfo().addToEffectStatusList(this);
		}
//		battleableSpriteBeAttacked.injure(battleSpriteInjureType);
	}
	
	public void checkEffect(BattleableSprite battleableSprite){
		
		if(intervalTimer.isCanShoot() && currentEffectTimes < effectTimes){
//			battleableSprite.injure(battleSpriteInjureType);
			battleableSprite.getAttributeInfo().setHp(battleableSprite.getAttributeInfo().getHp() + dmg);
			currentEffectTimes++;
		}else if (currentEffectTimes >= effectTimes){
			battleableSprite.getAttributeInfo().removeFromEffectStatusList(this);
		}
	}
	
	public void cancelEffect(){
		
	}

	@Override
	public IEffect cloneEffect() {
		// TODO Auto-generated method stub
		return new HealEffect();
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
