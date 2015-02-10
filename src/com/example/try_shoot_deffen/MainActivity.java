package com.example.try_shoot_deffen;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.example.try_gameengine.scene.Scene;
import com.example.try_gameengine.scene.SceneManager;
import com.example.try_gameengine.stage.Stage;
import com.example.try_shoot_deffen.utils.AudioUtil;
import com.example.try_shoot_deffen.utils.BitmapUtil;
import com.example.try_shoot_deffen.utils.CommonUtil;

public class MainActivity extends Stage {
	public static Object Lock = new Object();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;

		AudioUtil.init(this);

		CommonUtil.statusBarHeight = CommonUtil.getStatusBarHeight(this);
		CommonUtil.screenHeight -= CommonUtil.statusBarHeight;

		BitmapUtil.initBitmap(this);

		initStage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public SceneManager initSceneManager() {
		// TODO Auto-generated method stub
		SceneManager sceneManager = new SceneManager();
		sceneManager.addScene(new GameScene(this, "game", 0, Scene.RESTART));
		sceneManager.startScene(0);
		return sceneManager;
	}
}
