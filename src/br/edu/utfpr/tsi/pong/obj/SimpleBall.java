package br.edu.utfpr.tsi.pong.obj;

import java.util.Random;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;
import br.edu.utfpr.tsi.pong.App;
import br.edu.utfpr.tsi.pong.spi.Ball;

public class SimpleBall extends JGObject implements Ball {
	private static final String NAME = "ball";
	private static final boolean UNIQUE_ID = true;
	private static final int POSITION_START_X = App.WIDTH/2;
	private static final int POSITION_START_Y = App.HEIGHT/2;
	private static final String SPRITE = null;
	
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;
	private static final int SCREEN_X_MIN = WIDTH/2;
	private static final int SCREEN_X_MAX = App.WIDTH - SCREEN_X_MIN;
	private static final int SCREEN_Y_MIN = HEIGHT/2;
	private static final int SCREEN_Y_MAX = App.HEIGHT - SCREEN_Y_MIN;
	private static final int MAX_SPEED = 7;
	private static final double ACCELERATION = 1.05;

	private final Random RANDOM;
	private final JGEngine engine;
	private double radianAngle;
	private JGColor color;
	private int playerWin;
	private double ballSpeed;
	
	public SimpleBall (final JGEngine engine) {
		super(NAME, UNIQUE_ID, 
				POSITION_START_X, POSITION_START_Y,
				App.COLLISION_ID_GLOBAL, SPRITE);

		this.engine = engine;
		
		RANDOM = new Random();
//		 varia de 0.75 até 1.25
		radianAngle = RANDOM.nextInt(16)/100.0 + 0.92;
		radianAngle += RANDOM.nextInt(2); // esq ou dir
		radianAngle *= Math.PI;
		ballSpeed = 1.5;
		xspeed = Math.cos(radianAngle) * ballSpeed;
		yspeed = Math.sin(radianAngle) * ballSpeed;
		color = JGColor.blue;
		setBBox(-WIDTH/2, -HEIGHT/2, WIDTH, HEIGHT);
	}

	public void move() {
		if (y > SCREEN_Y_MAX && yspeed > 0
				|| y < SCREEN_Y_MIN && yspeed<0) {
			yspeed = -yspeed;
		}
		
		if (x > SCREEN_X_MAX) {
			playerWin = 1;
		} else if (x < SCREEN_X_MIN) {
			playerWin = 2;
		}
	}

	public int getPlayerWin() {
		return playerWin;
	}
	
	@Override
	public void hit(JGObject obj) {
		xspeed = -xspeed;
		if (Math.abs(xspeed) < MAX_SPEED) {
			xspeed *= ACCELERATION;
//			xspeed = Math.cos(radianAngle) * ballSpeed;
//			yspeed = Math.sin(radianAngle) * ballSpeed;
			
			yspeed = Math.signum(yspeed) * random(0,xspeed*1.25);
			if (Math.abs(xspeed) > MAX_SPEED) {
				xspeed = Math.signum(xspeed) * MAX_SPEED;
			}
			final int tempColor = (int)(255-(255/MAX_SPEED)*Math.abs(xspeed));
			color = new JGColor(255-tempColor, 0, tempColor);
		}
	}

	public void paint() {
		engine.setColor(color);
		engine.drawOval(x,y,WIDTH,HEIGHT,true,true);
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
}
