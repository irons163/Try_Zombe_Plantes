package com.example.try_shoot_deffen.utils;

import com.example.try_shoot_deffen.model.BattleableSprite;

public class BattleUtil {
	
	public static void checkBattle(BattleableSprite battleableSprite, BattleableSprite battleableSprite2){
		if(battleableSprite.isInBattleRange(battleableSprite2)){
			battleableSprite.attack(battleableSprite2);
		}
		if(battleableSprite2.isInBattleRange(battleableSprite)){
			battleableSprite2.attack(battleableSprite);
		}
	}
	
}
