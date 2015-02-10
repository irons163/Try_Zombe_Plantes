package com.example.try_shoot_deffen;

import android.app.Activity;
import android.view.SurfaceHolder;
import android.widget.ImageView;

import com.example.try_gameengine.assemble.AssembleView;
import com.example.try_gameengine.assemble.AssembleViewConfig;
import com.example.try_gameengine.assemble.AssembleViewConfig.DirectionConfig;
import com.example.try_gameengine.framework.GameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_shoot_deffen.utils.BitmapUtil;

public class MyGameController extends GameController{
	MyGameView gameView;
	
	public MyGameController(Activity activity, IGameModel gameModel) {
		super(activity, gameModel);
		// TODO Auto-generated constructor stub
		initStart();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	AssembleView view;
	
	@Override
	protected void initGameView(Activity activity, IGameModel gameModel) {
		// TODO Auto-generated method stub
		gameView = new MyGameView(activity, this, gameModel);
		
		AssembleViewConfig config = new AssembleViewConfig.Builder().setDirectionConfig(DirectionConfig.LEFT).build();
		view = new AssembleView(gameView, activity);
	    
		AssembleView view2 = new AssembleView(R.layout.card_view, activity);
		view2.setConfig(config);
		
		ImageView imageView = (ImageView) view2.getView().findViewById(R.id.card_imageView);
		imageView.setImageBitmap(BitmapUtil.bullet01);
		
		view.addSubView(view2);	
	}

	@Override
	protected void arrangeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setActivityContentView(Activity activity) {
		// TODO Auto-generated method stub
//		activity.setContentView(gameView);
		activity.setContentView(view.generateViews());
	}

	@Override
	protected void beforeGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterGameStart() {
		// TODO Auto-generated method stub
		
	}

}
