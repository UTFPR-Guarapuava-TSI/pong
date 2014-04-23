package br.edu.utfpr.tsi.pong.obj;

import br.edu.utfpr.tsi.pong.App;
import jgame.JGColor;

public class Player {
	public static final Player PLAYER_1 = new Player(1);
	public static final Player PLAYER_2 = new Player(2);

	private final int x;
	private final JGColor color;
	
	private Player(final int number) {
		if (number == 1) {
			color = JGColor.cyan;
			x = SimpleBar.WIDTH/2;
		} else {
			color = JGColor.yellow;
			x = App.WIDTH - SimpleBar.WIDTH/2;
		}
	}
	
	public int getPositionStartX() {
		return x;
	}
	
	public JGColor getColor() {
		return color;
	}
	
}
