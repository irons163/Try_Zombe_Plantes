package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSet;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.script.ScriptPaser;
import com.example.try_shoot_deffen.MyGameModel;
import com.example.try_shoot_deffen.effect.IEffect;
import com.example.try_shoot_deffen.model.BattleableSprite.BattleSpriteInjureType;
import com.example.try_shoot_deffen.utils.BitmapUtil;
import com.example.try_shoot_deffen.utils.CommonUtil;
import com.example.try_shoot_deffen.utils.EffectUtil;

public class Bullets extends WeapenSprite{

	MovementAction movementActionShoot;
	MovementAction movementAction;
	double limit;
	final int Down = 0;
	final int Top = 1;
	int type = Down;
	final int stopRlectCount = 2;
	int reflectCounter = 0;
	boolean isMoveable = true;
	
	public static final int TYPE_SINGLE_MID = 0;
	public static final int TYPE_TWO_HAND_LEFT = 1;
	public static final int TYPE_TWO_HAND_RIGHT = 2;
	public static final int TYPE_THREE_HAND_LEFT = 3;
	public static final int TYPE_THREE_HAND_MID = 4;
	public static final int TYPE_THREE_HAND_RIGHT = 5;
	public static final int TYPE_TWO_HAND_LEFT_UP = 6;
	public static final int TYPE_TWO_HAND_RIGHT_UP = 7;
	int type_direction = TYPE_SINGLE_MID;
//	String[] singleHandSciptName = {"hand_single_mid.txt"};
//	String[] twoHandSciptName = {"hand_left.txt", "hand_right.txt"};
//	String[] threeHandSciptName = {"hand_left.txt", "hand_mid.txt", "hand_right.txt"};
	static String[] handScriteNames = {"hand_single_mid.txt", "hand_left.txt", "hand_right.txt", "hand_left.txt", "hand_mid.txt", "hand_right.txt", "hand_left.txt", "hand_right.txt"};
	
enum HandAnimaton {

		
		Move(
		"RABIT_MOVE",
		new int[] {
				
				7,
				7,
				7,
				7,
				7,
				7,
				7,
				7,
				7,
				7,
				7,
				7,
				7,
				7
		}),
					
		;

		String name;
		int[] framesTime;

		private HandAnimaton(String name, int[] framesTime) {
			this.name = name;
			this.framesTime = framesTime;
		}

		public String getName() {
			return name;
		}

		public int[] getFramesTime() {
			return framesTime;
		}
	}

	public Bullets(Context context, float x, float y, boolean autoAdd, int type_direction) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
//		setBitmapAndAutoChangeWH(BitmapUtil.hand);
		setBitmapAndFrameWH(BitmapUtil.hand, BitmapUtil.hand.getWidth()/7, BitmapUtil.hand.getHeight()/2);
		addActionFPSFrame(HandAnimaton.Move.getName(), new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13}, HandAnimaton.Move.getFramesTime());
		
//		setX(x - w/2);
		this.type_direction = type_direction;
		
//		if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
//			reflectCounter++;
//		}
		if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
			if(type==Top){
				type = Down;
			}else{
				type = Top;
			}
			limit = CommonUtil.cat_bg_height;
		}
		this.context = context;
		initCollisiontRectF();
		initMove();
	}
	
	private void initCollisiontRectF(){
		setCollisionRectFEnable(true);
		float collisionWidth = w/3.0f;
		float collisionHitght = h/3.0f;
		float collisionOffsetX = w/2-collisionWidth/2;
		float collisionOffsetY = h/2-collisionHitght/2;
		setCollisionOffsetXY(collisionOffsetX, collisionOffsetY);
		setCollisionRectFWH(collisionWidth, collisionHitght);
		setCollisionRectF(getX()+collisionOffsetX, getY()+collisionOffsetY, getX()+collisionOffsetX+collisionWidth, getY()+collisionOffsetY+collisionHitght);
	}

	private void initMove() {
		final ScriptPaser scriptPaser = new ScriptPaser();
		scriptPaser.paser(context, this, handScriteNames[type_direction]);
		
		scriptPaser.setScriptTriggerDoCommandLisener(new ScriptPaser.ScriptTriggerDoCommandLisener() {
			
			@Override
			public void onCommandPause() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCommandMove(float dx, float dy) {
				// TODO Auto-generated method stub
				if(!isMoveable)
					return;
				if(scriptPaser.isMove()){
					dx = scriptPaser.getDx();
					Log.e("script dx", dx+"");
					if(scriptPaser.getDx()>0){
						if(getX()+w >= moveRage.right){
							scriptPaser.setDx(-scriptPaser.getDx());
						}
					}else{
						if(getX() <= moveRage.left){
							scriptPaser.setDx(-scriptPaser.getDx());
						}
					}
				}
				
				dx = scriptPaser.getDx();
				
				
				Bullets.this.move(dx, dy);
			}
		});
		

		
		movementAction = new MovementActionSetWithThreadPool();
		movementAction.setMovementActionController(new MovementAtionController());
		MovementActionInfo info = new MovementActionInfo(2000, 1, 0, 4.0f, "", null, false, this, HandAnimaton.Move.getName());
		MovementActionItemBaseReugularFPS action = new MovementActionItemBaseReugularFPS(info);
		movementAction.addMovementAction(action);
		
		movementAction.isLoop = true;
		
		movementAction.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				if(!isMoveable)
					return;
				float bottom = Bullets.this.getY()+h;
				float top = Bullets.this.getY();
				Log.e("hand bottom", bottom + "");
				
//				if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP)
//					dy *= -1;
				
				
				
				if(type==Top){
					dy = -dy;
				}
				
				if( (bottom >= limit && type==Down) || (top <= limit && type==Top)){
//					dy = -dy;
//					if(type==Down || (type==Top &&( type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP))){
					if(type==Down){
							type=Top;
						if(reflectCounter==stopRlectCount){
							limit = moveRage.height() - moveRage.height()*Math.pow(0.66d, reflectCounter);
						}else{
							reflectCounter++;
							limit = moveRage.height() - moveRage.height()*Math.pow(0.66d, reflectCounter);
						}
							
						drawWithoutClip = true;
						isMoveable = false;
						
					}else{
						type=Down;
						limit = moveRage.bottom;
					}
					
				}
				
				
				move(dx, dy);
				
			}
		});
		
		movementAction.initMovementAction();
		
		movementAction.start();
		
//		setMovementAction(movementAction);
		
		
		
		movementActionShoot = new MovementActionSetWithThreadPool();
		info = new MovementActionInfo(2000, 1, 2, 0, "", null, false);
		MovementActionItemBaseReugularFPS reFlectaction = new MovementActionItemBaseReugularFPS(info);
		movementActionShoot.addMovementAction(reFlectaction);
		
		movementActionShoot.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				

				
				move(dx, dy);
				
//				scriptPaser.nextScriptLine();

				
//				scriptPaser.triggerAndDoCommandInSprite();
				
			}
		});
		
		movementActionShoot.initMovementAction();
		
		movementActionShoot.start();
		
		setMovementAction(movementActionShoot);

	}
	
	
	public int getSpeedX(){
		return 100;
	}
	
	public int getSpeedY(){
		return 100;
	}
	
	public void setSpeedX(int x){
		
	}
	
public void setSpeedY(int x){
		
	}

private final int Brick_Once = 0;
private final int Brick_Twice = 1;
private final int Brick_Three = 2;
private final int Brick_Iron = 3;
private final int Brick_Time = 4;
private final int Brick_Tool = 5;
private final int Brick_BallLevelUP = 6;
private final int Brick_SplitTwo = 7;
//Rect rect = new Rect();
//Bitmap bitmap;
//public int left, top, right, bottom;
Context context;
MyGameModel ballView;
EffectUtil effectUtil;
int whichBrickType;
private float angle;

public void set(int playGameLevel, MyGameModel ballView) {
	this.ballView = ballView;
//	rect.set(left, top, right, bottom);
//	this.left = left;
//	this.top = top;
//	this.right = right;
//	this.bottom = bottom;
	Random random = new Random();
	// 10��
	// if(playGameLevel == 0 || playGameLevel ==1)
	// whichBrickType = random.nextInt(1);
	// else if(playGameLevel == 2 || playGameLevel ==3){
	// int temp = random.nextInt(3);
	// if(temp ==0){
	// whichBrickType = 5;
	// }else if(temp ==1){
	// whichBrickType = 5;
	// }else {
	// whichBrickType = 5;
	// }
	// }else if(playGameLevel == 4 || playGameLevel ==5){
	// int temp = random.nextInt(4);
	// if(temp ==0){
	// whichBrickType = 2;
	// }else if(temp ==1){
	// whichBrickType = 3;
	// }else if(temp ==2){
	// whichBrickType = 5;
	// }else {
	// whichBrickType = 6;
	// }
	// }else if(playGameLevel == 6 || playGameLevel ==7){
	// int temp = random.nextInt(6);
	// if(temp ==0){
	// whichBrickType = 0;
	// }else if(temp ==1){
	// whichBrickType = 1;
	// }else if(temp ==2){
	// whichBrickType = 2;
	// }else if(temp ==3){
	// whichBrickType = 4;
	// }else if(temp ==4){
	// whichBrickType = 5;
	// }else {
	// whichBrickType = 6;
	// }
	// }else if(playGameLevel == 8 || playGameLevel ==9){
	// whichBrickType = random.nextInt(7);
	// }

//	do {
//		// 5��
//		if (playGameLevel == 0)
//			whichBrickType = Brick_Once;
//		else if (playGameLevel == 1) {
//			int temp = random.nextInt(3);
//			if (temp == 0) {
//				whichBrickType = Brick_Twice;
//			} else if (temp == 1) {
//				whichBrickType = Brick_Tool;
//			} else {
//				whichBrickType = Brick_BallLevelUP;
//			}
//		} else if (playGameLevel == 2) {
//			int temp = random.nextInt(4);
//			if (temp == 0) {
//				whichBrickType = Brick_Three;
//			} else if (temp == 1) {
//				whichBrickType = Brick_Iron;
//			} else if (temp == 2) {
//				whichBrickType = Brick_Tool;
//			} else {
//				whichBrickType = Brick_BallLevelUP;
//			}
//		} else if (playGameLevel == 3) {
//			int temp = random.nextInt(5);
//			if (temp == 0) {
//				whichBrickType = Brick_Twice;
//			} else if (temp == 1) {
//				whichBrickType = Brick_Three;
//			} else if (temp == 2) {
//				whichBrickType = Brick_Time;
//			} else if (temp == 3) {
//				whichBrickType = Brick_Tool;
//			} else {
//				whichBrickType = Brick_BallLevelUP;
//			}
//		} else if (playGameLevel == 4) {
//			int temp = random.nextInt(6);
//			if (temp == 0) {
//				whichBrickType = Brick_Twice;
//			} else if (temp == 1) {
//				whichBrickType = Brick_Three;
//			} else if (temp == 2) {
//				whichBrickType = Brick_Iron;
//			} else if (temp == 3) {
//				whichBrickType = Brick_Time;
//			} else if (temp == 4) {
//				whichBrickType = Brick_Tool;
//			} else {
//				whichBrickType = Brick_BallLevelUP;
//			}
//		}
//	} while (BrickMaxConfig.isBrickMaxConfigEnable()
//			&& BrickMaxConfig.isBrickOverMax(whichBrickType));
	
	int temp = random.nextInt(6);
	if (temp == 0) {
		whichBrickType = Brick_Twice;
	} else if (temp == 1) {
		whichBrickType = Brick_Three;
	} else if (temp == 2) {
//		whichBrickType = Brick_Iron;
//		whichBrickType = Brick_Once;
		
	} else if (temp == 3) {
		whichBrickType = Brick_Time;
	} else if (temp == 4) {
		whichBrickType = Brick_Tool;
	} else {
		whichBrickType = Brick_BallLevelUP;
	}
	setBitmapByBrickType(whichBrickType);
	setEffect(whichBrickType);
}

public void setType(int type){
	if (type == 0) {
		whichBrickType = Brick_Once;
	} else if (type == 1) {
		whichBrickType = Brick_Twice;
	} else if (type == 2) {
//		whichBrickType = Brick_Iron;
//		whichBrickType = Brick_Once;
		whichBrickType = Brick_Three;
	} else if (type == 3) {
		whichBrickType = Brick_Time;
	} else if (type == 4) {
		whichBrickType = Brick_Tool;
	} else if (type == 7){
		whichBrickType = Brick_SplitTwo;
	} else{
		whichBrickType = Brick_BallLevelUP;
	}
	
	setBitmapByBrickType(whichBrickType);
	setEffect(whichBrickType);
}

private void setBitmapByBrickType(int whichBrickType) {
	switch (whichBrickType) {
	case 0:
		bitmap = BitmapUtil.brick_once_bmp;
		break;
	case 1:
		bitmap = BitmapUtil.brick_twice_bmp;
		break;
	case 2:
		bitmap = BitmapUtil.brick_three_bmp;
		break;
	case 3:
		bitmap = BitmapUtil.brick_iron_bmp;
		break;
	case 4:
		bitmap = BitmapUtil.brick_time_bmp;
		break;
	case 5:
		bitmap = BitmapUtil.brick_tool_bmp;
		break;
	case 6:
		bitmap = BitmapUtil.brick_ball_level_up_bmp;
		break;
	case 7:
		bitmap = BitmapUtil.brick_iron_bmp;
	}
}

private void setEffect(int whichBrickType) {
	effectUtil = new EffectUtil(ballView, this);
	effectUtil.setEffect(whichBrickType);
}

public EffectUtil getEffect() {
	return effectUtil;
}

public void doHitEffect(Bullet ball,
		ArrayList<EffectUtil> showTimeBrickEffectTime) {
//	effectUtil.doEffect(ball, showTimeBrickEffectTime);
	effectUtil.doEffect();
	Bitmap newBrickBmp = getNewBrickBmpAfterHit(effectUtil
			.getNeedHitCount());
	if (newBrickBmp != null) {
		bitmap = newBrickBmp;
	}
}

public boolean isBrickExist() {
	return effectUtil.getNeedHitCount() != 0;
}

public Bitmap getBrickBitmap() {
	return bitmap;
}

public RectF getRect() {
	return dst;
}

public boolean isHitIronBrick() {
	if (whichBrickType == 3 && effectUtil.ironsCombo) {
		return true;
	}
	return false;
}

public Bitmap getNewBrickBmpAfterHit(int needHitCount) {
	Bitmap bitmap = null;
	switch (needHitCount) {
	case 1:
		bitmap = BitmapUtil.brick_once_bmp;
		break;
	case 2:
		bitmap = BitmapUtil.brick_twice_bmp;
		break;
	case -1:
		bitmap = BitmapUtil.brick_iron_break_bmp;
		break;
	default:
		break;
	}
	return bitmap;
}

public boolean isIronsBrick() {
	return whichBrickType == 3;
}

public float getAngle(){
	return angle;
}

public void setReflect(){
	setMovementAction(movementActionShoot);
}

@Override
public void setMoveRage(RectF moveRage) {
	// TODO Auto-generated method stub
	super.setMoveRage(moveRage);
	limit = moveRage.bottom;
	if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
		limit = CommonUtil.cat_bg_height;
	}
}

@Override
public void setMoveRage(float x, float y, float height, float width){
	super.setMoveRage(x, y, height, width);
	limit = moveRage.bottom;
	if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
		limit = CommonUtil.cat_bg_height;
	}
}

public Bullets newHand(int type_direction){
	Bullets hand =new Bullets(context, getX(), getY(), false, type_direction);
	hand.setPosition(getX(), getY());
	hand.setMoveRage(new RectF(moveRage.left, moveRage.top, moveRage.right, moveRage.bottom));
//	hand.setMoveRage(0, 0, CommonUtil.gameBoardHeight,
//			CommonUtil.screenWidth);
	return hand;
	
}

int count = 0;
int max_count = 3;
int level = 1;

@Override
public void drawSelf(Canvas canvas, Paint paint) {
	// TODO Auto-generated method stub
	
	if(getEffect().isHasTool()){
		float toolWidth = getEffect().getToolObj().toolBitmap.getWidth();
		float toolHeight = getEffect().getToolObj().toolBitmap.getHeight();
		canvas.drawBitmap(getEffect().getToolObj().toolBitmap, null, new RectF(getX()+ w/2 - toolWidth/2,getY() + h/2 - toolHeight/2 ,getX()+w/2+toolWidth/2,getY() + h/2 + toolHeight/2), paint);
//		canvas.drawBitmap(getEffect().getToolObj().toolBitmap, null, new RectF(getX()+ w/2 - toolWidth/2,getY() + h/2 - toolHeight/2 ,getX()+w/2+toolWidth/2,getY() + h/2 + toolHeight/2), paint);
	}
	
	
//	if(spriteMatrix!=null){
//		spriteMatrix = new Matrix();
//		spriteMatrix.postTranslate(getX(), getY());
//		spriteMatrix.postScale((float)Math.pow(0.9, level),(float)Math.pow(0.9, level),getX()/2,getY()/2);
//		spriteMatrix.postScale((float)Math.pow(0.99, level),(float)Math.pow(0.99, level),getX(),getY());
//	}
	
		if(drawWithoutClip){
			count++;
			
		if(level>16){
			count=0;
			level=1;
			drawWithoutClip = false;
//			isMoveable=true;
			setX(getX()+getFrameWidth()-getBitmapOrginalFrameWidth());
			setY(getY()+getFrameHeight()-getBitmapOrginalFrameHright());
			resetFrameWH();
			setWidth((int)getFrameWidth());
			setHeight((int)getFrameHeight());
			
		}
		
		
		if(count>max_count*level){
			
			
//			Matrix matrix = new Matrix();
//			matrix.mapRect(dst);
//			matrix.postTranslate(getX(), getY());
//			matrix.postRotate(30, getX(),getY());
			
			if(level<=8){
				setFrameWidth(new BigDecimal(getFrameWidth()*1.03).setScale(10,
						BigDecimal.ROUND_HALF_EVEN).floatValue());
				setFrameHeight(new BigDecimal(getFrameHeight()*0.97).setScale(10,
						BigDecimal.ROUND_HALF_EVEN).floatValue());
				setX(getX()+w/2.0f-getFrameWidth()/2.0f);
				setY(getY()+h-getFrameHeight());
				setWidth((int)getFrameWidth());
				setHeight((int)getFrameHeight());
				
			}else{
				isMoveable = true;
				setFrameWidth(new BigDecimal(getFrameWidth()*0.97).setScale(10,
						BigDecimal.ROUND_HALF_EVEN).floatValue());
				setFrameHeight(new BigDecimal(getFrameHeight()*1.03).setScale(10,
						BigDecimal.ROUND_HALF_EVEN).floatValue());
//				setX(getX()+w/2.0f-getFrameWidth()/2.0f);
				setY(getY()+h-getFrameHeight());
				setWidth((int)getFrameWidth());
				setHeight((int)getFrameHeight());
				
			}
//			matrix.postTranslate(getX(), getY());
//			matrix.postScale((float)Math.pow(0.9, level),(float)Math.pow(0.9, level),getX()/2,getY()/2);
//			matrix.postScale((float)Math.pow(0.9, level),(float)Math.pow(0.9, level));
//			matrix.postScale((float)Math.pow(0.99, level),(float)Math.pow(0.99, level),getX(),getY());
//			spriteMatrix = matrix;
			level++;
			
			}
		}
	

		Matrix matrix = new Matrix();
//		matrix.mapRect(dst);
//		matrix.postTranslate(getX(), getY());
		matrix.postRotate(-90, getX()+w/2.0f,getY()+h/2.0f);
		spriteMatrix = matrix;
//		canvas.drawBitmap(bitmap, matrix, paint);
	
	
	super.drawSelf(canvas, paint);
}

public void destory(){
	movementAction.controller.cancelAllMove();
}

interface BulletsEventListener{
	void willAttack(BattleableSprite battleableSprite);
}

BulletsEventListener bulletsEventListener;

public void setBulletsEventListener(BulletsEventListener bulletsEventListener){
	this.bulletsEventListener = bulletsEventListener;
}

@Override
public void attack(BattleableSprite battleable) {
	// TODO Auto-generated method stub
//	super.attack(battleable);
	
	bulletsEventListener.willAttack(battleable);
	
		IEffect effect = getWeapenEffect();
		if(getWeapenEffect()!=null)
			effect.doEffect(this, battleable);
}

@Override
public void checkIfInBattleRangeThenAttack(
		List<BattleableSprite> battleables) {
	// TODO Auto-generated method stub
	for(BattleableSprite battleableSprite : battleables){
		boolean isInBattleRange = isInBattleRange(battleableSprite);
		if(isInBattleRange){
			attack(battleableSprite);
		}
	}
}

}
