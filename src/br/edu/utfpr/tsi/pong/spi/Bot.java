package br.edu.utfpr.tsi.pong.spi;

import br.edu.utfpr.tsi.pong.Context;


public interface Bot {

	/**
	 * Initialize your bot.
	 * @param playerNumber 1 or 2
	 * @param context contains the ball and the players
	 */
	void init(int playerNumber, Context context);
	/**
	 * Should calculate your move.
	 * @return 1 up, -1 down, other stay
	 */
	int calculate();
	
}
