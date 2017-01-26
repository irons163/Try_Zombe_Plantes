package com.example.try_shoot_deffen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.try_shoot_deffen.effect.FireEffect;
import com.example.try_shoot_deffen.effect.FrozenEffect;
import com.example.try_shoot_deffen.effect.HealEffect;
import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.effect.NormalEffect;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;

public class AttributeInfo {
	private int hp;
	private int atk;
	private int def;
	
	private List<IEffect> effectStatusList = new CopyOnWriteArrayList<IEffect>(); 
	
	public AttributeInfo(){
		
	}
	
	public AttributeInfo(AttributeInfo attributeInfo){
		this.hp = attributeInfo.hp;
		this.atk = attributeInfo.atk;
		this.def = attributeInfo.def;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	
	public void addToEffectStatusList(IEffect effect){
		effectStatusList.add(effect);
	}
	
	public void removeFromEffectStatusList(IEffect effect){
		effectStatusList.remove(effect);
	}
	
	public void removeAllFromEffectStatusList(){
		effectStatusList.clear();
	}
	
	public void removeTheSameEffectFromEffectStatusList(IEffect effect){
		if(effect instanceof FrozenEffect){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof FrozenEffect){
					effectStatusList.remove(effect);
					break;
				}
			}
		}else if(effect instanceof FireEffect){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof FireEffect){
					effectStatusList.remove(effect);
					break;
				}
			}
		}
	}
	
	public boolean checkIsAlreadyHasTheSameEffect(IEffect effect){
		
		boolean alreadyHasTheSameEffect = false;
		
		if(effect instanceof FrozenEffect){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof FrozenEffect){
					alreadyHasTheSameEffect = true;
					break;
				}
			}
		}
		
		if(effect instanceof FireEffect){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof FireEffect){
					alreadyHasTheSameEffect = true;
					break;
				}
			}
		}
		
		return alreadyHasTheSameEffect;
	}
	
	public boolean checkHasEffectOrNotByEffectType(BattleSpriteInjureType battleSpriteInjureType){
		
		boolean hasEffect = false;
		
		if(battleSpriteInjureType == BattleSpriteInjureType.Frozen){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof FrozenEffect){
					hasEffect = true;
					break;
				}
			}
		} else if(battleSpriteInjureType == BattleSpriteInjureType.Fire){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof FireEffect){
					hasEffect = true;
					break;
				}
			}
		} else if(battleSpriteInjureType == BattleSpriteInjureType.Normal){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof NormalEffect){
					hasEffect = true;
					break;
				}
			}
		} else if(battleSpriteInjureType == BattleSpriteInjureType.Heal){
			
			for(IEffect efIEffect : effectStatusList){
				if(efIEffect instanceof HealEffect){
					hasEffect = true;
					break;
				}
			}
		}
		
		return hasEffect;
	}
	
	public void checkAllEffect(BattleableSprite battleableSprite){
		for(IEffect efIEffect : effectStatusList){
			efIEffect.checkEffect(battleableSprite);
		}
	}
}
