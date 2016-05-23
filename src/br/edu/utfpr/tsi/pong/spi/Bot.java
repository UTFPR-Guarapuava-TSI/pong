package br.edu.utfpr.tsi.pong.spi;

public interface Bot {

	/**
	 * Called only once
	 * @param yourPlayerNumber 1 or 2
	 */
	void init(int yourPlayerNumber, Ball ball, VerticalBar player1, VerticalBar player2);
	
	/**
	 * Should calculate your move.
	 */
	Direction calculate();
	
}
