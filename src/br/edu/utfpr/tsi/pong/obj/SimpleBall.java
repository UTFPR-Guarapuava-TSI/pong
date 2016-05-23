package br.edu.utfpr.tsi.pong.obj;

import java.util.Random;

import br.edu.utfpr.tsi.pong.Const;
import br.edu.utfpr.tsi.pong.spi.Ball;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class SimpleBall extends JGObject implements Ball {
	private static final String NAME = "ball";
	private static final boolean UNIQUE_ID = true;
	private static final String SPRITE = null;
	private static final int BALL_RADIUS = 4;
	private static final int SCREEN_LIMIT_LEFT = BALL_RADIUS;
	private static final int SCREEN_LIMIT_RIGHT = Const.SCREEN_WIDTH - BALL_RADIUS;
	private static final int SCREEN_LIMIT_TOP = BALL_RADIUS;
	private static final int SCREEN_LIMIT_BOTTOM = Const.SCREEN_HEIGHT - BALL_RADIUS;
	private static final int MAX_SPEED = 7;
	private static final double ACCELERATION = 1.05;

	private final Random random;
	private final JGEngine engine;
	private double radianAngle;
	private JGColor ballColor;
	private double ballSpeed;
	
	public SimpleBall (final JGEngine engine) {
		super(NAME, UNIQUE_ID, Const.SCREEN_CENTER_X, Const.SCREEN_CENTER_Y, Const.COLLISION_ID_GLOBAL, SPRITE);

		this.engine = engine;
		
		random = new Random();
		radianAngle = random.nextInt(16)/100.0 + 0.92;
		radianAngle += random.nextInt(2); // add zero or 1 PI
		radianAngle *= Math.PI;
		ballSpeed = 1.5;
		xspeed = Math.cos(radianAngle) * ballSpeed;
		yspeed = Math.sin(radianAngle) * ballSpeed;
		ballColor = JGColor.blue;
		setBBox(-BALL_RADIUS, -BALL_RADIUS, BALL_RADIUS*2, BALL_RADIUS*2);
	}

	@Override
	public void move() {
		if (y > SCREEN_LIMIT_BOTTOM && yspeed > 0 || y < SCREEN_LIMIT_TOP && yspeed < 0) {
			yspeed = -yspeed;
		}
	}
	
	@Override
	public void hit(JGObject obj) {
		invertDirection();
		if (!isMaxSpeed()) {
			increaseSpeed();
			changeBallColor();
		}
	}
	
	private void invertDirection() {
		xspeed = -xspeed;
	}
	
	private boolean isMaxSpeed() {
		return Math.abs(xspeed) >= MAX_SPEED;
	}
	
	private void increaseSpeed() {
		xspeed *= ACCELERATION;
		
		yspeed = Math.signum(yspeed) * random(0,xspeed*1.25);
		if (Math.abs(xspeed) > MAX_SPEED) {
			xspeed = Math.signum(xspeed) * MAX_SPEED;
		}
	}
	
	/**
	 * From blue to red
	 */
	private void changeBallColor() {
		final int tempColor = (int)(255-(255/MAX_SPEED)*Math.abs(xspeed));
		ballColor = new JGColor(255-tempColor, 0, tempColor);
	}

	@Override
	public void paint() {
		engine.setColor(ballColor);
		engine.drawOval(x,y,BALL_RADIUS*2,BALL_RADIUS*2,true,true);
	}
	
	public boolean reachedAnyLimit() {
		return reachedLeftLimit() || reachedRightLimit();
	}
	
	public boolean reachedLeftLimit() {
		return x <= SCREEN_LIMIT_LEFT;
	}
	
	public boolean reachedRightLimit() {
		return x >= SCREEN_LIMIT_RIGHT;
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
