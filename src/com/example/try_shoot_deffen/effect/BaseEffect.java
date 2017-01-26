package com.example.try_shoot_deffen.effect;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.WeapenSprite;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;
import com.example.try_shoot_deffen.utils.IntervalTimer;

public abstract class BaseEffect implements IEffect{
	protected BattleSpriteInjureType battleSpriteInjureType = BattleSpriteInjureType.Fire;
	protected int effectTimes = 1;
	protected int currentEffectTimes = 0;
	protected int dmg = 0;
	protected IntervalTimer intervalTimer;
	protected EffectListener effectListener;
	
	@Override
	public void doEffect(WeapenSprite weapenSprite,
			BattleableSprite battleableSpriteBeAttacked) {
		// TODO Auto-generated method stub
		if(!battleableSpriteBeAttacked.getAttributeInfo().checkIsAlreadyHasTheSameEffect(this)){
			addEffectAndDoEffectDetail(weapenSprite, battleableSpriteBeAttacked);
			battleableSpriteBeAttacked.getAttributeInfo().addToEffectStatusList(this);
		}
//		weapenSprite.beAttacked(weapenSprite);
		effectListener.didEffect(battleableSpriteBeAttacked);
		battleableSpriteBeAttacked.injure(battleSpriteInjureType);
	}
	
	protected abstract void addEffectAndDoEffectDetail(WeapenSprite weapenSprite,
			BattleableSprite battleableSpriteBeAttacked);
	
	protected abstract void removeEffectAndDoEffectDetail(BattleableSprite battleableSprite);

	@Override
	public void checkEffect(BattleableSprite battleableSprite) {
		// TODO Auto-generated method stub
		if(intervalTimer.isCanShoot() && currentEffectTimes < effectTimes){
//			battleableSprite.injure(battleSpriteInjureType);
			currentEffectTimes++;
		}else if(currentEffectTimes >= effectTimes){
			removeEffectAndDoEffectDetail(battleableSprite);
			battleableSprite.getAttributeInfo().removeFromEffectStatusList(this);
		}
	}

	@Override
	public IEffect cloneEffect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEffectListener(EffectListener effectListener) {
		// TODO Auto-generated method stub
		this.effectListener = effectListener;
	}

}
