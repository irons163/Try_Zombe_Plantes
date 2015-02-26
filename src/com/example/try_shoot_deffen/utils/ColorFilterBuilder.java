package com.example.try_shoot_deffen.utils;

import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public class ColorFilterBuilder {
	private static final int FROZEN = 0;
	private static final int FIRE = 1;
	
	private static ColorMatrixColorFilter[] colorMatrixColorFilters = new ColorMatrixColorFilter[2];
	
	public static void init(){
		colorMatrixColorFilters[FROZEN] = createFozenEffectColor();
		colorMatrixColorFilters[FIRE] = createFireEffectColor();
	}
	
	public static ColorMatrixColorFilter getEffectColor(BattleSpriteInjureType battleSpriteInjureType){
		if(battleSpriteInjureType == BattleSpriteInjureType.Frozen){
			return colorMatrixColorFilters[FROZEN];
		}else if(battleSpriteInjureType == BattleSpriteInjureType.Fire){
			return colorMatrixColorFilters[FIRE];
		}
		
		return null;
	}
	
	private static ColorMatrixColorFilter createFozenEffectColor(){
		ColorMatrix cm = new ColorMatrix();
		
		ColorFilterGenerator.adjustHue(cm, -162);
		
		ColorFilterGenerator.adjustBrightness(cm, -30);
		
		ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(cm);
		
		return colorMatrixColorFilter;
	}
	
	private static ColorMatrixColorFilter createFireEffectColor(){
		ColorMatrix cm = new ColorMatrix();
		
		ColorFilterGenerator.adjustHue(cm, -50);
		
		ColorFilterGenerator.adjustBrightness(cm, -30);
		
		ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(cm);
		
		return colorMatrixColorFilter;
	}
	
}
