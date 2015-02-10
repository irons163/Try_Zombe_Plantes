package com.example.try_shoot_deffen.utils;

import java.util.ArrayList;

import com.example.try_shoot_deffen.MainActivity;
import com.example.try_shoot_deffen.MyGameModel;

public class TimerThread extends Thread {
	private int time;
	private ToolUtil tool;
	private EffectUtil effect;
	private boolean flag = true;
	private Object Lock = new Object();

	public TimerThread(int time) {
		this.time = time;
	}

	public TimerThread(int time, ToolUtil tool) {
		this.time = time;
		this.tool = tool;
	}

	public TimerThread(int time, EffectUtil effect) {
		this.time = time;
		this.effect = effect;
	}

	@Override
	public void run() {
		
		while (flag && time != 0) {
			while(MyGameModel.gameFlag && !MyGameModel.waitGameSuccessProcessing){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (MainActivity.Lock) {
//				if (MainActivity.GAME_PAUSE_FLAG)
//					try {
//						MainActivity.Lock.wait();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				if (time > 0)
					time--;
			}
			if(MyGameModel.GAME_PAUSE_FLAG){
				synchronized (MyGameModel.LOCK) {
					try {
						MyGameModel.LOCK.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}}
	}

	public void cancel() {
		flag = false;
	}

	public int getCurrentTime() {
		return time;
	}

	public void setCurrentTime(int time) {
		synchronized (MainActivity.Lock) {
			this.time = time;
		}
	}
	
	public void stopTimer(){
		MyGameModel.GAME_PAUSE_FLAG = true;
	}
	
	public void resumeTimer(){
		MyGameModel.GAME_PAUSE_FLAG = false;
		synchronized (MyGameModel.LOCK) {
			MyGameModel.LOCK.notifyAll();
		}
	}
	
	
}
