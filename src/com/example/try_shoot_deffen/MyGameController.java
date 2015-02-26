package com.example.try_shoot_deffen;

import android.app.Activity;
import android.content.ClipData.Item;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.try_gameengine.assemble.AssembleView;
import com.example.try_gameengine.assemble.AssembleViewConfig;
import com.example.try_gameengine.assemble.AssembleViewConfig.DirectionConfig;
import com.example.try_gameengine.framework.GameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_shoot_deffen.model.Defener;
import com.example.try_shoot_deffen.model.DefenerBuilder;
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
	    
		final AssembleView view2 = new AssembleView(R.layout.card_view, activity);
		view2.setConfig(config);
		
		OnClickListener clickListener = new OnClickListener() {
			
			LinearLayout linearLayout = (LinearLayout) view2.getView().findViewById(R.id.card_view_frame1);
			LinearLayout linearLayout2 = (LinearLayout) view2.getView().findViewById(R.id.card_view_frame2);
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.card_imageView:
					DefenerBuilder.select = 0;
					linearLayout.setBackgroundResource(R.drawable.frame);
					linearLayout2.setBackgroundColor(Color.TRANSPARENT);
					break;
				case R.id.card_imageView2:
					DefenerBuilder.select = 1;
					linearLayout2.setBackgroundResource(R.drawable.frame);
					linearLayout.setBackgroundColor(Color.TRANSPARENT);
					break;
				default:
					break;
				}
			}
		}; 
		
		ImageView imageView = (ImageView) view2.getView().findViewById(R.id.card_imageView);
		imageView.setImageBitmap(BitmapUtil.bullet01);
//		imageView.setBackgroundResource(R.drawable.bullet01);
//		imageView.setBackgroundResource(R.drawable.image_item);
		imageView.setOnClickListener(clickListener);
		
		ImageView imageView2 = (ImageView) view2.getView().findViewById(R.id.card_imageView2);
		imageView2.setImageBitmap(BitmapUtil.bullet01);
		imageView2.setOnClickListener(clickListener);
		
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
