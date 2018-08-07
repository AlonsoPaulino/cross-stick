package com.alonso.crossstick.game.util;

public class Util {
	
	public static final Integer NORMAL_STATE = 0;
	public static final Integer FALL_OUT_STATE = 1;
	public static final Integer GROW_UP_STATE = 2;
	public static final Integer IS_LYING = 4;
	public static final Integer IS_MOVING = 5;
	public static final Integer IS_FALLING = 6;
	public static final Integer FINISH_MOVING_ON_THE_BRIDGE = 7;
	public static final Integer DOUBLE_POINTS = 8;
	public static final Integer COIN_POINTS = 9;	
	public static final Integer FALL_OUT_LOSE = 10;
	public static final Integer PRICES[] = {0,25,75,150,250,300,400,500};
	
	public static final float HEIGTH_TOWER = 380;
	public static final float BRIDGE_WIDTH = 5;
	public static final float TIME_RESET = 0.2f;
	public static final float TOWER_INITIAL_WIDTH = 120;
	
	public static float TOWER_MIN_WIDTH = 25;
	public static float TOWER_MAX_WIDTH = 120;
	public static float TOWER_MIN_X = 220;
	public static float TOWER_MAX_X = 410;
	
	public static int NUM_CHARACTERS = 8;
	
	public static float randomFloat(float limite_inferior, float limite_superior){
		return (float) (Math.random()*((limite_superior - limite_inferior) + 1) + limite_inferior);
	}
	
	public static int randomInteger(int limite_inferior, int limite_superior){
		return (int)(Math.random()*((limite_superior - limite_inferior) + 1 )+ limite_inferior);
	}
}
