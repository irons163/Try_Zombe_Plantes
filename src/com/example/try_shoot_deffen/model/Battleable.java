package com.example.try_shoot_deffen.model;

public interface Battleable {
	public boolean isInBattleRange(Battleable battleable);
	public void attack(Battleable battleable);
}
