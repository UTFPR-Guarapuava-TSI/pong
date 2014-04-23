package br.edu.utfpr.tsi.pong;

import br.edu.utfpr.tsi.pong.spi.Ball;
import br.edu.utfpr.tsi.pong.spi.Bar;

public final class Context {

	public final Ball ball;
	public final Bar player1;
	public final Bar player2;
	
	public Context(Ball ball, Bar player1, Bar player2) {
		this.ball = ball;
		this.player1 = player1;
		this.player2 = player2;
	}
	
}
