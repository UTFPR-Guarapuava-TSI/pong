package br.edu.utfpr.tsi.pong.bot;

import br.edu.utfpr.tsi.pong.Context;
import br.edu.utfpr.tsi.pong.spi.Ball;
import br.edu.utfpr.tsi.pong.spi.Bar;
import br.edu.utfpr.tsi.pong.spi.Bot;

public class BotEasy implements Bot {
	private Bar me;
	private Ball ball;
	
	@Override
	public void init(int playerNumber, Context context) {
		ball = context.ball;
		if (playerNumber == 1) {
			me = context.player1;
		} else {
			me = context.player2;
		}
	}
	
	@Override
	public int calculate() {
		return (int)Math.signum(me.getY() - ball.getY());
	}
	
}
