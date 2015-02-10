package com.example.try_shoot_deffen;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.example.try_gameengine.scene.Scene;

public class GameScene extends Scene{
	
	MyGameModel gameModel;
	
	public GameScene(Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void initGameModel() {
		// TODO Auto-generated method stub
		gameModel = new MyGameModel(context, null);
	}

	@Override
	public void initGameController() {
		// TODO Auto-generated method stub
		gameController = new MyGameController((Activity)context, gameModel);
	}

}
