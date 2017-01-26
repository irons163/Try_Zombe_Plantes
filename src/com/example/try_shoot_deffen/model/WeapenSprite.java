package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;

public class WeapenSprite extends Sprite implements IWeapen, Battleable{
	protected IEffect effect;
	protected float atkR = 50;
	protected AttributeInfo attributeInfo;
	public static final float NO_ATK_RANGE = 0;
	
	protected Attribute attribute;
	protected AttributeHelper attributeHelper;
	protected long lastShootTime;
	protected float interval = -1;
	
	public WeapenSprite(float x, float y, boolean autoAdd) {
//		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		this(x, y, autoAdd, -1);
	}
	
	public WeapenSprite(float x, float y, boolean autoAdd, float interval) {
		super(x, y, autoAdd);
		
		this.interval = interval;
		attributeInfo = new AttributeInfo();
		attributeInfo.setAtk(10);
		attributeInfo.setDef(5);
		attributeInfo.setHp(20);
		initAttribute();
	}
	
	protected void initAttribute() {
		attribute = new Attribute();
		
		if(interval<=-1){
			interval = new BigDecimal(1.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}

	@Override
	public void setWeapenEffect(IEffect effect) {
		// TODO Auto-generated method stub
		this.effect = effect;
		setEffectListener(this.effect);
	}
	
	protected void setEffectListener(IEffect effect) {
		
		effect.setEffectListener(new IEffect.EffectListener() {
			
			@Override
			public void didEffect(BattleableSprite battleableSpriteByEffecten) {
				// TODO Auto-generated method stub
				int dmg = attributeInfo.getAtk() - battleableSpriteByEffecten.getAttributeInfo().getDef();
				if(dmg > 0){
					battleableSpriteByEffecten.getAttributeInfo().setHp(battleableSpriteByEffecten.getAttributeInfo().getHp() - dmg);
				}
			}
			
		});
	}

	@Override
	public IEffect getWeapenEffect() {
		// TODO Auto-generated method stub
		return effect;
	}

//	@Override
//	public boolean isCollision(BattleableSprite battleableSprite) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	protected boolean isInBattleRange(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		if(!battleable.isBattleable()){
			return false;
		}
		
		if(!isHasATKRange()){			
			return noATKRangeListener.isInNoATKBattleRange(battleable);
		}
		
		double distance = Math.pow(getCenterX() - battleable.getCenterX(), 2) + Math.pow(getCenterY() - battleable.getCenterY(), 2);
		distance = Math.sqrt(distance);
		Log.e("distance", distance+"");
		if(distance <= atkR){
			return true;
		}
		return false;
	}

	protected void attack(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		boolean isAtLeastOneTargetInBattleRange = false;
		for(BattleableSprite battleableSprite : battleables){
			boolean isInBattleRange = isInBattleRange(battleableSprite);
			if(isInBattleRange){ 
				attack(battleableSprite);
				isAtLeastOneTargetInBattleRange = true;
			}
		}
		return isAtLeastOneTargetInBattleRange;
	}

	@Override
	public void setBattleRange(float atkR) {
		// TODO Auto-generated method stub
		this.atkR = atkR;
	}

	@Override
	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo) {
		// TODO Auto-generated method stub
		AttributeInfo attributeInfoNew = new AttributeInfo(attributeInfo);
		attributeInfoNew.setAtk(attributeInfoNew.getAtk()+5);
		return attributeInfoNew;
	}

	@Override
	public void beAttacked(WeapenSprite weapenSprite) {
		// TODO Auto-generated method stub
		
	}	

	public boolean isHasATKRange(){
		if(atkR==NO_ATK_RANGE)
			return false;
		return true;
	}
	
	public interface NoATKRangeListener{
		boolean isInNoATKBattleRange(BattleableSprite battleableSprite);
		void attack(BattleableSprite battleableSprite);
		void willDoEffect(IEffect effect, BattleableSprite battleableSprite);
//		void attackFinish(BattleableSprite battleableSprite);
	}
	
	NoATKRangeListener noATKRangeListener = new NoATKRangeListener() {
		
		@Override
		public boolean isInNoATKBattleRange(BattleableSprite battleableSprite) {
			// TODO Auto-generated method stub
			new RuntimeException();
			return false;
		}

		@Override
		public void attack(BattleableSprite battleableSprite) {
			// TODO Auto-generated method stub
			new RuntimeException();
		}
		
		@Override
		public void willDoEffect(IEffect effect, BattleableSprite battleableSprite) {
			// TODO Auto-generated method stub
			new RuntimeException();
		}
		
//		@Override
//		public void attackFinish(BattleableSprite battleableSprite) {
//			// TODO Auto-generated method stub
//			new RuntimeException();
//		}
	};
	
	public void setNoATKRangeListener(NoATKRangeListener noATKRangeListener){
		this.noATKRangeListener = noATKRangeListener;
	}
	
	HashMap<String, IEffect> effects = new HashMap<String, IEffect>();
	
	public void addWeapenEffect(String key, IEffect effect){
//		effects.add(effect);
		effects.put(key, effect);
	}
	
	public void setWeapenEffectByKey(String key){
		setWeapenEffect(effects.get(key));
	}
}
