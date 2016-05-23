package br.edu.utfpr.tsi.pong.obj;

import br.edu.utfpr.tsi.pong.Const;
import br.edu.utfpr.tsi.pong.spi.Bot;
import br.edu.utfpr.tsi.pong.spi.Direction;
import br.edu.utfpr.tsi.pong.spi.VerticalBar;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class SimpleBar extends JGObject implements VerticalBar {
	private static final String NAME = "bar";
	private static final boolean UNIQUE_ID = true;
	private static final int POSITION_START_Y = Const.SCREEN_CENTER_Y;
	private static final String SPRITE = null;
	
	public static final int WIDTH = 8;
	private static final int HEIGHT = 40;
	private static final int SCREEN_LIMIT_TOP = HEIGHT/2;
	private static final int SCREEN_LIMIT_BOTTOM = Const.SCREEN_HEIGHT - HEIGHT/2;
	
	private static final int MOVE_MAX_PIXELS_PER_SECOND = 60;
	private static final int FRAME_THRESHOLD = Const.FPS/MOVE_MAX_PIXELS_PER_SECOND;

	private final JGColor color;
	private final JGEngine engine;
	private Bot bot;
	private int frameCount;

	public SimpleBar(final JGEngine engine, int x, JGColor color, Bot bot) {
		super(NAME, UNIQUE_ID, x, POSITION_START_Y, Const.COLLISION_ID_GLOBAL, SPRITE);

		this.engine = engine;
		this.color = color;
		this.bot = bot;
		setBBox(-WIDTH/2, -HEIGHT/2, WIDTH, HEIGHT);
	}

	@Override
	public void move() {
		frameCount++;
		if (frameCount < FRAME_THRESHOLD) {
			return;
		}
		frameCount = 0;
		
		final Direction move = bot.calculate();
		if (move == Direction.UP) {
			y -= 3;
			if (y < SCREEN_LIMIT_TOP) {
				y = SCREEN_LIMIT_TOP;
			}
		} else if (move == Direction.DOWN) {
			y += 3;
			if (y > SCREEN_LIMIT_BOTTOM) {
				y = SCREEN_LIMIT_BOTTOM;
			}
		}
	}

	@Override
	public void paint() {
		engine.setColor(color);
		engine.drawRect(x, y, WIDTH, HEIGHT, true, true);
	}
	
	@Override
	public double getY() {
		return y;
	}
	
}
