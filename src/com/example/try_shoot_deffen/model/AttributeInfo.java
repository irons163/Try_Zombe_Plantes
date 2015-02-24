package com.example.try_shoot_deffen.model;

public class AttributeInfo {
	private int hp;
	private int atk;
	private int def;
	
	public AttributeInfo(){
		
	}
	
	public AttributeInfo(AttributeInfo attributeInfo){
		this.hp = attributeInfo.hp;
		this.atk = attributeInfo.atk;
		this.def = attributeInfo.def;
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
}
