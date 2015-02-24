package com.example.try_shoot_deffen.utils;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_shoot_deffen.model.BattleableSprite;
import com.example.try_shoot_deffen.model.MapTileObject;

public class MapTileUtil {
	private MapTileObject[][] mapTileObjects = new MapTileObject[5][5];
	
	private float offsetX = 100.0f;
	private float offsetY = 100.0f;
	
	public MapTileUtil(){
		init();
	}
	
	private void init(){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				mapTileObjects[i][j] = new MapTileObject(i*150+offsetX, j*150+offsetY, false);
				mapTileObjects[i][j].setBitmapAndAutoChangeWH(BitmapUtil.hp);
			}
		}
	}
	
	public void checkMapDefenerInBattle(List<BattleableSprite> battleableSprites){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
//				mapTileObjects[i][j] = new MapTileObject(i*100, j*100, false);
				MapTileObject mapTileObject = mapTileObjects[i][j];
				BattleableSprite defener = mapTileObject.getSprite();
				if(defener!=null){
					for(BattleableSprite monster : battleableSprites){
						BattleUtil.checkBattle(defener, monster);
					}		
				}
			}
		}
	}
	
	public MapTileObject checkTouchXY(float x, float y){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				MapTileObject mapTileObject = mapTileObjects[i][j];
				if(mapTileObject.isTouchXY(x ,y)){
					return mapTileObject;
				}
			}
		}
		
		return null;
	}
	
	public void frameTrig(){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				mapTileObjects[i][j].frameTrig();
			}
		}
	}
	
	public void drawSelf(Canvas canvas, Paint paint){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				mapTileObjects[i][j].drawSelf(canvas, paint);
			}
		}
	}
}
