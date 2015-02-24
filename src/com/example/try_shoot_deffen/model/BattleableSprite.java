package com.example.try_shoot_deffen.model;

import java.util.List;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.model.Cat.ShootType;

public class BattleableSprite extends Sprite implements Battleable{
	protected float battleRange = 50.0f;
	protected float battleInviable = 1.0f;
	protected WeapenSprite weapenSprite;
	protected AttributeInfo attributeInfo;
	
	public BattleableSprite(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		attributeInfo = new AttributeInfo();
		attributeInfo.setAtk(10);
		attributeInfo.setDef(5);
		attributeInfo.setHp(20);
	}

	protected boolean isInBattleRange(BattleableSprite battleableSprite) {
		// TODO Auto-generated method stub
		boolean isInBattleRange;
		if(weapenSprite!=null)
			isInBattleRange = weapenSprite.isInBattleRange(battleableSprite);
		else
			isInBattleRange = collisionRectF.contains(battleableSprite.collisionRectF);
		
		return isInBattleRange;
	}

	protected void attack(BattleableSprite battleable) {
		// TODO Auto-generated method stub
		
		AttributeInfo attributeInfoNew;
		if(weapenSprite!=null){
//			attributeInfoNew = weapenSprite.getNewAttributeInfo(attributeInfo);
			weapenSprite.attack(battleable);
		}else{
			attributeInfoNew = attributeInfo;
		
			AttributeInfo attributeInfoBeAttacked = battleable.getAttributeInfo();
			
			int hurt = attributeInfoNew.getAtk() - attributeInfoBeAttacked.getDef();
			attributeInfoBeAttacked.setHp(attributeInfoBeAttacked.getHp() - hurt);
		}
		
//		if(weapenSprite!=null)
//			battleable.beAttacked(weapenSprite);
//		else
//			battleable.beAttacked(null);
	}
	
	@Override
	public void checkIfInBattleRangeThenAttack(
			List<BattleableSprite> battleables) {
		// TODO Auto-generated method stub
		if(weapenSprite!=null)
			weapenSprite.checkIfInBattleRangeThenAttack(battleables);
		else
		for(BattleableSprite battleableSprite : battleables){
//			boolean isInBattleRange = isInBattleRange(battleableSprite);
//			if(isInBattleRange){
//				attack(battleableSprite);
//			}
			
			
				collisionRectF.contains(battleableSprite.collisionRectF);
			
		}
		
	}

	public float getSpeed(){
		return battleInviable;
	}
	
	public void setSpeed(float speeed){
		battleInviable = speeed;
	}
	
	public AttributeInfo getAttributeInfo(){
		return attributeInfo;
	}

	@Override
	public void setBattleRange(float atkR) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beAttacked(WeapenSprite weapenSprite) {
		// TODO Auto-generated method stub
		
	}
	
	public enum BattleSpriteInjureType{
		None, Normal, Frozen, Fire
	}
	
	BattleSpriteInjureType battleSpriteInjureType;
	
	public void injure(BattleSpriteInjureType battleSpriteInjureType){
		this.battleSpriteInjureType = battleSpriteInjureType;
		
		switch (battleSpriteInjureType) {
		
		case Normal:
			
			break;
		case Frozen:
			
			break;
		default:
			break;
		}
	}


}
