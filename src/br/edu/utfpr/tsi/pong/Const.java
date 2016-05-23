package br.edu.utfpr.tsi.pong;

import jgame.JGFont;

public interface Const {
	public static final int FPS = 60;
	
	public static final int SCREEN_WIDTH = 320;
	public static final int SCREEN_HEIGHT = 240;
	public static final int SCREEN_CENTER_X = SCREEN_WIDTH/2;
	public static final int SCREEN_CENTER_Y = SCREEN_HEIGHT/2;
	public static final int TILE_NUM_X = 20;
	public static final int TILE_NUM_Y= 15;
	public static final int TILE_SIZE_X = SCREEN_WIDTH/TILE_NUM_X;
	public static final int TILE_SIZE_Y = SCREEN_HEIGHT/TILE_NUM_Y;
	
	
	public static final int COLLISION_ID_GLOBAL = 1;
	public static final JGFont GAME_FONT = new JGFont(null, 0, 25);
	
}
