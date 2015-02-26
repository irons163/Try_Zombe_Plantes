package com.example.try_shoot_deffen.model;

import java.math.BigDecimal;
import java.util.Random;

import com.example.try_shoot_deffen.utils.Attribute;
import com.example.try_shoot_deffen.utils.AttributeHelper;
import com.example.try_shoot_deffen.utils.CommonUtil;

public class ZombeBuilder {
	Attribute attribute;
	AttributeHelper attributeHelper;
	long lastShootTime;
	
	public ZombeBuilder() {
		// TODO Auto-generated constructor stub
		initAttribute();
	}
	
	private void initAttribute() {
		attribute = new Attribute();
		float interval = new BigDecimal(1.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}
	
	public Zombe createZombe(){
		long currentTime = System.currentTimeMillis();
		if (!attributeHelper.isCanShoot(lastShootTime, currentTime)) {
			return null;
		}

		lastShootTime = currentTime;
		
		Random random = new Random();
		int y = random.nextInt(CommonUtil.screenHeight/2);
		
		Zombe zombe = new Zombe(800, 100, false);
		zombe.setPosition(1000, 200 + y);
		
		return zombe;
	}
}
