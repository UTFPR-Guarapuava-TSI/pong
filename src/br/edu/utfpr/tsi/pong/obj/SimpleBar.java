package br.edu.utfpr.tsi.pong.obj;

import jgame.JGObject;
import jgame.platform.JGEngine;
import br.edu.utfpr.tsi.pong.App;
import br.edu.utfpr.tsi.pong.spi.Bar;
import br.edu.utfpr.tsi.pong.spi.Bot;

public class SimpleBar extends JGObject implements Bar {
	private static final String NAME = "bar";
	private static final boolean UNIQUE_ID = true;
	private static final int POSITION_START_Y = App.HEIGHT/2;
	private static final String SPRITE = null;
	
	protected static final int WIDTH = 8;
	private static final int HEIGHT = 40;
	private static final int SCREEN_Y_MIN = HEIGHT/2;
	private static final int SCREEN_Y_MAX = App.HEIGHT - SCREEN_Y_MIN;
	private static final int MAX_PX_BY_SECS = 60;
	private static final int FRAME_THRESHOLD = App.FPS/MAX_PX_BY_SECS;

	private final Player player;
	private final JGEngine engine;
	private Bot bot;
	private int frameCount;

	public SimpleBar(final JGEngine engine, final Player player) {
		super(NAME, UNIQUE_ID, 
				player.getPositionStartX(), POSITION_START_Y,
				App.COLLISION_ID_GLOBAL, SPRITE);

		this.engine = engine;
		this.player = player;
		setBBox(-WIDTH/2, -HEIGHT/2, WIDTH, HEIGHT);
	}
	
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	public void move() {
		frameCount++;
		if (frameCount < FRAME_THRESHOLD) {
			return;
		}
		frameCount = 0;
		
		final int move = bot.calculate();
		if (move == 1) {
			if (y > SCREEN_Y_MIN) {
				y -= 3;
				if (y < SCREEN_Y_MIN) {
					y = SCREEN_Y_MIN;
				}
			}
		} else if (move == -1) {
			if (y < SCREEN_Y_MAX) {
				y += 3;
				if (y > SCREEN_Y_MAX) {
					y = SCREEN_Y_MAX;
				}
			}
		}
	}

	public void paint() {
		engine.setColor(player.getColor());
		engine.drawRect(x, y, WIDTH, HEIGHT, true, true);
	}
	
	@Override
	public double getY() {
		return y;
	}
	
}
