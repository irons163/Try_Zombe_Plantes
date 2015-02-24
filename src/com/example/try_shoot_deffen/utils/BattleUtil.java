package com.example.try_shoot_deffen.utils;

import java.util.List;

import android.util.MonthDisplayHelper;

import com.example.try_shoot_deffen.model.BattleableSprite;

public class BattleUtil {
	
	public static void checkBattle(List<BattleableSprite> battleableSpriteDefeners, List<BattleableSprite> battleableSpriteMonsters){
//		if(battleableSprite.isInBattleRange(battleableSprite2)){
//			battleableSprite.attack(battleableSprite2);
//		}
//		if(battleableSprite2.isInBattleRange(battleableSprite)){
//			battleableSprite2.attack(battleableSprite);
//		}
		for(BattleableSprite defener : battleableSpriteDefeners){
			defener.checkIfInBattleRangeThenAttack(battleableSpriteMonsters);
		}
		for(BattleableSprite monster : battleableSpriteMonsters){
			monster.checkIfInBattleRangeThenAttack(battleableSpriteDefeners);
		}
	}
	
	public static int[] changeToNew(int[] olds, float time){
		int[] news = new int[olds.length];
		for(int i = 0; i<olds.length; i++){
			int t = olds[i];
			news[i] = Math.round(t*time);
		}
		return news;
	}
	
	public static int changeToNew(int old, float time){
		int thenew = Math.round(old*time);
		return thenew;
	}
}
