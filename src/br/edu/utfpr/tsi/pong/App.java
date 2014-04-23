package br.edu.utfpr.tsi.pong;

import jgame.JGColor;
import jgame.JGFont;
import jgame.platform.JGEngine;
import br.edu.utfpr.tsi.pong.bot.BotEasy;
import br.edu.utfpr.tsi.pong.bot.BotHard;
import br.edu.utfpr.tsi.pong.obj.Player;
import br.edu.utfpr.tsi.pong.obj.SimpleBall;
import br.edu.utfpr.tsi.pong.obj.SimpleBar;
import br.edu.utfpr.tsi.pong.spi.Bot;


public final class App extends JGEngine {
	private static final long serialVersionUID = 1L;
	public static final int FPS = 60;
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int TILE_QNT_X = 20;
	public static final int TILE_QNT_Y= 15;
	public static final int TILE_SIZE_X = 16;
	public static final int TILE_SIZE_Y = 16;
	public static final int COLLISION_ID_GLOBAL = 1;
	private static final JGFont FONT = new JGFont(null, 0, 25);
	
	private SimpleBall ball;
	private String endMessage;
	
	public static void main(String[] args) {
		new App();
	}

	public App() {
		initEngine(WIDTH*2, HEIGHT*2);
	}
	
	@Override
	public void initCanvas() {
		setCanvasSettings(
				TILE_QNT_X, TILE_QNT_Y,
				TILE_SIZE_X, TILE_SIZE_Y,
				null, null, null); // cor, cor fundo e fonte
	}
	
	@Override
	public void initGame() {
		setFrameRate(FPS, 0);
		ball = new SimpleBall(this);
		SimpleBar bar1 = new SimpleBar(this, Player.PLAYER_1);
		SimpleBar bar2 = new SimpleBar(this, Player.PLAYER_2);
		Context context = new Context(ball, bar1, bar2);
		Bot bot1 = new BotEasy();
		bot1.init(1, context);
		Bot bot2 = new BotHard();
		bot2.init(2, context);
		bar1.setBot(bot1);
		bar2.setBot(bot2);
		time = System.currentTimeMillis()+1000;
	}
	
	@Override
	public void doFrame() {
		if (ball.getPlayerWin() == 0) {
			moveObjects();
			checkCollision(1, 1);
		} else if (endMessage == null) {
			endMessage = "PLAYER " + ball.getPlayerWin() + " GANHOU!";
		}
	}
	
	long fps = 0;
	long fpsMedia = 0;
	long time;
	@Override
	public void paintFrame() {
		fps++;
		if (System.currentTimeMillis() >= time) {
			fpsMedia = fps;
			fps = 0;
			time = System.currentTimeMillis()+1000;
		}
		setColor(JGColor.white);
		
		if (endMessage == null) {
			drawString("FPS: " + fpsMedia, 
					viewWidth()/2, 
					viewHeight()/2, 
					0);
		} else {
			drawString(endMessage, 
					viewWidth()/2, 
					viewHeight()/2, 
					0,
					FONT,
					JGColor.white);
		}
	}
	
}
