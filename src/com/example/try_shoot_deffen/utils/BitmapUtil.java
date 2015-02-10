package com.example.try_shoot_deffen.utils;

import java.io.IOException;
import java.io.InputStream;

import com.example.try_shoot_deffen.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings.Global;

public class BitmapUtil {
	static Context context;
	
public static Bitmap bar;
	
	public static Bitmap brick_once_bmp;
	public static Bitmap brick_twice_bmp;
	public static Bitmap brick_three_bmp;
	public static Bitmap brick_iron_bmp;
	public static Bitmap brick_time_bmp;
	public static Bitmap brick_tool_bmp;
	public static Bitmap brick_ball_level_up_bmp;
	public static Bitmap brick_iron_break_bmp;

//	public static Bitmap tool_BallSpeedUp_bmp;
//	public static Bitmap tool_BallSpeedDown_bmp;
//	public static Bitmap tool_StickLongUp_bmp;
//	public static Bitmap tool_StickLongDown_bmp;
//	public static Bitmap tool_BallCountUpToThree_bmp;
//	public static Bitmap tool_LifeUp_bmp;
//	public static Bitmap tool_Weapen_bmp;
//	public static Bitmap tool_BallReset_bmp;
//	public static Bitmap tool_StickLongMax_bmp;
//	public static Bitmap tool_BallRadiusUp_bmp;
//	public static Bitmap tool_BallRadiusDown_bmp;
//	public static Bitmap tool_BlackHole_bmp;
////	public static Bitmap tool_BallLevelUpOnce_bmp;
//	public static Bitmap tool_BallLevelUpTwice_bmp;
//	public static Bitmap tool_BallLevelDownOnce_bmp;
	
	public static Bitmap tool_DoubleBullets_bmp;
	public static Bitmap tool_TripleBullets_bmp;
	public static Bitmap tool_doubleSpeedBullets_bmp;
	public static Bitmap tool_doublePowerBullets_bmp;
	public static Bitmap tool_Cure_AddHp_bmp;
	public static Bitmap tool_DoubleBullets_bmp02;
	public static Bitmap tool_TripleBullets_bmp02;
	public static Bitmap tool_doubleSpeedBullets_bmp02;
	public static Bitmap tool_doublePowerBullets_bmp02;
	public static Bitmap tool_Cure_AddHp_bmp02;
	
	public static Bitmap ball_Show_bmp;
	
	public static Bitmap cat01_1,cat01_2,cat01_3,cat01_4,cat02_1,cat02_2,cat02_3,cat02_4,cat03_1,cat03_2,cat03_3,cat03_4,cat04_1,cat04_2,cat04_3,cat04_4;
	
	public static Bitmap[][] catBitmaps = new Bitmap[2][2];
	public static Bitmap[][] catInjureBitmaps = new Bitmap[2][1];
	public static Bitmap[][] catDeadBitmaps = new Bitmap[2][1];
	
	public static Bitmap bullet, bullet01, bullet_peanut;
	
	public static Bitmap hamster, hamsterShoot, hamsterShoot2;
	public static Bitmap hand;
	
//	public static Bitmap gamebg;
//	public static Bitmap gameboard;
	
	public static Bitmap bg01,bg02;
	
	public static Bitmap controller_bar, info_bar;
	
	public static Bitmap hp;
	
	public static Bitmap s0,s1,s2,s3,s4,s5,s6,s7,s8,s9,dot;
	
	public static Bitmap explode;
	
	public static Bitmap cat_hp_bar, cat_hp_bar_frame;
	
	public static Bitmap hamster_injure;
	
	public static Bitmap start_bmp;
	
	public static Bitmap invincibel;
	
	public static void initBitmap(Context context){
		BitmapUtil.context = context;
//		initBitmap();
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 1;
		options.inJustDecodeBounds = false;
		
		brick_once_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick01, options);
		brick_twice_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick02, options);
		brick_three_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick03, options);
		brick_iron_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick04, options);
		brick_time_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick05, options);
		brick_tool_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick06, options);
		brick_ball_level_up_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick07, options);
		brick_iron_break_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick04_1, options);
		
		brick_once_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick01, options);
		brick_twice_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick02, options);
		brick_three_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick03, options);
		brick_iron_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick04, options);
		brick_time_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick05, options);
		brick_tool_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick06, options);
		brick_ball_level_up_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick07, options);
		brick_iron_break_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.brick04_1, options);
		
//		tool_BallSpeedUp_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool01, options);
//		tool_BallSpeedDown_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool02, options);
//		tool_StickLongUp_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool03, options);
//		tool_StickLongDown_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool04, options);
//		tool_BallCountUpToThree_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool05, options);
//		tool_LifeUp_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool06, options);
//		tool_Weapen_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool07, options);
//		tool_BallReset_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool08, options);
//		tool_StickLongMax_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool09, options);
//		tool_BallRadiusUp_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool10, options);
//		tool_BallRadiusDown_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool11, options);
//		tool_BlackHole_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool12, options);
//		tool_BallLevelUpTwice_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool13, options);
//		tool_BallLevelDownOnce_bmp = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.tool14, options);
		
		tool_DoubleBullets_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool1_1, options);
		tool_DoubleBullets_bmp02 = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool1_2, options);
		tool_TripleBullets_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool3_1, options);
		tool_TripleBullets_bmp02 = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool3_2, options);
		tool_doubleSpeedBullets_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool2_1, options);
		tool_doubleSpeedBullets_bmp02 = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool2_2, options);
		tool_doublePowerBullets_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool4_1, options);
		tool_doublePowerBullets_bmp02 = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool4_2, options);
		tool_Cure_AddHp_bmp = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool5_1, options);
		tool_Cure_AddHp_bmp02 = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.tool5_2, options);
		
		bar = BitmapFactory.decodeResource(context.getResources(), R.drawable.bar);  
		
		controller_bar = BitmapFactory.decodeResource(context.getResources(), R.drawable.controller_bar);
		info_bar = BitmapFactory.decodeResource(context.getResources(), R.drawable.info_bar);
		
//		BitmapUtil.gamebg = BitmapUtil.createSpecificSizeBitmap(getResources().getDrawable(R.drawable.gamebg), CommonUtil.screenWidth, (int) (CommonUtil.screenWidth/3.0f));
		BitmapUtil.controller_bar = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.controller_bar), CommonUtil.screenWidth, BitmapUtil.controller_bar.getHeight());
		BitmapUtil.info_bar = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.info_bar), CommonUtil.screenWidth, BitmapUtil.info_bar.getHeight());
//		BitmapUtil.gameboard = BitmapUtil.createSpecificSizeBitmap(getResources().getDrawable(R.drawable.gameboard), CommonUtil.screenWidth, CommonUtil.screenHeight - BitmapUtil.gamebg.getHeight() - BitmapUtil.controller_bar.getHeight() - BitmapUtil.info_bar.getHeight());
		CommonUtil.gameBoardHeight = CommonUtil.screenHeight - BitmapUtil.controller_bar.getHeight() - BitmapUtil.info_bar.getHeight();
		BitmapUtil.bg01 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.bg01), CommonUtil.screenWidth, CommonUtil.gameBoardHeight);
		BitmapUtil.bg02 = BitmapUtil.createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.bg02), CommonUtil.screenWidth, CommonUtil.gameBoardHeight);
		
		cat01_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat01_1); 
		cat01_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat01_2);
		cat01_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat01_3);
		cat01_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat01_4);
		cat02_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat02_1);
		cat02_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat02_2);
		cat02_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat02_3);
		cat02_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat02_4);
//		cat03_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat03_1);
//		cat03_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat03_2);
//		cat03_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat03_3);
//		cat03_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat03_4);
//		cat04_1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat04_1);
//		cat04_2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat04_2);
//		cat04_3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat04_3);
//		cat04_4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat04_4);
		
		cat_hp_bar_frame = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_hp_bar_frame);
		cat_hp_bar_frame = createBitmap(cat_hp_bar_frame, (float)CommonUtil.screenWidth/cat_hp_bar_frame.getWidth());
		
		cat_hp_bar = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_hp_bar);
		cat_hp_bar = createBitmap(cat_hp_bar, (float)CommonUtil.screenWidth/cat_hp_bar.getWidth());
		
		CommonUtil.cat_bg_height = CommonUtil.gameBoardHeight/149.0f*37;
		float cat_hright = CommonUtil.cat_bg_height - cat_hp_bar_frame.getHeight();
		
		//?®Ë??™ÊòØÂÆåÂÖ®?áÈ??ÑÊ?Ê≥Å‰?
//		cat01_1 = createBitmap(cat01_1, cat_hright/cat01_1.getHeight());
//		cat01_2 = createBitmap(cat01_2, cat_hright/cat01_2.getHeight());
//		cat01_3 = createBitmap(cat01_3, cat_hright/cat01_3.getHeight());
//		cat01_4 = createBitmap(cat01_4, cat_hright/cat01_4.getHeight());
//		cat02_1 = createBitmap(cat02_1, cat_hright/cat02_1.getHeight());
//		cat02_2 = createBitmap(cat02_2, cat_hright/cat02_2.getHeight());
//		cat02_3 = createBitmap(cat02_3, cat_hright/cat02_3.getHeight());
//		cat02_4 = createBitmap(cat02_4, cat_hright/cat02_4.getHeight());
		
		//Ë≤ìÂí™?Ñ‰??¢Ê??ôÁôΩ
		cat01_1 = createBitmap(cat01_1, CommonUtil.cat_bg_height/cat01_1.getHeight());
		cat01_2 = createBitmap(cat01_2, CommonUtil.cat_bg_height/cat01_2.getHeight());
		cat01_3 = createBitmap(cat01_3, CommonUtil.cat_bg_height/cat01_3.getHeight());
		cat01_4 = createBitmap(cat01_4, CommonUtil.cat_bg_height/cat01_4.getHeight());
		cat02_1 = createBitmap(cat02_1, CommonUtil.cat_bg_height/cat02_1.getHeight());
		cat02_2 = createBitmap(cat02_2, CommonUtil.cat_bg_height/cat02_2.getHeight());
		cat02_3 = createBitmap(cat02_3, CommonUtil.cat_bg_height/cat02_3.getHeight());
		cat02_4 = createBitmap(cat02_4, CommonUtil.cat_bg_height/cat02_4.getHeight());
		
//		cat02_4 = createBitmap(cat02_4, (float)CommonUtil.screenWidth/3/cat02_4.getWidth());
		
		catBitmaps[0] = new Bitmap[]{cat01_1,cat01_2};
		catBitmaps[1] = new Bitmap[]{cat02_1,cat02_2};
		
		catInjureBitmaps[0] = new Bitmap[]{cat01_3,cat01_3};
		catInjureBitmaps[1] = new Bitmap[]{cat02_3,cat02_3};
		
		catDeadBitmaps[0] = new Bitmap[]{cat01_4,cat01_4};
		catDeadBitmaps[1] = new Bitmap[]{cat02_4,cat02_4};
		
		bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);	
		bullet01 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet01);
		bullet_peanut = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_peanut);
		
		hamster = BitmapFactory.decodeResource(context.getResources(), R.drawable.hamster);
	
		hamsterShoot  = BitmapFactory.decodeResource(context.getResources(), R.drawable.green_point);
		hamsterShoot2  = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_point);
	
		hand = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_hand);
		
		brick_once_bmp = hand;
		brick_twice_bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_hand2);
		brick_three_bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat_hand3);
		
//		gamebg = BitmapFactory.decodeResource(context.getResources(), R.drawable.gamebg);
//		gameboard = BitmapFactory.decodeResource(context.getResources(), R.drawable.gameboard);
		
//		bg01 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg01);
//		bg02 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg02);
		
		
	
		hp = BitmapFactory.decodeResource(context.getResources(), R.drawable.hp); 
		
		s0 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s0);
		s1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s1);
		s2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s2);
		s3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s3);
		s4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s4);
		s5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s5);
		s6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s6);
		s7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s7);
		s8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s8);
		s9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.s9);
		dot = BitmapFactory.decodeResource(context.getResources(), R.drawable.dot);
		
		float hamsterPersentByScreen = 6.0f;
		hamster = createBitmap(hamster, CommonUtil.screenWidth/hamsterPersentByScreen*7/hamster.getWidth());
		
		float handPersentByScreen = 4.0f;
		hand = brick_once_bmp = createBitmap(brick_once_bmp, CommonUtil.screenWidth/handPersentByScreen*7/brick_once_bmp.getWidth());
		brick_twice_bmp = createBitmap(brick_twice_bmp, CommonUtil.screenWidth/handPersentByScreen*7/brick_twice_bmp.getWidth());
		brick_three_bmp = createBitmap(brick_three_bmp, CommonUtil.screenWidth/handPersentByScreen*7/brick_three_bmp.getWidth());
		    
		tool_DoubleBullets_bmp = createBitmap(tool_DoubleBullets_bmp, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_DoubleBullets_bmp.getWidth());
		tool_DoubleBullets_bmp02 = createBitmap(tool_DoubleBullets_bmp02, CommonUtil.screenWidth/handPersentByScreen/2.0f/tool_DoubleBullets_bmp02.getWidth());
		tool_TripleBullets_bmp = createBitmap(tool_TripleBullets_bmp, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_TripleBullets_bmp.getWidth());
		tool_TripleBullets_bmp02 = createBitmap(tool_TripleBullets_bmp02, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_TripleBullets_bmp02.getWidth());
		tool_doubleSpeedBullets_bmp = createBitmap(tool_doubleSpeedBullets_bmp, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_doubleSpeedBullets_bmp.getWidth());
		tool_doubleSpeedBullets_bmp02 = createBitmap(tool_doubleSpeedBullets_bmp02, CommonUtil.screenWidth/handPersentByScreen/2.0f/tool_doubleSpeedBullets_bmp02.getWidth());
		tool_doublePowerBullets_bmp = createBitmap(tool_doublePowerBullets_bmp, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_doublePowerBullets_bmp.getWidth());
		tool_doublePowerBullets_bmp02 = createBitmap(tool_doublePowerBullets_bmp02, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_doublePowerBullets_bmp02.getWidth());
		tool_Cure_AddHp_bmp = createBitmap(tool_Cure_AddHp_bmp, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_Cure_AddHp_bmp.getWidth());
		tool_Cure_AddHp_bmp02 = createBitmap(tool_Cure_AddHp_bmp02, CommonUtil.screenWidth/handPersentByScreen/3.0f/tool_Cure_AddHp_bmp02.getWidth());
		
		explode = BitmapFactory.decodeResource(context.getResources(), R.drawable.explode);
		explode = createBitmap(explode, (float)hand.getWidth()/explode.getWidth());
		
		hamster_injure = BitmapFactory.decodeResource(context.getResources(), R.drawable.hamster_injure);
		hamster_injure = createBitmap(hamster_injure, CommonUtil.screenWidth/hamsterPersentByScreen/hamster_injure.getWidth());
		
		float bulletPersentByScreen = 10.0f;
		bullet = createBitmap(bullet, CommonUtil.screenWidth/bulletPersentByScreen*3.0f/bullet.getWidth());
		bullet_peanut = createBitmap(bullet_peanut, CommonUtil.screenWidth/bulletPersentByScreen*8.0f/bullet_peanut.getWidth());
	
		start_bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.start);
		start_bmp = createBitmap(start_bmp, CommonUtil.cat_bg_height/3.0f*2/start_bmp.getHeight());
		
		invincibel = BitmapFactory.decodeResource(context.getResources(), R.drawable.invincible);
		float invincibelPersentByScreen = 4.5f;
		invincibel = createBitmap(invincibel, CommonUtil.screenWidth/invincibelPersentByScreen*4/invincibel.getWidth());
	}
	
	public static void resizeToolBmp(){
		
	}
	
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width, int height) {
		
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); 
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas); 
		return bitmap;
	}
	
	public static Bitmap getBitmap(String path) {
		try {
			InputStream is = context.getAssets().open(path);

			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap getBitmapFromRes(int resId){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
		return bitmap;
	}
	
	public static Bitmap createBitmap(Bitmap bmp, float scale){
		
		Matrix matrix = new Matrix(); 
		
		matrix.postScale(scale, scale); 
			
		Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(), 
		
				bmp.getHeight(),matrix,true);
		return resizeBmp; 
	}
	
	public static void createTimeCounterBitmap(int w, int h){
		float scaleX, scaleY, scale; 
		scaleX = (float)w/s0.getWidth();
		scaleY = (float)h/s0.getHeight();
		
		scale = scaleX < scaleY ? scaleX : scaleY;
		
		s0 = createBitmap(s0, scale);
		s1 = createBitmap(s1, scale);
		s2 = createBitmap(s2, scale);
		s3 = createBitmap(s3, scale);
		s4 = createBitmap(s4, scale);
		s5 = createBitmap(s5, scale);
		s6 = createBitmap(s6, scale);
		s7 = createBitmap(s7, scale);
		s8 = createBitmap(s8, scale);
		s9 = createBitmap(s9, scale);
		dot = createBitmap(dot, scale);

	}
	
}
