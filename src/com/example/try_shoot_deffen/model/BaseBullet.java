package com.example.try_shoot_deffen.model;

import java.util.List;

import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.model.Bullet.BulletsEventListener;

public class BaseBullet extends WeapenSprite{

	public BaseBullet(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}

	interface BulletsEventListener{
		void willAttack(BattleableSprite battleableSprite);
	}

	BulletsEventListener bulletsEventListener;

	public void setBulletsEventListener(BulletsEventListener bulletsEventListener){
		this.bulletsEventListener = bulletsEventListener;
	}

	@Override
	public void attack(BattleableSprite battleable) {
		// TODO Auto-generated method stub
//		super.attack(battleable);
		
		bulletsEventListener.willAttack(battleable);
		
			IEffect effect = getWeapenEffect();
			if(getWeapenEffect()!=null)
				effect.doEffect(this, battleable);
	}

	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		boolean isInBattleRange = false;
		for(BattleableSprite battleableSprite : battleables){
			isInBattleRange = isInBattleRange(battleableSprite);
			if(isInBattleRange){
				attack(battleableSprite);
				break;
			}	
		}
		return isInBattleRange;
	}
	
}
