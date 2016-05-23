package br.edu.utfpr.tsi.pong;

import br.edu.utfpr.tsi.pong.bot.BotEasy;
import br.edu.utfpr.tsi.pong.bot.BotHard;
import br.edu.utfpr.tsi.pong.obj.SimpleBall;
import br.edu.utfpr.tsi.pong.obj.SimpleBar;
import br.edu.utfpr.tsi.pong.spi.Bot;
import jgame.JGColor;
import jgame.platform.JGEngine;

public final class Game extends JGEngine {
	private static final long serialVersionUID = 1L;	
	private SimpleBall ball;
	private String endMessage;
		
	public Game() {
		setFrameRate(Const.FPS, 0);
		initEngine(Const.SCREEN_WIDTH*2, Const.SCREEN_HEIGHT*2);
	}
	
	@Override
	public void initCanvas() {
		setCanvasSettings(Const.TILE_NUM_X,
				Const.TILE_NUM_Y,
				Const.TILE_SIZE_X,
				Const.TILE_SIZE_Y);
	}
	
	@Override
	public void initGame() {
		ball = new SimpleBall(this);
		Bot bot1 = new BotEasy();
		Bot bot2 = new BotHard();
		SimpleBar bar1 = new SimpleBar(this, Player.INITIAL_X_1, Player.COLOR_1, bot1);
		SimpleBar bar2 = new SimpleBar(this, Player.INITIAL_X_2, Player.COLOR_2, bot2);
		bot1.init(1, ball, bar1, bar2);
		bot2.init(2, ball, bar1, bar2);
	}
	
	@Override
	public void doFrame() {
		if (isOver()) {
			finish();
		} else {
			moveObjects();
			checkCollision(1, 1);
		}
	}
	
	private boolean isOver() {
		return ball.reachedAnyLimit();
	}
	
	private void finish() {
		if (endMessage == null) {
			endMessage = "PLAYER " + (ball.reachedLeftLimit() ? "2" : "1") + " WON!";
		}
	}
	
	@Override
	public void paintFrame() {
		if (endMessage != null) {
			drawString(endMessage, viewWidth()/2, viewHeight()/2, 0, Const.GAME_FONT, JGColor.white);
		}
	}
	
}