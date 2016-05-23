package br.edu.utfpr.tsi.pong.bot;

import br.edu.utfpr.tsi.pong.spi.Ball;
import br.edu.utfpr.tsi.pong.spi.Bot;
import br.edu.utfpr.tsi.pong.spi.Direction;
import br.edu.utfpr.tsi.pong.spi.VerticalBar;

public class BotEasy implements Bot {
	private VerticalBar me;
	private Ball ball;
	
	@Override
	public void init(int yourPlayerNumber, Ball ball, VerticalBar player1, VerticalBar player2) {
		this.ball = ball;
		if (yourPlayerNumber == 1) {
			me = player1;
		} else {
			me = player2;
		}
	}
	
	/**
	 * Follow the ball Y
	 */
	@Override
	public Direction calculate() {
		return Math.signum(me.getY() - ball.getY()) == 1 ? Direction.UP : Direction.DOWN;
	}
	
}
